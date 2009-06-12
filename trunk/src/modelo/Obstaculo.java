package modelo;

import java.util.Observable;

import vista.Seleccionable;

public abstract class Obstaculo extends Observable implements Seleccionable {
	private int precio;
	private int danioQueGenera;
	private int alcance;
	private Posicion lugarQueOcupa;
	private int velocidadDisparo = 1;

	public void setVelocidadDisparo(int velocidadDisparo) {
		this.velocidadDisparo = velocidadDisparo;
	}

	public void setPrecio(int unPrecio) {
		this.precio = unPrecio;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setDanioQueGenera(int unDanio) {
		this.danioQueGenera = unDanio;
	}

	public int getDanioQueGenera() {
		return this.danioQueGenera;
	}

	public void setAlcance(int unAlcance) {
		this.alcance = unAlcance;
	}

	public int getAlcance() {
		return this.alcance;
	}

	public void setPosicion(Posicion unaPosicion) {
		this.lugarQueOcupa = unaPosicion;
	}

	public Posicion getPosicion() {
		return this.lugarQueOcupa;
	}
	
	public abstract void atacar() throws Exception;

	public int getRango_Velocidad() {
		return alcance;
	}

	public int getVida_Danio() {
		return danioQueGenera;
	}
	
	public int getVelocidadDisparo() {
		return velocidadDisparo;
	}

}
