package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import customExceptions.BaseMapNotFoundException;
import customExceptions.InvalidMapFormatException;
import customExceptions.InvalidPositionException;
import customExceptions.MapNotCreatedException;
import customExceptions.NoteHagaselVivoException;

import modelo.terrenos.*;

/**
 * Esta clase controla todos los aspectos generales del manejo del mapa, desde
 * su creacion a su correcta configuracion.
 * 
 * @author hector
 * 
 */
public class Escenario implements Persistente {

	private int MAPCOLUMNS = 50;
	private int MAPROWS = 50;
	private static final double DINEROBASEMAX = 2000;
	private static Escenario escenario;
	private int CantBichos;
	private int Cant_Mapas_Disponibles;
	private double DineroBase;
	private int CantidadDeVidaBase;
	private List CaminoAlaSalida;
	private List EnemigosEnElMapa;
	private List ObstaculosEnElMapa;
	private Posicion Salida, Entrada;
	private Posicion[][] Mapa;
	private int NumeroNivel;
	private File ArchivodeMapa;
	private HashMap MapaTerreno;
	private boolean mapasDisponibles;

	/**
	 * Este metodo se encarga de la implementacion de Escenario como singleton.
	 * <p>
	 * Si ya existe un escenario, el metodo retorna ese mismo escenario. Y
	 * ademas es estatico para poder ser llamdo desde cualquier lugar.
	 * 
	 * @return Un Escenario nuevo si es el primero a ser creado o la referencia
	 *         al que ya fue creado.
	 */
	public static Escenario obtenerEscenario() {
		if (escenario == null)
			escenario = new Escenario();
		return escenario;
	}

	private void cargarMapaConfiguracion() {
		MapaTerreno = new HashMap();
		MapaTerreno.put("-", new CaracterLinea());
		MapaTerreno.put("c", new CaracterC());
		MapaTerreno.put("S", new CaracterS());
		MapaTerreno.put("E", new CaracterE());
	}

	public void reIniciar() {
		EnemigosEnElMapa.clear();
		ObstaculosEnElMapa.clear();
		this.NumeroNivel = 1;
		this.configurarMapa();
	}

	private void configurarMapa() {

		cargarMapaConfiguracion();

		search_maps();

		try {
			ArchivodeMapa = new File("Mapas/Mapa" + NumeroNivel + ".mp");
			this.cargarMapa(); // Este método tira una excepción si el formato
			// del archivo está mal
			this.crearListaAlaSalida();
			EnemigosEnElMapa = Collections.synchronizedList(new LinkedList());
			ObstaculosEnElMapa = Collections.synchronizedList(new LinkedList());
		} catch (NullPointerException e) {
			System.out.println("Archivo no encontrado");
		} catch (IllegalStateException e) {
			System.out.println("Formato de mapa Invalido");
		}
	}

	/**
	 * Este es el constructor de Escenario. Es privado para evitar las multiples
	 * llamadas e instancias de Escenario. Para crear un nuevo escenario, este
	 * constructor toma los valores de sus atributos.
	 * 
	 * @throws FileNotFoundException
	 *             Si el archivo no fue encontrado
	 * @throws InvalidMapFormatException
	 *             Si hubo un error al leer la informacion contenida en el
	 *             archivo. Es decir, se encontró el archivo, pero esta mal
	 *             configurado
	 */
	private Escenario() {
		this.NumeroNivel = 1;
		configurarMapa();
	}

	private void cargarValorenPosiciondeMatriz(int fila, int columna,
			boolean caminable) {
		if (Mapa[fila][columna] == null) {
			Mapa[fila][columna] = new Posicion(columna, fila, caminable);
		} else {
			Mapa[fila][columna].setCaminable(caminable);
			Mapa[fila][columna].setCoordX(columna);
			Mapa[fila][columna].setCoordY(fila);
		}

	}

