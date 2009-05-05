package logica;

import customExceptions.*;

public abstract class Enemigo {
	
	private int vida;
	private boolean bichoVolador = false;
	private int velocidad;
	private Posicion lugarQueOcupa;

	public abstract void avanzar(Escenario terreno);
	
	public void disminuirVida(int vidaRestada) throws EnemigoYaMuerto {
		if (vida == 0)
			throw new EnemigoYaMuerto();
		else
			if (vida <= vidaRestada)
				vida = 0;
			else
				vida -= vidaRestada;
	}
	
	public void disminuirVelocidad(int porcentaje) throws ValorNegativoException{
		if (porcentaje < 0)
			throw new ValorNegativoException();
		else
			velocidad = porcentaje * velocidad / 100;
	}
	
	public void frenarPorUnTiempo(long tiempoEnMilisegundos) throws ValorNegativoException {
		if (tiempoEnMilisegundos < 0)
			throw new ValorNegativoException();
		else{
			long t0, t1;
			t0 = System.currentTimeMillis();
			do
				t1 = System.currentTimeMillis();
			while(t1-t0 < tiempoEnMilisegundos);
		}
	}
	
	public Posicion getPosicion(){
		return lugarQueOcupa;
	}
	
	public int getVida(){
		return vida;
	}
	
	protected void setVelocidad(int velocidadInicial) throws ValorNegativoException {
		if (velocidadInicial < 0)
			throw new ValorNegativoException();
		else
			velocidad = velocidadInicial;
	}
	
	protected void setVida(int vidaInicial) throws ValorNegativoException{
		if (vidaInicial < 0)
			throw new ValorNegativoException();
		else
			vida = vidaInicial;
		
	}
	
	protected void setVolador(){
		bichoVolador = true;
	}
	
	public int getVelocidad(){
		return velocidad;
	}
	
	protected void setPosicion(Posicion entrada) throws NoEsEntradaException{
		Escenario escenario = Escenario.obtenerEscenario();
		if (entrada != escenario.getEntrada())
			throw new NoEsEntradaException();
		else
			lugarQueOcupa = entrada;
	}
	
	public void cambiarPosicion(Posicion unLugar){
		lugarQueOcupa = unLugar;
	}
	
	public boolean getVolador(){
		return bichoVolador;
	}
		
}
