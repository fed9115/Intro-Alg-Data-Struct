package assign09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ComparisonTiming {

	public static void main(String[] args) {
		int timesToLoop = 10000;
		long startTime, midTime, stopTime;

		for (int n = 1000; n <= 20000; n += 1000) {
			// Create one of our HashTables
			// HashTable<String, Double> hashT = new HashTable<>();
			HashMap<String, Double> hashM = new HashMap<>();

			ArrayList<String> arrListStr = new ArrayList<>();
			ArrayList<Double> arrListVal = new ArrayList<>();
			// Random Number Generator
			Random rng = new Random();
			for (int i = 0; i < n; i++) {
				// generate one random strings for the first and last name of the student
				String key = "";
				int randStrLen = rng.nextInt(4) + 4;
				for (int j = 0; j < randStrLen; j++) {
					key += (rng.nextInt(26) + 97);
				}
				Double randDbl = rng.nextDouble() * 10.0;
				// hashT.put(key, randDbl);
				hashM.put(key, randDbl);
				arrListStr.add(key);
				arrListVal.add(randDbl);
			}

			// Stabilize the thread
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) {
			}

			// Run the timing test
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				String randString = arrListStr.get(rng.nextInt(n));
				// hashT.containsKey(randString);
				hashM.containsKey(randString);

				// Double randDbl = arrListVal.get(rng.nextInt(n));
				// hashT.containsValue(randDbl);
				// hashM.containsValue(randDbl);
			}

			midTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				String randString = arrListStr.get(rng.nextInt(n));
				// Double randDbl = arrListVal.get(rng.nextInt(n));
			}

			stopTime = System.nanoTime();

			// Calculate averages and print the results
			double averageTime = ((midTime - startTime) - (stopTime - midTime)) / (timesToLoop);
			System.out.println(n + "\t" + averageTime);
		}
	}
}
