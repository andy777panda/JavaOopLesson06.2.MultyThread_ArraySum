package net.ukr.andy777;

import java.math.BigInteger;

public class SumThread implements Runnable {
	private int array[];
	private int start;
	private int end;
	private BigInteger sumThread;
	private Thread thr;
	
	public SumThread(int array[], int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
		thr = new Thread(this);
		thr.start();
	}

	public int[] getArray() {
		return array;
	}

	public BigInteger getSumThreads() {
		return sumThread;
	}

	public Thread getThr() {
		return thr;
	}

	protected static BigInteger SumArray(int array[], int start, int end) {
		BigInteger sum = new BigInteger("0");
		for (int i = start; i < end; i++)
			sum = sum.add(new BigInteger("" + array[i]));
		return sum;
	}

	@Override
	public void run() {
		sumThread = new BigInteger("" + SumThread.SumArray(array, start, end));
	}
}
