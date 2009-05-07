package net.anotheria.communication.data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author skyball
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 */
public class SimpleMailMessage extends AbstractMailMessage  implements Serializable{
	
	private String recipient;

	public Message transformToMessage(Session session) throws AddressException, MessagingException{ 
		Message msg = new MimeMessage(session);
		try{
			msg.setFrom(new InternetAddress(getSender(), getSenderName()));
		}catch(UnsupportedEncodingException e){
			msg.setFrom(new InternetAddress(getSender()));
		}
        InternetAddress[] receivers = new InternetAddress[1];
        receivers[0] = new InternetAddress(getRecipient());
		InternetAddress[] replyTo = new InternetAddress[1];
		replyTo[0] = new InternetAddress(getReplyTo()!=null?getReplyTo():getSender());
		msg.setReplyTo(replyTo);
		msg.setRecipients(Message.RecipientType.TO, receivers);
        msg.setSubject((getSubject()!=null?getSubject():""));
        msg.setContent(getMessage()!=null?getMessage():"", "text/plain");
		return msg;
	}
	/**
	 * Gets the receipient.
	 * @return Returns a String
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Sets the receipient.
	 * @param receipient The receipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String toString(){
		return super.toString()+" to:"+recipient;
	}
}
