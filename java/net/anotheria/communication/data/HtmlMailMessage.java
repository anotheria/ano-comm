package net.anotheria.communication.data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * This class implements an <code>AbstractMailMessage</code> to send an HTML-Mail with alternative Plain-Text-View. Images may be attached
 * to be referenced within the HTML-Code.
 * 
 * @author Michel Fuegeisen
 * @author Oliver Toense
 */
public class HtmlMailMessage extends AbstractMailMessage implements Serializable {

	private static final long serialVersionUID = -7844502191961385383L;

	private static final String TEXT_HTML = "text/html;charset=UTF-8";
	private static final String TEXT_PLAIN = "text/plain";

	/**
	 * The recipient of the message
	 */
	private String recipient;
	/**
	 * The html content part
	 */
	private String htmlContent;
	/**
	 * The plain text content part
	 */
	private String plainTextContent;
	/**
	 * Map with includeable urls
	 */
	private Map<String, URL> imageMap;

	/**
	 * Creates a new message
	 */
	public HtmlMailMessage() {
		super();
		imageMap = new HashMap<String, URL>();
	}

	@Override
	public Message transformToMessage(Session session) throws AddressException, MessagingException {

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(getSender(), getSenderName()));
		} catch (UnsupportedEncodingException e) {
			msg.setFrom(new InternetAddress(getSender()));
		}
		InternetAddress[] receivers = new InternetAddress[1];
		receivers[0] = new InternetAddress(getRecipient());
		InternetAddress[] replyTo = new InternetAddress[1];
		replyTo[0] = new InternetAddress(getReplyTo() != null ? getReplyTo() : getSender());
		msg.setRecipients(Message.RecipientType.TO, receivers);
		msg.setSubject((getSubject() != null ? getSubject() : ""));
		msg.setReplyTo(replyTo);

		// toplevel/alternative provides the client with the possibility to choose its best view
		Multipart topLevel = new MimeMultipart("alternative");

		// subpart for html
		MimeBodyPart htmlContainer = new MimeBodyPart();

		// attach images
		if (imageMap.size() > 0) {

			MimeMultipart htmlMultipart = new MimeMultipart("related");
			// htmlMultipart.setSubType("text/html;charset=windows-1251");
			// part for html source
			MimeBodyPart htmlContent = new MimeBodyPart();
			htmlContent.setContent(getHtmlContent(), TEXT_HTML);

			htmlMultipart.addBodyPart(htmlContent);

			// part for related images
			Iterator<Map.Entry<String, URL>> allImages = imageMap.entrySet().iterator();
			while (allImages.hasNext()) {
				Map.Entry<String, URL> imageDef = allImages.next();
				htmlMultipart.addBodyPart(generateImageContent((URL) imageDef.getValue(), (String) imageDef.getKey()));
			}
			htmlContainer.setContent(htmlMultipart);

		} else {
			htmlContainer.setContent(getHtmlContent(), TEXT_HTML);
		}

		// subpart for plain
		MimeBodyPart textAlternate = new MimeBodyPart();
		textAlternate.setContent(getPlainTextContent(), TEXT_PLAIN);

		topLevel.addBodyPart(textAlternate);
		topLevel.addBodyPart(htmlContainer);

		msg.setContent(topLevel);
		addHeadersToMessage(msg);

		return msg;
	}

	private MimeBodyPart generateImageContent(URL url, String id) throws MessagingException {
		MimeBodyPart imageContent = new MimeBodyPart();
		DataSource ds = new URLDataSource(url);
		imageContent.setDataHandler(new DataHandler(ds));
		imageContent.setHeader("Content-ID", id);
		return imageContent;
	}

	/**
	 * Return the recipient
	 * 
	 * @return a string with recipient's adress
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Sets the recipient's email address
	 * 
	 * @param recipient
	 *            the address to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Override
	public String toString() {
		return super.toString() + " to:" + recipient;
	}

	/**
	 * Returns the html content part
	 * 
	 * @return the html content part as string.
	 */
	public String getHtmlContent() {
		return htmlContent;
	}

	/**
	 * Sets the HTML-representation of the mail
	 * 
	 * @param htmlContent
	 */
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getPlainTextContent() {
		return plainTextContent;
	}

	/**
	 * Sets the plaintext-representation of the mail
	 * 
	 * @param textContent
	 */
	public void setPlainTextContent(String textContent) {
		this.plainTextContent = textContent;
	}

	/**
	 * Adds an image attachment. Use cid:id as the image-src within the HTML-representation of the mail to reference the image.
	 * 
	 * @param imageUrl
	 * @param id
	 */
	public void addImageAttachment(URL imageUrl, String id) {
		imageMap.put(id, imageUrl);
	}

	/**
	 * Removes an image attachment.
	 * 
	 * @param id
	 */
	public void removeImageAttachment(String id) {
		imageMap.remove(id);
	}
}
