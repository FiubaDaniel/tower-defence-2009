package vista.menu;

import controlador.BotonIniciarPartidaListener;


public class BotonIniciarPartida extends javax.swing.JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549189642321921023L;

	public BotonIniciarPartida() {
		setAccelerator(javax.swing.KeyStroke.getKeyStroke( 
				java.awt.event.KeyEvent.VK_I,
				java.awt.event.InputEvent.ALT_MASK));
		setText("Iniciar partida");
		setDoubleBuffered(true);
		addMenuKeyListener(new BotonIniciarPartidaListener());
	}
	
	

}