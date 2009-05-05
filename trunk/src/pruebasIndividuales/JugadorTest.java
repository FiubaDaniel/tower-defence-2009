package pruebasIndividuales;

import customExceptions.DineroMuyBajoException;
import junit.framework.TestCase;
import logica.Jugador;

public class JugadorTest extends TestCase {

	Jugador jugador1 = Jugador.obtenerJugador(20, 50, "Prueba");

	public void testAgregarQuitarVida() {

		jugador1.AgregarQuitarVida(10);
		assertEquals(30, jugador1.getCantidadVidas());

	}
	public void testModificarDinero() {
		try {
			jugador1.ModificarDinero(100);
			jugador1.ModificarDinero(-500);
			fail("Debería haber lanzado una excepción");
		} catch (DineroMuyBajoException e) {
			assertTrue(true);
		}
		assertEquals(150, (long) jugador1.getDinero());
	}
	public void testAgregarPuntos() {
		try {
			jugador1.agregarPuntos(100);
			jugador1.agregarPuntos(-500);
			fail("Debería haber lanzado una excepción");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		assertEquals(100, (long) jugador1.getPuntos());
	}

}
