package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This is a class for testing our binary max heap. Some methods (i.e. peek,
 * clear, isEmpty, size, toArray) are not testing separately because in some of
 * other testing cases, we called these method to check, thus they are
 * definitely correct
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */
class BinaryMaxHeapTest {

	BinaryMaxHeap<Integer> noParams = new BinaryMaxHeap<>();
	BinaryMaxHeap<Integer> functorOnly = new BinaryMaxHeap<>(new Functor());
	List<Integer> list = new ArrayList<>();
	BinaryMaxHeap<Integer> bothParamsFunctor;
	BinaryMaxHeap<Integer> listOnlyParam;

	@Test
	void testAddNoParams() {
		noParams.add(10);
		noParams.add(20);
		noParams.add(5);
		int[] expected = new int[] { 20, 10, 5 };
		for (int i = 0; i < 3; i++) {
			assertEquals(expected[i], noParams.toArray()[i]);
		}
		assertTrue(noParams.size() == 3);
		noParams.add(11);
		noParams.add(21);
		noParams.add(51);
		noParams.add(12);
		noParams.add(22);
		noParams.add(52);
		noParams.add(13);
		noParams.add(24);
		noParams.add(53);
		assertTrue(noParams.size() == 12);
		expected = new int[] { 53, 51, 52, 22, 24, 21, 12, 10, 20, 11, 13, 5 };
		for (int i = 0; i < 12; i++)
			assertEquals(expected[i], noParams.toArray()[i]);
		noParams.clear();
		assertEquals(0, noParams.size());
	}

	@Test
	void testAddFunctorOnly() {
		functorOnly.add(10);
		functorOnly.add(20);
		functorOnly.add(5);
		int[] expected = new int[] { 5, 20, 10 };
		for (int i = 0; i < expected.length; i++)
			assertEquals(expected[i], functorOnly.toArray()[i]);

		assertTrue(functorOnly.size() == 3);
		functorOnly.add(11);
		functorOnly.add(21);
		functorOnly.add(51);
		functorOnly.add(12);
		functorOnly.add(22);
		functorOnly.add(52);
		functorOnly.add(13);
		functorOnly.add(24);
		functorOnly.add(53);
		assertTrue(functorOnly.size() == 12);
		expected = new int[] { 5, 11, 10, 20, 13, 51, 12, 22, 52, 21, 24, 53 };
		for (int i = 0; i < 12; i++)
			assertEquals(expected[i], functorOnly.toArray()[i]);
		functorOnly.clear();
		assertEquals(0, functorOnly.size());
	}

	@Test
	void testAddListOnlyParams() {
		list.add(10);
		list.add(20);
		list.add(5);
		listOnlyParam = new BinaryMaxHeap<>(list);
		int[] expected = new int[] { 20, 10, 5 };
		for (int i = 0; i < 3; i++) {
			assertEquals(expected[i], listOnlyParam.toArray()[i]);
		}
		assertTrue(listOnlyParam.size() == 3);
		list.add(11);
		list.add(21);
		list.add(51);
		list.add(12);
		list.add(22);
		list.add(52);
		list.add(13);
		list.add(24);
		list.add(53);
		listOnlyParam = new BinaryMaxHeap<>(list);
		assertTrue(listOnlyParam.size() == 12);
		expected = new int[] { 53, 51, 52, 22, 24, 21, 12, 10, 20, 11, 13, 5 };
		for (int i = 0; i < 12; i++)
			assertEquals(expected[i], listOnlyParam.toArray()[i]);
		listOnlyParam.clear();
		assertEquals(0, listOnlyParam.size());
	}

	@Test
	void testAddBothParamsFunctor() {
		list.add(10);
		list.add(20);
		list.add(5);
		bothParamsFunctor = new BinaryMaxHeap<>(list, new Functor());
		int[] expected = new int[] { 5, 20, 10 };
		for (int i = 0; i < expected.length; i++)
			assertEquals(expected[i], bothParamsFunctor.toArray()[i]);

		assertTrue(bothParamsFunctor.size() == 3);
		list.add(11);
		list.add(21);
		list.add(51);
		list.add(12);
		list.add(22);
		list.add(52);
		list.add(13);
		list.add(24);
		list.add(53);
		bothParamsFunctor = new BinaryMaxHeap<>(list, new Functor());
		assertTrue(bothParamsFunctor.size() == 12);
		expected = new int[] { 5, 11, 10, 20, 13, 51, 12, 22, 52, 21, 24, 53 };
		for (int i = 0; i < 12; i++)
			assertEquals(expected[i], bothParamsFunctor.toArray()[i]);
		bothParamsFunctor.clear();
		assertEquals(0, bothParamsFunctor.size());
	}

	@Test
	void testExtractMax() {
		noParams.add(10);
		noParams.add(20);
		noParams.add(21);
		assertFalse(noParams.isEmpty());
		assertEquals(21, (int) noParams.peek());
		noParams.extractMax();
		assertEquals(20, (int) noParams.peek());
		noParams.extractMax();
		assertEquals(10, (int) noParams.peek());
		noParams.extractMax();
		assertTrue(noParams.isEmpty());
	}
}
