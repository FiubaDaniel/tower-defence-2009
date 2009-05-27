package vista.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Arena;
import modelo.Escenario;
import modelo.Jugador;
import modelo.Obstaculo;
import modelo.Pegote;
import modelo.Posicion;
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
	private javax.swing.JLabel LabelNombreJugador;
	private javax.swing.JLabel LinkNombreJugador;
	
	
	private PanelDatosDeSeleccion PanelDatosSeleccion;
	private PanelDatosDeTorres PanelDatosTorres;
	
	
	public PanelDeDatos(PanelDatosDeSeleccion panelDatosSeleccion, PanelDatosDeTorres panelDatosTorres) {

		LabelNombreJugador = new javax.swing.JLabel();
		LinkNombreJugador = new javax.swing.JLabel();
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
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		
		Jugador jugador = Jugador.obtenerJugador(50, 1000, "fafa");
		Escenario escenario = Escenario.obtenerEscenario();
		
		jugador.addObserver(this);
		
		LinkVidaJugador.setText(String.valueOf(jugador.getCantidadVidas()));

		LabelPuntos.setText("Puntos:");

		LabelNombreJugador.setText("Nombre Jugador: ");
		
		LinkNombreJugador.setText(jugador.getNombre());
		
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

		LabelVida.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelDinero.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelPuntos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelTorres.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelEnemigos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelNombreJugador.setAlignmentX(Component.RIGHT_ALIGNMENT);
		PanelDatosSeleccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelDatosTorres.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelNombreJugador);
		add(Box.createHorizontalGlue());
		add(LinkNombreJugador);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelDinero);
		add(Box.createHorizontalGlue());
		add(LinkDineroJugador);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelEnemigos);
		add(Box.createHorizontalGlue());
		add(LinkCantEnemigosEscenario);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelPuntos);
		add(Box.createHorizontalGlue());
		add(LinkPuntosJugador);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelVida);
		add(Box.createHorizontalGlue());
		add(LinkVidaJugador);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(LabelTorres);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(ScrollTorres);
		add(PanelDatosTorres);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(PanelDatosSeleccion);
		
		jugador.addObserver(this);
		
	}
	
	public class ListaDeTorresListener implements ListSelectionListener {

		private HashMap TablaEquivalencias;
		
		private void cargarTabla() {
			TablaEquivalencias = new HashMap ();
			Posicion auxp = new Posicion();
			TablaEquivalencias.put("0", new TorreBlanca(auxp));
			TablaEquivalencias.put("1", new TorrePlateada(auxp));
			TablaEquivalencias.put("2", new TorreDorada(auxp));
			TablaEquivalencias.put("3", new TorreAzul(auxp));
			TablaEquivalencias.put("4", new Pegote(auxp));
			TablaEquivalencias.put("5", new Arena(auxp));
		}
		
		public ListaDeTorresListener() {
			cargarTabla();
		}
		
		public void valueChanged(ListSelectionEvent e) {
			Obstaculo aux = null;
			
			aux = (Obstaculo)TablaEquivalencias.get(String.valueOf(ListaTorres.getSelectedIndex()));
			
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
	
	public PanelDatosDeSeleccion getPanelDatosSeleccion() {
		return PanelDatosSeleccion;
	}
	
	public PanelDatosDeTorres getPanelDatosTorres() {
		return PanelDatosTorres;
	}

}
