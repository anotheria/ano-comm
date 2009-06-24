package net.anotheria.communication.service;

import java.util.List;

import net.anotheria.asg.service.ASGService;
import net.anotheria.communication.data.AbstractMessage;

public interface IMessagingService extends ASGService {

	public boolean sendMessage(AbstractMessage msg) throws Exception;

	public List<Exception> getErrors(int messageType);

}
