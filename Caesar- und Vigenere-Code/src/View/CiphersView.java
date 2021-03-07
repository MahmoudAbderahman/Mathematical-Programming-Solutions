/**
 * Class CiphersView that contains the view code for the project.
 * 
 * @autor Mahmoud Abdelrahman, Mulhamam Alisali, Nawid Shadab
 * 
 */
package View;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import Model.CiphersModel;
import Model.Exceptions;

public class CiphersView extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	CiphersModel model;
	JButton btnclear = new JButton("clear");
	JButton btnopenPlainText = new JButton("openPlainText");
	JButton btnopenCipherText = new JButton("openCipherText");
	
	JButton btnchiffrieren = new JButton("Chiffrieren");
	JButton btndechiffrieren = new JButton("Dechiffrieren");
	
	JTextArea klartext = new JTextArea(20,20);	
	JTextField txtkey = new JTextField("",10);
	JTextArea cipherText = new JTextArea(20,20);
	
	JScrollPane scrollpane = new JScrollPane(klartext);
	JScrollPane scrollpane2 = new JScrollPane(cipherText);

	
	@SuppressWarnings("unused")
	private static File openFile(Frame f) {
		 
        String filename = File.separator + "txt";
        JFileChooser fileChooser = new JFileChooser(new File(filename));
 
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // only directories
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // only files 
        // pop up an "Open File" file chooser dialog
        fileChooser.showOpenDialog(f);

        return fileChooser.getSelectedFile();
 
    }
	
	
	
	public CiphersView(CiphersModel model)  {
		this.model = model;
		model.addPropertyChangeListener(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("klartext");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		klartext.setAlignmentX(LEFT_ALIGNMENT);
		klartext.setLineWrap(true);
	
		
		scrollpane.setPreferredSize(new Dimension(400,400));
		box.add(scrollpane, klartext);
		add(box);
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		
		//Create the combo box, select item at index 4.
		String[] combolist = {"Caeser", "Vigenere"};
		
		box2.add(Box.createVerticalStrut(20));
		
		
		JLabel lblkey = new JLabel("key");
		lblkey.setAlignmentX(TOP_ALIGNMENT);
		box2.add(lblkey);
		box2.add(Box.createVerticalStrut(5));
		box2.add(txtkey);
		
		box2.add(Box.createVerticalStrut(20));
		btnopenCipherText.addActionListener(this);
		btnopenCipherText.setAlignmentX(LEFT_ALIGNMENT);
		btnopenCipherText.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnopenCipherText);
		
		box2.add(Box.createVerticalStrut(20));
		btnclear.addActionListener(this);
		btnclear.setAlignmentX(LEFT_ALIGNMENT);
		btnclear.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnclear);
		
		box2.add(Box.createVerticalStrut(20));
		btnopenPlainText.addActionListener(this);
		btnopenPlainText.setAlignmentX(LEFT_ALIGNMENT);
		btnopenPlainText.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnopenPlainText);
		
		box2.add(Box.createVerticalStrut(20));
		btnchiffrieren.addActionListener(this);
		btnchiffrieren.setAlignmentX(LEFT_ALIGNMENT);
		btnchiffrieren.setAlignmentY(TOP_ALIGNMENT);
		btnchiffrieren.setBounds(50, 50, 100, 100);
		box2.add(btnchiffrieren);
		
		box2.add(Box.createVerticalStrut(20));
		btndechiffrieren.addActionListener(this);
		btndechiffrieren.setAlignmentX(LEFT_ALIGNMENT);
		btndechiffrieren.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btndechiffrieren);
		
		add(box2);
		
		
		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		box3.add(new JLabel("cipher Text"));
		box3.add(Box.createVerticalStrut(5));
		cipherText.setAlignmentX(LEFT_ALIGNMENT);
		cipherText.setLineWrap(true);
		scrollpane2.setPreferredSize(new Dimension(400,400));
		box3.add(scrollpane2, cipherText);
		box3.add(Box.createVerticalStrut(15));
		add(box3);	

	}

	/**
	 * In this method, we check which action is performed
	 * and regarding the action, we implement the code required.
	 */
	public void actionPerformed(ActionEvent e)  {		
		if (e.getSource() == btnchiffrieren){
			
			try {
				if(this.klartext.getText().equals(""))
				{
					throw new Exceptions("Klar Text darf nicht leer sein!");
				}
				pruefKey();

				readInput();
				model.chiffrieren();
			}
			catch (Exceptions e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,
						e1.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		else if(e.getSource() == btnclear) {
			this.cipherText.setText("");
			this.klartext.setText("");
			readInput();
		}
		else if(e.getSource() == btndechiffrieren) {
			
			try
			{
				if(this.cipherText.getText().equals(""))
				{
					throw new Exceptions("Chipher Text darf nicht leer sein!");

				}
				pruefKey();
				readInput();
				model.dechiffrieren();
			}
			catch(Exceptions e1)
			{
				JOptionPane.showMessageDialog(this,
						e1.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(e.getSource() == btnopenCipherText) {
			model.setFile(openFile(model.getFrame()));
			try {
				this.cipherText.setText(model.readFile(model.getFile()));
				readInput();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exceptions e1) {
				// TODO Auto-generated catch block
		        JOptionPane.showMessageDialog(null ,e1.getMessage(), "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

			}
		
			
		}
		else if(e.getSource() == btnopenPlainText) {
			
			model.setFile(openFile(model.getFrame()));
			try {
				klartext.setText(model.readFile(model.getFile()));
				readInput();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exceptions e1) {
				// TODO Auto-generated catch block
		        JOptionPane.showMessageDialog(null ,e1.getMessage(), "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
	}
	
	public void readInput() {
		model.setKlartext(this.klartext.getText());
		model.setKey(this.txtkey.getText());
		model.setCiphertext(this.cipherText.getText());
	}
	
	
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		
		
		String pn = e.getPropertyName();
	
		switch(pn) {
		case CiphersModel.VIGENERE_EN:
			cipherText.setText((String) e.getNewValue());
			break;

		case CiphersModel.VIGENERE_DE:
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
	private void pruefKey() throws Exceptions
	{
		if(txtkey.getText().equals(""))
		{
			throw new Exceptions("Key darf nicht leer sein!");
		}
		if(!txtkey.getText().matches("[a-zA-Z]+"))
		{
			throw new Exceptions("Key darf nur alphabatischen Inhalt enthalten!");
		}
	}
}


