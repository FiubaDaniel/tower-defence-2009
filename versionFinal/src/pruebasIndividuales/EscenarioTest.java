package pruebasIndividuales;

import java.io.FileNotFoundException;
import java.util.Iterator;

import customExceptions.EnemigoYaMuerto;
import customExceptions.MapaSinEnemigosExcepion;

import junit.framework.TestCase;
import modelo.Arania;
import modelo.Enemigo;
import modelo.Escenario;
import modelo.Posicion;

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
			escenario.eliminarEnemigosSinVidadelaLista();
		} catch (MapaSinEnemigosExcepion e) {
			fail("Enemigo no encontrado");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		
		escenario.limpiarListaEnemigos();

		escenario.agregarEnemigoALista(Auxiliar1);
		escenario.agregarEnemigoALista(Auxiliar2);
		escenario.agregarEnemigoALista(Auxiliar3);
		escenario.agregarEnemigoALista(Auxiliar4);
		
		try {
			escenario.eliminarEnemigosSinVidadelaLista();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail ("Error al eliminar enemigos");
		}
		
		Iterator it = escenario.getIteradordeEnemigos();
		int cant = 0;
		
		while (it.hasNext()) {
			cant++;
			it.next();
		}
		
		assertEquals(2, cant);


	}

	public void testObtenerSiguientePosicionCaminable() {

		// En el mapa1 la posición (22,20) es seguida por (22,19)

		Posicion Origen = new Posicion(26, 47, true);

		Posicion Destino = escenario.obtenerSiguientePosicionCaminable(Origen,18);

		if (Destino.getCoordX() != 26 || Destino.getCoordY() != 46)
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
