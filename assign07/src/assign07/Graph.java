package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * This is a generic class
 * @author Hui Wang && Erdi Fan
 *
 * @param <T>
 */
public class Graph<Type> {
	
		/** the graph -- a set of vertices (String name mapped to Vertex instance) */
		private HashMap<Type, Vertex<Type>> vertices;
		
		/**
		 * Constructs an empty graph.
		 */
		public Graph() {
			vertices = new HashMap<Type, Vertex<Type>>();
		}

		/**
		 * Add edge between src and dst vertices
		 */
		public void addEdge(Type src, Type dst) {
			Vertex<Type> srcV;
			Vertex<Type> dstV;

			if(vertices.containsKey(src))
				srcV = vertices.get(src);
			else {
				srcV = new Vertex<Type>(src);
				vertices.put(src, srcV);
			}

			if(vertices.containsKey(dst))
				dstV = vertices.get(dst);
			else {
				dstV = new Vertex<Type>(dst);
				vertices.put(dst, dstV);
			}

			srcV.addEdge(dstV);
			dstV.setIndegreePlusOne();
		}
		
		/**
		 * Returns the collection of all vertices of the graph
		 */
		public HashMap<Type, Vertex<Type>> getVertices(){
			return vertices;
		}
		
		/**
		 * Checks whether or not the graph has any cycles from the start vertex x
		 * Throws an IllegalArgumentException if vertex x is null
		 */
		public boolean hasCycle(Vertex<Type> x, Stack<Vertex<Type>> visitedStack) {
			if(x == null) {
				throw new IllegalArgumentException("Vertex " + x + " does not exist.");
			}
			if (!visitedStack.contains(x)) {
				visitedStack.push(x);
			}
			
			Iterator<Edge<Type>> itr = x.edges();
			while (itr.hasNext()) {
				Vertex<Type> w = itr.next().getDstVertex();
				if (visitedStack.contains(w)) {
					return true;
				}
				if (hasCycle(w, visitedStack)){		
					return true;
				}
			}
			visitedStack.pop();
			return false;
		}
		
		/**
		 * Checks whether or not the two vertices src and dst have a path to connect each other
		 */
		public boolean areConnected(Vertex<Type> src, Vertex<Type> dst) {
			LinkedList<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();
			queue.addFirst(src);
			src.setVisited(true);
			while(!queue.isEmpty()) {
				Vertex<Type> x = queue.removeFirst();
				Iterator<Edge<Type>> itr = x.edges();
				while (itr.hasNext()) {
					Vertex<Type> w = itr.next().getDstVertex();
					if (w.equals(dst)) {
						return true;
					}
					
					if(!queue.contains(w) && !w.isVisited()) {
						queue.addFirst(w);
						w.setVisited(true);
					}								
				}
			}
			return false;
		}
		
		/**
		 * Returns a sorted list of the vertices in the graph
		 * It may have more than one valid ordering
		 */
		public List<Type> topologicalSort(List<Type> sortedList, Graph<Type> graph) {
			
			LinkedList<Vertex<Type>> queue = new LinkedList<>();
			for (int i = 0; i < sortedList.size(); i++) {
				queue.add(graph.getVertices().get(sortedList.get(i)));
			}
			
			while (!queue.isEmpty()) {
				Vertex<Type> x = queue.remove();
				if (!sortedList.contains(x.getData())) {
					sortedList.add(x.getData());
				}
				
				Iterator<Edge<Type>> itr = x.edges();
				while (itr.hasNext()) {
					Vertex<Type> w = itr.next().getDstVertex();
					w.setIndegreeMinusOne();
					if (w.getIndegree() == 0) {
						queue.add(w);
					}
				}
			}
			return sortedList;	
		}
}
