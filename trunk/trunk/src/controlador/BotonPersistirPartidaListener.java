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
import modelo.Jugador;

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
                /*System.out.println("Elegiste el file: " + fc.getSelectedFile().getAbsoluteFile() +
                        fc.getSelectedFile().getName());*/
            	
            	 try {
					this.recuperar(fc.getSelectedFile().getName());
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
           }
        }
        if (e.getSource() == guardar) {
        	if (!simulacion.isPausado()){
           		simulacion.pausarJuego();
           		vista.getPanelDatos().cambiarEtiquetaIniciar_Pausar();
           	}
        	int returnVal = fc.showSaveDialog(vista);
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		System.out.print(returnVal);
        		//Llamadas para guardar un estado del juego.   
        		/*System.out.println("Elegiste el file: " + fc.getSelectedFile().getAbsoluteFile() +
                        fc.getSelectedFile().getName());*/
        		File unFile=fc.getSelectedFile().getAbsoluteFile();
           	    try {
					this.guardar(fc.getSelectedFile().getName());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	} 
        }
    }
 
    public static void guardar(String archivo) throws IOException{
		Escenario escenario = Escenario.obtenerEscenario();
		Jugador jugador = Jugador.obtenerJugador();
		FabricaDeEnemigos fabrica = FabricaDeEnemigos.obtenerFabricaEnemigos(escenario.getCantBichos(),escenario.getNumeroNivel());
		PrintStream archDeTexto= new PrintStream(archivo);

		Element raiz=new Element("Juego");
		raiz.addContent(escenario.persistir());
		raiz.addContent(jugador.persistir());
		raiz.addContent(fabrica.persistir());
		Document doc = new Document(raiz);   
		try {
  	          XMLOutputter serializer = new XMLOutputter();
		      serializer.output(doc, archDeTexto);
		    }
		  catch (IOException e) {
		      
		  }
    }
 
	
	public static void recuperar(String archivo) throws IOException{	 
		FileInputStream archRecu;
		Escenario escenario=Escenario.obtenerEscenario();
		Jugador jugador= Jugador.obtenerJugador();
		FabricaDeEnemigos fabrica=FabricaDeEnemigos.obtenerFabricaEnemigos(escenario.getCantBichos(),escenario.getNumeroNivel());
		escenario.reIniciar();
				try {
					archRecu = new FileInputStream(archivo);
					SAXBuilder parser = new SAXBuilder();
					Document doc = parser.build(archivo);
					Element raiz=doc.getRootElement();
					escenario=escenario.recuperar(raiz.getChild("Escenario"));
                    jugador = jugador.recuperar(raiz.getChild("Jugador"));
                    fabrica = fabrica.recuperar(raiz.getChild("FabricaDeEnemigos"));

				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				
	    controlador.GameLoop nuevoJuego = new controlador.GameLoop();
	    nuevoJuego.Jugar();
	
	}
 	
}
    
    
    



