package net.anotheria.communication;

import net.anotheria.communication.data.SimpleMailMessage;
import net.anotheria.communication.service.MessagingService;

public class SendMail {
	public static void main(String a[]) throws Exception{
//		BasicConfigurator.configure();
		MessagingService service = MessagingService.getInstance();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setSender("scoreshop@anotheria.net");
		mail.setSenderName("Golf-Industries OnlineShop");
		//mail.setRecipient("leon@leon-rosenberg.net");
		//mail.setRecipient("business@affairo.com");
		mail.setRecipient("rosenberg.leon@gmail.com");
		
		mail.setSubject("Test PLAIN mail");
		mail.setMessage("this is a message \n and aother lien");
		
		service.sendMessage(mail);
		
	}
}
