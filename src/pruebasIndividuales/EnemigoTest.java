package pruebasIndividuales;

import junit.framework.TestCase;

import org.junit.*;
import logica.*;
import customExceptions.*;

public class EnemigoTest extends TestCase{
	
	Escenario escenario = Escenario.obtenerEscenario();
	Enemigo bicho1 = new Arania(escenario.getEntrada());
	Enemigo bicho2 = new Cucaracha(escenario.getEntrada());
	Enemigo bicho3 = new Hormiga(escenario.getEntrada());
	Enemigo bicho4 = new Mosca(escenario.getEntrada());
	
	@Test
	public void testcrearEnemigos(){
		Escenario escenario = Escenario.obtenerEscenario();
		try{
			Enemigo bicho1 = new Arania(escenario.getEntrada());
			Enemigo bicho2 = new Cucaracha(escenario.getEntrada());
			Enemigo bicho3 = new Hormiga(escenario.getEntrada());
			Enemigo bicho4 = new Mosca(escenario.getEntrada());
		}
		catch (NoEsEntradaException error){
			System.out.println("crearEnemigos: La posicion pasada no es la entrada al mapa");
		}
	}
	
	@Test
	public void testdisminuirVida(){
		int vidaRestada = 10;
		try{
			bicho1.disminuirVida(vidaRestada);
			bicho2.disminuirVida(vidaRestada);
			bicho3.disminuirVida(vidaRestada);
			bicho4.disminuirVida(vidaRestada);
		}
		catch (EnemigoYaMuerto error){
			System.out.println("disminuirVida: El enemigo ya esta muerto");
		}
		
	}
	
	@Test
	public void testdisminuirVelocidad(){
		int porcentaje = 1;
		try{
			bicho1.disminuirVelocidad(porcentaje);
		}
		catch (ValorNegativoException error){
			System.out.println("disminuirVelocidad: El valor pasado es negativo");
		}
	}
	
	@Test
	public void testfrenarPorUnTiempo(){
		long tiempo = 1000;
		try{
			bicho1.frenarPorUnTiempo(tiempo);
			bicho2.frenarPorUnTiempo(tiempo);
			bicho3.frenarPorUnTiempo(tiempo);
			bicho4.frenarPorUnTiempo(tiempo);
		}
		catch (ValorNegativoException error){
			System.out.println("frenarPorUnTiempo: El valor pasado es negativo");
		}
	}
	
}
	  

