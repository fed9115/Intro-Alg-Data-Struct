package assign03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

public class SimplePrioityQueueTester {
	
	private SimplePriorityQueue<Integer> testInteger;
	private SimplePriorityQueue<String> testString;
	private SimplePriorityQueue<Character> testCharacter;
	private SimplePriorityQueue<MyNumber> testMyNumber;
	private ArrayList<Integer> arrayListInt = new ArrayList<>();

	@BeforeEach
	void setUp() {
		testInteger = new SimplePriorityQueue<Integer>();
		testString = new SimplePriorityQueue<String>();
		testCharacter = new SimplePriorityQueue<Character>();
		testMyNumber = new SimplePriorityQueue<MyNumber>(new MyNumberComparator());
		arrayListInt.add(1);
		arrayListInt.add(7);
		arrayListInt.add(-8);
		arrayListInt.add(4);
		arrayListInt.add(-23);
	}

	@Test
	public void testIntFindMinException() {
		assertThrows(NoSuchElementException.class, () -> { testInteger.findMin(); });
	}
	
	@Test
	public void testStringFindMinException() {
		assertThrows(NoSuchElementException.class, () -> { testString.findMin(); });
	}
	
	@Test
	public void testCharFindMinException() {
		assertThrows(NoSuchElementException.class, () -> { testCharacter.findMin(); });
	}
	
	@Test
	public void testMyNumberFindMinException() {
		assertThrows(NoSuchElementException.class, () -> { testMyNumber.findMin(); });
	}
	
	@Test
	public void testIntDeleteMinException() {
		assertThrows(NoSuchElementException.class, () -> { testInteger.deleteMin(); });
	}
	
	@Test
	public void testStringDeleteMinException() {
		assertThrows(NoSuchElementException.class, () -> { testString.deleteMin(); });
	}
	
	@Test
	public void testCharDeleteMinException() {
		assertThrows(NoSuchElementException.class, () -> { testCharacter.deleteMin(); });
	}
	
	@Test
	public void testMyNumberDeleteMinException() {
		assertThrows(NoSuchElementException.class, () -> { testMyNumber.deleteMin(); });
	}
	
	@Test
	public void testInsertNull() {
		testInteger.insert(null);
		assertThrows(NoSuchElementException.class, () -> { testInteger.findMin(); });
		testString.insert(null);
		assertThrows(NoSuchElementException.class, () -> { testString.findMin(); });
		testCharacter.insert(null);
		assertThrows(NoSuchElementException.class, () -> { testCharacter.findMin(); });
		testMyNumber.insert(null);
		assertThrows(NoSuchElementException.class, () -> { testMyNumber.findMin(); });
	}
	
	@Test
	public void testIntegerInsert() {
			testInteger.insert(9);
			testInteger.insert(2);
			testInteger.insert(-8);
			testInteger.insert(5);
			testInteger.insert(21);
			testInteger.insert(0);
			testInteger.insert(-7);
			testInteger.insert(42);
			testInteger.insert(100);
			testInteger.insert(-198);
			Integer[] expected = {100,42,21,9,5,2,0,-7,-8,-198};
			for (int i = 9; i >= 0; i--) {
				assertEquals( expected[i], testInteger.findMin());
				testInteger.deleteMin();
			}
	}
	
	@Test
	public void testStringInsert() {
			testString.insert("Apples");
			testString.insert("Zebras");
			testString.insert("Dogs");
			testString.insert("Red Tea");
			testString.insert("Brownies");
			String[] expected = {"Zebras", "Red Tea", "Dogs", "Brownies", "Apples"};
			for (int i = 4; i >= 0; i--) {
				assertEquals(expected[i], testString.findMin());
				testString.deleteMin();
			}
	}
	
	@Test
	public void testCharInsert() {
			testCharacter.insert('A');
			testCharacter.insert('a');
			testCharacter.insert('D');
			testCharacter.insert('Z');
			testCharacter.insert('?');
			Character[] expected = {'a','Z','D','A','?'};
			for (int i = 4; i >= 0; i--) {
				assertEquals( expected[i], testCharacter.findMin());
				testCharacter.deleteMin();
			}
	}
	
	@Test
	public void testComparatorInsert() {
		testMyNumber.insert(new MyNumber(8));
		testMyNumber.insert(new MyNumber(0));
		testMyNumber.insert(new MyNumber(-10));
		testMyNumber.insert(new MyNumber(27));
		int[] expected = {27,8, 0, -10};
		for (int i = 3; i >= 0; i--) {
			assertEquals( expected[i], testMyNumber.findMin().myNumberInt());
			testMyNumber.deleteMin();
		}
	}
	
