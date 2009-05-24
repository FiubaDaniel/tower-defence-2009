package vista.menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modelo.Escenario;

public class Mapa extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996408967876688000L;

	private static final int UNIDADANCHO = 15;
	private static final int UNIDADALTO = 15;
	
	
	public Mapa() {
		setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(255, 0, 0)));

		javax.swing.GroupLayout PanelMapaLayout = new javax.swing.GroupLayout(
				this);
		setLayout(PanelMapaLayout);
		
		PanelMapaLayout.setHorizontalGroup(PanelMapaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, Escenario.getMapColumns() * UNIDADANCHO,
				Short.MAX_VALUE));
		PanelMapaLayout.setVerticalGroup(PanelMapaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, Escenario.getMapRows() * UNIDADALTO,
				Short.MAX_VALUE));
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void dibujar() {
		/*Graphics aux = new Graphics();
		this.print(aux);*/
	}

}
