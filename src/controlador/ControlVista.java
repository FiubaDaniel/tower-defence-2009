package controlador;

import java.util.HashMap;
import java.util.Iterator;

import customExceptions.EnemigoYaMuerto;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Obstaculo;
import modelo.Torre;
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
import vista.menu.PanelDatosDeTorres;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;

public class ControlVista {
	
	private static ControlVista instancia = null;
	
	private HashMap<String, VistaObjetoDeMapa> TablaVistas;
	
	/* Instancio un objeto de vista, para usar como auxiliar y manejar las
	 * vistas necesarias.
	 */
	private VistaObjetoDeMapa vistaObjeto;
	
	private Escenario escenario;
	
	/* Este atributo establece la cantidad de milisegundos a esperar entre Loop
	 * del simulador
	 */
	private int SleepTime = 100;
	
	/* En esta variable, como el nombre lo indica guardo el tiempo pasado en
	 * el juego.
	 */
	private double tiempo_pasado = 0;
	
	private ControlVista(){
		this.generarVistaInicial();
		this.CargarTabla();
	}
	
	public static ControlVista obtenerControl(){
		if (instancia == null) 
			instancia = new ControlVista();
		return instancia;
	}
	
	private void generarVistaInicial(){
		// Instancio los objetos de la ventana
		PanelDatosDeTorres PanelTorres = new PanelDatosDeTorres();
		PanelDatosDeSeleccion PanelSeleccion = new PanelDatosDeSeleccion();
		MenuSuperior MenuArchivoAyuda = new MenuSuperior();
		PanelDeDatos PanelDatos = new PanelDeDatos(PanelSeleccion, PanelTorres);
		Mapa PanelMapa = new Mapa();
				
		//Cargo el Frame Principal
		VistaPrincipal VentanaPrincipal;
		VentanaPrincipal = VistaPrincipal.obtenerVistaPrincipal(MenuArchivoAyuda, PanelDatos, PanelMapa);
		VentanaPrincipal.setVisible(true);
		
		//Agrego un Observador de jugador
		Jugador jugador = Jugador.obtenerJugador();
		jugador.addObserver(PanelDatos);
	}
	
