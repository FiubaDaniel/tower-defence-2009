package logica;

import customExceptions.DineroMuyBajoException;
import logica.Enemigo;
import logica.Jugador;
import logica.Posicion;

public class TorreBlanca extends Torre{
	
	/*instancio un jugador*/
	private Jugador jugador= Jugador.obtenerJugador(1,55.60,"Daniel");
	
	
	/*constructor*/
	public TorreBlanca(Posicion posicionEnEscenario){
	     if(this.getCosto()>=jugador.getDinero()){
	    	 throw new DineroMuyBajoException();
	     }
	     else{
	    	 this.setPosicion(posicionEnEscenario);	 	
	     }
	     this.setAlcance(3);
	     this.setCosto(10);
	     this.setDaño(1);
	     this.setCostoEvolucion(3);
	     this.setEvolucion(2);
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





	

