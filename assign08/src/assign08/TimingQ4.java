package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

// DO NOT SUBMIT THIS CLASS!!!
public class TimingQ4 {

	public static void main(String[] args) {
		int timesToLoop = 10000;
		long startTime, midTime, stopTime;
		for (int n = 1000; n <= 20000; n += 1000) {
			TreeSet<Integer> ts = new TreeSet<>();
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			ArrayList<Integer> al = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				al.add(i);
			}
			Collections.shuffle(al);
			ts.addAll(al);
			// bst.addAll(al);
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
				// empty block
			}
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				// bst.contains(n);
				ts.contains(n);
			}
			midTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();
			double averageTime = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;
			System.out.println(averageTime);
		}
	}

}
