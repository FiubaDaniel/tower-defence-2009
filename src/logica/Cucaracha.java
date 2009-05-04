package logica;

public class Cucaracha extends Enemigo {
	
	public Cucaracha(Posicion unLugar){
		this.setPosicion(unLugar);
		this.setResistencia(4);
		this.setVelocidad(5);
		this.setvida(10);
	}
	
	public void avanzar(Escenario terreno){
		Posicion siguiente= terreno.obtenerSiguientePosicionCaminable(this.getPosicion());
		this.cambiarPosicion(siguiente);		
	}

}
