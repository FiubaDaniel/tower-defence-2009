package logica;

import customExceptions.*;
import java.util.Iterator;

public class Pegote extends Obstaculo {
	
	public Pegote(Posicion posActual) {
		this.setPrecio(20);
		this.setPosicion(posActual);
		this.setAlcance(1);
		
	}
	
	public void atacar(Enemigo bicho) {
		
		Iterator it = Escenario.obtenerEscenario().getIteradordeEnemigos();
		while ( it.hasNext()) {
			Enemigo Aux = (Enemigo) it.next();
			if (!Aux.getVolador() && this.getPosicion().getDistancia(Aux.getPosicion()) == this.getAlcance()) {
				try {
				Aux.disminuirVelocidad(50);
				}
				catch (ValorNegativoException e) {
					System.out.println("La capturo para que no se queje el compilador");															
				}
				
			}
		}		
	}

}

