package modelo;

import customExceptions.NoEsEntradaException;

public class Mosca extends Enemigo {

	public Mosca(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		this.setVolador();
		this.setVelocidad(4);
		this.setVida(3);
	}

	/**
	 * Avanza a la siguiente posicion que puede ocupar del camino definido.
	 *
	 */
	public void avanzar(Escenario terreno) {
		Posicion siguiente;
		siguiente = terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);
	}

	public String toString() {
		return "Mosca";
	}
}
