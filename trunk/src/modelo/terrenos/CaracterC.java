package modelo.terrenos;

import modelo.Posicion;

/**
 * Esta clase representa al Caracter "C", es decir el terreno caminable.
 * Para datos sobre los metodos, leer la interfaz
 * @author exus
 * 
 */
public class CaracterC implements LectorCaracteres {

	public boolean setearCaminable() {
		return true;
	}

	public Posicion setearEntrada(int x, int y, Posicion pos) {
		return pos;
	}

	public Posicion setearSalida(int x, int y, Posicion pos) {
		return pos;
	}

}
