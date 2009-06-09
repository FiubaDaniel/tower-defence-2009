package controlador;

import java.io.FileNotFoundException;
import java.util.Iterator;

import customExceptions.EnemigoYaMuerto;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Nivel;
import modelo.Obstaculo;

public class ControlSimulacion {

	private static ControlSimulacion instancia = null;
	
	private boolean termino_nivel = false;
	
	private FabricaDeEnemigos fabrica;
	
	private Escenario escenario;
	
	// Este metodo establece la cantidad de milisegundos a esperar entre Loop
	// del simulador
	private int SleepTime = 100;
	
	// En esta variable, como el nombre lo indica guardo el tiempo pasado en
	// el juego
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
		Jugador jugador = Jugador.obtenerJugador(50, 1000, "Grupo 4");
		Nivel NivelGeneral = Nivel.obtenerNivel(escenario, jugador);
		fabrica = new FabricaDeEnemigos(escenario.getCantBichos(), escenario.getNumeroNivel());
	}
	
	public void agregarEnemigos(){
		
		// Calculo si el tiempo pasado es un multiplo del intervalo
		// entre salida, para ver si saco un enemigo de la fabrica o no
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
	
	public void actuar(){
		Iterator it_en = escenario.getIteradordeEnemigos();
		while (it_en.hasNext()){
			Enemigo aux = (Enemigo) it_en.next();
			if (((SleepTime * tiempo_pasado) % (200 / aux
					.getVelocidad())) == 0)
				aux.avanzar(escenario);
		}
		Iterator it_obs = escenario.getIteradordeObstaculos();
		while (it_obs.hasNext()){
			Obstaculo aux = (Obstaculo) it_obs.next();
			if (((SleepTime * tiempo_pasado) % (200 / aux
					.getVelocidadDisparo())) == 0)
				try {
					aux.atacar();
				} catch (EnemigoYaMuerto e) {
				} catch (Exception e) {
				}
		}
		this.aumentarTiempoPasado();
	}
	
	private void aumentarTiempoPasado(){
		// Aumento el tiempo pasado, y le pongo un tope para no producir
		// OverFlow
		tiempo_pasado++;
		if (tiempo_pasado > 60000)
			tiempo_pasado = 0;
	}
	
}
