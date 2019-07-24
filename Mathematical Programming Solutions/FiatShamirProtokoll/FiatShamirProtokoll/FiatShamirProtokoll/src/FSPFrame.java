/**
 * Klasse die fuer die Erzeugerung eine Benutzeroberflaeche gilt. 
* Mulham Alesali:20203753
* Nawid Shadab:20201725
* Mahmoud Abderahman:20202225

*/

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class FSPFrame extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	
	public FSPFrame() {
		super("Fiat-Shamir-Protokoll");
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		JTabbedPane tab = new JTabbedPane();
		FSPModel model = new FSPModel();
		//
	    FSPView login = new FSPView(model, FSPView.ViewMode.Login);
	    FSPView registration = new FSPView(model, FSPView.ViewMode.Register);
	    
	    tab.add("Register",registration);
	    tab.add("Login", login);
		getContentPane().add(tab);
		setSize(1700,800);
		setResizable(false);
		pack();
	}
	
	public static void main(String[] args) {
		FSPFrame ff = new FSPFrame();
		ff.setVisible(true);
	}
	 
}
