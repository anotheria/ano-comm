package net.anotheria.communication.service;

import net.anotheria.communication.data.AbstractMessage;
import net.anotheria.communication.exceptions.MessagingServiceException;


public interface IMessageDeliverer {
	public void deliverMessage(AbstractMessage msg) throws MessagingServiceException;
}
