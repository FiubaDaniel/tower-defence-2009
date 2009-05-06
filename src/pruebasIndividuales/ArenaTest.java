package pruebasIndividuales;

import customExceptions.DineroMuyBajoException;
import junit.framework.*;
import logica.*;


public class ArenaTest extends TestCase {
	
	
	public void testElementoVoladorNoVolador() {
		
		/* El objetivo de la prueba, es comprobar como frena
		 * para los diferentes tipos de enemigos voladores/novoladores
		 * el pasar sobre un obstaculo del tipo Arena. Supongo que lo detiene al 
		 * menos por un avanzar.
		 */
		Escenario escenario = Escenario.obtenerEscenario();
		Posicion primeraPosicion = escenario.getEntrada();
		Posicion segundaPosicion = Escenario.obtenerEscenario().obtenerSiguientePosicionCaminable(primeraPosicion);
		Posicion terceraPosicion = Escenario.obtenerEscenario().obtenerSiguientePosicionCaminable(segundaPosicion);
		
		Enemigo unaMosca = new Mosca(primeraPosicion);		
		Enemigo unaCucaracha = new Cucaracha(primeraPosicion);
		Enemigo unaHormiga = new Hormiga(primeraPosicion);
		
		escenario.agregarEnemigoALista(unaMosca);
		escenario.agregarEnemigoALista(unaCucaracha);
		escenario.agregarEnemigoALista(unaHormiga);
		
		Obstaculo obstaculo1 = new Arena(segundaPosicion);
		
		/* Luego del primer ataque no se debieran producir cambios
		 * ya que no están en el alcance de la Arena 
		 */
		try {
		obstaculo1.atacar();
		} catch (Exception e) {
			System.out.println("Capturo excepcion");
		}
		
		Assert.assertEquals(primeraPosicion, unaMosca.getPosicion());
		Assert.assertEquals(primeraPosicion, unaHormiga.getPosicion());
		Assert.assertEquals(primeraPosicion, unaCucaracha.getPosicion());
		
		/* Avanzan todos los bichos, valido se mueven a la segunda Posicion */
		unaMosca.avanzar(escenario);
		unaHormiga.avanzar(escenario);
		unaCucaracha.avanzar(escenario);
		
		Assert.assertEquals(segundaPosicion, unaMosca.getPosicion());
		Assert.assertEquals(segundaPosicion, unaHormiga.getPosicion());
		Assert.assertEquals(segundaPosicion, unaCucaracha.getPosicion());
		
		/* Atacamos a los bichos que se encuentran en nuestra posición.
		 * Luego el próximo avance, debería dejar a los bichos no voladores
		 * en su lugar y a los voladores pasar a la siguiente posición. 
		 */
		try {
		obstaculo1.atacar();
		} catch (Exception e) {
			System.out.println("Capturo excepcion");
		}
		
		unaMosca.avanzar(escenario);
		unaHormiga.avanzar(escenario);
		unaCucaracha.avanzar(escenario);
		
		Assert.assertEquals(terceraPosicion, unaMosca.getPosicion());
		Assert.assertEquals(segundaPosicion, unaHormiga.getPosicion());
		Assert.assertEquals(segundaPosicion, unaCucaracha.getPosicion());
		
		/* Validamos que el avance los posiciona en la misma ubicación */
		unaHormiga.avanzar(escenario);
		unaCucaracha.avanzar(escenario);
		
		Assert.assertEquals(terceraPosicion, unaHormiga.getPosicion());
		Assert.assertEquals(terceraPosicion, unaCucaracha.getPosicion());
		
	}
}