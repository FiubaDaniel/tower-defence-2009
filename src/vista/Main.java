package vista;

import modelo.Escenario;
import modelo.Jugador;
import vista.menu.VistaPrincipal;


public class Main {

	/**
	 * 
	 * Dónde llamare a todo.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	
	Escenario escenario = Escenario.obtenerEscenario();
	Jugador jugador = Jugador.obtenerJugador(50, 1000, "Exus");
	
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VistaPrincipal.obtenerVistaPrincipal().setVisible(true);
			}
		});
	}

}
