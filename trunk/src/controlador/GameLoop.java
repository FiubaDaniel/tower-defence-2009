package controlador;

import vista.audio.ReproductorAudio;
import controlador.ControlVista;
import controlador.ControlSimulacion;

public class GameLoop {
	
	public void Jugar() {
		//Instancio el reproductor de Audio.
		ReproductorAudio repro = ReproductorAudio.getInstancia();
		repro.reproducirCancion(ReproductorAudio.CANCION_INTRO);
		
		//Instancio los controladores.
		ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
		ControlVista vista = ControlVista.obtenerControl();
		

		//Corazon del GameLoop
	   
		while (!simulacion.isTerminoJuego()){
			while (!simulacion.isPausado()){
				simulacion.actuar();
				vista.actualizarVista();
			}
			vista.finDeJuego();
			
			try {
				Thread.sleep(simulacion.getSleepTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Clase principal donde se realiza el ciclo del juego.
	 */
	public static void main(String[] args) {
		GameLoop juego = new GameLoop();
		juego.Jugar();
		
	}

}
