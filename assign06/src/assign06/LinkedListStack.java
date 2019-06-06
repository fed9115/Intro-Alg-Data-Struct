package assign06;

import java.util.NoSuchElementException;

/**
 * Create a generic stack class called LinkedListStack that implements this
 * Stack interface.
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

public class LinkedListStack<E> implements Stack<E> {

	/**
	 * The stack should be backed by an instance of our generic SinglyLinkedList
	 * class, stored as a private member variable.
	 */
	private SinglyLinkedList<E> stack;

	/**
	 * Include a zero-parameter constructor
	 */
	public LinkedListStack() {
		stack = new SinglyLinkedList<>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	@Override
	public void clear() {
		stack.clear();
	}

	/**
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Returns, but does not remove, the element at0
	 * 
	 * 
	 * the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		return stack.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E pop() throws NoSuchElementException {
		return stack.removeFirst();
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@Override
	public void push(E element) {

		stack.addFirst(element);

	}

	/**
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

}
