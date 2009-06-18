package vista.menu;

import java.awt.Dialog;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Escenario;
import modelo.Jugador;
import controlador.*;
public class GameOver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892429956405115420L;

	public GameOver(VistaPrincipal vista, boolean gane){
		vista.obtenerVistaPrincipal();
		String valor;
		if (gane) {
			valor="Game Over";
		} else {
			valor="Ganaste";
		}
	    Object[] options = {"Si",
                "No"};
	    int n = JOptionPane.showOptionDialog(vista,
	    		"Desea jugar otra partida (Si/No) ",
	    		valor,
	    		JOptionPane.YES_NO_CANCEL_OPTION,
	    		JOptionPane.QUESTION_MESSAGE,
	    		null,
	    		options,
	    		options[1]);
	    if (n==1) {
	    	System.exit(0);
	    } else {
	    	
	    	Escenario nuevoEscenario = Escenario.obtenerEscenario();
	  		nuevoEscenario.reIniciar();
	  		try {
				nuevoEscenario.setNumeroNivel(1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		ControlSimulacion simul = ControlSimulacion.obtenerControl();
	  		simul.setTerminado();
	  		Jugador jugador = Jugador.obtenerJugador();
	  		jugador.matarJugador();
	  		vista.getPanelDatos().cambiarEtiquetaIniciar_Pausar();
	    	controlador.GameLoop nuevoJuego = new controlador.GameLoop();
	    	nuevoJuego.Jugar();
	    }
	    
	}
	
}
