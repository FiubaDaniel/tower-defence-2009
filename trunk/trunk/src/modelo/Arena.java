package modelo;

import java.util.Iterator;

public class Arena extends Obstaculo {

	public Arena(Posicion posActual) {
		this.setPrecio(10);
		this.setPosicion(posActual);
		this.setAlcance(0);
		this.setDanioQueGenera(0);
		this.setVelocidadDisparo(500);

	}

	public void atacar() {

		Iterator it = Escenario.obtenerEscenario().getIteradordeEnemigos();
		while (it.hasNext()) {
			Enemigo enemigoAux = (Enemigo) it.next();
			if (!enemigoAux.getVolador()
					&& this.getPosicion().getDistancia(enemigoAux.getPosicion()) == this
							.getAlcance()) {
				enemigoAux.frenarOAvanzar();
			}
		}
	}

	public String toString() {
		return "Arena";
	}

	public int getValorEvolucion() {
		return 0;
	}
}
