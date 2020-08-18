package bellman_Ford;

import java.util.LinkedList;
import java.util.List;

public class Vertex
{
	private String element; // name of vertex
	private List<Edge> adjacent; // adjacency list
	private double distance; // distance from source
	private Vertex prev; // parent in shortest path

	/*
	 * Procedure 
	 *   Vertex
	 * Parameters
	 *   Element, a String
	 * Purpose
	 *   To initialize a new object of type Vertex 
	 * Produces 
	 *   v, an object of type Vertex
	 * Preconditions 
	 *   Element is a String
	 * Postconditions
	 *   v is not adjacent to any other vertex
	 *   v.parent = null
	 *   v.distance = infinity
	 */

	public Vertex(String element)
	{
		this.element = element;
		adjacent = new LinkedList<Edge>();
		reset();
	}

	/*
	 * Procedure
	 *   reset
	 * Parameters
	 *   Called on a Vertex V
	 * Purpose
	 *   To reset a Vertex to original parameters
	 * Produces 
	 *   side-effect on a Vertex v 
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   v.parent = null
	 *   v.distance = infinity
	 */

	public void reset() 
	{
		distance = Graph.INFINITY;
		prev = null;
	}

	/*
	 * Procedure
	 *   getElement
	 * Parameters
	 *   Called on a Vertex v 
	 * Purpose
	 *   To extract the name of Vertex
	 * Produces 
	 *   element, a String
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   element = v.element
	 * 
	 */

	public String getElement()
	{
		return element;
	}

	/*
	 * Procedure
	 *   getDistance
	 * Parameters
	 *   called on Vertex v
	 * Purpose
	 *   to return distance of v
	 * Produces 
	 *   distance, a double
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   distance is the shortest distance of v to 
	 *   a source vertex if bellmanFord is called
	 * 
	 */

	public double getDistance()
	{
		return distance;
	}

	/*
	 * Procedure
	 *   getAdjacent
	 * Parameters
	 *   called on Vertex v
	 * Purpose
	 *   return the adjacency list of v
	 * Produces 
	 *   adjacent, a list of edges of v
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   adjacent contains all edges incident to v.
	 */

	public List<Edge> getAdjacent()
	{
		return adjacent;
	}

	/*
	 * Procedure
	 *   isAdjacent
	 * Parameters
	 *   v, a Vertex
	 *   called on a Vertex u
	 * Purpose
	 *   to check if u and v are adjacent
	 * Produces 
	 *   e, an Edge
	 * Preconditions 
	 *   u is not null
	 * Postconditions
	 *   if u and v are adjacent, returns the Edge related, otherwise
	 *   returns null
	 * 
	 */

	public Edge isAdjacent(Vertex v)
	{
		for(Edge e : adjacent)
		{
			if (e.isIncident(v) != null)
				return e.isIncident(v);
		}
		return null;
	}

	/*
	 * Procedure
	 *   setDistance
	 * Parameters
	 *   dist, a double
	 *   called on a Vertex v
	 * Purpose
	 *   to change the distance of a vertex V
	 * Produces 
	 *   a side effect on v
	 * Preconditions 
	 *   v is none null
	 * Postconditions
	 *   v.distance = dist
	 * 
	 */

	public void setDistance(double dist)
	{
		distance = dist;
	}

	/*
	 * Procedure
	 *   setParent
	 * Parameters
	 *   parent, a Vertex
	 *   called on a Vertex v
	 * Purpose
	 *   to change the parent of v
	 * Produces 
	 *   a side effect on v
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   v.prev = parent
	 * 
	 */

	public void setParent(Vertex parent)
	{
		prev = parent;
	}
	
	/*
	 * Procedure
	 *   addAdjacent
	 * Parameters
	 *   e, an Edge
	 *   called on a Vertex v
	 * Purpose
	 *   adds an edge e to the adjacency list of v
	 * Produces 
	 *   a side effect on v
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   e is now in the adjacency list of v
	 * 
	 */
	
	public void addAdjacent(Edge e)
	{
		adjacent.add(e);
	}
	
	/*
	 * Procedure
	 *   getPrev
	 * Parameters
	 *   called on a Vertex v
	 * Purpose
	 *   returns prev
	 * Produces 
	 *   prev, a vertex
	 * Preconditions 
	 *   v is not null
	 * Postconditions
	 *   prev is the parent of v after bellman-ford is called
	 * 
	 */
	
	public Vertex getPrev()
	{
		return prev;
	}
}

