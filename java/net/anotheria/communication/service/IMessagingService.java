package net.anotheria.communication.service;

import java.util.List;

import net.anotheria.anoprise.metafactory.Service;
import net.anotheria.communication.data.AbstractMessage;



public interface IMessagingService extends Service {

	public boolean sendMessage(AbstractMessage msg) throws Exception;

	public List<Exception> getErrors(int messageType);

}
