package pruebasIndividuales;

import customExceptions.DineroMuyBajoException;
import customExceptions.ErrorEnemigoException;
import junit.framework.*;
import modelo.Arania;
import modelo.Cucaracha;
import modelo.Enemigo;
import modelo.Escenario;
import modelo.Hormiga;
import modelo.Jugador;
import modelo.Mosca;
import modelo.Posicion;
import modelo.TorrePlateada;

public class pruebaTorrePlateada extends TestCase {

	private TorrePlateada torre;
	private Enemigo enemigo, enemigo2, enemigo3, enemigo4, enemigo5,enemigo6,enemigo7,enemigo8,enemigo9;
	private Posicion posicionTorre, posicionEnemigo, posicionEnemigo2,
			posicionEnemigo3, posicionEnemigo4, posicionEnemigo5;
	private Jugador jugador;
	private Escenario escenario;
	private int vida,vida2,vida3,vida4;

	public pruebaTorrePlateada(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		escenario = Escenario.obtenerEscenario();
		posicionTorre = new Posicion(5, 5, true);
		posicionEnemigo = new Posicion(20, 6, true);
		posicionEnemigo2 = new Posicion(5, 3, true);
		posicionEnemigo3 = new Posicion(5, 18, true);
		posicionEnemigo4 = new Posicion(5, 6, true);
		posicionEnemigo5 = new Posicion(5, 7, true);

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

		torre = new TorrePlateada(posicionTorre);
	}

	public void testVerificarInicializacion() {
		Assert.assertEquals(20, torre.getPrecio());
		Assert.assertEquals(5, torre.getAlcance());
		Assert.assertEquals(2, torre.getDanioQueGenera());
	}

	public void testAtacarGenerico() throws ErrorEnemigoException {
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(1, enemigo.getVida());
		Assert.assertEquals(2, enemigo2.getVida());
		Assert.assertEquals(2, enemigo3.getVida());
		Assert.assertEquals(1, enemigo4.getVida());
		Assert.assertEquals(2, enemigo5.getVida());
	}
	
	public void testAtacarCucaracha(){
		enemigo6 = new Cucaracha(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo2);
		enemigo7 = new Cucaracha(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo2);
		enemigo8 = new Cucaracha(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo2);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		vida=enemigo6.getVida()-torre.getDanioQueGenera();
		vida2=enemigo7.getVida()-torre.getDanioQueGenera();
		vida3=enemigo8.getVida()-torre.getDanioQueGenera();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
			
	}
	
	public void testAtacarMosca(){
		enemigo6 = new Mosca(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo2);
		enemigo7 = new Mosca(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo2);
		enemigo8 = new Mosca(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo2);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		vida=enemigo6.getVida()-torre.getDanioQueGenera();
		vida2=enemigo7.getVida()-torre.getDanioQueGenera();
		vida3=enemigo8.getVida()-torre.getDanioQueGenera();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
			
	}
	
	public void testAtacarArania(){
		enemigo6 = new Arania(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo2);
		enemigo7 = new Arania(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo2);
		enemigo8 = new Arania(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo2);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		vida=enemigo6.getVida()-torre.getDanioQueGenera();
		vida2=enemigo7.getVida()-torre.getDanioQueGenera();
		vida3=enemigo8.getVida()-torre.getDanioQueGenera();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
			
	}

	public void testAtacarHormiga(){
		enemigo6 = new Hormiga(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo2);
		enemigo7 = new Hormiga(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo2);
		enemigo8 = new Hormiga(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo2);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(0, enemigo6.getVida());
		Assert.assertEquals(0, enemigo7.getVida());
		Assert.assertEquals(0, enemigo8.getVida());
	}
	
