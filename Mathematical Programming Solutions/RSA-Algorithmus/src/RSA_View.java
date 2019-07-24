/**
 * Class RSA_View that contains the view code for the project.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigInteger;

import javax.swing.*;



public class RSA_View extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	RSA_Model model;
	
	// Buttons
	JButton btnClearKlartext = new JButton("Clear Klartext");
	JButton btnchiffrieren = new JButton("Chiffrieren");
	JButton btndechiffrieren = new JButton("Dechiffrieren");
	JButton btnClearCiphertext = new JButton("Clear Ciphertext");

	
	// Textareas 
	JTextArea klartext = new JTextArea(20,20);
	JTextArea d = new JTextArea(20,20);
	JTextArea cipherText = new JTextArea(20,20);
	JTextArea n = new JTextArea(20,20);	
	JTextArea ee = new JTextArea(20,20);	
	
	
	// ScrollPanes
	JScrollPane scrollpane = new JScrollPane(klartext);
	JScrollPane scrollpane2 = new JScrollPane(cipherText);
	JScrollPane scrollpane3 = new JScrollPane(n);
	JScrollPane scrollpane4 = new JScrollPane(ee);
	JScrollPane scrollpane5 = new JScrollPane(d);



	
	
	public RSA_View(RSA_Model model)  {
		this.model = model;
		model.addPropertyChangeListener(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		
		JLabel label1 = new JLabel("klartext");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		klartext.setAlignmentX(LEFT_ALIGNMENT);
		klartext.setLineWrap(true);
		scrollpane.setPreferredSize(new Dimension(300,200));
		box.add(scrollpane, klartext);
		add(box);


				
		JLabel label2 = new JLabel("N");
		box.add(label2);
		box.add(Box.createVerticalStrut(5));
		n.setAlignmentX(LEFT_ALIGNMENT);
		n.setLineWrap(true);
		scrollpane3.setPreferredSize(new Dimension(300,200));
		box.add(scrollpane3, n);
		
		JLabel label3 = new JLabel("E");
		label3.setAlignmentY(TOP_ALIGNMENT);
		box.add(label3);
		box.add(Box.createVerticalStrut(5));
		ee.setLineWrap(true);

		scrollpane4.setPreferredSize(new Dimension(300,200));
		box.add(scrollpane4, ee);
	
		
		
		add(box);
		
		
		
		Box box2 = Box.createVerticalBox();		
		box2.add(Box.createHorizontalStrut(50));

		box2.add(btnClearKlartext);
		btnClearKlartext.addActionListener(this);
		btnClearKlartext.setAlignmentY(TOP_ALIGNMENT);
		box2.add(Box.createVerticalStrut(10));	
		
		box2.add(btnClearCiphertext);
		btnClearCiphertext.addActionListener(this);
		btnClearCiphertext.setAlignmentY(TOP_ALIGNMENT);
		box2.add(Box.createVerticalStrut(10));

		box2.add(btnchiffrieren);
		btnchiffrieren.addActionListener(this);
		btnchiffrieren.setAlignmentY(TOP_ALIGNMENT);
		box2.add(Box.createVerticalStrut(10));

		box2.add(btndechiffrieren);
		btndechiffrieren.addActionListener(this);
		btndechiffrieren.setAlignmentY(TOP_ALIGNMENT);
		box2.add(Box.createVerticalStrut(10));
		
		add(box2);
		
		
		
		
		
		Box box3 = Box.createVerticalBox();
		box2.add(Box.createHorizontalStrut(50));
		box3.add(new JLabel("D"));
		box3.add(Box.createVerticalStrut(5));
		d.setLineWrap(true);
		scrollpane5.setPreferredSize(new Dimension(300,200));
		box3.add(scrollpane5, d);
		
		box3.add(new JLabel("Cipher Text"));
		box3.add(Box.createVerticalStrut(5));
		cipherText.setLineWrap(true);
		scrollpane2.setPreferredSize(new Dimension(300,200));
		box3.add(scrollpane2, cipherText);
		
		box3.add(Box.createVerticalStrut(15));
		add(box3);	
		
	}

	/**
	 * In this method, we check which action is performed
	 * and regarding the action, we execute the code required.
	 */
	public void actionPerformed(ActionEvent e)  {		
		if (e.getSource() == btnchiffrieren){
			
			try {
				pruefKlartextEingabe();
				readInput();
				model.chiffrieren();
			}
			catch (Exceptions e1) {
				JOptionPane.showMessageDialog(this,
						e1.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == btnClearKlartext) {
			this.klartext.setText("");
		}
		else if(e.getSource() == btndechiffrieren) {
			
			try {
				pruefCiphertextEingabe();
				model.setD(new BigInteger(d.getText()));
				model.setN(new BigInteger(n.getText()));
				model.dechiffrieren();
			} catch (Exceptions e1) {
				JOptionPane.showMessageDialog(this,
						e1.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == btnClearCiphertext) {
			
			this.cipherText.setText("");
		}
		
	}
	
	/**
	 * In this method, we call the method to check the input
	 * and set the value of model's msg to klar text.
	 * @throws Exceptions
	 */
	private void readInput() throws Exceptions {
		pruefKlartextEingabe();
		model.setMsg(this.klartext.getText());
	}
	
	
	/**
	 * In this method is the code for tracing the property changes implemented
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String pn = e.getPropertyName();
		switch(pn) {
		case RSA_Model.RSA_EN:
			cipherText.setText((String) e.getNewValue());
			break;
		case RSA_Model.RSA_D:
			d.setText((String) e.getNewValue());
			break;
		case RSA_Model.RSA_E:
			ee.setText((String) e.getNewValue());
			break;	
		case RSA_Model.RSA_N:
			n.setText((String) e.getNewValue());
			break;	
		case RSA_Model.RSA_DE:
			klartext.setText((String) e.getNewValue());
			break;
		default: 
			throw new IllegalArgumentException("Unknown property name: "+pn);
		}
	}
	/**
	 * In this method, we check if the text key
	 * is in the right format.
	 * @throws Exceptions
	 */
	private void pruefKlartextEingabe() throws Exceptions
	{
		
		if(klartext.getText().equals(""))
		{
			throw new Exceptions("Klar Text darf nicht leer sein!");
		}
	}
	/**
	 * In this method, we check if the cipher text, d and n
	 * are in the right format.
	 * @throws Exceptions
	 */
	private void pruefCiphertextEingabe() throws Exceptions
	{
		if(cipherText.getText().equals(""))
		{
			throw new Exceptions("Klar Text darf nicht leer sein!");
		}
		if(d.getText().equals(""))
		{
			throw new Exceptions("D darf nicht leer sein!");
		}
		if(n.getText().equals(""))
		{
			throw new Exceptions("N darf nicht leer sein!");
		}
	}
}


