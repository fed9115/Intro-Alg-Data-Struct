package assign05;

import java.util.ArrayList;

public class ArrayListSorterTimingMergeSort {

	public static void main(String[] args) {
		long startTime, midpointTime, stopTime, count = 0, addListStart = 0, addListEnd = 0;

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		// Create a temp and test ArrayLists to constantly reset and use the values of.
		ArrayList<Integer> test;
		ArrayList<Integer> temp;

		// Loop through changing the array size accordingly.
		int arraySize = 100;
		while (arraySize <= 2000) {
//			test = ArrayListSorter.generateAscending(arraySize);
//			test = ArrayListSorter.generatePermuted(arraySize);
			test = ArrayListSorter.generateDescending(arraySize);
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			long timesToLoop = 500;

			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++) {

				// Remove the time it takes to reset the array
				addListStart = System.nanoTime();
				temp = new ArrayList<Integer>(test);
				addListEnd = System.nanoTime();

				count += (addListEnd - addListStart);

				ArrayListSorter.setThreshold(10);
				ArrayListSorter.mergesort(temp);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (long i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			averageTime = averageTime - (count / timesToLoop);
			System.out.println("It takes exactly " + averageTime + " nanoseconds");

			arraySize += 100;

		}

	}

}
