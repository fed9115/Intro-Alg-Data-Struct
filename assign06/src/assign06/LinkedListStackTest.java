package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * This test class tests the methods in our LinkedListStack class
 * 
 * There are some methods we do not need to write testers for them (like size
 * and push) since we have already used these methods in other test methods, so
 * they are definitely correct
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */

class LinkedListStackTest {

	LinkedListStack<Integer> lls = new LinkedListStack<>();

	/**
	 * First of all, I will test different exceptions together
	 */
	@Test
	void testExceptions() {
		assertThrows(NoSuchElementException.class, () -> {
			lls.peek();
		});
		assertThrows(NoSuchElementException.class, () -> {
			lls.pop();
		});
	}

	@Test
	void testClear() {
		lls.push(23);
		lls.push(23);
		lls.push(23);
		lls.push(23);
		assertEquals(4, lls.size());
		lls.clear();
		assertEquals(0, lls.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(lls.isEmpty());
		lls.push(23);
		assertFalse(lls.isEmpty());
	}

	@Test
	void testPeek() {
		lls.push(23);
		lls.push(33);
		lls.push(43);
		lls.push(53);
		assertEquals(53, (int) lls.peek());
		assertEquals(4, lls.size());
	}

	@Test
	void testPop() {
		lls.push(23);
		lls.push(33);
		lls.push(43);
		lls.push(53);
		assertEquals(53, (int) lls.pop());
		assertEquals(3, lls.size());
		assertEquals(43, (int) lls.peek());
	}
}
