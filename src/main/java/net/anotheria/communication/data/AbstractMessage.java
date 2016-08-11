package net.anotheria.communication.data;

import java.io.Serializable;

/**
 * The base class for messages. TODO: Should be an interface anyway.
 * 
 * @author lrosenberg
 */
public abstract class AbstractMessage implements Serializable {

	private static final long serialVersionUID = 1502207950231409651L;

	public abstract int getMessageType();
}
