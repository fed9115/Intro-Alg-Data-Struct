package assign06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Erin Parker && Kyle Perry, and Erdi Fan
 * @version v1.9260.817
 */
public class BalancedSymbolChecker {

	/**
	 * Generates a message indicating whether the input file has unmatched symbols.
	 * (Use the helper methods below for constructing messages.)
	 * 
	 * @param filename - name of the input file to check
	 * @return a message indicating whether the input file has unmatched symbols
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static String checkFile(String filename) throws FileNotFoundException {

		File f = new File(filename);
		// Check if there is a file
		if (!f.exists())
			throw new FileNotFoundException();

		// Create the stack for checking and the scanner to pick up each line
		LinkedListStack<Character> baseStack = new LinkedListStack<>();
		Scanner scn = new Scanner(f);

		// Set some flags for some special cases, e.g. Two kinds of comments are allowed
		// (// and /* ... */), and we will ignore any symbols contained in a comment.
		// Furthermore, we will also ignore symbols contained in string and character
		// literals.
		boolean isNotComment = true, isNotString = true, isNotChar = true;

		int lineNum = 0;

		// Pull out each line to check
		while (scn.hasNextLine()) {

			String currLine = scn.nextLine();
			lineNum++;

			// Note down how many columns are there in the current line
			int lineLength = currLine.length();

			for (int colNum = 0; colNum < lineLength; colNum++) {

				// Check the character one by one
				char currChar = currLine.charAt(colNum);

				// Check if there is a "//" comment
				if (colNum < lineLength - 1 && currChar == '/' && currLine.charAt(colNum + 1) == '/') {
					break;
				}

				// Check if there is a "/* ... */" comment
				if (colNum < lineLength - 1 && currChar == '/' && currLine.charAt(colNum + 1) == '*') {
					isNotComment = false;

				}
				if (!isNotComment && currChar == '*' && currLine.charAt(colNum + 1) == '/') {
					isNotComment = true;
				}

				// Check if there is a string literal
				if (colNum < lineLength && isNotComment && currChar == '\"' && currLine.charAt(colNum - 1) != '\\') {
					// Switch the flag status
					if (isNotString) {
						isNotString = false;
					} else {
						isNotString = true;
					}
				}

				// Check if there is a character literal
				if (colNum < lineLength && isNotComment && currChar == '\'' && currLine.charAt(colNum - 1) != '\\') {
					// Switch the flag status
					if (isNotChar) {
						isNotChar = false;
					} else {
						isNotChar = true;
					}
				}

				// Check for '(', '{', '['
				if (isNotComment && isNotString && isNotChar
						&& (currChar == '(' || currChar == '{' || currChar == '[')) {
					baseStack.push(currChar);
				} else if (isNotComment && isNotString && isNotChar
						&& (currChar == ')' || currChar == '}' || currChar == ']')) {
					if (baseStack.size() == 0)
						return unmatchedSymbol(lineNum, colNum + 1, currChar, ' ');

					char popChar = (char) baseStack.pop();

					if (popChar == '(' && currChar != ')')
						return unmatchedSymbol(lineNum, colNum + 1, currChar, ')');
					if (popChar == '{' && currChar != '}')
						return unmatchedSymbol(lineNum, colNum + 1, currChar, '}');
					if (popChar == '[' && currChar != ']')
						return unmatchedSymbol(lineNum, colNum + 1, currChar, ']');
				}
			}
		}

		if (!isNotComment)
			return unfinishedComment();
		if (baseStack.isEmpty())
			return allSymbolsMatch();
		else {
			// Create an ArrayList to contain the last elements in the base stack if any
			ArrayList<Character> popChars = new ArrayList<>();
			for (int i = 0; i < baseStack.size(); i++) {
				popChars.add((Character) baseStack.pop());
			}
			for (int j = 0; j < popChars.size(); j++) {
				if (popChars.get(j) == '(')
					return unmatchedSymbolAtEOF(')');
				if (popChars.get(j) == '{')
					return unmatchedSymbolAtEOF('}');
				if (popChars.get(j) == '[')
					return unmatchedSymbolAtEOF(']');
			}
		}

		return null;
	}

	/**
	 * Use this error message in the case of an unmatched symbol.
	 * 
	 * @param lineNumber     - the line number of the input file where the matching
	 *                       symbol was expected
	 * @param colNumber      - the column number of the input file where the
	 *                       matching symbol was expected
	 * @param symbolRead     - the symbol read that did not match
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
	}

	/**
	 * Use this error message in the case of an unmatched symbol at the end of the
	 * file.
	 * 
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Use this error message in the case of an unfinished comment (i.e., a file
	 * that ends with an open /* comment).
	 * 
	 * @return the error message
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Use this message when no unmatched symbol errors are found in the entire
	 * file.
	 * 
	 * @return the success message
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}