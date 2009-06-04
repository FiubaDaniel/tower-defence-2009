package modelo;

import customExceptions.DineroMuyBajoException;
import customExceptions.NoteHagaselVivoException;
import modelo.Jugador;
import modelo.Posicion;

public class TorreBlanca extends Torre {

	/* instancio un jugador */
	private Jugador jugador = Jugador.obtenerJugador();

	/* constructor */
	public TorreBlanca(Posicion posicionEnEscenario) {
		if (this.getPrecio() >= jugador.getDinero())
			throw new DineroMuyBajoException();
		else
			if (!posicionEnEscenario.isCaminable())
				this.setPosicion(posicionEnEscenario);
			else
				throw new NoteHagaselVivoException();
		this.setAlcance(3);
		this.setPrecio(10);
		this.setDanioQueGenera(1);
		this.setCostoEvolucion(3);
		this.setEvolucion(2);
	}

	public void evolucionarce() {
		/* nombre aca tambien */
		if (jugador.getDinero() > getCostoEvolucion()) {
			this.setAlcance(this.getAlcance() + this.getEvolucion());
			this.setCostoEvolucion(this.getCostoEvolucion()
					+ this.getEvolucion());
		} else {
			throw new DineroMuyBajoException();
		}
	}

	public String toString() {
		return "Torre Blanca";
	}
}
