import java.util.ArrayList;
import java.util.Scanner;
public class SecretSanta {
	private ArrayList<Person> participants; 
	
	public SecretSanta() {
		participants = initPeople();
		
		match();
	}
	
	private ArrayList<Person> initPeople() {
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner in = new Scanner(System.in);
		boolean end = false;
		while(end == false) {
			System.out.println("Enter the person's name, phone number, and email address in that order in a list: ");
			String[] info = in.nextLine().split(",");
			info[1] = process(info[1].replaceAll("[\\s\\-()]", ""));
			System.out.println("Is this the last person? (Y/N): ");
			Person p = new Person(info);
			people.add(p);
			if(in.nextLine().toLowerCase().equals("y"))
				end = true;	
		}
		in.close();
		return people;
		
	}
	
	private void match() {
		
	}
	
	private String process(String s) {
		return s.substring(0, 3) + "-" + s.substring(3,6) + "-" + s.substring(6);
	}

	public static void main(String[] args) {
		SecretSanta s = new SecretSanta();
	}

}


