package net.anotheria.communication;

import net.anotheria.communication.data.HtmlMailMessage;
import net.anotheria.communication.service.MessagingService;

import org.apache.log4j.BasicConfigurator;

public class SendHtmlMail {
	public static void main(String a[]) throws Exception{
		BasicConfigurator.configure();
		MessagingService service = MessagingService.getInstance();
		HtmlMailMessage mail = new HtmlMailMessage();
		mail.setSender("registration@adoptive.de");
		mail.setRecipient("leon@leon-rosenberg.net");
		
		mail.setSubject("Test HTML mail");
		mail.setMessage("this is a message \n and aother lien");
		
		
		mail.setHtmlContent("<html><body><h1>Hurra</h1><a href=\"http://www.google.de\">Link</a></body></html>");
		mail.setPlainTextContent("this is a plain message \n and aother lien");
	
		
		service.sendMessage(mail);
		
	}
}
