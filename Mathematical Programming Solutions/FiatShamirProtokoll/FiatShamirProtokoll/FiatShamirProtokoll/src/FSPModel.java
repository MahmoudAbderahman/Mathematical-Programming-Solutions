/**
 * Klasse zur definition alle benoetigen Methoden fuer die 
 * Implementierung der FiatShamirProtokoll
 * @author Steve Titinang, Rahimi Mortaza, Maryam Mohandeszadeh
 */
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Random;

public class FSPModel {
	//registration input
	private String name;
	private Session session;
	private int v;
	//Login input 
	private int x;
	private int y;
	//
	public static final String STATE_CHANGE = "status";
	private HashMap<String, Integer> registered = new HashMap<String, Integer>();
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public static final int N = 101;
	public static final int RequiredPasses = 5;
	//Es kann zwei Challenge mode eingeben. Tail oder Head. Dies wird zufaelig generieren.
	public static enum ChallengeMode {
		Tail,
		Head,
		None
	}
	
	//Konstruktoren
	public FSPModel(String name, int v) {
		this.name = name;
		this.v = v;
	}
	
	public FSPModel() {
		super();
	}
	
	//Getter und Setter Methoden
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public HashMap<String, Integer> getRegistered() {
		return registered;
	}

	public void setRegistered(HashMap<String, Integer> registered) {
		this.registered = registered;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	/* Da viele sich einloggen koennen, stehen wir zur Verfuegbung eine Session fuer jeder Benutzer.
	 * Fuer eine Session gebraucht wird den Name den Benutzer. Falls dieser Benutzer schon in der List
	 * vorhanden dann startet eine Session fuer den entsprechenden Benutzer. Danach wird nach seinem
	 * Key gefragt und ein Versuch zum Einloggen versuch. 
	 */
	public class Session {
		private ChallengeMode mode = ChallengeMode.None;
		private int v; //Der Wert von V wenn eine Session startet
		private Random random = new Random();
		
		public Session(int v) {
			this.v = v;
		}
		
		/**
		 * ChallengeMode - Methode fuer die zufaellige Auswahl eines Modus.
		 * @return - Das ausgewahlte Modus (Head/Tail)
		 */
		public ChallengeMode getChallenge() {
			if (mode == ChallengeMode.None)
				mode = (random.nextInt(2) == 0) ? ChallengeMode.Head : ChallengeMode.Tail;
			return mode;
		}
		
		/**
		 * check - Diese Methode hilf dabei das Status fuer den Versuch ein Einloggen zurueckzuliefern
		 * Sie informiert, ob das Einloggen geklappt hat oder nicht. Diese Methode wird ausgefuehrt, wenn
		 * auf Login gedrueckt wird.
		 * @param x - wird von Benutzer gebraucht, um den Versuch fuer ein Einloggen zu beginnen
		 * @param y - wird von Benutzer gebraucht, um den Versuch fuer ein Einloggen zu beginnen
		 * @return - True/False (Einlogen geklappt / nicht geklappt)
		 */
		public boolean check(int x, int y) {
			boolean pass = false;
			int ySquared = (y * y) % N;
			if (mode == ChallengeMode.Head)
				pass = (ySquared == x);
			else if (mode == ChallengeMode.Tail)
				pass = (ySquared == (x * v % N));
			mode = ChallengeMode.None;
			return pass;
		}
		
		
	}
	
	/**
	 * register - Methode zu Registrierung einen Benutzer. Dafuer wird den Name und das Key(V). 
	 * Diese Mehode wird ausgefuehrt wenn auf Register gedrueckt wird.
	 * @throws FSPExceptions - Exception wird geworfen, falls den eingegebenen Name schon bereit in der List vorhanden. 
	 */
	public void register() throws FSPExceptions {
		if (name.isEmpty()) throw new FSPExceptions("Der Name bitte eingeben");
 		if (!registered.containsKey(name)) 
			registered.put(name, v);
		else throw new FSPExceptions("Der Benutzername ist schon registriert.");
	}
	
	/**
	 * gibt die User Session zurueck.
	 * @return
	 * @throws FSPExceptions
	 */
	public Session checkUser() throws FSPExceptions {
		if(name.isEmpty()) throw new FSPExceptions("Name bitte eingebn.");
		if (!getRegistered().containsKey(name)) throw new FSPExceptions("Sind sie bereit noch nicht registriert.");
		this.session = newSession(name);
		return session;
	}
	
	/**
	 * benachricht ueber das Status ein Einloggen. Wenn der user versuch sich anzumelden dann
	 * muss him bebachricht werden ob es geklappt hat oder nicht.
	 * @return state das Ergebnis nach der Versuch eines Einloggens
	 * @throws FSPExceptions - wir rechnen mit keine negative Zahlen mit.
	 */
	public boolean login() throws FSPExceptions {
		//User Session id abholen damit ein Einloggen zu versuchen
		if (x < 0 || x > 101) throw new FSPExceptions("Eingabe von X Stimmt nicht.(X < 101 und X>0)");
		if (y < 0) throw new FSPExceptions("Keine Negative Zahlen ist erlaubt.");
		boolean state = getSession().check(x, y);
		return state;
	}
	/**
	 * newSession - Diese Methode hilft dabei eine User Session zu starten. Dafuer wird gebraucht den Name des Nutzers
	 * @param name - Name der, der sich einlogen will.
	 * @return - die Adresse eine Session in der Speicher, falls das Starten einer Session geklpappt hat,
	 *           sonst wird die Adresse auf null zeigen.
	 */
	public Session newSession(String name) {
		return registered.containsKey(name) ? new Session(registered.get(name).intValue()) : null;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
}
