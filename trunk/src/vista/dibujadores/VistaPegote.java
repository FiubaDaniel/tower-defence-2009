package vista.dibujadores;

/**
 * Esta clase guarda la ubicacion de la imagen del Pegote

 *
 */
public class VistaPegote extends VistaObjetoDeMapa {

	public VistaPegote(int x, int y) {
		super(x, y);
		this.setDir_imagen("Imagenes/Obstaculos/pegote.jpg");
	}

}
