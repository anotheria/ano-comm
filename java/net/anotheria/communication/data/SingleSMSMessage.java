package net.anotheria.communication.data;

import java.io.Serializable;

public class SingleSMSMessage extends AbstractSMSMessage  implements Serializable{

	private String recepient;

	public SingleSMSMessage(String text, String recepient){
		super(text);
		this.recepient = recepient;
	}

    public String getRecepientString() {
		return recepient;
    }

	public void setRecepient(String recepient){
	    this.recepient = recepient;
	}

	public String getRecepient(){
	    return recepient;
	}
}

