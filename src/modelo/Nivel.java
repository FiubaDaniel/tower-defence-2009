package modelo;


public class Nivel {

	private static Nivel nivel;
	private Escenario Escenario1;
	private Jugador Jugador1;
	
	
	public static Nivel obtenerNivel(Escenario escenario, Jugador jugador) {
		if (nivel == null)
			nivel = new Nivel(escenario, jugador);
		
		return nivel;
	}
	
	private Nivel(Escenario escenario, Jugador jugador) {
		Escenario1 = escenario;
		Jugador1 = jugador;
	}
	
	public void setEscenario1(Escenario escenario1) {
		Escenario1 = escenario1;
	}
	public Escenario getEscenario1() {
		return Escenario1;
	}
	public void setJugador1(Jugador jugador1) {
		Jugador1 = jugador1;
	}
	public Jugador getJugador1() {
		return Jugador1;
	}
	
	
	
}