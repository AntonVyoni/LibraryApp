package application;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

	String email;
	String title;
	String output = "";

	LoanModel lm = new LoanModel();
	
	public SendEmail(String email, String title) {
	    this.email = email;
	    this.title = title;
	  }
	
	public SendEmail() {
		
	}
	
	public void eMail(String email, String title) {

		String to = email;
		String from = "authenticreallibrary@gmail.com";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("authenticreallibrary@gmail.com", "qwerty123asdf");
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Försenade artiklar på det Riktiga Biblioteket™");
			

			
			message.setText("Du har försening på följande titel: " + title);
			Transport.send(message);
			System.out.println("Sent message successfully!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void getEmails() {
//		for(int i = 0; i < lm.lateReturnEmail.size(); i++) {
//
////			String lateEmail = lm.lateReturnEmail.get(i);
////			lm.getLateTitle(lateEmail);
////			System.out.println(lm.getLateTitle(lateEmail));
////			lm.getLateTitle(lm.lateReturnTitle.get(i));
//			eMail(lm.lateReturnEmail.get(i));
//			
//		}
//	}
//	
//	public void getTitles() {
//		for (int i = 0; i < lm.getLateTitle(email).size(); i++) {
//			output += lm.getLateTitle(email).get(i);
//		}
//		System.out.println(output);
//	}

	
}
