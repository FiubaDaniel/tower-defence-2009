package logica;

import javax.management.InvalidAttributeValueException;

public class Posicion {

	private boolean TipodeCamino; // Si es tipo = 1 es caminable
	private int CoordX;
	private int CoordY;

	public Posicion(int coordX, int coordY, boolean tipodeCamino)
			throws InvalidAttributeValueException {
		if ((CoordX >= 0) && (CoordY >= 0)) {
			setCoordX(coordX);
			setCoordY(coordY);
			setTipodeCamino(tipodeCamino);
		} else
			throw new InvalidAttributeValueException();

	}

	public void setTipodeCamino(boolean tipodeCamino) {
		TipodeCamino = tipodeCamino;
	}

	public boolean isTipodeCamino() {
		return TipodeCamino;
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

}
