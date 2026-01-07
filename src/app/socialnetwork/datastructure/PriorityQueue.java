package app.socialnetwork.datastructure;

public class PriorityQueue {
	private class PriorityPair implements Comparable {
		private Object element;
		private Object priority;

		public PriorityPair(Object element, Object priority) {
			this.element = element;
			this.priority = priority;
		}

		public int compareTo(Object o) {
			PriorityPair p2 = (PriorityPair) o;
			return ((Comparable) priority).compareTo(p2.priority);
		}
	}

	private LinkedList data;

	public PriorityQueue() {
		data = new LinkedList();
	}

	public void push(Object o, int priority) {

		PriorityPair newPair = new PriorityPair(o, priority);

		// Case 1: Empty queue - insert at beginning
		if (data.isEmpty()) {
			data.addFirst(newPair);
			return;
		}

		// Case 2: New element has highest priority (smallest value)
		PriorityPair first = (PriorityPair) data.getFirst();
		if (newPair.compareTo(first) < 0) {
			data.addFirst(newPair);
			return;
		}

		// Case 3: Find correct position in sorted list
		int size = data.size();
		for (int i = 0; i < size; i++) {
			PriorityPair current = (PriorityPair) data.get(i);

			// Found position: new priority is less than current
			if (newPair.compareTo(current) < 0) {
				// Insert by rebuilding: add all before position, add new, add rest
				LinkedList temp = new LinkedList();

				// Add all elements before insertion point
				for (int j = 0; j < i; j++) {
					temp.addLast(data.get(j));
				}

				// Add new element
				temp.addLast(newPair);

				// Add remaining elements
				for (int j = i; j < size; j++) {
					temp.addLast(data.get(j));
				}

				data = temp;
				return;
			}
		}

		// Case 4: New element has lowest priority - insert at end
		data.addLast(newPair);

	}

	public Object pop() {
		if (data.isEmpty()) {
			return null;
		}
		PriorityPair pair = (PriorityPair) data.getFirst();
		data.removeFirst();
		return pair.element;
	}

	public Object top() {
		if (data.isEmpty()) {
			return null;
		}
		PriorityPair pair = (PriorityPair) data.getFirst();
		return pair.element;
	}
}