

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int head = 0;
	private int tail = 0;

	public RandomizedQueue() {
		items = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		return tail-head<=0;
	}

	public int size() {
		return tail-head;
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
		int index = StdRandom.uniform(head, tail);
		Item item = items[index];
		items[index]=items[head];
		items[head++] = null;

		return item;
	}

	private void throwIfNull(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
	}

	private void throwIfEmpty() {
		if (isEmpty())
			throw new NoSuchElementException();
	}

	private void resize(int capacity) {
		int size = tail - head;
		assert capacity >= size;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = head; i < tail; i++) {
			temp[i-head] = items[i];
		}
		head=0;
		tail=0;
		items = temp;
	}

	public Item sample() {
		throwIfEmpty();
		int index = StdRandom.uniform(head, tail);
		Item item = items[index];

		return item;
	}

	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {

		int currentIndex;

		public QueueIterator() {
			currentIndex = head;
		}

		@Override
		public boolean hasNext() {
			return items[currentIndex] != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = items[currentIndex];
			if (tail - head >= 0)
				currentIndex++;

			return item;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {

	}
}