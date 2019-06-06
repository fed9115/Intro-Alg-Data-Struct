package assign10;

import java.util.ArrayList;

public class TimingQ3 {

	public static void main(String[] args) {
		int timesToLoop = 100000;
		long startTime, midTime, stopTime;
		for (int n = 100000; n <= 2000000; n += 100000) {
			ArrayList<Integer> al = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				al.add(i);
			}
			BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>(al);
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) {
				// empty block
			}
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				bmh.add(n + 1);// worst case adding
//				bmh.add(n / 2);// average case adding
//				bmh.peek();
				bmh.extractMax();

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
