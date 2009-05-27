package modelo.terrenos;

import modelo.Posicion;

public class CaracterLinea implements LectorCaracteres {

	public boolean setearCaminable() {
		return false;
	}

	public Posicion setearEntrada(int x, int y, Posicion pos) {
		return pos;
	}

	public Posicion setearSalida(int x, int y, Posicion pos) {
		return pos;
	}

}
