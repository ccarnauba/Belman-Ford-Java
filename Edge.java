package bellman_Ford;

import bellman_Ford.Vertex;

public class Edge
{
	private Vertex head;
	private double weight;
	
	/*
	 * Procedure
	 *   Edge
	 * Parameters
	 *   head, a Vertex
	 *   weight, a double
	 * Purpose
	 *   to initialize an object of type Edge
	 * Produces 
	 *   initializes an object of type Edge
	 * Preconditions 
	 *   head is not null
	 * Postconditions
	 *   Edge is a new Edge object representing (v, head),
	 *   where v is the vertex in who's adjacency list Edge will
	 *   be added. 
	 * 
	 */
	
	public Edge(Vertex head, double weight)
	{
		this.head = head;
		this.weight = weight;
	}
	
	/*
	 * Procedure
	 *   getWeight
	 * Parameters
	 *   called on e, an Edge
	 * Purpose
	 *   to return the weight of e
	 * Produces 
	 *   weight, a double
	 * Preconditions 
	 *   e is not null
	 * Postconditions
	 *   weight = e.weight
	 * 
	 */
	
	public double getWeight()
	{
		return weight;
	}
	
	/*
	 * Procedure
	 *   getHead
	 * Parameters
	 *   called on e, an Edge
	 * Purpose
	 *   to return the head of the edge e
	 * Produces 
	 *   head, a Vertex
	 * Preconditions 
	 *   e is not null
	 * Postconditions
	 *   head is the Vertex incident to e.
	 * 
	 */
	
	public Vertex getHead()
	{
		return head;
	}
	
	/*
	 * Procedure
	 *   isIncident
	 * Parameters
	 *   head, a Vertex
	 *   called on e, an edge
	 * Purpose
	 *   to check if e is incident to head
	 * Produces 
	 *   edge, an edge
	 * Preconditions 
	 *   e is not null
	 * Postconditions
	 *   if head is incident, returns e, else returns null.
	 * 
	 */
	
	public Edge isIncident(Vertex head)
	{
		if (this.head == head)
			return this;
		
		return null;
	}
}