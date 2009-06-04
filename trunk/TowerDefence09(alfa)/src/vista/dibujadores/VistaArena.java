package vista.dibujadores;

/**
 * Esta clase guarda la ubicacion de la imagen de la arena
 * @author exus
 *
 */
public class VistaArena extends VistaObjetoDeMapa {

	public VistaArena(int x, int y) {
		super(x, y);
		this.setDir_imagen("Imagenes/Obstaculos/arena.jpg");
	}

}