	/**
	 * Este metodo lee el archivo que contiene la informacion del mapa y la pasa
	 * a una matriz. A su vez, carga lso valores iniciales de Dinero, Vida y
	 * Cantidad de Bichos en el escenario.
	 * 
	 * @throws InvalidMapFormatException
	 *             Si hubo algun error al acceder el archivo.
	 */
	private void cargarMapa() throws InvalidMapFormatException {
		FileReader fl = null;

		char auxiliardeLecturadeChars = 0;

		int i = 0, j = 0;

		try {

			fl = new FileReader(ArchivodeMapa);

			LectorCaracteres Lector;

			MAPROWS = (char) fl.read();
			auxiliardeLecturadeChars = (char) fl.read();
			MAPCOLUMNS = (char) fl.read();
			auxiliardeLecturadeChars = (char) fl.read();

			Mapa = new Posicion[MAPROWS][MAPCOLUMNS];

			int EOF = 0;

			for (i = 0; (EOF != -1) && (i != MAPROWS); i++) {
				for (j = 0; (EOF != -1) && (j != MAPCOLUMNS); j++) {

					auxiliardeLecturadeChars = (char) fl.read();
					if (i > 0 && j == 0) {
						auxiliardeLecturadeChars = (char) fl.read();
					}

					Lector = (LectorCaracteres) MapaTerreno.get(String
							.valueOf(auxiliardeLecturadeChars));

					cargarValorenPosiciondeMatriz(i, j, Lector
							.setearCaminable());
					Entrada = Lector.setearEntrada(j, i, Entrada);
					Salida = Lector.setearSalida(j, i, Salida);
				}
			}

			if ((i != MAPROWS) || (j != MAPCOLUMNS)) {
				throw new InvalidMapFormatException();
			}
			auxiliardeLecturadeChars = (char) fl.read();
			this.setDineroBase(fl.read() * 5);
			auxiliardeLecturadeChars = (char) fl.read();
			this.setCantBichos(fl.read());
			auxiliardeLecturadeChars = (char) fl.read();
			this.setCantidadDeVidaBase(fl.read());

			fl.close();

		} catch (IOException e) {
			throw new InvalidMapFormatException();
		} catch (IllegalArgumentException e) {
			throw new InvalidMapFormatException();
		} catch (NoteHagaselVivoException e) {
			throw new InvalidMapFormatException();
		}
	}

