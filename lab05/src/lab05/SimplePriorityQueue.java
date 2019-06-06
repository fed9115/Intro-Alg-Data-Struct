package lab05;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A simple priority queue generic class.
 * 
 * @author Erdi Fan, and Kyle Perry
 *
 * @param <E>
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>, Iterator<E> {

	// General array to hold our priority queue items.
	private E[] dataArray;

	// Capture the comparator if one exists
	private Comparator<? super E> comparator;

	// Denote the actual number of elements in the array
	private int numberOfElements;

	private int size;
	E[] queue;

	private int nextIndex;
	private boolean canRemove;

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (nextIndex < size) {
			return true;
		}
		return false;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		nextIndex++;
		canRemove = true;
		return queue[nextIndex - 1];
	}

	@Override
	public void remove() {
		if (!canRemove) {
			throw new IllegalStateException();
		}
		canRemove = false;
		nextIndex--;
		for (int i = nextIndex; i < size - 1; i++) {
			queue[i] = queue[i + 1];
		}
		size--;
	}

}

	/**
	 * If the priority queue is created using this constructor it is assumed that
	 * the elements in the priority queue are ordered using the natural ordering.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		numberOfElements = 0;
		comparator = null;
		dataArray = (E[]) new Object[10];
	}

	/**
	 * If the priority queue is created using this constructor it is assumed that
	 * the elements are ordered using the ordering defined by the provided
	 * comparator object.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> comparator) {
		numberOfElements = 0;
		this.comparator = comparator;
		dataArray = (E[]) new Object[10];
	}

	@Override
	public E findMin() throws NoSuchElementException {
		if (numberOfElements == 0) {
			throw new NoSuchElementException();
		}
		return dataArray[this.size() - 1];
	}

	@Override
	public E deleteMin() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		E min = this.findMin();
		numberOfElements--;
		return min;
	}

	public void insert(E item) {
		// If the item to be inserted is null do nothing
		if (item == null) {
			return;
		}

		// If the data array is full double its size to ensure room for insertion of
		// item
		this.checkArraySize();

		// If the dataArray is empty, add the item directly without comparison
		if (numberOfElements == 0) {
			dataArray[0] = item;
			numberOfElements++;
			return;
		} else {
			// Determine the position (index) where the item being inserted should be placed
			int insertPosition = binarySearch(item);

			// Make room for the item being inserted into the priority queue
			for (int i = this.size() - 1; i >= insertPosition; i--) {
				dataArray[i + 1] = dataArray[i];
			}

			// Insert the item being added to the priority queue at the correct position
			dataArray[insertPosition] = item;

			numberOfElements++;
		}
	}

	/*
	 * Check the size of the simple priority queue. If it is full, double its size.
	 */
	@SuppressWarnings("unchecked")
	public void checkArraySize() {
		if (this.size() == dataArray.length) {
			int newSize = dataArray.length * 2;
			E[] result = (E[]) new Object[newSize];
			for (int i = 0; i < dataArray.length; i++) {
				result[i] = dataArray[i];
			}
			dataArray = result;
		}
	}

	/**
	 * Generic compare method for comparing two elements based on Comparator or
	 * Comparable interface
	 * 
	 * @param item
	 * @param e
	 * @return negative number if item is less than e; positive number if item is
	 *         greater than e
	 */
	@SuppressWarnings("unchecked")
	private int compare(E item, E e) {
		// if we are using Comparable interface, sorting by the natural ordering
		if (comparator == null) {
			return (((Comparable<? super E>) item).compareTo(e));
		} else
			return comparator.compare(item, e);
	}

	/**
	 * Helper method for finding the index of the item in dataArray that should be
	 * inserted in
	 * 
	 * @return the index of item in dataArray
	 */
	private int binarySearch(E candidate) {
		int low = 0;
		int high = numberOfElements - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (compare(dataArray[mid], candidate) == 0)
				return mid;
			if (compare(dataArray[mid], candidate) > 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E element : coll) {
			this.insert(element);
		}
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		if (numberOfElements == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		numberOfElements = 0;
	}

	public int getLength() {
		return this.dataArray.length;
	}
}
