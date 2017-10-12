package assigment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first = null;
	private Node last = null;
	private int size = 0;

	public Deque() {

	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		throwIfNull(item);
		Node oldFirst = first;
		first = new Node();
		first.value = item;
		first.next = oldFirst;
		first.previous = null;
		oldFirst.previous = first;
		size++;
		if (size == 1)
			last = first;
	}

	public void addLast(Item item) {
		throwIfNull(item);
		Node newLast = new Node();
		newLast.value = item;
		newLast.next = null;
		newLast.previous = last;
		last.next = newLast;
		last = newLast;
		size++;
		if (size == 1)
			first = last;
	}

	private void throwIfNull(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
	}

	public Item removeFirst() {
		throwIfEmpty();
		Item value = first.value;
		first = first.next;
		first.previous = null;
		size--;
		return value;
	}

	public Item removeLast() {
		throwIfEmpty();
		Item value = last.value;
		last = last.previous;
		last.next = null;
		size--;

		return value;
	}

	private void throwIfEmpty() {
		if (isEmpty())
			throw new NoSuchElementException();
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	public static void main(String[] args) {

	}

	private class Node {
		private Item value;
		private Node next = null;
		private Node previous = null;
	}

	private class ListIterator implements Iterator<Item> {
		Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();

			Item value = current.value;
			current = current.next;
			return value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}