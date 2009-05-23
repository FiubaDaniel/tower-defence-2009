package vista.menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Mapa extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996408967876688000L;

	public Mapa() {
		setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(255, 0, 0)));

		javax.swing.GroupLayout PanelMapaLayout = new javax.swing.GroupLayout(
				this);
		setLayout(PanelMapaLayout);
		PanelMapaLayout.setHorizontalGroup(PanelMapaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 512,
				Short.MAX_VALUE));
		PanelMapaLayout.setVerticalGroup(PanelMapaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 470,
				Short.MAX_VALUE));
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
