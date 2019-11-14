package no.hvl.dat103.boundbuffer_boken;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	int n = 10;
	Semafor mutex = new Semafor(1);
	Semafor empty = new Semafor(n);
	Semafor full = new Semafor(0);
	
	Queue<Integer> buffer = new LinkedList<Integer>();

	public void produser(Integer integer) {
		int item = integer;
		empty.vent();
		mutex.vent();
		buffer.add(item);
		mutex.signal();
		full.signal();
	}
	
	public Integer konsumer() {
		full.vent();
		mutex.vent();
		int item = buffer.remove();
		mutex.signal();
		empty.signal();
		return item;
	}
	
}