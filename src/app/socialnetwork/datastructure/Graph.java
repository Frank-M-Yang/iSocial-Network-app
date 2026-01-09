package app.socialnetwork.datastructure;

/**
 * Graph using adjacency list representation. Supports both directed and
 * undirected graphs. Nodes are identified by Comparable labels (e.g.,
 * usernames).
 */
public class Graph {

	public class Node implements Comparable {
		private Comparable info;
		private Vector edges;

		public Node(Comparable label) {
			info = label;
			edges = new Vector(100);
		}

		public void addEdge(Edge e) {
			edges.addLast(e);
		}

		/**
		 * Get all edges from this node
		 * 
		 * @return the vector of edges
		 */
		public Vector getEdges() {
			return edges;
		}

		public int compareTo(Object o) {
			// two nodes are equal if they have the same label
			Node n = (Node) o;
			return n.info.compareTo(info);
		}

		public Comparable getLabel() {
			return info;
		}
	}

	private class Edge implements Comparable {
		private Node toNode;

		public Edge(Node to) {
			toNode = to;
		}

		/**
		 * Get the destination node of this edge
		 * 
		 * @return the destination node
		 */
		public Node getToNode() {
			return toNode;
		}

		public int compareTo(Object o) {
			// two edges are equal if they point
			// to the same node.
			// this assumes that the edges are
			// starting from the same node !!!
			Edge n = (Edge) o;
			return n.toNode.compareTo(toNode);
		}
	}

	private Vector nodes;

	public Graph() {
		nodes = new Vector(100);
	}

	public void addNode(Comparable label) {
		nodes.addLast(new Node(label));
	}

	private Node findNode(Comparable nodeLabel) {
		Node res = null;
		for (int i = 0; i < nodes.size(); i++) {
			Node n = (Node) nodes.get(i);
			if (n.getLabel().equals(nodeLabel)) { // ← 改用 .equals() 而不是 ==
				res = n;
				break;
			}
		}
		return res;
	}

	public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2) {
		Node n1 = findNode(nodeLabel1);
		Node n2 = findNode(nodeLabel2);
		if (n1 != null && n2 != null) {
			n1.addEdge(new Edge(n2));
		}
	}

	/**
	 * Check if there is an edge from nodeLabel1 to nodeLabel2 Time complexity:
	 * O(degree of nodeLabel1)
	 * 
	 * @param nodeLabel1 the source node label
	 * @param nodeLabel2 the destination node label
	 * @return true if edge exists, false otherwise
	 */
	public boolean hasEdge(Comparable nodeLabel1, Comparable nodeLabel2) {
		Node n1 = findNode(nodeLabel1);
		Node n2 = findNode(nodeLabel2);

		// If either node doesn't exist, no edge
		if (n1 == null || n2 == null) {
			return false;
		}

		// Check if n1 has an edge pointing to n2
		Vector edges = n1.getEdges();
		for (int i = 0; i < edges.size(); i++) {
			Edge e = (Edge) edges.get(i);
			if (e.getToNode().getLabel().equals(n2.getLabel())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Get all neighbors of a node (nodes it has edges to) Returns a Vector of
	 * Comparable labels Time complexity: O(degree of the node)
	 * 
	 * @param nodeLabel the node label
	 * @return a Vector containing the labels of all neighbors
	 */
	public Vector getNeighbors(Comparable nodeLabel) {
		Node node = findNode(nodeLabel);

		// If node doesn't exist, return empty vector
		if (node == null) {
			return new Vector(0);
		}

		Vector neighbors = new Vector(node.edges.size());
		for (int i = 0; i < node.edges.size(); i++) {
			Edge e = (Edge) node.edges.get(i);
			neighbors.addLast(e.getToNode().getLabel());
		}

		return neighbors;
	}

	/**
	 * Get the number of nodes in the graph
	 * 
	 * @return the number of nodes
	 */
	public int getNodeCount() {
		return nodes.size();
	}

	/**
	 * Check if a node exists in the graph
	 * 
	 * @param nodeLabel the node label to check
	 * @return true if the node exists, false otherwise
	 */
	public boolean hasNode(Comparable nodeLabel) {
		return findNode(nodeLabel) != null;
	}
}