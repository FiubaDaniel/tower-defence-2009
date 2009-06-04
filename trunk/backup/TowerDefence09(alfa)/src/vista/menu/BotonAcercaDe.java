package vista.menu;

import javax.swing.JMenuItem;

import controlador.BotonAcercaDeListener;

public class BotonAcercaDe extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1644479687034658035L;

	public BotonAcercaDe() {

		setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_D,
				java.awt.event.InputEvent.ALT_MASK));
		setText("Acerca de...");
		addMenuKeyListener(new BotonAcercaDeListener());

	}

	

}
