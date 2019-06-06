package assign02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {

	private String holder;

	private GregorianCalendar dueDate;

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		this.dueDate = null;
		this.holder = null;
	}

	public String getHolder() {
		return this.holder;
	}

	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	public void checkIn() {
		this.dueDate = null;
		this.holder = "";
	}

	public void checkOut(String holder, GregorianCalendar dueDate) {
		this.dueDate = dueDate;
		this.holder = holder;
	}

	public boolean inLibrary() {
		if (this.dueDate == null && this.holder == null)
			return true;

		return false;
	}
}
