package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedList {

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
	
	
}
