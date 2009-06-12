package modelo;

import java.util.Iterator;

import customExceptions.DineroMuyBajoException;
import customExceptions.EnemigoYaMuerto;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Posicion;

public class TorreAzul extends Torre {

	private Posicion salida;
	private Enemigo enemigoAatacar;

	/* instancio un jugador y un escenario */
	private Jugador jugador = Jugador.obtenerJugador();
	private Escenario escenario = Escenario.obtenerEscenario();

	/* constructor */
	public TorreAzul(Posicion posicionEnEscenario) { /* ver lo dell costructor */

		if (this.getPrecio() >= jugador.getDinero()) {
			throw new DineroMuyBajoException();
		} else {
			this.setPosicion(posicionEnEscenario);
		}
		this.setPrecio(6);
		this.setDanioQueGenera(1);
		this.setCostoEvolucion(6);
		this.setEvolucion(2);
	}

	/*
	 * la torre azul ataca a una distancia de entre 3 y 8 si el enemigo esta mas
	 * cerca de la salida q la torre y entre 5 y 8 si esta por delante
	 */
	public void atacar() throws EnemigoYaMuerto {
		Iterator itEnemigos = escenario.getIteradordeEnemigos();

		salida = escenario.getSalida();

		while (itEnemigos.hasNext()) {
				enemigoAatacar = (Enemigo) itEnemigos.next();
			if (this.getPosicion().getDistancia(salida) > salida
					.getDistancia(enemigoAatacar.getPosicion())) {
				if ((3 < this.getPosicion().getDistancia(
						enemigoAatacar.getPosicion()))
						&& (this.getPosicion().getDistancia(
								enemigoAatacar.getPosicion()) < 8)) {
					enemigoAatacar.disminuirVida(this.getDanioQueGenera());
				} else {
					if ((this.getPosicion().getDistancia(
							enemigoAatacar.getPosicion()) < 8)
							&& (this.getPosicion().getDistancia(
									enemigoAatacar.getPosicion()) > 5)) {

						enemigoAatacar.disminuirVida(this.getDanioQueGenera());
					}
				}
			}
			if (enemigoAatacar.getVida() == 0)
				escenario.eliminarEnemigosSinVidadelaLista();
		}
	}

	public void evolucionarce() {
		/* nombre aca tambien */
		if (jugador.getDinero() > getCostoEvolucion()) {
			this.setDanioQueGenera(this.getDanioQueGenera()
					* this.getEvolucion());
			this.setCostoEvolucion(this.getCostoEvolucion()
					+ this.getCostoEvolucion());
		} else {
			throw new DineroMuyBajoException();
		}
	}

	public String toString() {
		return "Torre Azul";
	}
}