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

		

		
		
		
		
		
		//Instancio el controlador del GameLoop
		ControladorDios Dios = ControladorDios.obtenerControlador();
		
		//Corro el Simulador
		Dios.iniciar_juego();
		
	}
}
