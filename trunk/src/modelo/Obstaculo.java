package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;



import org.jdom.Element;
import org.omg.CORBA.Object;



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

	public int getValorEvolucion() {
		return 0;
	}

	public Element persistir() {
		Element xmlElement = new Element("Obstaculo");
        xmlElement.addContent(lugarQueOcupa.persistir());
        xmlElement.setAttribute("Tipo", this.getClass().getName());
        return xmlElement;
	}
	
	public static Obstaculo recuperar(Element actual) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Element posXML=actual.getChild("Posicion");
		Posicion pos=new Posicion(posXML);
		Class<?> claseActual = Class.forName(actual.getAttributeValue("Tipo"));
		Constructor<?> constructor = claseActual.getDeclaredConstructor(Posicion.class);
		Obstaculo Obs = (Obstaculo) constructor.newInstance(pos);
		return Obs;
	}
}
