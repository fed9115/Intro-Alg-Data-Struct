package lab08;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Edge {
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	private double weight;

	// destination of this directed edge
	private Vertex dst;

	public Edge(Vertex dst) {
		this.dst = dst;
	}

	public Vertex getOtherVertex() {
		return this.dst;
	}

	public String toString() {
		return this.dst.getName();
	}

}