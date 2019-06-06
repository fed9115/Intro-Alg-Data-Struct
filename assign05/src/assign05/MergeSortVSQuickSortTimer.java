package assign05;

import java.util.ArrayList;
import static assign05.ArrayListSorter.*;

/**
 * Timer to test merge sort and quick sort on lists in ascending, descending,
 * and permuted order
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

public class MergeSortVSQuickSortTimer {

	public static void main(String[] args) {
		// Do 1000 lookups and use the average running time.
		int timesToLoop = 1000;
		long startTime, midpointTime, stopTime;

		// Setup the printing area
		System.out.println("quickSort() Timer Test Data" + "\n" + "---------------------------------------------");
		System.out.printf("%-15s %-15s %-15s %-15s %n", "Problem Size", "Average Time", "Sort Used", "List Type");

		// For each problem size n...
		for (int n = 1000; n <= 20000; n += 1000) {
			// Generate an ascending array of problem size n
			ArrayList<Integer> ascendingList = generateAscending(n);

			// Generate a descending array of problem size n
			ArrayList<Integer> descendingList = generateDescending(n);

			// Generate a permuted array of problem size, n
			ArrayList<Integer> permutedList = generatePermuted(n);

			// Set the pivot strategy to the one determined to be the best from preivous
			// experiments
			setStrategy(2);

			// Set the threshold value to the one determined to be the best from previous
			// experiments
			setThreshold(12);

			// Now, run the timing test once for each list (ascending, descending, and
			// permuted)
			// for both mergesort and quicksort methods

			/**
			 * Run the ascending list timing test for merge sort
			 */

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}

			startTime = System.nanoTime();
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				// Sort a copy of the ascending list
				mergesort(new ArrayList<>(ascendingList));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and copying
			// the
			// permuted array
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				new ArrayList<>(ascendingList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.printf("%-15s %-15s %-15s %-15s %n", n, averageTime, "Merge Sort", "Ascending");

			/**
			 * Run the ascending list timing test for quick sort
			 */

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}

			startTime = System.nanoTime();
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				// Sort a copy of the ascending list
				quicksort(new ArrayList<>(ascendingList));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and copying
			// the
			// permuted array
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				new ArrayList<>(ascendingList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.printf("%-15s %-15s %-15s %-15s %n", n, averageTime, "Quick Sort", "Ascending");

			/**
			 * Run the descending list timing test for merge sort
			 */

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}

			startTime = System.nanoTime();
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				// Sort a copy of the ascending list
				mergesort(new ArrayList<>(descendingList));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and copying
			// the
			// permuted array
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				new ArrayList<>(descendingList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.printf("%-15s %-15s %-15s %-15s %n", n, averageTime, "Merge Sort", "Descending");

			/**
			 * Run the descending list timing test for quick sort
			 */

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}

			startTime = System.nanoTime();
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				// Sort a copy of the ascending list
				quicksort(new ArrayList<>(descendingList));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and copying
			// the
			// permuted array
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				new ArrayList<>(descendingList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.printf("%-15s %-15s %-15s %-15s %n", n, averageTime, "Quick Sort", "Descending");

			/**
			 * Run the permuted list timing test for merge sort
			 */

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}

			startTime = System.nanoTime();
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				// Sort a copy of the ascending list
				mergesort(new ArrayList<>(permutedList));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and copying
			// the
			// permuted array
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				new ArrayList<>(permutedList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.printf("%-15s %-15s %-15s %-15s %n", n, averageTime, "Merge Sort", "Permuted");

			/**
			 * Run the permuted list timing test for quick sort
			 */

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}

			startTime = System.nanoTime();
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				// Sort a copy of the ascending list
				quicksort(new ArrayList<>(permutedList));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and copying
			// the
			// permuted array
			for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
				new ArrayList<>(permutedList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.printf("%-15s %-15s %-15s %-15s %n", n, averageTime, "Quick Sort", "Permuted");
		}
	}
}
