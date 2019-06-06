package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import static assign04.AnagramChecker.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnagramCheckerTest {

	String testString1;
	String testString2;
	String testString3;
	String testString4;
	String[] testStringArr;
	String[] testStringArrNoAnagram;
	String[] testStringArrSpaces;
	String[] testStringArrSpecialChars;

	@BeforeEach
	void setUp() {
		testString1 = "BbAa";
		testString2 = "Liar";
		testString3 = "Rail";
		testString4 = "bBaC";
		testStringArrNoAnagram = new String[] { "Apple", "horse" };
		testStringArr = new String[] { "liar", "Apple", "horse", "rail" };
		testStringArrSpaces = new String[] { "House", "apple", "ZeD", " ", " Zebra!", " " };
		testStringArrSpecialChars = new String[] { "!", "apple", "ZeD", " ", " Zebra!", "!" };
	}

	@Test
	void testSort() {
		assertEquals("ABab", sort(testString1));
	}

	@Test
	void testSortCaseSensitive() {
		assertEquals("BCab", sort(testString4));
	}

	@Test
	void testSortNull() {
		String nullString = null;
		assertNull(sort(nullString));
	}

	@Test
	void testInsertionSortOneOrNoCharInString() {
		String[] noCharStr = new String[] { "" };
		insertionSort(noCharStr, new StringArrayComparator());
		assertEquals("", noCharStr[0]);
		String[] oneCharStr = new String[] { "a" };
		insertionSort(oneCharStr, new StringArrayComparator());
		assertEquals("a", oneCharStr[0]);
	}

	@Test
	void testInsertionSort() {
		String[] str = new String[] { "#2019!", "KylePerry", "erdiFan" };
		insertionSort(str, new StringArrayComparator());
		String[] expected = new String[] { "#2019!", "erdiFan", "KylePerry" };
		for (int i = 0; i < str.length; i++) {
			assertEquals(str[i], expected[i]);
		}
	}

	@Test
	void testInsertionSortGeneric() {
		Character[] source = new Character[] { '1', '4', '3', '5', '2' };
		Character[] expected = new Character[] { '1', '2', '3', '4', '5' };
		insertionSort(source,
				(Character c1, Character c2) -> (int) Character.toLowerCase(c1) - (int) Character.toLowerCase(c2));
		for (int i = 0; i < source.length; i++) {
			assertEquals(expected[i], source[i]);
		}

	}

	@Test
	void testAreAnagramsOneNull() {
		assertFalse(areAnagrams(null, "sdf"));
	}

	@Test
	void testAreAnagramsBothNull() {
		assertFalse(areAnagrams(null, null));
	}

	@Test
	void testAreNotAnagrams() {
		assertFalse(areAnagrams("dsf", "fs"));
	}

	@Test
	void testAreAnagrams() {
		assertTrue(areAnagrams(testString2, testString3));
	}

	@Test
	void testGetLargestAnagramGroupOneWord() {
		String[] str1 = new String[] { "Kyle" };
		String[] expected = new String[0];
		String[] actual = getLargestAnagramGroup(str1);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected, actual);
		}
	}

	@Test
	void testGetLargestAnagramGroupNullPointerException() {
		String[] str1 = new String[10];
		assertThrows(NullPointerException.class, () -> {
			getLargestAnagramGroup(str1);
		});
	}

	@Test
	void testGetLargestAnagramGroupStringArray() {
		String[] expected = new String[] { "liar", "rail" };
		String[] actual = getLargestAnagramGroup(testStringArr);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	void testAraAnagramesSpaces() {
		String[] expected = new String[] { " ", " " };
		String[] actual = getLargestAnagramGroup(testStringArrSpaces);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	void testAraAnagramesSpecialChars() {
		String[] expected = new String[] { "!", "!" };
		String[] actual = getLargestAnagramGroup(testStringArrSpecialChars);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	void testGetLargestAnagramGroupFileName() {
		String[] expected = new String[] { "carets", "Caters", "caster", "crates", "Reacts", "recast", "traces" };
		String[] actual = getLargestAnagramGroup("sample_word_list.txt");
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	void testGetLargestAnagramGroupFileNotFoundException() {
		String[] expected = new String[0];
		String[] actual = getLargestAnagramGroup("no_Such_File.txt");
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	void testGetLargestAnagramGroupNoAnagramGroup() {
		String[] actual = getLargestAnagramGroup(testStringArrNoAnagram);
		String[] expected = new String[0];
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

}
