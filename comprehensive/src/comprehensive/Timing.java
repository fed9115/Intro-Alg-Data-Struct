package comprehensive;

public class Timing {

	public static void main(String[] args) {
		int timesToLoop = 1000;
		long startTime, stopTime;
		
		for (int n = 1000; n <= 20000; n += 1000) {
			//GrammarFileGenerator(int numNonTerminals, int numProdRulesPerNonTerm, int numNonTermPerProdRule, String fileName)
			GrammarFileGenerator gen = new GrammarFileGenerator(10,5,0, "grammarfileN" + n);
			gen.generateGrammar();
			
			//Create a RandomPhraseGenerator and the backing data structure
			RandomPhraseGenerator rpg = new RandomPhraseGenerator("grammarFileN" + n + ".g", n);
			rpg.buildMap();
			
			// Stabilize the thread
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) {
				// empty block
			}

			// Start the timing
			double totalTime = 0;
			for (int i = 0; i < timesToLoop; i++) {

				startTime = System.nanoTime();
				// Generate N random phrases
				rpg.generateRandPhrases();
				stopTime = System.nanoTime();
				totalTime += (stopTime - startTime);

			}

			double averageTime = totalTime / timesToLoop;
			System.out.println(n + "\t" + averageTime);
		}

	}

}
