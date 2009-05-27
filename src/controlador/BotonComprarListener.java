package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.menu.VistaPrincipal;

public class BotonComprarListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		BotonComprar_Apretado(arg0);

	}

	public void BotonComprar_Apretado(ActionEvent evt) {
		VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal(null, null, null);
		
		boolean aux = !vistaP.getMapa().isInsetar_objeto();
		vistaP.getMapa().setInsetar_objeto(aux);
		
	}
}
