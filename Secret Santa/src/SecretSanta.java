import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SecretSanta {
	private ArrayList<Person> participants; 
	private static Random rand;
	private static Properties prop;
	private static Session session;
	private static String username;
	private static String password;
	
	public SecretSanta(Scanner people) {
		participants = initPeople(people);
		rand = new Random();
		mailSetup();
		match();
	}
	
	private static void mailSetup() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your email username and pw: ");
		String[] info = in.nextLine().split(",");
		username = info[0];
		password =  info[1];

        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        
        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }                });
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
	
	private void sendMail(Person p) {
		
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(p.getEmail())
            );
            message.setSubject("Secret Santa Assignments");
            String txt = String.format("Hello %s,\n\nYour assignment for Lee Hall Secret Santa is %s. "
            		+ "The spending limit is $25, so make the gift good you fucking nerd. \n\nHappy Kwanzaa,\nPatrick's bot", 
            		p.getName(), p.getPair().getName());
            message.setText(txt);

            Transport.send(message);

            System.out.println("Email Sent to " +  p.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		Scanner people = null;
		try {
			people = new Scanner(new FileInputStream(args[0]));
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
		
		SecretSanta s = new SecretSanta(people);
		//System.out.println(s.participants);
		people.close();
		
		for(Person p: s.participants)
			s.sendMail(p);
		
		System.out.println("All emails sent");
	}

}


