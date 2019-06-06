package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the MathVector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a
 * correctly implemented equals method. Be careful of false positives (i.e.,
 * tests that pass because your equals method incorrectly returns true).
 * 
 * @author Erin Parker & Erdi Fan
 * @version January 9, 2019
 */
class MathVectorJUnitTester {

	private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][] { { 3, 1, 2 } });

		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][] { { 3 }, { 1 }, { 2 } });

		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][] { { 1, 1, 1 } });

		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][] { { 4, 2, 3 } });

		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][] { { -11 }, { 2.5 }, { 36 }, { -3.14 }, { 7.1 } });
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][] { { 3, 1, 2 } })));
	}

	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
		double arr[][] = { { 1, 2 }, { 3, 4 } };
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(arr);
		});
		// NOTE: The code above is an example of a lambda expression. See Lab 1 for more
		// info.
	}

	@Test
	public void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}

	@Test
	public void addRowAndColVectors() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(colVec);
		});
		// NOTE: The code above is an example of a lambda expression. See Lab 1 for more
		// info.
	}

	@Test
	public void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));
	}

	@Test
	public void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);
	}

	@Test
	public void smallRowVectorMagnitude() {
		double vecMagnitude = rowVec.magnitude();
		assertEquals(vecMagnitude, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));
	}

	@Test
	public void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double magnitude = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec
				.equals(new MathVector(new double[][] { { 3.0 / magnitude, 1.0 / magnitude, 2.0 / magnitude } })));
	}

	@Test
	public void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(colVec.toString(), resultStr);
	}

	@Test
	public void vectorWithoutARow() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(new double[0][3]);
		});
	}

	@Test
	public void vectorWithoutAColumn() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(new double[3][0]);
		});
	}

	@Test
	public void vectorUnequalsSomethingOtherThanVector() {
		assertFalse(rowVec.equals(1));
	}

	@Test
	public void vectorUnequalsAnotherVectorWithDifferentNumberOfRow() {
		assertFalse(rowVec.equals(new MathVector(new double[][] { { 3, 1 } })));
	}

	@Test
	public void vectorUnequalsAnotherVectorWithDifferentNumberOfCol() {
		assertFalse(colVec.equals(new MathVector(new double[][] { { -11 }, { 2.5 }, { 36 }, { -3.14 } })));
	}

	@Test
	public void transposeSmallColVector() {
		MathVector transposeResult = rowVecTranspose.transpose();
		assertTrue(transposeResult.equals(rowVec));
	}

	@Test
	public void addRowVectorsWithDifferentRowNumbers() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(new MathVector(new double[][] { { 3, 1 } }));
		});
	}

	@Test
	public void addRowVectorsWithDifferentColNumbers() {
		assertThrows(IllegalArgumentException.class, () -> {
			colVec.add(new MathVector(new double[][] { { -11 }, { 2.5 }, { 36 }, { -3.14 } }));
		});
	}

	@Test
	public void addSmallColVectors() {
		MathVector addResult = colVec.add(colVec);
		assertTrue(addResult.equals(
				new MathVector(new double[][] { { -11 * 2 }, { 2.5 * 2 }, { 36 * 2 }, { -3.14 * 2 }, { 7.1 * 2 } })));
	}

	@Test
	public void dotProductOfRowVectorsWithDifferentRowNumbers() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.dotProduct(new MathVector(new double[][] { { 3, 1 } }));
		});
	}

	@Test
	public void dotProductOfRowVectorsWithDifferentColNumbers() {
		assertThrows(IllegalArgumentException.class, () -> {
			colVec.dotProduct(new MathVector(new double[][] { { -11 }, { 2.5 }, { 36 }, { -3.14 } }));
		});
	}

	@Test
	public void dotProductSmallColVectors() {
		double dotProdResult2 = colVec.dotProduct(colVec);
		assertEquals(dotProdResult2, -11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.14 * -3.14 + 7.1 * 7.1);
	}

	@Test
	public void smallColVectorMagnitude() {
		double vecMagnitude2 = colVec.magnitude();
		assertEquals(vecMagnitude2, Math.sqrt(-11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.14 * -3.14 + 7.1 * 7.1));
	}

	@Test
	public void smallColVectorNormalize() {
		MathVector normalVec2 = colVec.normalize();
		double magnitude2 = Math.sqrt(-11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.14 * -3.14 + 7.1 * 7.1);
		assertTrue(normalVec2.equals(new MathVector(new double[][] { { -11 / magnitude2 }, { 2.5 / magnitude2 },
				{ 36 / magnitude2 }, { -3.14 / magnitude2 }, { 7.1 / magnitude2 } })));
	}

	@Test
	public void smallRowVectorToString() {
		String resultStr2 = "3.0 1.0 2.0";
		assertEquals(rowVec.toString(), resultStr2);
	}
}