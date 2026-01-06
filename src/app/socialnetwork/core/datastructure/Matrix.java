package app.socialnetwork.core.datastructure;

public class Matrix {
	// some appropriate private members.
	private Comparable[][] matrix;
	private int size;

	public Matrix(int nrNodes) {
		// allocate an N-by-N matrix where N = nrNodes
		// all elements are initially 0

		this.size = nrNodes;
		matrix = new Comparable[nrNodes][nrNodes];
	}

	public void set(int row, int col, Comparable weight) {
		// store the weight at the given row and column.
		boundcheck(row, col);
		matrix[row][col] = weight;
	}

	public Comparable get(int row, int col) {
		// return the weight at the given row and column.
		boundcheck(row, col);
		return matrix[row][col];
	}

	public void boundcheck(int row, int col) {
		if (row < 0 || row >= size || col < 0 || col >= size) {
			throw new IndexOutOfBoundsException("Invalid matrix index (" + row + ", " + col + ")");
		}
	}
}
