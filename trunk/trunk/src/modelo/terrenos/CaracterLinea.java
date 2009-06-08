package modelo.terrenos;

import modelo.Posicion;

/**
 * Esta clase representa al Caracter "-", es decir el terreno no caminable.
 * Para datos sobre los metodos, leer la interfaz
 * @author exus
 * 
 */
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
