package vista.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Jugador;

import controlador.ControlSimulacion;

public class GameOver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892429956405115420L;

	public GameOver(VistaPrincipal vista){
		String valor;
		Jugador jugador = Jugador.obtenerJugador();
		if (jugador.getCantidadVidas() == 0) {
			valor="Game Over";
		} else {
			valor="Ganaste";
		}
	    Object[] options = {"Si","No"};
	    int n = JOptionPane.showOptionDialog(vista,
	    		"Desea jugar otra partida (Si/No) ",
	    		valor,
	    		JOptionPane.YES_NO_CANCEL_OPTION,
	    		JOptionPane.QUESTION_MESSAGE,
	    		null,
	    		options,
	    		options[1]);
	   if (n==1)
	    	System.exit(0);
	   else {
	    	ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
	    	simulacion.reiniciarSimulacion();
	    }
	    
	}
	
}
