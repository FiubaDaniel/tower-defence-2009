package modelo;

import customExceptions.NoEsEntradaException;

public class Arania extends Enemigo {

	public Arania(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		this.setVelocidad(3);
		this.setVida(2);
	}
	/**
	 * Avanza a la siguiente posicion que puede ocupar del camino definido.
	 *
	 */
	public void avanzar(Escenario terreno) {
		if (getFrenado() == false) {
			Posicion siguiente = terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
			this.cambiarPosicion(siguiente);
		}
	}

	public String toString() {
		return "Arania";
	}

}