	/**
	 * Carga la tabla de equivalencias entre un tipo de enemigos y su Vista
	 * Correspondiente. Es decir al recibir el string especificado, la tabla
	 * devuelve la clase que dibuja ese tipo de Objeto
	 */
	private void CargarTabla() {
		TablaVistas = new HashMap<String, VistaObjetoDeMapa>();
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
	
	private void dibujarEnemigos(){
		
		MenuSuperior menuArchivoAyuda = null;
		PanelDeDatos paneldedatos = null;
		Mapa mapa = null;
		
		// Obtengo el Frame principal del juego, desde el cual puedo obtener
		// todos los componentes del mismo. Lo llamo con arguementos nulos,
		// porque solo busco la instancia ya creada, no crear una nueva
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(
				menuArchivoAyuda, paneldedatos, mapa);
		
		// Aqui consigo un iterador de enemigos en el mapa, apra
		// recorrerlos e ir dibujandolos
		Iterator it_en = escenario.getIteradordeEnemigos();
		while (it_en.hasNext()) {
			// Saco un enemigo de la lista
			Enemigo aux = (Enemigo) it_en.next();
			// Pido el tipo de "Dibujador" necesario para el enemigo
			// conseguido de la lista y le seteo la posicion donde debe
			// dibujar.
			vistaObjeto = (VistaObjetoDeMapa) TablaVistas.get(aux);
			vistaObjeto.setX(aux.getPosicion().getCoordX());
			vistaObjeto.setY(aux.getPosicion().getCoordY());

			// Dibujo el enemigo en el mapa.
			vistaObjeto.dibujar(vistaP.getMapa());

			// Si el tiempo pasado es multiplo de la velocidad del
			// enemigo, este debe avanzar. EL numero 200 fue colocado
			// como una constante de acondicionamiento del tiempo
			if (((SleepTime * tiempo_pasado) % (200 / aux
					.getVelocidad())) == 0)
				aux.avanzar(escenario);

		}
	}
	
	private void dibujarObstaculo(){
		
		MenuSuperior menuArchivoAyuda = null;
		PanelDeDatos paneldedatos = null;
		Mapa mapa = null;
		
		// Obtengo el Frame principal del juego, desde el cual puedo obtener
		// todos los componentes del mismo. Lo llamo con arguementos nulos,
		// porque solo busco la instancia ya creada, no crear una nueva
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(
				menuArchivoAyuda, paneldedatos, mapa);
		
		// De la misma forma que con los enemigos, pido un iterador para
		// los obstaculos. Con este, recorro la lista de osbtaculos de
		// la lista, y uno por uno, pido el "dibujador" adecuado de la
		// Tabla de Vistas y dibujo el osbtaculo
		Iterator it_obs = escenario.getIteradordeObstaculos();
		while (it_obs.hasNext()) {
			Obstaculo aux = (Obstaculo) it_obs.next();
			vistaObjeto = (VistaObjetoDeMapa) TablaVistas.get(aux);
			vistaObjeto.setX(aux.getPosicion().getCoordX());
			vistaObjeto.setY(aux.getPosicion().getCoordY());

			vistaObjeto.dibujar(vistaP.getMapa());

			if (((SleepTime * tiempo_pasado) % (200 / aux
					.getVelocidadDisparo())) == 0)
				try {
					aux.atacar();
				} catch (EnemigoYaMuerto e) {
				} catch (Exception e) {
				}

		}
	}
	
	public void buscarObjetosSeleccionados(){
		
		MenuSuperior menuArchivoAyuda = null;
		PanelDeDatos paneldedatos = null;
		Mapa mapa = null;
		
		// Obtengo el Frame principal del juego, desde el cual puedo obtener
		// todos los componentes del mismo. Lo llamo con arguementos nulos,
		// porque solo busco la instancia ya creada, no crear una nueva
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(
				menuArchivoAyuda, paneldedatos, mapa);
		
		// Aqui busco el objeto seleccionado en el mapa, y con sus
		// datos, escribo los datos en el menu de seleccion. Se hace de
		// esta forma, para poder percibir en "tiempo real" los cambios
		// en el objeto seleccionado, como cuando lastimo a un enemigo
		PanelDatosDeSeleccion panel = vistaP.getPanelDatos()
				.getPanelDatosSeleccion();
		if (vistaP.getMapa().getObjeto_seleccionado() != null) {

			
			Object seleccionado = vistaP.getMapa().getObjeto_seleccionado();
			if (seleccionado.getClass() == Enemigo.class){
				Enemigo enemigo = (Enemigo)seleccionado;
				panel.setLabelRango_Vel_Text(String.valueOf(enemigo.getVelocidad()));
				panel.setLabelVida_danio_Text(String.valueOf(enemigo.getVida()));
				panel.setLabelSeleccion_Text(seleccionado.toString());
			}
			if (seleccionado.getClass() == Obstaculo.class){
				Obstaculo obstaculo = (Obstaculo)seleccionado;
				panel.setLabelRango_Vel_Text(String.valueOf(obstaculo.getAlcance()));
				panel.setLabelVida_danio_Text(String.valueOf(obstaculo.getDanioQueGenera()));
				panel.setLabelSeleccion_Text(seleccionado.toString());
			}
			if (seleccionado.getClass() == Torre.class){
				Torre torre = (Torre)seleccionado;
				panel.setLabelRango_Vel_Text(String.valueOf(torre.getAlcance()));
				panel.setLabelVida_danio_Text(String.valueOf(torre.getDanioQueGenera()));
				panel.setBottonUpdate_Text(String.valueOf(torre.getValorEvolucion()));
				panel.setLabelSeleccion_Text(seleccionado.toString());
			}
			
		}
		
	}
	
	private void pintarPantalla(){
		
		MenuSuperior menuArchivoAyuda = null;
		PanelDeDatos paneldedatos = null;
		Mapa mapa = null;
		
		// Obtengo el Frame principal del juego, desde el cual puedo obtener
		// todos los componentes del mismo. Lo llamo con arguementos nulos,
		// porque solo busco la instancia ya creada, no crear una nueva
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(
				menuArchivoAyuda, paneldedatos, mapa);
		
		mapa = vistaP.getMapa();
		// En cada GameLoop tengo que redibujar el mapa, para mostrar
		// los cambios en el mismo
		mapa.paint(mapa.getGraphics());
	}
	
	public void run(){
		
	}
}
