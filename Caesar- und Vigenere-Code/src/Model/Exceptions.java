/**
 * Class Exceptions that has one constructor to assign the exception message
 * and one method to get the message.
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 */
package Model;

public class Exceptions extends Exception {
	private String message;
	public Exceptions(String message)
	{
		super(message);
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}
}

