package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import vista.menu.VistaPrincipal;

/**
 * Clase encargada del funcionamiento del Botón Guardar

 */
public class BotonPersistirPartidaListener implements ActionListener {

	
    public void actionPerformed(ActionEvent e) {
		   	
        JFileChooser fc = new JFileChooser();

        VistaPrincipal vista = VistaPrincipal.obtenerVistaPrincipal();
        JButton guardar = vista.getPanelDatos().getBotonGuardar();
        JButton cargar = vista.getPanelDatos().getBotonCargar();
        //Handle open button action.
        if (e.getSource() == cargar) {
            int returnVal = fc.showOpenDialog(vista);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
            } else {
                //log.append("Open command cancelled by user." + newline);
            }
            //log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == guardar) {
            int returnVal = fc.showSaveDialog(vista);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                //log.append("Saving: " + file.getName() + "." + newline);
            } else {
                //log.append("Save command cancelled by user." + newline);
            }
            //log.setCaretPosition(log.getDocument().getLength());
        }

        

	}
	
}
