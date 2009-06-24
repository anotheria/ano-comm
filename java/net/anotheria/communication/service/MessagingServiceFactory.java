package net.anotheria.communication.service;

import net.anotheria.asg.metafactory.ServiceFactory;

/**
 * Creates a new messaging service instance.
 * 
 * @author lrosenberg
 */
public class MessagingServiceFactory implements ServiceFactory<IMessagingService> {

	/**
	 * Returns a messaging service instance (either new or a singleton).
	 * 
	 * @return
	 */
	public static IMessagingService getMessagingService() {
		return MessagingService.getInstance();
	}

	/**
	 * Prevent instantiation.
	 */
	public MessagingServiceFactory() {
	}

	@Override
	public IMessagingService create() {
		return getMessagingService();
	}
}
