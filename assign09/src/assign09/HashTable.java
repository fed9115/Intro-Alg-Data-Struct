package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * In this class, we'll implement the hash map using separate chaining method.
 * We'll use the ArrayList to represent the whole table and use the LinkedList
 * to represent the Map containing MapEntry objects
 * 
 * @author Kyle Perry, Erdi Fan
 */
public class HashTable<K, V> implements Map<K, V> {

	private ArrayList<LinkedList<MapEntry<K, V>>> store;
	private int numPairs;
	private int numCollisions;

	public HashTable() {
		numPairs = 0;
		store = new ArrayList<LinkedList<MapEntry<K, V>>>(100);
		for (int i = 0; i < 100; i++)
			store.add(new LinkedList<MapEntry<K, V>>());
	}

	// DELETE BEFORE SUBMISSION THIS IS ONLY FOR TESTING PURPOSES
	public int listLength() {
		return store.size();
	}

	@Override
	public void clear() {
		numPairs = 0;
		store = new ArrayList<LinkedList<MapEntry<K, V>>>(100);
		for (int i = 0; i < 100; i++)
			store.add(new LinkedList<MapEntry<K, V>>());
	}

	@Override
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i) != null) {
				LinkedList<MapEntry<K, V>> bucket = store.get(i);
				for (MapEntry<K, V> entry : bucket) {
					if (entry.getValue().equals(value))
						return true;
					// Increment the number of collisions by one for each entry we check that does
					// not contain the value we are looking for
					numCollisions++;
				}
			}
			numCollisions++;
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> list = new ArrayList<>();
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i) != null) {
				LinkedList<MapEntry<K, V>> bucket = store.get(i);
				for (MapEntry<K, V> entry : bucket) {
					list.add(entry);
				}
			}
		}
		return list;
	}

	@Override
	public V get(K key) {
		if (store.get(Math.abs(key.hashCode()) % store.size()) != null) {
			LinkedList<MapEntry<K, V>> bucket = store.get(Math.abs(key.hashCode()) % store.size());
			for (MapEntry<K, V> entry : bucket) {
				if (entry.getKey().equals(key))
					return entry.getValue();
				// Increment the number of collisions by one for each entry we check that does
				// not have the key value we are looking for
				numCollisions++;
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return numPairs == 0;
	}

	@Override
	public V put(K key, V value) {
		LinkedList<MapEntry<K, V>> bucket = store.get(Math.abs(key.hashCode()) % store.size());
		if (get(key) != null) {
			for (MapEntry<K, V> entry : bucket) {
				if (entry.getKey().equals(key)) {
					V prevVal = entry.getValue();
					entry.setValue(value);
					return prevVal;
				}
				// Increment the number of collisions by one for each entry we check that does
				// not have the key value we are looking for
				numCollisions++;
			}
		}
		// Check if the load factor will be greater than 10 after adding the map entry
		if (numPairs + 1 >= store.size() * 10.0) {
			rehash();
		}
		MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
		bucket.add(mapEntry);
		numPairs++;
		return null;
	}

	/**
	 * Helper method for us to increment the size of the ArrayList called store
	 */
	private void rehash() {
		List<MapEntry<K, V>> entriesList = entries();
		int size = store.size();
		store = new ArrayList<>(size * 2);
		numPairs = 0;
		for (int i = 0; i < size * 2; i++) {
			store.add(new LinkedList<MapEntry<K, V>>());
		}
		for (MapEntry<K, V> entry : entriesList) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(K key) {
		if (store.get(Math.abs(key.hashCode()) % store.size()) != null) {
			LinkedList<MapEntry<K, V>> bucket = store.get(Math.abs(key.hashCode()) % store.size());
			for (MapEntry<K, V> entry : bucket) {
				if (entry.getKey().equals(key)) {
					V delete = entry.getValue();
					bucket.remove(entry);
					numPairs--;
					return delete;
				}
				// Increment the number of collisions by one for each entry we check that does
				// not have the key value we are looking for
				numCollisions++;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return numPairs;
	}

	/**
	 * A getter method to return the number of collisions that have occurred in this
	 * hash table from all actions taken from instantiation OR since the last time
	 * the number of collisions was reset @return; the number of collisions
	 */
	public int getNumCollisions() {
		return numCollisions;
	}

	/**
	 * Resets the number of collisions that have occurred in this hash table from
	 * all actions taken.
	 */
	public void resetNumCollisions() {
		numCollisions = 0;
	}

}
