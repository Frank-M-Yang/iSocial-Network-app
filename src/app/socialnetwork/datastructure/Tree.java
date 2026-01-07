package app.socialnetwork.datastructure;

public class Tree {

	public class TreeNode implements Comparable {
		private Comparable value;
		private TreeNode leftNode;
		private TreeNode rightNode;

		public TreeNode(Comparable v) {
			value = v;
			leftNode = null;
			rightNode = null;
		}

		public TreeNode(Comparable v, TreeNode left, TreeNode right) {
			value = v;
			leftNode = left;
			rightNode = right;
		}

		public TreeNode getLeftTree() {
			return leftNode;
		}

		public TreeNode getRightTree() {
			return rightNode;
		}

		public Comparable getValue() {
			return value;
		}

		public void setLeftTree(TreeNode left) {
			leftNode = left;
		}

		public void setRightTree(TreeNode right) {
			rightNode = right;
		}

		@Override
		public int compareTo(Object o) {
			if (o instanceof TreeNode) {
				TreeNode other = (TreeNode) o;
				return this.value.compareTo(other.value);
			}
			return this.value.compareTo(o);
		}
	}

	public abstract class TreeAction {
		public abstract void run(TreeNode n);
	}

	protected TreeNode root;

	public Tree() {
		root = null;
	}

	public void traverse(TreeAction action) {
		if (root == null) {
			return;
		}

		Queue t = new Queue();
		t.push(root);
		while (!t.empty()) {
			TreeNode n = (TreeNode) t.pop();
			action.run(n);

			if (n.getLeftTree() != null)
				t.push(n.getLeftTree());
			if (n.getRightTree() != null)
				t.push(n.getRightTree());
		}
	}

	public void traverseNode(TreeNode n, TreeAction action) {
		if (n != null) {
			if (n.getLeftTree() != null)
				traverseNode(n.getLeftTree(), action);
			action.run(n);
			if (n.getRightTree() != null)
				traverseNode(n.getRightTree(), action);
		}
	}

	public void traverseInOrder(TreeAction action) {
		traverseNode(root, action);
	}

	public void insert(Comparable element) {
		insertAtNode(element, root, null);
	}

	private void insertAtNode(Comparable element, TreeNode current, TreeNode parent) {
		if (current == null) {
			TreeNode newNode = new TreeNode(element);
			if (parent != null) {
				if (element.compareTo(parent.value) < 0) {
					parent.leftNode = newNode;
				} else {
					parent.rightNode = newNode;
				}
			} else {
				root = newNode;
			}
		} else if (element.compareTo(current.value) == 0) {
			return;
		} else if (element.compareTo(current.value) < 0) {
			insertAtNode(element, current.getLeftTree(), current);
		} else {
			insertAtNode(element, current.getRightTree(), current);
		}
	}

	public boolean contains(Comparable element) {
		return searchNode(element, root);
	}

	private boolean searchNode(Comparable element, TreeNode current) {
		if (current == null) {
			return false;
		}

		int comparison = element.compareTo(current.value);

		if (comparison == 0) {
			return true;
		} else if (comparison < 0) {
			return searchNode(element, current.getLeftTree());
		} else {
			return searchNode(element, current.getRightTree());
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public TreeNode getRoot() {
		return root;
	}

	public int height() {
		return heightNode(root);
	}

	private int heightNode(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int leftHeight = heightNode(node.getLeftTree());
		int rightHeight = heightNode(node.getRightTree());

		return 1 + Math.max(leftHeight, rightHeight);
	}

	public int size() {
		return sizeNode(root);
	}

	private int sizeNode(TreeNode node) {
		if (node == null) {
			return 0;
		}

		return 1 + sizeNode(node.getLeftTree()) + sizeNode(node.getRightTree());
	}

	public Comparable findMin() {
		if (root == null) {
			return null;
		}
		return findMinNode(root).value;
	}

	private TreeNode findMinNode(TreeNode node) {
		if (node.getLeftTree() == null) {
			return node;
		}
		return findMinNode(node.getLeftTree());
	}

	public Comparable findMax() {
		if (root == null) {
			return null;
		}
		return findMaxNode(root).value;
	}

	private TreeNode findMaxNode(TreeNode node) {
		if (node.getRightTree() == null) {
			return node;
		}
		return findMaxNode(node.getRightTree());
	}

	public void swapTree() {
		swapTreeNode(root);
	}

	private void swapTreeNode(TreeNode node) {
		if (node == null) {
			return;
		}

		TreeNode temp = node.leftNode;
		node.leftNode = node.rightNode;
		node.rightNode = temp;

		swapTreeNode(node.leftNode);
		swapTreeNode(node.rightNode);
	}

	public Comparable findMedian() {
		if (root == null) {
			return null;
		}

		int size = size();
		int medianIndex = size / 2;
		int[] counter = { 0 };
		return findKthElement(root, medianIndex, counter);
	}

	private Comparable findKthElement(TreeNode node, int k, int[] counter) {
		if (node == null) {
			return null;
		}

		Comparable left = findKthElement(node.getLeftTree(), k, counter);
		if (left != null) {
			return left;
		}

		if (counter[0] == k) {
			return node.value;
		}
		counter[0]++;

		return findKthElement(node.getRightTree(), k, counter);
	}

	public double findAverage() {
		if (root == null) {
			return 0.0;
		}

		int size = size();
		double sum = calculateSum(root);

		return sum / size;
	}

	private double calculateSum(TreeNode node) {
		if (node == null) {
			return 0.0;
		}

		double currentValue = 0.0;
		if (node.value instanceof Number) {
			currentValue = ((Number) node.value).doubleValue();
		}

		double leftSum = calculateSum(node.getLeftTree());
		double rightSum = calculateSum(node.getRightTree());

		return currentValue + leftSum + rightSum;
	}

	public Comparable removeMin() {
		if (root == null) {
			return null;
		}

		if (root.getLeftTree() == null) {
			Comparable minValue = root.value;
			root = root.getRightTree();
			return minValue;
		}

		return removeMinNode(root);
	}

	private Comparable removeMinNode(TreeNode parent) {
		TreeNode current = parent.getLeftTree();

		if (current.getLeftTree() == null) {
			Comparable minValue = current.value;
			parent.setLeftTree(current.getRightTree());
			return minValue;
		}

		return removeMinNode(current);
	}
}