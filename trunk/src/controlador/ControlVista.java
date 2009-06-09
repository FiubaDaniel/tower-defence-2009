package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Obstaculo;
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
import vista.menu.PanelDatosDeTorres;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;

public class ControlVista implements ActionListener{

	private static ControlVista instancia = null;
	
	private HashMap TablaVistas;
	
	// Este atributo establece si la simulacion se detiene o si ocntinua
	private boolean pausado = true;
	
	private Escenario escenario = Escenario.obtenerEscenario();
	
	private VistaPrincipal VentanaPrincipal;
	
	public static ControlVista obtenerControl(){
		if (instancia == null)
			instancia = new ControlVista();
		else
			return instancia;
		return instancia;
	}
	
	private ControlVista(){
		// Instancio los objetos de la ventana
		PanelDatosDeTorres PanelTorres = new PanelDatosDeTorres();
		PanelDatosDeSeleccion PanelSeleccion = new PanelDatosDeSeleccion();
		MenuSuperior MenuArchivoAyuda = new MenuSuperior();
		PanelDeDatos PanelDatos = new PanelDeDatos(PanelSeleccion, PanelTorres);
		Mapa PanelMapa = new Mapa();
		
		
		//Cargo el Frame Principal
		VentanaPrincipal = VistaPrincipal.obtenerVistaPrincipal(
				MenuArchivoAyuda, PanelDatos, PanelMapa);
		VentanaPrincipal.setVisible(true);

		//Agrego un Observador de jugador
		Jugador jugador = Jugador.obtenerJugador(50, 1000, "Grupo 4");
		jugador.addObserver(PanelDatos);
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
	
	private void dibujarEnemigos(){
		
		// Instancio un obsjeto de vista, para usar como auxiliar y manejar las
		// vistas necesarias
		VistaObjetoDeMapa vistaObjeto;
		
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
			vistaObjeto.dibujar(VentanaPrincipal.getMapa());
		}
	}
	
	private void dibujarObstaculos(){
		
		VistaObjetoDeMapa vistaObjeto;
		
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

			vistaObjeto.dibujar(VentanaPrincipal.getMapa());
		}
	}
	
	private void actualizarPaneles(){
		// Aqui busco el objeto seleccionado en el mapa, y con sus
		// datos, escribo los datos en el menu de seleccion. Se hace de
		// esta forma, para poder percibir en "tiempo real" los cambios
		// en el objeto seleccionado, como cuando lastimo a un enemigo
		PanelDatosDeSeleccion panel = VentanaPrincipal.getPanelDatos()
				.getPanelDatosSeleccion();
		if (VentanaPrincipal.getMapa().getObjeto_seleccionado() != null) {

			Seleccionable seleccionado = VentanaPrincipal.getMapa()
					.getObjeto_seleccionado();
			panel.setLabelRango_Vel_Text(String.valueOf(seleccionado
					.getRango_Velocidad()));
			panel.setLabelVida_danio_Text(String.valueOf(seleccionado
					.getVida_Daño()));
			panel.setBottonUpdate_Text(String.valueOf(seleccionado
					.getValorEvolucion()));
			panel.setLabelSeleccion_Text(seleccionado.getNombre());
		}
	}
	
	private void dibujarMapa(){
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