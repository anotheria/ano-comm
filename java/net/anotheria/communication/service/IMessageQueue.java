package net.anotheria.communication.service;

import java.util.List;

import net.anotheria.communication.data.AbstractMessage;


public interface IMessageQueue {
	
	public static final int MAX_ERRORS = 100;
	public boolean queue(AbstractMessage message);
	public List getErrors();
}
