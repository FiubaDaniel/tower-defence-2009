package controlador;

import vista.audio.ReproductorAudio;
import controlador.ControlVista;
import controlador.ControlSimulacion;

public class GameLoop {

	/**
	 * Clase principal donde se realiza el ciclo del juego.
	 */
	public static void main(String[] args) {
		
		//Instancio el reproductor de Audio
		
		ReproductorAudio repro = ReproductorAudio.getInstancia();
		repro.reproducirCancion(ReproductorAudio.CANCION_INTRO);
		
		
		ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
		ControlVista vista = ControlVista.obtenerControl();
		
		while (!simulacion.isTerminoNivel())
				while (!simulacion.isPausado()){
					simulacion.actuar();
					vista.actualizarVista();
		}

	}

}
