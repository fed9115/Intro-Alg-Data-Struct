package lab12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * This class contains two methods / strategies for removing duplicates from a
 * dataset.
 * 
 * @author Erin Parker & Erdi Fan
 * @version April 5, 2019
 */
public class Deduplication {

	/**
	 * Eliminates items with duplicate keys in the given list, using a hash table
	 * strategy. Specifically, when encountering an item whose key is already in the
	 * table, the item is ignored / eliminated.
	 * 
	 * @param dataset - the given list
	 * @return the deduplicated dataset, with only one entry per key in the original
	 *         dataset
	 */
	public static List<MapEntry<Character, Double>> deduplicateHashing(List<MapEntry<Character, Double>> dataset) {
		// FILL IN
		List<MapEntry<Character, Double>> result = new ArrayList<>();
		HashMap<Character, Double> map = new HashMap<>();
		for (MapEntry<Character, Double> entry : dataset) {
			if (map.put(entry.getKey(), entry.getValue()) == null) {
				result.add(entry);
			}
		}
		return result;
	}

	/**
	 * Eliminates items with duplicate keys in the given list, using a sorting
	 * strategy. Specifically, for all items with the same key, average their data
	 * and keep one pair with that key mapped to the average data.
	 * 
	 * @param dataset - the given list
	 * @return the deduplicated dataset, with only one entry per key in the original
	 *         dataset
	 */
	public static List<MapEntry<Character, Double>> deduplicateSorting(List<MapEntry<Character, Double>> dataset) {
		// FILL IN
		List<MapEntry<Character, Double>> result = new ArrayList<>();
		Collections.sort(dataset);
		// count the sum of the values of the same key
		int total = 0;
		// count the duplicates that are in order
		int count = 1;
		for (int i = 0; i < dataset.size(); i++) {
			MapEntry<Character, Double> current = dataset.get(i);
			if (i + 1 < dataset.size() && current.equals(dataset.get(i + 1))) {
				count++;
				total += current.getValue();
			} else {
				MapEntry<Character, Double> newer = new MapEntry<>current.getKey(), total / count));
				
				result.add(newer);
				count = 1;
				total = 0;
			}
		}
		return null;
	}

	public static void main(String[] args) {

		// generate dataset
		Random rng = new Random(333); // using a seed, we get the same sequence on each run
		List<MapEntry<Character, Double>> dataset = new ArrayList<MapEntry<Character, Double>>();
		for (int i = 0; i < 1000; i++) {
			char randChar = (char) ('!' + rng.nextInt(94)); // random printable character '!' to '~'
			double randValue = rng.nextDouble() * 10; // random value 0.0 to 10.0 (exclusive)

			dataset.add(new MapEntry<Character, Double>(randChar, randValue));
		}

		// deduplicate using hashing and check work against Lab 10 method
		List<MapEntry<Character, Double>> datasetDedup = deduplicateHashing(dataset);
		if (DuplicationAnalysis_soln.countDuplicatesHashing(dataset) != dataset.size() - datasetDedup.size())
			System.out.println(
					"There is a problem (hashing) -- the number of duplicates and difference in dataset size should be the same.");

		// deduplicate using sorting and check work against Lab 10 method
		datasetDedup = deduplicateSorting(dataset);
		if (DuplicationAnalysis_soln.countDuplicatesSorting(dataset) != dataset.size() - datasetDedup.size())
			System.out.println(
					"There is a problem (sorting) -- the number of duplicates and difference in dataset size should be the same.");

		// compare a few average values from datasetDedup (sorting version) with your
		// neighbor
	}
}