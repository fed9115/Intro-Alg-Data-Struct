package assign05;

import java.util.ArrayList;
import java.util.Random;

/**
 * In this assignment, we perform experiments to see which of these two sorting
 * algorithms has the fastest running times for Java ArrayLists of various sizes
 * in the following three categories.
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */
public class ArrayListSorter {

	private static int threshold;
	private static int strategy;

	/**
	 * This method is a driver method performs a merge sort on the generic ArrayList
	 * given as input.
	 * 
	 * @param unsortedAL
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> unsortedAL) {
		if (unsortedAL == null)
			throw new NullPointerException();
		if (unsortedAL.size() < 2)
			return;
		ArrayList<T> temp = new ArrayList<>();
		for (T generObj : unsortedAL) {
			temp.add(generObj);
		}
		recursiveMerge(unsortedAL, temp, 0, unsortedAL.size() - 1);
	}

	/**
	 * The merge sort helper method (use for dividing) to do the sorting. It will
	 * switch over to insertion sort when the size of the sublist to be sorted meets
	 * the threshold.
	 * 
	 * @param unsortedAL
	 * @param container
	 * @param left
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void recursiveMerge(ArrayList<T> unsortedAL,
			ArrayList<T> container, int left, int right) {
		int subArrSize = right - left + 1;
		// If the array size has reached the threshold value call insertion sort
		if (subArrSize < threshold) {
			insertionSort(unsortedAL, left, right, subArrSize);
			return;
		}
		// If the array size has reached 1 or 0 return
		if (subArrSize <= 1) {
			return;
		}
		int mid = (left + right) / 2;
		recursiveMerge(unsortedAL, container, left, mid);
		recursiveMerge(unsortedAL, container, mid + 1, right);
		merge(unsortedAL, container, left, mid + 1, right);
	}

	/**
	 * The merge sort helper method (use for conquering) to do the sorting. Merge
	 * the two sorted lists together.
	 * 
	 * @param unsortedAL
	 * @param container
	 * @param left
	 * @param ctr
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> unsortedAL, ArrayList<T> container,
			int left, int ctr, int right) {
		int current1 = left;
		int current2 = ctr;
		int current3 = 0;
		int arrSize = right - left + 1;
		while (current1 < ctr && current2 <= right) {
			if (unsortedAL.get(current1).compareTo(unsortedAL.get(current2)) < 0) {
				container.set(current3++, unsortedAL.get(current1++));
			} else {
				container.set(current3++, unsortedAL.get(current2++));
			}
		}
		while (current1 < ctr) {
			container.set(current3++, unsortedAL.get(current1++));
		}
		while (current2 <= right) {
			container.set(current3++, unsortedAL.get(current2++));
		}
		// Copy the sorted values in the temp array back to the appropriate location in
		// the original array
		for (int tempInd = 0, unsortedInd = left; tempInd < arrSize; tempInd++, unsortedInd++) {
			unsortedAL.set(unsortedInd, container.get(tempInd));
		}

	}

	/**
	 * This generic method sorts the input array using an insertion sort using the
	 * compareTo method to sort with the natural ordering.
	 * 
	 * @param unsortedAL
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> unsortedAL, int left, int right,
			int subArrSize) {
		if (unsortedAL == null)
			throw new NullPointerException();
		// If the array size has reached 1 or 0 return
		if (subArrSize <= 1) {
			return;
		}
		for (int i = left + 1; i <= right; i++) {
			T val = unsortedAL.get(i);
			int j;
			for (j = i - 1; j >= left && unsortedAL.get(j).compareTo(val) > 0; j--) {
				unsortedAL.set(j + 1, unsortedAL.get(j));
			}
			unsortedAL.set(j + 1, val);
		}
	}

	/**
	 * This method is a driver method performs a merge sort on the generic ArrayList
	 * given as input. It has 3 different strategies for determining the pivot. It
	 * is able to easily switch among these strategies.
	 * 
	 * @param unsortedAL
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> unsortedAL) {
		if (unsortedAL == null)
			throw new NullPointerException();
		if (unsortedAL.size() < 2)
			return;
		quickSort(unsortedAL, 0, unsortedAL.size() - 1);
	}

	/**
	 * Quick Sort Method Implementation
	 */
	public static <T extends Comparable<? super T>> void quickSort(ArrayList<T> unsortedAL, int left, int right) {
		int subArrSize = right - left + 1;
		// If the array size has reached 1 or 0 return
		if (subArrSize <= 1) {
			return;
		}
		int partitionIndex = partition(unsortedAL, left, right);
		quickSort(unsortedAL, left, partitionIndex - 1);
		quickSort(unsortedAL, partitionIndex + 1, right);
	}

