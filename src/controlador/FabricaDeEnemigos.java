package controlador;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import modelo.Arania;
import modelo.Cucaracha;
import modelo.Enemigo;
import modelo.Escenario;
import modelo.Hormiga;
import modelo.Mosca;
import modelo.Posicion;

/**
 * Esta clase se encarga del manejo de la creación de enemigos en el mapa. Según
 * sea el nivel del mapa, se busca la cantidad adecuada de enemigos de cada
 * clase a crear.
 * 
 * @author exus
 * 
 */
public class FabricaDeEnemigos {

	private int Cantidad_Enemigos;
	private int NumeroNivel;
	private Iterator it_enemigos;
	private LinkedList ListaEnemigosListos;
	private HashMap CodigosUtiles;

	/**
	 * Este array de float guarda la cantidad base de enemigos en cada ronda
	 */
	private float[] niveles_base = { 0.1f, 0.1f, 0.1f, 0.1f, 0.15f, 0.15f,
			0.15f, 0.15f };

	private int intervalo_entre_salidas;

	private void cargarCodigos() {
		CodigosUtiles = new HashMap();

		Escenario escenario = Escenario.obtenerEscenario();

		Posicion aux_pos = new Posicion(escenario.getEntrada());
		
		CodigosUtiles.put("0", new Hormiga(aux_pos));
		CodigosUtiles.put("1", new Arania(aux_pos));
		CodigosUtiles.put("2", new Mosca(aux_pos));
		CodigosUtiles.put("3", new Cucaracha(aux_pos));
		CodigosUtiles.put("4", new Hormiga(aux_pos));
		CodigosUtiles.put("5", new Arania(aux_pos));
		CodigosUtiles.put("6", new Mosca(aux_pos));
		CodigosUtiles.put("7", new Cucaracha(aux_pos));
		CodigosUtiles.put("Hormiga", new Hormiga(aux_pos));
		CodigosUtiles.put("Arania", new Arania(aux_pos));
		CodigosUtiles.put("Mosca", new Mosca(aux_pos));
		CodigosUtiles.put("Cucaracha", new Cucaracha(aux_pos));
	}

	public void crearNuevosEnemigos(int nivel) {
		
		NumeroNivel = nivel;
		
		if (ListaEnemigosListos == null)
			ListaEnemigosListos = new LinkedList();
		else
			ListaEnemigosListos.clear();
		
		for (int i = 0; i < niveles_base.length; i++) {

			int cant_enemigos_actual = (int) (Cantidad_Enemigos
					* niveles_base[i] / 2 * NumeroNivel);
			Escenario escenario = Escenario.obtenerEscenario();
			Posicion aux_pos = new Posicion(escenario.getEntrada());

			for (int j = 0; j < cant_enemigos_actual; j++) {
				Enemigo para_crear = (Enemigo) CodigosUtiles.get(String
						.valueOf(i));

				Constructor[] constructor = para_crear.getClass()
						.getConstructors();

				Object args[] = new Object[1];
				args[0] = aux_pos;

				try {
					para_crear = (Enemigo) constructor[0].newInstance(args);
					para_crear.cambiarPosicion(aux_pos);

					ListaEnemigosListos.add(para_crear);

				} catch (InstantiationException e) {
				} catch (IllegalAccessException e) {
				} catch (IllegalArgumentException e) {
				} catch (InvocationTargetException e) {
				}

			}
		}
		
		it_enemigos = ListaEnemigosListos.iterator();
		intervalo_entre_salidas = 150 / NumeroNivel; 
		
	}
	
	public FabricaDeEnemigos(int cantidad_enemigos, int numeroNivel) {

		Cantidad_Enemigos = cantidad_enemigos;
		NumeroNivel = numeroNivel;

		cargarCodigos();

		crearNuevosEnemigos(numeroNivel);

	}

	public void setCantidad_Enemigos(int cantidad_Enemigos) {
		Cantidad_Enemigos = cantidad_Enemigos;
	}

	public int getCantidad_Enemigos() {
		return Cantidad_Enemigos;
	}

	public int getIntervalo_entre_salidas() {
		return intervalo_entre_salidas;
	}

	/**
	 * Este método devuelve el siguiente enemigo a crear en el mapa.
	 * 
	 * @return devuelve el siguiente enemigo a crear en el mapa. Si devuelve
	 *         null, quiere decir que la lista se termino.
	 */
	public Enemigo getSiguienteEnemigoParaCrear() {
		if (it_enemigos.hasNext())
			return (Enemigo) it_enemigos.next();

		return null;
	}

}
