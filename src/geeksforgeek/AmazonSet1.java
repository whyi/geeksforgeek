package geeksforgeek;

import static org.junit.Assert.*;

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
	
	@Test
	public void isPalindrome() {
		// Check if a character link list is palindrome or not.
	}

}
