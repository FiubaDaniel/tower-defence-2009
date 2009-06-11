package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.menu.VistaPrincipal;

/**
 * Clase encargada del funcionamiento del Botón Comprar Obstaculos

 *
 */

public class BotonComprarListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		BotonComprar_Apretado(arg0);

	}

	/**
	 * Este método activa el modo Insertar_Objeto de la clase Mapa
	 * @param evt
	 */
	public void BotonComprar_Apretado(ActionEvent evt) {
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(null, null, null);
		
		boolean aux = !vistaP.getMapa().isInsetar_objeto();
		vistaP.getMapa().setInsetar_objeto(aux);	
		
	}
}
