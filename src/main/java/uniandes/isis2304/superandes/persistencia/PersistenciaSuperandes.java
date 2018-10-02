package uniandes.isis2304.superandes.persistencia;

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

import uniandes.isis2304.superandes.negocio.*;

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

	private SQLCiudad sqlCiudad;

	private SQLConsumidor sqlConsumidor;

	private SQLFidelizacion sqlFidelizacion;

	private SQLProductoTransaccion sqlProductoTransaccion;

	private SQLVenta sqlVenta;

	private SQLFactura sqlFactura;

	private SQLCarritoCompras sqlCarritoCompras;

	private SQLProductoVenta sqlProductoVenta;

	private SQLProductoCarritoCompras sqlProductoCarritoCompras;
	
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
		tablas.add ("CONSUMIDOR");
		tablas.add ("PERSONANATURAL");
		tablas.add ("EMPRESA");
		tablas.add ("FIDELIZACION");
		tablas.add ("PRODUCTOTRANSACCION");
		tablas.add ("FACTURA");
		tablas.add ("VENTA");
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
		sqlCiudad = new SQLCiudad(this);
		
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
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaEmpresa ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaConsumidor ()
	{
		return tablas.get (12);
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
		return tablas.get (18);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaFactura ()
	{
		return tablas.get (17);
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

	/* ****************************************************************
	 * 			M�todos para manejar los CIUDAD
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public Ciudad adicionarCiudad(String nombre,String direccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCiudad.adicionar(pm, id,nombre,direccion);
            tx.commit();
            
            log.trace ("Inserci�n de Ciudad: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Ciudad (id, nombre,direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @param nombre 
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarCiudadPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCiudad.eliminarPorNombre(pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarCiudadPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCiudad.eliminarPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Ciudad> darCiudades ()
	{
		return sqlCiudad.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Ciudad> darCiudadPorNombre (String nombre)
	{
		return sqlCiudad.darListaPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Ciudad darCiudadPorId (long id)
	{
		return sqlCiudad.darPorId (pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			M�todos para manejar los Consumidor
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public Consumidor adicionarConsumidor(String nombre,String correoElectronico, String tipoConsumidor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlConsumidor.adicionar(pm, id,nombre,correoElectronico, tipoConsumidor);
            tx.commit();
            
            log.trace ("Inserci�n de Consumidor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Consumidor ( id,nombre,correoElectronico,tipoConsumidor);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @param nombre 
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarConsumidorPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConsumidor.eliminarPorNombre(pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarConsumidorPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConsumidor.eliminarPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Consumidor> darConsumidores ()
	{
		return sqlConsumidor.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Consumidor> darConsumidorPorNombre (String nombre)
	{
		return sqlConsumidor.darListaPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Consumidor darConsumidorPorId (long id)
	{
		return sqlConsumidor.darPorId (pmf.getPersistenceManager(), id);
	}

	
	/* ****************************************************************
	 * 			M�todos para manejar los Fidelizacion
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public Fidelizacion adicionarFidelizacion(int puntos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlFidelizacion.adicionar(pm, id,puntos);
            tx.commit();
            
            log.trace ("Inserci�n de Fidelizacion: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Fidelizacion (id, puntos);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarFidelizacionPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlFidelizacion.eliminarPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Fidelizacion> darFidelizaciones ()
	{
		return sqlFidelizacion.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Fidelizacion darFidelizacionPorId (long id)
	{
		return sqlFidelizacion.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M�todos para manejar los ProductoTransaccion
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public ProductoTransaccion adicionarProductoTransaccion(int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlProductoTransaccion.adicionar(pm, id,cantidad);
            tx.commit();
            
            log.trace ("Inserci�n de ProductoTransaccion: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ProductoTransaccion (cantidad,id);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarProductoTransaccionPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoTransaccion.eliminarPorIdProducto(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarProductoTransaccionPorCantidad (int cant) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoTransaccion.eliminarPorCantidad(pm,cant);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<ProductoTransaccion> darProductoTransacciones ()
	{
		return sqlProductoTransaccion.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<ProductoTransaccion> darProductoTransaccionPorId (long id)
	{
		return  sqlProductoTransaccion.darListaPorIdProducto (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M�todos para manejar  Venta
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public Venta adicionarVenta(String fecha, String formaPago, double valorTotal, long factura, long consumidor){
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlVenta.adicionar(pm, id, fecha, formaPago, valorTotal, factura, consumidor);
            tx.commit();
            
            log.trace ("Inserci�n de Venta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Venta (id, fecha, formaPago, valorTotal, factura, consumidor);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @param nombre 
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarVentaPorFecha (String fec) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVenta.eliminarPorFecha(pm, fec);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarVentaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVenta.eliminarPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Venta> darVentas ()
	{
		return sqlVenta.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Venta darVentaPorId (long id)
	{
		return sqlVenta.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M�todos para manejar Factura
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public Factura adicionarFactura()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlFactura.adicionar(pm, id);
            tx.commit();
            
            log.trace ("Inserci�n de Factura: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Factura (id);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarFacturaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlFactura.eliminarPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Factura> darFacturas ()
	{
		return sqlFactura.darLista(pmf.getPersistenceManager());
	}

 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Factura darFacturaPorId (long id)
	{
		return sqlFactura.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	
	/* ****************************************************************
	 * 			M�todos para manejar CarritoCompras
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public CarritoCompras adicionarCarritoCompras(String estado, long consumidor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCarritoCompras.adicionar(pm, id,estado, consumidor);
            tx.commit();
            
            log.trace ("Inserci�n de CarritoCompras: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new CarritoCompras(id, estado, consumidor);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @param nombre 
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarCarritoComprasPoIdConsumidor (long con) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCarritoCompras.eliminarPorIdConsumidor(pm, con);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarCarritoComprasPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCarritoCompras.eliminarPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<CarritoCompras> darCarritosCompras ()
	{
		return sqlCarritoCompras.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<CarritoCompras> darCarritoComprasPorIdConsumidor(long con)
	{
		return sqlCarritoCompras.darPorIdConsumidor (pmf.getPersistenceManager(), con);
	}
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public CarritoCompras darCarritoComprasPorId (long id)
	{
		return sqlCarritoCompras.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M�todos para manejar ProductoVenta
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public ProductoVenta adicionarProductoVenta(long venta, long producto, int cantidadProducto, String unidadMedida)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlProductoVenta.adicionar(pm, venta, producto, cantidadProducto, unidadMedida);
            tx.commit();
            
            log.trace ("Inserci�n de ProductoVenta: " + venta + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ProductoVenta (venta, cantidadProducto, unidadMedida, producto);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarProductoVentaPorIdVenta (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoVenta.eliminarPorIdVenta(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<ProductoVenta> darProductosVentas ()
	{
		return sqlProductoVenta.darListaTodos(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<ProductoVenta> darProductoVentaPorIdVenta (long id)
	{
		return sqlProductoVenta.darListaPorIdVenta (pmf.getPersistenceManager(), id);
	}
	
	
	
	/* ****************************************************************
	 * 			M�todos para manejar ProductoCarritoCompras
	 *****************************************************************/
	
	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci�n
	  * @return El objeto adicionado. null si ocurre alguna Excepci�n
	 */
	public ProductoCarritoCompras adicionarProductoCarritoCompras(long carritoCompras, long producto, int cantidadProducto, String unidadMedida)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlProductoCarritoCompras.adicionar(pm, carritoCompras, producto, cantidadProducto, unidadMedida);
            tx.commit();
            
            log.trace ("Inserci�n de ProductoCarritoCompras: " + carritoCompras + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ProductoCarritoCompras (carritoCompras, cantidadProducto, unidadMedida, producto);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	/**
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarProductoCarritoComprasPorIdcarritoCompras (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoCarritoCompras.eliminarPorIdCarritoCompras(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<ProductoCarritoCompras> darProductoscarritoCompras ()
	{
		return sqlProductoCarritoCompras.darListaTodos(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M�todo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<ProductoCarritoCompras> darProductoCarritoComprasPorIdcarritoCompras (long id)
	{
		return sqlProductoCarritoCompras.darListaPorIdCarritoCompras (pmf.getPersistenceManager(), id);
	}
	
	
	
	
	
	
	
	
	
	private long nextval() {
		// TODO Auto-generated method stub
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
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
