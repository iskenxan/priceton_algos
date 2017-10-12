package assigment_1;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int n;
	private int openSites=0;
	private int virtualTopSite=n*n;
	private int virtualBottomSite=n*n+1;
	
	private boolean [][] isOpen;
	private WeightedQuickUnionUF quickUnion;

	public Percolation(int n) {
		this.n = n;
		if (n <= 0)
			throw new IllegalArgumentException();

		isOpen = new boolean[n][n];
		
		quickUnion = new WeightedQuickUnionUF(n * n+2);
	}
	

	public void open(int row, int col) {
		validate(row, col);
		openSites++;
		if(row==1) {
			quickUnion.union(col-1, virtualTopSite);
		}
		
		int index=xyTo1D(row,col);
		isOpen[row-1][col-1]=true;
		connectToLeftCell(row,col,index);
		connectToTopCell(row, col, index);
		connectToRightCell(row,col,index);
		connectToBottomCell(row,col,index);
	}
	
	
	
	private void connectToLeftCell(int row, int col,int index) {
		if(col>1&&isOpen(row,col-1)) {
			int leftIndex=xyTo1D(row,col-1);
			quickUnion.union(leftIndex, index);
		}
	}

	
	private void connectToTopCell(int row, int col, int index) {
		if(row>1&&isOpen(row-1,col)) {
			int topIndex=xyTo1D(row-1,col);
			quickUnion.union(topIndex, index);
		}
	}
	
	
	private void connectToRightCell(int row, int col, int index) {
		if(col+1<n&&isOpen(row,col+1)) {
			int rightIndex=xyTo1D(row,col+1);
			quickUnion.union(rightIndex, index);
		}
	}
	
	
	private void connectToBottomCell(int row, int col, int index) {
		if(row+1<n&&isOpen(row+1,col)) {
			int rightIndex=xyTo1D(row+1,col);
			quickUnion.union(rightIndex, index);
		}
	}
	
	
	public boolean isOpen(int row, int col) {
		validate(row,col);

		return isOpen[row-1][col-1];
	}

	
	public boolean isFull(int row, int col) {
		int index= xyTo1D(row,col);
		boolean isConnectedToTop=quickUnion.connected(index, virtualTopSite);
		return isConnectedToTop;
	}
	

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {
		boolean bottomConnectToTop=quickUnion.
				connected(virtualTopSite, virtualBottomSite);
		return bottomConnectToTop;
	}

	private int xyTo1D(int row, int column) {
		int vRow = row - 1;
		int vColumn = column - 1;

		int finalIndex = vRow * n + vColumn;

		return finalIndex;
	}

	private void validate(int row, int column) {
		if (row < 0 || row > n || column < 0 || column > n)
			throw new IllegalArgumentException();
	}

	public static void main(String[] args) {
	}
}
