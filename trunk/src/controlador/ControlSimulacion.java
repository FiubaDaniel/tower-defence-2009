package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JButton;
import modelo.Enemigo;
import modelo.Escenario;
import modelo.Nivel;
import modelo.Obstaculo;


/**
 * Esta clase se encarga del Manejo del GameLoop.
 *   
 */

public class ControlSimulacion implements ActionListener, Runnable {

	private static ControlSimulacion instancia;

	// Este atributo establece si la simulacion se detiene o si continua.
	private boolean pausado = true;
	// Este atributo es para inidicar que se termino el nivel.
	private boolean TerminoNivel = false;
	/* Este atributo establece la cantidad de milisegundos a esperar entre Loop
	 * del simulador
	 */
	private int SleepTime = 100;
	private Escenario escenario;
	private FabricaDeEnemigos fabrica;
	private Nivel nivel;

	/**
	 * Metodo para obtener la unica instancia del controlador
	 * 
	 * @return Devuelve la instancia unica del Controlador.
	 */
	public static ControlSimulacion obtenerControl() {
		if (instancia == null)
			instancia = new ControlSimulacion();
		return instancia;
	}

	private ControlSimulacion() {
		escenario = Escenario.obtenerEscenario();
		fabrica = new FabricaDeEnemigos(escenario.getCantBichos(), nivel.getDificultad());
	}

	/**
	 * Este metodo es llamado si el boton de Pausar-Iniciar es activado.
	 */
	public void actionPerformed(ActionEvent e) {
		pausado = !pausado;
		JButton aux = (JButton) (e.getSource());
		if (pausado)
			aux.setText("Iniciar");
		else
			aux.setText("Pausa");

	}
	
	/**
	 * Este método se encarga de llamar a los metodos atacar
	 * y avanzar de todos los enemigos y obstaculos del juego.
	 * 
	 */
	private void actuar(){
		Iterator iteradorEnemigo = escenario.getIteradordeEnemigos();
		while (iteradorEnemigo.hasNext())
			((Enemigo)iteradorEnemigo).avanzar(escenario);
		Iterator iteradorObstaculo = escenario.getIteradordeObstaculos();
		while (iteradorObstaculo.hasNext())
			((Obstaculo)iteradorObstaculo).atacar();
	}
	
	/**
	 * Este método comienza la simulacion del juego. Depende de los flags
	 * SleepTime, Pausado y Termino_Nivel.
	 */
	public void run() {

		/* En esta variable, como el nombre lo indica guardo el tiempo pasado en
		 * el juego.
		 */
		double tiempo_pasado = 0;
		while ((!TerminoNivel) || (!pausado)){
			
			this.actuar();
			try {
				Thread.sleep(SleepTime);
			} catch (InterruptedException e) {}
			// Aumento el tiempo pasado, y le pongo un tope para no producir
			// OverFlow
			tiempo_pasado++;
			if (tiempo_pasado > 60000)
				tiempo_pasado = 0;
			// Calculo si el tiempo pasado es un multiplo del intervalo
			// entre salida, para ver si saco un enemigo de la fabrica o no
			if (((tiempo_pasado * SleepTime) % fabrica.getIntervalo_entre_salidas()) == 0) {
				Enemigo aux_enemigo = fabrica.getSiguienteEnemigoParaCrear();
				// Pregunto por null, ya que si la fabrica está vacia,
				// devuelve nulls
				if (aux_enemigo != null)
				escenario.agregarEnemigoALista(aux_enemigo);
			}
		}
	}
}
