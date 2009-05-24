package vista.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modelo.Escenario;
import modelo.Posicion;

public class Mapa extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996408967876688000L;

	private static final int UNIDADANCHO = 15;
	private static final int UNIDADALTO = 15;
	
	
	public Mapa() {
		
		Escenario escenario = Escenario.obtenerEscenario();
		
		escenario.addObserver(this);
		
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
		this.repaint();
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Escenario escenario = Escenario.obtenerEscenario();
		
		for (int i = 0; i < Escenario.getMapRows(); i ++)
			for (int j = 0; j < Escenario.getMapColumns(); j ++) {
				
				Posicion aux = new Posicion(i, j, true);
				
				if (escenario.obtener_tipo_de_terreno(aux))
					g.setColor(Color.getHSBColor(0.1f, 0.5f, 1));
				else
					g.setColor(Color.GREEN);
				
				g.fillRect(j * UNIDADANCHO, i * UNIDADALTO, UNIDADANCHO + j * UNIDADANCHO, UNIDADALTO + i * UNIDADALTO);
			}
		
		g.setColor(Color.BLACK);
		
		
		
	}
	
	public void dibujar() {
		/*Graphics aux = new Graphics();
		this.print(aux);*/
	}

}
