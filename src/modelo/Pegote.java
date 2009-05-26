package modelo;

import customExceptions.*;
import java.util.Iterator;

public class Pegote extends Obstaculo {

	public Pegote(Posicion posActual) {
		this.setPrecio(20);
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
				try {
					enemigoAux.disminuirVelocidad(50);
				} catch (ValorNegativoException e) {
					System.out
							.println("La capturo para que no se queje el compilador");
				}

			}
		}
	}

	public String getNombre() {
		return "Pegote";
	}

	public int getValorEvolucion() {
		return 0;
	}

}
