/**
 * Klasse zur Handlung alle Fehler die auftreten wegen den Ablauf des Programms 
 * auftreten koennen.
 * @author Steve Titinang, Rahimi Mortaza, Maryam Mohandeszadeh
 */
@SuppressWarnings("serial")
public class FSPExceptions extends Exception {
	
	private String msg = "Error";
	
	public FSPExceptions(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
