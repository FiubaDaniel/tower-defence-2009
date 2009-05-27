package modelo.terrenos;

import modelo.Posicion;

public interface LectorCaracteres {

	public Posicion setearEntrada(int x, int y, Posicion pos);
	public Posicion setearSalida(int x, int y, Posicion pos);
	public boolean setearCaminable();
	
}
