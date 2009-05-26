package modelo;

import customExceptions.DineroMuyBajoException;
import modelo.Jugador;
import modelo.Posicion;

public class TorrePlateada extends Torre {

	/* instancio un jugador */
	private Jugador jugador = Jugador.obtenerJugador(1, 1.5, "Daniel");

	/* constructor */
	public TorrePlateada(Posicion posicionEnEscenario) {
		if (this.getPrecio() >= jugador.getDinero()) {
			throw new DineroMuyBajoException();
		} else {
			this.setPosicion(posicionEnEscenario);
		}
		this.setAlcance(5);
		this.setPrecio(20);
		this.setDanioQueGenera(2);
		this.setCostoEvolucion(15);
		this.setEvolucion(3);

	}

	public void evolucionarce() {
		/* nombre aca tambien */
		if (jugador.getDinero() > this.getCostoEvolucion()) {
			this.setAlcance(this.getAlcance() + this.getEvolucion());
			this.setCostoEvolucion(this.getCostoEvolucion()
					+ this.getEvolucion());
			this.setDanioQueGenera(this.getDanioQueGenera()
					+ this.getDanioQueGenera());
		} else {
			throw new DineroMuyBajoException();
		}
	}

	public String getNombre() {
		return "Torre Plateada";
	}

}
