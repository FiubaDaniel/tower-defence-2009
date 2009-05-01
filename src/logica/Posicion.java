package logica;

import javax.management.InvalidAttributeValueException;

public class Posicion {

	private boolean Caminable; // Si es tipo = 1 es caminable
	private int CoordX;
	private int CoordY;

	public Posicion(int coordX, int coordY, boolean tipodeCamino)
			throws InvalidAttributeValueException {
		if ((CoordX >= 0) && (CoordY >= 0)) {
			setCoordX(coordX);
			setCoordY(coordY);
			setCaminable(tipodeCamino);
		} else
			throw new InvalidAttributeValueException();

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

	public void setCoordX(int coordX) throws InvalidAttributeValueException {
		if (coordX >= 0)
			CoordX = coordX;
		else
			throw new InvalidAttributeValueException();
	}

	public int getCoordX() {
		return CoordX;
	}

	public void setCoordY(int coordY) throws InvalidAttributeValueException {
		if (coordY >= 0)
			CoordY = coordY;
		else
			throw new InvalidAttributeValueException();
	}

	public int getCoordY() {
		return CoordY;
	}
	
	public double getDistancia(Posicion posicion){
		
		//calcula la distancia entre las coordenadas de "this" con "posicion" 
		
		double sqrX = Math.pow((CoordX - posicion.getCoordX()), 2);
		double sqrY = Math.pow((CoordY - posicion.getCoordY()), 2);
		
		return (Math.sqrt(sqrX + sqrY));
	}

}
