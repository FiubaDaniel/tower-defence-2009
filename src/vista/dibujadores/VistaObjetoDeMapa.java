package vista.dibujadores;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observable;

import javax.swing.ImageIcon;

import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import vista.menu.Mapa;

public abstract class VistaObjetoDeMapa implements Dibujable {

	private String dir_imagen;
	
	private int X_pos, Y_pos;
	
	private Posicionable posicionable;
	
	protected Image_Observer Observador;
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = ((Mapa)superfice).getGraphics();
		
		ImageIcon aux = new ImageIcon(this.getDir_imagen());

		int ANCHOUNITARIO = ((Mapa)superfice).getUNIDADANCHO();
		int ALTOUNITARIO = ((Mapa)superfice).getUNIDADANCHO();
		
		grafico.drawImage(aux.getImage(), this.getX() * ANCHOUNITARIO,this.getY() * ALTOUNITARIO, Observador);

	}
	
	public VistaObjetoDeMapa(int x, int y) {
		Observador = new Image_Observer();
		
		this.setX(x);
		this.setY(y);
	}

	public Posicionable getPosicionable() {
		return posicionable;
	}

	public void setPosicionable(Posicionable posicionable) {
		this.posicionable = posicionable;
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
	
	public class Image_Observer implements ImageObserver{

		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		public boolean imageUpdate(Image img, int infoflags, int x, int y,
				int width, int height) {
			// TODO Auto-generated method stub
			return false;
		}
		
	};

}
