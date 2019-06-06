package assign07;

/**
 * This generic class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Hui Wang && Erdi Fan
 *
 */

public class Edge<Type> {
		/** destination of this directed edge */
		private Vertex<Type> dst;

		/** Constructor */
		public Edge(Vertex<Type> dstV) {
			this.dst = dstV;
		}

		/**
		 * Returns the destination vertex
		 */
		public Vertex<Type> getDstVertex() {
			return this.dst;
		}

}
