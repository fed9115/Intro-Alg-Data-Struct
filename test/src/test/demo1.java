package test;

public class demo1 {
	class Person {
		public Person() {

		}

		public String getAddress() {
			return "Person getAddress";
		}
	}

	class Employee extends Person {
		public Employee() {

		}

		public double getSalary() {
			return 100.99;
		}
	}

	class Student extends Person {
		public Student() {

		}

		public String toString() {
			return "Student toString";
		}
	}

	class GradStudent extends Student {
		public GradStudent() {

		}

		public boolean equals(Object other) {
			return true;
		}
	}

	public void main(String[] args) {
		Employee e = new Employee();
		Student s = new GradStudent();
		Person p = e;
		Object o = s;

//		p.getSalary();
//		s.toString();
//		o.getA
	}
}
