package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 * 
 * @author Erin Parker & Hui Wang && Erdi Fan
 * @version February 27, 2019
 */
public class GraphUtility {

	
	/**
	 * It uses depth-first search algorithm to check if the graph contains a cycle
	 * If the number of elements in the sources and destinations lists do not match, throw an IllegalArgumentException.
	 * 
	 * @throws IllegalArgumentException
	 */
	public static <Type> boolean isCyclic(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		// Throw an IllegalArgumentException if the size of the two lists are not same
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("sources and destinations lists do not match.");
		}

		// Construct the graph using the two lists sources and destinations
		Graph<Type> graph = constructGraph(sources, destinations);
		
		for (Vertex<Type> v : graph.getVertices().values()){		
			// Pick a start vertex			
			Stack<Vertex<Type>> visitedStack = new Stack<Vertex<Type>>();
			if (graph.hasCycle(v, visitedStack)) {
				return true;
			}
		}
		
		return false;
	}

	
	/**
	 * It uses the breath-first search algorithm to check whether there is a path 
	 * from the vertex with srcData to the vertex with dstData in the graph.
	 * 
	 * It throws IllegalArgumentException if there does not exist a vertex in the graph with srcData, and likewise for dstData.
	 * If the number of elements in the sources and destinations lists do not match, throw an IllegalArgumentException.
	 * If the srcData or dstData is null, throw an IllegalArgumentException.
	 * @throws IllegalArgumentException
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// Throw an IllegalArgumentException if the size of the two lists are not same
		if (sources.size() != destinations.size() || srcData == null || dstData == null) {
			throw new IllegalArgumentException("sources and destinations lists do not match OR invalid data input.");
		}
		
		// Construct the graph using the two lists sources and destinations
		Graph<Type> graph = constructGraph(sources, destinations);
		
		// Throw an IllegalArgumentException if graph doesn't contain srcData or dstData
		if (!graph.getVertices().keySet().contains(srcData) || !graph.getVertices().keySet().contains(dstData)) {
			throw new IllegalArgumentException("Graph doesn't contain " + srcData + " or " + dstData);
		}
		
		return graph.areConnected(graph.getVertices().get(srcData), graph.getVertices().get(dstData));
	}

	/**
	 * It uses the topographical sort algorithm to generate a sorted ordering of the vertices in the graph.
	 * It may have more than one valid ordering, and any such ordering is accepted. 
	 * It throws IllegalArgumentException if the graph contains a cycle.
	 * If the number of elements in the sources and destinations lists do not match, throw an IllegalArgumentException.
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		// Throw an IllegalArgumentException if the size of the two lists are not same
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("sources and destinations lists do not match.");
		}
		
		// Construct the graph using the two lists sources and destinations
		Graph<Type> graph = constructGraph(sources, destinations);
		
		List<Type> sortedList = new LinkedList<Type>();
		for (Vertex<Type> v : graph.getVertices().values()){

			Stack<Vertex<Type>> visitedStack = new Stack<Vertex<Type>>();
			
			// Throw an IllegalArgumentException if the graph contains a cycle
			if (graph.hasCycle(v, visitedStack)) {
				throw new IllegalArgumentException("graph contains a cycle");
			}			
			
			if (v.getIndegree() == 0) {
				sortedList.add(v.getData());
			}
		}
		
		if (sortedList.isEmpty()) {
			return sortedList;
		}
		else{
			return graph.topologicalSort(sortedList, graph);
		}
	}

	/**
	 * Construct a graph using the two data lists
	 * @param sources
	 * @param destinations
	 */
	public static <Type> Graph<Type> constructGraph(List<Type> sources, List<Type> destinations){
		Graph<Type> g = new Graph<>();
		for (int i = 0; i < sources.size(); i++) {	
			g.addEdge(sources.get(i), destinations.get(i));
		}
		return g;
	}
	
	
	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
	
	/**
	 * A method generates random directed acyclic graph
	 */
	public static void DAG(List<Integer> srcList, List<Integer> dstList, int vertexCount) {
		Random rand = new Random();
		LinkedList<Vertex<Integer>> vertices = new LinkedList<>();
		
		// generate a list of vertices, |V|.
		for (int i = 0; i < vertexCount; i++) {
			vertices.add(new Vertex<Integer>(i));
		}
		Collections.shuffle(vertices);
		

		// Connect srcV to dstV, |E|, using (some small number) * |V|
		for (int i = 0; i < vertexCount; i++) {
			Vertex<Integer> src = vertices.get(i);
			Vertex<Integer> dst = vertices.get(rand.nextInt(vertexCount));
			while (src == dst || hasConnection(dst, src)) {
				src = vertices.get(rand.nextInt(vertexCount));
				dst = vertices.get(rand.nextInt(vertexCount));
			}
			
			src.addEdge(dst);
			srcList.add(src.getData());
			dstList.add(dst.getData());
		}
	}
	
	/**
	 * Helper method to check for connection between v1 and v2, supporting DAG method
	 */
	private static boolean hasConnection(Vertex<Integer> v1, Vertex<Integer> v2) {
		LinkedList<Vertex<Integer>> queue = new LinkedList<>();
		queue.add(v1);
		while (!queue.isEmpty()) {
			Vertex<Integer> x = queue.remove();
			Iterator<Edge<Integer>> itr = x.edges();
			while (itr.hasNext()) {
				Vertex<Integer> w = itr.next().getDstVertex();
				if (w.equals(v2)) {
					return true;
				}
				if (hasConnection(w, v2)) {
					return true;
				}
			}
		}
		return false;
	}
}