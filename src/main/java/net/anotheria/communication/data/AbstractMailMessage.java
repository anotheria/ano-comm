package net.anotheria.communication.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;

import net.anotheria.communication.service.IMessageTypes;

/**
 * The base class for mail messages.
 */
public abstract class AbstractMailMessage extends AbstractMessage implements Serializable {

	public static final String DEFAULT_ENCODING = "iso-8859-15";
	
	private static final long serialVersionUID = -4454530548825883428L;
	/**
	 * The encoding of the content
	 */
	private String contentEncoding;
	/**
	 * Sender address of the message
	 */
	private String sender;
	/**
	 * Sender personal name of the message
	 */
	private String senderName = null;
	/**
	 * Subject of the message
	 */
	private String subject;
	/**
	 * Message body
	 */
	private String message;
	/*
	 * Reply-to header field
	 */
	private String replyTo;
	/**
	 * A map of addition headers
	 */
	private Map<String, String> headers = new HashMap<String, String>();

	
	
	AbstractMailMessage(){
		this(DEFAULT_ENCODING);
	}
	
	AbstractMailMessage(String aContentEncoding){
		this.contentEncoding = aContentEncoding;
	}
	
	/**
	 * @see AbstractMessage#getMessageType()
	 */
	@Override
	public int getMessageType() {
		return IMessageTypes.TYPE_MAIL;
	}

	/**
	 * Gets the sender.
	 * 
	 * @return Returns a String
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the sender.
	 * 
	 * @param sender
	 *            The sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the sender name
	 * 
	 * @return Returns either the sender name (if set) or the sender
	 */
	public String getSenderName() {
		return (senderName != null ? senderName : sender);
	}

	/**
	 * Sets the sender name
	 * 
	 * @param senderName
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * Gets the subject.
	 * 
	 * @return Returns a String
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            The subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the sender.
	 * 
	 * @return Returns a String
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * Sets the sender.
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	/**
	 * Gets the message.
	 * 
	 * @return Returns a String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * This method is called to create a new java.mail.message
	 * 
	 * @param session
	 *            the associated session
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public abstract Message transformToMessage(Session session) throws AddressException, MessagingException;

	@Override
	public String toString() {
		return "from:" + sender;
	}

	/**
	 * Adds a header field to the message
	 * 
	 * @param name
	 *            name of the headerfiled
	 * @param value
	 *            value of the header field
	 */
	public void addHeader(String name, String value) {
		headers.put(name, value);
	}

	/**
	 * Called by extending class to set headers in the message after transformation.
	 * 
	 * @param msg
	 * @throws MessagingException
	 */
	protected void addHeadersToMessage(Message msg) throws MessagingException {
		Collection<String> allHeaders = headers.keySet();
		for (String key : allHeaders) {
			String val = headers.get(key);
			msg.addHeader(key, val);
		}
	}
	
	/**
	 * Returns the content encoding
	 * @return the content encoding
	 */
	public String getContentEncoding() {
		return contentEncoding;
	}

	/**
	 * Sets the content encoding
	 * @param contentEncoding
	 */
	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}
}
