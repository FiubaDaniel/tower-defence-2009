package controlador;

import javax.swing.JMenuItem;

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
	addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
				public void menuKeyPressed(
						javax.swing.event.MenuKeyEvent evt) {
					Boton_Opciones_Apretado(evt);
				}

				public void menuKeyReleased(
						javax.swing.event.MenuKeyEvent evt) {
				}

				public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
				}
			});
	}
	
	private void Boton_Opciones_Apretado(javax.swing.event.MenuKeyEvent evt) {
		// TODO add your handling code here:
	}

	
}
