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

import controlador.BotonCargarPartidaListener;
import controlador.BotonGuardarPartidaListener;
import controlador.ControlSimulacion;

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

/**
 * Esta clase forma el panel izquierdo del juego. Contiene la informacion
 * general del juego y los menus de comprar y seleccion de objetos del mapa.
 * 

 * 
 */
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
	private javax.swing.JButton BotonIniciar_Pausar;
	private javax.swing.JButton BotonGuardar;
	private javax.swing.JButton BotonCargar;
	
	// Atributo que representa el objeto seleccionado en el mapa.
	private Obstaculo Obs_seleccionado;

	private PanelDatosDeSeleccion PanelDatosSeleccion;
	private PanelDatosDeTorres PanelDatosTorres;

	public PanelDeDatos(PanelDatosDeSeleccion panelDatosSeleccion,
			PanelDatosDeTorres panelDatosTorres) {

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
		BotonIniciar_Pausar = new javax.swing.JButton();
		BotonGuardar = new javax.swing.JButton();
		BotonCargar = new javax.swing.JButton();
		LabelTorres = new javax.swing.JLabel();
		ScrollTorres = new javax.swing.JScrollPane();
		ListaTorres = new javax.swing.JList();
		PanelDatosTorres = panelDatosTorres;
		PanelDatosSeleccion = panelDatosSeleccion;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		Jugador jugador = Jugador.obtenerJugador();
		Escenario escenario = Escenario.obtenerEscenario();
		
		jugador.addObserver(this);

		// Seteo los nombres de los controles.
		LinkVidaJugador.setText(String.valueOf(jugador.getCantidadVidas()));

		LabelNombreJugador.setText("Nombre Jugador: ");

		LinkNombreJugador.setText(jugador.getNombre());

		LabelVida.setText("Vida:");

		LabelEnemigos.setText("Enemigos:");

		LabelDinero.setText("Dinero:");

		LinkCantEnemigosEscenario.setText(String.valueOf(escenario
				.getCantBichos()));

		LinkDineroJugador.setText(String.valueOf(jugador.getDinero()));

		LabelTorres.setText("Torres:");

		BotonIniciar_Pausar.setText("Iniciar");

		BotonGuardar.setText("Guardar");
		
		BotonCargar.setText("Cargar");
		
		// Agrego un Listener del boton Iniciar_Pausar
		BotonIniciar_Pausar.addActionListener(ControlSimulacion.obtenerControl());

		BotonGuardar.addActionListener(new BotonGuardarPartidaListener());
		
		BotonCargar.addActionListener(new BotonCargarPartidaListener());
		
		ListaDeCompras ListaCompras = new ListaDeCompras();

		ListaTorres.setModel(ListaCompras);

		// Agrego un Listener de la lista de compras.
		ListaTorres.addListSelectionListener(new ListaDeTorresListener());
		ScrollTorres.setViewportView(ListaTorres);

		// Alineo los componentes.
		BotonIniciar_Pausar.setAlignmentX(Component.CENTER_ALIGNMENT);
		BotonGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
		BotonCargar.setAlignmentX(Component.CENTER_ALIGNMENT);
		LabelVida.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelDinero.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelPuntos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelTorres.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelEnemigos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelNombreJugador.setAlignmentX(Component.RIGHT_ALIGNMENT);
		PanelDatosSeleccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelDatosTorres.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Organizo los componentes.

		// Creo una zona rigida entre componentes de tamaño 10 x 10 pixels.
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BotonIniciar_Pausar);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BotonCargar);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BotonGuardar);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelNombreJugador);
		// Creo una zona que se amolda para ocupar toda la zona vacia.
		add(Box.createHorizontalGlue());
		add(LinkNombreJugador);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelDinero);
		add(Box.createHorizontalGlue());
		add(LinkDineroJugador);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelEnemigos);
		add(Box.createHorizontalGlue());
		add(LinkCantEnemigosEscenario);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelPuntos);
		add(Box.createHorizontalGlue());
		add(LinkPuntosJugador);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelVida);
		add(Box.createHorizontalGlue());
		add(LinkVidaJugador);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(LabelTorres);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(ScrollTorres);
		add(PanelDatosTorres);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(PanelDatosSeleccion);

	}

	public void update(Graphics g) {

		Jugador jugador = Jugador.obtenerJugador();

		LinkDineroJugador.setText(String.valueOf(jugador.getDinero()));
		LinkVidaJugador.setText(String.valueOf(jugador.getCantidadVidas()));

		this.repaint();
	}
	
	public void update(Observable o, Object arg) {
		LinkVidaJugador.setText(String.valueOf(((Jugador)o).getCantidadVidas()));
		LinkDineroJugador.setText(String.valueOf(((Jugador)o).getDinero()));
	};
	
	public javax.swing.JLabel getLabel_Cant_Enemigos(){
		return LinkCantEnemigosEscenario;
	}

	public PanelDatosDeSeleccion getPanelDatosSeleccion() {
		return PanelDatosSeleccion;
	}

	public PanelDatosDeTorres getPanelDatosTorres() {
		return PanelDatosTorres;
	}

	public Obstaculo getObs_seleccionado() {
		return Obs_seleccionado;
	}
	
	public javax.swing.JButton getBotonIniciar_Pausar() {
		return BotonIniciar_Pausar;
	}

	// Creo el observador de la liste de torres, para poder acceder de forma
	// directa a los datos en el panel de datos de torres
	public class ListaDeTorresListener implements ListSelectionListener {

		private HashMap TablaEquivalencias;

		private void cargarTabla() {
			TablaEquivalencias = new HashMap();
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

			Obs_seleccionado = (Obstaculo) TablaEquivalencias.get(String
					.valueOf(ListaTorres.getSelectedIndex()));

			PanelDatosTorres.setLinkLabelDanioText(String
					.valueOf(Obs_seleccionado.getDanioQueGenera()));
			PanelDatosTorres.setLinkLabelPrecioText(String
					.valueOf(Obs_seleccionado.getPrecio()));
			PanelDatosTorres.setLinkLabelRangoText(String
					.valueOf(Obs_seleccionado.getAlcance()));

		}

	}
}
