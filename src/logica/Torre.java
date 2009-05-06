package logica;

import java.util.Iterator;
import java.util.NoSuchElementException;
import logica.Enemigo;
import logica.Escenario;
import logica.Posicion;
import java.lang.*;
import customExceptions.ErrorEnemigoException;
import customExceptions.TerminoAtaqueException;

	public abstract class Torre extends Obstaculo{
	
		private int danioQueGenera, alcance,costo,costoEvolucion,evolucion;;
		private Posicion lugarQueOcupa;
		private Enemigo enemigoAatacar; 
		
		/*instancio el escenario*/
		private Escenario escenario = Escenario.obtenerEscenario();
		
		
		public int getEvolucion(){
			return evolucion;
		}
		
		public int getCostoEvolucion(){
			return costoEvolucion;
		}

		protected void setCostoEvolucion(int costoEvolucionTorre){
			costoEvolucion=costoEvolucionTorre;
		}
		
	    protected void setEvolucion(int cantidadQueEvoluvciona){
	    	evolucion=cantidadQueEvoluvciona;
	    }
	    
		//Realizar Ataque
		
	    public void atacar() throws Exception{
	    	Iterator itEnemigos= escenario.getIteradordeEnemigos(); /*lo puse como static ver lo de singleton*/
	         while(itEnemigos.hasNext()){
	    		
	    			try{
	    				enemigoAatacar=(Enemigo) itEnemigos.next();
	    			}
	    			catch( ClassCastException e){   				
                      throw  new ErrorEnemigoException();
	    			}
	    			if (this.getPosicion().getDistancia(enemigoAatacar.getPosicion())<this.getAlcance()){
	    				enemigoAatacar.disminuirVida(this.getDanioQueGenera());   					
	    				if(enemigoAatacar.getVida()==0)
	    					escenario.eliminarEnemigosSinVidadelaLista();
	    			}
	    	}
	    }
		abstract public void evolucionarce();
					
}