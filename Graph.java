package bellman_Ford;

import java.util.*;

public class Graph
{
	public class NoVertexException extends Exception
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String vert; 

		/*
		 * Procedure
		 *    NoVertexException
		 * Parameters
		 *    vert, a String
		 * Purpose
		 *    to create an error for when a vertex is not in a graph
		 * Produces 
		 *    a new NoVertexException error
		 * Preconditions 
		 *    none
		 * Postconditions
		 *    none
		 * 
		 */

		private NoVertexException(String vert)
		{
			this.vert = vert;
		}

		/*
		 * Procedure
		 *   getString
		 * Parameters
		 *   called on a NoVertexException object 
		 * Purpose
		 *   to return the error string
		 * Produces 
		 *   vert, a string
		 * Preconditions 
		 *   NoVertexException is not null
		 * Postconditions
		 *   vert is the vertex that caused the error
		 * 
		 */

		private String getString()
		{
			return vert;
		}
	}
	//largest value used for infinity
	public static final double INFINITY = Double.MAX_VALUE;
	//representing graph as map of string(name), vertex pairs
	private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	/*
	 * Procedure
	 *   getVertex
	 * Parameters
	 *   name, a String
	 *   called on a Graph g
	 * Purpose
	 *   returns the vertex associated with name
	 * Produces 
	 *   v, a vertex
	 * Preconditions 
	 *   g is not null
	 * Postconditions
	 *   if v is in g, returns v, otherwise returns an error
	 * 
	 */

	public Vertex getVertex(String name) throws NoVertexException
	{
		Vertex v = vertexMap.get(name);
		if (v == null)
			throw new NoVertexException(name);
		return v;
	}

	/*
	 * Procedure
	 *   addVertex
	 * Parameters
	 *   vert, a String
	 *   called on g, a Graph
	 * Purpose
	 *   to add a new Vertex to g
	 * Produces 
	 *   a side effect in vertexMap
	 * Preconditions 
	 *   g is not null.
	 * Postconditions
	 *   if v is not in g, adds to g, otherwise returns a statement
	 * 
	 */

	public void addVertex(String vert)
	{
		if(vertexMap.containsKey(vert))
			System.out.println(vert + " is already in the graoh");
		else
			vertexMap.put(vert, new Vertex(vert));
	}

	/*
	 * Procedure
	 *   addEdge
	 * Parameters
	 *   head, a String
	 *   tail, a String
	 *   cost, a double
	 *   called on a Graph g
	 * Purpose
	 *   to add a directed edge (tail, head) of weight cost to g
	 * Produces 
	 *   a side effect in g
	 * Preconditions 
	 *   g is not Null
	 *   head and tail are already vertices in g
	 * Postconditions
	 *   head is now in the adjacency list of tail.
	 * 
	 */

	public void addEdge(String tail, String head, double cost)
	{
		try 
		{
			Vertex v = getVertex(tail);
			Vertex u = getVertex(head);
			v.addAdjacent(new Edge(u, cost));		
		}
		catch(NoVertexException e)
		{
			System.out.print("Vertex " + e.getString() + " does not exist");
		}
	}

	/*
	 * Procedure
	 *   initializeSingleSource
	 * Parameters
	 *   source, a String
	 *   called on a Graph g
	 * Purpose
	 *   to initialize g for bellmanFord
	 * Produces 
	 *   a side effect on g
	 * Preconditions 
	 *   g is not null
	 *   source is a vertex in g
	 * Postconditions
	 *   all vertices in g now have distance infinity and null parents,
	 *   except for source, with 0 distance. 
	 * 
	 */

	private void initializeSingleSource(String source)
	{
		try 
		{
			Vertex s = getVertex(source);
			for (Map.Entry<String, Vertex> mapElement : vertexMap.entrySet())
			{
				mapElement.getValue().setDistance(INFINITY);
				mapElement.getValue().setParent(null);
			}
			s.setDistance(0);
		}
		catch(NoVertexException e)
		{
			System.out.print("Vertex " + e.getString() + " does not exist");	
		}
	}

	/*
	 * Procedure
	 *   relax
	 * Parameters
	 *   v, a Vertex
	 *   u, a Vertex
	 *   called on a Graph g
	 * Purpose
	 *   to relax the edge (u, v)
	 * Produces 
	 *   a side effect to the edge (u, v)
	 * Preconditions 
	 *   g is not null
	 *   v and u are vertices in g
	 * Postconditions
	 *   relaxes the edge (u, v) if the distance of the edge is 
	 *   shorter than the previous distance
	 * 
	 */

	public void relax (Vertex u, Vertex v)
	{
		if (u.isAdjacent(v) != null)
			if (v.getDistance() > u.getDistance() + u.isAdjacent(v).getWeight())
			{
				v.setDistance(u.getDistance() + u.isAdjacent(v).getWeight());
				v.setParent(u);
			}
	}

	/*
	 * Procedure
	 *   bellmanFord
	 * Parameters
	 *   source, a String
	 *   called on a Graph g
	 * Purpose
	 *   runs the bellmanFord algorithm on g, with source as
	 *   the starting vertex
	 * Produces 
	 *   a side effect on g, and a boolean bool
	 * Preconditions 
	 *   source is a vertex in g
	 *   g is not null
	 * Postconditions
	 *   If there is a negative edge in g, bool is false
	 *   otherwise, bool is true
	 *   All vertices in g now have prev and distance initialized
	 *   according to Bellman Ford algorithm.
	 *   Breaks ties arbitrarily.
	 */

	public boolean bellmanFord(String source)
	{
		initializeSingleSource(source);
		//for i = 1 to |G.v| - 1
		for(int i = 1; i < vertexMap.size(); i++)
		{
			//for every Vertex v
			for (Map.Entry<String, Vertex> mapElement : vertexMap.entrySet())
			{ 
				//for every edge in adjacency list of v
				for(Edge e : mapElement.getValue().getAdjacent())
				{
					relax(mapElement.getValue(), e.getHead());
				}
			}
		}
		
		//for every Vertex v
		for (Map.Entry<String, Vertex> mapElement : vertexMap.entrySet())
		{
			//for every vertex in the adjacency of v
			for(Edge e : mapElement.getValue().getAdjacent())
			{
				if(e.getHead().getDistance() > mapElement.getValue().getDistance() + e.getWeight())
					return false;
			}
		}
		
		return true;
	}

	/*
	 * Procedure
	 *   printPath
	 * Parameters
	 *   dest, a Vertex
	 *   called on a Graph g
	 * Purpose
	 *   prints the path from dest to the root of the shortest path tree
	 * Produces 
	 *   prints the path to the console
	 * Preconditions 
	 *   g is not null
	 *   name is a Vertex in g
	 * Postconditions
	 *   prints the path from dest to the root of the shortest
	 *   path tree to the console
	 * 
	 */

	public void printPath(Vertex dest) throws NoVertexException
	{
		if(dest.getPrev() != null)
		{
			printPath(dest.getPrev());
			System.out.print(" to ");
		}
		System.out.print(dest.getElement());
	}
}
