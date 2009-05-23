package controlador;


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
		addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
			public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
				Boton_Salir_Handler(evt);
			}

			public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
			}

			public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
			}
		});
	}

	private void Boton_Salir_Handler(javax.swing.event.MenuKeyEvent evt) {


	}

}
