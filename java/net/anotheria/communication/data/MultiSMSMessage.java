package net.anotheria.communication.data;

import java.io.Serializable;
import java.util.Vector;

public class MultiSMSMessage extends AbstractSMSMessage implements Serializable{
	private Vector recepients;

	public MultiSMSMessage(String text){
	    this(text, new Vector());
	}

	public MultiSMSMessage(String text, Vector recepientList){
	    super(text);
		this.recepients = recepientList;
	}

	public Vector getRecepients(){
	    return recepients;
	}

	public void removeRecepient(String recepient){
	    recepients.remove(recepient);
	}

	public void addRecepient(String recepient){
	    if (recepients.indexOf(recepient)==-1)
			recepients.addElement(recepient);
	}

    public String getRecepientString() {
        if (recepients == null || recepients.size() == 0)
            return  "";
        String ret = "";
        for (int i = 0; i < recepients.size(); i++) {
            ret += (String)recepients.elementAt(i);
            if (i < recepients.size() - 1)
                ret += ",";
        }
        return  ret;
    }
}
