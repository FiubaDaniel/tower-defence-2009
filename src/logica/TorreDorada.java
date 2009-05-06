package logica;

import customExceptions.DineroMuyBajoException;
import logica.Enemigo;
import logica.Jugador;
import logica.Posicion;

public class TorreDorada extends Torre{
	
	/*instancio un jugador*/
	private Jugador jugador= Jugador.obtenerJugador(1,60,"Daniel");
	
	/*constructor*/
	public TorreDorada(Posicion posicionEnEscenario){
	     if(this.getCosto()>=jugador.getDinero()){
	    	 throw new DineroMuyBajoException();
	     }
	     else{
	    	 	this.setPosicion(posicionEnEscenario);	 	
	     }
	     this.setAlcance(7);
	     this.setCosto(50);
	     this.setDanio(4);
	     this.setCostoEvolucion(25);
	     this.setEvolucion(6);
	}
	
	public void evolucionarce(){	
	    /*nombre aca tambien*/
		if (jugador.getDinero()>getCostoEvolucion()){
			this.setAlcance(this.getAlcance()+this.getEvolucion());
			this.setCostoEvolucion(this.getCostoEvolucion()+this.getEvolucion());
		}	
			else{
				throw new DineroMuyBajoException();
			}
		}
}
