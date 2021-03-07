import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class EuDView extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	EuDModel model;
	
	JButton compute = new JButton("Berechnen");

	JTextField a = new JTextField("",35);
	JTextField b = new JTextField("",35);
	JTextField g = new JTextField("",35);
	JTextField x = new JTextField("",35);
	JTextField y = new JTextField("",35);

	
	
	public EuDView(EuDModel model)  {
		this.model = model;
		model.addPropertyChangeListener(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		a.setAlignmentX(LEFT_ALIGNMENT);
		box.add(a);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		b.setAlignmentX(LEFT_ALIGNMENT);
		box.add(b);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(compute);
		add(box);		
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  ggt"));		
		box2.add(Box.createVerticalStrut(5));
		g.setAlignmentX(LEFT_ALIGNMENT);
		g.setEditable(false);
		box2.add(g);
		add(box2);
		box2.add(new JLabel("   x"));
		box2.add(Box.createVerticalStrut(5));
		x.setAlignmentX(LEFT_ALIGNMENT);
		x.setEditable(false);
		box2.add(x);
		box2.add(new JLabel("   y"));
		box2.add(Box.createVerticalStrut(5));
		y.setAlignmentX(LEFT_ALIGNMENT);
		y.setEditable(false);
		box2.add(y);
		
	}

	private void readInput() throws Exception{
		try {
			if(a.getText().equals("") || b.getText().equals(""))
			{
				throw new Exceptions("Sie haben keinen Wert fuer A oder B eingegeben!");
			}
			model.setA(Integer.valueOf(a.getText()));
			model.setB(Integer.valueOf(b.getText()));
			model.ggt();
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		}
		catch (Exceptions e)
		{
			JOptionPane.showMessageDialog(this,
					e.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute)
			try {
				readInput();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// The switch is not strictly necessary in this example as there is
		// only one property "ggt". Do it anyway to show the general pattern.
		String pn = e.getPropertyName();
		switch(pn) {
		case EuDModel.GGT_CHANGE:
			// process change of "ggt" property
			g.setText(e.getNewValue()+"");
			break;
		case EuDModel.X_CHANGE:
			x.setText(e.getNewValue()+"");
			break;
		case EuDModel.Y_CHANGE:
			y.setText(e.getNewValue()+"");
			break;	
		default: 
			throw new IllegalArgumentException("Unknown property name: "+pn);
		}
	}
}



