package vista.menu;

import javax.swing.JPanel;

public class PanelDatosDeTorres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6584730303598103574L;

	private javax.swing.JLabel LabelDaño;
	private javax.swing.JLabel LabelPrecio;
	private javax.swing.JLabel LabelRango;
	private javax.swing.JLabel LinkDañoTorre;
	private javax.swing.JLabel LinkPrecioTorre;
	private javax.swing.JLabel LinkRangoTorre;
	private javax.swing.JButton BotonComprar;

	public PanelDatosDeTorres() {
		setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		LabelDaño = new javax.swing.JLabel();
		LabelRango = new javax.swing.JLabel();
		LabelPrecio = new javax.swing.JLabel();
		LinkDañoTorre = new javax.swing.JLabel();
		LinkRangoTorre = new javax.swing.JLabel();
		LinkPrecioTorre = new javax.swing.JLabel();
		BotonComprar = new javax.swing.JButton();

		LabelDaño.setText("Daño:");

		LabelRango.setText("Rango:");

		LabelPrecio.setText("Precio:");

		LinkDañoTorre.setText("DañoAqui");

		LinkRangoTorre.setText("RangoAqui");

		LinkPrecioTorre.setText("PrecioAqui");

		BotonComprar.setText("Comprar");

		javax.swing.GroupLayout PanelDatosTorresLayout = new javax.swing.GroupLayout(
				this);
		setLayout(PanelDatosTorresLayout);
		
		//Creo las relaciones paralelas entre los componentes
		
		PanelDatosTorresLayout
				.setHorizontalGroup(PanelDatosTorresLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanelDatosTorresLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanelDatosTorresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																PanelDatosTorresLayout
																		.createSequentialGroup()
																		.addComponent(
																				LabelDaño)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				LinkDañoTorre))
														.addGroup(
																PanelDatosTorresLayout
																		.createSequentialGroup()
																		.addGroup(
																				PanelDatosTorresLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								LabelRango)
																						.addComponent(
																								LabelPrecio))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanelDatosTorresLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								LinkRangoTorre)
																						.addComponent(
																								LinkPrecioTorre))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanelDatosTorresLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								BotonComprar))))
										.addContainerGap()));
		
		//Creo las relaciones verticales entre los componentes
		
		PanelDatosTorresLayout
				.setVerticalGroup(PanelDatosTorresLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanelDatosTorresLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanelDatosTorresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(LabelDaño)
														.addComponent(
																LinkDañoTorre))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosTorresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																LabelRango)
														.addComponent(
																LinkRangoTorre))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosTorresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																LabelPrecio)
														.addComponent(
																LinkPrecioTorre))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosTorresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																BotonComprar))
										.addContainerGap()));
	}

	public void setLinkLabelDañoText(String str) {
		LinkDañoTorre.setText(str);
	}

	public void setLinkLabelRangoText(String str) {
		LinkRangoTorre.setText(str);
	}

	public void setLinkLabelPrecioText(String str) {
		LinkPrecioTorre.setText(str);
	}

}
