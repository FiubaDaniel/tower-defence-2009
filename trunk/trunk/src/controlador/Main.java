package controlador;

import java.io.FileNotFoundException;


import modelo.Escenario;
import modelo.Jugador;
import modelo.Nivel;
import vista.audio.ReproductorAudio;
import vista.menu.Mapa;
import vista.menu.MenuSuperior;
import vista.menu.PanelDatosDeSeleccion;
import vista.menu.PanelDatosDeTorres;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;
/**
 * Clase dónde se encuentran las declaraciones de todos los objetos principales
 * de la aplicación.
 * 
 * @author grupo 4
 *
 */
public class Main {

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

		
		//Instancio el reproductor de Audio
		
	/*	ReproductorAudio repro = ReproductorAudio.getInstancia();
		repro.reproducirCancion(ReproductorAudio.CANCION_INTRO);*/
		
		// Instancio los objetos de la ventana
		PanelDatosDeTorres PanelTorres = new PanelDatosDeTorres();
		PanelDatosDeSeleccion PanelSeleccion = new PanelDatosDeSeleccion();
		MenuSuperior MenuArchivoAyuda = new MenuSuperior();
		PanelDeDatos PanelDatos = new PanelDeDatos(PanelSeleccion, PanelTorres);
		Mapa PanelMapa = new Mapa();
		
		
		//Cargo el Frame Principal
		VistaPrincipal VentanaPrincipal = VistaPrincipal.obtenerVistaPrincipal(
				MenuArchivoAyuda, PanelDatos, PanelMapa);
		VentanaPrincipal.setVisible(true);

		//Agrego un Observador de jugador
		jugador.addObserver(PanelDatos);
		
		//Instancio el controlador del GameLoop
		ControladorDios Dios = ControladorDios.obtenerControlador();
		
		//Corro el Simulador
		Dios.iniciar_juego();
		
	}
}