	public void setCantBichos(int cantBichos) {
		if (cantBichos >= 0) {
			this.CantBichos = cantBichos;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public int getCantBichos() {
		return CantBichos;
	}

	private void setCaminoAlaSalida(List caminoAlaSalida) {
		this.CaminoAlaSalida = caminoAlaSalida;
	}

	public List getCaminoAlaSalida() {
		return CaminoAlaSalida;
	}

	public Posicion getSalida() {
		return Salida;
	}

	public Posicion getEntrada() {
		return Entrada;
	}

	private void setNumeroNivel(int numeroNivel) {
		this.NumeroNivel = numeroNivel;
		configurarMapa();
		ObstaculosEnElMapa.clear();
	}

	public boolean isTerminoNivel() {
		if (CantBichos == 0)
			if (NumeroNivel < Cant_Mapas_Disponibles) {
				this.setNumeroNivel(++NumeroNivel);
				return true;
			} else {
				mapasDisponibles = false;
				return true;
			}
		else
			return false;
	}

	public boolean isMapasDisponibles() {
		return mapasDisponibles;
	}

	public int getNumeroNivel() {
		return NumeroNivel;
	}

	private void setDineroBase(double dineroBase) {
		if (dineroBase <= DINEROBASEMAX) {
			this.DineroBase = dineroBase;
		} else {
			throw new NoteHagaselVivoException();
			// Si salta esta excepcion quiere decir que alguien modifico el
			// archivo
			// de mapa con una contidad exagerada de dinero inicial
		}
	}

	public double getDineroBase() {
		return DineroBase;
	}

	private void setCantidadDeVidaBase(int cantidadDeVidaBase) {
		this.CantidadDeVidaBase = cantidadDeVidaBase;
	}

	public int getCantidadDeVidaBase() {
		return CantidadDeVidaBase;
	}

	/**
	 * Agrega un enemigo a la lista de enemigos del mapa. SI la lista no fue
	 * creada, este metodo la crea.
	 * 
	 * @param enemigo
	 *            Enemigo para agregar
	 */
	public void agregarEnemigoALista(Enemigo enemigo) {
		if (EnemigosEnElMapa.isEmpty()) {
			EnemigosEnElMapa = Collections.synchronizedList(new LinkedList());
		}
		EnemigosEnElMapa.add(enemigo);
	}

	/**
	 * Este método se asegura que la posicion a usar para llegar a la salida sea
	 * caminable y sea igual a la posicion anterior a la actual, es decir que no
	 * vaya hacia el comienzo.
	 * 
	 * @param fila
	 *            Fila de la posicion a verificar
	 * @param columna
	 *            Columna de la posicion a verificar
	 * @param OpcionesdeCamino
	 *            Lista con las opciones de posicion para colocar en el camino
	 * @param ListaProvisoria
	 *            Lista provisoria con el actual camino a la salida
	 */
	private void subCheck(int fila, int columna, LinkedList OpcionesdeCamino,
			LinkedList ListaProvisoria) {
		Iterator it = ListaProvisoria.descendingIterator();
		Posicion Aux = null;

		if (ListaProvisoria.size() != 1) {
			Aux = (Posicion) it.next(); // Me muevo al último elemento
		}
		Aux = (Posicion) it.next();

		if (Mapa[fila][columna].isCaminable() == true
				&& (Aux.getCoordX() != columna || Aux.getCoordY() != fila)) {
			OpcionesdeCamino.add(Mapa[fila][columna]);
		}
	}

	/**
	 * Cada casillero se puede tomar como el siguiente dibujo
	 * <p>
	 * 
	 * MI MP MI con MI = Movimiento Imposible
	 * <p>
	 * MP PA MP MP = Movimiento Posible
	 * <p>
	 * MI MP MI PA = Posicion Actual
	 * <p>
	 * 
	 * Los movimientos en diagonal están prohibidos.
	 * <p>
	 * 
	 * Uso try/catch separados porque si uno falla por ser una posicion de los
	 * límites tiene que seguir con el resto Y si falla por un error del mapa,
	 * sale del método.
	 * <p>
	 * 
	 * Las "constantes" aparecen -1 porque estas representan aaprtir de que
	 * número no hay más filas o columnas
	 * <p>
	 * 
	 * @param Aux
	 *            Ultima posicion colocada en la lista Provisoria
	 * @param ListaProvisoria
	 *            Lista provisoria con el actual camino a la salida
	 * @param OpcionesdeCamino
	 *            Lista con las opciones de posicion para colocar en el camino
	 */
	private void checkAdd(Posicion Aux, LinkedList ListaProvisoria,
			LinkedList OpcionesdeCamino) {

		try {
			this.subCheck(Aux.getCoordY() + 1, Aux.getCoordX(),
					OpcionesdeCamino, ListaProvisoria);
		} catch (Exception e) {
			if (Aux.getCoordY() != MAPROWS - 1) {
				throw new InvalidMapFormatException();
			}
		}

		try {
			this.subCheck(Aux.getCoordY(), Aux.getCoordX() + 1,
					OpcionesdeCamino, ListaProvisoria);
		} catch (Exception e) {
			if (Aux.getCoordX() != MAPCOLUMNS - 1) {
				throw new InvalidMapFormatException();
			}
		}

		try {
			this.subCheck(Aux.getCoordY() - 1, Aux.getCoordX(),
					OpcionesdeCamino, ListaProvisoria);
		} catch (Exception e) {
			if (Aux.getCoordY() != 0) {
				throw new InvalidMapFormatException();
			}
		}

		try {
			this.subCheck(Aux.getCoordY(), Aux.getCoordX() - 1,
					OpcionesdeCamino, ListaProvisoria);
		} catch (Exception e) {
			if (Aux.getCoordX() != 0) {
				throw new InvalidMapFormatException();
			}
		}

	}

	/**
	 * Este algoritmos se encarga de elegir el camino correcto ante una
	 * bifurcación. Esto se logra bsucando cual de las opciones de camino sigue
	 * la misma dirreción con la que venía la ultima posicion. Esto es más que
	 * nada para los "rulos".
	 * <p>
	 * 
	 * MI BB CC con MI = Movimiento Imposible (Terreno No Caminable)
	 * <p>
	 * SS AA DD
	 * <p>
	 * MI EE MI
	 * <p>
	 * 
	 * El camino deseado es EE -> AA -> BB -> CC -> DD -> AA -> SS
	 * <p>
	 * 
	 * Al llegar a AA, se produce una división del camino. En las opciones de
	 * camino están BB, DD y SS. EE no está porque es la última posición en la
	 * ListaProvisoria. Ahora, el algoritmo compara para ver que posibles
	 * caminos tienen la misma coordenada X o Y de la última posicion usada (es
	 * decir comparten la misma dirreción). Esto lo cumple BB, por lo que es
	 * elegida una posicion. Luego el camino sigue de forma normal.
	 * <p>
	 * 
	 * 
	 * @param ListaProvisoria
	 * @param OpcionesdeCamino
	 * @return La posicion siguiente valida para el camino a la salida
	 */
	private Posicion SiguientePosicionenCamino(LinkedList ListaProvisoria,
			LinkedList OpcionesdeCamino) {

		Iterator it = OpcionesdeCamino.iterator();

		Iterator itProvisoria = ListaProvisoria.descendingIterator();

		Posicion Auxiliar = null;

		if (ListaProvisoria.size() != 1) {
			itProvisoria.next(); // Me muevo al último elemento
		}
		Auxiliar = (Posicion) itProvisoria.next();

		int j = Auxiliar.getCoordY();
		int i = Auxiliar.getCoordX();

		boolean AgregueAlgo = false;

		while ((it.hasNext()) && !AgregueAlgo) {

			Auxiliar = (Posicion) it.next();

			if ((Auxiliar.getCoordX() == i) || (Auxiliar.getCoordY() == j)) {
				ListaProvisoria.add(Auxiliar);
				AgregueAlgo = true;
			}
		}

		return Auxiliar;

	}

	private void crearListaAlaSalida() {
		if (Entrada != null) {
			// Si entrada es null, quiere decir que no se cargo el mapa

			if (CaminoAlaSalida != null)
				CaminoAlaSalida.clear();

			LinkedList ListaProvisoria = new LinkedList();
			LinkedList OpcionesdeCamino = new LinkedList();

			ListaProvisoria.add(Entrada);

			Posicion Aux = Entrada;

			while ((Aux.getCoordX() != Salida.getCoordX())
					|| (Aux.getCoordY() != Salida.getCoordY())) {

				OpcionesdeCamino.clear();

				this.checkAdd(Aux, ListaProvisoria, OpcionesdeCamino);

				if (!OpcionesdeCamino.isEmpty()) {
					if (OpcionesdeCamino.size() == 1) {
						ListaProvisoria.add((Posicion) OpcionesdeCamino
								.getLast());
						Aux = (Posicion) OpcionesdeCamino.getLast();
					} else {
						Aux = SiguientePosicionenCamino(ListaProvisoria,
								OpcionesdeCamino);
					}
				} else {
					throw new IllegalStateException();
				}
			}

			this.setCaminoAlaSalida(ListaProvisoria);
		} else {
			throw new MapNotCreatedException();
		}
	}
	
	public void limpiarListaEnemigos() {
		EnemigosEnElMapa.clear();
	}

	public void eliminarEnemigosSinVidadelaLista() throws FileNotFoundException {
		Iterator it = EnemigosEnElMapa.iterator();

		Enemigo PosibleVictima = null;

		while (it.hasNext()) {

			PosibleVictima = (Enemigo) it.next();

			if (PosibleVictima.getVida() <= 0) {
				Jugador player = Jugador.obtenerJugador();
				player.ModificarDinero(PosibleVictima.getPremioDinero());
				it.remove();
				CantBichos--;
			}
		}
	}

	/**
	 * Este método devuelve la siguiente posicion a dónde debe dirigirse el
	 * enemigo.
	 * 
	 * @param ubicacion
	 *            Posicion de la posicion actual del enemigo.
	 * @return La posicion siguiente a la actual. Si se llego al final del
	 *         camino, se devuelve el comienzo.
	 */
	public Posicion obtenerSiguientePosicionCaminable(Posicion ubicacion,
			int cant_avanzada) {

		Iterator it = CaminoAlaSalida.iterator();

		Posicion Aux = new Posicion(MAPROWS + 1, MAPCOLUMNS + 1, false);

		int indice_actual = 0;

		while (indice_actual != cant_avanzada) {
			Aux = (Posicion) it.next();
			indice_actual++;
		}

		if (Aux.getCoordX() == Salida.getCoordX()
				&& Aux.getCoordY() == Salida.getCoordY()) {
			return Entrada;
		}
		return (Posicion) it.next();

	}

	public Iterator getIteradordeEnemigos() {

		// El iterador que devuelve este método, itera sobre objects, por lo que
		// al usar lo que devuelva habrá que cartearlo.

		Iterator it = EnemigosEnElMapa.iterator();
		return it;

	}

	public Iterator getIteradordeObstaculos() {

		// El iterador que devuelve este método, itera sobre objects, por lo que
		// al usar lo que devuelva habrá que cartearlo.

		Iterator it = ObstaculosEnElMapa.iterator();
		return it;
	}

	/**
	 * Este método recibe un obstaculo a insertar en el mapa. Si la posicion a
	 * insertar es invalida se lanza un InvalidPositionException
	 * 
	 * @param obstaculo
	 * @throws InvalidPositionException
	 */

	public void insertarObstaculoEnMapa(Obstaculo obstaculo) {

		boolean insertar = true;
		Iterator it_obs = ObstaculosEnElMapa.iterator();
		while (it_obs.hasNext()) {
			Obstaculo actual = (Obstaculo) it_obs.next();
			if (actual.getPosicion().getCoordX() == obstaculo.getPosicion()
					.getCoordX()
					&& actual.getPosicion().getCoordY() == obstaculo
							.getPosicion().getCoordY())
				insertar = false;
		}

		if (insertar
				&& Mapa[obstaculo.getPosicion().getCoordY()][obstaculo
						.getPosicion().getCoordX()].isCaminable()) {
			if (obstaculo.getAlcance() == 0) {
				// Si entra aca, es que es un pegote o arena.
				ObstaculosEnElMapa.add(obstaculo);
			} else {
				throw new InvalidPositionException();
			}
		} else {
			if (obstaculo.getAlcance() > 0) {
				// Si entra aca es una torre.
				ObstaculosEnElMapa.add(obstaculo);
			} else {
				throw new InvalidPositionException();
			}
		}
	}

	/**
	 * Este método recibe un enemigo a insertar en el mapa. Si la posicion a
	 * insertar es invalida se lanza un InvalidPositionException
	 * 
	 * @param enemigo
	 * @throws InvalidPositionException
	 */

	public void insertarEnemigoEnMapa(Enemigo enemigo) {
		if (Mapa[enemigo.getPosicion().getCoordX()][enemigo.getPosicion()
				.getCoordY()].isCaminable()) {
			// Si entra aca, es que es un pegote o arena.
			EnemigosEnElMapa.add(enemigo);
		} else {
			throw new InvalidPositionException();
		}
	}

	/**
	 * Este método devuelve TRUE si la posicion es caminable
	 * 
	 * @param pos
	 * @return
	 */
	public boolean obtener_tipo_de_terreno(Posicion pos) {
		try {
			return Mapa[pos.getCoordX()][pos.getCoordY()].isCaminable();
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public int getMapColumns() {
		return MAPCOLUMNS;
	}

	public int getMapRows() {
		return MAPROWS;
	}

	/**
	 * Este método verifica la cantidad de archivos de mapas disponibles que
	 * hay. Esto lo hace, tratando de abrir todos los posibles, y cuando salta
	 * una excepcion quiere decir que ese archivo no existe.
	 */

	private void search_maps() {
		int numero_intento = 1;
		File Mapas;
		FileReader fl;
		try {
			while (true) {
				Mapas = new File("Mapas/Mapa" + numero_intento + ".mp");
				fl = new FileReader(Mapas);
				numero_intento++;
				fl.close();
			}
		} catch (FileNotFoundException e) {
			this.setCant_Mapas_Disponibles(numero_intento - 1);
			this.mapasDisponibles = true;
		} catch (IOException e) {
		}
	}

	public void setCant_Mapas_Disponibles(int cant_Mapas_Disponibles) {
		Cant_Mapas_Disponibles = cant_Mapas_Disponibles;
	}

	public int getCant_Mapas_Disponibles() {
		return Cant_Mapas_Disponibles;
	}

	private void setRecuperar(Element xmlElement) throws FileNotFoundException,
			ClassNotFoundException, SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {

		this.NumeroNivel = Integer.parseInt(xmlElement
				.getAttributeValue("Nivel"));

		this.setNumeroNivel(this.NumeroNivel);

		EnemigosEnElMapa.clear();
		ObstaculosEnElMapa.clear();

		List Element_list = xmlElement.getChildren();

		Iterator it = Element_list.iterator();

		while (it.hasNext()) {
			Element actual = (Element) it.next();
			if (actual.getName() == "Obstaculo") {
				Obstaculo obstaculo = Obstaculo.recuperar(actual);
				ObstaculosEnElMapa.add(obstaculo);
			} else if (actual.getName() == "Enemigo") {
				Enemigo enemigo = Enemigo.recuperar(actual);
				EnemigosEnElMapa.add(enemigo);
			}
		}

		this.CantBichos = EnemigosEnElMapa.size();
	}

	public void recuperar(Element xmlElement) {

		try {
			this.setRecuperar(xmlElement);
		} catch (FileNotFoundException e) {
			throw new BaseMapNotFoundException();
		} catch (SecurityException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (NoSuchMethodException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (InstantiationException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (IllegalAccessException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (InvocationTargetException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Element persistir() {
		Element xmlElement = new Element("Escenario");
		Iterator it = EnemigosEnElMapa.iterator();
		while (it.hasNext()) {
			xmlElement.addContent(((Enemigo) it.next()).persistir());
		}
		it = ObstaculosEnElMapa.iterator();
		while (it.hasNext())
			xmlElement.addContent(((Obstaculo) it.next()).persistir());

		xmlElement.setAttribute("Nivel", String.valueOf(this.NumeroNivel));
		return xmlElement;
	}

}
