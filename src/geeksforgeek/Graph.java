package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;
import static org.junit.Assert.*;
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

	private Vertex getVertexAt(int index) {
		try {
			return vertices.get(index);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("getVertexAt : no such vertex " + index);
			return null;
		}		
	}
	
	public boolean addEdgeMutually(int from, int to) {
		final Vertex fromVertex = getVertexAt(from);
		final Vertex toVertex = getVertexAt(to);
		
		if (fromVertex != null && toVertex != null) {
			fromVertex.addNeighbor(toVertex);
			toVertex.addNeighbor(fromVertex);
			return true;
		}
		
		return false;
	}
	
	public boolean addEdge(int from, int to) {
		final Vertex fromVertex = getVertexAt(from);
		final Vertex toVertex = getVertexAt(to);
		
		if (fromVertex != null && toVertex != null) {
			fromVertex.addNeighbor(toVertex);
			return true;
		}
		
		return false;
	}	
	
	public void addVertex(Vertex vertex) {
		vertices.add(vertex);
	}
	
	//http://www.geeksforgeeks.org/graph-and-its-representations/
	private void constructGraph() {
		vertices.clear();
		// 5 vertices, 0 1 2 3 4
		for (int i = 0; i < 5; ++i) {
			addVertex(new Vertex(i));
		}
		
		addEdgeMutually(0,1);
		addEdgeMutually(0,4);
		addEdgeMutually(1,4);
		addEdgeMutually(1,3);
		addEdgeMutually(4,3);
		addEdgeMutually(1,2);
		addEdgeMutually(3,2);
	}
	
	private void constructGraphWithoutCycle() {
		vertices.clear();
		// 5 vertices, 0 1 2 3
		for (int i = 0; i < 4; ++i) {
			addVertex(new Vertex(i));
		}
		
		addEdge(0,1);
		addEdge(2,0);
		addEdge(2,3);
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
	
	public boolean hasCycle() {
		Stack<Vertex> stack = new Stack<Vertex>();
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		
		stack.push(vertices.get(0));

		while (!stack.isEmpty()) {
			Vertex vertex = stack.pop();
			if (!visited.containsKey(vertex)) {
				visited.put(vertex, true);
				for (Vertex v : vertex.neighbors) {
					stack.push(v);
				}
				continue;
			}
			if (visited.containsKey(vertex)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Test
	public void test() {
		constructGraph();
		printGraph();
		
		// O(V+E)
		ArrayList<Integer> expectedBFS = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,1,4,3,2}));
		assertEquals(expectedBFS, breadthFirstTraversal(0));
		ArrayList<Integer> expectedDFS = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,4,3,2,1}));
		assertEquals(expectedDFS, depthFirstTraversal(0));
	}
	
	@Test
	public void detectCycle() {
		// O(V+E)
		constructGraph();
		assertEquals(true, hasCycle());

		constructGraphWithoutCycle();
		assertEquals(false, hasCycle());
	}
}
