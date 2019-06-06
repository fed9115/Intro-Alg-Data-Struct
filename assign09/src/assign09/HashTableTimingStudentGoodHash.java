package assign09;

import java.util.ArrayList;
import java.util.Random;

import assign09.HashTable;

public class HashTableTimingStudentGoodHash {

	public static void main(String[] args) {
		int timesToLoop = 10000;
		long startTime, midTime, stopTime;
		for (int n = 1000; n <= 20000; n += 1000) {
			HashTable<StudentGoodHash, Double> hashT = new HashTable<>();
			ArrayList<StudentGoodHash> arrListStudents = new ArrayList<>();
			ArrayList<Double> gpaList = new ArrayList<>();
			// Random Number Generator
			Random rng = new Random();
			for (int i = 0; i < n; i++) {
				// generate two random strings for the first and last name of the student
				String firstName = "";
				String lastName = "";
				int firstNameLen = rng.nextInt(4) + 4;
				for (int j = 0; j < firstNameLen; j++) {
					firstName += (char) (rng.nextInt(26) + 97);
				}
				int lastNameLen = rng.nextInt(4) + 4;
				for (int j = 0; j < lastNameLen; j++) {
					lastName += (char) (rng.nextInt(26) + 97);
				}
				int uID = rng.nextInt(999999) + 1000000;
				StudentGoodHash student = new StudentGoodHash(uID, firstName, lastName);
				student.hashCode();
				double randGPA = rng.nextDouble() * 4.0;
				hashT.put(student, randGPA);
				arrListStudents.add(student);
				gpaList.add(randGPA);
			}

			// Stabilize the thread
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 2000000000) {
			}

			// Reset the number of collisions for the hashTable so we are only looking at
			// the ones that occur from calling the containsKey method below
			hashT.resetNumCollisions();

			// Run the timing test
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				//StudentGoodHash randStudent = arrListStudents.get(rng.nextInt(n));
				//hashT.containsKey(randStudent);
				Double randGPAVal = gpaList.get(rng.nextInt(n));
				hashT.containsValue(randGPAVal);
			}

			midTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				//StudentGoodHash randStudent = arrListStudents.get(rng.nextInt(n));
				Double randGPAVal = gpaList.get(rng.nextInt(n));
			}

			stopTime = System.nanoTime();

			// Calculate averages and print the results
			double averageTime = ((midTime - startTime) - (stopTime - midTime)) / (timesToLoop);
			double averageCollisions = hashT.getNumCollisions() / timesToLoop;
			System.out.println(n + "\t" + averageTime + "\t" + averageCollisions);
		}
	}
}
