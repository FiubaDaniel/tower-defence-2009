package modelo.terrenos;

import modelo.Posicion;

/**
 * Esta interfaz debe ser implementada en todas las clases que representen un
 * caracter a ser leido desde el archivo de mapa
 * 
 * @author exus
 * 
 */
public interface LectorCaracteres {

	/**
	 * Este metodo se encarga de tomar las acciones adecuadas sobre la posicion
	 * de Entrada.
	 */
	public Posicion setearEntrada(int x, int y, Posicion pos);

	/**
	 * Este metodo se encarga de tomar las acciones adecuadas sobre la posicion
	 * de Salida.
	 */
	public Posicion setearSalida(int x, int y, Posicion pos);

	/**
	 * Este metodo devuelve si este caracter representa un terreno caminable o
	 * no.
	 */
	public boolean setearCaminable();

}
