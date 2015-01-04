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
	public ArrayList<Integer> neighborIndices = null;
	public Integer data;
	public Integer weight;

	public Vertex() {
		neighbors = new ArrayList<Vertex>();
		neighborIndices = new ArrayList<Integer>();
		data = null;
		weight = 0;
	}
	
	public Vertex(Integer data) {
		this.data = data;
		neighbors = new ArrayList<Vertex>();
		neighborIndices = new ArrayList<Integer>();
		weight = 0;
	}	

	public void addNeighbor(Vertex vertex) {
		neighbors.add(vertex);
		weight = 0;
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
		
//		addEdge(0,1);
//		addEdge(2,0);
//		addEdge(2,3);
		
		addEdgeMutually(0,1);
		addEdgeMutually(2,0);
		addEdgeMutually(2,3);
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
	
	public void depthFirstTraversalRecursive(Vertex v, HashMap<Vertex, Boolean> visited) {
		// mark as visited
		visited.put(v, true);
		System.out.print(v.data + " ");
		
		for (Vertex neighbor : v.neighbors) {
			if (!visited.containsKey(neighbor)) {
				depthFirstTraversalRecursive(neighbor, visited);
			}
		}
		
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
		depthFirstTraversalRecursive(vertices.get(0), new HashMap<Vertex, Boolean>());
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
	
	private void constructedUndirectedGraphWithCycles() {
		vertices.clear();
		edges.clear();
		// 3 vertices, 0 1 2, triangle
		for (int i = 0; i < 5; ++i) {
			addVertex(new Vertex(i));
		}
		
		addEdgeMutually(1,0);
		addEdgeMutually(0,2);
		addEdgeMutually(2,0);
		addEdgeMutually(0,3);
		addEdgeMutually(3,4);

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
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isCyclic() {
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		
		for (Vertex vertex: vertices) {
			if (!visited.containsKey(vertex)) {
				if (isCyclic(vertex, visited, null) == true) {
					return true;
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

		// maybe only for the directedgraphs..... not sure
		//constructGraphWithoutCycle();
		//assertEquals(false, hasCycle());
		
		// http://www.geeksforgeeks.org/union-find/
		// O(n) where n is # of vertices??? so is it O(V) then?
		// nonono.. it says O(ElogV) WTF.. who's wrong??
		constructDirectedGraphWithCycle();
		assertEquals(true, detectCycleWithFindAndUnionAlgorithm());
		
		// http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
		// cannot figure this out.... commenting out
		// must be an undirected graph.. maybe that's why? gotta fix it now!!!
		constructedUndirectedGraphWithCycles();
		assertEquals(true, isCyclic());
	}
	
	private void topologicalSorting(Vertex vertex, HashMap<Vertex, Boolean> visited, Stack<Vertex> stack) {
		visited.put(vertex, true);
		
		for (Vertex neighbor: vertex.neighbors) {
			if (!visited.containsKey(neighbor)) {
				topologicalSorting(neighbor, visited, stack);
			}
		}
		stack.push(vertex);
	}
	
	private void constructTopologicalSortingGraph() {
		vertices.clear();
		edges.clear();
		// 0 1 2 3 4 5
		for (int i = 0; i < 6; ++i) {
			addVertex(new Vertex(i));
		}
		
		addEdge(5,2);
		addEdge(5,0);
		addEdge(2,3);
		addEdge(3,1);
		addEdge(4,1);
		addEdge(4,0);
	}
	
	@Test
	public void topologicalSorting() {
		constructTopologicalSortingGraph();
		System.out.println("topological sorting");

		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		Stack<Vertex> stack = new Stack<Vertex>();
		for (Vertex vertex: vertices) {
			if (!visited.containsKey(vertex)) {
				topologicalSorting(vertex, visited, stack);
			}
		}
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().data + " ");
		}
		System.out.println();
	}
	
	private boolean addWeightedEdge(int from, int to, int weight) {
		final Vertex fromVertex = getVertexAt(from);
		Vertex toVertex = getVertexAt(to);
		toVertex.weight = weight;

		if (fromVertex != null && toVertex != null) {
			fromVertex.addNeighbor(toVertex);
			// support for directed graph
			edges.add(new Edge(from, to));

			// support for neighborIndices
			fromVertex.neighborIndices.add(to);			
			return true;
		}
		
		return false;
	}
	
	private void constructGraphForLongestPathUsingTopologicalSorting() {
		vertices.clear();
		edges.clear();
		// 0 1 2 3 4 5
		for (int i = 0; i < 6; ++i) {
			addVertex(new Vertex(i));
		}
		
	    addWeightedEdge(0, 1, 5);
	    addWeightedEdge(0, 2, 3);
	    addWeightedEdge(1, 3, 6);
	    addWeightedEdge(1, 2, 2);
	    addWeightedEdge(2, 4, 4);
	    addWeightedEdge(2, 5, 2);
	    addWeightedEdge(2, 3, 7);
	    addWeightedEdge(3, 5, 1);
	    addWeightedEdge(3, 4, -1);
	    addWeightedEdge(4, 5, -2);
	}
	
	// slightly modified to support returning index directly (in the stack)
	private void topologicalSortingForLongestPath(int index, HashMap<Integer, Boolean> visited, Stack<Integer> stack) {
		visited.put(index, true);
		
		for (Integer neighbor: vertices.get(index).neighborIndices) {
			if (!visited.containsKey(neighbor)) {
				topologicalSortingForLongestPath(neighbor, visited, stack);
			}
		}
		stack.push(index);
	}

	// FIXME : debug this and fix the null pointer Exception
	@Test
	public void longestPathUsingTopologicalSorting() {
		System.out.println("\nlongestPathUsingTopologicalSorting");
		constructGraphForLongestPathUsingTopologicalSorting();

		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < vertices.size(); ++i) {
			if (!visited.containsKey(i)) {
				topologicalSortingForLongestPath(i, visited, stack);
			}
		}
		
		int[] distances = new int[vertices.size()];
		for (int i = 0; i < vertices.size(); ++i) {
			distances[i] = Integer.MAX_VALUE;
		}
		// starting point;
		distances[1] = 0;

		while (!stack.isEmpty()) {
			Integer vertexIndex = stack.pop();
			for (Integer neighborIndex : vertices.get(vertexIndex).neighborIndices) {
				if (distances[neighborIndex] < distances[vertexIndex] + vertices.get(vertexIndex).weight) {
					distances[neighborIndex] = distances[vertexIndex] + vertices.get(vertexIndex).weight;
				}
			}
		}
		
		for (int i = 0; i < distances.length; ++i) {
			if (distances[i] != Integer.MAX_VALUE) {
				System.out.print(distances[i] + " ");	
			}
		}
		
		System.out.println();
	}

	public boolean isTree() {
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		
		boolean hasCycle = false;
		for (Vertex vertex: vertices) {
			if (!visited.containsKey(vertex)) {
				if (isCyclic(vertex, visited, null) == true) {
					//System.out.println("cyclic @ " + vertex.data);
					hasCycle = true;
					break;
				}
			}
		}
		
		boolean isAllVisited = true;
		// see if all vertices are visited
		for (Vertex vertex: vertices) {
			if (!visited.containsKey(vertex)) {
				//System.out.println("vertex " + vertex.data + " is not visited! WTF!");
				isAllVisited = false;
				break;
			}
		}
		
		//System.out.println("hasCycle : " +  hasCycle + " visited " + isAllVisited);
		
		boolean isTree = !hasCycle && isAllVisited;
		return isTree;
	}
	
	@Test
	public void isTreeOrNot() {
		constructedUndirectedGraphWithCycles();
		assertEquals(false, isTree());
		
		constructGraphWithoutCycle();
		assertEquals(true, isTree());		
	}
}
