package pruebasIndividuales;

import junit.framework.*;
import modelo.*;


public class PegoteTest extends TestCase {
	
	private Escenario escenario;
	private Posicion primeraPosicion, segundaPosicion;
	private Obstaculo obstaculo1;
	
	protected void setUp() throws Exception {
		super.setUp();
		escenario = Escenario.obtenerEscenario();
		primeraPosicion = escenario.getEntrada();
		segundaPosicion = Escenario.obtenerEscenario().obtenerSiguientePosicionCaminable(primeraPosicion);
		obstaculo1 = new Pegote(segundaPosicion);
	}
	
	/* El objetivo de la prueba, es comprobar como modifica la velocidad
	 * para los diferentes tipos de enemigos voladores/novoladores
	 * el pasar sobre un obstaculo del tipo Pegote.
	 * Tomo tres posiciones caminables del mapa comenzando con la entrada */
	
	public void testMosca() {
		Enemigo unaMosca = new Mosca(primeraPosicion);
		int velocidadMosca=unaMosca.getVelocidad();
		escenario.agregarEnemigoALista(unaMosca);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
			
		Assert.assertEquals(velocidadMosca, unaMosca.getVelocidad());
		unaMosca.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		Assert.assertEquals(velocidadMosca, unaMosca.getVelocidad());
		unaMosca.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		
		Assert.assertEquals(velocidadMosca, unaMosca.getVelocidad());
	}
	
	public void testCucaracha() {
		Enemigo unaCucaracha = new Cucaracha(primeraPosicion);
		int velocidadCucaracha=unaCucaracha.getVelocidad();
		escenario.agregarEnemigoALista(unaCucaracha);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
			
		Assert.assertEquals(velocidadCucaracha, unaCucaracha.getVelocidad());
		unaCucaracha.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}		

		Assert.assertEquals(velocidadCucaracha/2, unaCucaracha.getVelocidad());
		unaCucaracha.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		Assert.assertEquals(velocidadCucaracha/2, unaCucaracha.getVelocidad());
	}
	
	public void testHormiga() {
		
		Enemigo unaHormiga = new Hormiga(primeraPosicion);
		int velocidadHormiga = unaHormiga.getVelocidad();
		escenario.agregarEnemigoALista(unaHormiga);
					
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
			
		Assert.assertEquals(velocidadHormiga, unaHormiga.getVelocidad());
		unaHormiga.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}			
		
		Assert.assertEquals(velocidadHormiga/2, unaHormiga.getVelocidad());
		unaHormiga.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		Assert.assertEquals(velocidadHormiga/2, unaHormiga.getVelocidad());
	}
	
	public void testArania() {
		
		Enemigo unaArania = new Arania(primeraPosicion);
		int velocidadArania = unaArania.getVelocidad();
		escenario.agregarEnemigoALista(unaArania);
					
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
			
		Assert.assertEquals(velocidadArania, unaArania.getVelocidad());
		unaArania.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}			
		
		Assert.assertEquals(velocidadArania/2, unaArania.getVelocidad());
		unaArania.avanzar(escenario);
		
		try {
			obstaculo1.atacar();
			} catch (Exception e) {
				System.out.println("Capturo excepcion");
			}
		Assert.assertEquals(velocidadArania/2, unaArania.getVelocidad());
	}
}