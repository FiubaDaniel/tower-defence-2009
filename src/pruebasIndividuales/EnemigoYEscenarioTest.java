package pruebasIndividuales;

import junit.framework.TestCase;
import org.junit.*;
import customExceptions.*;
import modelo.*;

public class EnemigoYEscenarioTest {
	
	@Test
	public void avanzar(){
		try{
			Escenario escenario = Escenario.obtenerEscenario();
			Enemigo bicho1 = new Arania(escenario.getEntrada());
			bicho1.avanzar(escenario);
		}
		catch (NoEsEntradaException error){
			System.out.println("Error: No se puede entrar por cualquier lado");
		}
	}

}
