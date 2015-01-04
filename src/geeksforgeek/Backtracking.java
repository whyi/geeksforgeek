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
	
	// naive, recursive
	private int longestIncreasingSubsequence(int[] arr, int n, int absoluteMax) {
		if (n==1) {
			return 1;
		}
		
		int maxSoFar = 1;

		for (int i = 1; i < n; ++i) {
			int result = longestIncreasingSubsequence(arr, i, absoluteMax);
			if (arr[i-1] < arr[n-1] && maxSoFar < result+1) {
				maxSoFar = result+1;
			}
		}
		
		if (maxSoFar > absoluteMax) {
			absoluteMax = maxSoFar;
		}
		return absoluteMax;
	}
	
	// dynamic programming O(n^2)
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

	@Test
	public void longestIncreasingSubsequence() {
		// 10 22 33 50 60
		int[] inputArr = new int[]{ 10, 22, 9, 33, 21, 50, 41, 60 };
		int result = longestIncreasingSubsequence(inputArr, inputArr.length, 1);
		//int maxMyImpl = longestIncreasingSubsequenceMyImpl(inputArr);
		System.out.println(result);
		// note that there's nlogn solution for this... 
		int maxGeeksForGeeks = longestIncreasingSubsequenceGeeksForGeeks(inputArr);
		//assertEquals(max1, maxMyImpl);
		assertEquals(result, maxGeeksForGeeks);
		//evaluateLongestIncreasingSubsequence(new int[]{ 10, 22, 9, 33, 21, 50, 11, 60 });
		//evaluateLongestIncreasingSubsequence(new int[]{ 10, 21,22,23,24,11,25,18,60 });
	}
	
	// naive recursive O^n in worst case when they all mismatch
	private static int longestCommonSubsequence(String s1, String s2, int i, int j) {
		
		// check this condition, in geeks for geeks those were given as i == 0 || j == 0
		// Why? it's zero based anyway.. not sure why until I grab the C code and compile and compare one by one
		// not gonna do it for now.
		if (i < 0 || j < 0) {
			return 0;
		}
		
		if (s1.charAt(i) == s2.charAt(j)) {
			return 1+longestCommonSubsequence(s1,s2,i-1,j-1);
		}
		else {
			return Integer.max(longestCommonSubsequence(s1,s2,i-1,j), longestCommonSubsequence(s1,s2,i,j-1));
		}
	}
	
	@Test
	public void longestCommonSubsequence() {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		int result = longestCommonSubsequence(s1, s2, s1.length()-1, s2.length()-1);
		System.out.println("result : " + result);
	}
}
