/**
 * Klasse zur definition alle benoetigen View fuer die 
 * Implementierung der FiatShamirProtokoll
 * @author Steve Titinang, Rahimi Mortaza, Maryam Mohandeszadeh
 */
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class FSPView extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	public static enum ViewMode {
		Login,
		Register,
		None
	}
	//Logins Items
	private JButton loginButton = new JButton("Login");
	private JTextField name = new JTextField("",15);
	private JTextField v = new JTextField("",10);
	//Regustrations Items
	private JButton register = new JButton("Register");
	
	private ViewMode viewmode = ViewMode.None;
	private FSPModel model = new FSPModel();
	
	//Constructor
	public FSPView(FSPModel model, ViewMode mode) {
		
		viewmode = mode;
		model.addPropertyChangeListener(this);

		this.model = model;
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		
		//Building the view of the Login 
		if (viewmode == ViewMode.Login) {
			JLabel label = new JLabel("Name: ");
			label.setAlignmentY(TOP_ALIGNMENT);
			box.add(Box.createVerticalStrut(15));
			box.add(label);
			box.add(Box.createVerticalStrut(5));
			name.setAlignmentX(LEFT_ALIGNMENT);
			box.add(name);
			box.add(Box.createVerticalStrut(40));
			loginButton.addActionListener(this);
			loginButton.setAlignmentX(LEFT_ALIGNMENT);
			box.add(loginButton);
			add(box);
		} else { // view for the registration 
			JLabel label2 = new JLabel("Name: ");
			label2.setAlignmentY(TOP_ALIGNMENT);
			box.add(label2);
			box.add(Box.createVerticalStrut(5));
			name.setAlignmentX(LEFT_ALIGNMENT);
			box.add(name);
			box.add(Box.createVerticalStrut(20));
			box.add(new JLabel("V: "));
			box.add(Box.createVerticalStrut(5));
			v.setAlignmentX(LEFT_ALIGNMENT);
			box.add(v);
			box.add(Box.createVerticalStrut(15));
			register.addActionListener(this);
			register.setAlignmentX(LEFT_ALIGNMENT);
			box.add(register);
			add(box);		
		}
	}
	
	
	/**
	 * ReadInput - Methode die ausgefuehrt wird nach bestimmte ereignisse
	 */
	public void ReadInput() {	
	
		String x, y;
		FSPModel.Session session;
		//falls die Anmeldung ein einzige mal fehtl, dann gehen wir aus die Schleife raus.
		boolean state = false;
		
		try {
			if(viewmode == ViewMode.Register) {//Implementation fuer eine Registrierunug
				model.setName(name.getText());
				model.setV(Integer.valueOf(v.getText()));
				model.register();
				//Die Reigistrierung hat geklappt, dann benachrichtigen
				JOptionPane.showMessageDialog(this,
						"Registrierung erfolgreich.","Eingabefehler!",JOptionPane.INFORMATION_MESSAGE);
			}
			//Implementation fuer ein Login
			else { 
				model.setName(name.getText());
				//pruef mal ob den Benutzer schon registriert ist. Falls dies wird die User Session gespeichert.
				session = model.checkUser();
				model.setSession(session);
				//Versuch des Einloggens
				for (int i=0; i < FSPModel.RequiredPasses; ++i) {
					x = JOptionPane.showInputDialog(this, "Wert von X", "Value zur X", JOptionPane.QUESTION_MESSAGE);
					//Parse the value of x to an integer
					model.setX(Integer.valueOf(x));
					//Wert von Y wird gefragt abhanegig von der Mode. mode = Head, y = r sonst y 0 r*s
					if (session.getChallenge() == FSPModel.ChallengeMode.Head) {
						// y = r
						y = JOptionPane.showInputDialog(this, "Wert von y = r", "Head mode", JOptionPane.QUESTION_MESSAGE);
						model.setY(Integer.valueOf(y)); //Parse the value of y to an integer
					} else {
						//y = r*s
						y = JOptionPane.showInputDialog(this, "Wert von y = r*s", "Tail mode", JOptionPane.QUESTION_MESSAGE);
						model.setY(Integer.valueOf(y)); //Parse the value of y to an integer
					}
					//Falls alles richtig eingeben wurde
					state = model.login();
					if (state == false) break;
				}
				//Hier wird den Benutzer benchrichtigt
				if (state == false) {
					JOptionPane.showMessageDialog(this,
							"Fehlgeschlagen","Anmeldung Fehler!",JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
							"Einloggen war erfolgreich.","Angemeldet",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Pruefen Sie bitte alle Eingaben Fehldern.\nFelde entweder leer oder falsche Eingabe.",
					"Eingabefehler!",JOptionPane.ERROR_MESSAGE);
		} catch(NullPointerException np) {
			//wenn auf Cancel gedrueckt wurde, dann wir diese Exception geworfen
		} catch (FSPExceptions ex) {
			JOptionPane.showMessageDialog(this,
					ex.getMessage(),"Eingabefehler!",JOptionPane.ERROR_MESSAGE);
		}
	}
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton || e.getSource() == register) 
			ReadInput();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub	
	}
	
}
 