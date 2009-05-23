package titiritero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Nicolas
 * Esta clase es la encargada de manejar todo el gameloop. B�sicamente tiene una lista
 * de ObjetosVivos y una Dibujables que son recorridas en cada gameloop.
 */
public class ControladorJuego {
	
	public ControladorJuego(){
		this.objetosVivos = new ArrayList();
		this.dibujables = new ArrayList();
		this.mouseClickObservadores = new ArrayList();
	}
	
	public void comenzar(){
		estaEnEjecucion = true;
		try{
		while(estaEnEjecucion){
			simular();
			dibujar();
			Thread.sleep(intervaloSimulacion);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void detener(){
		this.estaEnEjecucion = false;
	}
	
	public void agregarObjetoVivo(ObjetoVivo objetoVivo){
		objetosVivos.add(objetoVivo);
	}
	
	public void removerSimulador(ObjetoVivo objetoVivo){
		objetosVivos.remove(objetoVivo);
	}

	public void agregarDibujable(Dibujable unDibujable){
		dibujables.add(unDibujable);
	}
	
	public void removerDibujable(Dibujable unDibujable){
		dibujables.remove(unDibujable);
	}
	
	public long getIntervaloSimulacion() {
		return intervaloSimulacion;
	}

	public void setIntervaloSimulacion(long intervaloSimulacion) {
		this.intervaloSimulacion = intervaloSimulacion;
	}

	private void dibujar() {
		Iterator iterador = dibujables.iterator();
		while(iterador.hasNext()){
			Dibujable dibujable = (Dibujable)iterador.next();
			dibujable.dibujar(this.superficieDeDibujo);
			//System.out.println(dib.getPosicionable().getX());
			//System.out.println( dib.getPosicionable().getY());
		}		
		this.superficieDeDibujo.actualizar();
	}
	
	private void simular() {
		this.superficieDeDibujo.limpiar();
		Iterator iterador = objetosVivos.iterator();
		while(iterador.hasNext()){
			((ObjetoVivo)iterador.next()).vivir();
		}
	}

	public SuperficieDeDibujo getSuperficieDeDibujo() {
		return superficieDeDibujo;
	}

	public void setSuperficieDeDibujo(SuperficieDeDibujo superficieDeDibujo) {
		this.superficieDeDibujo = superficieDeDibujo;
	}
	
	/*
	 * Se encarga de derivar el manejo del evento click al objeto vista correspondiente
	 */
	public void despacharMouseClick(int x, int y){
		MouseClickObservador mouseClickObservador;
		Iterator iterador = this.mouseClickObservadores.iterator();
		while(iterador.hasNext()){
			mouseClickObservador = (MouseClickObservador)iterador.next();
			mouseClickObservador.MouseClick(x, y);
		}
	}
	
	public void agregarMouseClickObservador(MouseClickObservador unMouseClickObservador){
		this.mouseClickObservadores.add(unMouseClickObservador);
	}
	
	public void removerMouseClickObservador(MouseClickObservador unMouseClickObservador){
		this.mouseClickObservadores.remove(unMouseClickObservador);
	}
	
	private long intervaloSimulacion;
	private boolean estaEnEjecucion;
	private List objetosVivos;
	private List dibujables;
	private List mouseClickObservadores;
	private SuperficieDeDibujo superficieDeDibujo;	
}
