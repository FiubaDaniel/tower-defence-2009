package vista.menu;

import java.awt.Dialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameOver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892429956405115420L;

	public GameOver(VistaPrincipal vista){
		vista.obtenerVistaPrincipal();
	    Object[] options = {"Si",
                "No"};
	    int n = JOptionPane.showOptionDialog(vista,
	    		"Desea jugar otra partida (Si/No) ",
	    		"Game Over",
	    		JOptionPane.YES_NO_CANCEL_OPTION,
	    		JOptionPane.QUESTION_MESSAGE,
	    		null,
	    		options,
	    		options[1]);
	    if (n==1) {
	    	System.exit(0);
	    } else {
	    	controlador.GameLoop nuevoJuego = new controlador.GameLoop();
	    	nuevoJuego.Jugar();
	    }
	    
	}
	
}
