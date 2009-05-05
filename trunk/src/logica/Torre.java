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
	
		private int dañoQueGenera, alcance,costo,costoEvolucion,evolucion;;
		private Posicion lugarQueOcupa;
		private Enemigo enemigoAatacar; 
		
		/*instancio el escenario*/
		private Escenario escenario = Escenario.obtenerEscenario();
		
		public int getCosto(){
			return costo;
		}
		
		public Posicion getPosicion(){
			return lugarQueOcupa;
		}
		
		public int getDaño(){
			return dañoQueGenera;
		}
		
		public int getAlcance(){
			return alcance;
		}
		
		public int getEvolucion(){
			return evolucion;
		}
		
		public int getCostoEvolucion(){
			return costoEvolucion;
		}
		
		protected void setDaño(int daño){
			 dañoQueGenera= daño;
		}

		protected void setCosto(int presio){
			 costo=presio;
		}
		
		protected void setCostoEvolucion(int costoEvolucionTorre){
			costoEvolucion=costoEvolucionTorre;
		}
		
	    protected void setEvolucion(int cantidadQueEvoluvciona){
	    	evolucion=cantidadQueEvoluvciona;
	    }
	    
		//Reañlizar Ataque
		
	    public void atacar() throws Exception{
	    	Iterator itEnemigos= escenario.getIteradordeEnemigos(); /*lo puse como static ver lo de singleton*/
	         while(itEnemigos.hasNext()){
	    		
	    			try{
	    				enemigoAatacar=(Enemigo) itEnemigos.next();
	    			}
	    			catch( ClassCastException e){   				
                      throw  new ErrorEnemigoException();
	    			}
	    			/*catch (NoSuchElementException e){
	    			  throw new TerminoAtaqueException();
	    			}*/
	    			if (this.lugarQueOcupa.getDistancia(enemigoAatacar.getPosicion())<this.alcance){
	    				enemigoAatacar.disminuirVida(dañoQueGenera);   					
	    				if(enemigoAatacar.getVida()==0)
	    					escenario.eliminarEnemigosSinVidadelaLista();
	    			}
	    	}
	    }
		abstract public void evolucionarce();
					
}

