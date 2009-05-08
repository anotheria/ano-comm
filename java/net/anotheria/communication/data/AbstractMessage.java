package net.anotheria.communication.data;

import java.io.Serializable;

/**
 * The base class for messages. TODO: Should be an interface anyway.
 * @author lrosenberg
 */
public abstract class AbstractMessage  implements Serializable{

	public abstract int getMessageType();
}
