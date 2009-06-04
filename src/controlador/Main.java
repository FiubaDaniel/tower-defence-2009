package controlador;


/**
 * Clase principal donde se lanzan los threads sobre los que corre la aplicación.
 * 
 * @author Grupo 4
 *
 */
public class Main {

	public static void main(String args[]) {

		//Instancio el controladores
		ControlSimulacion Simulacion = ControlSimulacion.obtenerControl();
		//ControlVista Graficos = ControlVista.obtenerControl();
		
		//Inicio los controladores
		Simulacion.run();
		//Graficos.run();
		
						
	}
}
