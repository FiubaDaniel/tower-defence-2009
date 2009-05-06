package pruebasIndividuales;

import customExceptions.ErrorEnemigoException;
import junit.framework.*;
import logica.Arania;
import logica.Cucaracha;
import logica.Enemigo;
import logica.Escenario;
import logica.Hormiga;
import logica.Jugador;
import logica.Mosca;
import logica.Posicion;
import logica.TorreDorada;

public class pruebaTorreDorada extends TestCase {

	private TorreDorada torre;
	private Enemigo enemigo, enemigo2, enemigo3, enemigo4, enemigo5;
	private Posicion posicionTorre, posicionEnemigo, posicionEnemigo2,
			posicionEnemigo3, posicionEnemigo4, posicionEnemigo5;
	private Jugador jugador;
	private Escenario escenario;

	public pruebaTorreDorada(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		escenario = Escenario.obtenerEscenario();
		posicionTorre = new Posicion(5, 5, true);
		posicionEnemigo = new Posicion(20, 6, true);
		posicionEnemigo2 = new Posicion(18, 3, true);
		posicionEnemigo3 = new Posicion(5, 18, true);
		posicionEnemigo4 = new Posicion(5, 16, true);
		posicionEnemigo5 = new Posicion(5, 15, true);

		jugador = Jugador.obtenerJugador(1, 55.60, "Daniel");

		enemigo = new Hormiga(escenario.getEntrada());
		enemigo.cambiarPosicion(posicionEnemigo);
		enemigo2 = new Cucaracha(escenario.getEntrada());
		enemigo2.cambiarPosicion(posicionEnemigo2);
		enemigo3 = new Arania(escenario.getEntrada());
		enemigo3.cambiarPosicion(posicionEnemigo3);
		enemigo4 = new Mosca(escenario.getEntrada());
		enemigo4.cambiarPosicion(posicionEnemigo4);
		enemigo5 = new Cucaracha(escenario.getEntrada());
		enemigo5.cambiarPosicion(posicionEnemigo5);

		escenario.agregarEnemigoALista(enemigo);
		escenario.agregarEnemigoALista(enemigo2);
		escenario.agregarEnemigoALista(enemigo3);
		escenario.agregarEnemigoALista(enemigo4);
		escenario.agregarEnemigoALista(enemigo5);

		torre = new TorreDorada(posicionTorre);
	}

	public void testVerificarInicializacion() {
		Assert.assertEquals(50, torre.getPrecio());
		Assert.assertEquals(7, torre.getAlcance());
		Assert.assertEquals(4, torre.getDanioQueGenera());
	}

	public void testAtacar() throws ErrorEnemigoException {
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(1, enemigo.getVida());
		Assert.assertEquals(4, enemigo2.getVida());
		Assert.assertEquals(2, enemigo3.getVida());
		Assert.assertEquals(3, enemigo4.getVida());
		Assert.assertEquals(4, enemigo5.getVida());
	}

	public void testEvolucionar() {

		torre.evolucionarce();
		Assert.assertEquals(13, torre.getAlcance());
		Assert.assertEquals(31, torre.getCostoEvolucion());
	}

}