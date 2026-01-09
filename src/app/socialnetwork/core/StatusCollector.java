package app.socialnetwork.core;

import app.socialnetwork.datastructure.RedBlackTree;
import app.socialnetwork.datastructure.Vector;

public class StatusCollector extends RedBlackTree.TreeAction {

	private Vector collection;

	/**
	 * Creates a collector
	 *
	 * @param collection the Vector to collect items into
	 */
	public StatusCollector(Vector collection) {
		this.collection = collection;
	}

	/**
	 * Called for each node during tree traversal
	 *
	 * @param n the current tree node
	 */
	@Override
	public void run(RedBlackTree.ColouredTreeNode n) {
		collection.addLast(n.getValue());
	}
}
