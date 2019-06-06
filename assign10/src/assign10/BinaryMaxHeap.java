package assign10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is a generic class to represent a binary max heap
 * 
 * @author Kyle Perry, Erdi Fan
 *
 * @param <E>
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private int numElement;
	private E[] backArr;
	private Comparator<? super E> cmp;

	/**
	 * If this constructor is used to create an empty binary heap, it is assumed
	 * that the elements are ordered using their natural ordering (i.e., E
	 * implements Comparable<? super E>).
	 */
	public BinaryMaxHeap() {
		numElement = 0;
		cmp = null;
		backArr = (E[]) new Object[10];
	}

	/**
	 * If this constructor is used to create an empty binary heap, it is assumed
	 * that the elements are ordered using the provided Comparator object.
	 * 
	 * @param functor
	 */
	public BinaryMaxHeap(Comparator<? super E> functor) {
		numElement = 0;
		cmp = functor;
		backArr = (E[]) new Object[10];
	}

	/**
	 * If this constructor is used, then the binary heap is constructed from the
	 * given list. Also, it is assumed that the elements are ordered using their
	 * natural ordering (i.e., E implements Comparable<? super E>).
	 * 
	 * @param list
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		numElement = 0;
		cmp = null;
		backArr = (E[]) new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	/**
	 * If this constructor is used, then the binary heap is constructed from the
	 * given list. Also, it is assumed that the elements are ordered using the
	 * provided Comparator object.
	 * 
	 * @param list
	 * @param functor
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> functor) {
		numElement = 0;
		cmp = functor;
		backArr = (E[]) new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	@Override
	public void add(E item) {
		if (numElement >= backArr.length - 1)
			backArr = Arrays.copyOf(backArr, backArr.length * 2);
		backArr[numElement] = item;
		percolateUp(numElement);
		numElement++;
	}

	/**
	 * Helper method to percolate item upwards
	 */
	private void percolateUp(int index) {
		while (index >= 0) {
			if (innerCompare(backArr[index], backArr[getParent(index)]) > 0) {
				swap(index, getParent(index));
			} else
				break;
			index = getParent(index);
		}
	}

	/**
	 * Helper method to swap the element of the two nodes
	 * 
	 * @param index
	 * @param parent
	 */
	private void swap(int index, int parent) {
		E temp = backArr[index];
		backArr[index] = backArr[parent];
		backArr[parent] = temp;
	}

	/**
	 * Helper method to get the index of the parent of the current node
	 * 
	 * @param index
	 * @return
	 */
	private int getParent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Helper metod to determine to use whether Comparable or Comparator and compare
	 * the parameters
	 * 
	 * @param e
	 * @param e2
	 */
	private int innerCompare(E e, E e2) {
		if (cmp == null)
			return ((Comparable<? super E>) e).compareTo(e2);
		return cmp.compare(e, e2);
	}

	@Override
	public E peek() throws NoSuchElementException {
		if (numElement == 0)
			throw new NoSuchElementException();
		return backArr[0];
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		E min = peek();
		numElement--;
		backArr[0] = backArr[numElement];
		percolateDown(0);
		return min;
	}

	/**
	 * Helper method to percolate item downwards
	 * 
	 * @param i
	 */
	private void percolateDown(int index) {
		while (index < numElement) {
			if (getLeftChild(index) >= numElement)
				break;
			int bigger = getLeftChild(index);
			if (getRightChild(index) < numElement) {
				if (innerCompare(backArr[getLeftChild(index)], backArr[getRightChild(index)]) < 0) {
					bigger = getRightChild(index);
				}
			}
			if (innerCompare(backArr[index], backArr[bigger]) < 0) {
				swap(index, bigger);
			} else
				break;
			index = bigger;
		}
	}

	/**
	 * Helper method to get the index of the left child of the current node
	 * 
	 * @param index
	 * @return
	 */
	private int getLeftChild(int index) {
		return 2 * index + 1;
	}

	/**
	 * Helper method to get the index of the right child of the current node
	 * 
	 * @param index
	 * @return
	 */
	private int getRightChild(int index) {
		return 2 * index + 2;
	}

	@Override
	public int size() {
		return numElement;
	}

	@Override
	public boolean isEmpty() {
		return numElement == 0;
	}

	@Override
	public void clear() {
		numElement = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[numElement];
		for (int i = 0; i < numElement; i++) {
			arr[i] = backArr[i];
		}
		return arr;
	}

}
