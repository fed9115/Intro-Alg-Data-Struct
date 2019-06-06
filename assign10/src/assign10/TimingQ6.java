package assign10;

import java.util.ArrayList;
import java.util.Collections;

import static assign10.FindKLargest.*;

public class TimingQ6 {

	public static void main(String[] args) {
		int timesToLoop = 100;
		long startTime, midTime, stopTime;
		for (int n = 1000; n <= 20000; n += 1000) {
			ArrayList<Integer> al = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				al.add(i);
			}
			Collections.shuffle(al);

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) {
				// empty block
			}
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
//				findKLargestHeap(al, n / 10);
				findKLargestSort(al, n / 10);

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
