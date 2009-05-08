package net.anotheria.communication.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MultiSMSMessage extends AbstractSMSMessage implements Serializable{
	private List<String> recepients;

	public MultiSMSMessage(String text){
		super(text);
	    recepients = new ArrayList<String>();
	}

	public MultiSMSMessage(String text, Vector<String> recepientList){
	    this(text);
	    recepients.addAll(recepientList);
	}

	public List<String> getRecepients(){
	    return recepients;
	}

	public void removeRecepient(String recepient){
	    recepients.remove(recepient);
	}

	public void addRecepient(String recepient){
	    if (recepients.indexOf(recepient)==-1)
			recepients.add(recepient);
	}

    public String getRecepientString() {
        if (recepients == null || recepients.size() == 0)
            return  "";
        String ret = "";
        for (int i = 0; i < recepients.size(); i++) {
            ret += (String)recepients.get(i);
            if (i < recepients.size() - 1)
                ret += ",";
        }
        return  ret;
    }
}
