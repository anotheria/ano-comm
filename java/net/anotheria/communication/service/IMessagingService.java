package net.anotheria.communication.service;

import java.util.List;

import net.anotheria.communication.data.AbstractMessage;

//standart imports

/**
 * noch nichts
 */

public interface IMessagingService{
	/**
	 * no comment
	 */
	public boolean sendMessage(AbstractMessage msg) throws Exception;
	
	public List getErrors(int messageType);

}
