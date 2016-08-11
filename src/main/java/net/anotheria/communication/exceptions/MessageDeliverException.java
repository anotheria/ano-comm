package net.anotheria.communication.exceptions;

/**
 * TODO Please remind otoense to comment MessageDeliverException.java
 * 
 * @author otoense Created on Mar 31, 2005
 */
public class MessageDeliverException extends MessagingServiceException {

	private static final long serialVersionUID = -2605785188997541959L;

	public MessageDeliverException() {
		super();
	}

	/**
	 * @param message
	 */
	public MessageDeliverException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MessageDeliverException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public MessageDeliverException(Throwable cause) {
		super(cause);
	}
}