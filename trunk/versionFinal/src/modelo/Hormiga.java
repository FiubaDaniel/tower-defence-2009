package modelo;

import customExceptions.*;

public class Hormiga extends Enemigo {

	public Hormiga(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		try {
			this.setVelocidad(1);
			this.setVida(1);
		} catch (ValorNegativoException error) {
		}
		this.setPremioDinero(5);

	}

	public String toString() {
		return "Hormiga";
	}
}
