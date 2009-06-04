package modelo;

import customExceptions.NoEsEntradaException;

public class Cucaracha extends Enemigo {

	public Cucaracha(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		this.setVelocidad(5);
		this.setVida(4);
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
		return "Cucaracha";
	}

}
