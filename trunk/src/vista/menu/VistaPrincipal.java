package vista.menu;

import java.awt.Dialog;

import javax.swing.JFrame;

import modelo.Jugador;

import controlador.BotonComprarListener;

/**
 * Esta clase es el Frame principal, en el cual serán ubicados todo los
 * componentes de la vista. Implementa la patron Singleton
 * 

 */
public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 3L;

	private static VistaPrincipal frame;
	//private MenuSuperior MenuArchivoAyuda;
	private PanelDeDatos PanelDatos;
	private Mapa PanelMapa;

	/**
	 * Metodo para obtener la unica instancia de la clase. Una vez creada por
	 * primera vez, se puede llamar a este metodo con argumentos null
	 * 
	 * @param menuArchivoAyuda
	 * @param paneldedatos
	 * @param mapa
	 * @return
	 */
	public static VistaPrincipal obtenerVistaPrincipal() {
		if (frame == null) 
			frame = new VistaPrincipal();
		return frame;
	}

	private VistaPrincipal() {
		// Instancio los objetos de la ventana
		PanelDatosDeTorres PanelTorres = new PanelDatosDeTorres();
		PanelDatosDeSeleccion PanelSeleccion = new PanelDatosDeSeleccion();
		//MenuSuperior menuArchivoAyuda = new MenuSuperior();
		PanelDeDatos panelDedatos = new PanelDeDatos(PanelSeleccion, PanelTorres);
		Mapa PanelMapa = new Mapa();
		
		//Agrego un Observador de jugador
		Jugador jugador = Jugador.obtenerJugador();
		jugador.addObserver(panelDedatos);
		initComponents(/*menuArchivoAyuda,*/ panelDedatos, PanelMapa);

	}

	private void initComponents(/*MenuSuperior menuArchivoAyuda,*/
			PanelDeDatos paneldedatos, Mapa mapa) {

		PanelMapa = mapa;
		PanelDatos = paneldedatos;
		//MenuArchivoAyuda = menuArchivoAyuda;

		// Seteo para que al apretar el botón de cerra, se cierre la aplicacion
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Tower Defense");

		/* Cargo el menu superior.
		setJMenuBar(MenuArchivoAyuda);
		MenuArchivoAyuda.getAccessibleContext().setAccessibleName(
				"BarraMenuInicial");*/

		// Establezco un layout para los componentes incluidos en este Frame. EL
		// layout funciona como un "organizador" de componentes, es decir
		// establece alineacion, tamaño, etc.
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);

		// Establecezco un grupo horizontal para relacionar las alineaciones de
		// los componentes de la pantalla
		layout
				.setHorizontalGroup(layout
						// Agrega un un grupo de componentes que se encuentran
						// en la misma linea horizontal.
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										// Agrega componentes en la misma linea
										// vertical.
										.addContainerGap()
										// Agrega un espacio entre componentes
										.addComponent(
												// Agrego el componente con su
												// alineacion
												PanelMapa,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap( // Agrega un espacio
															// entre componentes
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												// Agrego el componente con su
												// alineacion
												PanelDatos,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												317, Short.MAX_VALUE)
										.addContainerGap()));

		// Establecezco un grupo vertical para relacionar las alineaciones de
		// los componentes de la pantalla. Se genera de forma igual al grupo
		// horizontal.
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(PanelDatos,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(
										PanelMapa,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)).addContainerGap()));

		pack(); // Junto los grupos verticales y horizontales.

		// Cargo un Listener del boton comprar, es decir uan clase que será
		// avisada cada vez que se haga click en el boton comprar.
		PanelDatos.getPanelDatosTorres().getBotonComprar().addActionListener(
				new BotonComprarListener());

	}

	public Mapa getMapa() {
		return PanelMapa;
	}

	public void setMapa(Mapa mapa) {
		PanelMapa = mapa;
	}

	/*public MenuSuperior getMenuArchivoAyuda() {
		return MenuArchivoAyuda;
	}

	public void setMenuArchivoAyuda(MenuSuperior menuArchivoAyuda) {
		MenuArchivoAyuda = menuArchivoAyuda;
	}*/

	public PanelDeDatos getPanelDatos() {
		return PanelDatos;
	}

	public void setPanelDatos(PanelDeDatos panelDatos) {
		PanelDatos = panelDatos;
	}

}
