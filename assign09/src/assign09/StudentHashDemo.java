package assign09;

/**
 * This class demonstrates how to use a hash table to store key-value pairs.
 * 
 * @author Erin Parker
 * @version March 20, 2019
 */
public class StudentHashDemo {

	public static void main(String[] args) {

		StudentBadHash alan = new StudentBadHash(1019999, "Alan", "Turing");
		StudentBadHash ada = new StudentBadHash(1004203, "Ada", "Lovelace");
		StudentBadHash edsgar = new StudentBadHash(1010661, "Edsgar", "Dijkstra");
		StudentBadHash grace = new StudentBadHash(1019941, "Grace", "Hopper");

		HashTable<StudentBadHash, Double> studentGpaTable = new HashTable<StudentBadHash, Double>();

		studentGpaTable.put(alan, 3.2);
		studentGpaTable.put(ada, 3.5);
		studentGpaTable.put(edsgar, 3.8);
		studentGpaTable.put(grace, 4.0);

		for (MapEntry<StudentBadHash, Double> e : studentGpaTable.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + ".");

		StudentMediumHash kyle = new StudentMediumHash(1019999, "Kyle", "Perry");
		StudentMediumHash joelle = new StudentMediumHash(1004203, "Joelle", "Perry");
		StudentMediumHash janice = new StudentMediumHash(1010661, "Janice", "Nilsson");
		StudentMediumHash heather = new StudentMediumHash(1019941, "Heather", "Moore");

		HashTable<StudentMediumHash, Double> studentGpaTable2 = new HashTable<StudentMediumHash, Double>();

		studentGpaTable2.put(kyle, 3.2);
		studentGpaTable2.put(joelle, 3.5);
		studentGpaTable2.put(janice, 3.8);
		studentGpaTable2.put(heather, 4.0);

		for (MapEntry<StudentMediumHash, Double> e : studentGpaTable2.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + ".");

		StudentGoodHash mike = new StudentGoodHash(1019999, "Mike", "Moore");
		StudentGoodHash eric = new StudentGoodHash(1004203, "Eric", "Moore");
		StudentGoodHash seth = new StudentGoodHash(1010661, "Seth", "Moore");
		StudentGoodHash remi = new StudentGoodHash(1019941, "Remington", "Moore");

		HashTable<StudentGoodHash, Double> studentGpaTable3 = new HashTable<StudentGoodHash, Double>();

		studentGpaTable3.put(mike, 3.2);
		studentGpaTable3.put(eric, 3.5);
		studentGpaTable3.put(seth, 3.8);
		studentGpaTable3.put(remi, 4.0);

		for (MapEntry<StudentGoodHash, Double> e : studentGpaTable3.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + ".");
	}
}