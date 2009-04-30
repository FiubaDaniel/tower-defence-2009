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

public class Escenario {

	private static Escenario escenario;
	private int CantBichos;
	private LinkedList CaminoAlaSalida;
	private Posicion Salida;
	private Posicion[][] Mapa;
	private int NumeroNivel;
	private File ArchivodeMapa;

	public Escenario crearEscenario() throws BaseMapNotFoundException {
		if (escenario == null)
			try {
				escenario = new Escenario();
			} catch (FileNotFoundException e) {
				throw new BaseMapNotFoundException();
			}
		return escenario;
	}

	private Escenario() throws FileNotFoundException {
		this.NumeroNivel = 1;
		try {
			ArchivodeMapa = new File("/ArchivosMapas/Mapa" + NumeroNivel
					+ ".mp");
			this.CargarMapa();
		} catch (NullPointerException e) {
			throw new FileNotFoundException();
		}

	}

	private void CargarMapa() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(ArchivodeMapa);

			// utilizo un buffer de datos para agilizar la lectura del archivo
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			for (int i = 0, j = 0;;) {
				while (dis.available() != 0) {

				}

				// dispose all the resources after using them.
				fis.close();
				bis.close();
				dis.close();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

	public void setSalida(Posicion salida) {
		this.Salida = salida;
	}

	public Posicion getSalida() {
		return Salida;
	}

	public void setNumeroNivel(int numeroNivel) {
		this.NumeroNivel = numeroNivel;
	}

	public int getNumeroNivel() {
		return NumeroNivel;
	}

}
