package vista.menu;

import java.awt.Dialog;

import javax.swing.JFrame;

public class GameOver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892429956405115420L;

	public GameOver(){
		Dialog miDialogo = new Dialog( this,"Perdiste!!!" );
	    miDialogo.setSize(250,250);
	    // Hace que el dialogo aparezca en la pantalla
	    miDialogo.setVisible(true);
	}
	
}
