/**
 * Class HelpMethods that contains Helper methods like replaceUmlaut() and normalize().
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 * 
 */
package Model;

public class HelpMethods {
	
	/**
	 * In this method we take the input an replace the umlauts by their
	 * corresponding letters.
	 * @param text: represents the input, in which we replace the umlauts by english letters.
	 * @return the text after modifying it.
	 */
	public static String replaceUmlaut(String text) {

	
		//replace all lower Umlauts
	     String output = text.replace("ü", "ue")
	                          .replace("ö", "oe")
	                          .replace("ä", "ae")
	                          .replace("ß", "ss");

	     //first replace all capital umlaute in a non-capitalized context (e.g. Übung)
	     output = output.replace("Ü(?=[a-zäöüß ])", "Ue")
	                    .replace("Ö(?=[a-zäöüß ])", "Oe")
	                    .replace("Ä(?=[a-zäöüß ])", "Ae");

	     //now replace all the other capital umlaute
	     output = output.replace("Ü", "UE")
	                    .replace("Ö", "OE")
	                    .replace("Ä", "AE");

	     return output;
	 }
	/**
	 * In this method we take the original text and ignore all white spaces
	 * and other special characters.
	 * @param text: the original text
	 * @return modified text after taking away all special characters
	 * and white spaces.
	 */
	public static String normalize(String text)
	{
		StringBuilder result = new StringBuilder("");
		for(int i = 0, length = text.length(); i< length; i++)
		{
			if(Character.isAlphabetic(text.charAt(i)))
			{
				result.append( text.charAt(i));
			}
		}
		return result.toString().toLowerCase();
	}
}
