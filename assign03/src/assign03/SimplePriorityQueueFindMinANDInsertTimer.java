package assign03;

import java.util.Random;

public class SimplePriorityQueueFindMinANDInsertTimer {

	public static void main(String args[]) {

		// Random Number Generator
		Random rng = new Random();

		// Array to hold findMin timer test data
		double[][] findMinData = new double[20][2];

		// Array to hold insert timer test data
		double[][] insertData = new double[20][2];

		SimplePriorityQueue<Integer> randSPQ = new SimplePriorityQueue<>();
		// For each problem size n . . .
		for (int n = 100000, probSet = 1; n <= 2000000; n += 100000, probSet++) {
			
			int timesToLoop = 10000;
			
			long testingSetupStartTime = System.currentTimeMillis();

			// Generate a simple priority queue of size n.
			// Add 100000 items, always to the end of the simple priority queue to avoid
			// shifting elements during queue creation.
			for (int i = n; i >= n - 100000 + 100; i--) {
				randSPQ.insert(n);
			}
			long testingSetupEndTime = System.currentTimeMillis();
			System.out.println("Time to setup the priority queue for problem set " + probSet + ", was "
					+ ((testingSetupEndTime - testingSetupStartTime) / 1000) + " seconds.");
			System.out.println("-----");

			double startTime, midpointTime, stopTime;

			// Run the findMin timing tests, print some information and store the values for
			// printing at the end
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) { // empty block
			}
			// Now, run the test.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				randSPQ.findMin();
			}
			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
			}
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
			System.out.println(
					"Total time to run findMin() was " + ((midpointTime - startTime) - (stopTime - midpointTime))
							+ " nanoseconds and we ran the method " + timesToLoop + " times.");
			System.out.println(
					"The cost of setting up the loop testing was " + (stopTime - midpointTime) + " nanoseconds.");
			System.out.println("The size of the priority queue for this test was " + n + " and took an average time of "
					+ averageTime + " nanoseconds \n");

			findMinData[probSet - 1][0] = n;
			findMinData[probSet - 1][1] = averageTime;

			timesToLoop = 100;
			
			// Create an int variable to hold the value of accessing our random array of ints
			//  in our time to subtract loop.
			int x;
			
			// Create an arraylist of random ints so they don't have to be generated in the
			// timing test.
			int[] randomInts = new int[timesToLoop];
			for (int i = 0; i < timesToLoop; i++) {
				randomInts[i] = rng.nextInt(n);
			}

			// Spin computing stuff and stabilize the thread again.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				randSPQ.insert(randomInts[i]);
				randSPQ.deleteMin();
			}
			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
				x = randomInts[i];
				randSPQ.deleteMin();
			}
			stopTime = System.nanoTime();
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
			System.out.println(
					"Total time to run insert() was " + ((midpointTime - startTime) - (stopTime - midpointTime))
							+ " nanoseconds and we ran the method " + timesToLoop + " times.");
			System.out.println(
					"The cost of setting up the loop testing was " + (stopTime - midpointTime) + " nanoseconds.");
			System.out.println("The size of the priority queue for this test was " + n + " and took an average time of "
					+ averageTime + " nanoseconds \n");
			System.out.println("-----------------------------------------------");

			insertData[probSet - 1][0] = n;
			insertData[probSet - 1][1] = averageTime;
		}

		System.out.println("findMin() Timer Test Data");
		System.out.printf("%-30s %-30s %n", "Problem Size", "Average Time");
		for (int i = 0; i < 20; i++) {
			System.out.printf("%-30s %-30s %n", findMinData[i][0], findMinData[i][1]);
		}

		System.out.println("-----------------------------------------------");
		System.out.println("insert() Timer Test Data");
		System.out.printf("%-30s %-30s %n", "Problem Size", "Average Time");
		for (int i = 0; i < 20; i++) {
			System.out.printf("%-30s %-30s %n", insertData[i][0], insertData[i][1]);
		}

	}
}
