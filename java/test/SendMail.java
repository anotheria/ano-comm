package test;

import org.apache.log4j.BasicConfigurator;

import net.anotheria.communication.data.SimpleMailMessage;
import net.anotheria.communication.service.MessagingService;

public class SendMail {
	public static void main(String a[]) throws Exception{
		BasicConfigurator.configure();
		MessagingService service = MessagingService.getInstance();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setSender("registration@adoptive.de");
		//mail.setRecipient("leon@leon-rosenberg.net");
		//mail.setRecipient("business@affairo.com");
		mail.setRecipient("rosenberg.leon@gmail.com");
		
		mail.setSubject("Test PLAIN mail");
		mail.setMessage("this is a message \n and aother lien");
		
		service.sendMessage(mail);
		
	}
}
