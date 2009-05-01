package logica;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.management.InvalidAttributeValueException;

import customExceptions.BaseMapNotFoundException;
import customExceptions.InvalidMapFormatException;
import customExceptions.MapNotCreatedException;
import customExceptions.MapaSinEnemigosExcepion;
import customExceptions.NoteHagaselVivoException;

public class Escenario {

	private static final int MAPCOLUMNS = 80;
	private static final int MAPROWS = 30;
	private static final double DINEROBASEMAX = 2000;
	private static Escenario escenario;
	private int CantBichos;
	private double DineroBase;
	private int CantidadDeVidaBase;
	private LinkedList CaminoAlaSalida;
	private LinkedList EnemigosEnElMapa;
	private Posicion Salida, Entrada;
	private Posicion[][] Mapa;
	private int NumeroNivel;
	private File ArchivodeMapa;

	public Escenario crearEscenario() throws BaseMapNotFoundException,
			InvalidMapFormatException, MapNotCreatedException {
		if (escenario == null)
			try {
				escenario = new Escenario();
			} catch (FileNotFoundException e) {
				throw new BaseMapNotFoundException();
			}
		return escenario;
	}

	private Escenario() throws FileNotFoundException,
			InvalidMapFormatException, MapNotCreatedException {
		this.NumeroNivel = 1;
		try {
			ArchivodeMapa = new File("/Mapa" + NumeroNivel + ".mp");
			this.cargarMapa(); // Este método tira una excepción si el formato
			// del archivo está mal
			this.crearListaAlaSalida();
		} catch (NullPointerException e) {
			throw new FileNotFoundException();
		}

	}

	private void cargarValorenPosiciondeMatriz(int fila, int columna,
			boolean caminable) throws InvalidAttributeValueException {
		if (Mapa[fila][columna] == null) {
			Mapa[fila][columna] = new Posicion(columna, fila, caminable);
		} else {
			Mapa[fila][columna].setCaminable(caminable);
			Mapa[fila][columna].setCoordX(columna);
			Mapa[fila][columna].setCoordY(fila);
		}

	}

	private void cargarMapa() throws InvalidMapFormatException {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		char auxiliardeLecturadeChars = 0;

		int i = 0, j = 0;

		try {
			fis = new FileInputStream(ArchivodeMapa);

			// utilizo un buffer de datos para agilizar la lectura del archivo
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			for (; (dis.available() != 0) && (i != MAPROWS); i++) {
				for (; (dis.available() != 0) && (j != MAPCOLUMNS); j++) {

					auxiliardeLecturadeChars = dis.readChar();

					switch (auxiliardeLecturadeChars) {
					case '-':
						cargarValorenPosiciondeMatriz(i, j, false);
						break;
					case 'c':
						cargarValorenPosiciondeMatriz(i, j, true);
						break;
					case 'S':
						cargarValorenPosiciondeMatriz(i, j, true);
						if (Salida == null)
							Salida = new Posicion(Mapa[i][j]);
						else
							this.setSalida(Mapa[i][j]);
						break;
					case 'E':
						cargarValorenPosiciondeMatriz(i, j, true);
						if (Entrada == null)
							Entrada = new Posicion(Mapa[i][j]);
						else
							this.setEntrada(Mapa[i][j]);
						break;

					}
				}
			}

			if ((i != MAPROWS) || (j != MAPCOLUMNS))
				throw new InvalidMapFormatException();

			this.setDineroBase(dis.readDouble());
			this.setCantBichos(dis.readInt());
			this.setCantidadDeVidaBase(dis.readInt());

			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace(); // TODO Cambiar esto
		} catch (IOException e) {
			e.printStackTrace(); // TODO Cambiar esto
		} catch (InvalidAttributeValueException e) {
			throw new InvalidMapFormatException();
		} catch (NoteHagaselVivoException e) {
			throw new InvalidMapFormatException();
		}
	}

