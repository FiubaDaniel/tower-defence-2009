package controlador;

import javax.swing.JMenuItem;

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
		addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
			public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
				Boton_Guardar_Apretado(evt);
			}

			public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
			}

			public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
			}
		});
	}
	
	private void Boton_Guardar_Apretado(javax.swing.event.MenuKeyEvent evt) {
		// TODO add your handling code here:
	}
	
}
