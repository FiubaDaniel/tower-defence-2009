package vista.menu;

import javax.swing.JMenuItem;

import controlador.BotonMenuOpcionesListener;

public class BotonMenuOpciones extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5921746710297159734L;

	public BotonMenuOpciones() {
	
	setAccelerator(javax.swing.KeyStroke.getKeyStroke(
			java.awt.event.KeyEvent.VK_P,
			java.awt.event.InputEvent.ALT_MASK));
	setText("Opciones");
	addMenuKeyListener(new BotonMenuOpcionesListener());
	}
	
	

	
}
