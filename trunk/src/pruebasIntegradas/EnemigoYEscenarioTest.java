package pruebasIntegradas;

import org.junit.*;
import logica.*;

public class EnemigoYEscenarioTest {
	
	@Test
	public void avanzar(){
		Escenario escenario = Escenario.obtenerEscenario();
		Enemigo bicho1 = new Araña(escenario.getEntrada());
		bicho1.avanzar(escenario);
	}

}
