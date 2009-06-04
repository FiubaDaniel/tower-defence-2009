package controlador;


/**
 * Clase principal donde se lanza la aplicación.
 * 
 * @author Grupo 4
 *
 */
public class Main {

	public static void main(String args[]) {

		//Instancio el controlador
		ControlSimulacion Simulacion = ControlSimulacion.obtenerControl();
				
		//Inicio los controladores
		Simulacion.run();
								
	}
}
