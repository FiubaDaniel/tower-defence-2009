package modelo;

import java.util.Iterator;

import modelo.Enemigo;
import modelo.Escenario;
import customExceptions.EnemigoYaMuerto;

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

	public void atacar(){
		Iterator itEnemigos = escenario.getIteradordeEnemigos();
	
		while (itEnemigos.hasNext()) {
				enemigoAatacar = (Enemigo) itEnemigos.next();		
			if (this.getPosicion().getDistancia(enemigoAatacar.getPosicion()) < this
					.getAlcance()) {
				enemigoAatacar.disminuirVida(this.getDanioQueGenera());
			}
		}
		escenario.eliminarEnemigosSinVidadelaLista();
	}

	abstract public void evolucionarce();

	public int getValorEvolucion() {
		return costoEvolucion;
	}
	
}