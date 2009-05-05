package logica;

public abstract class Obstaculo {
	private int precio;
	private int dañoQueGenera;
	private int alcance;
	private Posicion lugarQueOcupa;
	
	public void setPrecio(int unPrecio) {
		this.precio = unPrecio;		
	}
	
	public int getPrecio() {
		return this.precio;
	}
	
	public void setDañoQueGenera(int unDaño) {
		this.dañoQueGenera = unDaño;
	}
	
	public int getDañoQueGenera() {
		return this.dañoQueGenera;
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
