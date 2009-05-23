package controlador;

import javax.swing.JMenuItem;

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
		addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
			public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
				Boton_Ayuda_Apretado(evt);
			}

			public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
			}

			public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
			}
		});
		
	}
	
	private void Boton_Ayuda_Apretado(javax.swing.event.MenuKeyEvent evt) {
		// TODO add your handling code here:
	}
	
}
