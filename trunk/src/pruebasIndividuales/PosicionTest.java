package pruebasIndividuales;

import junit.framework.TestCase;
import logica.Posicion;

public class PosicionTest extends TestCase {
	
	public void testgetDistancia() {

		Posicion Inicial = new Posicion(); //Crea una posición en (0,0)
		Posicion Final = new Posicion(10,20, false);
		
		if (Inicial.getDistancia(Final) != 22.360679774997898)
			fail("Error en calculo de distancia");

	}
	
}
