package controlador;

import java.util.Iterator;

//import javax.swing.JButton;

import vista.audio.ReproductorAudio;
import vista.menu.VistaPrincipal;

import customExceptions.EnemigoYaMuerto;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.FabricaDeEnemigos;
import modelo.Jugador;
import modelo.Obstaculo;
import modelo.Posicion;

/**
 * Desede esta clase se realizan las invocaciones necesarias,
 * para hacer fincionar el modelo.
 * 

 *
 */
public class ControlSimulacion {

	private static ControlSimulacion instancia = null;
	// Este atributo establece si la simulacion se detiene o si continua
	private boolean pausado = true;
	private boolean terminoJuego = false;
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
		
		return instancia;
	}
	
	/**
	 * Instancio el modelo.
	 */
	private ControlSimulacion(){
		escenario = Escenario.obtenerEscenario();
		fabrica = FabricaDeEnemigos.obtenerFabricaEnemigos(escenario.getCantBichos(), escenario.getNumeroNivel());
	}
	
	public void reiniciarSimulacion(){
		escenario.reIniciar();
		fabrica.reiniciarFabrica();
  		Jugador jugador = Jugador.obtenerJugador();
  		jugador.reiniciarJugador();
  		this.pausado = true;
  		this.terminoJuego = false;
  	}
	
	public boolean isTerminoJuego(){
		return terminoJuego;
	}
	
	public boolean isPausado(){
		return pausado;
	}
	
	public void pausarSimulacion(){
		pausado = true;
	}
	
	public void despausarSimulacion() {
		pausado = false;
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
	
	private void VerificarSiTerminoNivel() {
		if (escenario.isTerminoNivel()){
			pausado = true;
			if (!escenario.isMapasDisponibles())
				terminoJuego = true;
			VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal();
			vistaP.getPanelDatos().cambiarEtiquetaIniciar_Pausar();			
			fabrica.crearNuevosEnemigos(escenario.getCantBichos());
			//toco sonido
			ReproductorAudio repro = ReproductorAudio.getInstancia();
			repro.stop();
			if(escenario.getNumeroNivel()==2)
				repro.reproducirCancion(ReproductorAudio.CANCION_NIVEL2);
			if(escenario.getNumeroNivel()==3)
				repro.reproducirCancion(ReproductorAudio.CANCION_NIVEL3);	
		}
		
	}
	
	/**
	 * Este metodo es el que se ocupa de llamar a las acciones
	 * necesarias para mover, disparar e ir agregando enemigos
	 * a la pantalla.
	 */
	public void actuar(){
		this.agregarEnemigos();
		this.avanzar();
		this.atacar();
		this.VerificarSiTerminoNivel();
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
					terminoJuego = true;
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
	
	public int getSleepTime() {
		return SleepTime;
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
}
