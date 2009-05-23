package vista;

import vista.menu.VistaPrincipal;


public class Main {

	/**
	 * 
	 * Dónde llamare a todo.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VistaPrincipal.obtenerVistaPrincipal().setVisible(true);
			}
		});
	}

}
