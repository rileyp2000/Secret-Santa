/**
 * This class represents a person who wants to do secret santa
 * @author Patrick
 *
 */
public class Person {	
	private String name;
	private String pn;
	private String email;
	private Person pair;
	
	/**
	 * Creates a new person
	 * @param info All of the person's info in an array
	 */
	public Person(String[] info) {
		name = info[0];
		email = info[1];
		pn = info[2];
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
	
	/**
	 * Gets the secret santa pair for this person
	 * @return The secret santa pair for this person
	 */
	public Person getPair() {
		return pair;
	}
	
	/**
	 * Sets the pair for a person
	 * @param p New pair for this person
	 */
	public void setPair(Person p) {
		pair = p;
	}
	
	/**
	 * Converts a person to a String
	 */
	public String toString() {
		return name + ", " + pn + ", " + email + ". Pair: " + pair.getName() + "\n" ;
	}
	
	public boolean equals(Object o) {
		Person p = (Person)o;
		
		return this.getName().equals(p.getName()) && this.getEmail().equals(p.getEmail()) && this.getPn().equals(p.getPn());
	}

	
}
