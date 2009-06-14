package vista.dibujadores;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observable;

import javax.swing.ImageIcon;

import vista.menu.Mapa;

/**
 * Esta clase contiene todos los datos en comun de las vista de obstaculos y
 * Enemigos.
 * 

 */
public abstract class VistaObjetoDeMapa {

	private String dir_imagen;

	private int X_pos, Y_pos;

	protected Image_Observer Observador;

	/**
	 * Este metodo dibuja la imagen en la superficie de DIbujo.
	 */
	public void dibujar(Mapa superfice) {

		// Pido una superficie donde dibujar, es decir un graphics.
		Graphics grafico = superfice.getGraphics();

		// Cargo una imagen, para ser dibujada.
		ImageIcon aux = new ImageIcon(this.getDir_imagen());

		// Creación buffer
		Image buffer = superfice.createImage(superfice.getUNIDADANCHO(), superfice.getUNIDADALTO());
		Graphics graph_buffer = buffer.getGraphics();
		// Creación buffer

		// Obtengo el ancho y alto de las iamgenes a crear.
		int ANCHOUNITARIO = superfice.getUNIDADANCHO();
		int ALTOUNITARIO = superfice.getUNIDADANCHO();

		// Dibujo la iamgen en el buffer y luego la copio al mapa, para evitar
		// del destello de la imagen
		graph_buffer.drawImage(aux.getImage(), 0, 0, Observador);
		grafico.drawImage(buffer, this.getX() * ANCHOUNITARIO, this.getY()
				* ALTOUNITARIO, Observador);

	}

	/**
	 * Contructor generico. Recibe las coordenadas en donde poner la imagen.
	 * 
	 * @param x
	 * @param y
	 */
	protected VistaObjetoDeMapa(int x, int y) {
		Observador = new Image_Observer();

		this.setX(x);
		this.setY(y);
	}

	public void setDir_imagen(String dir_imagen) {
		this.dir_imagen = dir_imagen;
	}

	public String getDir_imagen() {
		return dir_imagen;
	}

	public void setX(int x) {
		this.X_pos = x;
	}

	public int getX() {
		return X_pos;
	}

	public void setY(int y) {
		this.Y_pos = y;
	}

	public int getY() {
		return Y_pos;
	}

	public void setObservador(Image_Observer observador) {
		Observador = observador;
	}

	public Image_Observer getObservador() {
		return Observador;
	}

	// Este observador esta para que el compilador no me moleste. Me lo pedia el
	// metodo para dibujar, pero no lo necesitaba, asi que cree uno que no hace
	// nada.
	public class Image_Observer implements ImageObserver {

		public void update(Observable arg0, Object arg1) {

		}

		public boolean imageUpdate(Image img, int infoflags, int x, int y,
				int width, int height) {
			return false;
		}

	};

}
