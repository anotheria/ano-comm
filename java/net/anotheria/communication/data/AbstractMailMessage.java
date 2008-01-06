package net.anotheria.communication.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

import net.anotheria.communication.service.IMessageTypes;


/**
 * @author skyball
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 */
public abstract class AbstractMailMessage extends AbstractMessage implements Serializable {

	private String sender;
    private String senderName=null;
	private String subject;
	private String message;
	private String replyTo;
	private Map header = new HashMap();
	
	/**
	 * @see AbstractMessage#getMessageType()
	 */
	public int getMessageType() {
		return IMessageTypes.TYPE_MAIL;
	}

	/**
	 * Gets the sender.
	 * @return Returns a String
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the sender.
	 * @param sender The sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
    
    /**
     * Gets the sender name
     * @return Returns either the sender name (if set) or the sender
     */
    public String getSenderName() {
        return ( senderName!=null ? senderName: sender);
    }
    
    
    /**
     * Sets the sender name
     * @param senderName
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

	/**
	 * Gets the subject.
	 * @return Returns a String
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 * @param subject The subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the sender.
	 * @return Returns a String
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * Sets the sender.
	 * @param sender The sender to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
    
	/**
	 * Gets the message.
	 * @return Returns a String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * @param message The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public abstract Message transformToMessage(Session session) throws AddressException, MessagingException;
	
	public String toString(){
		return "from:"+sender;
	}
	
	public void addHeader(String name, String value) {
		header.put(name, value);
	}

	public void addHeadersToMessage(Message msg) throws MessagingException {
		Iterator allHeaders = header.entrySet().iterator();
		while(allHeaders.hasNext()) {
			Map.Entry h = (Map.Entry) allHeaders.next();
			msg.addHeader((String) h.getKey(), (String) h.getValue());
		}
	}
}
