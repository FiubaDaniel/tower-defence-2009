package pruebasIntegradas;

import org.junit.*;
import logica.*;

public class EnemigoYEscenarioTest {
	
	@Test
	public void avanzar(){
		Escenario escenario = Escenario.obtenerEscenario();
		Posicion unLugar = new Posicion(1,1,true);
		Enemigo bicho1 = new Araña(unLugar);
		bicho1.avanzar(escenario);
	}

}
