package logica;

import customExceptions.*;

public class Mosca extends Enemigo {

	public Mosca(Posicion unLugar){
		this.setPosicion(unLugar);
		this.setVolador();
		try{
			this.setVelocidad(4);
			this.setVida(5);
		}
		catch (ValorNegativoException error){}
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);		
	}
}
