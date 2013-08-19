package net.anotheria.communication;

import net.anotheria.communication.data.MailInmemoryFileEntry;
import net.anotheria.communication.data.MultiPartMailMessage;
import net.anotheria.communication.service.MessagingService;

public class SendMultiPartMail {
	public static void main(String a[]) throws Exception{
//		BasicConfigurator.configure();
		MessagingService service = MessagingService.getInstance();
		MultiPartMailMessage mail = new MultiPartMailMessage();
		mail.setSender("registration@adoptive.de");
		mail.setRecipient("leon@leon-rosenberg.net");
//		mail.setRecipient("vkazhdan@anotheria.net");
		
		mail.setSubject("Test multipart mail");
		mail.setMessage("this is a message \n and aother lien");
		
//		mail.addFile(new MailFileEntry(new File("/Users/vkazhdan/Temp/attachment.txt"), "attachment.txt"));
		mail.addInmemoryFile(new MailInmemoryFileEntry("attachment2.txt", "Line 1\nLine 2\nLine 3".getBytes()));
		mail.addInmemoryFile(new MailInmemoryFileEntry(null, null));
		
		service.sendMessage(mail);
		
	}
}
