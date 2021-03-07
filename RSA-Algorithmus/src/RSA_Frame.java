/**
 * Class RSA_Frame that contains the required code to display the window
 * and its contents.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 * 
 */
import java.awt.event.*;
import javax.swing.*;



public class RSA_Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public RSA_Frame(){
		super("RSA Algorithmus");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		RSA_Model model = new RSA_Model(this);
		RSA_View view = new RSA_View(model);
		getContentPane().add(view);
		pack();
	}
	
	/**
	 * Entry point to the program, will at first be executed.
	 * @param args
	 */
	public static void main(String[] args)  {
		RSA_Frame f = new RSA_Frame();
		f.setVisible(true);
		
	}

}