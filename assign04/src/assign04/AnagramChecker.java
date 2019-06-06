package assign04;

import static assign04.AnagramChecker.sort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Check lists of words from an array of Strings or file name which are anagrams
 * 
 * @author Erdi Fan, Kyle Perry
 * @version 1/31/19
 *
 */

public class AnagramChecker {

	/**
	 * This method returns the lexicographically-sorted version of the input string.
	 * 
	 * @return String
	 */
	public static String sort(String s) {
		if (s == null)
			return null;
		Character[] charArray = new Character[s.length()];
		for (int i = 0; i < s.length(); i++) {
			charArray[i] = s.charAt(i);
		}
		insertionSort(charArray, (Character c1, Character c2) -> ((int) c1 - (int) c2));
		String buildString = "";
		for (Character c : charArray) {
			buildString += c;
		}
		String sorted = buildString;
		return sorted;
	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 */
	public static <T> void insertionSort(T[] tArray, Comparator<? super T> functor) {
		if (tArray.length < 2)
			return;
		for (int i = 1; i < tArray.length; i++) {
			T val = tArray[i];
			int j;
			for (j = i - 1; j >= 0 && functor.compare(tArray[j], val) > 0; j--) {
				tArray[j + 1] = tArray[j];
			}
			tArray[j + 1] = val;
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each other,
	 * otherwise returns false.
	 */
	public static boolean areAnagrams(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;

		String sortedS1 = sort(s1.toLowerCase());
		String sortedS2 = sort(s2.toLowerCase());

		if (sortedS1.equalsIgnoreCase(sortedS2))
			return true;

		return false;
	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array.
	 */
	public static String[] getLargestAnagramGroup(String[] stringArray) {
		insertionSort(stringArray, new StringArrayComparator());
		int current = 0;
		int comparison = current + 1;
		int currLrgstAnagramGrpIndex = 0;
		int currLrgstAnagramGrpLength = 1;
		while (current < stringArray.length) {
			int testAnagramGroupIndex = current;
			int testAnagramGroupLength = 1;
			while (comparison < stringArray.length && areAnagrams(stringArray[current], stringArray[comparison])) {
				comparison++;
				testAnagramGroupLength++;
			}
			if (testAnagramGroupLength > currLrgstAnagramGrpLength) {
				currLrgstAnagramGrpIndex = testAnagramGroupIndex;
				currLrgstAnagramGrpLength = testAnagramGroupLength;
			}
			current = comparison;
			comparison++;
		}
		String[] result = new String[currLrgstAnagramGrpLength];
		for (int i = 0; i < currLrgstAnagramGrpLength; i++) {
			result[i] = stringArray[currLrgstAnagramGrpIndex + i];
		}

		if (result.length < 2) {
			result = new String[0];
		}

		return result;
	}

	/**
	 * This method behaves the same as the previous method, but reads the list of
	 * words from the input filename. It is assumed that the file contains one word
	 * per line. If the file does not exist or is empty, the method returns an empty
	 * array because there are no anagrams.
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		String[] wordsArray = new String[0];
		try {
			Scanner scan = new Scanner(new File(filename));
			ArrayList<String> wordsList = new ArrayList<>();
			while (scan.hasNextLine()) {
				wordsList.add(scan.nextLine());
			}
			scan.close();
			wordsArray = new String[wordsList.size()];
			for (int i = 0; i < wordsList.size(); i++) {
				wordsArray[i] = wordsList.get(i);
			}
		} catch (IOException e) {
			return new String[0];
		}

		return getLargestAnagramGroup(wordsArray);
	}
}

/**
 * This is the functor to help us compare two strings
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */
class StringArrayComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		String sortedS1 = sort(s1.toLowerCase());
		String sortedS2 = sort(s2.toLowerCase());
		return sortedS1.compareToIgnoreCase(sortedS2);
	}
}
