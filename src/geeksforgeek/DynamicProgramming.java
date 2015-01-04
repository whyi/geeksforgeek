package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
			int minimumSoFar = Integer.max(
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
			maxSoFar = Integer.max(cuttingRod(prices, n-i-1) + prices[i], maxSoFar);
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
				maxSoFar = Integer.max(lookup[i-j-1] + prices[j], maxSoFar);
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
			maxSoFar = Integer.max(maxSoFar, testCuttingRod(prices, n-i-1)+prices[i]);
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
			maxSoFar = Integer.max(maxSoFar, lookupTable[n-i-1]+prices[i]);
			
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
	
	@TEst
	public void fibonacciDelvedInto() {
		// OK. not really dynamic programming but in general just fibonacci
		// article says that it can be done in O(logn) using the multiplication matrix how nice!
		// need to memorize why it's possible though
	}
}
