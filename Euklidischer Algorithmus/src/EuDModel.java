import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JOptionPane;

public class EuDModel {
		
	int a, b, ggt, x, y;	
	static final String GGT_CHANGE = "ggt";
	static final String X_CHANGE = "x";
	static final String Y_CHANGE = "y";

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getGgt() {
		return ggt;
	}
	
	public EuDModel(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public EuDModel() {
		super();
		a = 0;
		b = 0;
	}
	public void ggt() throws NumberFormatException, Exception{
		if(a < 0 || b < 0)
		{
			throw new Exceptions("Negative Zahlen sind nicht erlaubt!");
		}
		else if(a == 0 && b == 0)
		{
			throw new Exceptions("GGT(0,0) ist nicht definiert!");
		}
		else
		{
			int oldggt = ggt;
			x = 0;
			y = 1;
			int lastX = 1;
			int lastY = 0;
			int temp;
			
			while(b != 0)
			{
				int q = a / b;
				int r = a % b;
				
				a = b;
				b = r;
				
				temp = x;
				x = lastX - q * x;
				lastX = temp;
				
				temp = y;
				y = lastY - q * y;
				lastY = temp;
			}
			ggt = a;
			// notify listeners of change
			pcs.firePropertyChange(GGT_CHANGE, oldggt, ggt);
			pcs.firePropertyChange(X_CHANGE, x, lastX);
			pcs.firePropertyChange(Y_CHANGE, y, lastY);

		}
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

}
