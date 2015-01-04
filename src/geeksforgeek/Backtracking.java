package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class Backtracking {

	private static String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		return new String(arr);
	}
	
	private static void stringPermutation(String s, int i, int n) {
		if (i == n) {
			System.out.println(s);
		}
		else {
			for (int j = i; j <= n; ++j) {
				s = swap(s, i, j);
				stringPermutation(s, i+1, n);
				s = swap(s, i, j); // backtrack
			}
		}
	}
	
	@Test
	public void stringPermutation() {
		stringPermutation("abc", 0, 2);
	}
	
	// naive recursive
	private int longestIncreasingSubsequence(int[] arr, int n, int absoluteMax) {
		if (n == 1) {
			return n;
		}
		
		int maxEndingHere = 1;
		
		for (int i = 1; i < n; ++i) {
			int result = longestIncreasingSubsequence(arr, i, absoluteMax);
			if (arr[i-1] < arr[i] && result+1 > maxEndingHere) {
				maxEndingHere = result+1;
			}
		}
		
		if (absoluteMax < maxEndingHere) {
			absoluteMax = maxEndingHere;
		}
		
		return absoluteMax;
	}
	
	// dynamic programming O(n^2)
	private int longestIncreasingSubsequenceMyImpl(int[] arr) {
		int[] lookup = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			lookup[i] = 1;
		}

		for (int i = arr.length-1; i >= 1; --i) {
			if (arr[i] > arr[i-1]) {
				lookup[i-1] = lookup[i]+1;
			}
			else {
				lookup[i-1] = lookup[i];
			}
		}
		
		return lookup[0];
	}
	
	private int longestIncreasingSubsequenceGeeksForGeeks(int[] arr) {
		int[] lookup = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			lookup[i] = 1;
		}

		for (int i = 1; i < arr.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (arr[j] < arr[i] && lookup[i] < lookup[j]+1) {
					lookup[i] = lookup[j]+1;
				}
			}
		}
		
		// return the max of it
		int result = lookup[0];
		for (int i = 1; i < lookup.length; ++i) {
			if (lookup[i] > result) {
				result = lookup[i];
			}
		}
		return result;
	}	

	public void evaluateLongestIncreasingSubsequence(int[] inputArr) {
		int max1 = longestIncreasingSubsequence(inputArr, 8, 1);
		//int maxMyImpl = longestIncreasingSubsequenceMyImpl(inputArr);
		int maxGeeksForGeeks = longestIncreasingSubsequenceGeeksForGeeks(inputArr);
		//assertEquals(max1, maxMyImpl);
		assertEquals(max1, maxGeeksForGeeks);
	}
	
	@Test
	public void longestIncreasingSubsequence() {
		evaluateLongestIncreasingSubsequence(new int[]{ 10, 22, 9, 33, 21, 50, 41, 60 });
		evaluateLongestIncreasingSubsequence(new int[]{ 10, 22, 9, 33, 21, 50, 11, 60 });
		evaluateLongestIncreasingSubsequence(new int[]{ 10, 21,22,23,24,11,25,18,60 });
		// 10 22 50 60
		// 9 21 50 60
		// 3 21 50 60
		// 21 50
		// 21 60
	}
}
