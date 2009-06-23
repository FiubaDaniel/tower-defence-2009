package modelo;

import org.jdom.Element;

/**
 * Esta clase controla todos los aspectos generales de las posiciones, desde su
 * creacion a su correcta configuracion.
 * 
 * @author hector
 * 
 */
public class Posicion implements Persistente{

	private boolean Caminable; // Si es tipo = 1 es caminable
	private int CoordX;
	private int CoordY;

	public Posicion(int coordX, int coordY, boolean tipodeCamino) {
		if ((CoordX >= 0) && (CoordY >= 0)) {
			setCoordX(coordX);
			setCoordY(coordY);
			setCaminable(tipodeCamino);
		} else
			throw new IllegalArgumentException();

	}

	public Posicion() {
		CoordX = 0;
		CoordY = 0;
		Caminable = false;
	}

	public Posicion(Posicion posicion) {
		CoordX = posicion.getCoordX();
		CoordY = posicion.getCoordY();
		Caminable = posicion.isCaminable();
	}

	public void setCaminable(boolean tipodeCamino) {
		Caminable = tipodeCamino;
	}

	public boolean isCaminable() {
		return Caminable;
	}

	public void setCoordX(int coordX) {
		if (coordX >= 0)
			CoordX = coordX;
		else
			throw new IllegalArgumentException();
	}

	public int getCoordX() {
		return CoordX;
	}

	public void setCoordY(int coordY) {
		if (coordY >= 0)
			CoordY = coordY;
		else
			throw new IllegalArgumentException();
	}

	public int getCoordY() {
		return CoordY;
	}

	/**
	 * Este método calcula la distancia entre las coordenadas de "this" con
	 * "posicion"
	 * 
	 * @param posicion Posicion con la que se quiere calcular la distancia
	 * @return distancia entre las coordenadas de "this" con "posicion"
	 */
	public double getDistancia(Posicion posicion) {

		double sqrX = Math.pow((CoordX - posicion.getCoordX()), 2);
		double sqrY = Math.pow((CoordY - posicion.getCoordY()), 2);

		return (Math.sqrt(sqrX + sqrY));
	}

	public boolean equals(Object posicion){
		Posicion aux = (Posicion)posicion;
		if ((aux.getCoordX() == CoordX)&&(aux.getCoordY() == CoordY))
			return true;
		else
			return false;
	}

	public Posicion(Element xmlElement) {

		this.CoordX = Integer.parseInt(xmlElement.getAttributeValue("CoordX"));
		this.CoordY = Integer.parseInt(xmlElement.getAttributeValue("CoordY"));
		this.Caminable = Boolean.parseBoolean(xmlElement.getAttributeValue("Caminable"));
	}
	
	public Element persistir() {
		Element xmlElement = new Element("Posicion");
        xmlElement.setAttribute("CoordX", String.valueOf(this.CoordX));
        xmlElement.setAttribute("CoordY", String.valueOf(this.CoordY));
        xmlElement.setAttribute("Caminable", String.valueOf(this.Caminable));
        return xmlElement;
	}
	
	public String toString(){
		System.out.print(CoordX+" "+CoordY);
		return "";
	}
}