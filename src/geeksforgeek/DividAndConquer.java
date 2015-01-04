package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;

public class DividAndConquer {

	// O(n)
	private static int myPow(int n, int power) {
		if (power == 0) {
			return 1;
		}
		
		if (power%2 == 0) {
			return myPow(n,power/2)*myPow(n,power/2);
		}
		else {
			return n*myPow(n,power/2)*myPow(n,power/2);
		}
	}

	// Olog(n)
	private static int myPowOptimized(int n, int power) {
		if (power == 0) {
			return 1;
		}
		
		final int intermediateResult = myPow(n,power/2);
		if (power%2 == 0) {
			return intermediateResult*intermediateResult;
		}
		else {
			return n*intermediateResult*intermediateResult;
		}
	}
	
	@Test
	public void myPow() {
		assertEquals(8,myPow(2,3));
		assertEquals(8,myPowOptimized(2,3));
	}

}
