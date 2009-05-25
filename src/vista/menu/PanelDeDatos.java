package vista.menu;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Escenario;
import modelo.Jugador;
import modelo.Posicion;
import modelo.Torre;
import modelo.TorreAzul;
import modelo.TorreBlanca;
import modelo.TorreDorada;
import modelo.TorrePlateada;

public class PanelDeDatos extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1980001560016222467L;
	
	private javax.swing.JLabel LabelDinero;
	private javax.swing.JLabel LabelEnemigos;
	private javax.swing.JLabel LabelPuntos;
	private javax.swing.JLabel LabelTorres;
	private javax.swing.JLabel LabelVida;
	private javax.swing.JLabel LinkCantEnemigosEscenario;
	private javax.swing.JLabel LinkDineroJugador;
	private javax.swing.JLabel LinkPuntosJugador;
	private javax.swing.JLabel LinkVidaJugador;
	private javax.swing.JList ListaTorres;
	private javax.swing.JScrollPane ScrollTorres;
	
	private PanelDatosDeSeleccion PanelDatosSeleccion;
	private PanelDatosDeTorres PanelDatosTorres;
	
	
	public PanelDeDatos(PanelDatosDeSeleccion panelDatosSeleccion, PanelDatosDeTorres panelDatosTorres) {

		LinkVidaJugador = new javax.swing.JLabel();
		LabelPuntos = new javax.swing.JLabel();
		LabelVida = new javax.swing.JLabel();
		LabelEnemigos = new javax.swing.JLabel();
		LabelDinero = new javax.swing.JLabel();
		LinkPuntosJugador = new javax.swing.JLabel();
		LinkCantEnemigosEscenario = new javax.swing.JLabel();
		LinkDineroJugador = new javax.swing.JLabel();
		LabelTorres = new javax.swing.JLabel();
		ScrollTorres = new javax.swing.JScrollPane();
		ListaTorres = new javax.swing.JList();
		PanelDatosTorres = panelDatosTorres;
		PanelDatosSeleccion = panelDatosSeleccion;
		
		setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1,
				1, 1, new java.awt.Color(255, 0, 0)));
		
		Jugador jugador = Jugador.obtenerJugador(50, 1000, "Exus");
		Escenario escenario = Escenario.obtenerEscenario();
		
		LinkVidaJugador.setText(String.valueOf(jugador.getCantidadVidas()));

		LabelPuntos.setText("Puntos:");

		LabelVida.setText("Vida:");

		LabelEnemigos.setText("Enemigos Restantes:");

		LabelDinero.setText("Dinero:");

		LinkPuntosJugador.setText(String.valueOf(jugador.getPuntos()));

		LinkCantEnemigosEscenario.setText(String.valueOf(escenario.getCantBichos()));

		LinkDineroJugador.setText(String.valueOf(jugador.getDinero()));

		LabelTorres.setText("Torres:");

		ListaDeCompras ListaCompras = new ListaDeCompras();
		
		ListaTorres.setModel(ListaCompras);
		
		ListaTorres.addListSelectionListener(new ListaDeTorresListener());
		ScrollTorres.setViewportView(ListaTorres);

		javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(
				this);
		setLayout(PanelDatosLayout);
		PanelDatosLayout
				.setHorizontalGroup(PanelDatosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanelDatosLayout
										.createSequentialGroup()
										.addComponent(LabelVida)
										.addGap(100, 100, 100)
										.addComponent(
												LinkVidaJugador,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												218, Short.MAX_VALUE).addGap(
												100, 100, 100))
						.addGroup(
								PanelDatosLayout
										.createSequentialGroup()
										.addComponent(LabelPuntos)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												LinkPuntosJugador,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												318,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(300, Short.MAX_VALUE))
						.addGroup(
								PanelDatosLayout
										.createSequentialGroup()
										.addComponent(LabelEnemigos)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												LinkCantEnemigosEscenario,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												203, Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								PanelDatosLayout
										.createSequentialGroup()
										.addComponent(LabelDinero)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												LinkDineroJugador,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												176,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(127, 127, 127))
						.addGroup(
								PanelDatosLayout
										.createSequentialGroup()
										.addGroup(
												PanelDatosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																LabelTorres,
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																PanelDatosLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				PanelDatosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								PanelDatosSeleccion,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								279,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								PanelDatosLayout
																										.createSequentialGroup()
																										.addComponent(
																												ScrollTorres,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												129,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(20, 20, 20)
																										.addComponent(
																												PanelDatosTorres,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))))
										.addContainerGap(54, Short.MAX_VALUE)));
		PanelDatosLayout
				.setVerticalGroup(PanelDatosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanelDatosLayout
										.createSequentialGroup()
										.addGroup(
												PanelDatosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(LabelVida)
														.addComponent(
																LinkVidaJugador))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																LabelPuntos)
														.addComponent(
																LinkPuntosJugador))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																LabelEnemigos)
														.addComponent(
																LinkCantEnemigosEscenario))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																LabelDinero)
														.addComponent(
																LinkDineroJugador))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												8, Short.MAX_VALUE)
										.addComponent(LabelTorres)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												PanelDatosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																PanelDatosTorres,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																130,
																Short.MAX_VALUE)
														.addComponent(
																ScrollTorres,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																130,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												PanelDatosSeleccion,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)));

		
		jugador.addObserver(this);
		
	}
	
	public class ListaDeTorresListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			Posicion auxp = new Posicion();
			Torre aux = null;
			
			//TODO Aprender Reflexion
			
			if (ListaTorres.getSelectedIndex() == 0) {
				aux = new TorreBlanca(auxp);
			} else if (ListaTorres.getSelectedIndex() == 1) {
				aux = new TorrePlateada(auxp);
			} else if (ListaTorres.getSelectedIndex() == 2) {
				aux = new TorreDorada(auxp);
			} else if (ListaTorres.getSelectedIndex() == 3) {
				aux = new TorreAzul(auxp);
			}
		
			PanelDatosTorres.setLinkLabelDañoText(String.valueOf(aux.getDanioQueGenera()));
			PanelDatosTorres.setLinkLabelPrecioText(String.valueOf(aux.getPrecio()));
			PanelDatosTorres.setLinkLabelRangoText(String.valueOf(aux.getAlcance()));
		
		}

	};

	public void update(Observable o, Object arg) {
		LinkDineroJugador.setText(String.valueOf(((Jugador)o).getDinero()));
		LinkPuntosJugador.setText(String.valueOf(((Jugador)o).getPuntos()));
		LinkVidaJugador.setText(String.valueOf(((Jugador)o).getCantidadVidas()));
		
		
		this.repaint();
	}
	
	public void update(Graphics g) {
		paint(g);
	}

}
