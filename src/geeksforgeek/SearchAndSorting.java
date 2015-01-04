package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchAndSorting {

	// assuming arr is sorted
	private static int binarySearch(int[] arr, int left, int right, int n) {
		final int mid = (left+right)/2;
		if (arr[mid] == n) {
			return n;
		}
		if (arr[mid] < n) {
			return binarySearch(arr, mid+1, right, n);
		}
		if (arr[mid] > n) {
			return binarySearch(arr, left, mid-1, n);
		}
		return -1;
	}
	
	private static int binarySearchIterative(int[] arr, int n) {
		int left = 0;
		int right = arr.length;
		
		while (left<=right) {
			int mid = (left + right)/2;

			if (arr[mid] == n) {
				return n;
			}
			
			if (arr[mid] < n) {
				left = mid+1;
			}
			
			if (arr[mid] > n) {
				right = mid-1;
			}
		}
		return -1;
	}
	
	@Test
	public void test() {
		int[] inputArray = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		assertEquals(5, binarySearch(inputArray, 0, inputArray.length, 5));
		assertEquals(5, binarySearchIterative(inputArray, 5));
	}

}
