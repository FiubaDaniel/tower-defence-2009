package vista.menu;

import javax.swing.JMenuItem;

import controlador.BotonAyudaListener;

public class BotonAyuda extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2678074626012242531L;

	public BotonAyuda() {
		
		setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Y,
				java.awt.event.InputEvent.ALT_MASK));
		setText("Ayuda");
		addMenuKeyListener(new BotonAyudaListener());
		
	}
	
	
	
}
