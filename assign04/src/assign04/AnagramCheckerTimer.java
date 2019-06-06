package assign04;

import java.util.Random;

/**
 * This is the timer for analysis doc to check the running time for areAnagrams method and getLargestAnagramGroup method
 * @author Kyle Perry, Erdi Fan
 *
 */

/**
 * This is the random String generator for us to create different strings with
 * different lengths
 *
 */
public class AnagramCheckerTimer {

	public static String randString(int length) {
		Random rand = new Random();
		StringBuilder sBuffNew = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sBuffNew.append((char) (97 + (rand.nextInt(25))));
		}
		return sBuffNew.toString();
	}

	public static void main(String[] args) {
		// Do 1000 lookups and use the average running time.
		int timesToLoop = 100;
		long startTime, midpointTime, stopTime;

		// Timer for areAnagram method
		
		//Strings to be built up and tested as parameters in areAnagrams()
		StringBuilder concatStr1 = new StringBuilder();
		StringBuilder concatStr2 = new StringBuilder();
		
		// For each problem size n...
		for (int n = 1000; n <= 20000; n += 1000) {
			// Generate two random strings to be tested to see if they are anagrams of each other		
			concatStr1.append(randString(1000));
			concatStr2.append(randString(1000));

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}
			// Now, run the test.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				AnagramChecker.areAnagrams(concatStr1.toString(), concatStr2.toString());
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
				concatStr1.toString();
				concatStr2.toString();
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println("It took " + averageTime
					+ "ns to run the areAnagrams method with a pair of strings containing " + n + " characters");
		}

		// Timer for getlargestAnagramGroup
		Random rng2 = new Random();

		// For each problem size n...
		for (int n = 10000; n <= 200000; n += 10000) {
			String[] test = new String[n];
			// Create n random strings in the test array with varying lengths
			for (int i = 0; i < n; i++) {
				test[i] = randString(3 + rng2.nextInt(7));
			}
			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}
			// Now, run the test.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				// create a copy of the array to be checked
				String[] copyOfTest = test;
				AnagramChecker.getLargestAnagramGroup(copyOfTest);
			}
			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
				String[] copyOfTest = test;
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop Average it over the
			// number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println("It took " + averageTime
					+ "ns to run the getLargestAnagramGroup method with a string Array containing " + n + " strings");
		}
	}
}
