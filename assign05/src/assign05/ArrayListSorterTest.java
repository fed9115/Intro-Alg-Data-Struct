package assign05;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static assign05.ArrayListSorter.*;

class ArrayListSorterTest {
	ArrayList<Integer> unsortedInt;
	ArrayList<Integer> sorted;

	@BeforeEach()
	void setUp() {
		unsortedInt = generatePermuted(10);
		sorted = generateAscending(10);
	}
//
//	@Test
//	void testInsertionSort() {
//		insertionSort(unsortedInt, 0, 9);
//		for(int i = 0; i < 10; i++) {
//			assertEquals(sorted.get(i), unsortedInt.get(i));
//		}
//	}

	/**
	 * we cannot check the randomly permuted array by using the assert method
	 * provided by JUnit. However, we can print them out to check if they are
	 * neither in ascending nor descending order
	 */
	@Test
	void testRandPermuteGenerator() {
		System.out.println(generatePermuted(100));
	}

	@Test
	void testMergesortOneItem() {
		ArrayList<Integer> sizeOne = new ArrayList<>();
		sizeOne.add(1);
		mergesort(sizeOne);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		assertEquals(expected, sizeOne);
	}

	@Test
	void testMergeException() {
		assertThrows(NullPointerException.class, () -> {
			mergesort(null);
		});
	}

	@Test
	void testMergeSort() {
		ArrayList<Integer> expected = generateAscending(100);
		ArrayList<Integer> actual = generatePermuted(100);
		mergesort(actual);
		assertEquals(expected, actual);
	}

//	@Test
//	void testGenericMerge() {
//		ArrayList<Double> actual = new ArrayList<>();
//		actual.add(1.2);
//		actual.add(0.1);
//		mergeSort(actual);
//		ArrayList<Double> expected = new ArrayList<>();
//		expected.add(0.1);
//		expected.add(1.2);
//		assertEquals(expected, actual);
//	}

	@Test
	void testQuicksortOneItem() {
		ArrayList<Integer> sizeOne = new ArrayList<>();
		sizeOne.add(1);
		quicksort(sizeOne);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		assertEquals(expected, sizeOne);
	}

	@Test
	void testQuicksort() {
		ArrayList<Integer> expected = generateAscending(100);
		ArrayList<Integer> actual = generatePermuted(100);
		quicksort(actual);
		assertEquals(expected, actual);
	}

	@Test
	void testQuickException() {
		assertThrows(NullPointerException.class, () -> {
			quicksort(null);
		});
	}

	@Test
	void testGenericQuick() {
		ArrayList<Double> actual = new ArrayList<>();
		actual.add(1.2);
		actual.add(0.1);
		quicksort(actual);
		ArrayList<Double> expected = new ArrayList<>();
		expected.add(0.1);
		expected.add(1.2);
		assertEquals(expected, actual);
	}

	@Test
	void testGenerateAscending() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(4);
		expected.add(5);
		ArrayList<Integer> actual = generateAscending(5);
		assertEquals(expected, actual);
	}

	@Test
	void testGenerateDescending() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(5);
		expected.add(4);
		expected.add(3);
		expected.add(2);
		expected.add(1);
		ArrayList<Integer> actual = generateDescending(5);
		assertEquals(expected, actual);
	}

}
