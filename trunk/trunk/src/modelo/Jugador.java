package modelo;

import java.util.Observable;

import customExceptions.DineroMuyBajoException;


/**
 * Esta clase controla todos los aspectos generales del manejo del Jugador, desde
 * su creacion a su correcta configuracion.
 * 
 * @author hector
 * 
 */
public class Jugador extends Observable{

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

	/*private void setPuntos(double puntos) {
		if (puntos >= 0) {
			this.Puntos = puntos;
			this.notifyObservers();
		}
		else
			throw new IllegalArgumentException();
	}

	public double getPuntos() {
		return Puntos;
	}*/

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

	/*
	public void agregarPuntos(double puntos) {

		if (puntos >= 0) {
			this.setPuntos(Puntos + puntos);
		} else
			throw new IllegalArgumentException();

	}*/
}
