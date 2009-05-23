package modelo;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;

public abstract class Obstaculo implements ObjetoVivo, Posicionable{
	private int precio;
	private int danioQueGenera;
	private int alcance;
	private Posicion lugarQueOcupa;

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
	
	public void vivir() {
		try {
			this.atacar();
		} catch (Exception e) {
			//El enemigo ya murio, por lo que no hace nada;
		}
	}
	
	public int getX() {
		return lugarQueOcupa.getCoordX();
	}
	public int getY() {
		return lugarQueOcupa.getCoordY();
	}

	public abstract void atacar() throws Exception;

}
