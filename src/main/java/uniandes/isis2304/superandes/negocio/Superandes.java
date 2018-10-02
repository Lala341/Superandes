package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;


import uniandes.isis2304.superandes.persistencia.PersistenciaSuperandes;



/**
 * @version 1.0
 * @created 30-sep-2018 10:00:44
 */
public class Superandes {

	private int cantidadDeRecompra;
	
	/**
	 * Logger para escribir la traza de la ejecuciÃ³n
	 */
	private static Logger log = Logger.getLogger(Superandes.class.getName());
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperandes pp;
	
	/* ****************************************************************
	 * 			MÃ©todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Superandes ()
	{
		pp = PersistenciaSuperandes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Superandes (JsonObject tableConfig)
	{
		pp = PersistenciaSuperandes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexiÃ³n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	

	/**
	 * @return the cantidadDeRecompra
	 */
	public int getCantidadDeRecompra() {
		return cantidadDeRecompra;
	}

	/**
	 * @param cantidadDeRecompra the cantidadDeRecompra to set
	 */
	public void setCantidadDeRecompra(int cantidadDeRecompra) {
		this.cantidadDeRecompra = cantidadDeRecompra;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar CIUDAD
	 *****************************************************************/
	/**
	 * Adiciona Ciudad de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Ciudad adicionarCiudad (String nombre, String direccion)
	{
        log.info ("Adicionando Ciudad: " + nombre);
        Ciudad ciudad = pp.adicionarCiudad (nombre, direccion);		
        log.info ("Adicionando ciudad: " + nombre);
        return ciudad;
	}
	
	/**
	 * Elimina Ciudad por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCiudadPorNombre (String nombre)
	{
		log.info ("Eliminando Ciudad por nombre: " + nombre);
        long resp = pp.eliminarCiudadPorNombre (nombre);		
        log.info ("Eliminando Ciudad por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina Ciudad por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCiudadPorId (long id)
	{
		log.info ("Eliminando Ciudad por id: " + id);
        long resp = pp.eliminarCiudadPorId (id);		
        log.info ("Eliminando Ciudad por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Ciudad en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Ciudad, llenos con su información básica
	 */
	public List<Ciudad> darCiudad ()
	{
		log.info ("Consultando Ciudad");
        List<Ciudad> tiposBebida = pp.darCiudades();	
        log.info ("Consultando Ciudad: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Ciudad en Superandes y los devuelve como una lista de VOCiudad
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCiudad con todos los tipos de Ciudad que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCiudad> darVOCiudades ()
	{
		log.info ("Generando los VO de Ciudad");        
        List<VOCiudad> voTipos = new LinkedList<VOCiudad> ();
        for (Ciudad tb : pp.darCiudades ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Ciudad: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra Ciudad en Superandes con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de Ciudad
	 * @return Un objeto Ciudad, con su información básica
	 */
	public Ciudad darCiudadPorNombre (String nombre)
	{
		log.info ("Buscando Ciudad por nombre: " + nombre);
		List<Ciudad> tb = pp.darCiudadPorNombre (nombre);
		return !tb.isEmpty () ? tb.get (0) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar Consumidor
	 *****************************************************************/
	/**
	 * Adiciona Consumidor de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Consumidor adicionarConsumidor (String nombre, String correoElectronico, String tipo)
	{
        log.info ("Adicionando Consumidor: " + nombre);
        Consumidor Consumidor = pp.adicionarConsumidor (nombre, correoElectronico, tipo);		
        log.info ("Adicionando Consumidor: " + nombre);
        return Consumidor;
	}
	
	/**
	 * Elimina Consumidor por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarConsumidorPorNombre (String nombre)
	{
		log.info ("Eliminando Consumidor por nombre: " + nombre);
        long resp = pp.eliminarConsumidorPorNombre (nombre);		
        log.info ("Eliminando Consumidor por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina Consumidor por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarConsumidorPorId (long id)
	{
		log.info ("Eliminando Consumidor por id: " + id);
        long resp = pp.eliminarConsumidorPorId (id);		
        log.info ("Eliminando Consumidor por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Consumidor en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Consumidor, llenos con su información básica
	 */
	public List<Consumidor> darConsumidor ()
	{
		log.info ("Consultando Consumidor");
        List<Consumidor> tiposBebida = pp.darConsumidores();	
        log.info ("Consultando Consumidor: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Consumidor en Superandes y los devuelve como una lista de VOConsumidor
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOConsumidor con todos los tipos de Consumidor que conoce la aplicación, llenos con su información básica
	 */
	public List<VOConsumidor> darVOConsumidores ()
	{
		log.info ("Generando los VO de Consumidor");        
        List<VOConsumidor> voTipos = new LinkedList<VOConsumidor> ();
        for (Consumidor tb : pp.darConsumidores ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Consumidor: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra Consumidor en Superandes con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de Consumidor
	 * @return Un objeto Consumidor, con su información básica
	 */
	public Consumidor darConsumidorPorNombre (String nombre)
	{
		log.info ("Buscando Consumidor por nombre: " + nombre);
		List<Consumidor> tb = pp.darConsumidorPorNombre (nombre);
		return !tb.isEmpty () ? tb.get (0) : null;
	}

	
	

	/* ****************************************************************
	 * 			MÃ©todos para administraciÃ³n
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos 
	 
	 */
	public long [] limpiarSuperandes ()
	{
        log.info ("Limpiando la BD de Superandes");
        long [] borrrados = pp.limpiarSuperandes();	
        log.info ("Limpiando la BD de Superandes: Listo!");
        return borrrados;
	}
	
	

}