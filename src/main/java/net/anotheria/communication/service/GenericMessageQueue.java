
package net.anotheria.communication.service;

import java.util.ArrayList;
import java.util.List;

import net.anotheria.communication.data.AbstractMessage;
import net.anotheria.communication.exceptions.MessagingServiceException;


/**
 * This is a generic message queue, which can handle different 
 * message types with the help of the appropriate MessageDeliverer.
 */
public class GenericMessageQueue implements IMessageQueue {
	
	private IMessageDeliverer deliverer;
	private List<Exception> errors;
	
	public GenericMessageQueue() {
		errors = new ArrayList<Exception>();
	}
	
	/**
	 * This implementation just sends the message directly to the deliverer.
	 * @see IMessageQueue#queue(AbstractMessage)
	 */
	public synchronized boolean queue(AbstractMessage message) {
		try{
			deliverer.deliverMessage(message);
		}catch(MessagingServiceException e){
			synchronized(errors) {
				if(errors.size() < MAX_ERRORS)
					errors.add(e);
			}
			return false;
		}
		return true;
	}
	
	public void setMessageDeliverer(IMessageDeliverer aDeliverer){
		deliverer = aDeliverer;
	}
	
	public List<Exception> getErrors() {
		synchronized(errors) {
			List<Exception> oldErrors = errors;
			errors = new ArrayList<Exception>();
			return oldErrors;
		}
	}
}
