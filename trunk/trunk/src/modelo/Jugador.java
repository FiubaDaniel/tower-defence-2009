package modelo;

import java.util.Observable;

import org.jdom.Element;

import customExceptions.DineroMuyBajoException;


/**
 * Esta clase controla todos los aspectos generales del manejo del Jugador, desde
 * su creacion a su correcta configuracion.
 * 
 * @author hector
 * 
 */
public class Jugador extends Observable implements Persistente{

	private static Jugador jugador;
	private int CantidadVidas;
	private double Dinero;
	private String Nombre;
	
	public static Jugador obtenerJugador(){
		if (jugador == null)
			jugador = new Jugador();
		return jugador;
	}

	private Jugador() {
		this.setCantidadVidas(100);
		this.setDinero(80);
		this.setNombre("Grupo 4");
	}
	
	private Jugador(String nombre, double dinero, int vidas) {
		this.setCantidadVidas(vidas);
		this.setDinero(dinero);
		this.setNombre(nombre);
	}

	private void setCantidadVidas(int cantidadVidas) {
			this.CantidadVidas = cantidadVidas;
			this.notifyObservers();
	}

	public int getCantidadVidas() {
		return CantidadVidas;
	}
	private void setDinero(double dinero){
		if (dinero >= 0) {
			this.Dinero = dinero;
			this.notifyObservers();
		}
		else
			throw new IllegalArgumentException();
	}

	public double getDinero() {
		return Dinero;
	}

	public void setNombre(String nombre) {
		if (!nombre.isEmpty()) {
			this.Nombre = nombre;
			this.notifyObservers();
		}
		else
			throw new IllegalArgumentException();
	}

	public String getNombre() {
		return Nombre;
	}


	/**
	 * Resta de a una unidad de vida.
	 */
	public void quitarVida() {
		this.setCantidadVidas(CantidadVidas - 1);
		this.setChanged();
	}

	public void ModificarDinero(double dinero) throws DineroMuyBajoException {
		// recibe un valor positivo si agrego dinero y negativo si quito dinero
		try {
			this.setDinero(Dinero + dinero);
		} catch (IllegalArgumentException e) {
			throw new DineroMuyBajoException();
		}
		this.setChanged();
	}

	public Jugador ObtenerJugador(Element xmlElement) {
		if (jugador == null) {
			jugador = new Jugador(xmlElement.getAttributeValue("Nombre"), Double.parseDouble(xmlElement.getAttributeValue("Dinero")), Integer.parseInt(xmlElement.getAttributeValue("Vidas")));
		}
		return jugador;
	}
	
	public Element persistir() {
		Element xmlElement = new Element("Jugador");
        xmlElement.setAttribute("Nombre", this.Nombre);
        xmlElement.setAttribute("Vidas", String.valueOf(this.CantidadVidas));
        xmlElement.setAttribute("Dinero", String.valueOf(this.Dinero));
        return xmlElement;
	}
}