	/**
	 * Partition the array ArrayList from left to right
	 * 
	 * @param unsortedAL
	 * @param left
	 * @param right
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> unsortedAL, int left, int right) {
		setupPivot(unsortedAL, left, right, strategy);
		// Choose the leftmost element in the sub array as the pivot value since
		// we just moved our desired pivot value to be in the leftmost position
		// when we called our "setupPivot()" method
		T pivot = unsortedAL.get(left);
		// Index for forward search
		int low = left + 1;
		// Index for backward search
		int high = right;
		while (high > low) {
			// Search forward from left
			while (low <= high && unsortedAL.get(low).compareTo(pivot) <= 0) {
				low++;
			}
			// Search backward from right
			while (low <= high && unsortedAL.get(high).compareTo(pivot) > 0) {
				high--;
			}
			// Swap two elements in the list
			if (high > low) {
				swap(unsortedAL, high, low);
			}
		}
		while (high > left && unsortedAL.get(high).compareTo(pivot) >= 0) {
			high--;
		}
		// Swap pivot with high's value
		if (pivot.compareTo(unsortedAL.get(high)) > 0) {
			unsortedAL.set(left, unsortedAL.get(high));
			unsortedAL.set(high, pivot);
			return high;
		} else {
			return left;
		}
	}

	/**
	 * Determine a pivot value and put it at the leftmost position of the "subarray"
	 * we are currently working with
	 * 
	 * @param unsortedAL
	 * @param left
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void setupPivot(ArrayList<T> unsortedAL, int left, int right,
			int strategy) {
		// If the strategy is incorrect, throw an exception
		if (strategy != 0 && strategy != 1 && strategy != 2)
			throw new IllegalArgumentException();
		// if strategy is 0, we choose the median value of the first element, the mid
		// element, and the last element in the unsortedAL as the pivot value
		if (strategy == 0) {
			int mid = (left + right) / 2;
			if (unsortedAL.get(left).compareTo(unsortedAL.get(mid)) > 0) {
				swap(unsortedAL, left, mid);
			}
			if (unsortedAL.get(left).compareTo(unsortedAL.get(right)) > 0) {
				swap(unsortedAL, left, right);
			}
			if (unsortedAL.get(mid).compareTo(unsortedAL.get(right)) > 0) {
				swap(unsortedAL, right, mid);
			}
			swap(unsortedAL, left, mid);
		}
		// if strategy is 1, we choose the element in the "middle" position of the
		// unsortedAL sub array as the pivot value
		if (strategy == 1) {
			int mid = (left + right) / 2;
			swap(unsortedAL, left, mid);
		}
		// if strategy is 2, we choose the leftmost element in the unsortedAL as the
		// pivot value
		if (strategy == 2) {
			// Do nothing, because the left element is at the beginning of the ArrayList at
			// this moment already
		}
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * ascending order.
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> asc = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			asc.add(i);
		}
		return asc;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * permuted order (i,e., randomly ordered).
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> pmt = new ArrayList<>();
		pmt = generateAscending(size);
		Random rng = new Random();
		for (int i = 0; i < size; i++) {
			int i1 = rng.nextInt(size);
			int i2 = rng.nextInt(size);
			swap(pmt, i1, i2);
		}
		return pmt;
	}

	/**
	 * Helper method for swapping two elements in an ArrayList
	 * 
	 * @param pmt
	 * @param i1
	 * @param i2
	 */
	private static <T> void swap(ArrayList<T> arr, int i1, int i2) {
		T temp = arr.get(i1);
		arr.set(i1, arr.get(i2));
		arr.set(i2, temp);

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * descending order.
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> dsc = new ArrayList<>();
		for (int i = size; i > 0; i--) {
			dsc.add(i);
		}
		return dsc;
	}

	/**
	 * Setter for changing the value of threshold
	 * 
	 * @param threshold
	 */
	public static void setThreshold(int threshold) {
//		if (threshold == 0)
//			throw new IllegalArgumentException();
		ArrayListSorter.threshold = threshold;
	}

	/**
	 * Setter for changing the value of different strategies using in the quicksort
	 * to determine the pivot
	 * 
	 * @param strategy
	 */
	public static void setStrategy(int strategy) {
		ArrayListSorter.strategy = strategy;
	}
}
