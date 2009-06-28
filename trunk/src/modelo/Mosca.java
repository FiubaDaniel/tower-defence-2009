package modelo;

import customExceptions.*;

public class Mosca extends Enemigo {

	public Mosca(Posicion unLugar) throws NoEsEntradaException {
		this.setPosicion(unLugar);
		this.setVolador();
		try {
			this.setVelocidad(4);
			this.setVida(3);
		}
		catch (ValorNegativoException error) {
		}
		this.setPremioDinero(20);
	}

	public String toString() {
		return "Mosca";
	}

	@Override
	public Enemigo clone() {
		Mosca clon = new Mosca(this.getPosicion());
		this.setVolador();
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
