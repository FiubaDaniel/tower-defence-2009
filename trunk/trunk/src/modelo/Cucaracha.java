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

	@Override
	public Enemigo clone() {
		Cucaracha clon = new Cucaracha(this.getPosicion());
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
