package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

public class AmazonSet0 {

	// http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
	
	/*
	 * Count total set bits in all numbers from 1 to n
Given a positive integer n, count the total number of set bits in binary representation of all numbers from 1 to n.

Examples:

Input: n = 3
Output:  4

Input: n = 6
Output: 9

Input: n = 7
Output: 12

Input: n = 8
Output: 13
	 */
	private static int getNumberOfBits(int n) {
		int result = 0;
		while (n > 1) {
			if ((n&0x01) == 1) {
				++result;
			}
			n = n >> 1;
		}
		return result;
	}
	

	private static int getNumberOfBitsOptimized(int n) {
		if (n <= 0) {
			return 0;
		}

		int result = 0;

		if (n%2 != 0) {
			result = 1;
		}

		return result+getNumberOfBitsOptimized(n/2); 
	}
	
	@Test
	public void test() {
		int n = 4;
		int result = 0;
		for(int i = 0; i < n; ++i) {
			result += getNumberOfBits(i);
		}
		System.out.println("n : " + result); // FIXME : didn't work.
		
		result = 0;
		for(int i = 0; i < n; ++i) {
			result += getNumberOfBitsOptimized(i);
		}
		System.out.println("n : " + result);
	}

}
