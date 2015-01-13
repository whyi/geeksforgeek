package geeksforgeek;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class AmazonSet1 {

	// http://www.geeksforgeeks.org/amazon-interview/
	/*
	 * I. To find if there is any root to leaf path with specified sum in a binary tree.
	 * II. Some question based on sorting. <- what question?!
	 */
	
	// dfs? bfs?
	// need to search for all anyway?
	// I think it's dfs then?
	
	public static class Node {
		public Node left;
		public Node right;
		public int data;
		public Node(int data) {
			this.data = data;
		}
	}
	
	private static Node sortedArrayToBST(int[] a, int left, int right) {
		if (left >= right) {
			return null;
		}
		final int mid = (left+right)/2;
		Node root = new Node(a[mid]);
		root.left = sortedArrayToBST(a, left, mid-1);
		root.right = sortedArrayToBST(a, mid+1, right);
		return root;
	}
	
	private static boolean findSum(Node node, int val) {
		if (val == 0) {
			return true;
		}
		
		if (node == null) {
			return false;
		}
		
		final int newValue = val-node.data;
		return findSum(node.left, newValue) || findSum(node.right, newValue);
	}
	
	@Test
	public void test() {
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		Node root = sortedArrayToBST(a, 0, a.length);
		for (int i = 6; i < 25; ++i) {
			System.out.println(i + " is :" + findSum(root, i));
		}
	}
	
	private static boolean isPalindrome(char[] chars) {
		Stack<Character> stack = new Stack<Character>();
		
		int size = 0;
		for (char c:chars) {
			stack.push(c);
			++size;
		}
		
		size = size/2;
		int i = 0;
		while (i != size) {
			if (chars[i] == stack.pop()) {
				++i;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void isPalindrome() {
		// Check if a character link list is palindrome or not.
		// suppose the following is linked list
		char[] truePalindrome = "abcdcba".toCharArray();
		char[] falsePalindrome = "abc213ba".toCharArray();
		
		assertEquals(true, isPalindrome(truePalindrome));
		assertEquals(false, isPalindrome(falsePalindrome));
	}

	public static void printInt(int n)	{
		if (n < 0) {
			return;
		}
		
		System.out.print(n);
	}

	// Assuming the array is sorted.
	// Therefore the pivot index is the index where the next index is smaller than it.
	private static int findPivotFrom(int[] array, int left, int right) {
		final int mid = (left+right)/2;
		if (array[mid] > array[mid+1]) {
			return mid;
		}
		
		if (array[mid] < array[left]) {
			return findPivotFrom(array, left, mid-1);
		}
		else {
			return findPivotFrom(array, mid+1, right);
		}
	}
	
	private static int binarySearch(int[] array, int left, int right, int value) {
		final int mid = (left+right)/2;

		if (array[mid] == value) {
			return mid;
		}

		if (array[mid] < value) {
			return binarySearch(array, mid+1, right, value);
		}
		else {
			return binarySearch(array, 0, mid-1, value);
		}
	}
	
	private static int findNumber(int[] array, int pivot, int val) {
		if (val == array[pivot]) {
			return pivot;
		}
		
		if (val < array[pivot]) {
			// search in the right array
			return binarySearch(array, pivot+1, array.length-1, val);
		}
		// search in the left array
		return binarySearch(array, 0, pivot-1, val);
	}
	
	@Test
	public void findInRotatedArray() {
		//  A sorted array has been rotated r times to the left. Find r in least possible time.
		// naive O(n)
		// can be done in logn imo
		
		int[] arr = {4,5,1,2,3};
		System.out.println("findPivotFrom : " + findPivotFrom(arr, 0, arr.length-1));
		
		final int pivot = findPivotFrom(arr, 0, arr.length-1);
		// given pivot split into 2 havles;
		// then search for each.
		
		System.out.println("findNumber : " + findNumber(arr, pivot, 2));
	}
}
