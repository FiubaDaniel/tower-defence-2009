package pruebasIndividuales;

import junit.framework.*;
import logica.*;


public class PegoteTest extends TestCase {
	
	
	public void testElementoVoladorNoVolador() {
		
		/* El objetivo de la prueba, es comprobar como modifica la velocidad
		 * para los diferentes tipos de enemigos voladores/novoladores
		 * el pasar sobre un obstaculo del tipo Pegote.
		 */
		Escenario escenario = Escenario.obtenerEscenario();
		
		/* Tomo tres posiciones caminables del mapa comenzando con la entrada */
		Posicion primeraPosicion = escenario.getEntrada();
		Posicion segundaPosicion = Escenario.obtenerEscenario().obtenerSiguientePosicionCaminable(primeraPosicion);
		
		/* Posiciono los bichos y el pegote */
		Enemigo unaMosca = new Mosca(primeraPosicion);
		int velocidadMosca=unaMosca.getVelocidad();
		Enemigo unaCucaracha = new Cucaracha(primeraPosicion);
		int velocidadCucaracha=unaCucaracha.getVelocidad();
		Enemigo unaHormiga = new Hormiga(primeraPosicion);
		int velocidadHormiga = unaHormiga.getVelocidad();
		
		escenario.agregarEnemigoALista(unaMosca);
		escenario.agregarEnemigoALista(unaCucaracha);
		escenario.agregarEnemigoALista(unaHormiga);
		
		Obstaculo obstaculo1 = new Pegote(segundaPosicion);
		
		/* Ataco, no deberían sufrir cambios ya que no están en la misma
		 * posición.
		 */
		try {
		obstaculo1.atacar();
		} catch (Exception e) {
			System.out.println("Capturo excepcion");
		}
		
		Assert.assertEquals(velocidadMosca, unaMosca.getVelocidad());
		Assert.assertEquals(velocidadHormiga, unaHormiga.getVelocidad());
		Assert.assertEquals(velocidadCucaracha, unaCucaracha.getVelocidad());
		
		
		/* Coloco a los bichos en la posición del obstaculo
		 * 
		 */
		unaMosca.avanzar(escenario);
		unaHormiga.avanzar(escenario);
		unaCucaracha.avanzar(escenario);
		
		try {
		obstaculo1.atacar();
		} catch (Exception e) {
			System.out.println("Capturo excepcion");
		}
		
		/* Verifico que se redujo la velocidad para los elementos que no vuelan
		 * y no se altero para los que vuelan.
		 */
		Assert.assertEquals(velocidadMosca, unaMosca.getVelocidad());
		Assert.assertEquals(velocidadHormiga/2, unaHormiga.getVelocidad());
		Assert.assertEquals(velocidadCucaracha/2, unaCucaracha.getVelocidad());
		
		/*
		 * Avanzo a la siguiente posición del Pegote, lo cual no debería tener 
		 * consecuencias sobre los bichos
		 */
		unaMosca.avanzar(escenario);
		unaHormiga.avanzar(escenario);
		unaCucaracha.avanzar(escenario);
		
		
		try {
		obstaculo1.atacar();
		} catch (Exception e) {
			System.out.println("Capturo excepcion");
		}
		
		Assert.assertEquals(velocidadMosca, unaMosca.getVelocidad());
		Assert.assertEquals(velocidadHormiga/2, unaHormiga.getVelocidad());
		Assert.assertEquals(velocidadCucaracha/2, unaCucaracha.getVelocidad());	
	}
}