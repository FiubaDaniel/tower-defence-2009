package vista.menu;

import javax.swing.JMenuItem;

import controlador.BotonGuardarPartidaListener;

public class BotonGuardarPartida extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8495732480453366171L;

	public BotonGuardarPartida(){
		setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_G,
				java.awt.event.InputEvent.ALT_MASK));
		setText("Guardar Partida");
		addMenuKeyListener(new BotonGuardarPartidaListener());
	}
	
	
	
}
