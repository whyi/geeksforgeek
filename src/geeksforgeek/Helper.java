package geeksforgeek;

import java.util.*;

public final class Helper {
	public static Node constructListFrom(int[] values) {
		Node dummy = new Node(0);
		Node prev = dummy;
		
		for (int value : values) {
			Node node = new Node(value);
			prev.next = node;
			prev = node;
		}
		return dummy.next;
	}
	
	public static void printList(Node root) {
		Node node = root;
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	public static int[] toArray(Node root) {
		Node node = root;
		int size = 0;
		while (node != null) {
			node = node.next;
			++size;
		}
		
		int[] result = new int[size];
		node = root;

		int i = 0;
		while (node != null) {
			result[i] = node.data;
			node = node.next;
			++i;
		}
		return result;
	}
	
	private static void getPrefixNodes(BSTNode root, ArrayList<BSTNode> nodes) {
		// preorder while keep pushing it to a container
		nodes.add(root);

		if (root.left != null) {
			getPrefixNodes(root.left, nodes);
		}
		
		if (root.right != null) {
			getPrefixNodes(root.right, nodes);
		}
	}
	
	// FIXME : more prettify is needed.. too ugly now
	public static void printInLevelOrder(BSTNode root) {
		Queue<BSTNode> q = new java.util.LinkedList<BSTNode>();

		int shouldPrintNewLine = 1;
		int i = 1;
		q.add(root);
		
		ArrayList<BSTNode> nodes = new ArrayList<BSTNode>();
		getPrefixNodes(root, nodes);
		int margins = nodes.size()/2;
				
		while (!q.isEmpty()) {
			BSTNode node = q.remove();
			
			// don't print margin for the leaves
			if (margins >= 1) {
				for (int j = 0; j < margins+margins/2; ++j) {
					System.out.print(" ");
				}
			}
			System.out.print(node.data + " ");
			if (node.left != null) {
				q.add(node.left);
			}
			
			if (node.right != null) {
				q.add(node.right);
			}

			if (i == shouldPrintNewLine) {
				System.out.println();
				if (margins >= 1) {
					for (int j = 0; j < margins; ++j) {
						System.out.print(" ");
					}
					
					for (int j = 0; j < shouldPrintNewLine; ++j) {
						System.out.print("/");
						for (int k = 0; k < margins/2+1; ++k) {
							System.out.print(" ");
						}
						System.out.print("\\");
						for (int k = 0; k < margins/2+1; ++k) {
							System.out.print(" ");
						}						
					}
					System.out.println();
				}
				
				i = 1;
				shouldPrintNewLine *= 2;
				margins /= 2;
			}
			else {
				++i;
			}
		}
	}
	
	
	public static void levelOrder(BSTNode root) { 
		Queue<BSTNode> q = (Queue<BSTNode>) new LinkedList(); 

		q.add(root); 
		int curlevel = 1; 
		int nextlevel = 0; 

		while(!q.isEmpty()) { 

			BSTNode v = q.poll(); 
	
			System.out.print(v.data + " "); 
	
			if(v.left != null) { 
				q.add(v.left); 
				++nextlevel; 
			} 

			if(v.right != null) { 
				q.add(v.right); 
				++nextlevel; 
			} 

			--curlevel;

			if(curlevel == 0) { 
				curlevel = nextlevel; 
				nextlevel = 0; 
				System.out.println(); 
			}
		} // while 
	} 
	
	private static void toArray(BSTNode root, ArrayList<Integer> list) {
		if (root == null) {
			return;
		}
		toArray(root.left, list);
		list.add(root.data);
		toArray(root.right, list);
	}
	
	public static int[] toArray(BSTNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		toArray(root, list);

		int[] result = new int[list.size()];
		int count = 0;
		for (Integer i : list) {
			result[count] = i.intValue();
			++count;
		}
		return result;
	}

}
