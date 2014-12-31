package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

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
		currentHead = 0;
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
		//Helper.printInLevelOrder(arrayToBSTNaive(new int[] {1,2,3,4,5,6,7}));
		
		// should build in O(n)....... really?
		//Helper.printInLevelOrder(arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7}));

		// FIXME : where's the assertEqual for this?
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
	
	// O(n)
	private int heightOfBST(BSTNode root) {
		if (root == null) {
			return 0;
		}
		
		final int leftHeight = heightOfBST(root.left);
		final int rightHeight = heightOfBST(root.right);
		
		return Math.max(leftHeight, rightHeight)+1;
	}
	
	@Test
	public void heightOfBST() {
		assertEquals(0, heightOfBST(arrayToBSTOptimized(new int[] {})));
		assertEquals(1, heightOfBST(arrayToBSTOptimized(new int[] {1})));
		assertEquals(2, heightOfBST(arrayToBSTOptimized(new int[] {1,2,3})));
		assertEquals(3, heightOfBST(arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7})));
		assertEquals(4, heightOfBST(arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7,8})));
		assertEquals(5, heightOfBST(arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17})));
	}

	// runtime O(n)
	// space O(1), if we count the stack O(n)
	private void deleteTree(BSTNode node) {
		if (node == null) {
			return;
		}
		deleteTree(node.left);
		deleteTree(node.right);
		node.left = null;
		node.right = null;
	}
	
	@Test
	public void deleteBinaryTree() {
		BSTNode root = arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17});
		deleteTree(root);
		
		// the tree is actually deleted
		// it's just the Java pass-by-value causing the root node dangling, so hard-coded to null for now.
		root = null;
		assertEquals(null, root);
	}

	private BSTNode flipTreeHelper(BSTNode node, int count) {
		if (node == null) {
			return null;
		}
		flipTreeHelper(node.left, count+1);
		flipTreeHelper(node.right, count+1);
		BSTNode temp = node.left;
		node.left = node.right;
		node.right = temp;

		if (count == 0) {
			return node;
		}
		else {
			return null;
		}
	}
	
	// O(n)
	private BSTNode flipTree(BSTNode node) {
		return flipTreeHelper(node, 0);
	}
	
	@Test
	public void mirrorBinaryTree() {
		/*             4                 4
		 *           /   \             /   \ 
		 *          2     6     ->    6     2
		 *         / \   / \         / \   / \
		 *        1   3 5   7       7   5 3   1
		 *        
		 *        and I'd like O(n)
		 */
		int[] originalArray = new int[] {1,2,3,4,5,6,7};
		BSTNode root = arrayToBSTOptimized(originalArray);
		flipTree(root);
		assertArrayEquals("result should equal", new int[]{7,6,5,4,3,2,1}, Helper.toArray(root));
		
		// if we flip again, do we get the original one back?
		flipTree(root);
		assertArrayEquals("result should equal", originalArray, Helper.toArray(root));
	}
	
	
	private static void print(ArrayList<BSTNode> list) {
		for (BSTNode node: list) {
			System.out.print(node.data + " ");
		}
		
		System.out.println();
	}
	
	private static void printPath(BSTNode root, ArrayList<BSTNode> nodesSoFar) {
		// suggested pre-order. can we do this iteratively?
		nodesSoFar.add(root);
		
		if (root.left != null) {
			printPath(root.left, nodesSoFar);
		}
		
		if (root.right != null) {
			printPath(root.right, nodesSoFar);
		}
		
		if (root.left == null && root.right == null) {
			// print the queue
			print(nodesSoFar);
		}
		nodesSoFar.remove(nodesSoFar.size()-1);
	}

	/*
			  4 
		    /   \
		   2     6 
		  / \   / \
		 1   3 5   7
	*/
	@Test
	public void printPathFromRootToLeaves() {
		BSTNode root = arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7});
		Helper.printInLevelOrder(root);
		ArrayList<BSTNode> list = new ArrayList<BSTNode>();
		printPath(root, list);
	}
	
	private BSTNode locateLowestCommonAncestor(BSTNode node, int n1, int n2) {
		if (node == null) {
			return null;
		}
		
		if (node.data < n1 && node.data < n2) {
			return locateLowestCommonAncestor(node.right, n1, n2);
		}
		
		if (node.data > n1 && node.data > n2) {
			return locateLowestCommonAncestor(node.left, n1, n2);
		}
		
		//if (node.data > n1 && node.data < n2) {
			return node;
		//}
		//return null;
	}
	
	// TODO : 1. may wanna try the iterative solution
	//        2. alternative version : findLowestCommonAncestor
	//           (instead of locating, which assumes the existence of the node in the tree)
	
	@Test
	// following up question : what if we call this billion of times
	public void locateLowestCommonAncestor() {
		// LCA..... for given two nodes (or values, whatever) from the tree

		// if we can have a pointer back to the parent node...
		// very easy thing in O(logn) just traverse upwards on by one until
		// they actually intersect.
		/*              6
		 *           /    \
		 *          3      9
		 *         / \    / \
		 *        2   5  8  10
		 *       /   /  /
		 *      1   4  7
		 */
		BSTNode root = arrayToBSTOptimized(new int[] {1,2,3,4,5,6,7,8,9,10});
		Helper.printInLevelOrder(root);
		assertEquals(3, locateLowestCommonAncestor(root, 1, 5).data); // expects 3
		assertEquals(3, locateLowestCommonAncestor(root, 1, 4).data); // expects 2
		assertEquals(3, locateLowestCommonAncestor(root, 1, 3).data); // expects 6
		assertEquals(8, locateLowestCommonAncestor(root, 7, 8).data); // expects 8
		assertEquals(9, locateLowestCommonAncestor(root, 7, 10).data); // expects 9
	}
}