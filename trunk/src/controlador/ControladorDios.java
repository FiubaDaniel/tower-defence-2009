package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;

import vista.Seleccionable;
import vista.dibujadores.VistaArania;
import vista.dibujadores.VistaArena;
import vista.dibujadores.VistaCucaracha;
import vista.dibujadores.VistaHormiga;
import vista.dibujadores.VistaMosca;
import vista.dibujadores.VistaObjetoDeMapa;
import vista.dibujadores.VistaPegote;
import vista.dibujadores.VistaTorreAzul;
import vista.dibujadores.VistaTorreBlanca;
import vista.dibujadores.VistaTorreDorada;
import vista.dibujadores.VistaTorrePlateada;
import vista.menu.Mapa;
import vista.menu.MenuSuperior;
import vista.menu.PanelDatosDeSeleccion;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Obstaculo;
import customExceptions.EnemigoYaMuerto;

/**
 * Esta clase se encarga del Manejo del GameLoop. Ademos Implementa la interfaz
 * ActionListener para poder observar los cambios en el boton Iniciar-Pausar
 * 
 * @author exus
 * 
 */

public class ControladorDios implements ActionListener {

	private static ControladorDios dios;

	// Este atributo establece si la simulacion se detiene o si ocntinua
	private boolean pausado = true;

	// Este metodo establece la cantidad de milisegundos a esperar entre Loop
	// del simulador
	private int SleepTime = 100;
	private boolean termino_nivel = false;

	private HashMap TablaVistas;

	/**
	 * Metodo para obtener la unica instancia del controlador
	 * 
	 * @return Devuelve la isntancia unica del Controlador.
	 */
	public static ControladorDios obtenerControlador() {
		if (dios == null)
			dios = new ControladorDios();
		return dios;
	}

	private ControladorDios() {
		CargarTabla();
	}

	/**
	 * Carga la tabla de equivalencias entre un tipo de enemigos y su Vista
	 * Correspondiente. Es decir al recibir el string especificado, la tabla
	 * devuelve la clase que dibuja ese tipo de Objeto
	 */
	private void CargarTabla() {
		TablaVistas = new HashMap();
		TablaVistas.put("Hormiga", new VistaHormiga(0, 0));
		TablaVistas.put("Arania", new VistaArania(0, 0));
		TablaVistas.put("Cucaracha", new VistaCucaracha(0, 0));
		TablaVistas.put("Mosca", new VistaMosca(0, 0));
		TablaVistas.put("Torre Blanca", new VistaTorreBlanca(0, 0));
		TablaVistas.put("Torre Azul", new VistaTorreAzul(0, 0));
		TablaVistas.put("Torre Dorada", new VistaTorreDorada(0, 0));
		TablaVistas.put("Torre Plateada", new VistaTorrePlateada(0, 0));
		TablaVistas.put("Arena", new VistaArena(0, 0));
		TablaVistas.put("Pegote", new VistaPegote(0, 0));
	}

	/**
	 * 
	 * @return Devuelve el tiempo entre Loops de simulacion
	 */
	public int getSleepTime() {
		return SleepTime;
	}

	/**
	 * Establece el tiempo entre Loops de simulacion
	 * 
	 * @param sleepTime
	 */
	public void setSleepTime(int sleepTime) {
		SleepTime = sleepTime;
	}

