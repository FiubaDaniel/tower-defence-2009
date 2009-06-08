package modelo.terrenos;

import modelo.Posicion;

/**
 * Esta clase representa al Caracter "E", es decir el terreno de Entrada.
 * Para datos sobre los metodos, leer la interfaz
 * @author exus
 * 
 */
public class CaracterE implements LectorCaracteres {

	public boolean setearCaminable() {
		return true;
	}

	public Posicion setearEntrada(int x, int y, Posicion pos) {
		if (pos == null) {
			pos = new Posicion(x, y, true);
		} else {
			pos.setCoordX(x);
			pos.setCoordY(y);
			pos.setCaminable(true);
		}
		
		return pos;
	}

	public Posicion setearSalida(int x, int y, Posicion pos) {
		return pos;
	}

}
