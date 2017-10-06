package priceton_algos;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int n;
	private int openSites=0;
	ArrayList<Boolean> isOpen;
	WeightedQuickUnionUF quickUnion;

	public Percolation(int n) {
		this.n = n;
		if (n <= 0)
			throw new IllegalArgumentException();

		isOpen = new ArrayList<Boolean>();
		for (int i = 0; i < n * n; i++) {
			isOpen.add(false);
		}
		quickUnion = new WeightedQuickUnionUF(n * n);
	}

	public void open(int row, int col) {
		openSites++;
		validate(row, col);

	}

	public boolean isOpen(int row, int col) {
		int index = xyTo1D(row, col);

		return isOpen.get(index);
	}

	public boolean isFull(int row, int col) {
		return false;
	}

	public int numberOfOpenSites() {
		return 0;
	}

	public boolean percolates() {
		return false;
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
