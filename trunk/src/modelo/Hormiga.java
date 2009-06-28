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

	@Override
	public Enemigo clone() {
		Hormiga clon = new Hormiga(this.getPosicion());
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
