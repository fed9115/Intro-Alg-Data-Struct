package lab03;

/**
 * This class contains code for completing the Lab 3: Debugging activities.
 * 
 * @author CS 2420 course staff
 * @version January 25, 2019
 */
public class Part3 {

	public static boolean checkPassword(String password) {
		String[] tokens = password.split(" ");
		int a = 0, b = 1;
		for(int i = 0; i < tokens.length; i++) {
			a += b;
			int currentToken = Integer.parseInt(tokens[i]);
			if(a != currentToken) 
				return false;
			b += 2;
		}
		return b == 11;
	}	
}