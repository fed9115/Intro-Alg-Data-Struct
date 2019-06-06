package assign06;

/**
 * This method is for timing. We will examine puch(), peek(), and pop() methods
 * in our LinkedListStack and the given ArrayStack
 * 
 * @author Erdi Fan, Kyle Perry
 *
 */
public class Timer {

	public static void main(String[] args) {
		int size;
		for (size = 1000000; size <= 20000000; size += 1000000) {
			// LinkedListStack<Integer> lls = new LinkedListStack<Integer>();
			ArrayStack<Integer> as = new ArrayStack<>();

			long totalTime = 0;
			long startTime;
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}
			long timesToLoop = 1000;

			for (long i = 0; i < timesToLoop; i++) {
				as.push(size);
				startTime = System.nanoTime();

				as.pop();
				totalTime += System.nanoTime() - startTime;
			}
			double averageTime = totalTime / timesToLoop;
			System.out.println(size + "	" + averageTime);
		}
	}

}
