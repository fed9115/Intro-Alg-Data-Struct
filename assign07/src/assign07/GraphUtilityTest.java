package assign07;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphUtilityTest {

	private Random rand = new Random();
	private ArrayList<String> sources = new ArrayList<String>();
	private ArrayList<String> destinations = new ArrayList<String>();
	private ArrayList<String> sources2 = new ArrayList<String>();
	private ArrayList<String> destinations2 = new ArrayList<String>();
	private ArrayList<String> sources3 = new ArrayList<String>();
	private ArrayList<String> destinations3 = new ArrayList<String>();
	private ArrayList<String> sources4 = new ArrayList<String>();
	private ArrayList<String> destinations4 = new ArrayList<String>();
	private ArrayList<String> sources5 = new ArrayList<String>();
	private ArrayList<String> destinations5 = new ArrayList<String>();
	private ArrayList<String> sources6 = new ArrayList<String>();
	private ArrayList<String> destinations6 = new ArrayList<String>();
	private ArrayList<String> sources7 = new ArrayList<String>();
	private ArrayList<String> destinations7 = new ArrayList<String>();
	private ArrayList<String> sources8 = new ArrayList<String>();
	private ArrayList<String> destinations8 = new ArrayList<String>();
	//private ArrayList<String> sources9 = new ArrayList<String>();
	//private ArrayList<String> destinations9 = new ArrayList<String>();
	private ArrayList<String> sources10 = new ArrayList<String>();
	private ArrayList<String> destinations10 = new ArrayList<String>();
	private ArrayList<Integer> src = new ArrayList<Integer>();
	private ArrayList<Integer> dst = new ArrayList<Integer>();
//	private ArrayList<String> src = new ArrayList<String>();
//	private ArrayList<String> dst = new ArrayList<String>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph.dot", sources, destinations);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph2.dot", sources2, destinations2);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph3.dot", sources3, destinations3);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph4.dot", sources4, destinations4);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph5.dot", sources5, destinations5);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph6.dot", sources6, destinations6);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph7.dot", sources7, destinations7);
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph8.dot", sources8, destinations8);		
		GraphUtility.buildListsFromDot("src/dot_examples/examplegraph10.dot", sources10, destinations10);
//		Graph<Integer> g = GraphUtility.generateAcyclicGraph(src, dst, 100);
		Timing.generateAcyclicGraph(src, dst, 30);
//		GraphUtility.generateRandomDotFile("src/dot_examples/examplegraphtest.dot", src, dst);
//		GraphUtility.buildListsFromDot("src/dot_examples/examplegraphtest.dot", sources10, destinations10);
	}
	
	
	@Test
	void testIsCyclic() {
		System.out.println("examplegraph has a cycle: " + GraphUtility.isCyclic(sources, destinations));		
		System.out.println("examplegraph2 has a cycle: " + GraphUtility.isCyclic(sources2, destinations2));		
		System.out.println("examplegraph3 has a cycle: " + GraphUtility.isCyclic(sources3, destinations3));		
		System.out.println("examplegraph4 has a cycle: " + GraphUtility.isCyclic(sources4, destinations4));		
		System.out.println("examplegraph5 has a cycle: " + GraphUtility.isCyclic(sources5, destinations5));		
		System.out.println("examplegraph6 has a cycle: " + GraphUtility.isCyclic(sources6, destinations6));		
		System.out.println("examplegraph7 has a cycle: " + GraphUtility.isCyclic(sources7, destinations7));		
		System.out.println("examplegraph8 has a cycle: " + GraphUtility.isCyclic(sources8, destinations8));	
		//System.out.println("examplegraph9 has a cycle: " + GraphUtility.isCyclic(sources9, destinations9));			
		System.out.println("examplegraph10 as a cycle: " + GraphUtility.isCyclic(sources10, destinations10));
		

//		System.out.println(src);
//		System.out.println(dst);
////		GraphUtility.generateRandomDotFile("src/dot_examples/examplegraphtest.dot", src, dst);
//		System.out.println("test dot is cyclic: " + GraphUtility.isCyclic(src, dst));
	}
	
	@Test
	public void testAreConnected() {
//		String src = sources.get(rand.nextInt(sources.size()));
//		String dst = destinations.get(rand.nextInt(destinations.size()));
//		System.out.println("1: " + src + " " + dst);
//		System.out.println(src + " and " + dst + " are connected: " + GraphUtility.areConnected(sources, destinations, src, dst));
//		
//		String src2 = sources2.get(rand.nextInt(sources2.size()));
//		String dst2 = destinations2.get(rand.nextInt(destinations2.size()));
//		System.out.println("2: " + src2 + " " + dst2);
//		System.out.println(src2 + " and " + dst2 + " are connected: " + GraphUtility.areConnected(sources2, destinations2, src2, dst2));
//		
//		String src3 = sources3.get(rand.nextInt(sources3.size()));
//		String dst3 = destinations3.get(rand.nextInt(destinations3.size()));
//		System.out.println("3: " + src3 + " " + dst3);
//		System.out.println(src3 + " and " + dst3 + " are connected: " + GraphUtility.areConnected(sources3, destinations3, src3, dst3));
//		System.out.println("n0 and n3 are connected: " + GraphUtility.areConnected(sources3, destinations3, "n0", "n3"));
//		
//		String src4 = sources4.get(rand.nextInt(sources4.size()));
//		String dst4 = destinations4.get(rand.nextInt(destinations4.size()));
//		System.out.println("4: " + src4 + " " + dst4);
//		System.out.println(src4 + " and " + dst4 + " are connected: " + GraphUtility.areConnected(sources4, destinations4, src4, dst4));
//		System.out.println("u2 and u2" + " are connected: " + GraphUtility.areConnected(sources4, destinations4, "u2", "u2"));
//		
//		String src5 = sources5.get(rand.nextInt(sources5.size()));
//		String dst5 = destinations5.get(rand.nextInt(destinations5.size()));
//		System.out.println("5: " + src5 + " " + dst5);
//		System.out.println(src5 + " and " + dst5 + " are connected: " + GraphUtility.areConnected(sources5, destinations5, src5, dst5));
//		
//		String src6 = sources6.get(rand.nextInt(sources6.size()));
//		String dst6 = destinations6.get(rand.nextInt(destinations6.size()));
//		System.out.println("6: " + src6 + " " + dst6);
//		System.out.println(src6 + " and " + dst6 + " are connected: " + GraphUtility.areConnected(sources6, destinations6, src6, dst6));
//		
//		String src7 = sources7.get(rand.nextInt(sources7.size()));
//		String dst7 = destinations7.get(rand.nextInt(destinations7.size()));
//		System.out.println("7: " + src7 + " " + dst7);
//		System.out.println(src7 + " and " + dst7 + " are connected: " + GraphUtility.areConnected(sources7, destinations7, src7, dst7));
//		
//		String src8 = sources8.get(rand.nextInt(sources8.size()));
//		String dst8 = destinations8.get(rand.nextInt(destinations8.size()));
//		System.out.println("8: " + src8 + " " + dst8);
//		System.out.println(src8 + " and " + dst8 + " are connected: " + GraphUtility.areConnected(sources8, destinations8, src8, dst8));
//		
//		//String src9 = sources9.get(rand.nextInt(sources9.size()));
//		//String dst9 = destinations9.get(rand.nextInt(destinations9.size()));
//		//while(dst9 == src9) {
//		//	dst9 = destinations.get(rand.nextInt(destinations.size()));
//		//}
//		//System.out.println(src9 + " and " + dst9 + " are connected: " + GraphUtility.areConnected(sources9, destinations9, src9, dst9));
//		
//		String src10 = sources10.get(rand.nextInt(sources10.size()));
//		String dst10 = destinations10.get(rand.nextInt(destinations10.size()));
//		System.out.println("10: " + src10 + " " + dst10);
//		System.out.println(src10 + " and " + dst10 + " are connected: " + GraphUtility.areConnected(sources10, destinations10, src10, dst10));
//		System.out.println("j and e" + " are connected: " + GraphUtility.areConnected(sources10, destinations10, "j", "e"));
	}
	
	@Test
	public void testSort() {
//		System.out.println(GraphUtility.sort(sources, destinations));
//		System.out.println(GraphUtility.sort(sources2, destinations2));
		System.out.println(GraphUtility.sort(sources3, destinations3));
//		System.out.println(GraphUtility.sort(sources4, destinations4));
		System.out.println(GraphUtility.sort(sources5, destinations5));
//		System.out.println(GraphUtility.sort(sources6, destinations6));
		System.out.println(GraphUtility.sort(sources7, destinations7));
//		System.out.println(GraphUtility.sort(sources8, destinations8));
		//System.out.println(GraphUtility.sort(sources9, destinations9));
//		System.out.println(GraphUtility.sort(sources10, destinations10));
	}

}
