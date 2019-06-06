package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * This test class tests the methods in our SinglyLinkedList class
 * 
 * There are some methods we do not need to write testers for them (like
 * getFirst, toArray, size) since we have already used these methods in other
 * test methods, so they are definitely correct
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

class SinglyLinkedListTest {

	SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();

	@Test
	void testSinglyLinkedList() {
		assertEquals(0, sll.size());
	}

	/**
	 * First of all, I will test different exceptions together
	 */
	@Test
	void testExceptions() {
		assertThrows(IndexOutOfBoundsException.class, () -> {sll.add(-1, 345);});
		assertThrows(IndexOutOfBoundsException.class, () -> {sll.add(1, 345);});
		assertThrows(NoSuchElementException.class, () -> {sll.getFirst();});
		assertThrows(IndexOutOfBoundsException.class, () -> {sll.get(-1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {sll.get(132);});
		assertThrows(NoSuchElementException.class, () -> {sll.removeFirst();});
		assertThrows(IndexOutOfBoundsException.class, () -> {sll.remove(-1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {sll.remove(132);});
	}

	@Test
	void testAddFirst() {
		sll.addFirst(10);
		assertEquals(10, (int) sll.getFirst());
		sll.addFirst(20);
		assertEquals(20, (int) sll.getFirst());
		assertEquals(2, sll.size());
	}

	@Test
	void testAdd() {
		sll.add(0, 2);
		sll.add(1, 30);
		sll.add(1, 3);
		Object[] actual = sll.toArray();
		Object[] expected = new Object[] { 2, 3, 30 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(actual[i], expected[i]);
		}
	}

	@Test
	void testGet() {
		sll.addFirst(32789);
		sll.addFirst(4532798);
		sll.addFirst(234);
		assertEquals(234, (int) sll.get(0));
		assertEquals(32789, (int) sll.get(2));
	}

	@Test
	void testRemoveFirst() {
		sll.addFirst(32789);
		sll.addFirst(4532798);
		sll.addFirst(234);
		sll.removeFirst();
		Object[] actual = sll.toArray();
		Object[] expected = new Object[] { 4532798, 32789 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(actual[i], expected[i]);
		}
	}

	@Test
	void testRemove() {
		sll.add(0, 2);
		sll.add(1, 30);
		sll.add(1, 3);
		sll.remove(2);
		Object[] actual = sll.toArray();
		Object[] expected = new Object[] { 2, 3 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(actual[i], expected[i]);
		}
	}

	@Test
	void testIndexOf() {
		sll.add(0, 2);
		sll.add(1, 30);
		assertEquals(1, sll.indexOf(30));
		sll.add(1, 3);
		assertEquals(2, sll.indexOf(30));
	}

	@Test
	void testIsEmpty() {
		assertTrue(sll.isEmpty());
		sll.addFirst(123);
		sll.add(0, 2348);
		sll.addFirst(6437289);
		assertFalse(sll.isEmpty());
	}

	@Test
	void testClear() {
		sll.addFirst(123);
		sll.add(0, 2348);
		sll.addFirst(6437289);
		assertEquals(3, sll.size());
		sll.clear();
		assertEquals(0, sll.size());
	}
}
