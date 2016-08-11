package net.anotheria.communication.data;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * @author lanny
 * 
 *         To change this generated comment edit the template variable "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to Window>Preferences>Java>Code Generation.
 */
public class MultiPartMailMessage extends SimpleMailMessage {

	private static final long serialVersionUID = 6233173875811365443L;

	private List<MailFileEntry> files;
	private List<MailInmemoryFileEntry> inmemoryFiles;

	public MultiPartMailMessage() {
		super();
		files = new Vector<MailFileEntry>(3);
		inmemoryFiles = new Vector<MailInmemoryFileEntry>(3);
	}

	public Message transformToMessage(Session session) throws AddressException, MessagingException {
		ANOMimeMessage msg = new ANOMimeMessage(session);
		msg.setFrom(new InternetAddress(getSender()));
		InternetAddress[] receivers = new InternetAddress[1];
		receivers[0] = new InternetAddress(getRecipient());
		msg.setRecipients(Message.RecipientType.TO, receivers);
		msg.setSubject((getSubject() != null ? getSubject() : ""), getContentEncoding());

		// create and fill the first message part
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(getMessage());

		// create the Multipart and its parts to it
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);

		// Add files
		for (int i = 0; i < files.size(); i++) {
			MimeBodyPart filePart = new MimeBodyPart();
			
			// attach the file to the message
			FileDataSource fds = new FileDataSource( files.get(i).getFile());
			filePart.setDataHandler(new DataHandler(fds));
			filePart.setFileName( files.get(i).getFilename());
			mp.addBodyPart(filePart);
		}
		
		// Add inmemory files
		for (int i = 0; i < inmemoryFiles.size(); i++) {
			MimeBodyPart filePart = new MimeBodyPart();

			// attach the file to the message
			DataSource fds = new ByteArrayDataSource(inmemoryFiles.get(i).getData() != null ? inmemoryFiles.get(i).getData() : new byte[]{}, "application/octet-stream");
			filePart.setDataHandler(new DataHandler(fds));
			filePart.setFileName(inmemoryFiles.get(i).getFilename() != null ? inmemoryFiles.get(i).getFilename() : "unknown");
			mp.addBodyPart(filePart);
		}

		// add the Multipart to the message
		msg.setContent(mp);

		// set the Date: header
		msg.setSentDate(new Date());

		addHeadersToMessage(msg);

		// send the message
		return msg;
	}

	/**
	 * Returns the files.
	 * 
	 * @return List
	 */
	public List<MailFileEntry> getFiles() {
		return files;
	}
	
	/**
	 * Returns the inmemory files.
	 * 
	 * @return List
	 */
	public List<MailInmemoryFileEntry> getInmemoryFiles() {
		return inmemoryFiles;
	}

	/**
	 * Sets the files.
	 * 
	 * @param files
	 *            The files to set
	 */
	public void setFiles(List<MailFileEntry> files) {
		this.files = files;
	}

	/**
	 * Sets the inmemory files.
	 * 
	 * @param inmemoryFiles
	 *            The files to set
	 */
	public void setInmemoryFiles(List<MailInmemoryFileEntry> inmemoryFiles) {
		this.inmemoryFiles = inmemoryFiles;
	}

	public void addFile(MailFileEntry f) {
		files.add(f);
	}
	
	public void addInmemoryFile(MailInmemoryFileEntry f) {
		inmemoryFiles.add(f);
	}

	public String toString() {
		return super.toString() + " Files:" + files + ", Invemory files: " + inmemoryFiles;
	}

}
