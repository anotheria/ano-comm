package net.anotheria.communication.exceptions;

/**
 * TODO Please remind otoense to comment MessagingServiceException.java
 * 
 * @author otoense Created on Mar 31, 2005
 */
public class MessagingServiceException extends Exception {

	private static final long serialVersionUID = -3114792560280149595L;

	public MessagingServiceException() {
		super();
	}

	public MessagingServiceException(String message) {
		super(message);
	}

	public MessagingServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessagingServiceException(Throwable cause) {
		super(cause);
	}
}