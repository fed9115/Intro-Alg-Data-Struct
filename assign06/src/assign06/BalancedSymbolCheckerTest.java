package assign06;

import static org.junit.jupiter.api.Assertions.*;
import static assign06.BalancedSymbolChecker.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class BalancedSymbolCheckerTest {

	@Test
	void testCheckFile() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
					checkFile("src/examples/Class1.java"));
			assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
					checkFile("src/examples/Class2.java"));
			assertEquals("No errors found. All symbols match.", checkFile("src/examples/Class3.java"));
			assertEquals("ERROR: File ended before closing comment.", checkFile("src/examples/Class4.java"));
			assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
					checkFile("src/examples/Class5.java"));
			assertEquals("No errors found. All symbols match.", checkFile("src/examples/Class6.java"));
			assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
					checkFile("src/examples/Class7.java"));
			assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
					checkFile("src/examples/Class8.java"));
			assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
					checkFile("src/examples/Class9.java"));
			assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
					checkFile("src/examples/Class10.java"));
			assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",
					checkFile("src/examples/Class11.java"));
			assertEquals("No errors found. All symbols match.", checkFile("src/examples/Class12.java"));
			assertEquals("No errors found. All symbols match.", checkFile("src/examples/Class13.java"));
			assertEquals("No errors found. All symbols match.", checkFile("src/examples/Class14.java"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
