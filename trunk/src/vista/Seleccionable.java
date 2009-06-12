package vista;

/**
 * Esta interfaz está hecha para abstraerme de que estoy seleccionando en la pantalla
 * @author exus
 *
 */

public interface Seleccionable {
	
	public int getVida_Danio();
	public int getRango_Velocidad();
	
	/**
	 * Si precio devuelve 0 es que no puede ser comprado
	 * @return
	 */
	public int getValorEvolucion();
	
}
