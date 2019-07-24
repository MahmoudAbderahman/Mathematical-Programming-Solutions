/**
 * Class Exceptions that has one constructor to assign the exception message
 * and one method to get the message.
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 */

public class Exceptions extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public Exceptions(String message)
	{
		super(message);
		this.message = message;
	}
	
	/**
	 * Getter method to return the value of message.
	 */
	public String getMessage()
	{
		return this.message;
	}
}