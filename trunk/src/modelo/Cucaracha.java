package modelo;

import customExceptions.NoEsEntradaException;
import customExceptions.ValorNegativoException;



public class Cucaracha extends Enemigo {

	public Cucaracha(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		try {
			this.setVelocidad(5);
			this.setVida(4);
		} catch (ValorNegativoException error) {
		}
		this.setPremioDinero(30);
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
		return "Cucaracha";
	}

}
