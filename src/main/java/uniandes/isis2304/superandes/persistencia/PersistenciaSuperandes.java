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
	 * Logger para escribir la traza de la ejecuci贸n
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
	 * Atributo privado que es el 煤nico objeto de la clase - Patr贸n SINGLETON
	 */
	private static PersistenciaSuperandes instance;
	
	/**
	 * F谩brica de Manejadores de persistencia, para el manejo correcto de las transacciones
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
	
	private SQLSucursal sqlSucursal;
	
	private SQLBodega sqlBodega;
	
	private SQLEstante sqlEstante;
	
	private SQLCategoria sqlCategoria;
	
	private SQLPerecedero sqlPerecedero;
	
	private SQLNoPerecedero sqlNoPerecedero;
	
	private SQLProducto sqlProducto;
	
	private SQLProductoEstante sqlProductoEstante;
	
	private SQLProductoBodega sqlProductoBodega;

	private SQLConsumidor sqlConsumidor;
	
	private SQLPersonaNatural sqlPersonaNatural;
	
	private SQLEmpresa sqlEmpresa;

	private SQLFidelizacion sqlFidelizacion;


	private SQLVenta sqlVenta;

	private SQLFactura sqlFactura;

	private SQLCarritoCompras sqlCarritoCompras;

	private SQLProductoVenta sqlProductoVenta;

	private SQLProductoCarritoCompras sqlProductoCarritoCompras;

	private SQLPromocion sqlPromocion;

	private SQLPromocionProducto sqlPromocionProducto;

	private SQLProveedores sqlProveedores;

	private SQLProductoOfrecido sqlProductoOfrecido;

	private SQLOrdenPedido sqlOrdenPedido;

	private SQLAdministrador sqlAdministrador;

	private SQLAdministradorSucursal sqlAdministradorSucursal;
	
	/* ****************************************************************
	 * 			M茅todos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/
	private PersistenciaSuperandes() {
		pmf = JDOHelper.getPersistenceManagerFactory("Superandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Superandes_sequence");
		tablas.add ("CIUDAD");
		tablas.add ("SUCURSAL");
		tablas.add ("ADMINISTRADOR");
		tablas.add ("ADMINISTRADORSUCURSAL");
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
		tablas.add ("VENTA");
		tablas.add ("FACTURA");
		tablas.add ("CARRITOCOMPRAS");
		tablas.add ("PRODUCTOVENTA");
		tablas.add ("PRODUCTOCARRITOCOMPRAS");
		tablas.add ("PROMOCION");
		tablas.add ("PROMOCIONDESCUENTO");
		tablas.add ("PROMOCIONPARTEDESCUENTO");
		tablas.add ("PROMOCIONUNIDAD");
		tablas.add ("PROMOCIONCANTIDAD");
		tablas.add ("PROMOCIONPRODUCTO");
		tablas.add ("PROVEEDORES");
		tablas.add ("PRODUCTOOFRECIDO");
		tablas.add ("ORDENPEDIDO");
	}
	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patr贸n SINGLETON
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
	 * @return Retorna el 煤nico objeto PersistenciaSuperandes existente - Patr贸n SINGLETON
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
	 * @return Retorna el 煤nico objeto PersistenciaSuperandes existente - Patr贸n SINGLETON
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
	 * Cierra la conexi贸n con la base de datos
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
		
		sqlUtil= new SQLUtil(this);
		
		sqlCiudad=new SQLCiudad(this);
		
		sqlSucursal = new SQLSucursal(this);
		
		sqlBodega = new SQLBodega(this);
		
		sqlEstante = new SQLEstante(this);
		
		sqlCategoria = new SQLCategoria(this);
		
		sqlPerecedero = new SQLPerecedero(this);
		
		sqlNoPerecedero = new SQLNoPerecedero(this);

		sqlProducto = new SQLProducto(this);
		
		sqlProductoEstante = new SQLProductoEstante (this);
		
		sqlProductoBodega = new SQLProductoBodega (this);

		sqlConsumidor= new SQLConsumidor(this);
		
		sqlPersonaNatural = new SQLPersonaNatural(this);
		
		sqlEmpresa = new SQLEmpresa(this);

		sqlFidelizacion= new SQLFidelizacion(this);


		sqlVenta= new  SQLVenta(this) ;

		sqlFactura= new SQLFactura(this) ;

		sqlCarritoCompras= new SQLCarritoCompras(this) ;

		sqlProductoVenta= new SQLProductoVenta(this) ;

		sqlProductoCarritoCompras= new SQLProductoCarritoCompras(this) ;

		sqlPromocion= new SQLPromocion(this) ;

		sqlPromocionProducto= new SQLPromocionProducto(this) ;

		sqlProveedores= new SQLProveedores(this) ;

		sqlProductoOfrecido= new SQLProductoOfrecido(this) ;

		sqlOrdenPedido= new SQLOrdenPedido(this) ;
		
		
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
	public String darTablaCiudad ()
	{
		return tablas.get (1);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaSucursal ()
	{
		return tablas.get (2);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaAdministrador()
	{
		return tablas.get (3);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaAdministradorSucursal()
	{
		return tablas.get (4);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaBodega ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaEstante ()
	{
		return tablas.get (6);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaCategoria ()
	{
		return tablas.get (7);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPerecedero ()
	{
		return tablas.get (8);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaNoPerecedero ()
	{
		return tablas.get (9);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProducto ()
	{
		return tablas.get (10);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoEstante ()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoBodega ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPersonaNatural ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaEmpresa ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaConsumidor ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaFidelizacion ()
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
	public String darTablaPromoDescuento ()
	{
		return tablas.get (23);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromoParteDescuento ()
	{
		return tablas.get (24);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromoUnidad ()
	{
		return tablas.get (25);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromoCantidad ()
	{
		return tablas.get (26);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromocionProducto ()
	{
		return tablas.get (27);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProveedores ()
	{
		return tablas.get (28);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaProductoOfrecido ()
	{
		return tablas.get (29);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaOrdenPedido ()
	{
		return tablas.get (30);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los CIUDAD
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de Ciudad: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Ciudad> darCiudades ()
	{
		return sqlCiudad.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Ciudad> darCiudadPorNombre (String nombre)
	{
		return sqlCiudad.darListaPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Ciudad darCiudadPorId (long id)
	{
		return sqlCiudad.darPorId (pmf.getPersistenceManager(), id);
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar las SUCURSALES
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idSucursal - El identificador del sucursal
	 * @param tamanho - El tamanho del sucursal
	 * @param tipoDeMercado - el tipo de mercado del sucursal
	 * @param ventasTotales - Las ventas totales de la sucursal
	 * @param idCiudad
	 * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public Sucursal adicionarSucursal (long idSucursal, long tamanho, String tipoDeMercado, double ventasTotales, long idCiudad) 
	{

		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pmf.getPersistenceManager(), idSucursal, tamanho, tipoDeMercado, ventasTotales, idCiudad);
            tx.commit();

            log.trace ("Inserci髇 de sucursal: " + idSucursal + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Sucursal (idSucursal, tamanho, tipoDeMercado, ventasTotales, idCiudad);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla SUCURSAL, dado el identificador del sucursal
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idSucursal - El identificador del sucursal
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarSucursalPorId (long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursal.eliminarSucursalPorId (pm, idSucursal);
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
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Sucursal darSucursalPorId (long id)
	{
		return sqlSucursal.darSucursalPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Sucursal> darSucursalesPorId (String id)
	{
		return sqlSucursal.darSucursalesPorId(pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar los Consumidor
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de Consumidor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Consumidor> darConsumidores ()
	{
		return sqlConsumidor.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Consumidor> darConsumidorPorNombre (String nombre)
	{
		return sqlConsumidor.darListaPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Consumidor darConsumidorPorId (long id)
	{
		return sqlConsumidor.darPorId (pmf.getPersistenceManager(), id);
	}

	
	/* ****************************************************************
	 * 			M閠odos para manejar los Fidelizacion
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public Fidelizacion adicionarFidelizacion(long id,int puntos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlFidelizacion.adicionar(pm, id,puntos);
            tx.commit();
            
            log.trace ("Inserci髇 de Fidelizacion: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Fidelizacion> darFidelizaciones ()
	{
		return sqlFidelizacion.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Fidelizacion darFidelizacionPorId (long id)
	{
		return sqlFidelizacion.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar  Venta
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de Venta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Venta> darVentas ()
	{
		return sqlVenta.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Venta darVentaPorId (long id)
	{
		return sqlVenta.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar Factura
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de Factura: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Factura> darFacturas ()
	{
		return sqlFactura.darLista(pmf.getPersistenceManager());
	}

 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Factura darFacturaPorId (long id)
	{
		return sqlFactura.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar CarritoCompras
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de CarritoCompras: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<CarritoCompras> darCarritosCompras ()
	{
		return sqlCarritoCompras.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<CarritoCompras> darCarritoComprasPorIdConsumidor(long con)
	{
		return sqlCarritoCompras.darPorIdConsumidor (pmf.getPersistenceManager(), con);
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public CarritoCompras darCarritoComprasPorId (long id)
	{
		return sqlCarritoCompras.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar ProductoVenta
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de ProductoVenta: " + venta + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<ProductoVenta> darProductosVentas ()
	{
		return sqlProductoVenta.darListaTodos(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<ProductoVenta> darProductoVentaPorIdVenta (long id)
	{
		return sqlProductoVenta.darListaPorIdVenta (pmf.getPersistenceManager(), id);
	}
	
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar ProductoCarritoCompras
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
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
            
            log.trace ("Inserci髇 de ProductoCarritoCompras: " + carritoCompras + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<ProductoCarritoCompras> darProductoscarritoCompras ()
	{
		return sqlProductoCarritoCompras.darListaTodos(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<ProductoCarritoCompras> darProductoCarritoComprasPorIdcarritoCompras (long id)
	{
		return sqlProductoCarritoCompras.darListaPorIdCarritoCompras (pmf.getPersistenceManager(), id);
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar Promocion
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public Promocion adicionarPromocion( String nombre, String fechaInicio, String fechaFinalizacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPromocion.adicionar(pm, id,nombre,fechaInicio, fechaFinalizacion);
            tx.commit();
            
            log.trace ("Inserci髇 de Promocion: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Promocion (id,nombre,fechaInicio, fechaFinalizacion);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocion.eliminarPorNombre(pm, nombre);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocion.eliminarPorId(pm, id);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Promocion> darPromociones ()
	{
		return sqlPromocion.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Promocion> darPromocionPorNombre (String nombre)
	{
		return sqlPromocion.darListaPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Promocion darPromocionPorId (long id)
	{
		return sqlPromocion.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	

	/* ****************************************************************
	 * 			M閠odos para manejar PromocionProducto
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public PromocionProducto adicionarPromocionProducto(long promocion, long Producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPromocionProducto.adicionar(pm, promocion, Producto);
            tx.commit();
            
            log.trace ("Inserci髇 de PromocionProducto: " + promocion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromocionProducto (promocion, Producto);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionProductoPorIdPromocion (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocionProducto.eliminarPorIdPromocion(pm, id);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionProductoPorIdProducto (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocionProducto.eliminarPorIdProducto(pm, id);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<PromocionProducto> darPromocionesProductoes ()
	{
		return sqlPromocionProducto.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<PromocionProducto> darPromocionesProductoPorProducto (long id)
	{
		return sqlPromocionProducto.darListaPorProducto (pmf.getPersistenceManager(), id);
	}
	
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public List<PromocionProducto> darPromocionesProductoPorPromocion (long id)
	{
		return sqlPromocionProducto.darListaPorPromocion (pmf.getPersistenceManager(), id);
	}
	
	
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar Proveedores
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public Proveedores adicionarProveedores(int nit,String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProveedores.adicionar(pm, nit,nombre);
            tx.commit();
            
            log.trace ("Inserci髇 de Proveedores: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Proveedores (nit, nombre);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProveedoresPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedores.eliminarPorNombre(pm, nombre);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProveedoresPorNit (int nit) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedores.eliminarPorNIT(pm, nit);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Proveedores> darProveedoreses ()
	{
		return sqlProveedores.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<Proveedores> darProveedoresPorNombre (String nombre)
	{
		return sqlProveedores.darListaPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Proveedores darProveedoresPorNIT(int i)
	{
		return sqlProveedores.darPorNIT (pmf.getPersistenceManager(), i);
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar ProductoOfrecido
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public ProductoOfrecido adicionarProductoOfrecido( double precioProveedor, int calificacionTotal, int calidad, int cumplimiento,  long productoId, long proveedor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlProductoOfrecido.adicionar(pm, id, precioProveedor, calificacionTotal, calidad, cumplimiento, productoId, proveedor);
            tx.commit();
            
            log.trace ("Inserci髇 de ProductoOfrecido: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ProductoOfrecido (id, precioProveedor, calificacionTotal, calidad, cumplimiento, productoId, proveedor);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProductoOfrecidoPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoOfrecido.eliminarPorId(pm, id);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<ProductoOfrecido> darProductosOfrecidos ()
	{
		return sqlProductoOfrecido.darLista(pmf.getPersistenceManager());
	}
 
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public ProductoOfrecido darProductoOfrecidoPorId (long id)
	{
		return sqlProductoOfrecido.darPorId (pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar OrdenPedido
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public OrdenPedido adicionarOrdenPedido(String estado, int calificacion, String fecha, String fechaEntrega, long proveedor, long productoOfrecido, double cantidadProducto, String unidadMedida, long sucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOrdenPedido.adicionar(pm, id, estado, calificacion, fecha, fechaEntrega,proveedor, productoOfrecido, cantidadProducto, unidadMedida, sucursal);
            tx.commit();
            
            log.trace ("Inserci髇 de OrdenPedido: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OrdenPedido(  fechaEntrega, calificacion,  estado, fecha, id, proveedor, (int) cantidadProducto, unidadMedida, productoOfrecido,  sucursal);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarOrdenPedidoPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOrdenPedido.eliminarPorId(pm, id);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<OrdenPedido> darOrdenesPedidos ()
	{
		return sqlOrdenPedido.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return La lista de objetos 
	 */
	public List<OrdenPedido> darListaOrdenPedidoPorFecha (String fecha)
	{
		return sqlOrdenPedido.darListaPorFecha (pmf.getPersistenceManager(), fecha);
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public OrdenPedido darOrdenPedidoPorId (long id)
	{
		return sqlOrdenPedido.darPorId (pmf.getPersistenceManager(), id);
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar  Administrador
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public Administrador adicionarAdministrador( int cantidadDeRecompra, String usuario,  String contrasenha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlAdministrador.adicionar(pm, id, cantidadDeRecompra, usuario, contrasenha);
            tx.commit();
            
            log.trace ("Inserci髇 de Administrador: " + usuario + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Administrador (id, cantidadDeRecompra, usuario, contrasenha);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre 
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarAdministradorPorUsuario (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAdministrador.eliminarPorUsuario(pm, nombre);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarAdministradorPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAdministrador.eliminarPorId(pm, id);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<Administrador> darAdministradores ()
	{
		return sqlAdministrador.darLista(pmf.getPersistenceManager());
	}
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public Administrador darAdministradorPorId (long id)
	{
		return sqlAdministrador.darPorId (pmf.getPersistenceManager(), id);
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar  AdministradorSucursal
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla 
	 * Adiciona entradas al log de la aplicaci髇
	  * @return El objeto adicionado. null si ocurre alguna Excepci髇
	 */
	public AdministradorSucursal adicionarAdministradorSucursal(long administrador, long sucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlAdministradorSucursal.adicionar(pm,administrador, sucursal);
            tx.commit();
            
            log.trace ("Inserci髇 de AdministradorSucursal: " + administrador + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new AdministradorSucursal (administrador, sucursal);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarAdministradorSucursalPorIdAdministrador (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAdministradorSucursal.eliminarPorIdAdministrador(pm, id);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarAdministradorSucursalPorIdSucursal (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAdministradorSucursal.eliminarPorIdSucursal(pm, id);
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
	 * M閠odo que consulta todas las tuplas en la tabla
	 * @return La lista de objetos 
	 */
	public List<AdministradorSucursal> darAdministradorSucursales ()
	{
		return sqlAdministradorSucursal.darLista(pmf.getPersistenceManager());
	}
 
	
 
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public AdministradorSucursal darAdministradorSucursalPorIdAdministrador (long id)
	{
		return sqlAdministradorSucursal.darPorIdAdministrador (pmf.getPersistenceManager(), id);
	}
	/**
	 * M閠odo que consulta todas las tuplas en la tabla 
	 * @return El objeto, construido con base en las tuplas de la tabla con el identificador dado
	 */
	public AdministradorSucursal darAdministradorSucursalPorIdSucursal (long id)
	{
		return sqlAdministradorSucursal.darPorIdSucursal (pmf.getPersistenceManager(), id);
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
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
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
	private String darDetalleException(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}
}
