package logica;

import java.util.Iterator;

public class Arena extends Obstaculo {

	public Arena(Posicion posActual) {
		this.setPrecio(10);
		this.setPosicion(posActual);
		this.setAlcance(0);

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
}
