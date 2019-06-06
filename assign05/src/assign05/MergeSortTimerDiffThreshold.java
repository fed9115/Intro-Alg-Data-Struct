package assign05;

import java.util.ArrayList;
import static assign05.ArrayListSorter.*;

/**
 * Timer to test different values of the threshold of when to switch to
 * selection sort when running merge sort.
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

public class MergeSortTimerDiffThreshold {

	public static void main(String[] args) {
		// Do 1000 lookups and use the average running time.
		int timesToLoop = 1000;
		long startTime, midpointTime, stopTime;

		// Setup the printing area
		System.out.println("mergeSort() Timer Test Data" + "\n" + "---------------------------------------------");
		System.out.printf("%-30s %-30s %-30s %n", "Problem Size", "Average Time", "Threshold Value");

		// For each problem size n...
		for (int n = 1000; n <= 20000; n += 1000) {

			// Create an array of 5 different threshold values to try, 3 static and 2
			// fractions of the
			// problem size
			ArrayList<Integer> thresholdVals = new ArrayList<>();
			thresholdVals.add(0);
			thresholdVals.add(5);
			thresholdVals.add(7);
			thresholdVals.add(10);
			thresholdVals.add(12);

			// Generate a permuted array of problem size, n
			ArrayList<Integer> unsortedList = generatePermuted(n);

			// Now, run the test once for each threshold value on a copies of the same
			// permuted array
			for (Integer threshold : thresholdVals) {

				// Set the threshold value
				setThreshold(threshold);

				// First, spin computing stuff until one second has gone by. This allows this
				// thread to stabilize.
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000) {
					// empty block
				}

				// Run the timing test
				startTime = System.nanoTime();
				for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
					// Sort a copy of the permuted, unsorted list
					mergesort(new ArrayList<>(unsortedList));
				}

				midpointTime = System.nanoTime();

				// Run a loop to capture the cost of running the "timesToLoop" loop and copying
				// the
				// permuted array
				for (int loopNum = 0; loopNum < timesToLoop; loopNum++) {
					new ArrayList<>(unsortedList);
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop from the cost of
				// running the loop and generate the random strings. Average it over the number
				// of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

				System.out.printf("%-30s %-30s %-30s %n", n, averageTime, threshold);
			}
		}
	}
}
