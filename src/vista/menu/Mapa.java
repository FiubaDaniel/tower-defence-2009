package vista.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;

import modelo.Escenario;
import modelo.Posicion;

public class Mapa extends JPanel implements Observer, SuperficieDeDibujo, Dibujable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996408967876688000L;

	private static final int UNIDADANCHO = 15;
	private static final int UNIDADALTO = 15;

	private boolean dibujar_habilitado = true;

	public int getUNIDADANCHO() {
		return UNIDADANCHO;
	}
	
	public int getUNIDADALTO() {
		return UNIDADALTO;
	}
	
	public Mapa() {

		Escenario escenario = Escenario.obtenerEscenario();

		escenario.addObserver(this);

		setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,
				new java.awt.Color(255, 0, 0)));

		javax.swing.GroupLayout PanelMapaLayout = new javax.swing.GroupLayout(
				this);
		setLayout(PanelMapaLayout);
		setDoubleBuffered(true);

		PanelMapaLayout.setHorizontalGroup(PanelMapaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				Escenario.getMapColumns() * UNIDADANCHO, Short.MAX_VALUE));
		PanelMapaLayout.setVerticalGroup(PanelMapaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				Escenario.getMapRows() * UNIDADALTO, Short.MAX_VALUE));
	}

	public void update(Observable o, Object arg) {
		dibujar_habilitado = true;
		this.repaint();

	}

	public void update(Graphics g) {
		dibujar_habilitado = true;
		paint(g);
	}

	
	public void paint(Graphics g) {

		if (dibujar_habilitado) {
			
			
			//Creación buffer
			Image buffer = createImage(Escenario.getMapColumns() * UNIDADANCHO, Escenario.getMapRows() * UNIDADALTO);
			Graphics graph_buffer = buffer.getGraphics();
			//Creación buffer
			
			Escenario escenario = Escenario.obtenerEscenario();

			//Grafico sobre el buffer
			for (int i = 0; i < Escenario.getMapRows(); i++)
				for (int j = 0; j < Escenario.getMapColumns(); j++) {

					Posicion aux = new Posicion(i, j, true);

					if (escenario.obtener_tipo_de_terreno(aux))
						graph_buffer.setColor(Color.getHSBColor(0.1f, 0.9f, 0.8f));
					else
						graph_buffer.setColor(Color.GREEN);

					graph_buffer.fillRect(j * UNIDADANCHO, i * UNIDADALTO, UNIDADANCHO + j
							* UNIDADANCHO, UNIDADALTO + i * UNIDADALTO);
				}

			g.setColor(Color.BLACK);

			
			//Copio el contenido del buffer a la ventana de Mapa
			g.drawImage(buffer, 0, 0, this);
			
		}

	}

	public void actualizar() {
		dibujar_habilitado = true;
		this.repaint();

	}

	public void limpiar() {
		dibujar_habilitado = false;
		this.repaint();
		
	}

	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = ((Mapa)superfice).getGraphics();
		paint(grafico);
		
	}

	public Posicionable getPosicionable() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPosicionable(Posicionable posicionable) {
		// TODO Auto-generated method stub
		
	}

}
