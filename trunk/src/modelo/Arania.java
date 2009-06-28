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

	@Override
	public Enemigo clone() {
		Arania clon = new Arania(this.getPosicion());
		try {
			this.setVelocidad(this.getVelocidad());
			this.setVida(this.getVida());
		}
		catch (ValorNegativoException error) {
		}
		this.setPremioDinero(this.getPremioDinero());
		return clon;
	}
	
	
}
