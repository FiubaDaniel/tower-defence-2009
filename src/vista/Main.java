package vista;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Observer;

import controlador.EnemigoListener;
import customExceptions.EnemigoYaMuerto;
import modelo.Enemigo;
import modelo.Escenario;
import modelo.Hormiga;
import modelo.Jugador;
import modelo.Nivel;
import modelo.Posicion;
import modelo.Torre;
import modelo.TorreBlanca;
import vista.dibujadores.VistaHormiga;
import vista.dibujadores.VistaTorreBlanca;
import vista.menu.Mapa;
import vista.menu.MenuSuperior;
import vista.menu.PanelDatosDeSeleccion;
import vista.menu.PanelDatosDeTorres;
import vista.menu.PanelDeDatos;
import vista.menu.VistaPrincipal;

public class Main {

	/**
	 * 
	 * Dónde llamare a todo.
	 * 
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String args[]) {

		// Instancio el modelo.
		Escenario escenario = Escenario.obtenerEscenario();
		try {
			escenario.setNumeroNivel(1);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Jugador jugador = Jugador.obtenerJugador(50, 1000, "Exus");
		Nivel NivelGeneral = Nivel.obtenerNivel(escenario, jugador);

		// Para pruebas - Eliminar esto después de haber probado todo
		Posicion aux_pos = NivelGeneral.getEscenario1().getEntrada();
		Hormiga hormiga = new Hormiga(aux_pos);
		// Hormiga hormiga2 = new Hormiga(aux_pos);

		escenario.agregarEnemigoALista(hormiga);
		// escenario.agregarEnemigoALista(hormiga2);

		Posicion aux_pos2 = new Posicion(8, 40, true);
		Torre Blanca = new TorreBlanca(aux_pos2);

		escenario.insertarObstaculoEnMapa(Blanca);
		Observer EnemigoObserver = new EnemigoListener();

		hormiga.addObserver(EnemigoObserver);
		// Para pruebas- Eliminar esto después de haber probado todo

		// Instancio los objetos de la ventana
		PanelDatosDeTorres PanelTorres = new PanelDatosDeTorres();
		PanelDatosDeSeleccion PanelSeleccion = new PanelDatosDeSeleccion();
		MenuSuperior MenuArchivoAyuda = new MenuSuperior();
		PanelDeDatos PanelDatos = new PanelDeDatos(PanelSeleccion, PanelTorres);
		Mapa PanelMapa = new Mapa();

		VistaPrincipal VentanaPrincipal = VistaPrincipal.obtenerVistaPrincipal(
				MenuArchivoAyuda, PanelDatos, PanelMapa);
		VentanaPrincipal.setVisible(true);

		jugador.addObserver(PanelDatos);
		
		VistaHormiga Imagen_Hormiga = new VistaHormiga(hormiga.getX(), hormiga
				.getY());
		VistaTorreBlanca Imagen_Blanca = new VistaTorreBlanca(Blanca.getX(),
				Blanca.getY());

		while (true) {

			PanelMapa.paint(PanelMapa.getGraphics());

			Iterator it_en = escenario.getIteradordeEnemigos();
			while (it_en.hasNext()) {
				Enemigo aux = (Enemigo) it_en.next();
				Imagen_Hormiga.setX(aux.getX());
				Imagen_Hormiga.setY(aux.getY());

				Imagen_Hormiga.dibujar(PanelMapa);

				aux.avanzar(NivelGeneral.getEscenario1());
				// TODO Ver como hacer para q no se peguen dos enemigos encima

			}

			boolean dibujado_todas_las_torres = false;
			Iterator it_obs = escenario.getIteradordeObstaculos();
			while (it_obs.hasNext() && !dibujado_todas_las_torres) {
				Torre aux = (Torre) it_obs.next();
				Imagen_Blanca.setX(aux.getX());
				Imagen_Blanca.setY(aux.getY());

				Imagen_Blanca.dibujar(PanelMapa);

				try {
					aux.atacar();
				} catch (EnemigoYaMuerto e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Ver como hacer para q no se peguen dos enemigos encima
				// NO sirve un timer :P

				dibujado_todas_las_torres = true;

			}
			if (PanelMapa.getObjeto_seleccionado() != null) {
				PanelSeleccion.setLabelRango_Vel_Text(String.valueOf(PanelMapa
						.getObjeto_seleccionado().getRango_Velocidad()));
				PanelSeleccion.setLabelVida_danio_Text(String.valueOf(PanelMapa
						.getObjeto_seleccionado().getVida_Daño()));
				PanelSeleccion.setBottonUpdate_Text(String.valueOf(PanelMapa
						.getObjeto_seleccionado().getValorEvolucion()));
				PanelSeleccion.setLabelSeleccion_Text(PanelMapa
						.getObjeto_seleccionado().getNombre());
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Para pruebas- Eliminar esto después de haber probado todo
		}

	}
}
