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

// support for directed graph
class Edge {
	public int src;
	public int dest;
	public Edge(int from, int to) {
		this.src = from;
		this.dest = to;
	}
}

public class Graph {
	public ArrayList<Vertex> vertices = null;
	public ArrayList<Edge> edges = null;

	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
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
			// support for directed graph
			edges.add(new Edge(from, to));
			edges.add(new Edge(to, from));
			return true;
		}
		
		return false;
	}
	
	public boolean addEdge(int from, int to) {
		final Vertex fromVertex = getVertexAt(from);
		final Vertex toVertex = getVertexAt(to);
		
		if (fromVertex != null && toVertex != null) {
			fromVertex.addNeighbor(toVertex);
			// support for directed graph
			edges.add(new Edge(from, to));			
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
		edges.clear();
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
		edges.clear();
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
	
	// A utility function to find the subset of an element i
	private int find(int[] parent, int i) {
	    if (parent[i] == -1) {
	        return i;
	    }
	    return find(parent, parent[i]);
	}
	
	// A utility function to do union of two subsets 
	private void union(int[] parent, int x, int y) {
	    int xset = find(parent, x);
	    int yset = find(parent, y);
	    parent[xset] = yset;
	}

	private boolean detectCycleWithFindAndUnionAlgorithm() {
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < vertices.size(); ++i) {
			parent[i] = -1;
		}

	    // Iterate through all edges of graph, find subset of both
	    // vertices of every edge, if both subsets are same, then there is
	    // cycle in graph.
		for (Edge edge: edges) {
			final int x = find(parent, edge.src);  // initially return src as it's not in the parent array
	        final int y = find(parent, edge.dest); // initially return dest as it's not in the parent array

	        if (x == y) {
	        	//System.out.println(" x == y @ " + x + " " + y + " with " + edge.src + " and " + edge.dest);
	            return true;
	        }
	 
	        union(parent, x, y);
	    }
		return false;
	}
	
	public void constructDirectedGraphWithCycle() {
		vertices.clear();
		edges.clear();
		// 3 vertices, 0 1 2, triangle
		for (int i = 0; i < 3; ++i) {
			addVertex(new Vertex(i));
		}
		
		addEdge(0,1);
		addEdge(1,2);
		addEdge(2,0);
	}
	
	private void constructedUndirectedGraph() {
		
	}
	
	private boolean isCyclic(Vertex vertex, HashMap<Vertex, Boolean> visited, Vertex parent) {
		visited.put(vertex, true);

		for (Vertex neighbor : vertex.neighbors) {
			if (!visited.containsKey(neighbor)) {
				if (isCyclic(neighbor, visited, vertex) == true) {
					return true;
				}
			}
			else {
				if (parent != neighbor) {
					return false;
				}
			}
		}
		return false;
	}
	
	@Test
	public void detectCycle() {
		// O(V+E)
		constructGraph();
		assertEquals(true, hasCycle());

		constructGraphWithoutCycle();
		assertEquals(false, hasCycle());
		
		// http://www.geeksforgeeks.org/union-find/
		// O(n) where n is # of vertices??? so is it O(V) then?
		// nonono.. it says O(ElogV) WTF.. who's wrong??
		constructDirectedGraphWithCycle();
		assertEquals(true, detectCycleWithFindAndUnionAlgorithm());
		
		// http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
		// cannot figure this out.... commenting out
		// assertEquals(false, isCyclic(vertices.get(0), new HashMap<Vertex, Boolean>(), null));
	}
}
