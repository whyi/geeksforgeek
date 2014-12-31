package geeksforgeek;

public class BSTNode {
	public BSTNode left;
	public BSTNode right;
	public int data;
	public BSTNode() {
		this.left = null;
		this.right = null;
	}
	
	public BSTNode(int data) {
		this.data = data;
	}
}
