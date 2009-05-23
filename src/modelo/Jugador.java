package modelo;

import customExceptions.DineroMuyBajoException;


/**
 * Esta clase controla todos los aspectos generales del manejo del Jugador, desde
 * su creacion a su correcta configuracion.
 * 
 * @author hector
 * 
 */
public class Jugador {

	private static Jugador jugador;
	private int CantidadVidas;
	private double Dinero;
	private String Nombre;
	private double Puntos;

	public static Jugador obtenerJugador(int vida, double dinero, String nombre){
		if (jugador == null)
			jugador = new Jugador(vida, dinero, nombre);
		return jugador;
	}

	private Jugador(int vida, double dinero, String nombre) {

		if ((vida > 0) && (dinero > 0)) {
			this.setCantidadVidas(vida);
			this.setDinero(dinero);
			this.setNombre(nombre);
			this.setPuntos(0);
		} else
			throw new IllegalArgumentException();
	}

	private void setCantidadVidas(int cantidadVidas) {
			this.CantidadVidas = cantidadVidas;
	}

	public int getCantidadVidas() {
		return CantidadVidas;
	}
	private void setDinero(double dinero){
		if (dinero >= 0)
			this.Dinero = dinero;
		else
			throw new IllegalArgumentException();
	}

	public double getDinero() {
		return Dinero;
	}

	public void setNombre(String nombre) {
		if (!nombre.isEmpty())
			this.Nombre = nombre;
		else
			throw new IllegalArgumentException();
	}

	public String getNombre() {
		return Nombre;
	}

	private void setPuntos(double puntos) {
		if (puntos >= 0)
			this.Puntos = puntos;
		else
			throw new IllegalArgumentException();
	}

	public double getPuntos() {
		return Puntos;
	}

	public void AgregarQuitarVida(int cantidad) {
		// recibe un valor positivo o negativo según quiera agregar vida o
		// quitar vida

			this.setCantidadVidas(CantidadVidas + cantidad);
	}

	public void ModificarDinero(double dinero) throws DineroMuyBajoException {
		// recibe un valor positivo si agrego dinero y negativo si quito dinero
		try {
			this.setDinero(Dinero + dinero);
		} catch (IllegalArgumentException e) {
			throw new DineroMuyBajoException();
		}
	}

	public void agregarPuntos(double puntos) {

		if (puntos >= 0) {
			this.setPuntos(Puntos + puntos);
		} else
			throw new IllegalArgumentException();

	}
}
