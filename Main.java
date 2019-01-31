package net.ukr.andy777;

/*
 Lesson06.2
 Написать код для многопоточного подсчета суммы элементов массива целых чисел.
 Сравнить скорость подсчета с простым алгоритмом.
 */

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int array[] = new int[44000000];
		int arrayCopy[];

		// initialize of array
		for (int i = 0; i < array.length; i++)
			array[i] = (int) (Math.random() * 9);

		resMutiThreadSum(array, 1);
		resMutiThreadSum(array, 2);
		resMutiThreadSum(array, 3);
		resMutiThreadSum(array, 4);

	}

	public static void resMutiThreadSum(int array[], int qThreads) {
		long tstart, tend;

		tstart = System.currentTimeMillis();
		System.out.print("sum of array elements = "
				+ mutiThreadSum(array, qThreads));
		tend = System.currentTimeMillis();
		System.out.println(" -- " + (tend - tstart) + " ms" + " = " + qThreads
				+ "-thread Sum");
	}

	public static BigInteger mutiThreadSum(int array[], int qThreads) {
		BigInteger res = new BigInteger("0");
		SumThread[] sumThread = new SumThread[qThreads];
		// int qThreads = кількість потоків

		for (int i = 0; i < sumThread.length; i++) {
			int size = array.length / qThreads;
			int begin = size * i;
			int end = ((i + 1) * size);
			if ((array.length - end) < size) {
				end = array.length;
			}
			sumThread[i] = new SumThread(array, begin, end);
		}
		for (int i = 0; i < sumThread.length; i++) {
			try {
				sumThread[i].getThr().join();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		for (int i = 0; i < sumThread.length; i++)
			res = res.add(new BigInteger("" + sumThread[i].getSumThreads()));
		return res;
	}
}