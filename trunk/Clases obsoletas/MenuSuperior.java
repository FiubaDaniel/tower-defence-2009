package vista.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;



/**
 * Esta clase se encarga de la colocación y manejo del menu superior "Archivo"
 * 
 * 
 * @author Hector
 *
 */

public class MenuSuperior extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7172122643645385892L;

	
	private JMenu MenuArchivo;
	private BotonIniciarPartida MenuIniciar;
	private BotonCargarPartida MenuCargar;
	private BotonGuardarPartida MenuGuardar;
	private JSeparator MenuSeparador1;
	private BotonMenuOpciones MenuOpciones;
	private JSeparator MenuSeparador2;
	private BotonSalir MenuSalir;
	
	private JMenu MenuBarraAyuda;
	private BotonAyuda MenuAyuda;
	private JSeparator MenuSeparador3;
	private BotonAcercaDe MenuAcercaDe;
	
	public MenuSuperior() {
		
		setDoubleBuffered(true);
		setName("Menu_Inicial");

		configurarMenuArchivo();
		add(MenuArchivo);
		
		configurarMenuAyuda();
		add(MenuBarraAyuda);

	}
	
	private void configurarMenuArchivo() {
		
		MenuArchivo = new javax.swing.JMenu();
		MenuArchivo.setText("Archivo");
		MenuArchivo.setToolTipText("Opciones Normales");
		MenuArchivo.setDoubleBuffered(true);
		
		MenuIniciar = new BotonIniciarPartida();
		MenuArchivo.add(MenuIniciar);
		
		MenuCargar = new BotonCargarPartida();
		MenuArchivo.add(MenuCargar);
		
		MenuGuardar = new BotonGuardarPartida();
		MenuArchivo.add(MenuGuardar);
		
		MenuSeparador1 = new javax.swing.JSeparator();
		MenuArchivo.add(MenuSeparador1);
		
		MenuOpciones = new BotonMenuOpciones();
		MenuArchivo.add(MenuOpciones);
		
		MenuSeparador2 = new javax.swing.JSeparator();
		MenuArchivo.add(MenuSeparador2);

		MenuSalir = new BotonSalir();
		MenuArchivo.add(MenuSalir);

	}	
	
	private void configurarMenuAyuda() {
	
		MenuBarraAyuda = new javax.swing.JMenu();
		MenuBarraAyuda.setText("Ayuda");

		MenuAyuda = new BotonAyuda();
		MenuBarraAyuda.add(MenuAyuda);
		
		MenuSeparador3 = new javax.swing.JSeparator();
		MenuBarraAyuda.add(MenuSeparador3);
		
		MenuAcercaDe = new BotonAcercaDe();
		MenuBarraAyuda.add(MenuAcercaDe);
		
	}
		
	
}
