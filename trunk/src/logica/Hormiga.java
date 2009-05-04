package logica;

public class Hormiga extends Enemigo {

	public Hormiga(Posicion unLugar){
		this.setPosicion(unLugar);
		this.setResistencia(1);
		this.setVelocidad(1);
		this.setvida(1);
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);		
	}
}
