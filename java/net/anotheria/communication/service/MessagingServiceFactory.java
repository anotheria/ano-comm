
package net.anotheria.communication.service;

/**
 * Creates a new messaging service instance.
 * @author lrosenberg
 */
public class MessagingServiceFactory {
	/**
	 * Returns a messaging service instance (either new or a singleton).
	 * @return 
	 */
	public static IMessagingService getMessagingService(){
		return MessagingService.getInstance();
	}

	/**
	 * Prevent instantiation. 
	 */
	private MessagingServiceFactory(){
		
	}
}
