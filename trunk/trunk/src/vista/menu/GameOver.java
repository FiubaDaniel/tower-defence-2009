package vista.menu;

import java.awt.Dialog;

import javax.swing.JFrame;

public class GameOver extends JFrame {

	public GameOver(){
		Dialog miDialogo = new Dialog( this,"Perdiste!!!" );
	    miDialogo.setSize(250,250);
	    // Hace que el dialogo aparezca en la pantalla
	    miDialogo.show();
	}
	
}
