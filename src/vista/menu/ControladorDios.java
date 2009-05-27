package vista.menu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Observer;

import vista.dibujadores.VistaArania;
import vista.dibujadores.VistaCucaracha;
import vista.dibujadores.VistaHormiga;
import vista.dibujadores.VistaMosca;
import vista.dibujadores.VistaObjetoDeMapa;
import vista.dibujadores.VistaTorreAzul;
import vista.dibujadores.VistaTorreBlanca;
import vista.dibujadores.VistaTorreDorada;
import vista.dibujadores.VistaTorrePlateada;

import controlador.EnemigoListener;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Hormiga;
import modelo.Mosca;
import modelo.Posicion;
import modelo.Torre;
import modelo.TorreBlanca;
import customExceptions.EnemigoYaMuerto;

/**
 * Esta clase se encarga del Manejo del GameLoop
 * 
 * @author exus
 * 
 */

public class ControladorDios {

	private boolean pausado = true;
	private int SleepTime = 100;

	private HashMap TablaVistas;
	
	public ControladorDios() {
		CargarTabla();
	}
	
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
	}
	
	public int getSleepTime() {
		return SleepTime;
	}

	public void setSleepTime(int sleepTime) {
		SleepTime = sleepTime;
	}

	public void iniciar_juego() {

		MenuSuperior menuArchivoAyuda = null;
		PanelDeDatos paneldedatos = null;
		Mapa mapa = null;

		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(
				menuArchivoAyuda, paneldedatos, mapa);

		mapa = vistaP.getMapa();

		Escenario escenario = Escenario.obtenerEscenario();

		Posicion aux_pos = escenario.getEntrada();
		Hormiga hormiga = new Hormiga(aux_pos);
		Mosca mosca = new Mosca(aux_pos);

		escenario.agregarEnemigoALista(hormiga);
		escenario.agregarEnemigoALista(mosca);

		Posicion aux_pos2 = new Posicion(8, 40, true);
		Torre Blanca = new TorreBlanca(aux_pos2);

		escenario.insertarObstaculoEnMapa(Blanca);
		Observer EnemigoObserver = new EnemigoListener();

		hormiga.addObserver(EnemigoObserver);

		VistaObjetoDeMapa vistaObjeto;

		double tiempo_pasado = 0;

		while (!isPausado()) {

			mapa.paint(mapa.getGraphics());

			Iterator it_en = escenario.getIteradordeEnemigos();
			while (it_en.hasNext()) {
				Enemigo aux = (Enemigo) it_en.next();
				vistaObjeto = (VistaObjetoDeMapa)TablaVistas.get(aux.getNombre());
				vistaObjeto.setX(aux.getX());
				vistaObjeto.setY(aux.getY());

				vistaObjeto.dibujar(vistaP.getMapa());

				if (((SleepTime * tiempo_pasado) % (200 / aux.getVelocidad())) == 0)
					aux.avanzar(escenario);

			}

			boolean dibujado_todas_las_torres = false;
			Iterator it_obs = escenario.getIteradordeObstaculos();
			while (it_obs.hasNext() && !dibujado_todas_las_torres) {
				Torre aux = (Torre) it_obs.next();
				vistaObjeto = (VistaObjetoDeMapa)TablaVistas.get(aux.getNombre());
				vistaObjeto.setX(aux.getX());
				vistaObjeto.setY(aux.getY());

				vistaObjeto.dibujar(vistaP.getMapa());

				if (((SleepTime * tiempo_pasado) % (200 / aux.getVelocidadDisparo())) == 0)
					try {
						aux.atacar();
					} catch (EnemigoYaMuerto e) {
					}
				dibujado_todas_las_torres = true;
			}

			PanelDatosDeSeleccion panel = vistaP.getPanelDatos()
					.getPanelDatosSeleccion();
			if (vistaP.getMapa().getObjeto_seleccionado() != null) {
				panel.setLabelRango_Vel_Text(String.valueOf(vistaP.getMapa()
						.getObjeto_seleccionado().getRango_Velocidad()));
				panel.setLabelVida_danio_Text(String.valueOf(vistaP.getMapa()
						.getObjeto_seleccionado().getVida_Daño()));
				panel.setBottonUpdate_Text(String.valueOf(vistaP.getMapa()
						.getObjeto_seleccionado().getValorEvolucion()));
				panel.setLabelSeleccion_Text(vistaP.getMapa()
						.getObjeto_seleccionado().getNombre());
			}
			try {
				Thread.sleep(SleepTime);
			} catch (InterruptedException e) {
			}

			tiempo_pasado++;

		}
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	public boolean isPausado() {
		return pausado;
	}

	public void setTablaVistas(HashMap tablaVistas) {
		TablaVistas = tablaVistas;
	}

	public HashMap getTablaVistas() {
		return TablaVistas;
	}

}
