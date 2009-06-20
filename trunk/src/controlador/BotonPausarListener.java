package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BotonPausarListener implements ActionListener{

	/**
	 * Este metodo es llamado si el boton de Pausar-Iniciar es activado.
	 */
	public void actionPerformed(ActionEvent e) {
		ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
		JButton botonPausado = (JButton) (e.getSource());
		if (simulacion.isPausado()){
			botonPausado.setText("Iniciar");
			simulacion.despausarSimulacion();
		}else{
			botonPausado.setText("Pausa");
			simulacion.pausarSimulacion();
		}
	}

	
}
