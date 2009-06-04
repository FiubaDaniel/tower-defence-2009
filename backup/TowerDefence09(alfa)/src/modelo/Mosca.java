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

	}

	public void avanzar(Escenario terreno) {
		Posicion siguiente = terreno.obtenerSiguientePosicionCaminable(this
				.getPosicion(), cant_avanzada);
		this.cambiarPosicion(siguiente);
		cant_avanzada++;
		if(cant_avanzada > terreno.getCaminoAlaSalida().size())
			cant_avanzada = 0;
	}

	public String getNombre() {
		return "Mosca";
	}
}
