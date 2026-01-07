package app.socialnetwork.datastructure;

public class Stack {
	private Vector data;

	public Stack() {
		data = new Vector(100);
	}

	public void push(Object o) {
		data.addLast(o);
	}

	public Object pop() {
		Object top = data.getLast();
		data.removeLast();
		return top;
	}

	public Object top() {
		return data.getLast();
	}

	public int size() {
		return data.size();
	}

	public boolean empty() {
		return data.isEmpty();
	}

}
