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
}
