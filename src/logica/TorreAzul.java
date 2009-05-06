package logica;

import java.util.Iterator;
import java.util.NoSuchElementException;

import customExceptions.DineroMuyBajoException;
import customExceptions.EnemigoYaMuerto;
import customExceptions.ErrorEnemigoException;
import customExceptions.TerminoAtaqueException;
import logica.Enemigo;
import logica.Escenario;
import logica.Hormiga;
import logica.Jugador;
import logica.Posicion;

public class TorreAzul extends Torre {
	
	private Posicion salida;
	private Enemigo enemigoAatacar;
	
	/*instancio un jugador y un escenario*/
	private Jugador jugador= Jugador.obtenerJugador(1,1.5,"Daniel");
	private Escenario escenario = Escenario.obtenerEscenario();
	
	/*constructor*/
	public TorreAzul(Posicion posicionEnEscenario){ /*ver lo dell costructor*/

		if(this.getPrecio()>=jugador.getDinero()){
	    	 throw new DineroMuyBajoException();
	     }
	     else{
	    		this.setPosicion(posicionEnEscenario);	 	
	     }
	     this.setPrecio(15);
	     this.setDanioQueGenera(6);
	     this.setCostoEvolucion(10);
	     this.setEvolucion(4);
	} 
	
	
	/*
	la torre azul ataca a una distancia de entre 3 y 7 si el enemigo esta mas cerca de la salida q la torre y 
	 entre 5 y 8 si esta por delante
	 */
    public void atacar() throws ErrorEnemigoException, TerminoAtaqueException, EnemigoYaMuerto{
    	Iterator itEnemigos= escenario.getIteradordeEnemigos();
    	
    	salida=escenario.getSalida();
    	
    	while(itEnemigos.hasNext()){
			try{
				enemigoAatacar=(Enemigo) itEnemigos.next();
			}
			catch( ClassCastException e){   				
              throw  new ErrorEnemigoException();
			}
			catch (NoSuchElementException e){
			  throw new TerminoAtaqueException();
			}
		
    	if(this.getPosicion().getDistancia(salida)>salida.getDistancia(enemigoAatacar.getPosicion())){
    		if ((3<this.getPosicion().getDistancia(enemigoAatacar.getPosicion()))&& (this.getPosicion().getDistancia(enemigoAatacar.getPosicion())<7)){
   			     enemigoAatacar.disminuirVida(this.getDanioQueGenera());
    		}
    	else{
    		if ((this.getPosicion().getDistancia(enemigoAatacar.getPosicion())<8)&& (this.getPosicion().getDistancia(enemigoAatacar.getPosicion())>5)){
    			
    			     enemigoAatacar.disminuirVida(this.getDanioQueGenera());
    			}
    		}
    	}
    	if(enemigoAatacar.getVida()==0)
    		escenario.eliminarEnemigosSinVidadelaLista();
    }
   }  
    
    public void evolucionarce(){	
    	/*nombre aca tambien*/
    	if (jugador.getDinero()>getCostoEvolucion()){
    		this.setAlcance(this.getAlcance()+this.getAlcance());
    		this.setDanioQueGenera(this.getDanioQueGenera()*this.getEvolucion());
    		}	
    		else{
    			throw new DineroMuyBajoException();
    			}
    		}
}