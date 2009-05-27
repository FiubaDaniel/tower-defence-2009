package vista.menu;

import javax.swing.JFrame;

import controlador.BotonComprarListener;

/**
 * 
 * @author exus
 */
public class VistaPrincipal extends JFrame {
	/** Creates new form VistaPrincipal */

	private static final long serialVersionUID = 3L;

	private static VistaPrincipal frame;
	private MenuSuperior MenuArchivoAyuda;
	private PanelDeDatos PanelDatos;
	private Mapa PanelMapa;

	public static VistaPrincipal obtenerVistaPrincipal(
			MenuSuperior menuArchivoAyuda, PanelDeDatos paneldedatos, Mapa mapa) {
		if (frame == null) {
			frame = new VistaPrincipal(menuArchivoAyuda, paneldedatos, mapa);
		}
		return frame;
	}

	private VistaPrincipal(MenuSuperior menuArchivoAyuda,
			PanelDeDatos paneldedatos, Mapa mapa) {
		initComponents(menuArchivoAyuda, paneldedatos, mapa);

	}

	private void initComponents(MenuSuperior menuArchivoAyuda,
			PanelDeDatos paneldedatos, Mapa mapa) {

		PanelMapa = mapa;
		PanelDatos = paneldedatos;
		MenuArchivoAyuda = menuArchivoAyuda;

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Tower Defense");

		setJMenuBar(MenuArchivoAyuda);
		MenuArchivoAyuda.getAccessibleContext().setAccessibleName(
				"BarraMenuInicial");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);

		// Establecezco un grupo horizontal para relacionar las alineaciones de
		// los componentes de la pantalla
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												PanelMapa,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												PanelDatos,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												317, Short.MAX_VALUE)
										.addContainerGap()));
		// Establecezco un grupo vertical para relacionar las alineaciones de
		// los componentes de la pantalla
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

		pack();
		
		PanelDatos.getPanelDatosTorres().getBotonComprar().addActionListener(new BotonComprarListener());
		
	}

	public Mapa getMapa() {
		return PanelMapa;
	}

	public void setMapa(Mapa mapa) {
		PanelMapa = mapa;
	}

	public MenuSuperior getMenuArchivoAyuda() {
		return MenuArchivoAyuda;
	}

	public void setMenuArchivoAyuda(MenuSuperior menuArchivoAyuda) {
		MenuArchivoAyuda = menuArchivoAyuda;
	}

	public PanelDeDatos getPanelDatos() {
		return PanelDatos;
	}

	public void setPanelDatos(PanelDeDatos panelDatos) {
		PanelDatos = panelDatos;
	}

}
