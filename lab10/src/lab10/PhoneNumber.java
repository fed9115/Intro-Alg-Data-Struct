package lab10;

import java.util.Random;

/**
 * This class represents a phone number.
 * 
 * E.g., for phone number 801-581-8224, the areaCode is 801, the trunk is 581,
 * and the rest is 8224.
 * 
 * @author Erin Parker
 * @version March 22, 2019
 */
public class PhoneNumber implements Comparable<PhoneNumber> {

	private String areaCode;

	private String trunk;

	private String rest;

	/**
	 * Creates a phone number object from given strings for area code, trunk, and
	 * rest.
	 * 
	 * @param areaCode
	 * @param trunk
	 * @param rest
	 */
	public PhoneNumber(String areaCode, String trunk, String rest) {
		this.areaCode = areaCode;
		this.trunk = trunk;
		this.rest = rest;
	}

	/**
	 * Creates a phone number object by parsing each of the three pieces from one
	 * "row" of a .csv file. In a such file, field are separated by commas (e.g.,
	 * "801,581,8224).
	 * 
	 * @param rowOfCsvFile - string to be parsed
	 * @throws IllegalArgumentException if .csv file is not formatted as expected
	 */
	public PhoneNumber(String rowOfCsvFile) throws IllegalArgumentException {
		this.areaCode = "";
		this.trunk = "";
		this.rest = "";

		String errorMsg = "CSV file must be 3-digit area code, 3-digit trunk, 4-digit rest.";
		
		try {
			this.areaCode = rowOfCsvFile.substring(0, 3);
			this.trunk = rowOfCsvFile.substring(4, 7);
			this.rest = rowOfCsvFile.substring(8, 12);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(errorMsg);
		}

		if (rowOfCsvFile.charAt(3) != ',' || rowOfCsvFile.charAt(7) != ',')
			throw new IllegalArgumentException(errorMsg);
	}
	
	/**
	 * Creates a phone number with random strings for area code, trunk, and rest.
	 */
	public PhoneNumber() {
		Random rng = new Random();
		
		int value = rng.nextInt(900) + 100;   // random integer from 100 to 999
		this.areaCode = value + "";
		
		value = rng.nextInt(900) + 100;   // random integer from 100 to 999
		this.trunk = value + "";
		
		value = rng.nextInt(9000) + 1000;   // random integer from 1000 to 9999
		this.rest = value + "";
	}

	/**
	 * Two phone numbers are considered equal if they have the same area code,
	 * trunk, and remaining numbers.
	 * 
	 * @param other - the object begin compared with this phone number
	 * @return true if other object is a PhoneNumer type and is equal to this phone
	 *         number, false otherwise
	 */
	public boolean equals(Object other) {
		if (!(other instanceof PhoneNumber))
			return false;

		PhoneNumber rhs = (PhoneNumber) other;
		PhoneNumber lhs = this;

		return lhs.areaCode.equals(rhs.areaCode) && lhs.trunk.equals(rhs.trunk) && lhs.rest.equals(rhs.rest);
	}

	/**
	 * This phone number is "smaller" than the other if its full 10-character string
	 * comes lexicographically before other's. E.g., "1234567890" is "smaller" than
	 * "1244567890"
	 */
	public int compareTo(PhoneNumber other) {
		String thisPhoneNum = areaCode + trunk + rest;
		String otherPhoneNum = other.areaCode + other.trunk + other.rest;

		return thisPhoneNum.compareTo(otherPhoneNum);
	}

	/**
	 * Generates a hash code for this phone number (used by HashMap and other
	 * hash table implementations).
	 * 
	 * As required, two equal phone numbers have the same hash code.
	 */
	public int hashCode() {
		return Integer.parseInt(areaCode) * 10000 + Integer.parseInt(trunk) * 100 + Integer.parseInt(rest);
	}

	/**
	 * Returns a textual representation of this phone number.
	 */
	public String toString() {
		return "(" + areaCode + ") " + trunk + "-" + rest;
	}
}