package controlador;

import vista.audio.ReproductorAudio;
import controlador.ControlVista;
import controlador.ControlSimulacion;

public class GameLoop {

	/**
	 * Clase principal donde se realiza el ciclo del juego.
	 */
	public static void main(String[] args) {
		
		//Instancio el reproductor de Audio.
		ReproductorAudio repro = ReproductorAudio.getInstancia();
		//repro.reproducirCancion(ReproductorAudio.CANCION_INTRO);
		
		//Instancio los controladores.
		ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
		ControlVista vista = ControlVista.obtenerControl();
		
		//Corazón del GameLoop
		while (!simulacion.isTerminoNivel()){
			while (!simulacion.isPausado()){
				simulacion.actuar();
				vista.actualizarVista();
			}
			
			try {
				Thread.sleep(simulacion.getSleepTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		vista.finDeJuego();
	}

}
