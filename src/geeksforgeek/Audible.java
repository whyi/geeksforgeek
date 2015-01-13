package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class Audible {

	@Test
	public void greedyTest() {
		int startTime[] = { 1, 3, 0, 5, 8, 5 };
		int finishTime[] = { 2, 4, 6, 7, 9, 9 };

		// sort it
		int i = 0;
		System.out.print(i + " ");
		for (int j = 1; j < startTime.length; ++j) {
			if (startTime[j] >= finishTime[i]) {
				System.out.print(j + " ");
				i = j;
			}
		}
		System.out.println();
	}

	private static int partition(ArrayList<Integer> arr, int left, int right) {
		final int pivot = arr.size() / 2;
		final int pivotValue = arr.get(pivot);

		// move to the end
		Collections.swap(arr, pivot, right);

		int result = left;
		for (int i = left; i < right; ++i) {
			final int currentValue = arr.get(i);
			if (currentValue > pivotValue) {
				Collections.swap(arr, result, i);
				++result;
			}
		}

		Collections.swap(arr, result, pivot);
		return result;
	}

	private static int quickSelect(int[] arr, int left, int right, int value) {
		//final int k = partition(arr, left, right);
		
//		if (k == value) {
//			return k;
//		}
//		
//		if (k > value) {
//			quickSelect(arr, left, k - 1, value);
//		}
//		
//		if (k < value) {
//			quickSelect(arr, k + 1, right, value);
//		}
		return 0;
	}

	@Test
	public void quickSelect() {
		//quickSelect(arr, 0, arr.length-1, value);
	}
	
	public static class Node {
		public Node left;
		public Node right;
		public int data;
		public Node(int data) {
			this.data = data;
		}
	}
	
	// aha! only works in Binary Search Tree.
	// doesn't work for Binary tree.....
	private static Node computeLCA(Node root, int n1, int n2) {
		while (root != null) {
			if (root.data < n1 && root.data < n2) {
				root = root.right;
			}
			else if (root.data > n1 && root.data > n2) {
				root = root.left;
			}
			else {
				break;
			}
		}
		return root;
	}
	
	private static Node computeLCAForBinaryTree(Node root, int n1, int n2) {
		if (root == null) {
			return null;
		}
		
		if (root.data == n1 || root.data == n2) {
			return root;
		}
		
		Node left = computeLCAForBinaryTree(root.left, n1, n2);
		Node right = computeLCAForBinaryTree(root.right, n1, n2);
		
		if (left != null && right != null) {
			return root;
		}

		if (left != null && right == null) {
			return left;
		}
		
		if (left == null && right != null) {
			return right;
		}
		
		return null;
	}
	
	// cannot do BST!!!!!! have to do in this way!!
	private static int findLevel(Node node, int n, int level) {
		if (node == null) {
			return -1;
		}
		
		if (node.data == n) {
			return level;
		}
		
		int result = findLevel(node.left, n, level+1);
		if (result != -1) {
			return result;
		}
		return findLevel(node.right, n, level+1);
	}
	
	private static int distanceBetweenTwoNodes(Node root, int n1, int n2) {
		final Node result = computeLCAForBinaryTree(root, 4, 5);
		//System.out.println("to n1 " + dist(result, n1) + " " + result.data + " " + n1);
		//System.out.println("to n2 " + dist(result, n2));
		return findLevel(result, n1, 0) + findLevel(result, n2, 0);
	}
	
	/*            1
	 *           2 3
	 *         4 5 6 7
	 */         
	@Test
	public void distanceBewteenTwoNodes() {
		// my approach
		// compute LCA
		// dist(LCA, n1) + dist(LCA, n2) = total distance we want.
	    Node root = new Node(1);
	    root.left = new Node(2);
	    root.right = new Node(3);
	    root.left.left = new Node(4);
	    root.left.right = new Node(5);
	    root.right.left = new Node(6);
	    root.right.right = new Node(7);
	    root.right.left.right = new Node(8);		
		//System.out.println("result : " + computeLCA(root, 4, 5).data);
		System.out.println("result : " + distanceBetweenTwoNodes(root, 4, 5));
	    
		
	}
}
