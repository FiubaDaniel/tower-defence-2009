package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;

/*
 * Esta clase representa una imagen JPG abstrayendo al usuario de los detalles de Java2D
 * Simplemente requiere de una referencia al nombre del archivo JPG
 */
public class Imagen implements Dibujable{
	
	public Imagen(){
		
	}

	public void dibujar(SuperficieDeDibujo superficeDeDibujo) {
		Graphics grafico = ((Ventana)superficeDeDibujo).getGrafico();
		grafico.drawImage(this.imagen, this.posicionable.getX(), this.posicionable.getY(), null);
	}
	    
	public String getNombreArchivoImagen() {
		return nombreArchivoImagen;
	}

	public void setNombreArchivoImagen(String nombreArchivoImagen) {
		this.nombreArchivoImagen = nombreArchivoImagen;
		InputStream in = getClass().getResourceAsStream(this.nombreArchivoImagen);
		JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
		try{
			this.imagen = decoder.decodeAsBufferedImage();
			in.close();
		}catch(Exception ex){

		}			
	}
	
	public Posicionable getPosicionable() {
		return posicionable;
	}

	public void setPosicionable(Posicionable posicionable) {
		this.posicionable = posicionable;
	}
	
	private String nombreArchivoImagen;
    private BufferedImage imagen;
    private Posicionable posicionable;

}
