
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first = null;
	private Node last = null;
	private int size = 0;

	public Deque() {

	}

	public boolean isEmpty() {
		return size==0;
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
		
		size++;
		if (size == 1)
			last = first;
		else
			oldFirst.previous = first;
	}

	public void addLast(Item item) {
		throwIfNull(item);
		Node newLast = new Node();
		newLast.value = item;
		newLast.next = null;
		newLast.previous = last;
		
		size++;
		if (size == 1)
			first = last;
		else
			last.next = newLast;
		last = newLast;
	}

	private void throwIfNull(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
	}

	public Item removeFirst() {
		throwIfEmpty();
		Item value = first.value;
		first = first.next;
		size--;
		if(isEmpty())
			last=null;
		else
			first.previous = null;
		
		return value;
	}

	public Item removeLast() {
		throwIfEmpty();
		Item value = last.value;
		last = last.previous;
		size--;
		if(isEmpty())
			first=null;
		else
			last.next = null;
		

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