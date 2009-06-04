package vista.menu;

import javax.swing.JButton;

import controlador.BotonUpdateListener;

/**
 * Este es ek botón para actualizar las torres.
 * 
 *
 */

public class BotonUpdate extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855543925543805174L;


	
	public BotonUpdate() {
		setText("Precio");
		addActionListener(new BotonUpdateListener());
	}
	
	
	
}
