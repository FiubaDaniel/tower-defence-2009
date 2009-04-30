package logica;

import javax.management.InvalidAttributeValueException;

import customExceptions.DineroMuyBajoException;
import customExceptions.VidaMenorCantidadException;

public class Jugador {

	private static Jugador jugador;
	private int CantidadVidas;
	private double Dinero;
	private String Nombre;
	private double Puntos;

	public static Jugador crearJugador(int vida, double dinero, String nombre)
			throws InvalidAttributeValueException {
		if (jugador == null)
			jugador = new Jugador(vida, dinero, nombre);
		return jugador;
	}

	private Jugador(int vida, double dinero, String nombre)
			throws InvalidAttributeValueException {

		if ((vida > 0) && (dinero > 0)) {
			this.setCantidadVidas(vida);
			this.setDinero(dinero);
			this.setNombre(nombre);
			this.setPuntos(0);
		} else
			throw new InvalidAttributeValueException();

	}

	private void setCantidadVidas(int cantidadVidas)
			throws InvalidAttributeValueException {
		if (cantidadVidas >= 0)
			this.CantidadVidas = cantidadVidas;
		else
			throw new InvalidAttributeValueException();
	}

	public int getCantidadVidas() {
		return CantidadVidas;
	}

	private void setDinero(double dinero) throws InvalidAttributeValueException {
		if (dinero >= 0)
			this.Dinero = dinero;
		else
			throw new InvalidAttributeValueException();
	}

	public double getDinero() {
		return Dinero;
	}

	public void setNombre(String nombre) throws InvalidAttributeValueException {
		if (!nombre.isEmpty())
			this.Nombre = nombre;
		else
			throw new InvalidAttributeValueException();
	}

	public String getNombre() {
		return Nombre;
	}

	private void setPuntos(double puntos) throws InvalidAttributeValueException {
		if (puntos >= 0)
			this.Puntos = puntos;
		else
			throw new InvalidAttributeValueException();
	}

	public double getPuntos() {
		return Puntos;
	}

	public void AgregarQuitarVida(int cantidad)
			throws VidaMenorCantidadException {
		// recibe un valor positivo o negativo según quiera agregar vida o
		// quitar vida

		try {
			this.setCantidadVidas(CantidadVidas + cantidad);
		} catch (InvalidAttributeValueException e) {
			throw new VidaMenorCantidadException();

		}

	}

	public void ModificarDinero(double dinero) throws DineroMuyBajoException {
		// recibe un valor positivo si agrego dinero y negativo si quito dinero
		try {
			this.setDinero(Dinero + dinero);
		} catch (InvalidAttributeValueException e) {
			throw new DineroMuyBajoException();
		}
	}

	public void agregarPuntos(double puntos)
			throws InvalidAttributeValueException {

		if (puntos >= 0) {
			this.setPuntos(Puntos + puntos);
		} else
			throw new InvalidAttributeValueException();

	}
}
