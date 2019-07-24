/**
 * Class CiphersFrame that contains the required code to display the window
 * and its contents.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 * Mulham Alesali:20203753
 * Nawid Shadab:20201725
 * Mahmoud Abderahman:20202225
 */
package Frame;
import java.awt.event.*;
import javax.swing.*;

import Model.CiphersModel;
import View.CiphersView;


public class CiphersFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public CiphersFrame(){
		super("Cäsar- und Vigenère-Code");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		CiphersModel model = new CiphersModel(this);
		CiphersView view = new CiphersView(model);
		getContentPane().add(view);
		pack();
	}
	
	public static void main(String[] args)  {
		CiphersFrame f = new CiphersFrame();
		f.setVisible(true);
		
	}

}