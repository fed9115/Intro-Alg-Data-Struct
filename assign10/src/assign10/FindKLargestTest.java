package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import static assign10.FindKLargest.*;
import org.junit.jupiter.api.Test;

/**
 * This is a class for testing the 4 find k largest methods
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */
class FindKLargestTest {

	List<Integer> list = new ArrayList<>();
	List<Integer> expectedAsc = new ArrayList<>();
	List<Integer> expectedDes = new ArrayList<>();

	void listConstructor(List<Integer> list) {
		list.add(20);
		list.add(10);
		list.add(40);
		list.add(30);
	}

	void AscListConstructor(List<Integer> list) {
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
	}

	void DesListConstructor(List<Integer> list) {
		list.add(40);
		list.add(30);
		list.add(20);
		list.add(10);
	}

	@Test
	void testFindKLargestHeapListOfEInt() {
		listConstructor(list);
		DesListConstructor(expectedDes);
		List<Integer> actual = findKLargestHeap(list, 4);
		for (int i = 0; i < 4; i++) {
			assertEquals(actual.get(i), expectedDes.get(i));
		}
	}

	@Test
	void testFindKLargestHeapListOfEIntComparatorOfQsuperE() {
		listConstructor(list);
		AscListConstructor(expectedAsc);
		List<Integer> actual = findKLargestHeap(list, 4, new Functor());
		for (int i = 0; i < 4; i++) {
			assertEquals(actual.get(i), expectedAsc.get(i));
		}
	}

	@Test
	void testFindKLargestSortListOfEInt() {
		listConstructor(list);
		DesListConstructor(expectedDes);
		List<Integer> actual = findKLargestSort(list, 4);
		for (int i = 0; i < 4; i++) {
			assertEquals(actual.get(i), expectedDes.get(i));
		}
	}

	@Test
	void testFindKLargestSortListOfEIntComparatorOfQsuperE() {
		listConstructor(list);
		AscListConstructor(expectedAsc);
		List<Integer> actual = findKLargestSort(list, 4, new Functor());
		for (int i = 0; i < 4; i++) {
			assertEquals(actual.get(i), expectedAsc.get(i));
		}
	}

	@Test
	void testExceptions() {
		listConstructor(list);
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestHeap(list, -1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestHeap(list, 5);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestHeap(list, -1, new Functor());
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestHeap(list, 5, new Functor());
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestSort(list, -1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestSort(list, 5);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestSort(list, -1, new Functor());
		});
		assertThrows(IllegalArgumentException.class, () -> {
			findKLargestSort(list, 5, new Functor());
		});
	}
}
