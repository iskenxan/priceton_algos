package assigment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	//TODO: implement the iterator 
	Item[] items;
	int head = 0;
	int tail = 0;

	public RandomizedQueue() {
		items = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		return items.length == 0;
	}

	public int size() {
		return items.length;
	}

	public void enqueue(Item item) {
		throwIfNull(item);
		int size = tail - head;
		if (size == items.length)
			resize(2 * items.length);
		items[tail++] = item;
	}
	

	public Item dequeue() {
		throwIfEmpty();
		int size = tail - head;
		if (size == items.length / 4)
			resize(items.length / 2);
		int index=StdRandom.uniform(head,tail);
		Item item = items[index];
		items[head]=null;
		head++;

		return item;
	}
	
	private void throwIfNull(Item item) {
		if(item==null)
			throw new IllegalArgumentException();
	}
	
	private void throwIfEmpty() {
		if(isEmpty())
			throw new NoSuchElementException();
	}
	

	private void resize(int capacity) {
		int size = tail - head;
		assert capacity >= size;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = head; i < tail; i++)
			temp[i] = items[i];
		items = temp;
	}

	public Item sample() {
		throwIfEmpty();
		int index=StdRandom.uniform(head, tail);
		Item item=items[index];
		
		return item;
	}

	public Iterator<Item> iterator() {
		return null;

	}

	public static void main(String[] args) {

	}
}