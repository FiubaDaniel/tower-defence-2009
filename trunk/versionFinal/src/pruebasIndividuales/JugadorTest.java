package pruebasIndividuales;

import customExceptions.DineroMuyBajoException;
import junit.framework.TestCase;
import modelo.Jugador;

public class JugadorTest extends TestCase {

	Jugador jugador1 = Jugador.obtenerJugador();

	public void testAgregarQuitarVida() {

		jugador1.quitarVida();
		assertEquals(79, jugador1.getCantidadVidas());

	}

	public void testModificarDinero() {
		try {
			jugador1.ModificarDinero(100);
			jugador1.ModificarDinero(-500);
			fail("Debería haber lanzado una excepción");
		} catch (DineroMuyBajoException e) {
			assertTrue(true);
		}
		assertEquals(180, (long) jugador1.getDinero());
	}
}