	public void testNoAtacoPorFueraDeRangoGenerico(){
		
		enemigo6 = new Hormiga(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo);
		enemigo7 = new Cucaracha(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo);
		enemigo8 = new Mosca(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo);
		enemigo9 = new Arania(escenario.getEntrada());
		enemigo9.cambiarPosicion(posicionEnemigo);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		escenario.agregarEnemigoALista(enemigo9);
		
		vida=enemigo6.getVida();
		vida2=enemigo7.getVida();
		vida3=enemigo8.getVida();
		vida4=enemigo9.getVida();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
		Assert.assertEquals(vida4, enemigo9.getVida());
		
	}
		
	public void testNoAtacoPorFueraDeRangoHormiga(){
		
		enemigo6 = new Hormiga(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo);
		enemigo7 = new Hormiga(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo);
		enemigo8 = new Hormiga(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo);
		enemigo9 = new Hormiga(escenario.getEntrada());
		enemigo9.cambiarPosicion(posicionEnemigo);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		escenario.agregarEnemigoALista(enemigo9);
		
		vida=enemigo6.getVida();
		vida2=enemigo7.getVida();
		vida3=enemigo8.getVida();
		vida4=enemigo9.getVida();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
		Assert.assertEquals(vida4, enemigo9.getVida());
		
	}
	
	public void testNoAtacoPorFueraDeRangoMosca()	{
		enemigo6 = new Mosca(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo);
		enemigo7 = new Mosca(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo);
		enemigo8 = new Mosca(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo);
		enemigo9 = new Mosca(escenario.getEntrada());
		enemigo9.cambiarPosicion(posicionEnemigo);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		escenario.agregarEnemigoALista(enemigo9);
		
		vida=enemigo6.getVida();
		vida2=enemigo7.getVida();
		vida3=enemigo8.getVida();
		vida4=enemigo9.getVida();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
		Assert.assertEquals(vida4, enemigo9.getVida());
		
	}
	
	public void testNoAtacoPorFueraDeRangoCucaracha(){
		enemigo6 = new Cucaracha(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo);
		enemigo7 = new Cucaracha(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo);
		enemigo8 = new Cucaracha(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo);
		enemigo9 = new Cucaracha(escenario.getEntrada());
		enemigo9.cambiarPosicion(posicionEnemigo);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		escenario.agregarEnemigoALista(enemigo9);
		
		vida=enemigo6.getVida();
		vida2=enemigo7.getVida();
		vida3=enemigo8.getVida();
		vida4=enemigo9.getVida();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
		Assert.assertEquals(vida4, enemigo9.getVida());
		
	}
	
	public void testNoAtacoPorFueraDeRangoArania(){
		enemigo6 = new Arania(escenario.getEntrada());
		enemigo6.cambiarPosicion(posicionEnemigo);
		enemigo7 = new Arania(escenario.getEntrada());
		enemigo7.cambiarPosicion(posicionEnemigo);
		enemigo8 = new Arania(escenario.getEntrada());
		enemigo8.cambiarPosicion(posicionEnemigo);
		enemigo9 = new Arania(escenario.getEntrada());
		enemigo9.cambiarPosicion(posicionEnemigo);
		escenario.agregarEnemigoALista(enemigo6);
		escenario.agregarEnemigoALista(enemigo7);
		escenario.agregarEnemigoALista(enemigo8);
		escenario.agregarEnemigoALista(enemigo9);
		
		vida=enemigo6.getVida();
		vida2=enemigo7.getVida();
		vida3=enemigo8.getVida();
		vida4=enemigo9.getVida();
		
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(vida, enemigo6.getVida());
		Assert.assertEquals(vida2, enemigo7.getVida());
		Assert.assertEquals(vida3, enemigo8.getVida());
		Assert.assertEquals(vida4, enemigo9.getVida());
		
	}

	public void testEvolucionar() {

		torre.evolucionarce();
		Assert.assertEquals(8, torre.getAlcance());
		Assert.assertEquals(18, torre.getCostoEvolucion());
		Assert.assertEquals(4, torre.getDanioQueGenera());
	}


}