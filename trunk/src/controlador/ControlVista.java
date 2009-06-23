package controlador;

import java.awt.Dialog;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Obstaculo;
import modelo.Seleccionable;
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
import vista.menu.GameOver;
import vista.menu.Mapa;
import vista.menu.PanelDatosDeSeleccion;
import vista.menu.VistaPrincipal;

/**
 * Esta clase se encarga de manejar los refresh de la vista.
 * 
 * @author Grupo 4
 *
 */
public class ControlVista {

	private static ControlVista instancia = null;
	
	private HashMap TablaVistas;
	
	private Escenario escenario = Escenario.obtenerEscenario();
	
	private VistaPrincipal VentanaPrincipal;
	
	public static void setTerminado() {
		 instancia = null;
	}
	
	public static ControlVista obtenerControl(){
		if (instancia == null)
			instancia = new ControlVista();
		else
			return instancia;
		return instancia;
	}
	
	private ControlVista(){
		//Cargo el Frame Principal
		VentanaPrincipal = VistaPrincipal.obtenerVistaPrincipal();
		VentanaPrincipal.setVisible(true);
		this.CargarTabla();
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

	public void actualizarVista(){
		this.dibujarMapa();
		this.dibujarEnemigos();
		this.dibujarObstaculos();
		this.actualizarPaneles();
	}
	
	/**
	 * Dibuja los enemigos despues de cada movimiento, se invoca al metodo desde
	 * actualizaVista.
	 * 
	 */
	private void dibujarEnemigos(){
		
		/* Instancio un obsjeto de vista, para usar como auxiliar y manejar las
		 * vistas necesarias
		 */
		VistaObjetoDeMapa vistaObjeto;
		
		/* Aqui consigo un iterador de enemigos en el mapa, apra
		 * recorrerlos e ir dibujandolos
		 */
		Iterator it_en = Escenario.obtenerEscenario().getIteradordeEnemigos();
		while (it_en.hasNext()) {
			// Saco un enemigo de la lista
			Enemigo aux = (Enemigo) it_en.next();
			/* Pido el tipo de "Dibujador" necesario para el enemigo
			 * conseguido de la lista y le seteo la posicion donde debe
			 * dibujar.
			 */
			vistaObjeto = (VistaObjetoDeMapa) TablaVistas.get(aux.toString());
			vistaObjeto.setX(aux.getPosicion().getCoordX());
			vistaObjeto.setY(aux.getPosicion().getCoordY());

			// Dibujo el enemigo en el mapa.
			vistaObjeto.dibujar((VentanaPrincipal.getMapa()));
		}
	}
	
	/**
	 * Dibuja los obstaculos, se llama desde actualizarVista.
	 */
	private void dibujarObstaculos(){
		
		VistaObjetoDeMapa vistaObjeto;
		
		/* De la misma forma que con los enemigos, pido un iterador para
		 * los obstaculos. Con este, recorro la lsita de osbtaculos de
		 * la lista, y uno por uno, pido el "dibujador" adecuado de la
		 * Tabla de Vistas y dibujo el osbtaculo
		 */
		Iterator it_obs = escenario.getIteradordeObstaculos();
		while (it_obs.hasNext()) {
			Obstaculo aux = (Obstaculo) it_obs.next();
			vistaObjeto = (VistaObjetoDeMapa) TablaVistas.get(aux.toString());
			vistaObjeto.setX(aux.getPosicion().getCoordX());
			vistaObjeto.setY(aux.getPosicion().getCoordY());

			vistaObjeto.dibujar(VentanaPrincipal.getMapa());
		}
	}
	
	private void actualizarPaneles(){
		/* Aqui busco el objeto seleccionado en el mapa, y con sus
		 * datos, escribo los datos en el menu de seleccion. Se hace de
		 * esta forma, para poder percibir en "tiempo real" los cambios
		 * en el objeto seleccionado, como cuando lastimo a un enemigo
		 */
		PanelDatosDeSeleccion panel = VentanaPrincipal.getPanelDatos()
				.getPanelDatosSeleccion();
		
		if (VentanaPrincipal.getMapa().getObjeto_seleccionado() != null) {

			Seleccionable seleccionado = VentanaPrincipal.getMapa()
					.getObjeto_seleccionado();
			panel.setLabelRango_Vel_Text(String.valueOf(seleccionado
					.getRango_Velocidad()));
			panel.setLabelVida_danio_Text(String.valueOf(seleccionado
					.getVida_Danio()));
			panel.setBottonUpdate_Text(String.valueOf(seleccionado
					.getValorEvolucion()));
			panel.setLabelSeleccion_Text(seleccionado.toString());
		}
		
		
		VentanaPrincipal.getPanelDatos().getLabel_Cant_Enemigos().setText(String.valueOf(escenario.getCantBichos()));
	}
	
	/**
	 * Se llama desde actualizarVista para actualizar el mapa,
	 * esto es para visualizar los cambios. 
	 */
	private void dibujarMapa(){
		Mapa mapa = VentanaPrincipal.getMapa();
		
		/* En cada GameLoop tengo que redibujar el mapa, para mostrar
		 * los cambios en el mismo
		 */
		mapa.paint(mapa.getGraphics());
	}
	
	public void finDeJuego(){
		ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
		if (simulacion.isTerminoJuego())
			new GameOver(VentanaPrincipal);
	}

}