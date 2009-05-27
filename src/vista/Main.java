package vista;

import java.io.FileNotFoundException;

import controlador.ControladorDios;

import modelo.Escenario;
import modelo.Jugador;
import modelo.Nivel;
import vista.menu.Mapa;
import vista.menu.MenuSuperior;
import vista.menu.PanelDatosDeSeleccion;
import vista.menu.PanelDatosDeTorres;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;

public class Main {

	/**
	 * 
	 * Dónde llamare a todo.
	 * 
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String args[]) {

		// Instancio el modelo.
		Escenario escenario = Escenario.obtenerEscenario();
		try {
			escenario.setNumeroNivel(1);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Jugador jugador = Jugador.obtenerJugador(50, 1000, "Exus");
		Nivel NivelGeneral = Nivel.obtenerNivel(escenario, jugador);

		// Instancio los objetos de la ventana
		PanelDatosDeTorres PanelTorres = new PanelDatosDeTorres();
		PanelDatosDeSeleccion PanelSeleccion = new PanelDatosDeSeleccion();
		MenuSuperior MenuArchivoAyuda = new MenuSuperior();
		PanelDeDatos PanelDatos = new PanelDeDatos(PanelSeleccion, PanelTorres);
		Mapa PanelMapa = new Mapa();

		VistaPrincipal VentanaPrincipal = VistaPrincipal.obtenerVistaPrincipal(
				MenuArchivoAyuda, PanelDatos, PanelMapa);
		VentanaPrincipal.setVisible(true);

		jugador.addObserver(PanelDatos);
		
		ControladorDios Dios = ControladorDios.obtenerControlador();
		
		Dios.setPausado(false);
		
		Dios.iniciar_juego();
		
	}
}
