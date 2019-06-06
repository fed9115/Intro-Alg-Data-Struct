package assign07;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Timing {

	public static void generateRandomDotFile(String filename, int vertexCount) {
	    PrintWriter out = null;
	    try {
	      out = new PrintWriter(filename);
	    } 
	    catch (IOException e) {
	      System.out.println(e);
	    }

	    Random rng = new Random();

	    // randomly construct either a graph or a digraph
	    String edgeOp = "--";
	    if (rng.nextBoolean()) {
	      out.print("di");
	      edgeOp = "->";
	    }
	    out.println("graph G {");

	    // generate a list of vertices
	    String[] vertex = new String[vertexCount];
	    for(int i = 0; i < vertexCount; i++)
	      vertex[i] = "v" + i;

	    // randomly connect the vertices using 2 * |V| edges
	    for(int i = 0; i < 2 * vertexCount; i++)
	      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp
	          + vertex[rng.nextInt(vertexCount)]);

	    out.println("}");
	    out.close();
	  }

	public static Graph<Integer> generateGraph(List<Integer> srcList, List<Integer> dstList){
		Graph<Integer> graph = new Graph<>();
		for (int i = 0; i < srcList.size(); i++) {	
			graph.addEdge(srcList.get(i), dstList.get(i));
		}
		
		return graph;
	}
	
	public static List<Integer> randIntList(int length) {
		Random rand = new Random();
		ArrayList<Integer> testList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			testList.add(rand.nextInt(length));
		}
		return testList;
	}

	/**
	 * Generates a random directed acyclic graph
	 * 
	 * @return
	 */
	public static void generateAcyclicGraph(List<Integer> srcList, List<Integer> dstList, int edgeCount) {
		Random rand = new Random();
		// generate a list of vertices, |V|. And |E| is how many times the add() method is called.
		for (int i = 0; i < edgeCount; i++) {
			int src = rand.nextInt(edgeCount);
			while (srcList.contains(src)) {
				src = rand.nextInt(edgeCount);
			}
			srcList.add(src);
		}

		// Connect srcV to dstV
		for (int i = 0; i < edgeCount; i++) {
			int src = srcList.get(i);
			int dst = src + 1 + rand.nextInt(edgeCount);
			while (dstList.contains(dst) || dst == src) {
				dst = src + 1 + rand.nextInt(edgeCount);
			}
			dstList.add(dst);
		}
	}

	public static void main(String[] args) {
		
		// Do 1000 lookups and use the average running time.
		int timesToLoop = 100;
		long startTime, midpointTime, stopTime;

//		Random rand = new Random();
		// For each problem size n...
		for (int n = 1000; n <= 20000; n += 1000) {
			// Integers lists to be built up and tested as parameters in timing
			List<Integer> srcList = new LinkedList<Integer>();
			List<Integer> dstList = new LinkedList<Integer>();

			generateAcyclicGraph(srcList, dstList, n);
			
			// Generate two random lists to be tested
//			generateRandomDotFile("src/assign07/dotexample.dot", srcList, dstList, n);
//			generateRandomDotFile(srcList, dstList, n);
//			Set<Vertex<Integer>> pathSet = new HashSet<Vertex<Integer>>();
//			Set<Vertex<Integer>> visitedSet = new HashSet<Vertex<Integer>>();
//			srcList = randIntList(n);
//			dstList = randIntList(n);
//			int srcDataInt = srcList.get(rand.nextInt(n));
//			int dstDataInt = dstList.get(rand.nextInt(n));

			// Pick up two data we need to use in areConnected method
//			Integer srcDataInt = srcList.get(rand.nextInt(n));
//			Integer dstDataInt = dstList.get(rand.nextInt(n));

			// First, spin computing stuff until one second has gone by. This allows this
			// thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}
			// Now, run the test.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
//				GraphUtility.dfs(g.getVertices().get(srcList.get(n / 4)), pathSet, visitedSet);
//				GraphUtility.hasCycle(g);
//				GraphUtility.isCyclic(srcList, dstList);
//				GraphUtility.areConnected(srcList, dstList, srcDataInt, dstDataInt);
				GraphUtility.sort(srcList, dstList);
//				g.topologicalSort(srcList, g);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) {
				generateGraph(srcList, dstList);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop from the cost of
			// running the loop and generate the random strings. Average it over the number
			// of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
			System.out.println(averageTime / 1000000);
//			System.out.println("It took " + averageTime / 1000000 + "ms to run the method containing " + n);
		}
	}

}
