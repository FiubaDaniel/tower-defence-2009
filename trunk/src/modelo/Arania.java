package modelo;

import customExceptions.NoEsEntradaException;
import customExceptions.ValorNegativoException;


public class Arania extends Enemigo {

	public Arania(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		try {
			this.setVelocidad(3);
			this.setVida(2);
		} catch (ValorNegativoException error) {
		}
		this.setPremioDinero(15);
	}

	public void avanzar(Escenario terreno) {
		if (getFrenado() == false) {
			Posicion siguiente = terreno.obtenerSiguientePosicionCaminable(this
					.getPosicion(), cant_avanzada);
			this.cambiarPosicion(siguiente);
			cant_avanzada++;
			if(cant_avanzada > terreno.getCaminoAlaSalida().size())
				cant_avanzada = 0;
		} else {
			frenarOAvanzar();
		}
	}

	public String toString() {
		return "Arania";
	}
}
