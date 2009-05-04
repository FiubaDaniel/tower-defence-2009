package logica;

public class Mosca extends Enemigo {

	Mosca(Posicion unLugar){
		this.setPosicion(unLugar);
		this.setResistencia(3);
		this.setVelocidad(4);
		this.setVolador();
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);		
	}
}
