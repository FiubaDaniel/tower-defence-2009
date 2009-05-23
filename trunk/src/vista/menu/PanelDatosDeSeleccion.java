package vista.menu;

import javax.swing.JPanel;

import controlador.BotonUpdate;

public class PanelDatosDeSeleccion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3561771492386756083L;

	private BotonUpdate ButtonUpdate;
	private javax.swing.JPanel ImagenSeleccion;
	private javax.swing.JLabel LabelNombreSeleccion;
	private javax.swing.JLabel LabelRango_Vel;
	private javax.swing.JLabel LabelVida_danio;
	private javax.swing.JLabel LabelUpdate;
	private javax.swing.JLabel LinkRango_Vel;
	private javax.swing.JLabel LinkVida_danio;

	public PanelDatosDeSeleccion() {
		ImagenSeleccion = new javax.swing.JPanel();
		LabelNombreSeleccion = new javax.swing.JLabel();
		LabelVida_danio = new javax.swing.JLabel();
		LabelRango_Vel = new javax.swing.JLabel();
		LabelUpdate = new javax.swing.JLabel();
		LinkVida_danio = new javax.swing.JLabel();
		LinkRango_Vel = new javax.swing.JLabel();
		ButtonUpdate = new BotonUpdate();
		

		setBorder(javax.swing.BorderFactory
				.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));

		ImagenSeleccion.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		javax.swing.GroupLayout ImagenSeleccionLayout = new javax.swing.GroupLayout(
				ImagenSeleccion);
		ImagenSeleccion.setLayout(ImagenSeleccionLayout);
		ImagenSeleccionLayout.setHorizontalGroup(ImagenSeleccionLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 63, Short.MAX_VALUE));
		ImagenSeleccionLayout.setVerticalGroup(ImagenSeleccionLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 62, Short.MAX_VALUE));

		LabelNombreSeleccion.setText("Obj_Seleccionado");

		LabelVida_danio.setText("Vida:");

		LabelRango_Vel.setText("Rango:");

		LabelUpdate.setText("Actualizacion:");

		LinkVida_danio.setText("Vida_danio");

		LinkRango_Vel.setText("Rango_Vel");

		

		javax.swing.GroupLayout PanelDatosSeleccionLayout = new javax.swing.GroupLayout(
				this);
		setLayout(PanelDatosSeleccionLayout);
		PanelDatosSeleccionLayout.setHorizontalGroup(PanelDatosSeleccionLayout.createParallelGroup(
			javax.swing.GroupLayout.Alignment.LEADING).addGroup(
			PanelDatosSeleccionLayout.createSequentialGroup().addContainerGap()
			.addGroup(PanelDatosSeleccionLayout.createParallelGroup(
			javax.swing.GroupLayout.Alignment.LEADING).addComponent(LabelNombreSeleccion,
			javax.swing.GroupLayout.PREFERRED_SIZE,118,javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGroup(PanelDatosSeleccionLayout.createSequentialGroup().addGap(6, 6, 6)
			.addComponent(ImagenSeleccion,javax.swing.GroupLayout.PREFERRED_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(PanelDatosSeleccionLayout.createParallelGroup(
			javax.swing.GroupLayout.Alignment.LEADING).addGroup(PanelDatosSeleccionLayout
			.createSequentialGroup().addGroup(PanelDatosSeleccionLayout.createParallelGroup(
			javax.swing.GroupLayout.Alignment.LEADING).addComponent(LabelVida_danio)
			.addComponent(LabelRango_Vel)).addGap(29, 29, 29).addGroup(PanelDatosSeleccionLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(
			LinkRango_Vel).addComponent(LinkVida_danio)).addGap(35, 35, 35)).addGroup(
			PanelDatosSeleccionLayout.createSequentialGroup().addComponent(LabelUpdate)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(ButtonUpdate))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
			.addContainerGap(26, Short.MAX_VALUE)));
		
		PanelDatosSeleccionLayout.setVerticalGroup(PanelDatosSeleccionLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
			PanelDatosSeleccionLayout.createSequentialGroup().addContainerGap()
			.addComponent(LabelNombreSeleccion).addGroup(PanelDatosSeleccionLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
			javax.swing.GroupLayout.Alignment.TRAILING,PanelDatosSeleccionLayout
			.createSequentialGroup().addGap(6, 6, 6).addGroup(PanelDatosSeleccionLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
			LabelVida_danio).addComponent(LinkVida_danio)).addPreferredGap(
			javax.swing.LayoutStyle.ComponentPlacement.RELATED,javax.swing.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE).addGroup(PanelDatosSeleccionLayout.createParallelGroup(
			javax.swing.GroupLayout.Alignment.BASELINE).addComponent(LabelRango_Vel)
			.addComponent(LinkRango_Vel)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(PanelDatosSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addComponent(LabelUpdate).addComponent(ButtonUpdate)).addGap(38, 38, 38))
			.addGroup(PanelDatosSeleccionLayout.createSequentialGroup().addGap(18, 18, 18)
			.addComponent(ImagenSeleccion,javax.swing.GroupLayout.PREFERRED_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.PREFERRED_SIZE)))
			.addContainerGap()));

	}

}
