package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import modelo.Escenario;

import vista.menu.VistaPrincipal;

/**
 * Clase encargada del funcionamiento del Botón Guardar

 */
public class BotonPersistirPartidaListener implements ActionListener {

	
    public void actionPerformed(ActionEvent e) {
		   	
        JFileChooser fc = new JFileChooser();
        ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
        VistaPrincipal vista = VistaPrincipal.obtenerVistaPrincipal();
        JButton guardar = vista.getPanelDatos().getBotonGuardar();
        JButton cargar = vista.getPanelDatos().getBotonCargar();
                
        if (e.getSource() == cargar) {
        	if (!simulacion.isPausado()){
        		simulacion.pausarJuego();
        		vista.getPanelDatos().cambiarEtiquetaIniciar_Pausar();
        	}
        	int returnVal = fc.showOpenDialog(vista);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	//Llamadas para cargar un estado del juego.
           }
        }
        if (e.getSource() == guardar) {
        	if (!simulacion.isPausado()){
           		simulacion.pausarJuego();
           		vista.getPanelDatos().cambiarEtiquetaIniciar_Pausar();
           	}
        	int returnVal = fc.showSaveDialog(vista);
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		//Llamadas para guardar un estado del juego.       		
        	} 
        }
    }
}