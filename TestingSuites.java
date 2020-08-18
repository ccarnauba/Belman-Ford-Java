package bellman_Ford;

import bellman_Ford.Graph.NoVertexException;

public class TestingSuites
{
	/*  Running instructions!!
	 * 
	 * Since Java doesn't come with a predefined Graph class,
	 * I made one. I implemented the graph using adjacency list notation.
	 * All elements in the adjacency list are Edges -- essentially a Vertex
	 * weight pair with getters and setters. 
	 * 
	 * To run the Bellman-Ford algorithm, first one has to define a Graph
	 * object, add Vertices using .addVertex(element), then add Edges using
	 * .addEdge(tail, head, weight). Once the graph is build, 
	 * .bellmanFord(source) finds the shortest path from source to all
	 * vertices in the graph and sets the parents of the vertices in the graph
	 * accordingly. To check the shortest path from source to a Vertex v, 
	 * the procedure .printPath(v) can be called. 
	 * 
	 */
	public static void testVertex()
	{
		System.out.println("Vertex Testing Suite");
		
		Vertex v = new Vertex("v");
		Vertex u = new Vertex("u");
		Vertex a = new Vertex("a");
		
		//testing getElement
		System.out.println("v has element " + v.getElement());
		
		//testing getDistance and setDistance
		System.out.println("v has distance " + v.getDistance());
		v.setDistance(10);
		System.out.println("v has distance " + v.getDistance());
		
		//test getParent and setParent
		System.out.println("v has parent " + v.getPrev());
		v.setParent(u);
		System.out.println("v has parent " + v.getPrev().getElement());
		
		//test reset
		v.reset();
		System.out.println("v has parent " + v.getPrev());
		System.out.println("v has distance " + v.getDistance());
		
		//test getAdjacent and addAdjacent
		v.addAdjacent(new Edge(u, 2));
		System.out.println("v is adjacent to " + v.getAdjacent().get(0).getHead().getElement());
		//test isAdjacent
		if(v.isAdjacent(u) != null)
			System.out.println("v is adjacent to u");
		if(v.isAdjacent(a) == null)
			System.out.println("v is not adjacent to a");
	}
	
	public static void testEdge()
	{
		System.out.println("Edge testing suite");
		Vertex v = new Vertex("v");
		Vertex u = new Vertex("u");
		Edge e = new Edge(v, 2);
		
		//test getWeight
		System.out.println(e.getWeight());
		
		//test getHead
		System.out.println(e.getHead().getElement());
		
		//test isIncident
		System.out.println(e.isIncident(u));
	}
	
	public static void testGraph() throws NoVertexException
	{
		System.out.println("Graph Testing Suite");
		System.out.println("Testing base Graph functions");
		Graph test = new Graph();
		 
		test.addVertex("a");
		System.out.println(test.getVertex("a").getElement());
		
		//testing NoVetexException, raises error if not commented
		//System.out.println(test.getVertex("b"));
		
		test.addVertex("b");
		test.addEdge("a", "b", 2);
		String aAdjacent = test.getVertex("a").getAdjacent().get(0).getHead().getElement();
		System.out.println("a is adjacent to " + aAdjacent);
		
		test.getVertex("b").setDistance(5);
		test.getVertex("a").setDistance(1);
		System.out.println("a has distance " + test.getVertex("a").getDistance() +
							", b has distance " + test.getVertex("b").getDistance() +
							", (a, b) has weight " + test.getVertex("a").getAdjacent().get(0).getWeight());
		
		test.relax(test.getVertex("a"), test.getVertex("b"));
		System.out.println("relaxing");
		System.out.println("a has distance " + test.getVertex("a").getDistance() +
							", b has distance " + test.getVertex("b").getDistance() +
							", (a, b) has weight " + test.getVertex("a").getAdjacent().get(0).getWeight());
		
		
		System.out.println("\nTesting Bellman-Ford on Graph from figure 1 in assignment 6.");
		Graph g1 = new Graph();
		g1.addVertex("s");
		g1.addVertex("t");
		g1.addVertex("y");
		g1.addVertex("z");
		g1.addVertex("x");
		
		g1.addEdge("s", "t", 3);
		g1.addEdge("s", "y", 5);

		g1.addEdge("t", "x", 6);
		g1.addEdge("t", "y", 2);

		g1.addEdge("y", "x", 4);
		g1.addEdge("y", "z", 6);
		g1.addEdge("y", "t", 1);

		g1.addEdge("x", "z", 2);

		g1.addEdge("z", "x", 7);
		g1.addEdge("z", "s", 3);

		System.out.println(g1.bellmanFord("s"));

		g1.printPath(g1.getVertex("z"));
		System.out.println();
		
		System.out.println("\nTesting Bellman-Ford on Graph from figure 2 in assignment 6.");
		Graph g2 = new Graph();
		g2.addVertex("s");
		g2.addVertex("t");
		g2.addVertex("y");
		g2.addVertex("z");
		g2.addVertex("x");

		g2.addEdge("s", "t", 6);
		g2.addEdge("s", "y", 7);

		g2.addEdge("t", "x", 5);
		g2.addEdge("t", "y", 8);
		g2.addEdge("t", "z", -4);

		g2.addEdge("y", "x", -3);
		g2.addEdge("y", "z", 9);

		g2.addEdge("x", "t", -2);

		g2.addEdge("z", "s", 2);
		g2.addEdge("z", "x", 7);

		System.out.println(g2.bellmanFord("s"));

		g2.printPath(g2.getVertex("z"));
		System.out.println();
		
		System.out.println("\nTesting Bellman-Ford on Graph with negative cycle");
		Graph neg = new Graph();
		neg.addVertex("a");
		neg.addVertex("b");
		neg.addVertex("c");
		
		neg.addEdge("a", "b", 1);
		neg.addEdge("b", "c", 1);
		neg.addEdge("c", "a", -3);
		
		System.out.println(neg.bellmanFord("a"));


	}
	
	public static void main(String [] args) throws NoVertexException
	{
		testVertex();
		System.out.println("\n");
		
		testEdge();
		System.out.println("\n");
		
		testGraph();
	}
}
