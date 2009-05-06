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
import logica.TorreAzul;

public class pruebaTorreAzul extends TestCase {

	private TorreAzul torre;
	private Enemigo enemigo,enemigo2,enemigo3,enemigo4,enemigo5;
	private Posicion posicionTorre,posicionEnemigo,posicionEnemigo2,posicionEnemigo3,posicionEnemigo4,posicionEnemigo5,salida;
	private Jugador jugador;
	private Escenario escenario;

	
	public pruebaTorreAzul(String name){
		super(name);   
	}

	protected void setUp() throws Exception {
		super.setUp();
		escenario= Escenario.obtenerEscenario();
		posicionTorre= new Posicion(3,3,true);
        posicionEnemigo=new Posicion(7,35,true);	
        posicionEnemigo2=new Posicion(7,7,true);
        posicionEnemigo3=new Posicion(7,7,true);
        posicionEnemigo4=new Posicion(7,7,true);
        posicionEnemigo5=new Posicion(7,7,true);
   
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
		
		
	    torre = new TorreAzul(posicionTorre);   
	}
	
	public void testVerificarInicializacion(){
		
		Assert.assertEquals(6,torre.getPrecio());
		Assert.assertEquals(1,torre.getDanioQueGenera());
	}
	
	public void testAtacar() throws ErrorEnemigoException{
		try {
			torre.atacar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(1, enemigo.getVida());
		Assert.assertEquals(3, enemigo2.getVida());
		Assert.assertEquals(1, enemigo3.getVida());
		Assert.assertEquals(2, enemigo4.getVida());
		Assert.assertEquals(3, enemigo5.getVida());
	}

	public void testEvolucionar(){
		
		torre.evolucionarce();
		Assert.assertEquals(2,torre.getDanioQueGenera());	
		Assert.assertEquals(12,torre.getCostoEvolucion());	
	}
	

}