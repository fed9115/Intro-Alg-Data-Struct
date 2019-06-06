package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * A test class for our HashTable class
 * 
 * @author Erdi Fan & Kyle Perry
 *
 */

public class HashTableTest {

	/**
	 * Create your own JUnit 5 test class(es). Ensure that your tests are organized
	 * and cover a broad range of possible inputs for all HashTable methods you
	 * write, and for a variety of different key and value types.
	 */

	@Test
	public void hashTableConstructorTest() {
		HashTable<String, Integer> hashT = new HashTable<>();
		assertEquals(0, hashT.size());
	}

	@Test
	public void hashTableSizeAndClearTest() {
		HashTable<String, Integer> hashT = new HashTable<>();
		// add 50 key, value pairs to the hash table
		for (int i = 0; i < 50; i++) {
			// generate a random string
			String key = "";
			Random rng = new Random();
			for (int j = 0; j < 5; j++) {
				key += (rng.nextInt(26) + 97);
			}
			int value = i;
			hashT.put(key, value);
		}

		assertEquals(50, hashT.size());

		hashT.clear();
		assertEquals(0, hashT.size());
	}

	@Test
	public void hashTableResizeAndRehashTest() {
		HashTable<String, Integer> hashT = new HashTable<>();
		// add 50 key, value pairs to the hash table
		for (int i = 0; i < 101; i++) {
			// generate a random string
			String key = "";
			Random rng = new Random();
			for (int j = 0; j < 5; j++) {
				key += (rng.nextInt(26) + 97);
			}
			int value = i;
			// If the load factor will be 10 after adding the next key value pair check the
			// size of the table, add the next pair and then recheck the load factor and the
			// size of the table
			if (hashT.size() + 1 >= hashT.listLength() * 10.0) {
				int preListLength = hashT.listLength();
				hashT.put(key, value);
				// Check to see that the length of the backing array list has doubled
				assertEquals(preListLength * 2, hashT.listLength());
				// Check to see that the load factor of the hash table is now "close to" 5 after
				// doubling the backing array list and adding one more pair
				assertTrue(hashT.size() / hashT.listLength() < 5.005);
			}
		}
	}
	
	HashTable<StudentBadHash, Double> test = new HashTable<StudentBadHash, Double>();

	StudentBadHash alan = new StudentBadHash(1019999, "Alan", "Turing");
	StudentBadHash ada = new StudentBadHash(1004203, "Ada", "Lovelace");
	StudentBadHash edsgar = new StudentBadHash(1010661, "Edsgar", "Dijkstra");
	StudentBadHash grace = new StudentBadHash(1019941, "Grace", "Hopper");

	@Test
	void testHashTable() {
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
	}

	@Test
	void testClear() {
		test.put(ada, 2.0);
		assertEquals(1, test.size());
		test.clear();
		assertEquals(0, test.size());
	}

	@Test
	void testContainsKey() {
		test.put(ada, 2.0);
		test.put(alan, 4.0);
		assertTrue(test.containsKey(ada));
		assertFalse(test.containsKey(edsgar));
	}

	@Test
	void testContainsValue() {
		test.put(ada, 2.0);
		test.put(alan, 4.0);
		assertTrue(test.containsValue(2.0));
		assertFalse(test.containsValue(2.1));
		test.remove(ada);
		assertFalse(test.containsValue(2.0));
	}

	@Test
	void testEntries() {
		test.put(ada, 2.0);
		test.put(alan, 4.0);
		test.put(edsgar, 1.0);
		test.put(grace, 3.0);
		ArrayList<MapEntry<StudentBadHash, Double>> list = new ArrayList<>();
		MapEntry<StudentBadHash, Double> m1 = new MapEntry<>(ada, 2.0);
		MapEntry<StudentBadHash, Double> m2 = new MapEntry<>(alan, 4.0);
		MapEntry<StudentBadHash, Double> m3 = new MapEntry<>(edsgar, 1.0);
		MapEntry<StudentBadHash, Double> m4 = new MapEntry<>(grace, 3.0);
		list.add(m2);
		list.add(m1);
		list.add(m4);
		list.add(m3);
		for (int i = 0; i < list.size(); i++) {
			assertTrue(list.get(i).equals(test.entries().get(i)));
		}
	}

	@Test
	void testGet() {
		test.put(ada, 2.0);
		assertEquals(2.0, (double) test.get(ada));
		assertEquals(null, test.get(alan));
	}

}
