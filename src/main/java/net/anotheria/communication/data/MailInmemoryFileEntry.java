package net.anotheria.communication.data;

/**
 * Inmemory File Entry.
 */
public class MailInmemoryFileEntry {

	/**
	 * File name.
	 */
	private String filename;
	/**
	 * File data.
	 */
	private byte[] data;

	/**
	 * Constructor.
	 * 
	 * @param filename file name
	 * @param data file data
	 */
	public MailInmemoryFileEntry(String filename, byte[] data) {
		super();

		this.filename = filename;
		this.data = data;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
