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

	private static int[] bubbleSort(int[] arr) {
		while (true) {
			boolean swapped = false;
			for (int i = 1; i < arr.length; ++i) {
				if (arr[i-1] > arr[i]) {
					final int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
					swapped = true;
				}
			}
			
			if (swapped == false) {
				break;
			}
		}
		return arr;
	}
	
	@Test
	public void bubbleSort() {
		int[] arr = {5,3,2,4,3,2,4,12,3,4,2,5,12,3,12,4,124,2,4,12,31,23,12,31,23};
		bubbleSort(arr);
	}
	
	@Test
	// O(n*k)
	public void findKthLargestOrSmallest() {
		int[] arr = {5,4,3,2,1,0};
		
		// run bubble sort up to k time
		final int k = 3;
		int cnt = 0;
		while (cnt <= k) {
			for (int i = 1; i < arr.length; ++i) {
				if (arr[i-1] > arr[i]) {
					final int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
				}
			}
			++cnt;
		}

		System.out.println("test");
		for (int i: arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("findKthLargestOrSmallestUsingTempArray : " + findKthLargestOrSmallestUsingTempArray(new int[]{5,4,3,2,1,0}, k));
		System.out.println("findKthLargestOrSmallestUsingSorting : " + findKthLargestOrSmallestUsingSorting(new int[]{5,4,3,2,1,0}, k));
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		arr1.add(5);arr1.add(4);arr1.add(3);arr1.add(2);arr1.add(1);arr1.add(0);
		System.out.println("findViaQuickSelect : " + findViaQuickSelect(arr1, k, 0, arr1.size()-1));
	}
	
	// not sure if I'm understanding this approach
	public int findKthLargestOrSmallestUsingTempArray(int[] arr, int k) {
		
		return 0;
	}
	
	public int findKthLargestOrSmallestUsingSorting(int[] arr, int k) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i:arr) {
			al.add(i);
		}
		Collections.sort(al);
		return al.get(k-1);
	}
	
	public int findViaQuickSelect(ArrayList<Integer> arr, int k, int left, int right) {
		// if k is smaller than the number of elements in the array
		System.out.println("quickselect @ " + left + " " + right);
		if (k > 0 && right-left > k) {
			int pivot = partition(arr, left, right);
			System.out.println("pivot returned and value is " + pivot + " " + arr.get(pivot) + " @ " + left + " " + right);
			if (pivot-left == k) {
				return arr.get(pivot-1);
			}
			
			if (pivot-left > k) {
				return findViaQuickSelect(arr, k, left, pivot-1);
			}
			else {
				return findViaQuickSelect(arr, k-pivot+left-1, pivot+1, right);
			}
		}
		return Integer.MAX_VALUE;
	}
	
	// 1. divide into arrays size of 5 except the last one maybe. FIVE!
	// 2. sort above groups and create auxiliary array median[] and store medians of all of those GIVE arrays
	// 3. // recursively call to find medians of medians? WTF?
	// 4. medianOfMedians = KthSmallest(median[0...n/5-1], n/10) not sure if I understand this
	// 5. partition .. WTF
	// So what's the conclusion here? IMO it can be the followings:
	// A. interviewer want to see if I can provide "the best one" right away. i.e) have you heard of it or not and can you implement it
	//    In other words, unless I'm dealing with kth shit everyday it's sorta impossible IMO
	// B. interviewer want to see how I'm progress and I have at least grasp of the existance of the algo
	// C. Whatever, he just want to reject candidates by this.... or company policy.
}
