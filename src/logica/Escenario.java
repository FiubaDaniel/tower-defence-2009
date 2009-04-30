package logica;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.management.InvalidAttributeValueException;

import custom_exceptions.BaseMapNotFoundException;
import custom_exceptions.InvalidMapFormatException;

public class Escenario {

	private static final int MapColumns = 80;
	private static final int MapRows = 30;
	private static Escenario escenario;
	private int CantBichos;
	private double DineroBase;
	private int CantidadDeVidaBase;
	private LinkedList CaminoAlaSalida;
	private Posicion Salida, Entrada;
	private Posicion[][] Mapa;
	private int NumeroNivel;
	private File ArchivodeMapa;

	public Escenario crearEscenario() throws BaseMapNotFoundException,
			InvalidMapFormatException {
		if (escenario == null)
			try {
				escenario = new Escenario();
			} catch (FileNotFoundException e) {
				throw new BaseMapNotFoundException();
			}
		return escenario;
	}

	private Escenario() throws FileNotFoundException, InvalidMapFormatException {
		this.NumeroNivel = 1;
		try {
			ArchivodeMapa = new File("/ArchivosMapas/Mapa" + NumeroNivel
					+ ".mp");
			this.CargarMapa(); // Este método tira una excepción si el formato
			// del archivo está mal
		} catch (NullPointerException e) {
			throw new FileNotFoundException();
		}

	}

	private void CargarValorenPosiciondeMatriz(int fila, int columna,
			boolean caminable) throws InvalidAttributeValueException {
		if (Mapa[fila][columna] == null) {
			Mapa[fila][columna] = new Posicion(columna, fila, caminable);
		} else {
			Mapa[fila][columna].setCaminable(caminable);
			Mapa[fila][columna].setCoordX(columna);
			Mapa[fila][columna].setCoordY(fila);
		}

	}

	private void CargarMapa() throws InvalidMapFormatException {
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

			for (; (dis.available() != 0) && (i != MapRows); i++) {
				for (; (dis.available() != 0) && (j != MapColumns); j++) {

					auxiliardeLecturadeChars = dis.readChar();

					switch (auxiliardeLecturadeChars) {
					case '-':
						CargarValorenPosiciondeMatriz(i, j, false);
						break;
					case 'c':
						CargarValorenPosiciondeMatriz(i, j, true);
						break;
					case 'S':
						CargarValorenPosiciondeMatriz(i, j, true);
						if (Salida == null)
							Salida = new Posicion(Mapa[i][j]);
						else
							this.setSalida(Mapa[i][j]);
						break;
					case 'E':
						CargarValorenPosiciondeMatriz(i, j, true);
						if (Entrada == null)
							Entrada = new Posicion(Mapa[i][j]);
						else
							this.setEntrada(Mapa[i][j]);
						break;

					}
				}
			}

			if ((i != MapRows) || (j != MapColumns))
				throw new InvalidMapFormatException();

			this.setDineroBase(dis.readDouble());
			this.setCantBichos(dis.readInt());
			this.setCantidadDeVidaBase(dis.readInt());
			
			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace(); // TODO Cambiar esto
		} catch (IOException e) {
			e.printStackTrace(); // TODO Cambiar esto
		} catch (InvalidAttributeValueException e) {
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

	public void setCaminoAlaSalida(LinkedList caminoAlaSalida) {
		this.CaminoAlaSalida = caminoAlaSalida;
	}

	public LinkedList getCaminoAlaSalida() {
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

	private void setDineroBase(double dineroBase) {
		this.DineroBase = dineroBase;
		//TODO verificar que no se modifique el archivo de mapa y sacar mucho dinero inicial
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

}
