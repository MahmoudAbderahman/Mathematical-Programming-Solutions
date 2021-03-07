/**
 * Class RSA_Model that contains model code for the RSA_Model class.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 */

import java.awt.Frame;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigInteger;
import java.util.Random;


public class RSA_Model {
	public static final String RSA_EN = "rsa_en";
	public static final String RSA_D = "rsa_d";
	public static final String RSA_E = "rsa_e";
	public static final String RSA_N = "rsa_n";
	public static final String RSA_DE = "rsa_de";

	private Frame frame;	
	private Random random = new Random();
	private final int BITS = 1024;
	private BigInteger p, q, n,z,d,e;
	private String msg;
	private BigInteger encryptedMsg;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public RSA_Model(Frame frame) {
		super();
		setFrame(frame);
	}
	
	
	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	
	/**
	 * Getter method to
	 * @return the value of encryptedMsg
	 */
	public BigInteger getEncryptedMsg() {
		return encryptedMsg;
	}

	/**
	 * Setter method to set the value of encryptedMsg to the passed parameter
	 * @param encryptedMsg
	 */
	public void setEncryptedMsg(BigInteger encryptedMsg) {
		this.encryptedMsg = encryptedMsg;
	}

	/**
	 * Getter method to 
	 * @return the value of n
	 */
	public BigInteger getN() {
		return n;
	}

	/**
	 * Setter method to set the value of n to the passed parameter
	 * @param n
	 */
	public void setN(BigInteger n) {
		this.n = n;
	}

	/**
	 * Getter method to 
	 * @return the value of d
	 */
	public BigInteger getD() {
		return d;
	}
	
	/**
	 * Setter method to set the value of d to the passed parameter
	 * @param d
	 */
	public void setD(BigInteger d) {
		this.d = d;
	}

	/**
	 * Getter method to
	 * @return the value of msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Setter method to set the value of msg to the passed parameter
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	/**
	 * In this method we write the implementation for calculating the key
	 * and its corresponding values
	 */
	private void calculateKey()
	{
		p = BigInteger.probablePrime(BITS,random);
		q = BigInteger.probablePrime(BITS,random);
		n = p.multiply(q);
		z =  p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(BITS, random);
		while(z.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0)
		{
			e = e.add(BigInteger.ONE);
		}
		d = e.modInverse(z);
	}
	
	

	/**
	 * In this method, we cipher the input text.
	 * The key and other values will at first be calculated
	 * and the cipher text will be passed to the cipher text.
	 */
	public void chiffrieren() {
		calculateKey();
		pcs.firePropertyChange(RSA_D, null, d.toString());
		pcs.firePropertyChange(RSA_N, null, n.toString());
		pcs.firePropertyChange(RSA_E, null, e.toString());
		encryptedMsg = (new BigInteger(msg.getBytes())).modPow(e,n);
		pcs.firePropertyChange(RSA_EN, null, encryptedMsg.toString());
	}
	
	/**
	 * In this method, we decipher the encrypted text
	 * and the decrpyted text will be passed to the klar text.
	 */
	
	public void dechiffrieren() {
		byte[] decryptedMsg = (encryptedMsg).modPow(d,n).toByteArray();
		pcs.firePropertyChange(RSA_DE, null, new String(decryptedMsg));
	}
	
	/**
	 * In this method, any property change will be traced.
	 * @param l represents the model
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
		
	}
}
