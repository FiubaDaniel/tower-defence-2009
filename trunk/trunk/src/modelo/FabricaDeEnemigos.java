package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jdom.Element;


/**
 * Esta clase se encarga del manejo de la creación de enemigos en el mapa. Según
 * sea el nivel del mapa, se busca la cantidad adecuada de enemigos de cada
 * clase a crear.
 * 
 * 
 * 
 */
public class FabricaDeEnemigos {

	private static FabricaDeEnemigos fabrica;

	private int Cantidad_Enemigos;
	private int NumeroNivel;
	private Queue ColaEnemigos;
	private HashMap CodigosUtiles;

	/**
	 * Este array de double guarda la cantidad base de enemigos en cada ronda En
	 * total son 8 rondas. Estos valores representan porsentajes sobre los enemigos
	 * que faltan crear. Por ej. el primer elemento del array dice que debo crear el
	 * 10% de lo que falta crear.
	 */
	private double[] niveles_base = {0.1, 0.15, 0.20, 0.30, 0.40, 0.60, 0.70, 1};

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
	public void crearNuevosEnemigos(int cantidad_enemigos) {

		Cantidad_Enemigos = cantidad_enemigos;

		if (ColaEnemigos == null)
			ColaEnemigos = new LinkedList();
		else
			ColaEnemigos.clear();
		
		
		// Itero sobre todas las rondas
		for (int i = 0; i < niveles_base.length; i++) {

			/* Aca es donde calculo los enemigos que voy a crear
			 * segun los porsentajes cargados en le array. 
			 */
			int cant_enemigos_actual = (int) (Cantidad_Enemigos
					* niveles_base[i]);
			/* A la cantidad de enemigos total que tengo que crar le
			 * resto los que voy a crear en esta ronda.
			 */
			Cantidad_Enemigos = Cantidad_Enemigos - cant_enemigos_actual;
			
			Escenario escenario = Escenario.obtenerEscenario();
			Posicion aux_pos = new Posicion(escenario.getEntrada());
			
			for (int j = 0; j < cant_enemigos_actual; j++) {
				/* Uso un random para elegir al azar que enemigo voy
				 * a crear.
				 */
				int x = (int)(Math.random() * 4);
				Enemigo para_crear = (Enemigo) CodigosUtiles.get(String
						.valueOf(x));

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
	 * Metodo para obtener la unica instancia de la clase Fabrica de Enemigos.
	 * 
	 * @param cantidad_enemigos
	 *            Cantidad de Enemigos Base del Nivel
	 * @param numeroNivel
	 *            Numero de Nivel
	 * @return retorna laa instancia de fabrica creada o crea una si no existe
	 *         ninguna.
	 */
	public static FabricaDeEnemigos obtenerFabricaEnemigos(
			int cantidad_enemigos, int numeroNivel) {
		if (fabrica == null)
			 fabrica = new FabricaDeEnemigos(cantidad_enemigos, numeroNivel);
		return fabrica;
	}

	/**
	 * Instancio una nueva fabrica de enemigos.
	 * 
	 * @param cantidad_enemigos
	 *            La cantidad total de enemigos a crear para el mapa en cuestión
	 * @param numeroNivel
	 *            El numero de nivel para el que se crearan los enemigos.
	 */
	private FabricaDeEnemigos(int cantidad_enemigos, int numeroNivel) {

		Cantidad_Enemigos = cantidad_enemigos;
		NumeroNivel = numeroNivel;

		this.cargarCodigos();

		this.crearNuevosEnemigos(cantidad_enemigos);

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

	/**
	 * Este método tiene ocmo objetivo crear el modulo de persistencia de la
	 * fabrica.
	 * 
	 * @return Retorna un elemento para persistir
	 */
	public Element persistir() {
		Element xmlElement = new Element("FabricaDeEnemigos");

		xmlElement.setAttribute("Nivel", String.valueOf(this.NumeroNivel));
		xmlElement.setAttribute("Cantidad_Enemigos", String
				.valueOf(this.Cantidad_Enemigos));

		Iterator it = ColaEnemigos.iterator();
		
		while (it.hasNext()) {
			xmlElement.addContent(((Enemigo) it.next()).persistir());
		}

		return xmlElement;
	}

	/**
	 * ESte método crea uan isntancia de la fabrica de enemigos, a partir de un
	 * elemento XML
	 * 
	 * @param xmlElement
	 *            El elemento XML que representa la Fabrica
	 * @return Una instancia de la Fabrica de Enemigos con los datos del XML
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	public static FabricaDeEnemigos recuperar(Element xmlElement) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		// Cargo los datos principales del XML
		int cant_enemigos = Integer.parseInt(xmlElement
				.getAttributeValue("Cantidad_Enemigos"));
		int num_nivel = Integer.parseInt(xmlElement.getAttributeValue("Nivel"));

		// Si no existe instancia crea uan isntancia nueva de la fabrica.
		if (fabrica == null)
			fabrica = new FabricaDeEnemigos(cant_enemigos, num_nivel);
		else {

			// Si ya existe, le cmabio los valores iniciales a la fabrica.
			fabrica.Cantidad_Enemigos = cant_enemigos;
			fabrica.NumeroNivel = num_nivel;

			fabrica.intervalo_entre_salidas = 150 / num_nivel;

		}

		/*
		 * En ambos casos, la cola de enemigos que viene con el constructor no
		 * tiene los enemigos adecuados, por eso la borramos y colocamos los
		 * enemigos del XML
		 */

		fabrica.ColaEnemigos.clear();

		List Element_List = xmlElement.getChildren();

		Iterator it = Element_List.iterator();

		while (it.hasNext()) {
			Element aux = (Element) it.next();
			if (aux.getName() == "Enemigo") {
				Enemigo enemigo = Enemigo.recuperar(aux);
				fabrica.ColaEnemigos.add(enemigo);
			}

			Escenario escenario = Escenario.obtenerEscenario();

			escenario.setCantBichos(fabrica.ColaEnemigos.size());

		}
		
		return fabrica;
	}
	
	public boolean tieneEnemigos(){
		return ColaEnemigos.isEmpty();
	}
	
	public void reiniciarFabrica(){
		Escenario escenario = Escenario.obtenerEscenario();
		ColaEnemigos.clear();
		this.crearNuevosEnemigos(escenario.getCantBichos());
	}
}
