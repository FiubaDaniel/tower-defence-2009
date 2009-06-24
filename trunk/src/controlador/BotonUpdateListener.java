package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Jugador;
import modelo.Torre;
import vista.menu.Mapa;
import vista.menu.VistaPrincipal;

/**
 * Clase encargada del funcionamiento del Botón Actualizar Torre

 *
 */

public class BotonUpdateListener implements ActionListener {

  Jugador jugador = Jugador.obtenerJugador();
	
	public void actionPerformed(ActionEvent e) {
		BotonUpdate_Apretado(e);
	}
	
	public void BotonUpdate_Apretado(ActionEvent evt){
		
		VistaPrincipal Vista = VistaPrincipal.obtenerVistaPrincipal();
		Mapa MapaActual = Vista.getMapa();
		Torre torre = (Torre) MapaActual.getObjeto_seleccionado();
		if (jugador.getDinero()>torre.getCostoEvolucion())
		torre.evolucionarce();
	}
}
