package logica;

import java.util.Iterator;

import customExceptions.ValorNegativoException;

public class Arena extends Obstaculo {
	
	public Arena(Posicion posActual) {
		this.setPrecio(10);
		this.setPosicion(posActual);
		this.setAlcance(0);
		
	}
	
	public void atacar() {
		
		Iterator it = Escenario.obtenerEscenario().getIteradordeEnemigos();
		while ( it.hasNext()) {
			Enemigo Aux = (Enemigo) it.next();
			if (!Aux.getVolador() && this.getPosicion().getDistancia(Aux.getPosicion()) == this.getAlcance()) {
					Aux.frenarOAvanzar();															
			}
		}		
	}
}

