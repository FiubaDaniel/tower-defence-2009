package controlador;

import javax.swing.JButton;

/**
 * Este es ek botón para actualizar las torres.
 * 
 *
 */

public class BotonUpdate extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855543925543805174L;


	
	public BotonUpdate() {
		setText("Precio");
		addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonUpdate_Apretado(evt);
            }
        });
	}
	
	private void BotonUpdate_Apretado(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		}
	
}
