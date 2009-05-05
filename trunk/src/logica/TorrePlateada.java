package logica;

import customExceptions.DineroMuyBajoException;
import logica.Enemigo;
import logica.Jugador;
import logica.Posicion;

public class TorrePlateada extends Torre{

	/*instancio un jugador*/
	private Jugador jugador= Jugador.obtenerJugador(1,1.5,"Daniel");
	
	/*constructor*/
	public TorrePlateada(Posicion posicionEnEscenario){
	     if(this.getCosto()>=jugador.getDinero()){
	    	 throw new DineroMuyBajoException();
	     }
	     else{
	   	 	this.setPosicion(posicionEnEscenario);	 	
	     }
	     this.setAlcance(5);
	     this.setCosto(20);
	     this.setDaño(2);
	     this.setCostoEvolucion(15);
	     this.setEvolucion(3);
	
	}
	
	public void evolucionarce(){	
	    /*nombre aca tambien*/
		if (jugador.getDinero()>this.getCostoEvolucion()){
		    this.setAlcance(this.getAlcance()+this.getEvolucion());
		    this.setCostoEvolucion(this.getCostoEvolucion()+this.getEvolucion());
		    this.setDaño(this.getDaño()+this.getDaño());
		}
			else{
				throw new DineroMuyBajoException();
			}
		}

}
