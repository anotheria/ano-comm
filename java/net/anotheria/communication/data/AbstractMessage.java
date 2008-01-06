package net.anotheria.communication.data;

import java.io.Serializable;

public abstract class AbstractMessage  implements Serializable{

	public abstract int getMessageType();
}
