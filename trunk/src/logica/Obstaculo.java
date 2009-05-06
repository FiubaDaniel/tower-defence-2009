package logica;

public abstract class Obstaculo {
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
		this.alcance= unAlcance;
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
	
	public abstract void atacar()throws Exception ;
	
}
