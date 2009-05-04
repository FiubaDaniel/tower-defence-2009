package logica;

import java.util.*;
import customExceptions.EnemigoYaMuerto;
import customExceptions.PorcentajeNegativoException;

public abstract class Enemigo {
	
	private int resistencia;
	private boolean bichoVolador = false;
	private int velocidad;
	private Posicion lugarQueOcupa;

	public abstract void avanzar();
	
	public void disminuirVida(int vidaRestada) throws EnemigoYaMuerto {
		try{
			if (resistencia == 0)
				throw new EnemigoYaMuerto();
			else
				if (resistencia <= vidaRestada)
					resistencia = 0;
				else
					resistencia -= vidaRestada;
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
	
	protected void setVelocidad(int velocidadInicial){
		velocidad = velocidadInicial;
	}
	
	protected void setResistencia(int resistenciaInicial){
		resistencia = resistenciaInicial;
	}
	
	protected void setVolador(){
		bichoVolador = true;
	}
	
	protected void setPosicion(Posicion unLugar){
		lugarQueOcupa = unLugar;
	}
	
	public boolean getVolador(){
		return bichoVolador;
	}
}
