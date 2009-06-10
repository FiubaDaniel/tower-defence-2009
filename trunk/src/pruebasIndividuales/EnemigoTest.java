package pruebasIndividuales;

import junit.framework.TestCase;
import java.util.*;
import org.junit.*;
import modelo.*;

public class EnemigoTest extends TestCase{
	
	Escenario escenario = Escenario.obtenerEscenario();
	ArrayList<Enemigo> listaDeEnemigos = new ArrayList<Enemigo>();
		
	public void testCrearEnemigos(){
		Escenario escenario = Escenario.obtenerEscenario();
		listaDeEnemigos.add(new Arania(escenario.getEntrada()));
		listaDeEnemigos.add(new Cucaracha(escenario.getEntrada()));
		listaDeEnemigos.add(new Hormiga(escenario.getEntrada()));
		listaDeEnemigos.add(new Mosca(escenario.getEntrada()));
		
	}
	
	
	public void testDatosCargadosDeEnemigos(){
		Iterator<Enemigo> it = listaDeEnemigos.iterator();
		while (it.hasNext()){
			if (((Enemigo)it).getVida() <= 0)
				Assert.fail("Vida mal seteada");
		}
	}
	
	public void testDisminuirVida(){
		Iterator<Enemigo> it = listaDeEnemigos.iterator();
		int vidaRestada = 1;
		while (it.hasNext()){
			int valor = ((Enemigo)it).getVida() - vidaRestada;
			((Enemigo)it).disminuirVida(vidaRestada);
			Assert.assertEquals(((Enemigo)it).getVida(),valor);
		}
	}
	
	public void testDisminuirVelocidad(){
		Iterator<Enemigo> it = listaDeEnemigos.iterator();
		int porcentaje = 50;
		while (it.hasNext()){
			int valor = ((Enemigo)it).getVelocidad() * porcentaje / 100;
			((Enemigo)it).disminuirVelocidad(porcentaje);
			Assert.assertEquals(((Enemigo)it).getVelocidad(),valor);
		}
	}
	
		
}
	  

