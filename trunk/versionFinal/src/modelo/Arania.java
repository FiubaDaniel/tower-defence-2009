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

	public String toString() {
		return "Arania";
	}
}
