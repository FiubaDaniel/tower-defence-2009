package logica;

import java.util.Iterator;

import customExceptions.MapaSinEnemigosExcepion;

import junit.framework.TestCase;
import logica.Escenario;

public class EscenarioTest extends TestCase {

	Escenario escenario = Escenario.obtenerEscenario();
	Enemigo Cobayo = new Enemigo("Prueba", 20);

	public void testAgregarEnemigoALista() {

		escenario.agregarEnemigoALista(Cobayo);
		Iterator it = escenario.getIteradordeEnemigos();
		Enemigo Auxiliar = new Enemigo("Prueba", 20);

		boolean enemigoencontrado = false;

		while (it.hasNext()) {
			Auxiliar = (Enemigo) it.next();

			if (Auxiliar.getNombre() == Cobayo.getNombre())
				enemigoencontrado = true;
		}
		if (!enemigoencontrado)
			fail("Not yet implemented");
	}

	public void testEliminarEnemigodeLista() {

		escenario.agregarEnemigoALista(Cobayo);

		try {
			escenario.eliminarEnemigodeLista(Cobayo);
		} catch (MapaSinEnemigosExcepion e) {
			fail("Enemigo no encontrado");
		}

	}

	public void testEliminarEnemigosSinVidadelaLista() {

		Enemigo Auxiliar1 = new Enemigo("Prueba", 20);
		Enemigo Auxiliar2 = new Enemigo("Prueba", 0);
		Enemigo Auxiliar3 = new Enemigo("Prueba", 20);
		Enemigo Auxiliar4 = new Enemigo("Prueba", 0);

		escenario.agregarEnemigoALista(Auxiliar1);
		escenario.agregarEnemigoALista(Auxiliar2);
		escenario.agregarEnemigoALista(Auxiliar3);
		escenario.agregarEnemigoALista(Auxiliar4);

		escenario.eliminarEnemigosSinVidadelaLista();

		if (escenario.eliminarEnemigodeLista(Auxiliar1) != true
				|| escenario.eliminarEnemigodeLista(Auxiliar2) != false
				|| escenario.eliminarEnemigodeLista(Auxiliar3) != true
				|| escenario.eliminarEnemigodeLista(Auxiliar4) != false)
			fail ("Error al eliminar");

	}

	public void testObtenerSiguientePosicionCaminable() {

		// En el mapa1 la posición (22,20) es seguida por (22,19)

		Posicion Origen = new Posicion(22, 20, true);

		Posicion Destino = escenario.obtenerSiguientePosicionCaminable(Origen);

		if (Destino.getCoordX() != 22 || Destino.getCoordY() != 19)
			fail("Error en posicion siguiente");
	}

	public void testGetIteradordeEnemigos() {

		escenario.agregarEnemigoALista(Cobayo);

		Iterator it = escenario.getIteradordeEnemigos();

		Enemigo Auxiliar = (Enemigo) it.next();

		if (Auxiliar.getNombre() != "Prueba")
			fail("Error del iterador");

	}

}
