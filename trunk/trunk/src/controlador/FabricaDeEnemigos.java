package controlador;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import modelo.Arania;
import modelo.Cucaracha;
import modelo.Enemigo;
import modelo.Escenario;
import modelo.Hormiga;
import modelo.Mosca;
import modelo.Posicion;

/**
 * Esta clase se encarga del manejo de la creación de enemigos en el mapa. Según
 * sea el nivel del mapa, se busca la cantidad adecuada de enemigos de cada
 * clase a crear.
 * 

 * 
 */
public class FabricaDeEnemigos {

	private int Cantidad_Enemigos;
	private int NumeroNivel;
	private Queue ColaEnemigos;
	private HashMap CodigosUtiles;

	/**
	 * Este array de float guarda la cantidad base de enemigos en cada ronda En
	 * total son 8 rondas
	 */
	private float[] niveles_base = { 0.1f, 0.1f, 0.1f, 0.1f, 0.15f, 0.15f,
			0.15f, 0.15f };

	private int intervalo_entre_salidas;

	/**
	 * Este metodo crea un HashMap que contiene que tipo de enemigo tiene que
	 * crear según el numero de ronda y según el string que reciba.
	 */
	private void cargarCodigos() {
		CodigosUtiles = new HashMap();

		Escenario escenario = Escenario.obtenerEscenario();

		Posicion aux_pos = new Posicion(escenario.getEntrada());

		/*
		 * El metodo "put" recibe el identificador de la posición como primer
		 * argumento y como segundo argumento, que devuelve si recibe el primer
		 * argumento.
		 */
		CodigosUtiles.put("0", new Hormiga(aux_pos));
		CodigosUtiles.put("1", new Arania(aux_pos));
		CodigosUtiles.put("2", new Mosca(aux_pos));
		CodigosUtiles.put("3", new Cucaracha(aux_pos));
		CodigosUtiles.put("4", new Hormiga(aux_pos));
		CodigosUtiles.put("5", new Arania(aux_pos));
		CodigosUtiles.put("6", new Mosca(aux_pos));
		CodigosUtiles.put("7", new Cucaracha(aux_pos));
		CodigosUtiles.put("Hormiga", new Hormiga(aux_pos));
		CodigosUtiles.put("Arania", new Arania(aux_pos));
		CodigosUtiles.put("Mosca", new Mosca(aux_pos));
		CodigosUtiles.put("Cucaracha", new Cucaracha(aux_pos));
	}

	/**
	 * Este método crea la lista de enemigos que deben ser creados en el mapa
	 * segun la ronda y el nivel.
	 * 
	 * @param nivel
	 */
	public void crearNuevosEnemigos(int nivel) {

		NumeroNivel = nivel;

		if (ColaEnemigos == null)
			ColaEnemigos = new LinkedList();
		else
			ColaEnemigos.clear();

		// Itero sobre todas las rondas
		for (int i = 0; i < niveles_base.length; i++) {

			// Obtengo la cantidad de enemigos a crear, en la ronda actual
			// La cuenta que se ve a continuación esta hecha para que en cada
			// aumento de 1 nivel, aumento en 50% la cantidad total de enemigos.
			// Empezando en el nivel 1, con el 50% del total de enemigos
			int cant_enemigos_actual = (int) (Cantidad_Enemigos
					* niveles_base[i] / 2 * NumeroNivel);
			Escenario escenario = Escenario.obtenerEscenario();
			Posicion aux_pos = new Posicion(escenario.getEntrada());

			for (int j = 0; j < cant_enemigos_actual; j++) {
				// Obtengo del HashMap que tipo de enemigo debo crear segun el
				// numero de la ronda
				Enemigo para_crear = (Enemigo) CodigosUtiles.get(String
						.valueOf(i));

				// Como el HashMap siemrpe devuelve la misma instancia de la
				// clase, debo crear un objeto nuevo del mismo tipo, para eso
				// uso reflexion

				// Obtengo en un array los constructores de la clase que me
				// proporciono el HashMap
				Constructor[] constructor = para_crear.getClass()
						.getConstructors();

				// Creo un array con los argumentos que recibe el constructor
				// que quiero usar, en este caso un tipo Posicion. Notar que el
				// array debe ser si o si de tipo Object
				Object args[] = new Object[1];
				args[0] = aux_pos;

				try {
					// Pido una nueva instancia del tipo de clase necesaria, por
					// medio de reflexion.
					// El metodo debe estar entre un try-catch ya que puede
					// lanzar excepciones si no encuentra un constructor
					// adecuado que reciba lso parametros proporcionados. En
					// este caso nunca sucederá porque conocemos exactamente
					// como está hecha la clase en cuestion

					para_crear = (Enemigo) constructor[0].newInstance(args);
					para_crear.cambiarPosicion(aux_pos);

					ColaEnemigos.add(para_crear);

				} catch (InstantiationException e) {
				} catch (IllegalAccessException e) {
				} catch (IllegalArgumentException e) {
				} catch (InvocationTargetException e) {
				}

			}
		}

		// Establezco el intervalo entre salidas de enemigos. EL valor 150 fue
		// puesto para que el intervalo resultante fuera adecuado, es decir si
		// muy corto ni muy largo
		intervalo_entre_salidas = 150 / NumeroNivel;

	}

	/**
	 * Instancio una nueva fabrica de enemigos.
	 * 
	 * @param cantidad_enemigos
	 *            La cantidad total de enemigos a crear para el mapa en cuestión
	 * @param numeroNivel
	 *            El numero de nivel para el que se crearan los enemigos.
	 */
	public FabricaDeEnemigos(int cantidad_enemigos, int numeroNivel) {

		Cantidad_Enemigos = cantidad_enemigos;
		NumeroNivel = numeroNivel;

		cargarCodigos();

		crearNuevosEnemigos(numeroNivel);

		Escenario escenario = Escenario.obtenerEscenario();
		
		escenario.setCantBichos(ColaEnemigos.size());
		
	}

	/**
	 * Setea la cantidad de enemigos a crear.
	 * 
	 * @param cantidad_Enemigos
	 */
	public void setCantidad_Enemigos(int cantidad_Enemigos) {
		Cantidad_Enemigos = cantidad_Enemigos;
	}

	/**
	 * 
	 * @return Devuelve la cantidad de enemigos a crear en el mapa (int)
	 */
	public int getCantidad_Enemigos() {
		return Cantidad_Enemigos;
	}

	/**
	 * 
	 * @return Devuelve el intervalo entre salida de enemigos, calculado segun
	 *         el nivel.
	 */
	public int getIntervalo_entre_salidas() {
		return intervalo_entre_salidas;
	}

	/**
	 * Este método devuelve el siguiente enemigo a crear en el mapa.
	 * 
	 * @return devuelve el siguiente enemigo a crear en el mapa. Si devuelve
	 *         null, quiere decir que la lista se termino.
	 */
	public Enemigo getSiguienteEnemigoParaCrear() {
		return (Enemigo) ColaEnemigos.poll();
	}

}
