/**
 * This class represents a person who wants to do secret santa
 * @author Patrick
 *
 */
public class Person {	
	private String name;
	private String pn;
	private String email;
	private String pair;
	
	/**
	 * Creates a new person
	 * @param info All of the person's info in an array
	 */
	public Person(String[] info) {
		name = info[0];
		pn = info[1];
		email = info[2];
		pair = "";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the pn
	 */
	public String getPn() {
		return pn;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	public String toString() {
		return name + ", " + pn + ", " + email + ". Pair: " + pair ;
	}

	
}
