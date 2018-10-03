package uniandes.isis2304.superandes.persistencia;

import java.sql.Date;
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
	
	private SQLPromocionUnidad sqlPromocionUnidad;
	
	private SQLPromocionDescuento sqlPromocionDescuento;
	
	private SQLPromocionParteDescuento sqlPromocionParteDescuento;
	
	private SQLPromocionCantidad sqlPromocionCantidad;

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
		
		sqlPromocionUnidad= new SQLPromocionUnidad(this);
		
		sqlPromocionDescuento= new SQLPromocionDescuento(this);
		
		sqlPromocionParteDescuento= new SQLPromocionParteDescuento(this);
		
		sqlPromocionCantidad= new SQLPromocionCantidad(this);

		sqlPromocionProducto= new SQLPromocionProducto(this) ;

		sqlProveedores= new SQLProveedores(this) ;

		sqlProductoOfrecido= new SQLProductoOfrecido(this) ;

		sqlOrdenPedido= new SQLOrdenPedido(this) ;
		
		sqlAdministrador= new SQLAdministrador(this);
		
		sqlAdministradorSucursal= new SQLAdministradorSucursal(this);
		
		
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
	public String darTablaPromocionDescuento ()
	{
		return tablas.get (23);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromocionParteDescuento ()
	{
		return tablas.get (24);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromocionUnidad ()
	{
		return tablas.get (25);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla
	 */
	public String darTablaPromocionCantidad ()
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
	public Ciudad adicionarCiudad(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCiudad.adicionar(pm, id,nombre);
            tx.commit();
            
            log.trace ("Inserci髇 de Ciudad: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Ciudad (id, nombre);
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
	public Sucursal adicionarSucursal ( String nombre,long tamanho, String tipoDeMercado, double ventasTotales, long idCiudad, String direccion) 
	{

		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idSucursal= nextval();
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pmf.getPersistenceManager(), idSucursal,nombre, tamanho, tipoDeMercado, ventasTotales, idCiudad,direccion);
            tx.commit();

            log.trace ("Inserci髇 de sucursal: " + idSucursal + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Sucursal (idSucursal, nombre,  tamanho, tipoDeMercado, ventasTotales, idCiudad, direccion);
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
	public List<Sucursal> darSucursales ()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar LAS BODEGAS
	 *****************************************************************/
	
	/**
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador del bodega
	 * @param cantidadProductos - la cantidad de productos en la bodega
	 * @param capacidadTotal - La capacidad de la bodega
	 * @param peso - El peso manejado en la bodega
	 * @param volumen - El volumen manejado en la bodega
	 * @param tipoProducto - El tipo de producto que maneja la bodega
	 * @param nivelDeAbastecimiento
	 * @param idSucursal
	 */
	public Bodega adicionarBodega( int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, double nivelDeReorden, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idBodega= nextval();
            long tuplasInsertadas = sqlBodega.adicionarBodega(pmf.getPersistenceManager(), idBodega, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, nivelDeReorden, idSucursal);
            tx.commit();

            log.trace ("Inserci髇 de Bodega: " + idBodega + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Bodega (idBodega, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, nivelDeReorden, idSucursal);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Bodega, dado el identificador del Bodega
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idBodega - El identificador del Bodega
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarBodegaPorId (long idBodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlBodega.eliminarBodegaPorId (pm, idBodega);
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
	 * M閠odo que consulta todas las tuplas en la tabla Bodega que tienen el identificador dado
	 * @param idBodega - El identificador del Bodega
	 * @return El objeto Bodega, construido con base en la tuplas de la tabla Bodega, que tiene el identificador dado
	 */
	public Bodega darBodegaPorId (long idBodega) 
	{
		return (Bodega) sqlBodega.darBodegaPorId (pmf.getPersistenceManager(), idBodega);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla Bodega
	 * @return La lista de objetos Bodega, construidos con base en las tuplas de la tabla Bodega
	 */
	public List<Bodega> darBodegas ()
	{
		return sqlBodega.darBodegas (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar los Estantes
	 *****************************************************************/
	
	/**
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @param cantidadProductos - la cantidad de productos en el estante
	 * @param capacidadTotal - La capacidad de el estante
	 * @param peso - El peso manejado en el estante
	 * @param volumen - El volumen manejado en el estante
	 * @param tipoProducto - El tipo de producto que maneja el estante
	 * @param equipamientoAdicional el equipamiento adicional del estante
	 * @param nivelDeAbastecimiento nivel de abastecimiento del estante
	 * @param idSucursal
	 * @return El n鷐ero de tuplas insertadas
	 */
	public Estante adicionarEstante ( int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, String equipamientoAdicional, long nivelReorden, int nivelDeAbastecimiento, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idEstante= nextval();
            long tuplasInsertadas = sqlEstante.adicionarEstante(pmf.getPersistenceManager(), idEstante, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, equipamientoAdicional,nivelReorden, nivelDeAbastecimiento, idSucursal);
            tx.commit();

            log.trace ("Inserci髇 de Estante: " + idEstante + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Estante (idEstante, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, equipamientoAdicional, nivelReorden, nivelDeAbastecimiento, idSucursal);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Estante, dado el identificador del Estante
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idEstante - El identificador del Estante
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarEstantePorId (long idEstante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEstante.eliminarEstantePorId (pm, idEstante);
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
	 * M閠odo que consulta todas las tuplas en la tabla Estante que tienen el identificador dado
	 * @param idEstante - El identificador del Estante
	 * @return El objeto Estante, construido con base en la tuplas de la tabla Estante, que tiene el identificador dado
	 */
	public Estante darEstantePorId (long idEstante) 
	{
		return (Estante) sqlEstante.darEstantePorId (pmf.getPersistenceManager(), idEstante);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla Estante
	 * @return La lista de objetos Estante, construidos con base en las tuplas de la tabla Estante
	 */
	public List<Estante> darEstantes ()
	{
		return sqlEstante.darEstantes (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar los Categorias
	 *****************************************************************/
	
	/**
	 * @param pm - El manejador de persistencia
	 * @param idCategoria - El identificador de la categoria
	 * @param nombre - El nombre de la categoria
	 * @param tipoDeAlmacenamiento - El tipo de almacenamiento de la categoria
	 * @param tipoDeManejo - El tipo de manejo de la categoria 
	 * @return EL n鷐ero de tuplas insertadas
	 */
	public Categoria adicionarCategoria ( String nombre, String tipoDeAlmacenamiento, String tipoDeManejo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idCategoria= nextval();
            long tuplasInsertadas = sqlCategoria.adicionarCategoria(pmf.getPersistenceManager(), idCategoria, nombre, tipoDeAlmacenamiento, tipoDeManejo);
            tx.commit();

            log.trace ("Inserci髇 de Categoria: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Categoria (idCategoria, nombre, tipoDeAlmacenamiento, tipoDeManejo);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Categoria, dado el nombre del Categoria
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre - El nombre del Categoria
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarCategoriaPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCategoria.eliminarCategoriaPorNombre (pm, nombre);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Categoria, dado el identificador del Categoria
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idCategoria - El identificador del Categoria
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarCategoriaPorId (long idCategoria) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCategoria.eliminarCategoriaPorId (pm, idCategoria);
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
	 * M閠odo que consulta todas las tuplas en la tabla Categoria que tienen el nombre dado
	 * @param nombreCategoria - El nombre del Categoria
	 * @return La lista de objetos Categoria, construidos con base en las tuplas de la tabla Categoria
	 */
	public List<Categoria> darCategoriasPorNombre (String nombreCategoria) 
	{
		return sqlCategoria.darCategoriasPorNombre (pmf.getPersistenceManager(), nombreCategoria);
	}
	/**
	 * M閠odo que consulta todas las tuplas en la tabla Categoria que tienen el nombre dado
	 * @param nombreCategoria - El nombre del Categoria
	 * @return La lista de objetos Categoria, construidos con base en las tuplas de la tabla Categoria
	 */
	public Categoria darCategoriasPorId (long categoria) 
	{
		return sqlCategoria.darCategoriasPorId (pmf.getPersistenceManager(), categoria);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla Categoria
	 * @return La lista de objetos Categoria, construidos con base en las tuplas de la tabla Categoria
	 */
	public List<Categoria> darCategorias ()
	{
		return sqlCategoria.darCategorias (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar los Perecederos
	 *****************************************************************/
	
	/**
	 * 
	 * @param idPerecedero - El identificador del perecedero
	 * @param fechaDeVencimiento - la fecha de vencimiento del producto perecedero
	 * @param tipoCategoriaPerecedera - el tipo de cateogoria del producto perecedero
	 * @param idCategoria
	 * @return El n鷐ero de tuplas insertadas
	 */
	public Perecedero adicionarPerecedero (Date fechaDeVencimiento, String tipoCategoriaPerecedera, long idCategoria) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPerecedero=nextval();
            long tuplasInsertadas = sqlPerecedero.adicionarPerecedero(pmf.getPersistenceManager(), idPerecedero, fechaDeVencimiento, tipoCategoriaPerecedera, idCategoria);
            tx.commit();

            log.trace ("Inserci髇 de Perecedero: " + idPerecedero + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Perecedero (idPerecedero, fechaDeVencimiento, tipoCategoriaPerecedera, idCategoria);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Perecedero, dado el identificador del Perecedero
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPerecedero - El identificador del Perecedero
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPerecederoPorId (long idPerecedero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPerecedero.eliminarPerecederoPorId (pm, idPerecedero);
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
	 * M閠odo que consulta todas las tuplas en la tabla Perecedero que tienen el identificador dado
	 * @param idPerecedero - El identificador del Perecedero
	 * @return El objeto Perecedero, construido con base en la tuplas de la tabla Perecedero, que tiene el identificador dado
	 */
	public Perecedero darPerecederoPorId (long idPerecedero) 
	{
		return (Perecedero) sqlPerecedero.darPerecederoPorId (pmf.getPersistenceManager(), idPerecedero);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla Perecedero
	 * @return La lista de objetos Perecedero, construidos con base en las tuplas de la tabla Perecedero
	 */
	public List<Perecedero> darPerecederos ()
	{
		return sqlPerecedero.darPerecederos (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar los NoPerecederos
	 *****************************************************************/
	
	/**
	 * 
	 * @param idNoPerecedero - El identificador del noPerecedero
	 * @param tipoCategoriaNoPerecedera - el tipo de cateogoria del producto noPerecedero
	 * @param idCategoria
	 * @return El n鷐ero de tuplas insertadas
	 */
	public NoPerecedero adicionarNoPerecedero ( String tipoCategoriaNoPerecedera, long idCategoria) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idNoPerecedero=nextval();
            long tuplasInsertadas = sqlNoPerecedero.adicionarNoPerecedero(pmf.getPersistenceManager(), idNoPerecedero, tipoCategoriaNoPerecedera, idCategoria);
            tx.commit();

            log.trace ("Inserci髇 de NoPerecedero: " + idNoPerecedero + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new NoPerecedero (idNoPerecedero, tipoCategoriaNoPerecedera, idCategoria);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla NoPerecedero, dado el identificador del NoPerecedero
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idNoPerecedero - El identificador del NoPerecedero
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarNoPerecederoPorId (long idNoPerecedero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlNoPerecedero.eliminarNoPerecederoPorId (pm, idNoPerecedero);
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
	 * M閠odo que consulta todas las tuplas en la tabla NoPerecedero que tienen el identificador dado
	 * @param idNoPerecedero - El identificador del NoPerecedero
	 * @return El objeto NoPerecedero, construido con base en la tuplas de la tabla NoPerecedero, que tiene el identificador dado
	 */
	public NoPerecedero darNoPerecederoPorId (long idNoPerecedero) 
	{
		return (NoPerecedero) sqlNoPerecedero.darNoPerecederoPorId (pmf.getPersistenceManager(), idNoPerecedero);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla NoPerecedero
	 * @return La lista de objetos NoPerecedero, construidos con base en las tuplas de la tabla NoPerecedero
	 */
	public List<NoPerecedero> darNoPerecederos ()
	{
		return sqlNoPerecedero.darNoPerecederos (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar los Productos
	 *****************************************************************/
	
	/**
	 * 
	 * @param idProducto - El identificador de la producto
	 * @param nombre - El nombre de la producto
	 * @param idCategoria - El identificador del tipo de categoria de la producto
	 * @param cantidad - cantidad de producto
	 * @param codigoDeBarras el codigo de barras
	 * @param especificacionDeEmpaquetado especificacion del empaquetado
	 * @param estado estado del producto
	 * @param marca marca del producto
	 * @param precioPorUnidadMedida precio por unidad medida
	 * @param precioUnitario precio unitario 
	 * @param presentacion presentacion
	 * @param unidadDeMedida unidad de medida
	 * @return EL n鷐ero de tuplas insertadas
	 */
	public Producto adicionarProducto (  String nombre, long idCategoria, int cantidad, String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida, double precioUnitario, String presentacion, String unidadDeMedida) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idProducto = nextval ();
            long tuplasInsertadas = sqlProducto.adicionarProducto(pmf.getPersistenceManager(), idProducto, nombre, idCategoria, cantidad, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, precioUnitario, presentacion, unidadDeMedida);
            tx.commit();

            log.trace ("Inserci髇 de Producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Producto (idProducto, nombre, idCategoria, cantidad, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, precioUnitario, presentacion, unidadDeMedida);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Producto, dado el nombre del Producto
	 * Adiciona entradas al log de la aplicaci髇
	 * @param nombre - El nombre del Producto
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProductoPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProducto.eliminarProductoPorNombre (pm, nombre);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Producto, dado el identificador del Producto
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idProducto - El identificador del Producto
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProductoPorId (long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProducto.eliminarProductoPorId (pm, idProducto);
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
	 * M閠odo que consulta todas las tuplas en la tabla Producto que tienen el nombre dado
	 * @param nombreProducto - El nombre del Producto
	 * @return La lista de objetos Producto, construidos con base en las tuplas de la tabla Producto
	 */
	public List<Producto> darProductosPorNombre (String nombreProducto) 
	{
		return sqlProducto.darProductosPorNombre (pmf.getPersistenceManager(), nombreProducto);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla Producto
	 * @return La lista de objetos Producto, construidos con base en las tuplas de la tabla Producto
	 */
	public List<Producto> darProductos ()
	{
		return sqlProducto.darProductos (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar la relaci髇 PRODUCTO_ESTANTE
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla PRODUCTO_ESTANTE
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idEstante - El identificador del estante
	 * @param idProducto - El identificador de la producto
	 * @return Un objeto PRODUCTO_ESTANTE con la informaci髇 dada. Null si ocurre alguna Excepci髇
	 */
	public ProductoEstante adicionarProductoEstante(long idEstante, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProductoEstante.adicionarProductoEstante (pm, idEstante, idProducto);
            tx.commit();

            log.trace ("Inserci髇 de productoEstante: [" + idEstante + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");

            return new ProductoEstante (idEstante, idProducto);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PRODUCTO_ESTANTE, dados los identificadores de promocionUnidad y bebida
	 * @param idEstante - El identificador del promocionUnidad
	 * @param idProducto - El identificador de la bebida
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProductoEstante(long idEstante, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoEstante.eliminarProductoEstante(pm, idEstante, idProducto);           
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
	 * M閠odo que consulta todas las tuplas en la tabla PRODUCTO_ESTANTE
	 * @return La lista de objetos PRODUCTO_ESTANTE, construidos con base en las tuplas de la tabla PRODUCTO_ESTANTE
	 */
	public List<ProductoEstante> darProductoEstante ()
	{
		return sqlProductoEstante.darProductoEstante (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar la relaci髇 PRODUCTO_BODEGA
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla PRODUCTO_BODEGA
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idBodega - El identificador del promocionUnidad - Debe haber un promocionUnidad con ese identificador
	 * @param idProducto - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto PRODUCTO_BODEGA con la informaci髇 dada. Null si ocurre alguna Excepci髇
	 */
	public ProductoBodega adicionarProductoBodega(long idBodega, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlProductoBodega.adicionarProductoBodega (pm, idBodega, idProducto);
            tx.commit();

            log.trace ("Inserci髇 de productoBodega: [" + idBodega + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");

            return new ProductoBodega (idBodega, idProducto);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PRODUCTO_BODEGA, dados los identificadores de promocionUnidad y bebida
	 * @param idBodega - El identificador del bodega
	 * @param idProducto - El identificador de la producto
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarProductoBodega(long idBodega, long idProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoBodega.eliminarProductoBodega(pm, idBodega, idProducto);           
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
	 * M閠odo que consulta todas las tuplas en la tabla PRODUCTO_BODEGA
	 * @return La lista de objetos PRODUCTO_BODEGA, construidos con base en las tuplas de la tabla PRODUCTO_BODEGA
	 */
	public List<ProductoBodega> darProductoBodega ()
	{
		return sqlProductoBodega.darProductoBodega (pmf.getPersistenceManager());
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
	 * 			M閠odos para manejar las PersonasNaturales
	 *****************************************************************/
	
	/**
	 * 
	 * @param idConsumidor - El identificador del Consumidor
	 * @param documentoIdentidad - documentoIdentidad de PersonaNatural
	 */
	public PersonaNatural adicionarPersonaNatural (long idConsumidor, long documentoIdentidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPersonaNatural = nextval ();
            long tuplasInsertadas = sqlPersonaNatural.adicionarPersonaNatural(pmf.getPersistenceManager(), idConsumidor, documentoIdentidad);
            tx.commit();

            log.trace ("Inserci髇 de PersonaNatural: " + idPersonaNatural + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PersonaNatural (idConsumidor, documentoIdentidad);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PersonaNatural, dado el identificador del PersonaNatural
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPersonaNatural - El identificador del PersonaNatural
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPersonaNaturalPorId (long idPersonaNatural) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPersonaNatural.eliminarPersonaNaturalPorId (pm, idPersonaNatural);
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
	 * M閠odo que consulta todas las tuplas en la tabla PersonaNatural que tienen el identificador dado
	 * @param idPersonaNatural - El identificador del PersonaNatural
	 * @return El objeto PersonaNatural, construido con base en la tuplas de la tabla PersonaNatural, que tiene el identificador dado
	 */
	public PersonaNatural darPersonaNaturalPorId (long idPersonaNatural) 
	{
		return (PersonaNatural) sqlPersonaNatural.darPersonaNaturalPorId (pmf.getPersistenceManager(), idPersonaNatural);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla PersonaNatural
	 * @return La lista de objetos PersonaNatural, construidos con base en las tuplas de la tabla PersonaNatural
	 */
	public List<PersonaNatural> darPersonasNaturales ()
	{
		return sqlPersonaNatural.darPersonasNaturales (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar las Empresas
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla Empresa
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idConsumidor - El identificador del Consumidor
	 * @param NIT - id de Empresa
	 * @param direccion direccion de Empresa
	 * @return El n鷐ero de tuplas insertadas
	 */
	public Empresa adicionarEmpresa (long idConsumidor, long nit, String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idEmpresa = nextval ();
            long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pmf.getPersistenceManager(), idConsumidor, nit, direccion);
            tx.commit();

            log.trace ("Inserci髇 de Empresa: " + idEmpresa + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Empresa (idConsumidor, nit, direccion);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla Empresa, dado el identificador del Empresa
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idEmpresa - El identificador del Empresa
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarEmpresaPorId (long idEmpresa) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEmpresa.eliminarEmpresaPorId (pm, idEmpresa);
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
	 * M閠odo que consulta todas las tuplas en la tabla Empresa que tienen el identificador dado
	 * @param idEmpresa - El identificador del Empresa
	 * @return El objeto Empresa, construido con base en la tuplas de la tabla Empresa, que tiene el identificador dado
	 */
	public Empresa darEmpresaPorId (long idEmpresa) 
	{
		return (Empresa) sqlEmpresa.darEmpresaPorId (pmf.getPersistenceManager(), idEmpresa);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla Empresa
	 * @return La lista de objetos Empresa, construidos con base en las tuplas de la tabla Empresa
	 */
	public List<Empresa> darEmpresas ()
	{
		return sqlEmpresa.darEmpresas (pmf.getPersistenceManager());
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
	public Venta adicionarVenta(String fecha, String formaPago, double valorTotal, long consumidor){
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlVenta.adicionar(pm, id, fecha, formaPago, valorTotal, consumidor);
            tx.commit();
            
            log.trace ("Inserci髇 de Venta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Venta (id, fecha, formaPago, valorTotal, consumidor);
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
	public Factura adicionarFactura(String textoFactura)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlFactura.adicionar(pm, id, textoFactura);
            tx.commit();
            
            log.trace ("Inserci髇 de Factura: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Factura (id,textoFactura);
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
	public Factura darFacturaPorIdVenta (long id)
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
	public Promocion adicionarPromocion( String nombre, String descripcion, String tipo, Date fechaInicio, Date fechaFinalizacion, String estado, int cantidadP)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPromocion.adicionar(pm, id,nombre, descripcion, tipo, fechaInicio, fechaFinalizacion, estado, cantidadP);
            tx.commit();
            
            log.trace ("Inserci髇 de Promocion: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Promocion (id,nombre,fechaInicio, fechaFinalizacion, estado,cantidadP);
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
	 * 			M閠odos para manejar los PROMOCION_UNIDAD
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla PROMOCION_UNIDAD
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocion - id de la promocion
	 * @param unidadVendidos unidad vendidos
	 * @param unidadPagados unidad pagados
	 * @return El objeto PROMOCION_UNIDAD adicionado. null si ocurre alguna Excepci髇
	 */
	public PromocionUnidad adicionarPromocionUnidad(long idPromocion, int unidadVendidos, int unidadPagados) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPromocionUnidad.adicionarPromocionUnidad(pmf.getPersistenceManager(), idPromocion, unidadVendidos, unidadPagados);
            tx.commit();

            log.trace ("Inserci髇 de promocionUnidad: " + idPromocion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromocionUnidad (idPromocion, unidadVendidos, unidadPagados);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PROMOCION_UNIDAD, dado el identificador del promocionUnidad
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocionUnidad - El identificador del promocionUnidad
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionUnidadPorId (long idPromocionUnidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocionUnidad.eliminarPromocionUnidadPorId (pm, idPromocionUnidad);
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
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_UNIDAD que tienen el identificador dado
	 * @param idPromocionUnidad - El identificador del promocionUnidad
	 * @return El objeto PROMOCION_UNIDAD, construido con base en la tuplas de la tabla PROMOCION_UNIDAD, que tiene el identificador dado
	 */
	public PromocionUnidad darPromocionUnidadPorId (long idPromocionUnidad) 
	{
		return (PromocionUnidad) sqlPromocionUnidad.darPromocionUnidadPorId (pmf.getPersistenceManager(), idPromocionUnidad);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_UNIDAD
	 * @return La lista de objetos PROMOCION_UNIDAD, construidos con base en las tuplas de la tabla PROMOCION_UNIDAD
	 */
	public List<PromocionUnidad> darPromocionesUnidad ()
	{
		return sqlPromocionUnidad.darPromocionesUnidad (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar los PROMOCION_DESCUENTO
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla PROMOCION_DESCUENTO
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocion - id de la promocion
	 * @param descuento el descuento
	 * @return El objeto PROMOCION_DESCUENTO adicionado. null si ocurre alguna Excepci髇
	 */
	public PromocionDescuento adicionarPromocionDescuento(long idPromocion, double descuento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPromocionDescuento.adicionarPromocionDescuento(pmf.getPersistenceManager(), idPromocion, descuento);
            tx.commit();

            log.trace ("Inserci髇 de promocionDescuento: " + idPromocion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromocionDescuento (idPromocion, descuento);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PROMOCION_DESCUENTO, dado el identificador del promocionDescuento
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocionDescuento - El identificador del promocionDescuento
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionDescuentoPorId (long idPromocionDescuento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocionDescuento.eliminarPromocionDescuentoPorId (pm, idPromocionDescuento);
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
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_DESCUENTO que tienen el identificador dado
	 * @param idPromocionDescuento - El identificador del promocionDescuento
	 * @return El objeto PROMOCION_DESCUENTO, construido con base en la tuplas de la tabla PROMOCION_DESCUENTO, que tiene el identificador dado
	 */
	public PromocionDescuento darPromocionDescuentoPorId (long idPromocionDescuento) 
	{
		return (PromocionDescuento) sqlPromocionDescuento.darPromocionDescuentoPorId (pmf.getPersistenceManager(), idPromocionDescuento);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_DESCUENTO
	 * @return La lista de objetos PROMOCION_DESCUENTO, construidos con base en las tuplas de la tabla PROMOCION_DESCUENTO
	 */
	public List<PromocionDescuento> darPromocionesDescuento ()
	{
		return sqlPromocionDescuento.darPromocionesDescuento (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			M閠odos para manejar los PROMOCION_PARTE_DESCUENTO
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla PROMOCION_PARTE_DESCUENTO
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocion - id de la promocion
	 * @param descuento el descuento
	 * @return El objeto PROMOCION_PARTE_DESCUENTO adicionado. null si ocurre alguna Excepci髇
	 */
	public PromocionParteDescuento adicionarPromocionParteDescuento(long idPromocion, double descuento, int unidadVendidos) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPromocionParteDescuento.adicionarPromocionParteDescuento(pmf.getPersistenceManager(), idPromocion, descuento, unidadVendidos);
            tx.commit();

            log.trace ("Inserci髇 de promocionParteDescuento: " + idPromocion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromocionParteDescuento (idPromocion, descuento, unidadVendidos);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PROMOCION_PARTE_DESCUENTO, dado el identificador del promocionParteDescuento
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocionParteDescuento - El identificador del promocionParteDescuento
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionParteDescuentoPorId (long idPromocionParteDescuento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocionParteDescuento.eliminarPromocionParteDescuentoPorId (pm, idPromocionParteDescuento);
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
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_PARTE_DESCUENTO que tienen el identificador dado
	 * @param idPromocionParteDescuento - El identificador del promocionParteDescuento
	 * @return El objeto PROMOCION_PARTE_DESCUENTO, construido con base en la tuplas de la tabla PROMOCION_PARTE_DESCUENTO, que tiene el identificador dado
	 */
	public PromocionParteDescuento darPromocionParteDescuentoPorId (long idPromocionParteDescuento) 
	{
		return (PromocionParteDescuento) sqlPromocionParteDescuento.darPromocionParteDescuentoPorId (pmf.getPersistenceManager(), idPromocionParteDescuento);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_PARTE_DESCUENTO
	 * @return La lista de objetos PROMOCION_PARTE_DESCUENTO, construidos con base en las tuplas de la tabla PROMOCION_PARTE_DESCUENTO
	 */
	public List<PromocionParteDescuento> darPromocionesParteDescuento ()
	{
		return sqlPromocionParteDescuento.darPromocionesParteDescuento (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M閠odos para manejar los PROMOCION_CANTIDAD
	 *****************************************************************/
	
	/**
	 * M閠odo que inserta, de manera transaccional, una tupla en la tabla PROMOCION_CANTIDAD
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocion - id de la promocion
	 * @param cantidadVendidos cantidad vendidos
	 * @param cantidadPagados cantidad pagados
	 * @return El objeto PROMOCION_CANTIDAD adicionado. null si ocurre alguna Excepci髇
	 */
	public PromocionCantidad adicionarPromocionCantidad(long idPromocion, int cantidadVendidos, int cantidadPagados) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPromocionCantidad.adicionarPromocionCantidad(pmf.getPersistenceManager(), idPromocion, cantidadVendidos, cantidadPagados);
            tx.commit();

            log.trace ("Inserci髇 de PromocionCantidad: " + idPromocion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromocionCantidad (idPromocion, cantidadVendidos, cantidadPagados);
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
	 * M閠odo que elimina, de manera transaccional, una tupla en la tabla PROMOCION_CANTIDAD, dado el identificador del PromocionCantidad
	 * Adiciona entradas al log de la aplicaci髇
	 * @param idPromocionCantidad - El identificador del PromocionCantidad
	 * @return El n鷐ero de tuplas eliminadas. -1 si ocurre alguna Excepci髇
	 */
	public long eliminarPromocionCantidadPorId (long idPromocionCantidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromocionCantidad.eliminarPromocionCantidadPorId (pm, idPromocionCantidad);
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
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_CANTIDAD que tienen el identificador dado
	 * @param idPromocionCantidad - El identificador del PromocionCantidad
	 * @return El objeto PROMOCION_CANTIDAD, construido con base en la tuplas de la tabla PROMOCION_CANTIDAD, que tiene el identificador dado
	 */
	public PromocionCantidad darPromocionCantidadPorId (long idPromocionCantidad) 
	{
		return (PromocionCantidad) sqlPromocionCantidad.darPromocionCantidadPorId (pmf.getPersistenceManager(), idPromocionCantidad);
	}

	/**
	 * M閠odo que consulta todas las tuplas en la tabla PROMOCION_CANTIDAD
	 * @return La lista de objetos PROMOCION_CANTIDAD, construidos con base en las tuplas de la tabla PROMOCION_CANTIDAD
	 */
	public List<PromocionCantidad> darPromocionesCantidad ()
	{
		return sqlPromocionCantidad.darPromocionesCantidad (pmf.getPersistenceManager());
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
	public Proveedores adicionarProveedores(long nit,String nombre)
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
	public long eliminarProveedoresPorNit (long nit) 
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
	public List<OrdenPedido> darOrdenPedidoPorIdProveedor (long id)
	{
		return sqlOrdenPedido.darPorIdProveedor (pmf.getPersistenceManager(), id);
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
