package assign02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

	private Type holder;

	private GregorianCalendar dueDate;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		this.dueDate = null;
		this.holder = null;
	}

	public Type getHolder() {
		return this.holder;
	}

	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	public void checkIn() {
		this.dueDate = null;
		this.holder = null;
	}

	public void checkOut(Type holder, GregorianCalendar dueDate) {
		this.dueDate = dueDate;
		this.holder = holder;
	}

	public boolean inLibrary() {
		if (this.dueDate == null && this.holder == null)
			return true;

		return false;
	}
}
