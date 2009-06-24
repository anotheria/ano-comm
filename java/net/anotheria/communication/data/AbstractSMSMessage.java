package net.anotheria.communication.data;

import java.io.Serializable;

import net.anotheria.communication.service.IMessageTypes;

public abstract class AbstractSMSMessage extends AbstractMessage implements Serializable {

	private static final long serialVersionUID = -4781057501791296879L;

	protected String messageBody;
	protected String sender;
	protected boolean flash;

	public abstract String getRecepientString();

	public AbstractSMSMessage(String aMessageBody) {
		this.messageBody = aMessageBody;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String aMessageBody) {
		this.messageBody = aMessageBody;
	}

	public boolean isFlash() {
		return flash;
	}

	public void setFlash(boolean value) {
		flash = value;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String toString() {
		String ret = "To: " + getRecepientString() + ", Msg:" + getMessageBody();
		if (flash)
			ret += " [flash]";
		if (sender != null)
			ret += " From:" + sender;
		return ret;
	}

	public int getMessageType() {
		return IMessageTypes.TYPE_SMS;
	}
}
