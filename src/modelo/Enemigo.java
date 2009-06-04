package modelo;

import java.util.Observable;

//import vista.Seleccionable;

import customExceptions.EnemigoYaMuerto;
import customExceptions.NoEsEntradaException;
import customExceptions.ValorNegativoException;

public abstract class Enemigo extends Observable {

	private int vida;
	private boolean bichoVolador = false;
	private int velocidad;
	private Posicion lugarQueOcupa;
	private boolean frenado = false;
	
	public abstract void avanzar(Escenario terreno);

	/**
	 * Si el enemigo ya esta muerto lanza una excepcion, sino le disminuye la vida
	 * 
	 * @param vidaRestada
	 * @throws EnemigoYaMuerto
	 */
	public void disminuirVida(int vidaRestada) throws EnemigoYaMuerto {
		if (vida == 0)
			throw new EnemigoYaMuerto();
		else if (vida <= vidaRestada)
			vida = 0;
		else
			vida -= vidaRestada;
	}

	/**
	 * Disminuye la velocidad, se le debe pasar el porcentaje de velocidad que 
	 * se desee que le quede para moverse. Cuidado!! que no sea negativo.
	 * 
	 * @param porcentaje
	 * @throws ValorNegativoException
	 */
	public void disminuirVelocidad(int porcentaje) throws ValorNegativoException {
		if (porcentaje < 0)
			throw new ValorNegativoException();
		else
			velocidad = porcentaje * velocidad / 100;
	}

	public boolean getFrenado() {
		return frenado;
	}

	/**
	 * Si el enemigo se encuentra en movimiento es frenado con este metodo y luego
	 * se lo vuelve a animar con en el mismo
	 * 
	 * @author dario
	 */
	public void frenarOAvanzar() {
		if (frenado)
			frenado = false;
		else
			frenado = true;
	}

	public Posicion getPosicion() {
		return lugarQueOcupa;
	}

	public int getVida() {
		return vida;
	}

	protected void setVelocidad(int velocidadInicial) throws ValorNegativoException {
		if (velocidadInicial < 0)
			throw new ValorNegativoException();
		else
			velocidad = velocidadInicial;
	}

	protected void setVida(int vidaInicial) throws ValorNegativoException {
		if (vidaInicial < 0)
			throw new ValorNegativoException();
		else
			vida = vidaInicial;

	}

	protected void setVolador() {
		bichoVolador = true;
	}

	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * La posicion inicial del enemigo debe ser una entrada del mapa.
	 * 
	 * @param entrada
	 * @throws NoEsEntradaException
	 */
	protected void setPosicion(Posicion entrada) throws NoEsEntradaException {
		Escenario escenario = Escenario.obtenerEscenario();
		if (entrada.getCoordX() != escenario.getEntrada().getCoordX() || entrada.getCoordY() != escenario.getEntrada().getCoordY())
			throw new NoEsEntradaException();
		else
			lugarQueOcupa = entrada;
	}

	public void cambiarPosicion(Posicion unLugar) {
		lugarQueOcupa = unLugar;
	}

	public boolean getVolador() {
		return bichoVolador;
	}
	
	/*public void setCant_avanzada(int cant_avanzada) {
		this.cant_avanzada = cant_avanzada;
	}

	public int getCant_avanzada() {
		return cant_avanzada;
	}
	
	public int getRango_Velocidad() {
		return velocidad;
	}

	public int getVida_Danio() {
		return vida;
	}*/
	

}
