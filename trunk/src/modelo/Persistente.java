package modelo;

import org.jdom.Element;

/**
 * La interfaz Persistente sirve para tratar a todos aquellos
 * objetos que conservan su estado en un medio de 
 * almacenamiento permanente,pudiendo ser reconstruidos posteriormente.
 * 
 * @author hector
 */
public interface Persistente {
	
	/**
	 * Serializacion
	 */
	public Element persistir();

}
