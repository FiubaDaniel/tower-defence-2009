package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

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
    
    public static void guardar(String archivo) throws IOException{
		Escenario escenario = Escenario.obtenerEscenario();
		PrintStream archDeTexto= new PrintStream(archivo);
		Document doc = new Document(this.persistir());   
		try {
		      XMLOutputter serializer = new XMLOutputter();
		      serializer.output(doc, archDeTexto);
		    }
		    catch (IOException e) {
		      
		    }
    }
	
	public static Escenario recuperar(String archivo) throws IOException{	 
		FileInputStream archRecu;
				try {
					archRecu = new FileInputStream(archivo);
					SAXBuilder parser = new SAXBuilder();
					Document doc = parser.build(archivo);
					Element raiz=doc.getRootElement();
		               /*  */
		 			return recuperar(raiz);

				} catch (Exception e) {
					e.printStackTrace();
				}return null;
			}
	
	public static void persistir(){
		
	Escenario escenario= Escenario.obtenerEscenario();
	escenario.reIniciar();
	Element elementoXML= escenario.persistir();
	
		
	}
	
}