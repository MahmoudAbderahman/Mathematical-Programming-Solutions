/**
 * Class CiphersModel that contains model code for the caesar class.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 */
package Model;

import java.awt.Frame;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CiphersModel {
	Frame frame;
	
	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public static final String VIGENERE_EN = "v_en";

	public static final String VIGENERE_DE = "v_de";
	String klartext;
	String Ciphertext;
	String key;
	File file;
	
	int chipher;// 0: cäser, 1: vigenere

	public int getChipher() {
		return chipher;
	}

	public void setChipher(int chipher) {
		this.chipher = chipher;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getKlartext() {
		return klartext;
	}

	public void setKlartext(String klartext) {
		this.klartext = klartext;
	}

	public String getCiphertext() {
		return Ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		Ciphertext = ciphertext;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	
	public CiphersModel(Frame frame) {
		super();
		this.frame = frame;
	}

	/**
	 * In this method we implement the code for reading the content of a file.
	 * @param file: The file, that we choose.
	 * @return The content of the file, after normalizing it.
	 * @throws IOException
	 * @throws Exceptions
	 */
	public String readFile(File file) throws IOException, Exceptions
	{
	    String content = null;
	
	    FileReader reader = null;
	    if(file != null)
	    try {
	    	
	    	if(!file.exists())
	    	{
	    		throw new Exceptions("File does not exist!");
	    	}
	    	
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if(reader != null){
	            reader.close();
	        }
	    }
	    if(content == null)
	    {
	    	return content;
	    }
	    return HelpMethods.normalize(content);
	}
	/**
	 * In this method we implement the code to get
	 * which cipher algorithm, the user has chosen
	 * and encrypt the text regarding the key.
	 * It will at the end fire a property change to the view.
	 */
	public void chiffrieren() {
		String result = "";
		
		
		Vigenere v = new Vigenere();
		v.setPlainText(getKlartext());
		v.setKey((getKey()));
		result = v.encipher();
		pcs.firePropertyChange(VIGENERE_EN, null, result);
		
	}
	/**
	 * In this method we implement the code to get
	 * which cipher algorithm, the user has chosen
	 * and decrypt the text regarding the key.
	 * It will at the end fire a property change to the view.
	 */
	public void dechiffrieren() {
		String result = "";
		
		
		Vigenere v = new Vigenere();
		v.setCipherText(getCiphertext());
		v.setKey((getKey()));
		result = v.decipher();
		pcs.firePropertyChange(VIGENERE_DE, null, result);
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
		
	}
}
