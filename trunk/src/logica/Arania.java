package logica;

import customExceptions.*;

public class Arania extends Enemigo {
	
	public Arania(Posicion unLugar) throws NoEsEntradaException{
		this.setPosicion(unLugar);
		try{
			this.setVelocidad(3);
			this.setVida(2);
		}
		catch (ValorNegativoException error){}
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);
	}

}
