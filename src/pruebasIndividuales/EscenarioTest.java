package pruebasIndividuales;

import java.util.Iterator;

import customExceptions.EnemigoYaMuerto;
import customExceptions.MapaSinEnemigosExcepion;

import junit.framework.TestCase;
import logica.Arania;
import logica.Enemigo;
import logica.Escenario;
import logica.Posicion;

public class EscenarioTest extends TestCase {

	Escenario escenario = Escenario.obtenerEscenario();
	Posicion PosicionGenerica = escenario.getEntrada();
	Enemigo Cobayo = new Arania(PosicionGenerica);

	public void testAgregarEnemigoALista() {

		escenario.agregarEnemigoALista(Cobayo);
		Iterator it = escenario.getIteradordeEnemigos();
		Enemigo Auxiliar = new Arania(PosicionGenerica);

		boolean enemigoencontrado = false;

		while (it.hasNext()) {
			Auxiliar = (Enemigo) it.next();

			if (Auxiliar.equals((Enemigo)Cobayo));
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

		Enemigo Auxiliar1 = new Arania(PosicionGenerica);
		Enemigo Auxiliar2 = new Arania(PosicionGenerica);
		Enemigo Auxiliar3 = new Arania(PosicionGenerica);
		Enemigo Auxiliar4 = new Arania(PosicionGenerica);
			
		try {
			Auxiliar2.disminuirVida(20);
			Auxiliar4.disminuirVida(20);
			
		} catch (EnemigoYaMuerto e) {
			fail ("Error con enemigo");
		}

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

		if (Auxiliar.equals((Enemigo)Cobayo))
			fail("Error del iterador");

	}

}
