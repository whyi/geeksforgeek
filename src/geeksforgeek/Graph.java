package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

class Vertex {
	public ArrayList<Vertex> neighbors = null;
	public Integer data;

	public Vertex() {
		neighbors = new ArrayList<Vertex>();
		data = null;
	}
	
	public Vertex(Integer data) {
		this.data = data;
		neighbors = new ArrayList<Vertex>();
	}	

	public void addNeighbor(Vertex vertex) {
		neighbors.add(vertex);
	}
}

public class Graph {
	public ArrayList<Vertex> vertices = null;

	public Graph() {
		vertices = new ArrayList<Vertex>();
	}

	private Vertex getvertexAt(int index) {
		try {
			return vertices.get(index);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("addEdge : no such vertex " + index);
			return null;
		}		
	}
	
	public boolean addEdge(int from, int to) {
		final Vertex fromvertex = getvertexAt(from);
		final Vertex tovertex = getvertexAt(to);
		
		if (fromvertex != null && tovertex != null) {
			fromvertex.addNeighbor(tovertex);
			tovertex.addNeighbor(fromvertex);
			return true;
		}
		
		return false;
	}
	
	public void addvertex(Vertex vertex) {
		vertices.add(vertex);
	}
	
	//http://www.geeksforgeeks.org/graph-and-its-representations/
	private void constructGraph() {
		// 5 vertices, 0 1 2 3 4
		for (int i = 0; i < 5; ++i) {
			addvertex(new Vertex(i));
		}
		
		addEdge(0,1);
		addEdge(0,4);
		addEdge(1,4);
		addEdge(1,3);
		addEdge(4,3);
		addEdge(1,2);
		addEdge(3,2);
	}
	
	public void printGraph() {
		System.out.println("-------------------Dumping Graph----------------");
		for (Vertex vertex: vertices) {
			System.out.print("vertex (" + vertex.data + ") : ");
			for (Vertex neighbor: vertex.neighbors) {
				System.out.print(neighbor.data + " ");	
			}
			System.out.println();
		}
		System.out.println("-----------------------Done---------------------");
	}
	
	public ArrayList<Integer> breadthFirstTraversal(int startIndex) {
		if (startIndex > vertices.size()-1) {
			return null;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		Queue<Vertex> queue = new LinkedList<Vertex>();
		
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		
		queue.add(vertices.get(startIndex));

		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove();
			if (!visited.containsKey(vertex)) {
				visited.put(vertex, true);
				result.add(vertex.data);
				for (Vertex v : vertex.neighbors) {
					queue.add(v);
				}
			}
		}
		
		return result;
	}
	
	public void printBreadthFirstTraversal(int startIndex) {
		final ArrayList<Integer> result = breadthFirstTraversal(startIndex);
		System.out.println("-----------------------BFS with " + startIndex + "---------------");
		if (result == null) {
			System.out.print("        nothing to print");
		}
		else {
			for (Integer data: result) {
				System.out.print(data + " ");
			}
		}
		System.out.println("\n-----------------------Done---------------------");
	}
	
	public ArrayList<Integer> depthFirstTraversal(int startIndex) {
		if (startIndex > vertices.size()-1) {
			return null;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		Stack<Vertex> stack = new Stack<Vertex>();
		
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		
		stack.push(vertices.get(startIndex));

		while (!stack.isEmpty()) {
			Vertex vertex = stack.pop();
			if (!visited.containsKey(vertex)) {
				visited.put(vertex, true);
				result.add(vertex.data);
				for (Vertex v : vertex.neighbors) {
					stack.push(v);
				}
			}
		}
		
		return result;
	}

	public void printDepthFirstTraversal(int startIndex) {
		final ArrayList<Integer> result = depthFirstTraversal(startIndex);
		System.out.println("-----------------------DFS with " + startIndex + "---------------");
		if (result == null) {
			System.out.print("        nothing to print");
		}
		else {
			for (Integer data: result) {
				System.out.print(data + " ");
			}
		}
		System.out.println("\n-----------------------Done---------------------");
	}
	
	@Test
	public void test() {
		constructGraph();
		printGraph();
		
		// O(V+E)
		for (int i = 0; i < 10; ++i) {
			printBreadthFirstTraversal(i);
			printDepthFirstTraversal(i);
		}
	}

}
