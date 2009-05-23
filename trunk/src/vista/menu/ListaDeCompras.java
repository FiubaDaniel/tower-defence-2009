package vista.menu;

import javax.swing.AbstractListModel;

public class ListaDeCompras extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3114730816784248789L;
	
	String[] strings = { "Torre Blanca", "Torre Plateada",
			"Torre Dorada", "Torre Azul" };

	public Object getElementAt(int arg0) {
		return strings[arg0];
	}

	public int getSize() {
		return strings.length;
	}
	
	

}
