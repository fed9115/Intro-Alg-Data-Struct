package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Create a generic class called SinglyLinkedList that implements this List
 * interface with a singly-linked list.
 * 
 * @author Kyle Perry, Erdi Fan
 *
 * @param <E>
 */

public class SinglyLinkedList<E> implements List<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;

	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * This method creates a new node of type E, containing the element parameter
	 * provided and this new node becomes the first one in the linked list.
	 */
	@Override
	public void addFirst(E element) {
		Node<E> newNode = new Node<E>(element);
		newNode.next = head;
		head = newNode;
		size++;

		if (tail == null)
			tail = head;
	}

	/**
	 * Insert an element as a new node of type E at the specified index.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (index == 0)
			addFirst(element);
		else if (index == size)
			addLast(element);
		else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next;
			current.next = new Node<>(element);
			current.next.next = temp;
			size++;
		}
	}

	/**
	 * Create a new node and append it to the end of the linked list and reset the
	 * tail.
	 * 
	 * @param element
	 */
	private void addLast(E element) {
		Node<E> newNode = new Node<E>(element);
		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	/**
	 * Return the first element in the linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (this.size == 0)
			throw new NoSuchElementException();
		return head.element;
	}

	/**
	 * Return the element at the specified index.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index > size + 1 || size == 0)
			throw new IndexOutOfBoundsException();
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;
	}

	/**
	 * Remove the first element from the linked list and return the element that was
	 * at the first of the linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		Node<E> temp = head;
		head = head.next;
		size--;
		// If the list becomes empty set tail to null
		if (head == null)
			tail = null;
		return temp.element;
	}

	/**
	 * Remove the element at the specified index from the linked list and return the
	 * element that was at the specified index that was removed.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index > size + 1 || size == 0)
			throw new IndexOutOfBoundsException();
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		Node<E> prev = head;
		for (int i = 1; i < index; i++) {
			prev = prev.next;
		}

		Node<E> current = prev.next;
		prev.next = current.next;
		size--;
		return current.element;
	}

	/**
	 * Remove the last element from the linked list and return the element that was
	 * removed
	 * 
	 * @return
	 */
	private E removeLast() {
		if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size--;
			return temp.element;
		} else {
			Node<E> current = head;
			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	/**
	 * Return of the index of the specified element, return -1 if there is no match.
	 */
	@Override
	public int indexOf(E element) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (current.element == element)
				return i;
			current = current.next;
		}
		return -1;
	}

	/**
	 * Return the size of the linked list.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Return true if the linked list is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Clear the linked list.
	 */
	@Override
	public void clear() {
		size = 0;
		head = tail = null;
	}

	/**
	 * Convert the linked list to an Object array. If the linked list is null return
	 * an empty array.
	 */
	@Override
	public Object[] toArray() {
		Object[] temp = new Object[size];
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			temp[i] = current.element;
			current = current.next;
		}
		return temp;
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	private class SinglyLinkedListIterator implements Iterator<E> {

		private Node<E> current = head;
		private boolean readyToRemove = false;
		private int prevIndex = -1;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new IndexOutOfBoundsException();
			E e = current.element;
			current = current.next;
			readyToRemove = true;
			prevIndex++;
			return e;
		}

		@Override
		public void remove() {
			if (readyToRemove == false)
				throw new IllegalStateException();
			SinglyLinkedList.this.remove(prevIndex);
			readyToRemove = false;
		}
	}

	private class Node<E> {
		E element;
		Node<E> next;

		Node(E e) {
			this.element = e;
		}
	}

}
