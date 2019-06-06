package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * This class contains tests for Library.
 * 
 * @author Erin Parker,Kyle Perry, and Erdi Fan
 * @version January 16, 2019
 */
public class LibraryTester {

	private Library emptyLib, smallLib, mediumLib, largeLib, missingLib;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();
		
		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
		
		largeLib = new Library();
		largeLib.addAll("src/assign02/Mushroom_Publishing_Large.txt");
		
		missingLib = new Library();

	}

	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}
	
	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}
	
	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testSmallLibraryCheckout() {
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
		assertFalse(smallLib.checkin(84392564893257043L));
	}

	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}
	
	@Test
	public void testMidLibraryLookupISBN() {
		assertNull(mediumLib.lookup(10780330351690L));
	}
	
	@Test
	public void testMidlLibraryLookupHolder() {
		mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9781843190363L, "Emma Lorant", "Cloner"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testMidLibraryCheckout() {
		assertTrue(mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testMidLibraryCheckinISBN() {
		mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008);
		assertTrue(mediumLib.checkin(9781843190363L));
		assertFalse(mediumLib.checkin(84392564893257043L));
	}

	@Test
	public void testMidLibraryCheckinHolder() {
		assertFalse(mediumLib.checkin("Jane Doe"));
	}
	
	@Test
	public void testLibraryMissing() {
		missingLib.addAll("src/assign02/Mushroom_Publishing_Missing.txt");
		assertNull(missingLib.lookup(9781843190011L));
		
	}
	
	@Test
	public void testLarLibraryLookupISBN() {
		assertNull(largeLib.lookup(2089897206263L));
	}
	
	@Test
	public void testLarlLibraryLookupHolder() {
		largeLib.checkout(2089897206263L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = largeLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(2089897206263L, "Kyle Perry", "Erdi Fan"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testLarLibraryCheckout() {
		assertTrue(largeLib.checkout(2089897206263L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testLarLibraryCheckinISBN() {
		largeLib.checkout(2089897206263L, "Jane Doe", 1, 1, 2008);
		assertTrue(largeLib.checkin(2089897206263L));
		assertFalse(largeLib.checkin(84392564893257043L));
	}

	@Test
	public void testLarLibraryCheckinHolder() {
		assertFalse(largeLib.checkin("Jane Doe"));
	}
}