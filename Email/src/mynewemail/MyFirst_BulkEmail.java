package mynewemail;
import java.util.HashSet;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyFirst_BulkEmail {

	public static  void main(String[] args){
		//get a user input
		@SuppressWarnings("resource")
		Scanner main=new Scanner(System.in);
		
		// create a list of email
		HashSet<String> emails = new HashSet<String>();
		
		//crete how many emails to send
		int length;
		System.out.println("enter the number of email id to send ");
		length=main.nextInt();
		main.nextLine();
		System.out.println("enter the email ids");
		for(int count=0;count<length;count++) {
			emails.add(main.nextLine());
		}
//		emails.add("hasanabdulmuhaimeen93@gmail.com");
//		emails.add("abdulmuhaimeen2000@gmail.com");
//		emails.add("madhavan23052@gmail.com");
//		emails.add("ksasi3066@gmail.com");
		
		//subject which is to be sent
		String subject = "this is bulk email program using java (Eclipse IDE)";
		
		// message which is to be sent
		String text ="Assalamualaikum,\n\tI am hasan abdul muhaimeen, this mail was sent through a Java Program using Javamail APIs using Eclipse IDE.....\n\n  My Github Link:https://github.com/muhaimeen12 ";
		
		// send the email to multiple recipients
		sendBulkEmail(subject,emails ,text);
	}

	//main method to run all codes
	private static void sendBulkEmail(final String subject, final HashSet<String> emailsToAddresses, final String text){
		
		// from email address
		final String username = "muhaimeen227@gmail.com";

		// make sure you put your correct password
		final String password = "Zxc@#123";

		// SMTP email server
		final String smtpHost = "smtp.gmail.com";
		
		//SMTP email port
		final int port=587;

		// will put some properties for SMTP configurations
		Properties props = new Properties();

		// set properties in props
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port",port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable","true");
		
		//-this is for debugging process....like,show how code to be executed...
		props.put("mail.debug", "true");
	

		// authenticate using your email and password and on successful
		// create the session
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		String emails =null;

		try {
			// create new message
			Message message = new MimeMessage(session);

			// set the from 'email address'
			message.setFrom(new InternetAddress(username));

			// set email subject
			message.setSubject(subject);

			//set email text
			message.setText(text);

//			// form all email in a comma separated string
			StringBuffer sb = new StringBuffer();
			int i=0;
			for(String email:emailsToAddresses) {
				sb.append(email);
				i++;
				if(emailsToAddresses.size()>i) {
					sb.append(" ,");
				}
				
			}
			
			emails = sb.toString();

			// set 'to email address'
			// you can set also BCC or CC for recipient type
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(sb.toString()));

			System.out.println("Sending Email to " + emails + " from " + username + " with Subject - " + subject);

			// send the email
			Transport.send(message,username,password);

			//sending and failed notification for my confirmations.
			System.out.println("Email successfully sent to " + emails);
			
			//any errors occurs in between of the running codes....its indicate the error exactly.
		} catch (MessagingException e) {
			System.out.println("Email sending failed to " + emails);
			System.out.println(e);
		}
	}

	
	


}