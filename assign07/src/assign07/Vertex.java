package assign07;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is a generic class representing a vertex (AKA node) in a directed graph.
 * 
 * @author Hui Wang && Erdi Fan
 *
 * @param <Type>
 */
public class Vertex<Type> implements Comparable<Vertex<Type>> {

	/** Indicate whether or not the vertex has been visited */
	private boolean visited;

	/** The data that the vertex contains */
	private Type data;

	/** distance of the current vertex from start vertex */
	private double distanceFromStart;

	/** adjacency list */
	private LinkedList<Edge<Type>> adj;

	/** The number of incoming edges */
	private int indegree;

	/**
	 * Returns the number of incoming edges
	 */
	public int getIndegree() {
		return indegree;
	}

	/**
	 * Sets the number of incoming edges to one bigger
	 */
	public void setIndegreePlusOne() {
		indegree++;
	}

	/**
	 * Sets the number of incoming edges to one smaller
	 */
	public void setIndegreeMinusOne() {
		indegree--;
	}

	/** Constructor */
	public Vertex(Type data) {
		this.data = data;
		this.adj = new LinkedList<Edge<Type>>();
	}

	/** Return whether or not the vertex has been visited */
	public boolean isVisited() {
		return visited;
	}

	/** Set the visiting status of the vertex */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Returns the data of the vertex that contains
	 */
	public Type getData() {
		return data;
	}

	/**
	 * Returns the distance of the vertex from the start vertex
	 */
	public double getDistanceFromStart() {
		return distanceFromStart;
	}

	/**
	 * Set the distance of the vertex from the start vertex
	 */
	public void setDistanceFromStart(double distanceFromStart) {
		this.distanceFromStart = distanceFromStart;
	}

	/**
	 * Add an edge to connect two vertices
	 */
	public void addEdge(Vertex<Type> otherVertex) {
		adj.add(new Edge<Type>(otherVertex));
	}

	/**
	 * An iterator for all edges of the current vertex
	 */
	public Iterator<Edge<Type>> edges() {
		return adj.iterator();
	}

	public String toString() {
		String s = "Vertex " + data + " adjacent to vertices ";
		Iterator<Edge<Type>> itr = adj.iterator();
		while (itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}

	@Override
	public int compareTo(Vertex<Type> o) {
		return Double.compare(this.distanceFromStart, o.getDistanceFromStart());
	}
}
