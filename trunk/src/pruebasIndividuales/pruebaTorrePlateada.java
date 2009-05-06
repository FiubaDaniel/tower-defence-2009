package pruebasIndividuales;

import java.util.Iterator;

import customExceptions.DineroMuyBajoException;
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
import logica.TorrePlateada;

public class pruebaTorrePlateada extends TestCase {

	private TorrePlateada torre;
	private Enemigo enemigo,enemigo2,enemigo3,enemigo4,enemigo5;
	private Posicion posicionTorre,posicionEnemigo,posicionEnemigo2,posicionEnemigo3,posicionEnemigo4,posicionEnemigo5;
	private Jugador jugador;
	private Escenario escenario;

	
	public pruebaTorrePlateada(String name){
		super(name);   
	}

	protected void setUp() throws Exception {
		super.setUp();
		escenario= Escenario.obtenerEscenario();
		posicionTorre= new Posicion(5,5,true);
        posicionEnemigo=new Posicion(20,6,true);	
        posicionEnemigo2=new Posicion(5,3,true);
        posicionEnemigo3=new Posicion(5,18,true);
        posicionEnemigo4=new Posicion(5,6,true);
        posicionEnemigo5=new Posicion(5,7,true);
   
	    jugador= Jugador.obtenerJugador(1,55.60,"Daniel");
	    
	    enemigo=new Hormiga(escenario.getEntrada());
	         enemigo.cambiarPosicion(posicionEnemigo);
	    enemigo2=new Cucaracha(escenario.getEntrada());
	         enemigo2.cambiarPosicion(posicionEnemigo2);
	    enemigo3=new Arania(escenario.getEntrada());
	         enemigo3.cambiarPosicion(posicionEnemigo3);
	    enemigo4=new Mosca(escenario.getEntrada());
	         enemigo4.cambiarPosicion(posicionEnemigo4);    
	    enemigo5=new Cucaracha(escenario.getEntrada());
	         enemigo5.cambiarPosicion(posicionEnemigo5);
	    
	    escenario.agregarEnemigoALista(enemigo); 
		escenario.agregarEnemigoALista(enemigo2);
		escenario.agregarEnemigoALista(enemigo3);
		escenario.agregarEnemigoALista(enemigo4);
		escenario.agregarEnemigoALista(enemigo5);
		
		
	    torre = new TorrePlateada(posicionTorre);   
	}
	
	public void testVerificarInicializacion(){
		Assert.assertEquals(20,torre.getPrecio());
		Assert.assertEquals(5,torre.getAlcance());
		Assert.assertEquals(2,torre.getDanioQueGenera());
	}
	
	public void testAtacar() throws ErrorEnemigoException{
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(1, enemigo.getVida());
		Assert.assertEquals(8, enemigo2.getVida());
		Assert.assertEquals(2, enemigo3.getVida());
		Assert.assertEquals(3, enemigo4.getVida());
		Assert.assertEquals(8, enemigo5.getVida());
	}

	public void testEvolucionar(){
		
		torre.evolucionarce();
		Assert.assertEquals(8,torre.getAlcance());	
		Assert.assertEquals(18,torre.getCostoEvolucion());
		Assert.assertEquals(4,torre.getDanioQueGenera());
	}
	
	public void TestNoTienedinero(){
		
	jugador= Jugador.obtenerJugador(1,15,"Daniel");
	int i=0;
	try{
	    torre.evolucionarce();
	}catch(DineroMuyBajoException e){
		 i= 1;
	}
	Assert.assertEquals(1, i);
       }

}