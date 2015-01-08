package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void SegregateEvenOddNodesNaive() {
		Node root = Helper.constructListFrom(new int[] {1,2,3,4,5,6,7,8,9,10});
		
		Node node = root;
		
		Node evenNode = new Node(0);
		Node oddNode = new Node(0);

		Node evenRoot = evenNode;
		Node oddRoot = oddNode;
		while (node != null) {
			
			if (node.data %2 == 0) {
				 evenNode.next = new Node(node.data);
				 evenNode = evenNode.next;
			}
			else {
				oddNode.next = new Node(node.data);
				oddNode = oddNode.next;
			}
			node = node.next;
		}

		evenNode.next = oddRoot.next;
		
		Node newRoot = evenRoot.next;
		assertArrayEquals("result should equal", new int[]{2,4,6,8,10,1,3,5,7,9}, Helper.toArray(newRoot));
	}

	@Test
	public void SegregateEvenOddNodesInPlace() {
		Node root = Helper.constructListFrom(new int[] {1,2,3,4,5,6,7,8,9,10});

		Node node = root;
		Node dummy = new Node(0);
		Node prev = null;

		Node lastEvenNode = null;
		Node newRoot = null;

		while (node != null) {
			if (node.data %2 != 0) {
				Node next = node.next;
				if (prev != null)
					prev.next = next;

				node.next = null;
				dummy.next = node;
				dummy = node;
				node = next;
			}
			else {
				if (newRoot == null) {
					newRoot = node;
				}
				
				if (node != null) {
					lastEvenNode = node;
				}				
				prev = node;
				node = node.next;
			}
		}

		if (lastEvenNode != null) {
			lastEvenNode.next = root;
		}
		
		assertArrayEquals("result should equal", new int[]{2,4,6,8,10,1,3,5,7,9}, Helper.toArray(newRoot));
	}

	private static Node DeleteNodesWhichGreaterThan(Node root, int k) {
		Node node = root;
		Node prev = null;
		Node newRoot = null;

		while (node != null) {
			if (node.data > k) {

				if (prev != null) {
					prev.next = node.next;
				}
				node = node.next;
			}
			else {
				if (newRoot == null) {
					newRoot = node;
				}
				prev = node;
				node = node.next;
			}
		}

		return newRoot;		
	}
	
	@Test
	public void DeleteNodesWhichGreaterThan() {
		assertArrayEquals("result should equal", new int[]{4,4,3,1,2,4,3},
				Helper.toArray(
						DeleteNodesWhichGreaterThan(
								Helper.constructListFrom(new int[]
										{6,54,34,5,35,23,4,23,4,25,3246,5,3,23,1,2,4,3,235,43,64,57,5,74,345,23}),
						4)));
		
		// case when everything got deleted
		assertArrayEquals("result should equal", new int[]{},
				Helper.toArray(
						DeleteNodesWhichGreaterThan(
								Helper.constructListFrom(new int[]
										{1,2,3,4,5,6,7}),
						-10)));
		
		// case when everything nothing get deleted
		assertArrayEquals("result should equal", new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3},
				Helper.toArray(
						DeleteNodesWhichGreaterThan(
								Helper.constructListFrom(new int[]
										{3,3,3,3,3,3,3,3,3,3,3,3,3,3}),
						100)));
	}

	private Node ReverseNodeHelper(Node root) {
		Node newNode = null;
		
		Node prev = null;
		Node current = root;
		Node next = current.next;
		
		while (current != null) {
			next = current.next;
			
			// swap them
			current.next = prev;

			// move pointers			
			prev = current;
			current = next;
		}
		
		return prev;
	}
	
	@Test
	public void ReverseNode() {
		assertArrayEquals("result should equal", new int[]{6,5,4,3,2,1},
				Helper.toArray(
						ReverseNodeHelper(
								Helper.constructListFrom(new int[]
										{1,2,3,4,5,6}))));
	}
	
	private Node ReverseEveryKNode(Node root, int k) {
		if (k <= 1) {
			return root;
		}
		
		Node newRoot = null;
		Node node = root;
		
		while (node != null) {
			Node prev = node;
			node = node.next;
			if (newRoot == null) {
				System.out.println("new root is @" + node.data);
				newRoot = node;
			}			
			for (int i = 1; i < k; ++i) {
				if (node.next == null) {
					System.out.println("end @" + node.data);
					node.next = prev;
					prev.next = null;
					i = 100;
					break;
				}
				else {
					Node nextNode = node.next;
					prev.next = nextNode.next;
					node.next = prev;
					node = nextNode;
				}
			}
		}
		
		if (newRoot == null) {
			System.out.println("null?!@#");
		}
		else {
			System.out.println("e l s e " + newRoot.data);
		}
		
		Helper.printList(newRoot);
		return newRoot;
		 
	}
	
	private void ReverseEveryKNodeHelper(int[] input, int[] expected, int k) {
		assertArrayEquals("result should equal",
				input,
				Helper.toArray(ReverseEveryKNode(Helper.constructListFrom(expected),k)));		
	}
	
	@Test
	public void ReverseEveryKNode() {
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 0);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 1);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 2);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 3);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 4);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 5);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 6);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 7);
	}
	
	@Test
	public void LinkedListToBST() {
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 0);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 1);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 2);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 3);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 4);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 5);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 6);
//		ReverseEveryKNodeHelper(new int[]{1,2,3,4,5,6}, new int[]{1,2,3,4,5,6}, 7);
	}
	
	
}
