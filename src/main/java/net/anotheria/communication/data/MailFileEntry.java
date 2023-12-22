package net.anotheria.communication.data;

import java.io.File;

/**
 * @author lanny
 */
public class MailFileEntry {
	private File file;
	private String filename;

	/**
	 * Returns the file.
	 * 
	 * @return File
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Returns the filename.
	 * 
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the file.
	 * 
	 * @param file
	 *            The file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Sets the filename.
	 * 
	 * @param filename
	 *            The filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public MailFileEntry(File f) {
		this(f, f.getName());
	}

	public MailFileEntry(File f, String name) {
		this.file = f;
		this.filename = name;
	}

	public String toString() {
		return file.getName() + " as " + filename;
	}

}
