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
 *         To change this generated comment edit the template variable "typecomment": Window>Preferences>Java>Templates.
 */
public class SimpleMailMessage extends AbstractMailMessage implements Serializable {

	private static final String MIME_TYPE_PREFIX = "text/plain; charset=";
	
	private static final long serialVersionUID = -391021925964562176L;
	private String recipient;

	public SimpleMailMessage(){
		super();
	}
	
	public SimpleMailMessage(String aContentEncoding){
		super(aContentEncoding);
	}
	
	public Message transformToMessage(Session session) throws AddressException, MessagingException {
        ANOMimeMessage msg = new ANOMimeMessage(session);
//		msg.setHeader("Content-Type", getPlainContentType());
		try {
			msg.setFrom(new InternetAddress(getSender(), getSenderName(), getContentEncoding()));
		} catch (UnsupportedEncodingException e) {
			msg.setFrom(new InternetAddress(getSender()));
		}
		InternetAddress[] receivers = new InternetAddress[1];
		receivers[0] = new InternetAddress(getRecipient());
		InternetAddress[] replyTo = new InternetAddress[1];
		replyTo[0] = new InternetAddress(getReplyTo() != null ? getReplyTo() : getSender());
		msg.setReplyTo(replyTo);
		msg.setRecipients(Message.RecipientType.TO, receivers);
		msg.setSubject((getSubject() != null ? getSubject() : ""), getContentEncoding());
		msg.setContent(getMessage() != null ? getMessage() : "", getPlainContentType());
		

		
		return msg;
	}

	/**
	 * Gets the receipient.
	 * 
	 * @return Returns a String
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Sets the receipient.
	 * 
	 * @param receipient
	 *            The receipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String toString() {
		return super.toString() + " to:" + recipient ;
	}

	
	/**
	 * @return
	 */
	protected String getPlainContentType(){
		return MIME_TYPE_PREFIX + getContentEncoding();
	}
}
