package logica;

import java.util.Iterator;
import logica.Enemigo;
import logica.Escenario;
import customExceptions.ErrorEnemigoException;

public abstract class Torre extends Obstaculo {

	private int costoEvolucion, evolucion;
	private Enemigo enemigoAatacar;

	/* instancio el escenario */
	private Escenario escenario = Escenario.obtenerEscenario();

	public int getEvolucion() {
		return evolucion;
	}

	public int getCostoEvolucion() {
		return costoEvolucion;
	}

	protected void setCostoEvolucion(int costoEvolucionTorre) {
		costoEvolucion = costoEvolucionTorre;
	}

	protected void setEvolucion(int cantidadQueEvoluvciona) {
		evolucion = cantidadQueEvoluvciona;
	}

	// Realizar Ataque

	public void atacar() throws Exception {
		Iterator itEnemigos = escenario.getIteradordeEnemigos();
	
		while (itEnemigos.hasNext()) {

			try {
				enemigoAatacar = (Enemigo) itEnemigos.next();
			} catch (ClassCastException e) {
				throw new ErrorEnemigoException();
			}
			if (this.getPosicion().getDistancia(enemigoAatacar.getPosicion()) < this
					.getAlcance()) {
				enemigoAatacar.disminuirVida(this.getDanioQueGenera());
			}
		}
		escenario.eliminarEnemigosSinVidadelaLista();
	}

	abstract public void evolucionarce();

}