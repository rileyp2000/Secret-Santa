import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class SecretSanta {
	private ArrayList<Person> participants; 
	private static Random rand;
	
	public SecretSanta(Scanner people) {
		participants = initPeople(people);
		rand = new Random();
		match();
	}
	
	private ArrayList<Person> initPeople(Scanner p) {
		ArrayList<Person> people = new ArrayList<Person>();
		
		while(p.hasNext()) {
			String[] info = p.nextLine().split(",");
			info[2] = process(info[2].replaceAll("[\\s\\-()]", ""));
			people.add(new Person(info));
		}
		p.close();
		return people;
		
	}
	
	private static int random(ArrayList<Person> p) {
		return rand.nextInt(p.size());
	}
	
	private void match() {
		ArrayList<Person> pCopy = deepCopy(participants);
		for(Person p: participants) {
			int pair = random(pCopy);
			if(p.equals(pCopy.get(pair))) {
				int p2 = random(pCopy);
				while(pair == p2)
					p2 = random(pCopy);
				pair = p2;
			}
			
			p.setPair(pCopy.remove(pair));
				
		}
	}
	
	private ArrayList<Person> deepCopy(ArrayList<Person> peeps){
		ArrayList<Person> pCopy = new ArrayList<Person>();
		for(Person p : peeps)
			pCopy.add((Person) p);
		return pCopy;
	}
	
	private String process(String s) {
		return s.substring(0, 3) + "-" + s.substring(3,6) + "-" + s.substring(6);
	}

	public static void main(String[] args) {
		Scanner people = null;
		try {
			people = new Scanner(new FileInputStream(args[0]));
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
		
		SecretSanta s = new SecretSanta(people);
		System.out.println(s.participants);
		people.close();
	}

}


