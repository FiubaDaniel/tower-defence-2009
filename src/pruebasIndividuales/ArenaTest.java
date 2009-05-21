package pruebasIndividuales;

import junit.framework.*;
import logica.*;



public class ArenaTest extends TestCase {
	
	private Escenario escenario;
	private Posicion primeraPosicion, segundaPosicion, terceraPosicion;
	private Obstaculo obstaculo1;

	
	protected void setUp() throws Exception {
		super.setUp();
		escenario = Escenario.obtenerEscenario();
		primeraPosicion = escenario.getEntrada();
		segundaPosicion = Escenario.obtenerEscenario().obtenerSiguientePosicionCaminable(primeraPosicion);
		terceraPosicion = Escenario.obtenerEscenario().obtenerSiguientePosicionCaminable(segundaPosicion);
		obstaculo1 = new Arena(segundaPosicion);
	
	}	
	
	/* El objetivo de la prueba, es comprobar como frena
	 * para los diferentes tipos de enemigos voladores/novoladores
	 * el pasar sobre un obstaculo del tipo Arena. Supongo que lo detiene al 
	 * menos por un avanzar.
	 */
	
	public void testMosca() {
		
		Enemigo unaMosca = new Mosca(primeraPosicion);
		escenario.agregarEnemigoALista(unaMosca);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		Assert.assertEquals(primeraPosicion, unaMosca.getPosicion());
		unaMosca.avanzar(escenario);
		Assert.assertEquals(segundaPosicion, unaMosca.getPosicion());

		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		unaMosca.avanzar(escenario);
		Assert.assertEquals(terceraPosicion, unaMosca.getPosicion());
	}
	
	public void testCucaracha() {
				
		Enemigo unaCucaracha = new Cucaracha(primeraPosicion);
		escenario.agregarEnemigoALista(unaCucaracha);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		Assert.assertEquals(primeraPosicion, unaCucaracha.getPosicion());
		unaCucaracha.avanzar(escenario);
		Assert.assertEquals(segundaPosicion, unaCucaracha.getPosicion());	

		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		unaCucaracha.avanzar(escenario);		
		Assert.assertEquals(segundaPosicion, unaCucaracha.getPosicion());
		unaCucaracha.avanzar(escenario);
		Assert.assertEquals(terceraPosicion, unaCucaracha.getPosicion());

	}
	
	public void testHormiga() {
		
		Enemigo unaHormiga = new Hormiga(primeraPosicion);
		escenario.agregarEnemigoALista(unaHormiga);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
			
		Assert.assertEquals(primeraPosicion, unaHormiga.getPosicion());
		unaHormiga.avanzar(escenario);
		Assert.assertEquals(segundaPosicion, unaHormiga.getPosicion());

		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		unaHormiga.avanzar(escenario);		
		Assert.assertEquals(segundaPosicion, unaHormiga.getPosicion());
		unaHormiga.avanzar(escenario);
		Assert.assertEquals(terceraPosicion, unaHormiga.getPosicion());
	}
	
	public void testArania() {
		
		Enemigo unaArania = new Arania(primeraPosicion);
		escenario.agregarEnemigoALista(unaArania);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
			
		Assert.assertEquals(primeraPosicion, unaArania.getPosicion());
		unaArania.avanzar(escenario);
		Assert.assertEquals(segundaPosicion, unaArania.getPosicion());

		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		unaArania.avanzar(escenario);		
		Assert.assertEquals(segundaPosicion, unaArania.getPosicion());
		unaArania.avanzar(escenario);
		Assert.assertEquals(terceraPosicion, unaArania.getPosicion());
	}
}