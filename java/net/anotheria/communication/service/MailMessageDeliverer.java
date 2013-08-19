package net.anotheria.communication.service;

import net.anotheria.communication.data.AbstractMailMessage;
import net.anotheria.communication.data.AbstractMessage;
import net.anotheria.communication.exceptions.MessageDeliverException;
import net.anotheria.communication.exceptions.MessagingServiceException;
import net.anotheria.communication.exceptions.UnsupportedMessageTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Provider;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import java.util.Properties;


/**
 * @author another
 */
public class MailMessageDeliverer implements IMessageDeliverer {


	private Session mailSession;
	
	private static Logger log = LoggerFactory.getLogger(MailMessageDeliverer.class);
	
	private MailDelivererConfig config;
	
	
	public MailMessageDeliverer(){
		Properties props = new Properties();
		
		config = new MailDelivererConfig();
		props.put("mail.smtp.host", config.getHost());
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", config.isDebug());
		
 		mailSession = Session.getInstance(props, new SMTPAuthenticator());
 		
		mailSession.setDebug(config.isDebug());
		//mailSession.setPasswordAuthentication(new URLName(resServer),new PasswordAuthentication(resUser, resPassword));
		log.debug("initialized with:"+ config);
		log.debug("\n\n************************************************");
		log.debug("* to reconfigure this provider set:            *");
		log.debug("* mail.smtp.host                               *");
		log.debug("* mail.smtp.user                               *");
		log.debug("* mail.smtp.password                           *");
		log.debug("* mail.smtp.debug                              *");
		log.debug("* mail.smtp.popbeforesmtp                      *");
		log.debug("************************************************\n\n");
		log.info("Initialized with: " + config);
		log.info("mail.mime.charset=" + System.getProperty("mail.mime.charset"));
	}

	/**
	 * @see IMessageDeliverer#deliverMessage(AbstractMessage)
	 */
	public void deliverMessage(AbstractMessage msg) throws MessagingServiceException{
		if (msg.getMessageType()!=IMessageTypes.TYPE_MAIL)
			throw new UnsupportedMessageTypeException();
		deliverMailMessage( (AbstractMailMessage) msg);
	}
	
	private void deliverMailMessage(AbstractMailMessage message) throws MessageDeliverException{
		try {
			
			if (config.isPopBeforeSmtp())
				popBeforeSmtp();
				
			//SMTPTransport t = (SMTPTransport)mailSession.getTransport("smtp");
			//t.connect(resServer, resUser, resPassword);
			//System.out.println("Response: "+t.getLastServerResponse());
			//t.send(message.transformToMessage(mailSession), message.transformToMessage(mailSession).getAllRecipients());
			Transport.send(message.transformToMessage(mailSession));
		} catch(AddressException e) {
			log.error("deliverMailMessage message :{"+message.toString()+"}", e);
			throw new MessageDeliverException(e);
		} catch(MessagingException e) {
			log.error("deliverMailMessage message :{"+message.toString()+"}", e);
			throw new MessageDeliverException(e);
		}
	}
	
	private void popBeforeSmtp(){
		try {
			Provider prov = mailSession.getProvider("pop3");
			mailSession.getStore(prov).connect(config.getHost(), config.getUser() , config.getPassword());
			mailSession.getStore(prov).close();
		} catch(NoSuchProviderException e) {
			log.error("popBeforeSmtp1", e);
		} catch(MessagingException e) {
			log.error("popBeforeSmtp2",e);
		}
	}
	
	   private class SMTPAuthenticator extends javax.mail.Authenticator {
	        public PasswordAuthentication getPasswordAuthentication() {
	           String username = config.getUser();
	           String password = config.getPassword();
	           return new PasswordAuthentication(username, password);
	        }
	    }
	
}
