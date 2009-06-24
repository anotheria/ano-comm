package net.anotheria.communication.data;

import java.io.Serializable;

import net.anotheria.communication.service.IMessageTypes;

public class InstantMessage extends AbstractMessage implements Serializable {

	private static final long serialVersionUID = 5894201237509006815L;

	private String recepient;
	private String messageBody;
	private String sender;

	public InstantMessage(String aSender, String aRecepient, String aMessageBody) {
		sender = aSender;
		recepient = aRecepient;
		messageBody = aMessageBody;
	}

	/**
	 * @see AbstractMessage#getMessageType()
	 */
	public int getMessageType() {
		return IMessageTypes.TYPE_IM;
	}

	/**
	 * Gets the messageBody.
	 * 
	 * @return Returns a String
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * Sets the messageBody.
	 * 
	 * @param messageBody
	 *            The messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * Gets the recepient.
	 * 
	 * @return Returns a String
	 */
	public String getRecepient() {
		return recepient;
	}

	/**
	 * Sets the recepient.
	 * 
	 * @param recepient
	 *            The recepient to set
	 */
	public void setRecepient(String recepient) {
		this.recepient = recepient;
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

}
