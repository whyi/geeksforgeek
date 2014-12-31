package geeksforgeek;

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
}
