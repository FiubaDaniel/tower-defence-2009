package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import org.jdom.Element;
import customExceptions.*;

public abstract class Enemigo  implements Cloneable, Seleccionable, Persistente {

	private int vida;
	private boolean bichoVolador = false;
	private int velocidad;
	private Posicion lugarQueOcupa;
	private boolean frenado = false;
	private int tiempoFrenado;
	private int segundosDelSistema;
	protected int cant_avanzada = 0;
	private int premioDinero;

	/**
	 * Devuelve true si pudo avanzar y false si no pudo.
	 * @param terreno
	 * @return
	 */
	public boolean avanzar(Escenario terreno){
		if (this.getFrenado() == false) {
			Posicion siguiente = terreno.obtenerSiguientePosicionCaminable(this
					.getPosicion(), cant_avanzada);
			this.cambiarPosicion(siguiente);
			cant_avanzada++;
			if(cant_avanzada > terreno.getCaminoAlaSalida().size())
				cant_avanzada = 0;
		}
		return true;
	}

	public void disminuirVida(int vidaRestada) throws EnemigoYaMuerto {
		if (vida == 0)
			throw new EnemigoYaMuerto();
		else if (vida <= vidaRestada)
			vida = 0;
		else
			vida -= vidaRestada;
	}

	public void disminuirVelocidad(int porcentaje) throws ValorNegativoException {
		if (porcentaje < 0)
			throw new ValorNegativoException();
		else
			velocidad = porcentaje * velocidad / 100;
	}

	public boolean getFrenado() {
		this.procesarTiempoDetenido();
		return frenado;
	}

	private boolean procesarTiempoDetenido(){
		Calendar calendario = Calendar.getInstance();
		int diferencia = calendario.get(Calendar.SECOND) - segundosDelSistema;
		if (diferencia < 0)
			diferencia = diferencia * -1;
		if (diferencia > tiempoFrenado)
			frenado = false;
		else
			frenado = true;
		return frenado;
	}
	
	public void frenar(int segundos) {
		if (segundos < 0)
			throw new ValorNegativoException();
		else{
			Calendar calendario = Calendar.getInstance();
			segundosDelSistema = calendario.get(Calendar.SECOND);
			tiempoFrenado = segundos;
			this.procesarTiempoDetenido();
		}
	}

	public Posicion getPosicion() {
		return lugarQueOcupa;
	}

	public int getVida() {
		return vida;
	}

	protected void setVelocidad(int velocidadInicial) throws ValorNegativoException {
		if (velocidadInicial < 0)
			throw new ValorNegativoException();
		else
			velocidad = velocidadInicial;
	}

	protected void setVida(int vidaInicial) throws ValorNegativoException {
		if (vidaInicial < 0)
			throw new ValorNegativoException();
		else
			vida = vidaInicial;

	}

	protected void setVolador() {
		bichoVolador = true;
	}

	public int getVelocidad() {
		return velocidad;
	}

	protected void setPosicion(Posicion posicion) {
		lugarQueOcupa = posicion;
	}


	public void cambiarPosicion(Posicion unLugar) {
		lugarQueOcupa = unLugar;
	}

	public boolean getVolador() {
		return bichoVolador;
	}
	
	public void setCant_avanzada(int cant_avanzada) {
		this.cant_avanzada = cant_avanzada;
	}

	public int getCant_avanzada() {
		return cant_avanzada;
	}
	
	public int getRango_Velocidad() {
		return velocidad;
	}

	public int getVida_Danio() {
		return vida;
	}

	public int getValorEvolucion() {
		return 0;
	}

	protected void setPremioDinero(int premioDinero) {
		this.premioDinero = premioDinero;
	}

	public int getPremioDinero() {
		return premioDinero;
	}

	public Element persistir() {
		Element xmlElement = new Element("Enemigo");
        xmlElement.addContent(lugarQueOcupa.persistir());
        xmlElement.setAttribute("Tipo", this.getClass().getName());
        xmlElement.setAttribute("Cantidad_Avanzada", String.valueOf(this.cant_avanzada));
       
        return xmlElement;
	}

	public static Enemigo recuperar(Element actual) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
	
		Escenario escenario=Escenario.obtenerEscenario();
		Posicion pos = new Posicion(escenario.getEntrada());
		Element posXML=actual.getChild("Posicion");
		Class<?> claseActual = Class.forName(actual.getAttributeValue("Tipo"));
		Constructor<?> constructor = claseActual.getDeclaredConstructor(Posicion.class);	
		Enemigo enemigo = (Enemigo) constructor.newInstance(pos);
		Posicion pos2=new Posicion(posXML);
		enemigo.cambiarPosicion(pos2);
		enemigo.cant_avanzada = Integer.parseInt(actual.getAttributeValue("Cantidad_Avanzada"));
		return enemigo;
	}
	
	public abstract Enemigo clone();
}
