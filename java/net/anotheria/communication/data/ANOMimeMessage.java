package net.anotheria.communication.data;

import net.anotheria.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * Class extends of MimeMessage with override updateMessageID method.
 * updateMessageID method set Message-ID by super class method in case
 * when MessageID empty.
 *
 * @author Roman Kapushchak
 */
public class ANOMimeMessage extends MimeMessage {

    public ANOMimeMessage(Session session) {
        super(session);
    }

    @Override
    protected void updateMessageID() throws MessagingException {
        /*If delimiter == null then method return only first header.
          If you need all headers, you should replace null on characters
          that you need.
         */
        String header = headers.getHeader("Message-ID", null);
        if(StringUtils.isEmpty(header)){
            super.updateMessageID();
        }
    }
}
