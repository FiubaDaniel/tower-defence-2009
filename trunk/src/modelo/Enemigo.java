package modelo;

import org.jdom.Element;


import customExceptions.*;

public abstract class Enemigo  implements Seleccionable, Persistente {

	private int vida;
	private boolean bichoVolador = false;
	private int velocidad;
	private Posicion lugarQueOcupa;
	private boolean frenado = false;
	protected int cant_avanzada = 0;
	private int premioDinero;

	public abstract void avanzar(Escenario terreno);

	public void disminuirVida(int vidaRestada) throws EnemigoYaMuerto {
		if (vida == 0)
			throw new EnemigoYaMuerto();
		else if (vida <= vidaRestada)
			vida = 0;
		else
			vida -= vidaRestada;
	}

	public void disminuirVelocidad(int porcentaje) throws ValorNegativoException {
		if (porcentaje < 0)
			throw new ValorNegativoException();
		else
			velocidad = porcentaje * velocidad / 100;
	}

	public boolean getFrenado() {
		return frenado;
	}

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

	protected void setPosicion(Posicion entrada) throws NoEsEntradaException {
		Escenario escenario = Escenario.obtenerEscenario();
		if (!entrada.equals(escenario.getEntrada()))
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
	
	public void setCant_avanzada(int cant_avanzada) {
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
	}

	public int getValorEvolucion() {
		return 0;
	}

	protected void setPremioDinero(int premioDinero) {
		this.premioDinero = premioDinero;
	}

	public int getPremioDinero() {
		return premioDinero;
	}

	public Element persistir() {
		return null;
	}
}
