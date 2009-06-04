package modelo;

import java.io.FileNotFoundException;


public class Nivel {

	private int Dificultad;
	private static Nivel instancia = null;	
	
	public static Nivel obtenerNivel(){
		if (instancia == null)
			instancia = new Nivel();
		return instancia;
	}
	
	private Nivel(){}
	
	/**
	 * Recibe un entero, puede ser 1,2 o 3 segun:
	 * 
	 * 1 - Facil
	 * 2 - Normal
	 * 3 - Dificil
	 * 
	 * @param dificultad
	 * @throws FileNotFoundException 
	 */
	public int setDificultad(int dificultad) throws FileNotFoundException{
		Escenario escenario = Escenario.obtenerEscenario();
		Dificultad = dificultad;
		if (dificultad == 1){
			escenario.setCantBichos(20);
			escenario.elegirMapa(dificultad);
			throw new FileNotFoundException();
		}
		else
			if (dificultad == 2){
				escenario.setCantBichos(50);
				escenario.elegirMapa(dificultad);
				throw new FileNotFoundException();
			}
			else{
				escenario.setCantBichos(75);
				escenario.elegirMapa(dificultad);
				throw new FileNotFoundException();
			}
	}
	
	public int getDificultad(){
		return Dificultad;
	}
}	
	