	public void setCantBichos(int cantBichos)
			throws InvalidAttributeValueException {
		if (cantBichos >= 0)
			this.CantBichos = cantBichos;
		else
			throw new InvalidAttributeValueException();
	}

	public int getCantBichos() {
		return CantBichos;
	}

	private void setCaminoAlaSalida(LinkedList caminoAlaSalida) {
		this.CaminoAlaSalida = caminoAlaSalida;
	}
	public LinkedList getCaminoAlaSalida(){
		return CaminoAlaSalida;
	}

	private void setSalida(Posicion salida) {
		this.Salida = salida;
	}

	private void setEntrada(Posicion entrada) {
		this.Entrada = entrada;
	}

	public Posicion getSalida() {
		return Salida;
	}

	public Posicion getEntrada() {
		return Entrada;
	}

	public void setNumeroNivel(int numeroNivel) { // nose si debería ser publico
		// o privado
		this.NumeroNivel = numeroNivel;
	}

	public int getNumeroNivel() {
		return NumeroNivel;
	}

	private void setDineroBase(double dineroBase)
			throws NoteHagaselVivoException {
		if (dineroBase <= DINEROBASEMAX) {
			this.DineroBase = dineroBase;
		} else
			throw new NoteHagaselVivoException();
		// Si salta esta excepcion quiere decir que alguien modifico el archivo
		// de mapa con una contidad exagerada de dinero inicial
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

	public void agregarEnemigoALista(Enemigo enemigo) {
		EnemigosEnElMapa.add(enemigo);
	}

	private void subCheck(int fila, int columna, LinkedList OpcionesdeCamino,
			LinkedList ListaProvisoria) {
		if (Mapa[fila][columna].isCaminable() == true
				&& ListaProvisoria.getLast() != Mapa[fila][columna])
			OpcionesdeCamino.add(Mapa[fila][columna]);

	}

	private void checkAdd(Posicion Aux, LinkedList ListaProvisoria,
			LinkedList OpcionesdeCamino) throws InvalidMapFormatException {

		// Cada casillero se puede tomar como el siguiente dibujo

		// MI MP MI con MI = Movimiento Imposible
		// MP PA MP MP = Movimiento Posible
		// MI MP MI PA = Posicion Actual

		// Los movimientos en diagonal están prohibidos.

		// Uso try/catch separados porque si uno falla por ser una posicion de
		// los límites tiene que seguir con el resto
		// Y si falla por un error del mapa, sale del método.

		// Las "constantes" aparecen -1 porque estas representan aaprtir de que
		// número no hay más filas o columnas

		try {
			this.subCheck(Aux.getCoordY() + 1, Aux.getCoordX(),
					OpcionesdeCamino, ListaProvisoria);
		} catch (ArrayIndexOutOfBoundsException e) {
			if ((Aux.getCoordX() != MAPCOLUMNS - 1)
					|| (Aux.getCoordY() != MAPROWS - 1))
				throw new InvalidMapFormatException();
		}
		try {
			this.subCheck(Aux.getCoordY(), Aux.getCoordX() + 1,
					OpcionesdeCamino, ListaProvisoria);
		} catch (ArrayIndexOutOfBoundsException e) {
			if ((Aux.getCoordX() != MAPCOLUMNS - 1)
					|| (Aux.getCoordY() != MAPROWS - 1))
				throw new InvalidMapFormatException();
		}
		try {
			this.subCheck(Aux.getCoordY() - 1, Aux.getCoordX(),
					OpcionesdeCamino, ListaProvisoria);
		} catch (ArrayIndexOutOfBoundsException e) {
			if ((Aux.getCoordX() != MAPCOLUMNS - 1)
					|| (Aux.getCoordY() != MAPROWS - 1))
				throw new InvalidMapFormatException();
		}
		try {
			this.subCheck(Aux.getCoordY(), Aux.getCoordX() - 1,
					OpcionesdeCamino, ListaProvisoria);
		} catch (ArrayIndexOutOfBoundsException e) {
			if ((Aux.getCoordX() != MAPCOLUMNS - 1)
					|| (Aux.getCoordY() != MAPROWS - 1))
				throw new InvalidMapFormatException();
		}

	}

	private Posicion SiguientePosicionenCamino(LinkedList ListaProvisoria,
			LinkedList OpcionesdeCamino) {

		// Este algoritmos se encarga de elegir el camino correcto ante una
		// bifurcación. Esto se logra bsucando cual de las opciones de
		// camino sigue la misma dirreción con la que venía la ultima
		// posicion. Esto es más que nada para los "rulos".

		// MI BB CC con MI = Movimiento Imposible (Terreno No Caminable)
		// SS AA DD
		// MI EE MI
		// El camino deseado es EE -> AA -> BB -> CC -> DD -> AA -> SS

		// Al llegar a AA, se produce una división del camino. En las
		// opciones de camino están BB, DD y SS. EE no está porque es la
		// última posición en la ListaProvisoria. Ahora, el algoritmo
		// compara para ver que posibles caminos tienen la misma coordenada
		// X o Y de la última posicion usada (es decir comparten la misma
		// dirreción). Esto lo cumple BB, por lo que es elegida una posicion.
		// Luego el camino sigue de forma normal.

		int i = ((Posicion) ListaProvisoria.getLast()).getCoordY();
		int j = ((Posicion) ListaProvisoria.getLast()).getCoordX();

		Iterator it = OpcionesdeCamino.iterator();

		Posicion Auxiliar = null;

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

	private void crearListaAlaSalida() throws InvalidMapFormatException,
			MapNotCreatedException {
		if (Entrada != null) {
			// Si entrada es null, quiere decir que no se cargo el mapa

			LinkedList ListaProvisoria = new LinkedList();

			ListaProvisoria.add(Entrada);

			Posicion Aux = Entrada;

			while (Aux != this.Salida) {

				LinkedList OpcionesdeCamino = new LinkedList();

				this.checkAdd(Aux, ListaProvisoria, OpcionesdeCamino);

				if (OpcionesdeCamino.size() == 1) {
					ListaProvisoria.add((Posicion) OpcionesdeCamino.getLast());
					Aux = (Posicion) OpcionesdeCamino.getLast();
				} else {
					Aux = SiguientePosicionenCamino(ListaProvisoria,
							OpcionesdeCamino);
				}
			}

			this.setCaminoAlaSalida(ListaProvisoria);
		} else {
			throw new MapNotCreatedException();
		}
	}

	public void eliminarEnemigodeLista(Enemigo paraeliminar)
			throws MapaSinEnemigosExcepion {
		if (EnemigosEnElMapa.isEmpty() == false) {
			EnemigosEnElMapa.remove(paraeliminar);
		} else
			throw new MapaSinEnemigosExcepion();
	}

	public void eliminarEnemigosSinVidadelaLista() throws MapaSinEnemigosExcepion {
		Iterator it = EnemigosEnElMapa.iterator();

		Enemigo PosibleVictima = null;

		while (it.hasNext()) {

			PosibleVictima = (Enemigo) it.next();

			if (true) {
				// TODO terminar cuando este la clase enemigo, asi evito
				// error con PosibleVictima.getvida <= 0

				this.eliminarEnemigodeLista(PosibleVictima);
			}
		}

	}

	public Posicion obtenerSiguientePosicionCaminable(Posicion ubicacion) {

		Iterator it = EnemigosEnElMapa.iterator();

		Posicion Aux = null;

		while (it.hasNext() && Aux != ubicacion) {
			Aux = (Posicion) it.next();
		}

		return (Posicion) it.next();

	}

	public Iterator getIteradordeEnemigos() {

		// El iterador que devuelve este método, itera sobre objects, por lo que
		// al usar lo que devuelva habrá que cartearlo.

		Iterator it = EnemigosEnElMapa.iterator();
		return it;

	}

}
