package logica;

public class Araña extends Enemigo {
	
	Araña(Posicion unLugar){
		this.setPosicion(unLugar);
		this.setVelocidad(3);
		this.setResistencia(2);
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);
	}

}
