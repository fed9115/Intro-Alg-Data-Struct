package comprehensive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class generates random phrases at prints them to the console. It uses a
 * .g file that specifies a set of grammar rules and then uses those grammar
 * rules to generate random phrases that follow the rules defined in the input
 * file. The file name of the grammar file to be used is the first parameter
 * given to main. The number of random phrases to generate is the second
 * parameter given to main.
 * 
 * @param, the grammar file name
 * 
 * @param, the number of random phrases to generate
 * 
 * @author Erdi Fan, Kyle Perry
 *
 */

public class RandomPhraseGenerator {

	// The data structure that will hold all of our non-terminal definitions and
	// their associated production rules
	private HashMap<String, ArrayList<String>> map;

	// The number of random phrases to be generated and printed to standard out
	private int numPhrases;

	// The name of the grammar file specifying the rules for our random phrases
	private String grammarFileName;

	// A random number generator to be used for randomly selecting a production rule
	private Random rng;

	public static void main(String[] args) {
		String fileName = args[0];
		int numPhrases = Integer.parseInt(args[1]);

//		String fileName = "grammarfileN1000.g";
//		int numPhrases = Integer.parseInt("5");

		RandomPhraseGenerator g = new RandomPhraseGenerator(fileName, numPhrases);
		g.buildMap();
		g.printRandPhrases();
	}

	/**
	 * Constructor for our random phrase generator
	 */
	public RandomPhraseGenerator(String grammarFileName, int numPhrases) {
		this.map = new HashMap<>();
		this.numPhrases = numPhrases;
		this.grammarFileName = grammarFileName;
		this.rng = new Random();
	}

	/**
	 * Takes a grammar file as input and builds a hash map by hashing the non
	 * terminal names and stores the production rules for the non terminals.
	 * 
	 * @param input
	 */
	public void buildMap() {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(grammarFileName));
			ArrayList<String> prodPoss = new ArrayList<>(100);
			while (br.ready()) {
				if (br.readLine().equals("{")) {
					prodPoss = new ArrayList<>();
					String nonTerminal = br.readLine();
					String nextLine = br.readLine();
					while (!nextLine.equals("}")) {
						prodPoss.add(nextLine);
						nextLine = br.readLine();
					}
					map.put(nonTerminal, prodPoss);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates one random phrase using the grammar rules defined for this
	 * RandomPhraseGenerator
	 * 
	 * @return a random phrase that follows the given grammar rules
	 */
	private String generateRandomPhrase() {
		StringBuilder result = new StringBuilder(1000);
		StringBuilder currPhrase = new StringBuilder(1000);
		currPhrase.append(this.randProductionRule("<start>"));
		int open = currPhrase.indexOf("<");
		while (open != -1) {
			result.append(currPhrase.substring(0, open));
			currPhrase = currPhrase.delete(0, open);
			int close = currPhrase.indexOf(">");
			currPhrase.replace(0, close + 1, this.randProductionRule(currPhrase.substring(0, close + 1)).toString());
			open = currPhrase.indexOf("<");
		}
		return result.append(currPhrase).toString();
	}

	/**
	 * Returns a randomly chosen production rule from the provided nonTerminal
	 * parameter
	 * 
	 * @param nonTerminal
	 * @return a string representation of one of the production rules for the
	 *         nonTerminal parameter
	 */
	private String randProductionRule(String nonTerminal) {
		ArrayList<String> nonTerminalArr = map.get(nonTerminal);
		return nonTerminalArr.get(rng.nextInt(nonTerminalArr.size()));
	}

	/**
	 * Prints the number of random phrases defined by by the numPhrases object field
	 */
	public void printRandPhrases() {
		try {
			BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out), 1000);
			for (int i = 0; i < numPhrases; i++) {
				log.write(this.generateRandomPhrase());
				log.newLine();
				log.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates the number of random phrases defined by the numPhrases object field
	 */
	public void generateRandPhrases() {
		for (int i = 0; i < numPhrases; i++)
			this.generateRandomPhrase();
	}
}
