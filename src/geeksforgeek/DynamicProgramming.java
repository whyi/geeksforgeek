package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class DynamicProgramming {

	private static int fibRecursive(int n) {
		if (n<=1) {
			return n;
		}
		
		return fibRecursive(n-1) + fibRecursive(n-2);
	}
	
	private static int fibIterative(int n) {
		if (n==0) {
			return 0;
		}
		if (n==1) {
			return 1;
		}

		int prev1 = 1;
		int prev2 = 0;
		int current = 0;
		
		for (int i = 2; i <= n; ++i) {
			current = prev1+prev2;
			prev2 = prev1;
			prev1 = current;
		}
		return current;
	}
	
	static ArrayList<Integer> lookupTable = new ArrayList<Integer>();
	
	// memorization - TopDown
	private static int fibMemorization(int n) {
		if (lookupTable.get(n) == null) {
			if (n <= 1) {
				lookupTable.set(n, n);
			}
			else {
				lookupTable.set(n, fibRecursive(n-1) + fibRecursive(n-2));
			}
		}
		return lookupTable.get(n);
	}
	
	// tabulation - BottomUp
	private static int fibTabulation(int n) {
		int[] array = new int[n+2];

		// base cases
		array[0] = 0;
		array[1] = 1;

		for (int i = 2; i <= n; ++i) {
			array[i] = array[i-1] + array[i-2];
		}
		
		return array[n];
	}
	
	@Test
	public void test() {
		for (int i = 0; i < 5; ++i) {
			lookupTable.clear();
			for (int j = 0; j < i+1; ++j) {
				lookupTable.add(null);
			}
			assertEquals(fibRecursive(i), fibIterative(i));
			assertEquals(fibRecursive(i), fibMemorization(i));
			assertEquals(fibMemorization(i), fibIterative(i));
			assertEquals(fibMemorization(i), fibTabulation(i));
			assertEquals(fibTabulation(i), fibRecursive(i));
			assertEquals(fibTabulation(i), fibIterative(i));
		}
	}
	
	private static int computeMinimumNumberOfDrops(int numberOfEggs, int numberOfFloors) {
		if (numberOfFloors == 1 || numberOfFloors == 0) {
			return 1;
		}
		
		if (numberOfEggs == 1) {
			return numberOfFloors;
		}
		
		int result = Integer.MAX_VALUE;
		
		for (int i = 1; i <= numberOfFloors; ++i) {
			// reason for max : we want the WORST case.......
			// as the best case(when we take min) it's not so meaningful.
			int minimumSoFar = Math.max(
					computeMinimumNumberOfDrops(numberOfEggs-1, i-1),  // egg breaks
					computeMinimumNumberOfDrops(numberOfEggs,   numberOfFloors-i)); // egg didn't break
			
			if (minimumSoFar < result) {
				result = minimumSoFar;
			}
		}

		return result+1;
	}
	
	// TODO : implement memorization version of the computeMinimumNumberOfDrops here.
	
	
	@Test
	public void eggDrop() {
		int numberOfEggs = 2;
		int numberOfFloors = 10;
		assertEquals(4,computeMinimumNumberOfDrops(numberOfEggs, numberOfFloors));
	}
	
	private static int cuttingRod(int[] prices, int n) {
		if (n <= 0) {
			return 0;
		}

		++calcCount;
		int maxSoFar = Integer.MIN_VALUE;

		for (int i = 0; i < n; ++i) {
			maxSoFar = Math.max(cuttingRod(prices, n-i-1) + prices[i], maxSoFar);
		}
		return maxSoFar;
	}
	
	public static int[] lookup;
	
	// O(n^2)
	private static int cuttingRodDynamicProgramming(int[] prices, int n) {
//		if (n <= 0) {
//			return 0;
//		}
		lookup[0] = 0;

		int maxSoFar = Integer.MIN_VALUE;

		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < i; ++j) {
				++calcCount;
				maxSoFar = Math.max(lookup[i-j-1] + prices[j], maxSoFar);
			}
			lookup[i] = maxSoFar;
		}
		return lookup[n];
	}	
	
	private static int testCuttingRod(int[] prices, int n) {
		if (n == 0) {
			return 0;
		}
		
		++calcCount;
		int maxSoFar = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i) {
			maxSoFar = Math.max(maxSoFar, testCuttingRod(prices, n-i-1)+prices[i]);
		}
		return maxSoFar;
	}
	
	public static int readingCount = 0;
	public static int calcCount = 0;
	private static int testCuttingRodDynamicProgramming(int[] prices, int n, int[] lookupTable) {
		if (lookupTable[n] != -1) {
			++readingCount;
			return lookupTable[n];
		}
		
		++calcCount;
		int maxSoFar = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i) {
			lookupTable[n-i-1] = testCuttingRodDynamicProgramming(prices, n-i-1, lookupTable);
			maxSoFar = Math.max(maxSoFar, lookupTable[n-i-1]+prices[i]);
			
		}
		return maxSoFar;
	}
	
	private static int testCuttingRodDynamicProgramming(int[] prices, int n) {
		int[] lookupTable = new int[n+1];
		lookupTable[0] = 0;
		for (int i = 1; i <= n; ++i) {
			lookupTable[i] = -1;
		}
		return testCuttingRodDynamicProgramming(prices, n, lookupTable);
	}
	
	@Test
	public void cuttingRod() {
		// optimal substructure = max(currentPrice + takingRod, currentPrice + notTakingRod)
		//                      = max(currentPrice+prices[nextRod], currentPrice)
		int[] arr = {1, 5, 8, 9, 10, 17, 17, 20};
		lookup = new int[arr.length+1];
		System.out.println("cuttingRod : "       + cuttingRod(arr, arr.length) + " " + calcCount);
		calcCount = 0;
		System.out.println("cuttingRodDP : "     + cuttingRodDynamicProgramming(arr, arr.length) + " " + calcCount);
		calcCount = 0;
		System.out.println("cuttingRod : "   + testCuttingRod(arr, arr.length) + " " + calcCount);
		calcCount = 0;
		System.out.println("cuttingRodDP : " + testCuttingRodDynamicProgramming(arr, arr.length) + " " + calcCount + " " + readingCount);
		
	}
	
	@Test
	public void fibonacciDelvedInto() {
		// OK. not really dynamic programming but in general just fibonacci
		// article says that it can be done in O(logn) using the multiplication matrix how nice!
		// need to memorize why it's possible though
	}
	
	private int testLongestIncreasingSubsequence(int[] arr, int n) {
		if (n==1) {
			return 1;
		}
		
		int maxSoFar = 1;
		
		for (int i = 1; i < n; ++i) {
			int result = testLongestIncreasingSubsequence(arr, i);
			if (arr[n-1] > arr[i-1]) {
				++result;
			}
			maxSoFar = Math.max(maxSoFar, result);
		}
		
		return maxSoFar;
	}
		
	@Test
	public void testLongestIncreasingSubsequence() {
		int result = testLongestIncreasingSubsequence(new int[]{10,22,9,33,21,50,41,60}, 8);
		System.out.println("result of lis " + result);
	}
	
	private int testLongestCommonSubsequence(String s1, String s2, int x, int y) {
		if (x == 0 || y == 0) {
			return 0;
		}
		
		if (s1.charAt(x-1) == s2.charAt(y-1)) {
			return testLongestCommonSubsequence(s1, s2, x-1, y-1)+1;
		}
		
		return Math.max(testLongestCommonSubsequence(s1, s2, x-1, y), testLongestCommonSubsequence(s1, s2, x, y-1));
	}
	
	private int testLongestCommonSubsequenceDP(String s1, String s2, int x, int y) {
		
		int[][] lcs = new int[s1.length()+1][s2.length()+1];
		
		for (int i = 0; i <= x; ++i) {
			lcs[i] = new int[y+1];
		}
		
		for (int i = 0; i <= x; ++i) {
			for (int j = 0; j <= y; ++j) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
					continue;
				}
				
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				}
				else {
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		return lcs[x][y];
	}	

	@Test
	public void testLongestCommonSubsequence() {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		int result = testLongestCommonSubsequence(s1, s2, s1.length(), s2.length());
		//System.out.println("result of lcs " + result);
		int result2 = testLongestCommonSubsequenceDP(s1, s2, s1.length(), s2.length());
		//System.out.println("result of lcs " + result2);		
	}
	
	@Test
	public void testKadane() {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		int result = testLongestCommonSubsequence(s1, s2, s1.length(), s2.length());
		//System.out.println("result of lcs " + result);
		int result2 = testLongestCommonSubsequenceDP(s1, s2, s1.length(), s2.length());
		//System.out.println("result of lcs " + result2);		
	}
	
	// O(n^3)
	private static int findPossibleNumberOfTriangles(Integer[] a) {
		// brute force first
		int result = 0;
		final int n = a.length;
		for (int i = 0; i < n-2; ++i) {
			for (int j = i+1; j < n-1; ++j) {
				for (int k = j+1; k < n; ++k) {
					// if satisfies the triangle property,
					if (a[i]+a[j] > a[k] && a[j]+a[k] > a[i] && a[i]+a[k] > a[j]) {
						++result;
					}
				}
			}
		}
		return result;
	}
	
	/// O(n^2)
	private static int findPossibleNumberOfTrianglesEfficient(Integer[] arr) {
		int result = 0;
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));
		Collections.sort(list);
		int[] a = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			a[i] = list.get(i);
		}
		
		final int n = a.length;
		for (int i = 0; i < n-2; ++i) {
			int k = i+2;
			for (int j = i+1; j < n; ++j) {
				while (k<n && a[i] + a[j] > a[k]) {
					++k;
				}
				result += k-j-1;
			}
		}
		return result;
	}
	
	private static int findPossibleNumberOfTrianglesEfficient2(Integer[] arr) {
		// brute force first
		int result = 0;
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));
		Collections.sort(list);
		int[] a = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			a[i] = list.get(i);
		}
		
		final int n = a.length;
		for (int i = 0; i < n-2; ++i) {
			int k = i+2;
			for (int j = i+1; j < n; ++j) {
				while (k<n && (a[i]+a[j] > a[k])) {
					++k;
				}
				
				result += k-j-1;
			}
		}
		
		return result;
	}	
	
	@Test
	public void findPossibleNumberOfTriangles() {
		Integer[] arr = {4, 6, 3, 7};
		
		int result = findPossibleNumberOfTriangles(arr);
		System.out.println("possible number of triangles :  " + result);
		result = findPossibleNumberOfTrianglesEfficient(arr);
		System.out.println("possible number of triangles :  " + result);
		result = findPossibleNumberOfTrianglesEfficient2(arr);
		System.out.println("possible number of triangles :  " + result);		
	}
}
