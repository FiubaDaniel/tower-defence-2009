package modelo;

import org.jdom.Element;

public abstract class Obstaculo implements Seleccionable, Persistente {
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

	public void persistir(String nombreArchivo){
		
	}

	public int getValorEvolucion() {
		return 0;
	}

	public Element persistir() {
		Element xmlElement = new Element("Obstaculo");
        xmlElement.addContent(lugarQueOcupa.persistir());
        xmlElement.setAttribute("Tipo", this.getClass().getCanonicalName());
        return xmlElement;
	}
}
