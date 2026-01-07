package app.socialnetwork.datastructure;

public class Queue {
	private Vector data;

	public Queue() {
		data = new Vector(100);
	}

	public void push(Object o) {
		data.addLast(o);
	}

	public Object pop() {
		Object front = data.getFirst();
		data.removeFirst();
		return front;
	}

	public Object top() {
		return data.getFirst();
	}

	public int size() {
		return data.size();
	}

	public boolean empty() {
		return data.isEmpty();
	}

}
