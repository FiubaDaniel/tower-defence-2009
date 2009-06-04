package vista.menu;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Este panel muestra la informacion de la torre mostrada en la lista de compras.
 * @author exus
 *
 */
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

	public javax.swing.JButton getBotonComprar() {
		return BotonComprar;
	}

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

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		LabelDaño.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelRango.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelPrecio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		

		add(LabelDaño);
		add(LinkDañoTorre);
		add(LabelRango);
		add(LinkRangoTorre);
		add(LabelPrecio);
		add(LinkPrecioTorre);
		add(BotonComprar);
		
		
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
