package pruebasIndividuales;

import org.junit.*;
import logica.*;
import customExceptions.*;

public class EnemigoTest {
	
	Escenario escenario = Escenario.obtenerEscenario();
	Enemigo bicho1 = new Araña(escenario.getEntrada());
	Enemigo bicho2 = new Cucaracha(escenario.getEntrada());
	Enemigo bicho3 = new Hormiga(escenario.getEntrada());
	Enemigo bicho4 = new Mosca(escenario.getEntrada());
	
	@Test
	public void crearEnemigos(){
		Escenario escenario = Escenario.obtenerEscenario();
		Enemigo bicho1 = new Araña(escenario.getEntrada());
		Enemigo bicho2 = new Cucaracha(escenario.getEntrada());
		Enemigo bicho3 = new Hormiga(escenario.getEntrada());
		Enemigo bicho4 = new Mosca(escenario.getEntrada());
	}
	
	@Test
	public void disminuirVida(){
		int vidaRestada = 10;
		try{
			bicho1.disminuirVida(vidaRestada);
			bicho2.disminuirVida(vidaRestada);
			bicho3.disminuirVida(vidaRestada);
			bicho4.disminuirVida(vidaRestada);
		}
		catch (EnemigoYaMuerto error){}
	}
	
	@Test
	public void disminuirVelocidad(){
		int porcentaje = -1;
		try{
			bicho1.disminuirVelocidad(porcentaje);
		}
		catch (ValorNegativoException error){}
	}
	
	@Test
	public void frenarPorUnTiempo(){
		long tiempo = 1000;
		try{
			bicho1.frenarPorUnTiempo(tiempo);
			bicho2.frenarPorUnTiempo(tiempo);
			bicho3.frenarPorUnTiempo(tiempo);
			bicho4.frenarPorUnTiempo(tiempo);
		}
		catch (ValorNegativoException error){}
	}
	
} 
	  

