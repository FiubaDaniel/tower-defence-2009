package modelo;

import customExceptions.DineroMuyBajoException;
import customExceptions.NoteHagaselVivoException;
import modelo.Jugador;
import modelo.Posicion;

public class TorreDorada extends Torre {

	/* instancio un jugador */
	private Jugador jugador = Jugador.obtenerJugador();

	/* constructor */
	public TorreDorada(Posicion posicionEnEscenario) {
		if (this.getPrecio() >= jugador.getDinero())
			throw new DineroMuyBajoException();
		else
			if (!posicionEnEscenario.isCaminable())
				this.setPosicion(posicionEnEscenario);
			else
				throw new NoteHagaselVivoException();
		this.setAlcance(7);
		this.setPrecio(50);
		this.setDanioQueGenera(4);
		this.setCostoEvolucion(25);
		this.setEvolucion(6);
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
		return "Torre Dorada";
	}
}
