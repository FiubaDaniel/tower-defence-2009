package pruebasIntegradas;

import junit.framework.TestCase;

import org.junit.*;
import logica.*;

public class EnemigoYEscenarioTest extends TestCase{
	
	//@Test
	public void testavanzar(){
		Escenario escenario = Escenario.obtenerEscenario();
		Enemigo bicho1 = new Araña(escenario.getEntrada());
		bicho1.avanzar(escenario);
	}

}
