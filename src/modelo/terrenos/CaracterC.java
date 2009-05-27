package modelo.terrenos;

import modelo.Posicion;

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
