package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.swing.JButton;

import customExceptions.EnemigoYaMuerto;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Nivel;
import modelo.Obstaculo;
import modelo.Posicion;

/**
 * Desede esta clase se realizan las invocaciones necesarias,
 * para hacer fincionar el modelo.
 * 

 *
 */
public class ControlSimulacion  implements ActionListener{

	private static ControlSimulacion instancia = null;
	
	// Este atributo establece si la simulacion se detiene o si continua
	private boolean pausado = true;
	
	private boolean terminoNivel = false;
	
	private FabricaDeEnemigos fabrica;
	
	private Escenario escenario;
	
	/* Este atributo establece la cantidad de milisegundos a esperar entre Loop
	 * del simulador
	 */
	private int SleepTime = 100;
	
	/* En esta variable, como el nombre lo indica guardo el tiempo pasado en
	 * el juego
	 */
	private double tiempo_pasado = 0;
	
	public static ControlSimulacion obtenerControl(){
		if (instancia == null)
			instancia = new ControlSimulacion();
		else
			return instancia;
		return instancia;
	}
	
	/**
	 * Instancio el modelo.
	 */
	private ControlSimulacion(){

		escenario = Escenario.obtenerEscenario();
		try {
			escenario.setNumeroNivel(1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Jugador jugador = Jugador.obtenerJugador();
		Nivel NivelGeneral = Nivel.obtenerNivel(escenario, jugador);
		fabrica = new FabricaDeEnemigos(escenario.getCantBichos(), escenario.getNumeroNivel());
	}
	
	public boolean isTerminoNivel(){
		return terminoNivel;
	}
	
	public boolean isPausado(){
		return pausado;
	}
	
	private void agregarEnemigos(){
		
		/* Calculo si el tiempo pasado es un multiplo del intervalo
		 * entre salida, para ver si saco un enemigo de la fabrica o no
		 */
		if (((tiempo_pasado * SleepTime) % fabrica
				.getIntervalo_entre_salidas()) == 0) {
			Enemigo aux_enemigo = fabrica
					.getSiguienteEnemigoParaCrear();
			// Pregunto por null, ya que si la fabrica está vacia,
			// devuelve nulls
			if (aux_enemigo != null)
				escenario.agregarEnemigoALista(aux_enemigo);
		}
	}
	
	/**
	 * Este metodo es el que se ocupa de llamar a las acciones
	 * necesarias para mover, disparar e ir agregando enemigos
	 * a la pantalla.
	 * 
	 */
	public void actuar(){
		this.agregarEnemigos();
		this.avanzar();
		this.atacar();
		this.aumentarTiempoPasado();
	}
	
	private void avanzar(){
		Iterator itEnemigos = escenario.getIteradordeEnemigos();
		Posicion salida = escenario.getSalida();
		Jugador player = Jugador.obtenerJugador();
		while (itEnemigos.hasNext()){
			Enemigo enemigo = (Enemigo) itEnemigos.next();
			for (int i=0;i<enemigo.getVelocidad();i++){
				enemigo.avanzar(escenario);
				if (salida.equals(enemigo.getPosicion()))
					player.quitarVida();
				if (player.getCantidadVidas() == 0){
					terminoNivel = true;
					pausado = true;
					break;
				}
			}
		}
	}
	
	private void atacar(){
		Iterator itObstaculos = escenario.getIteradordeObstaculos();
		while (itObstaculos.hasNext()){
			Obstaculo obstaculo = (Obstaculo) itObstaculos.next();
			try {
				obstaculo.atacar();
			} catch (EnemigoYaMuerto e){
			}catch (Exception e) {
			}
		}
	}
	
	/**
	 * Aumento el tiempo pasado, y le pongo un tope para no producir
	 * OverFlow
	 */
	private void aumentarTiempoPasado(){
		tiempo_pasado++;
		if (tiempo_pasado > 60000)
			tiempo_pasado = 0;
	}
	
	/**
	 * Este metodo es llamado si el boton de Pausar-Iniciar es activado.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		pausado = !pausado;
		JButton aux = (JButton) (e.getSource());
		if (pausado)
			aux.setText("Iniciar");
		else
			aux.setText("Pausa");

	}
}
