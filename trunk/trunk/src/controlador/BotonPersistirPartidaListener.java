package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import modelo.Escenario;
import modelo.FabricaDeEnemigos;
import modelo.Jugador;

import vista.menu.VistaPrincipal;

/**
 * Clase encargada del funcionamiento del Botón Guardar

 */
public class BotonPersistirPartidaListener implements ActionListener {

	
    public void actionPerformed(ActionEvent e) {
		   	
        JFileChooser fc = new JFileChooser();
        FileFilter filter1 = new ExtensionFileFilter("XML", new String[] { "XML" });
        fc.setFileFilter(filter1);
        
        ControlSimulacion simulacion = ControlSimulacion.obtenerControl();
        VistaPrincipal vista = VistaPrincipal.obtenerVistaPrincipal();
        JButton guardar = vista.getPanelDatos().getBotonGuardar();
        JButton cargar = vista.getPanelDatos().getBotonCargar();
        
        if (e.getSource() == cargar) {
        	if (!simulacion.isPausado()){
        		simulacion.pausarSimulacion();
        		vista.getPanelDatos().cambiarEtiquetaIniciar_Pausar();
        	}
        	int returnVal = fc.showOpenDialog(vista);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	//Llamadas para cargar un estado del juego.
            	 try {
					this.recuperar(fc.getSelectedFile().getAbsoluteFile());
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
           }
        }
        
        if (e.getSource() == guardar) {
        	if (!simulacion.isPausado()){
           		simulacion.pausarSimulacion();
           		vista.getPanelDatos().cambiarEtiquetaIniciar_Pausar();
           	}
        	int returnVal = fc.showSaveDialog(vista);
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		//Llamadas para guardar un estado del juego.
        		File unFile=fc.getSelectedFile().getAbsoluteFile();
           	    try {
           	    	this.guardar(fc.getSelectedFile().getAbsoluteFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	} 
        }
    }
 
    public static void guardar(File archivo) throws IOException{
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
		catch (IOException e) {}
    }
 
	
	public static void recuperar(File archivo) throws IOException{	 
		FileInputStream archRecu;
		Escenario escenario=Escenario.obtenerEscenario();
		Jugador jugador= Jugador.obtenerJugador();
		FabricaDeEnemigos fabrica=FabricaDeEnemigos.obtenerFabricaEnemigos(escenario.getCantBichos(),escenario.getNumeroNivel());
		try {
			archRecu = new FileInputStream(archivo);
			SAXBuilder parser = new SAXBuilder();
			Document doc = parser.build(archivo);
			Element raiz=doc.getRootElement();
			escenario.recuperar(raiz.getChild("Escenario"));
            jugador.recuperarJugador(raiz.getChild("Jugador"));
            fabrica = fabrica.recuperar(raiz.getChild("FabricaDeEnemigos"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ControlVista vista = ControlVista.obtenerControl();
		vista.actualizarVista();
	}
	
	class ExtensionFileFilter extends FileFilter {
		  String description;

		  String extensions[];

		  public ExtensionFileFilter(String description, String extension) {
		    this(description, new String[] { extension });
		  }

		  public ExtensionFileFilter(String description, String extensions[]) {
		    if (description == null) {
		      this.description = extensions[0];
		    } else {
		      this.description = description;
		    }
		    this.extensions = (String[]) extensions.clone();
		    toLower(this.extensions);
		  }

		  private void toLower(String array[]) {
		    for (int i = 0, n = array.length; i < n; i++) {
		      array[i] = array[i].toLowerCase();
		    }
		  }

		  public String getDescription() {
		    return description;
		  }

		  public boolean accept(File file) {
		    if (file.isDirectory())
		      return true;
		    else{
		      String path = file.getAbsolutePath().toLowerCase();
		      for (int i = 0, n = extensions.length; i < n; i++) {
		        String extension = extensions[i];
		        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
		          return true;
		        }
		      }
		    }
		    return false;
		  }
		}
}
    
    
    



