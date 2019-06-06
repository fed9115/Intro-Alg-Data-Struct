package comprehensive;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class GrammarFileGenerator {

	// The number of non-terminals in the grammar
	private int numNonTerminals;

	// The number of production rules per non-terminal
	private int numProdRulesPerNonTerm;

	// The desired average number of non-terminals per production rule
	private int avgNumNonTermPerProdRule;

	// The name of the file to save the grammar to
	private String fileName;

	// An arrayList of all the non-terminals
	private ArrayList<String> nonTermList;

	// Random number generator to be used for generating random terminals
	private Random rng;

	/**
	 * Constructor
	 * 
	 * @param numNonTerminals
	 * @param numProdRulesPerNonTerm
	 * @param numNonTermPerProdRule
	 * @param fileName
	 */
	public GrammarFileGenerator(int numNonTerminals, int numProdRulesPerNonTerm, int avgNumNonTermPerProdRule,
			String fileName) {
		this.numNonTerminals = numNonTerminals;
		this.numProdRulesPerNonTerm = numProdRulesPerNonTerm;
		this.avgNumNonTermPerProdRule = avgNumNonTermPerProdRule;
		this.fileName = fileName;
		this.nonTermList = new ArrayList<>(numNonTerminals);
		this.rng = new Random();
	}

	/**
	 * Creates a grammar file according to the guidelines given by the object fields
	 */
	public void generateGrammar() {
		// Create n non-terminal definitions and add them to our nonTermList
		nonTermList.add("<start>");
		for (int i = 0; i < numNonTerminals; i++) {
			nonTermList.add("<" + i + ">");
		}

		try {
			// Create the file writer
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("/Users/hathbutterfly/eclipse-workspace/comprehensive" + fileName + ".g"));
			// Create and write to the file all of the non-terminals and their production
			// rules from the nonTermList
			for (String nonTerm : nonTermList) {
				writer.append('{');
				writer.newLine();
				writer.append(nonTerm);
				writer.newLine();
				// Generate numProdRulesPerNonTerm production rules
				for (int i = 0; i < numProdRulesPerNonTerm; i++) {
					writer.append(generateProdRule());
					writer.newLine();
				}
				writer.append('}');
				writer.newLine();
				writer.newLine();
			}
			// Close the file writer
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Generates a production rule with the specified number of non-terminals in the
	 * rule
	 * 
	 * @return
	 */
	private String generateProdRule() {
		StringBuilder prodRule = new StringBuilder(100);
		int nonTerminals = 0;
		// Add something to the prod rule to guarantee it's not blank
		prodRule.append(rng.nextInt(10000));
		prodRule.append(' ');
		while (nonTerminals < avgNumNonTermPerProdRule) {
			// Choose randomly whether to add a terminal or non terminal to the production
			// rule
			if (Math.random() < 0.85) {
				prodRule.append(rng.nextInt(10000));
				prodRule.append(' ');
			} else {
				prodRule.append(nonTermList.get(rng.nextInt(nonTermList.size())));
				prodRule.append(' ');
				nonTerminals++;
			}
		}
		// Delete the last space so there are no extraneous spaces at the end of the
		// prod rule
		prodRule.deleteCharAt(prodRule.length() - 1);
		return prodRule.toString();
	}
}
