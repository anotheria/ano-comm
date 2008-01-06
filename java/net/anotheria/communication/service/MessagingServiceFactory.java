
package net.anotheria.communication.service;

public class MessagingServiceFactory {
	public static IMessagingService getMessagingService(){
		return MessagingService.getInstance();
	}
}
