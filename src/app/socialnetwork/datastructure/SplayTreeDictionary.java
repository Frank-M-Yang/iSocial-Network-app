package app.socialnetwork.datastructure;

/**
 * Splay Tree based Dictionary. Tree is splayed upon every element access for
 * better performance.
 * 
 */
public class SplayTreeDictionary {

	private class SplayNode implements Comparable {
		private Comparable key;
		private Object value;
		private SplayNode left;
		private SplayNode right;
		private SplayNode parent;

		public SplayNode(Comparable key, Object value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		public int compareTo(Object o) {
			SplayNode other = (SplayNode) o;
			return this.key.compareTo(other.key);
		}
	}

	private SplayNode root;

	public SplayTreeDictionary() {
		root = null;
	}

	/**
	 * Rotate right at node y.
	 */
	private void rotateRight(SplayNode y) {
		SplayNode x = y.left;
		y.left = x.right;
		if (x.right != null) {
			x.right.parent = y;
		}
		x.parent = y.parent;
		if (y.parent == null) {
			root = x;
		} else if (y == y.parent.right) {
			y.parent.right = x;
		} else {
			y.parent.left = x;
		}
		x.right = y;
		y.parent = x;
	}

	/**
	 * Rotate left at node x.
	 */
	private void rotateLeft(SplayNode x) {
		SplayNode y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	/**
	 * Splay operation - move node to root.
	 */
	private void splay(SplayNode node) {
		while (node.parent != null) {
			if (node.parent.parent == null) {
				// Zig step
				if (node == node.parent.left) {
					rotateRight(node.parent);
				} else {
					rotateLeft(node.parent);
				}
			} else if (node == node.parent.left && node.parent == node.parent.parent.left) {
				// Zig-zig step (left-left)
				rotateRight(node.parent.parent);
				rotateRight(node.parent);
			} else if (node == node.parent.right && node.parent == node.parent.parent.right) {
				// Zig-zig step (right-right)
				rotateLeft(node.parent.parent);
				rotateLeft(node.parent);
			} else if (node == node.parent.right && node.parent == node.parent.parent.left) {
				// Zig-zag step (left-right)
				rotateLeft(node.parent);
				rotateRight(node.parent);
			} else {
				// Zig-zag step (right-left)
				rotateRight(node.parent);
				rotateLeft(node.parent);
			}
		}
	}

	/**
	 * Add a key-value pair to the dictionary. Splays the inserted node to root.
	 */
	public void add(Comparable key, Object value) {
		SplayNode newNode = new SplayNode(key, value);

		if (root == null) {
			root = newNode;
			return;
		}

		SplayNode current = root;
		SplayNode parent = null;

		while (current != null) {
			parent = current;
			int comparison = key.compareTo(current.key);

			if (comparison == 0) {
				current.value = value;
				splay(current);
				return;
			} else if (comparison < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		newNode.parent = parent;
		if (key.compareTo(parent.key) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

		splay(newNode);
	}

	/**
	 * Find a value by key. Splays the found node to root.
	 */
	public Object find(Comparable key) {
		SplayNode node = findNode(key);
		if (node != null) {
			splay(node);
			return node.value;
		}
		return null;
	}

	/**
	 * Helper to find a node by key.
	 */
	private SplayNode findNode(Comparable key) {
		SplayNode current = root;

		while (current != null) {
			int comparison = key.compareTo(current.key);

			if (comparison == 0) {
				return current;
			} else if (comparison < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		return null;
	}

	/**
	 * Remove a key from the dictionary.
	 */
	public void removeKey(Comparable key) {
		SplayNode node = findNode(key);
		if (node == null) {
			return;
		}

		splay(node);

		if (node.left == null) {
			root = node.right;
			if (root != null) {
				root.parent = null;
			}
		} else if (node.right == null) {
			root = node.left;
			if (root != null) {
				root.parent = null;
			}
		} else {
			SplayNode maxLeft = node.left;
			while (maxLeft.right != null) {
				maxLeft = maxLeft.right;
			}

			splay(maxLeft);
			maxLeft.right = node.right;
			node.right.parent = maxLeft;
			root = maxLeft;
		}
	}

	/**
	 * Get the size of the dictionary.
	 */
	public int size() {
		return sizeHelper(root);
	}

	private int sizeHelper(SplayNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + sizeHelper(node.left) + sizeHelper(node.right);
	}
}
