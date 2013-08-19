package net.anotheria.communication.service;

import org.configureme.ConfigurationManager;
import org.configureme.annotations.AfterConfiguration;
import org.configureme.annotations.Configure;
import org.configureme.annotations.ConfigureMe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ConfigureMe(name="mail")
public class MailDelivererConfig {

	@Configure private String host;   
	@Configure private String user;   
	@Configure private String password;   
	@Configure private boolean debug;
	@Configure private boolean popBeforeSmtp;

	private static Logger log = LoggerFactory.getLogger(MailDelivererConfig.class);

	public MailDelivererConfig(){
		ConfigurationManager.INSTANCE.configure(this);
	}
	
	public String getConfigurationName() {
		return "mail";
	}


	@AfterConfiguration public void notifyConfigurationFinished() {
		log.info(getConfigurationName()+" config finished: "+this);
	}

	public String toString(){
		return getUser()+"!"+getPassword()+":"+getHost()+" - "+isDebug()+"/"+isPopBeforeSmtp();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isPopBeforeSmtp() {
		return popBeforeSmtp;
	}

	public void setPopBeforeSmtp(boolean popBeforeSmtp) {
		this.popBeforeSmtp = popBeforeSmtp;
	}
}