	/**
	 * Este método comienza la simulacion del juego. Depende de los flags
	 * SleepTime, Pausado y Termino_Nivel
	 */
	public void iniciar_juego() {

		MenuSuperior menuArchivoAyuda = null;
		PanelDeDatos paneldedatos = null;
		Mapa mapa = null;

		// Obtengo el Frame principal del juego, desde el cual puedo obtener
		// todos los componentes del mismo. Lo llamo con arguementos nulos,
		// porque solo busco la instancia ya creada, no crear una nueva
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(
				menuArchivoAyuda, paneldedatos, mapa);

		mapa = vistaP.getMapa();

		Escenario escenario = Escenario.obtenerEscenario();

		FabricaDeEnemigos fabrica = new FabricaDeEnemigos(escenario
				.getCantBichos(), escenario.getNumeroNivel());

		// En esta variable, como el nombre lo indica guardo el tiempo pasado en
		// el juego
		double tiempo_pasado = 0;

		// Instancio un obsjeto de vista, para usar como auxiliar y manejar las
		// vistas necesarias
		VistaObjetoDeMapa vistaObjeto;

		while (!termino_nivel) {
			while (!isPausado()) {

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

				// En cada GameLoop tengo que redibujar el mapa, para mostrar
				// los cambios en el mismo
				mapa.paint(mapa.getGraphics());

				// Aqui consigo un iterador de enemigos en el mapa, apra
				// recorrerlos e ir dibujandolos
				Iterator it_en = escenario.getIteradordeEnemigos();
				while (it_en.hasNext()) {
					// Saco un enemigo de la lista
					Enemigo aux = (Enemigo) it_en.next();
					// Pido el tipo de "Dibujador" necesario para el enemigo
					// conseguido de la lista y le seteo la posicion donde debe
					// dibujar.
					vistaObjeto = (VistaObjetoDeMapa) TablaVistas.get(aux
							.getNombre());
					vistaObjeto.setX(aux.getX());
					vistaObjeto.setY(aux.getY());

					// Dibujo el enemigo en el mapa.
					vistaObjeto.dibujar(vistaP.getMapa());

					// Si el tiempo pasado es multiplo de la velocidad del
					// enemigo, este debe avanzar. EL numero 200 fue colocado
					// como una constante de acondicionamiento del tiempo
					if (((SleepTime * tiempo_pasado) % (200 / aux
							.getVelocidad())) == 0)
						aux.avanzar(escenario);

				}

				// De la misma forma que con los enemigos, pido un iterador para
				// los obstaculos. Con este, recorro la lsita de osbtaculos de
				// la lista, y uno por uno, pido el "dibujador" adecuado de la
				// Tabla de Vistas y dibujo el osbtaculo
				Iterator it_obs = escenario.getIteradordeObstaculos();
				while (it_obs.hasNext()) {
					Obstaculo aux = (Obstaculo) it_obs.next();
					vistaObjeto = (VistaObjetoDeMapa) TablaVistas.get(aux
							.getNombre());
					vistaObjeto.setX(aux.getX());
					vistaObjeto.setY(aux.getY());

					vistaObjeto.dibujar(vistaP.getMapa());

					if (((SleepTime * tiempo_pasado) % (200 / aux
							.getVelocidadDisparo())) == 0)
						try {
							aux.atacar();
						} catch (EnemigoYaMuerto e) {
						} catch (Exception e) {
						}

				}

				// Aqui busco el objeto seleccionado en el mapa, y con sus
				// datos, escribo los datos en el menu de seleccion. Se hace de
				// esta forma, para poder percibir en "tiempo real" los cambios
				// en el objeto seleccionado, como cuando lastimo a un enemigo
				PanelDatosDeSeleccion panel = vistaP.getPanelDatos()
						.getPanelDatosSeleccion();
				if (vistaP.getMapa().getObjeto_seleccionado() != null) {

					Seleccionable seleccionado = vistaP.getMapa()
							.getObjeto_seleccionado();
					panel.setLabelRango_Vel_Text(String.valueOf(seleccionado
							.getRango_Velocidad()));
					panel.setLabelVida_danio_Text(String.valueOf(seleccionado
							.getVida_Daño()));
					panel.setBottonUpdate_Text(String.valueOf(seleccionado
							.getValorEvolucion()));
					panel.setLabelSeleccion_Text(seleccionado.getNombre());
				}
				try {
					Thread.sleep(SleepTime);
				} catch (InterruptedException e) {
				}

				// Aumento el tiempo pasado, y le pongo un tope para no producir
				// OverFlow
				tiempo_pasado++;
				if (tiempo_pasado > 60000)
					tiempo_pasado = 0;

			}
		}
	}

	/**
	 * Seteo si el simulador esta pausado o no
	 * 
	 * @param pausado
	 */
	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	/**
	 * 
	 * @return boolean - Devuelve si el simulador esta pausado o no
	 */
	public boolean isPausado() {
		return pausado;
	}

	/**
	 * 
	 * @return HashMap - Devuelve el Mapa de Equivalencias de Vistas
	 */
	public HashMap getTablaVistas() {
		return TablaVistas;
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

}
