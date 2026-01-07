package app.socialnetwork.datastructure;

/**
 * Dictionary implementation using Vector. Simple key-value storage with O(n)
 * lookup.
 * 
 */
public class Dictionary {

	private class DictionaryPair implements Comparable {
		private Object key;
		private Object value;

		public DictionaryPair(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return key;
		}

		public void setKey(Object key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public int compareTo(Object o) {
			DictionaryPair other = (DictionaryPair) o;
			return ((Comparable) this.key).compareTo(other.key);
		}
	}

	private Vector data;

	public Dictionary() {
		data = new Vector(100);
	}

	/**
	 * Add a key-value pair to the dictionary. If key exists, update the value. Time
	 * complexity: O(n)
	 */
	public void add(Object key, Object value) {
		int position = findPosition(key);
		if (position != -1) {
			DictionaryPair pair = (DictionaryPair) data.get(position);
			pair.setValue(value);
		} else {
			data.addLast(new DictionaryPair(key, value));
		}
	}

	/**
	 * Find the position of a key in the vector. Returns -1 if not found. Time
	 * complexity: O(n)
	 */
	public int findPosition(Object key) {
		for (int i = 0; i < data.size(); i++) {
			DictionaryPair pair = (DictionaryPair) data.get(i);
			if (pair.getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find and return the value associated with a key. Returns null if key not
	 * found. Time complexity: O(n)
	 */
	public Object find(Object key) {
		int position = findPosition(key);
		if (position != -1) {
			DictionaryPair pair = (DictionaryPair) data.get(position);
			return pair.getValue();
		}
		return null;
	}

	/**
	 * Remove a key-value pair from the dictionary. Time complexity: O(n)
	 */
	public void removeKey(Object key) {
		int position = findPosition(key);
		if (position != -1) {
			for (int i = position; i < data.size() - 1; i++) {
				data.set(i, data.get(i + 1));
			}
			data.removeLast();
		}
	}

	/**
	 * Get the number of key-value pairs in the dictionary.
	 */
	public int size() {
		return data.size();
	}
}