/**
 * Class Vigenere that encapsulates the Vigenère cipher method to encrypt and decrypt text.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 * 
 */
package Model;

public class Vigenere {
	String plainText, cipherText, key;

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key.toUpperCase();
	}
	/**
	 * In this method we implement the vigenere cipher encryption algorithm.
	 * We go through the text, letter by letter and encrypt it.
	 * @return The text after encryption.
	 */
	public String encipher()
	{
		plainText = HelpMethods.replaceUmlaut(plainText);
		plainText = plainText.toUpperCase();
		
		cipherText = "";
		for(int i = 0, length = plainText.length(), count = 0; i< length; i++)
		{
			if(Character.isAlphabetic(plainText.charAt(i)))
			{
				char result = (char) (((plainText.charAt(i) - 65) + (key.charAt(count % key.length()) - 65)) % 26);
				cipherText += Character.toString((char) (result + 65));
				count ++;
			}

		}
		return cipherText;
	}
	/**
	 * In this method we implement the vigenere cipher decryption algorithm.
	 * We go through the text, letter by letter and decrypt it.
	 * @return The text after decryption.
	 */
	public String decipher()
	{
		cipherText = HelpMethods.replaceUmlaut(cipherText);
		cipherText = cipherText.toUpperCase();
		plainText = "";
		for(int i = 0, count = 0, length = cipherText.length(); i< length; i++)
		{
			if(Character.isAlphabetic(cipherText.charAt(i)))
			{
				char result = (char) (((cipherText.charAt(i)) - key.charAt(count % key.length()) + 26) % 26 + 65);
				plainText += Character.toString((char) (result));
				count++;
			}
		}
		return plainText.toLowerCase();
	}
}
