package pruebasIndividuales;

import junit.framework.TestCase;
import java.util.*;
import org.junit.*;
import modelo.*;
import customExceptions.*;

public class EnemigoTest {
	
	Escenario escenario = Escenario.obtenerEscenario();
	ArrayList<Enemigo> listaDeEnemigos = new ArrayList<Enemigo>();
		
	@Test
	public void crearEnemigos(){
		Escenario escenario = Escenario.obtenerEscenario();
		try{
			listaDeEnemigos.add(new Arania(escenario.getEntrada()));
			listaDeEnemigos.add(new Cucaracha(escenario.getEntrada()));
			listaDeEnemigos.add(new Hormiga(escenario.getEntrada()));
			listaDeEnemigos.add(new Mosca(escenario.getEntrada()));
		}
		catch (NoEsEntradaException error){
			System.out.println("crearEnemigos: La posicion pasada no es la entrada al mapa");
		}
	}
	
	@Test
	public void datosCargadosDeEnemigos(){
		Iterator<Enemigo> it = listaDeEnemigos.iterator();
		while (it.hasNext()){
			if (((Enemigo)it).getVida() <= 0)
				System.out.println("Vida mal seteada");
		}
	}
	
	@Test
	public void disminuirVida(){
		Iterator<Enemigo> it = listaDeEnemigos.iterator();
		int vidaRestada = 1;
		try{
			while (it.hasNext()){
				int valor = ((Enemigo)it).getVida() - vidaRestada;
				((Enemigo)it).disminuirVida(vidaRestada);
				if (((Enemigo)it).getVida() != valor)
					System.out.println("Error al disminuir vida");
			}
		}
		catch (EnemigoYaMuerto error){
			System.out.println("disminuirVida: El enemigo ya esta muerto");
		}
		
	}
	
	@Test
	public void disminuirVelocidad(){
		Iterator<Enemigo> it = listaDeEnemigos.iterator();
		int porcentaje = 50;
		try{
			while (it.hasNext()){
				int valor = ((Enemigo)it).getVelocidad() * porcentaje / 100;
				((Enemigo)it).disminuirVelocidad(porcentaje);
				if (((Enemigo)it).getVelocidad() != valor)
					System.out.println("Error: disminuirVelocidad");
			}
		}
		catch (ValorNegativoException error){
			System.out.println("disminuirVelocidad: El valor pasado es negativo");
		}
	}
	
		
}
	  

