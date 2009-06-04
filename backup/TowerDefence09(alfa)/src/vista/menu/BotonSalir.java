package vista.menu;

import controlador.BotonSalirListener;


public class BotonSalir extends javax.swing.JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3050958118437893464L;

	public BotonSalir() {
		setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.ALT_MASK));
		setText("Salir");
		addMenuKeyListener(new BotonSalirListener());
	}

	

}
