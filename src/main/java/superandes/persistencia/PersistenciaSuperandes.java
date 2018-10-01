package main.java.superandes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PersistenciaSuperandes {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias 
	 */
	private SQLUtil sqlUtil;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/
	private PersistenciaSuperandes() {
		pmf = JDOHelper.getPersistenceManagerFactory("Superandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Superandes_sequence");
		tablas.add ("SUPERANDES");
		tablas.add ("CIUDAD");
		tablas.add ("SUCURSAL");
		tablas.add ("BODEGA");
		tablas.add ("ESTANTE");
		tablas.add ("CATEGORIA");
		tablas.add ("PERECEDERO");
		tablas.add ("NOPERECEDERO");
		tablas.add ("PRODUCTO");
		tablas.add ("PRODUCTOESTANTE");
		tablas.add ("PRODUCTOBODEGA");
		tablas.add ("PERSONANATURAL");
		tablas.add ("EMPRESA");
		tablas.add ("CONSUMIDOR");
		tablas.add ("FIDELIZACION");
		tablas.add ("PRODUCTOTRANSACCION");
		tablas.add ("VENTA");
		tablas.add ("FACTURA");
		tablas.add ("CARRITOCOMPRAS");
		tablas.add ("PRODUCTOVENTA");
		tablas.add ("PRODUCTOCARRITOCOMPRAS");
		tablas.add ("PROMOCION");
		tablas.add ("PROMOCIONSUCURSAL");
		tablas.add ("PROVEEDORES");
		tablas.add ("PRODUCTOOFRECIDO");
		tablas.add ("ORDENPEDIDO");
	}
	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaSuperandes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaSuperandes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}

	
	
	private void crearClasesSQL() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return La cadena de caracteres con el nombre del secuenciador 
	 */
	public String darSeqSuperandes ()
	{
		return tablas.get (0);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaSuperandes ()
	{
		return tablas.get (1);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaCiudad ()
	{
		return tablas.get (2);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaSucursal ()
	{
		return tablas.get (3);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaBodega ()
	{
		return tablas.get (4);
	}

}