	@Test
	public void testInsertDuplicates() {
		testMyNumber.insert(new MyNumber(8));
		testMyNumber.insert(new MyNumber(0));
		testMyNumber.insert(new MyNumber(-10));
		testMyNumber.insert(new MyNumber(27));
		testMyNumber.insert(new MyNumber(0));
		testMyNumber.insert(new MyNumber(0));
		int[] expected = {27,8, 0, 0, 0, -10};
		for (int i = 5; i >= 0; i--) {
			assertEquals( expected[i], testMyNumber.findMin().myNumberInt());
			testMyNumber.deleteMin();
		}
	}
	
	@Test
	public void testInsertAll() {
		testInteger.insertAll(arrayListInt);
		Integer[] expected = {7, 4, 1, -8, -23};
		for (int i = 4; i >= 0; i--) {
			assertEquals( expected[i], testInteger.findMin());
			testInteger.deleteMin();
		}
	}
	
	@Test
	public void testArraySizeIncreaseComparable() {
		testInteger.insert(9);
		testInteger.insert(2);
		testInteger.insert(-8);
		testInteger.insert(5);
		testInteger.insert(21);
		testInteger.insert(0);
		testInteger.insert(-7);
		testInteger.insert(42);
		testInteger.insert(100);
		testInteger.insert(-198);
		testInteger.insert(1);
		assertEquals(20, testInteger.getLength());
	}
	
	@Test
	public void testArraySizeIncreaseComparator() {
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		testMyNumber.insert(new MyNumber(1));
		assertEquals(20, testMyNumber.getLength());
	}
	
	@Test
	public void testClear() {
		testInteger.insert(9);
		testInteger.insert(2);
		testInteger.insert(-8);
		testInteger.insert(5);
		testInteger.clear();
		assertEquals(0, testInteger.size());
		testCharacter.insert('A');
		testCharacter.insert('a');
		testCharacter.insert('D');
		testCharacter.insert('Z');
		testCharacter.insert('?');
		testCharacter.clear();
		assertEquals(0, testCharacter.size());
		testString.insert("Apples");
		testString.insert("Zebras");
		testString.insert("Dogs");
		testString.insert("Red Tea");
		testString.insert("Brownies");
		testString.clear();
		assertEquals(0, testString.size());
		testMyNumber.insert(new MyNumber(8));
		testMyNumber.insert(new MyNumber(0));
		testMyNumber.insert(new MyNumber(-10));
		testMyNumber.insert(new MyNumber(27));
		testMyNumber.clear();
		assertEquals(0, testMyNumber.size());
	}
	
	@Test
	public void testFindMinNoException() {
		testCharacter.insert('A');
		testCharacter.insert('a');
		testCharacter.insert('D');
		testCharacter.insert('Z');
		testCharacter.insert('?');
		assertEquals( "?", testCharacter.findMin().toString());
		testString.insert("Apples");
		testString.insert("Zebras");
		testString.insert("Dogs");
		testString.insert("Red Tea");
		testString.insert("Brownies");
		assertEquals("Apples", testString.findMin().toString());
		testInteger.insert(9);
		testInteger.insert(2);
		testInteger.insert(-8);
		testInteger.insert(5);
		testInteger.insert(21);
		assertEquals("-8", testInteger.findMin().toString());
		testMyNumber.insert(new MyNumber(8));
		testMyNumber.insert(new MyNumber(0));
		testMyNumber.insert(new MyNumber(-10));
		testMyNumber.insert(new MyNumber(27));
		assertEquals(-10, testMyNumber.findMin().myNumberInt());
	}
	
	@Test
	public void testDeleteMin() {
		testCharacter.insert('A');
		testCharacter.insert('a');
		testCharacter.insert('D');
		testCharacter.insert('Z');
		testCharacter.insert('?');
		assertEquals( "?", testCharacter.deleteMin().toString());
		assertEquals( 4 , testCharacter.size());
		testString.insert("Apples");
		testString.insert("Zebras");
		testString.insert("Dogs");
		testString.insert("Red Tea");
		testString.insert("Brownies");
		assertEquals("Apples", testString.deleteMin().toString());
		assertEquals( 4 , testString.size());
		testInteger.insert(9);
		testInteger.insert(2);
		testInteger.insert(-8);
		testInteger.insert(5);
		testInteger.insert(21);
		assertEquals("-8", testInteger.deleteMin().toString());
		assertEquals( 4 , testInteger.size());
		testMyNumber.insert(new MyNumber(8));
		testMyNumber.insert(new MyNumber(0));
		testMyNumber.insert(new MyNumber(-10));
		testMyNumber.insert(new MyNumber(27));
		assertEquals(-10, testMyNumber.deleteMin().myNumberInt());
		assertEquals(3, testMyNumber.size());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(testInteger.isEmpty());
		testInteger.insert(9);
		testInteger.insert(2);
		testInteger.insert(-8);
		assertFalse(testInteger.isEmpty());
	}
	
}
