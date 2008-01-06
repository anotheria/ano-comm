package net.anotheria.communication.service;

import java.util.Hashtable;
import java.util.List;

import net.anotheria.communication.data.AbstractMessage;


public class MessagingService implements IMessagingService{
	
	
	private Hashtable queues;
	
	private static MessagingService instance;
	
	
	protected MessagingService(){
		queues = new Hashtable();
		setup();
	}
	
	public static MessagingService getInstance(){
		if (instance==null){
			instance = new MessagingService();
		}
		return instance;
	}

	public boolean sendMessage(AbstractMessage msg) throws Exception{
		int messageType = msg.getMessageType();
		IMessageQueue queue = (IMessageQueue) queues.get(new Integer(messageType));
		if (queue==null)
			throw new RuntimeException("Unsupported message type: "+messageType);
		return queue.queue(msg);
	}
	
	
	
	//die einzige methode wo konkrete klassen zum senden von messages verwendet werden.
	private void setup(){
	
		GenericMessageQueue mailQueue = new GenericMessageQueue();
		mailQueue.setMessageDeliverer(new MailMessageDeliverer());
		addMessageQueue(IMessageTypes.TYPE_MAIL, mailQueue);
	}
	
	public void addMessageQueue(int type, IMessageQueue deliverer){
		queues.put(new Integer(type), deliverer);
	}
	
	public void init(){}
	public void deInit(){}

	public List getErrors(int messageType) {
		IMessageQueue queue = (IMessageQueue) queues.get(new Integer(messageType));
		if (queue==null)
			throw new RuntimeException("Unsupported message type: "+messageType);
		return queue.getErrors();
	}
}
