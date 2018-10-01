package main.java.superandes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

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

	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaEstante ()
	{
		return tablas.get (5);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaCategoria ()
	{
		return tablas.get (6);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPerecedero ()
	{
		return tablas.get (7);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaNoPerecedero ()
	{
		return tablas.get (8);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProducto ()
	{
		return tablas.get (9);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoEstante ()
	{
		return tablas.get (10);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoBodega ()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPersonaNatural ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaEmpresa ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaConsumidor ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaFidelizacion ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoTransaccion ()
	{
		return tablas.get (16);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaVenta ()
	{
		return tablas.get (17);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaFactura ()
	{
		return tablas.get (18);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaCarritoCompras ()
	{
		return tablas.get (19);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoVenta ()
	{
		return tablas.get (20);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoCarritoCompras ()
	{
		return tablas.get (21);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromocion ()
	{
		return tablas.get (22);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromocionSucursal ()
	{
		return tablas.get (23);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProveedores ()
	{
		return tablas.get (24);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoOfrecido ()
	{
		return tablas.get (25);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaOrdenPedido ()
	{
		return tablas.get (26);
	}
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos 
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 */
	public long [] limpiarSuperandes()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperandes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	//log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	//return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		return null;
		
	}
	private String darDetalleException(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}
}
