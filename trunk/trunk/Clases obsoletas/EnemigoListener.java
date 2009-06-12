package controlador;

import java.util.Observable;
import java.util.Observer;

import vista.menu.Mapa;
import vista.menu.MenuSuperior;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Posicion;

/**
 * Clase encargada de observar los cambios de la clase enemigos, para así 
 * cambiar los valores del menú izquierdo.

 *
 */

public class EnemigoListener implements Observer {

	public void update(Observable arg0, Object arg1) {

		Escenario escenario = Escenario.obtenerEscenario();
		Posicion salida = escenario.getSalida();
		Posicion actual = ((Enemigo) arg0).getPosicion();
		
		
		
		if (actual.getCoordX() == salida.getCoordX()
				&& actual.getCoordY() == salida.getCoordY()) {

			// Si el enemigo llego a la salida, vuelve a aparecer en la entrada
			// y disminuye un punto de vida al jugador

			Jugador jugador = Jugador.obtenerJugador();
			
			//jugador.AgregarQuitarVida(-1);
			((Enemigo) arg0).cambiarPosicion(escenario.getEntrada());
			
			VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal();
			
			vistaP.getPanelDatos().paint(vistaP.getPanelDatos().getGraphics());
			
		}

	}

}
