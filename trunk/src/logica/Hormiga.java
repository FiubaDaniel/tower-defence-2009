package logica;

import customExceptions.*;

public class Hormiga extends Enemigo {

	public Hormiga(Posicion unLugar){
		this.setPosicion(unLugar);
		try{
			this.setVelocidad(1);
			this.setVida(1);
		}
		catch (ValorNegativoException error){}
		
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);		
	}
}
