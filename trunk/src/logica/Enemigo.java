package logica;

import customExceptions.EnemigoYaMuerto;
import customExceptions.PorcentajeNegativoException;

public abstract class Enemigo {
	
	private int vida;
	private boolean bichoVolador = false;
	private int velocidad;
	private Posicion lugarQueOcupa;

	public abstract void avanzar(Escenario terreno);
	
	public void disminuirVida(int vidaRestada) throws EnemigoYaMuerto {
		try{
			if (vida == 0)
				throw new EnemigoYaMuerto();
			else
				if (vida <= vidaRestada)
					vida = 0;
				else
					vida -= vidaRestada;
		}
		catch (EnemigoYaMuerto error){
			System.out.print("El enemigo ya esta muerto");
		}
	}

	public void disminuirVelocidad(int porcentaje) throws PorcentajeNegativoException{
		try{
			if (porcentaje < 0)
				throw new PorcentajeNegativoException();
			else
				velocidad = porcentaje * velocidad / 100;
		}
		catch (PorcentajeNegativoException error){
			System.out.println("Porcentaje Negativo");
		}
	}
	
	public void frenarPorUnTiempo(long tiempoEnMilisegundos){
		long t0, t1;
		t0 = System.currentTimeMillis();
		do
			t1 = System.currentTimeMillis();
		while(t1-t0 < tiempoEnMilisegundos);
	}
	
	public Posicion getPosicion(){
		return lugarQueOcupa;
	}
	
	public int getVida(){
		return vida;
	}
	protected void setVelocidad(int velocidadInicial){
		velocidad = velocidadInicial;
	}
	
	protected void setResistencia(int vidaInicial){
		vida = vidaInicial;
	}
	
	protected void setVolador(){
		bichoVolador = true;
	}
	
	public int getVelocidad(){
		return velocidad;
	}
	
	protected void setPosicion(Posicion unLugar){
		lugarQueOcupa = unLugar;
	}
	
	public void cambiarPosicion(Posicion unLugar){
		lugarQueOcupa = unLugar;
	}
	
	public boolean getVolador(){
		return bichoVolador;
	}
	
	protected void setvida(int cantidad){
		if (cantidad > 0)
			vida = cantidad;
		else
			throw new IllegalArgumentException();
	}
}
