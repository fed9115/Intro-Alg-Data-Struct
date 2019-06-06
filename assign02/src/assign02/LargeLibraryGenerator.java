package assign02;

public class LargeLibraryGenerator {

	public static void main (String args[]) {
		for (int i = 0; i < 1000; i++) {
			long rand = (long)( Math.random()  * 10000000000000L);
			if (rand >= 1000000000000L)
			System.out.print(rand + "\t" + "Kyle Perry" + "\t" + "Erdi Fan" + "\n");
			
		}
	}
}
