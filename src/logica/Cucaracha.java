package logica;

import customExceptions.*;

public class Cucaracha extends Enemigo {
	
	public Cucaracha(Posicion unLugar) throws NoEsEntradaException{
		this.setPosicion(unLugar);
		try{
			this.setVelocidad(5);
			this.setVida(4);
		}
		catch (ValorNegativoException error){}
	}
	
	public void avanzar(Escenario terreno){
		if (getFrenado() == false) {
			Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
			this.cambiarPosicion(siguiente);
		} 
		else {
			frenarOAvanzar();
		}
	}

}