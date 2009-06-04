package modelo;

import customExceptions.NoEsEntradaException;

public class Hormiga extends Enemigo {

	public Hormiga(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		this.setVelocidad(1);
		this.setVida(1);
	}
	/**
	 * Avanza a la siguiente posicion que puede ocupar del camino definido.
	 *
	 */
	public void avanzar(Escenario terreno) {
		if (getFrenado() == false) {
			Posicion siguiente;
			siguiente = terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
			this.cambiarPosicion(siguiente);
		}
	}	

	public String toString() {
		return "Hormiga";
	}
}
