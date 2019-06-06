package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in
 * a list.
 * 
 * @author Erin Parker && Kyle Perry, Erdi Fan
 * @version April 3, 2019
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and
	 * the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		BinaryMaxHeap<E> bmh = new BinaryMaxHeap<>(items);
		List<E> list = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			list.add(bmh.extractMax());
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		BinaryMaxHeap<E> bmh = new BinaryMaxHeap<>(items, cmp);
		List<E> list = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			list.add(bmh.extractMax());
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine
	 * and the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		Collections.sort(items);
		List<E> list = new ArrayList<>();
		for (int i = items.size() - 1; i > items.size() - 1 - k; i--) {
			list.add(items.get(i));
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		Collections.sort(items, cmp);
		List<E> list = new ArrayList<>();
		for (int i = items.size() - 1; i > items.size() - 1 - k; i--) {
			list.add(items.get(i));
		}
		return list;
	}
}