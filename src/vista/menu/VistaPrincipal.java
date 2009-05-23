package vista.menu;


/**
 * 
 * @author exus
 */
public class VistaPrincipal extends javax.swing.JFrame {
	/** Creates new form VistaPrincipal */

	private static final long serialVersionUID = 3L;

	private static VistaPrincipal frame;

	private MenuSuperior MenuArchivoAyuda;
	
	
	
	private PanelDeDatos PanelDatos;
	
	private Mapa PanelMapa;
	
	
	
	public static VistaPrincipal obtenerVistaPrincipal() {
		if (frame == null) {
			frame = new VistaPrincipal();
		}
		return frame;
	}

	private VistaPrincipal() {
		initComponents();
	}

	private void initComponents() {

		PanelMapa = new Mapa();
		PanelDatos = new PanelDeDatos();
		MenuArchivoAyuda = new MenuSuperior();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Tower Defense");

		setJMenuBar(MenuArchivoAyuda);
		MenuArchivoAyuda.getAccessibleContext().setAccessibleName("BarraMenuInicial");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
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
	}

}
