package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class BinarySearchTree {

	private static BSTNode arrayToBSTNaiveHelper(int[] array, int left, int right) {
		if (left < right) {
			final int midIndex = (left+right)/2;
			final int midData = array[midIndex];
			BSTNode root = new BSTNode(midData);
			root.left = arrayToBSTNaiveHelper(array, left, midIndex-1);
			root.right = arrayToBSTNaiveHelper(array, midIndex+1, right);
			return root;
		}

		if (left == right && left < array.length) {
			BSTNode root = new BSTNode(array[left]);
			return root;
		}
		return null;
	}
	
	private static BSTNode arrayToBSTNaive(int[] array) {
		return arrayToBSTNaiveHelper(array, 0, array.length);
	}
                         
	private static int currentHead = 0;
	private static BSTNode arrayToBSTOptimizedHelper(int[] array, int n) {
		if (n <= 0) {
			return null;
		}
		
		BSTNode leftNode = arrayToBSTOptimizedHelper(array, n/2);
		BSTNode rootNode = new BSTNode(array[currentHead]);
		rootNode.left = leftNode;
		++currentHead;

		/* Recursively construct the right subtree and link it with root 
	      The number of nodes in right subtree  is total nodes - nodes in 
	      left subtree - 1 (for root) which is n-n/2-1*/
		rootNode.right = arrayToBSTOptimizedHelper(array, n-n/2-1);
		return rootNode;
	}
	
	private static BSTNode arrayToBSTOptimized(int[] array) {
		return arrayToBSTOptimizedHelper(array, array.length);
	}
	
	@Test
	public void arrayToBST() {
		/*
		 *          4
		 *        /   \
		 *       2     6
		 *      / \   / \
		 *     1   3 5   7
		 */

		// should build in O(nlogn)
		Helper.printInLevelOrder(arrayToBSTNaive(new int[] {1,2,3,4,5,6,7}));
		
		// should build in O(n)....... really?
		Helper.printInLevelOrder(arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7}));
	}

	// naive iterative algorithm o(n^2)
	private int heightOfArrayBST(int[] array) {
		int maximumHeight = 1;

		for (int i = 0; i < array.length; ++i) {
			int parent = array[i];
			int count = 0;
			while (parent != -1) {
				parent = array[parent];
				++count;
			}
			
			if (count > maximumHeight) {
				maximumHeight = count;
			}
		}
		
		return maximumHeight+1;
	}
		
	private void fillDepth(int[] parent, int i, int[] depth) {
		if (depth[i] != 0) {
			return;
		}
		
		if (parent[i] == -1) {
			depth[i] = 1;
			return;
		}
		
		if (depth[parent[i]] == 0) {
			fillDepth(parent, parent[i], depth);
		}
		
		depth[i] = depth[parent[i]] + 1;
	}
	
	// figure out O(n)
	private int heightOfArrayBSTOptimized(int[] parent) {
		
		int[] depth = new int[parent.length];
		for (int i = 0; i < parent.length; ++i) {
			depth[i] = 0;
		}
		
		for (int i = 0; i < parent.length; ++i) {
			fillDepth(parent, i, depth);
		}
		
		// return the max of it.
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i : depth) {
			list.add(i);
		}
		return Collections.max(list);
	}
	
	@Test
	public void heightOfArrayBST() {
		// value represents the parent node in the BST
		assertEquals(4, heightOfArrayBST(new int[] {1,5,5,2,2,-1,3}));
		assertEquals(5, heightOfArrayBST(new int[] {-1,0,0,1,1,3,5}));
		
		assertEquals(4, heightOfArrayBSTOptimized(new int[] {1,5,5,2,2,-1,3}));
		assertEquals(5, heightOfArrayBSTOptimized(new int[] {-1,0,0,1,1,3,5}));		
	}	
}
