package vista.menu;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Este panel muestra la informacion de la torre mostrada en la lista de compras.

 *
 */
public class PanelDatosDeTorres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6584730303598103574L;

	private javax.swing.JLabel LabelDanio;
	private javax.swing.JLabel LabelPrecio;
	private javax.swing.JLabel LabelRango;
	private javax.swing.JLabel LinkDanioTorre;
	private javax.swing.JLabel LinkPrecioTorre;
	private javax.swing.JLabel LinkRangoTorre;
	private javax.swing.JButton BotonComprar;

	public javax.swing.JButton getBotonComprar() {
		return BotonComprar;
	}

	public PanelDatosDeTorres() {
		setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		LabelDanio = new javax.swing.JLabel();
		LabelRango = new javax.swing.JLabel();
		LabelPrecio = new javax.swing.JLabel();
		LinkDanioTorre = new javax.swing.JLabel();
		LinkRangoTorre = new javax.swing.JLabel();
		LinkPrecioTorre = new javax.swing.JLabel();
		BotonComprar = new javax.swing.JButton();
		

		LabelDanio.setText("Daño:");

		LabelRango.setText("Rango:");

		LabelPrecio.setText("Precio:");

		LinkDanioTorre.setText("DañoAqui");

		LinkRangoTorre.setText("RangoAqui");

		LinkPrecioTorre.setText("PrecioAqui");

		BotonComprar.setText("Comprar");

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		LabelDanio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelRango.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelPrecio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		

		add(LabelDanio);
		add(LinkDanioTorre);
		add(LabelRango);
		add(LinkRangoTorre);
		add(LabelPrecio);
		add(LinkPrecioTorre);
		add(BotonComprar);
		
		
	}

	public void setLinkLabelDanioText(String str) {
		LinkDanioTorre.setText(str);
	}

	public void setLinkLabelRangoText(String str) {
		LinkRangoTorre.setText(str);
	}

	public void setLinkLabelPrecioText(String str) {
		LinkPrecioTorre.setText(str);
	}

}
