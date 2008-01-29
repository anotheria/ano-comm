package net.anotheria.communication.service;

import org.apache.log4j.Logger;

import net.java.dev.moskito.core.configuration.ConfigurationServiceFactory;
import net.java.dev.moskito.core.configuration.IConfigurable;

public class MailDelivererConfig implements IConfigurable{

	public static final String KEY_PREFIX = "mail.smtp.";
	
	public static final String KEY_SERVER = KEY_PREFIX+"host";
	public static final String KEY_USER   = KEY_PREFIX+"user";
	public static final String KEY_PASSWORD = KEY_PREFIX+"password";
	public static final String KEY_DEBUG = KEY_PREFIX+"debug";
	public static final String KEY_POPBEFORESMTP = KEY_PREFIX+"popbeforesmtp";
	
	private String resServer;   
	private String resUser;   
	private String resPassword;   
	private boolean resDebug;
	private boolean popBeforeSmtp;

	private static Logger log = Logger.getLogger(MailDelivererConfig.class);

	public MailDelivererConfig(){
		ConfigurationServiceFactory.getConfigurationService().addConfigurable(this);
	}
	
	public String getConfigurationName() {
		return "mail";
	}


	public void setProperty(String key, String value) {
		if (KEY_SERVER.equals(key))
			resServer = value;
		if (KEY_USER.equals(key))
			resUser = value;
		if (KEY_PASSWORD.equals(key))
			resPassword = value;
		if (KEY_DEBUG.equals(key))
			resDebug = value.equalsIgnoreCase("true");
		if (KEY_POPBEFORESMTP.equals(key))
			popBeforeSmtp = value.equalsIgnoreCase("true");
	}

	public void notifyConfigurationFinished() {
		log.info(getConfigurationName()+" config finished: "+this);
	}

	public void notifyConfigurationStarted() {
		
	}
	
	public String getServer(){
		return resServer;
	}
	
	public String getUser(){
		return resUser;
	}
	
	public String getPassword(){
		return resPassword;
	}
	
	public boolean isDebugOn(){
		return resDebug;
	}
	
	public boolean isPopBeforeSmtpOn(){
		return popBeforeSmtp;
	}
	
	public String toString(){
		return resUser+"!"+resPassword+":"+resServer+" - "+resDebug+"/"+popBeforeSmtp;
	}
}
