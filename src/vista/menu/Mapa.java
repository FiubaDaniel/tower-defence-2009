package vista.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import titiritero.SuperficieDeDibujo;
import vista.Seleccionable;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Obstaculo;
import modelo.Posicion;

public class Mapa extends JPanel implements Observer, SuperficieDeDibujo,
		MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996408967876688000L;

	private static final int UNIDADANCHO = 15;
	private static final int UNIDADALTO = 15;

	private boolean dibujar_habilitado = true;

	private Seleccionable Objeto_seleccionado;

	public int getUNIDADANCHO() {
		return UNIDADANCHO;
	}

	public int getUNIDADALTO() {
		return UNIDADALTO;
	}

	public Mapa() {

		addMouseListener(this);

		Escenario escenario = Escenario.obtenerEscenario();

		setObjeto_seleccionado(null);

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

			// Creación buffer
			Image buffer = createImage(Escenario.getMapColumns() * UNIDADANCHO,
					Escenario.getMapRows() * UNIDADALTO);
			Graphics graph_buffer = buffer.getGraphics();
			// Creación buffer

			Escenario escenario = Escenario.obtenerEscenario();

			// Grafico sobre el buffer
			for (int i = 0; i < Escenario.getMapRows(); i++)
				for (int j = 0; j < Escenario.getMapColumns(); j++) {

					Posicion aux = new Posicion(i, j, true);

					if (escenario.obtener_tipo_de_terreno(aux))
						graph_buffer.setColor(Color.getHSBColor(0.1f, 0.9f,
								0.8f));
					else
						graph_buffer.setColor(Color.GREEN);

					graph_buffer.fillRect(j * UNIDADANCHO, i * UNIDADALTO,
							UNIDADANCHO + j * UNIDADANCHO, UNIDADALTO + i
									* UNIDADALTO);
				}

			g.setColor(Color.BLACK);

			// Copio el contenido del buffer a la ventana de Mapa
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

	public void mouseClicked(MouseEvent arg0) {

		if (arg0.getButton() == MouseEvent.BUTTON1) {
			int posX = (int) (arg0.getX() / UNIDADANCHO);
			int posY = (int) (arg0.getY() / UNIDADALTO);

			Escenario escenario = Escenario.obtenerEscenario();

			Iterator it_en = escenario.getIteradordeEnemigos();

			boolean EnemigoSeleccionado = false;
			Iterator it_obs = escenario.getIteradordeObstaculos();

			while (it_en.hasNext()) {
				try {
					Enemigo aux_en = (Enemigo) it_en.next();
					if (aux_en.getPosicion().getCoordX() == posX
							&& aux_en.getPosicion().getCoordY() == posY) {
						setObjeto_seleccionado(aux_en);
						EnemigoSeleccionado = true;
						break;
					}
				} catch (NoSuchElementException e) {
					break;
				}

			}

			while (it_obs.hasNext() && !EnemigoSeleccionado) {
				try {
					Obstaculo aux_obs = (Obstaculo) it_obs.next();
					if (aux_obs.getPosicion().getCoordX() == posX
							&& aux_obs.getPosicion().getCoordY() == posY) {
						setObjeto_seleccionado(aux_obs);
						break;
					}
				} catch (NoSuchElementException e) {
					break;
				}
			}

		}

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void setObjeto_seleccionado(Seleccionable objeto_seleccionado) {
		Objeto_seleccionado = objeto_seleccionado;
	}

	public Seleccionable getObjeto_seleccionado() {
		return Objeto_seleccionado;
	}

}
