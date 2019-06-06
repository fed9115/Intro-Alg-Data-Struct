package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	BinarySearchTree<Integer> bst = new BinarySearchTree<>();

	@Test
	void testAdd() {
		assertTrue(bst.add(1));
		assertFalse(bst.add(1));
		assertTrue(bst.add(2));
		assertEquals(2, bst.size());
	}

	@Test
	void testAddAll() {
		ArrayList<Integer> al = new ArrayList<>();
		assertFalse(bst.addAll(al));
		al.add(1);
		al.add(2);
		assertTrue(bst.addAll(al));
	}

	@Test
	void testClear() {
		bst.add(1);
		assertFalse(bst.isEmpty());
		bst.clear();
		assertFalse(bst.contains(1));
		assertEquals(0, bst.size());
		assertTrue(bst.isEmpty());
		assertFalse(bst.contains(1));
	}

	@Test
	void testContainsAll() {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(2);
		bst.addAll(al);
		assertTrue(bst.containsAll(al));
		al.add(3);
		assertFalse(bst.containsAll(al));
	}

	@Test
	void testFirst() {
		bst.add(2);
		bst.add(34);
		bst.add(1);
		bst.add(5);
		assertEquals(1, (int) bst.first());
	}

	@Test
	void testFirstOrLastException() {
		assertThrows(NoSuchElementException.class, () -> {
			bst.first();
		});
		assertThrows(NoSuchElementException.class, () -> {
			bst.last();
		});
	}

	@Test
	void testLast() {
		bst.add(2);
		bst.add(34);
		bst.add(1);
		bst.add(5);
		assertEquals(34, (int) bst.last());
	}

	@Test
	void testRemove() {
		bst.add(2);
		bst.add(34);
		bst.add(1);
		bst.add(5);
		bst.add(3);
		bst.add(24);
		bst.add(10);
		bst.add(51);
		assertTrue(bst.contains(24));
		assertEquals(8, bst.size());
		bst.remove(24);
		assertFalse(bst.contains(24));
		assertEquals(7, bst.size());
	}

	@Test
	void testRemoveAll() {
		bst.add(2);
		bst.add(34);
		bst.add(1);
		bst.add(5);
		bst.add(3);
		bst.add(24);
		bst.add(10);
		bst.add(51);
		ArrayList<Integer> al = new ArrayList<>();
		assertFalse(bst.removeAll(al));
		al.add(34);
		al.add(10);
		assertTrue(bst.removeAll(al));
		assertFalse(bst.contains(34));
		assertFalse(bst.contains(10));
	}

	@Test
	void testToArrayList() {
		bst.add(2);
		bst.add(34);
		bst.add(1);
		bst.add(5);
		bst.add(3);
		bst.add(24);
		bst.add(10);
		bst.add(51);
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(5);
		al.add(10);
		al.add(24);
		al.add(34);
		al.add(51);
		assertTrue(bst.toArrayList().equals(al));
	}

}
