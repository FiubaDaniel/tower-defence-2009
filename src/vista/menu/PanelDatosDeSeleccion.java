package vista.menu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

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

		setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(255, 0, 0)));

		ImagenSeleccion.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		

		LabelNombreSeleccion.setText("Obj_Seleccionado");

		LabelVida_danio.setText("Vida:");

		LabelRango_Vel.setText("Rango:");

		LabelUpdate.setText("Actualizacion:");

		LinkVida_danio.setText("Vida_danio");

		LinkRango_Vel.setText("Rango_Vel");
		
		LabelNombreSeleccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		LabelVida_danio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelRango_Vel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelUpdate.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelNombreSeleccion);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(ImagenSeleccion);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelVida_danio);
		add(LinkVida_danio);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelRango_Vel);
		add(LinkRango_Vel);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelUpdate);
		add(ButtonUpdate);
		
	}

	public void setLabelVida_danio_Text(String s) {
		if (s != null)
			LinkVida_danio.setText(s);
	}

	public void setLabelRango_Vel_Text(String s) {
		if (s != null)
			LinkRango_Vel.setText(s);
	}

	public void setBottonUpdate_Text(String s) {
		if (s != null)
			if (s != "0") {
				ButtonUpdate.setVisible(true);
				ButtonUpdate.setText(s);
			} else {
				ButtonUpdate.setVisible(false);
			}

	}

	public void setLabelSeleccion_Text(String s) {
		if (s != null)
			LabelNombreSeleccion.setText(s);
	}

	public void setImagen_Seleccion(String s) {
		
	}

}
