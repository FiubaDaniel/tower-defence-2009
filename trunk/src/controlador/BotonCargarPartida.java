package controlador;



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
		addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
			public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
				Boton_Cargar_Apretado(evt);
			}

			public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
			}

			public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
			}
		});
		
	}

	private void Boton_Cargar_Apretado(javax.swing.event.MenuKeyEvent evt) {// GEN-FIRST:event_Boton_Cargar_Handler
		// TODO add your handling code here:
	}// GEN-LAST:event_Boton_Cargar_Handler

	
}
