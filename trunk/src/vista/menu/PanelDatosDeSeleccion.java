package vista.menu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Esta clase se encarga de la presentacion de datos del objeto seleccionado en
 * el mapa. Además de la organizacion de los componentes en este Panel.
 * 
 * @author exus
 * 
 */
public class PanelDatosDeSeleccion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3561771492386756083L;

	private BotonUpdate ButtonUpdate;
	private JPanel ImagenSeleccion;
	private JLabel LabelNombreSeleccion;
	private JLabel LabelRango_Vel;
	private JLabel LabelVida_danio;
	private JLabel LabelUpdate;
	private JLabel LinkRango_Vel;
	private JLabel LinkVida_danio;

	public PanelDatosDeSeleccion() {
		ImagenSeleccion = new JPanel();
		LabelNombreSeleccion = new JLabel();
		LabelVida_danio = new JLabel();
		LabelRango_Vel = new JLabel();
		LabelUpdate = new JLabel();
		LinkVida_danio = new JLabel();
		LinkRango_Vel = new JLabel();
		ButtonUpdate = new BotonUpdate();

		//Coloco un borde al panel
		setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(255, 0, 0)));

		//Coloco un borde al panel de la imagen.
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

		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelNombreSeleccion);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(ImagenSeleccion);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelVida_danio);
		add(LinkVida_danio);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelRango_Vel);
		add(LinkRango_Vel);
		add(Box.createRigidArea(new Dimension(10, 10)));
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
