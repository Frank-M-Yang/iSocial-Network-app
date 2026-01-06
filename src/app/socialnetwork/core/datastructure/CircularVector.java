package app.socialnetwork.core.datastructure;

public class CircularVector {
	private Object data[];
	private int first;
	private int count;

	public CircularVector(int capacity) {
		count = 0;
		first = 0;
		data = new Object[capacity];
	}

	public int size() {
		return count;
	}

	public void addFirst(Object element) {
		first = (first - 1 + data.length) % data.length;
		data[first] = element;
		count++;
	}

	public void addLast(Object element) {
		int last = (first + count) % data.length; // % ensures wraparound when reaching array end
		data[last] = element;
		count++;
	}

	public Object getFirst() {
		if (count == 0)
			return null;
		return data[first];
	}

	public Object getLast() {
		if (count == 0)
			return null;
		int last = (first + count - 1) % data.length;
		return data[last];
	}

	public void removeFirst() {
		if (count > 0) {
			first = (first + 1) % data.length;
			count--;
		}
	}

	public void removeLast() {
		if (count > 0) {
			count--;
		}
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public String toString() {
		String s = "[";
		for (int i = 0; i < count; i++) {
			int index = (first + i) % data.length;
			s += data[index].toString();
			s += " ";
		}
		s += "]";
		return s;
	}

}
