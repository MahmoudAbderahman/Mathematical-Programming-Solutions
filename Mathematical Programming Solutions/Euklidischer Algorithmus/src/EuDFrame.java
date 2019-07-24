import java.awt.event.*;

import javax.swing.*;

public class EuDFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public EuDFrame(){
		super("Euklid'scher Algorithmus");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		EuDModel model = new EuDModel();
		EuDView view = new EuDView(model);
		getContentPane().add(view);
		setSize(1000,500);
		pack();
	}
	
	public static void main(String[] args)  {
		EuDFrame ef = new EuDFrame();
		ef.setVisible(true);
		
	}


}
