package vista.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import customExceptions.InvalidPositionException;

import vista.SuperficieDeDibujo;
import vista.Seleccionable;

import modelo.Enemigo;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Obstaculo;
import modelo.Posicion;

public class Mapa extends JPanel implements Observer, SuperficieDeDibujo,
		MouseInputListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996408967876688000L;

	private static final int UNIDADANCHO = 15;
	private static final int UNIDADALTO = 15;

	private boolean dibujar_habilitado = true;

	private boolean insetar_objeto = false;

	private int RepresentacionMouseEnMapa_X;
	private int RepresentacionMouseEnMapa_Y;

	public boolean isInsetar_objeto() {
		return insetar_objeto;
	}

	public void setInsetar_objeto(boolean insetar_objeto) {
		this.insetar_objeto = insetar_objeto;
	}

	private Seleccionable Objeto_seleccionado;

	public int getUNIDADANCHO() {
		return UNIDADANCHO;
	}

	public int getUNIDADALTO() {
		return UNIDADALTO;
	}

	public Mapa() {

		addMouseListener(this);
		addMouseMotionListener(this);

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

		
		/**
		 * Esta parte se encarga de la seleccion de enemigos por el click del mouse
		 */
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

		} else if (arg0.getButton() == MouseEvent.BUTTON3)
			insetar_objeto = false;

		/**
		 * Este método se encarga de la generación de torres nuevas.
		 */
		if (insetar_objeto && arg0.getButton() == MouseEvent.BUTTON1) {
			Escenario escenario = Escenario.obtenerEscenario();
			Jugador jugador = Jugador.obtenerJugador();
			VistaPrincipal vistaP = VistaPrincipal.obtenerVistaPrincipal();

			
			Obstaculo aux = vistaP.getPanelDatos().getObs_seleccionado();

			if (jugador.getDinero() >= aux.getPrecio()) {

				Posicion aux_pos = new Posicion(RepresentacionMouseEnMapa_X,
						RepresentacionMouseEnMapa_Y, false);

				aux_pos.setCaminable(escenario.obtener_tipo_de_terreno(aux_pos));

				/* Como necesito crear nuevas torres con cada click, uso reflexion para
				 * conseguir el constructor y crear una neuva clase del mismo tipo
				 */
				
				// Creo un array de constructores
				Constructor[] constructor = aux.getClass().getConstructors();
			
				/*
				 *  Creo un array de Objects, que van a ser los argumentos 
				 *  del constructor a usar. Y le digo que la posicion 0 
				 *  hay un objeto del tipo posicion
				 */
				Object args[] = new Object[1];
				args[0] = aux_pos;
				
				try {
					
					//Llamo al constructor con el argumento seleccionado.
					aux = (Obstaculo) constructor[0].newInstance(args);
					
					aux.setPosicion(aux_pos);
					
					escenario.insertarObstaculoEnMapa(aux);
					jugador.ModificarDinero(-aux.getPrecio());
					
				} catch (InvalidPositionException e) {
					insetar_objeto = false;	
				} catch (InstantiationException e1) {
				} catch (IllegalAccessException e1) {
				} catch (IllegalArgumentException e) {
				} catch (InvocationTargetException e) {
				}
				
			}
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		

	}

	public void mouseExited(MouseEvent arg0) {
		

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {
		

	}

	private void setObjeto_seleccionado(Seleccionable objeto_seleccionado) {
		Objeto_seleccionado = objeto_seleccionado;
	}

	public Seleccionable getObjeto_seleccionado() {
		return Objeto_seleccionado;
	}

	public void mouseDragged(MouseEvent e) {
		

	}

	public void mouseMoved(MouseEvent e) {
		if (insetar_objeto) {
			Graphics grafico = this.getGraphics();

			grafico.setColor(Color.RED);
			grafico.fillOval(e.getX(),e.getY(),UNIDADANCHO,UNIDADALTO);
			
			setX_representacion(e.getX() / UNIDADANCHO);
			setY_representacion(e.getY() / UNIDADALTO);

			grafico.setColor(Color.BLACK);

		}

	}

	public void setX_representacion(int x_mouse) {
		RepresentacionMouseEnMapa_X = x_mouse;
	}

	public int getX_representacion() {
		return RepresentacionMouseEnMapa_X;
	}

	public void setY_representacion(int y_mouse) {
		RepresentacionMouseEnMapa_Y = y_mouse;
	}

	public int getY_representacion() {
		return RepresentacionMouseEnMapa_Y;
	}

}
