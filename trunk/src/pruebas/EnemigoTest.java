package pruebas;

import org.junit.*;
import logica.Enemigo;
import logica.Posicion;
import logica.Araña;

public class EnemigoTest {
	
	public void crear(){
		boolean valor = true;
		Posicion lugar = new Posicion(1,1,valor);
		Enemigo bicho = new Araña(lugar);
	}
	
}	
	 
	  

