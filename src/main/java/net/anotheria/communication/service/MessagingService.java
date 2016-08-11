package net.anotheria.communication.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.anotheria.communication.data.AbstractMessage;

public class MessagingService implements IMessagingService {

	private Map<Integer, IMessageQueue> queues;

	private static MessagingService instance = new MessagingService();

	protected MessagingService() {
		queues = new ConcurrentHashMap<Integer, IMessageQueue>();
		setup();
	}

	public static MessagingService getInstance() {
		return instance;
	}

	public boolean sendMessage(AbstractMessage msg) throws Exception {
		int messageType = msg.getMessageType();
		IMessageQueue queue = queues.get(new Integer(messageType));
		if (queue == null)
			throw new IllegalArgumentException("Unsupported message type: " + messageType);
		return queue.queue(msg);
	}

	private void setup() {
		GenericMessageQueue mailQueue = new GenericMessageQueue();
		mailQueue.setMessageDeliverer(new MailMessageDeliverer());
		addMessageQueue(IMessageTypes.TYPE_MAIL, mailQueue);
	}

	public void addMessageQueue(int type, IMessageQueue deliverer) {
		queues.put(new Integer(type), deliverer);
	}

	public void init() {
	}

	public void deInit() {
	}

	public List<Exception> getErrors(int messageType) {
		IMessageQueue queue = queues.get(new Integer(messageType));
		if (queue == null)
			throw new RuntimeException("Unsupported message type: " + messageType);
		return queue.getErrors();
	}

}
