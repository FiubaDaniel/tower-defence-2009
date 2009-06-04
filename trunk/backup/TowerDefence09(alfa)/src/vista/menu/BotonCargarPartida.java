package vista.menu;

import controlador.BotonCargarPartidaListener;



public class BotonCargarPartida extends javax.swing.JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8751562348075177204L;

	public BotonCargarPartida() {
		setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_C,
				java.awt.event.InputEvent.ALT_MASK));
		setText("Cargar Partida");
		addMenuKeyListener(new BotonCargarPartidaListener());
		
	}

	

	
}
