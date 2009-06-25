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

	public String toString() {
		return "Cucaracha";
	}

}
