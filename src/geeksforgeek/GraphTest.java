package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GraphTest {

	public static class MyGraph {
		private ArrayList<ArrayList<Integer>> adjList;
		
		public MyGraph(int numberOfVertices) {
			adjList = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < numberOfVertices; ++i) {
				adjList.add(new ArrayList<Integer>());
			}
		}
		
		// directed. only add 1 edge, not the back edge
		public void addEdge(int from, int to) {
			adjList.get(from).add(to);
		}
		
		private void depthFirstTraversal(int start, boolean[] visited) {
			if (!visited[start]) {
				visited[start] = true;
				System.out.print(start + " ");
				ArrayList<Integer> neighbors = adjList.get(start);
	
				for (Integer neighborIndex:neighbors) {
					depthFirstTraversal(neighborIndex, visited);
				}
			}
		}
		
		public void depthFirstTraversal(int start) {
			System.out.print("DepthFirstTraversal : ");
			depthFirstTraversal(start, new boolean[adjList.size()]);
			System.out.println();
		}
		
		public boolean hasCycle() {
			// I.M.P.L.E.M.E.N.T
			return false;
		}
	}
	
	@Test
	public void testDepthFirstTraversal() {
	    MyGraph g = new MyGraph(5);
	    g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 3);
	    g.addEdge(3, 4);
	    g.addEdge(2, 4);
	    g.depthFirstTraversal(0);
	}
	
	@Test
	public void testHasCycle() {
		MyGraph g = new MyGraph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		assertEquals(true, g.hasCycle());
	}

}
