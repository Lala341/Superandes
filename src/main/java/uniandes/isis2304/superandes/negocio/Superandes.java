package uniandes.isis2304.superandes.negocio;

import java.sql.Date;
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
	

	
	
	/* ****************************************************************
	 * 			Métodos para manejar CIUDAD
	 *****************************************************************/
	/**
	 * Adiciona Ciudad de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Ciudad adicionarCiudad (String nombre)
	{
        log.info ("Adicionando Ciudad: " + nombre);
        Ciudad ciudad = pp.adicionarCiudad (nombre);		
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
	 * 			Métodos para manejar Fidelizacion
	 *****************************************************************/
	/**
	 * Adiciona Fidelizacion de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Fidelizacion adicionarFidelizacion (long idConsumidor, int cantidadPuntos)
	{
        log.info ("Adicionando Fidelizacion: " + idConsumidor);
        Fidelizacion Fidelizacion = pp.adicionarFidelizacion (idConsumidor,cantidadPuntos);		
        log.info ("Adicionando Fidelizacion: " +  idConsumidor);
        return Fidelizacion;
	}
	
	
	
	/**
	 * Elimina Fidelizacion por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarFidelizacionPorId (long id)
	{
		log.info ("Eliminando Fidelizacion por id: " + id);
        long resp = pp.eliminarFidelizacionPorId (id);		
        log.info ("Eliminando Fidelizacion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Fidelizacion en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Fidelizacion, llenos con su información básica
	 */
	public List<Fidelizacion> darFidelizaciones ()
	{
		log.info ("Consultando Fidelizacion");
        List<Fidelizacion> tiposBebida = pp.darFidelizaciones();	
        log.info ("Consultando Fidelizacion: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Fidelizacion en Superandes y los devuelve como una lista de VOFidelizacion
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOFidelizacion con todos los tipos de Fidelizacion que conoce la aplicación, llenos con su información básica
	 */
	public List<VOFidelizacion> darVOFidelizaciones ()
	{
		log.info ("Generando los VO de Fidelizacion");        
        List<VOFidelizacion> voTipos = new LinkedList<VOFidelizacion> ();
        for (Fidelizacion tb : pp.darFidelizaciones ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Fidelizacion: " + voTipos.size() + " existentes");
        return voTipos;
	}

	
	

	
	/* ****************************************************************
	 * 			Métodos para manejar Venta
	 *****************************************************************/
	/**
	 * Adiciona Venta de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Venta adicionarVenta (String fecha, String formaPago, double valorTotal, long consumidor)
	{
        log.info ("Adicionando Venta: " + consumidor);
        Venta Venta = pp.adicionarVenta (fecha, formaPago, valorTotal,  consumidor);		
        log.info ("Adicionando Venta: " + consumidor);
        return Venta;
	}
	
	/**
	 * Elimina Venta por su fecha
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarVentaPorFecha (String f)
	{
		log.info ("Eliminando Venta por fecha: " + f);
        long resp = pp.eliminarVentaPorFecha (f);		
        log.info ("Eliminando Venta por fecha: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina Venta por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarVentaPorId (long id)
	{
		log.info ("Eliminando Venta por id: " + id);
        long resp = pp.eliminarVentaPorId (id);		
        log.info ("Eliminando Venta por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Venta en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Venta, llenos con su información básica
	 */
	public List<Venta> darVentas ()
	{
		log.info ("Consultando Venta");
        List<Venta> tiposBebida = pp.darVentas();	
        log.info ("Consultando Venta: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Venta en Superandes y los devuelve como una lista de VOVenta
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOVenta con todos los tipos de Venta que conoce la aplicación, llenos con su información básica
	 */
	public List<VOVenta> darVOVentas ()
	{
		log.info ("Generando los VO de Venta");        
        List<VOVenta> voTipos = new LinkedList<VOVenta> ();
        for (Venta tb : pp.darVentas ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Venta: " + voTipos.size() + " existentes");
        return voTipos;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar Factura
	 *****************************************************************/
	/**
	 * Adiciona Factura de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Factura adicionarFactura (String textoFactura)
	{
        log.info ("Adicionando Factura: " );
        Factura Factura = pp.adicionarFactura (textoFactura);		
        log.info ("Adicionando Factura: " );
        return Factura;
	}
	
	
	
	/**
	 * Elimina Factura por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarFacturaPorId (long id)
	{
		log.info ("Eliminando Factura por id: " + id);
        long resp = pp.eliminarFacturaPorId (id);		
        log.info ("Eliminando Factura por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Factura en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Factura, llenos con su información básica
	 */
	public List<Factura> darFactura ()
	{
		log.info ("Consultando Factura");
        List<Factura> tiposBebida = pp.darFacturas();	
        log.info ("Consultando Factura: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Factura en Superandes y los devuelve como una lista de VOFactura
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOFactura con todos los tipos de Factura que conoce la aplicación, llenos con su información básica
	 */
	public List<VOFactura> darVOFacturaes ()
	{
		log.info ("Generando los VO de Factura");        
        List<VOFactura> voTipos = new LinkedList<VOFactura> ();
        for (Factura tb : pp.darFacturas ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Factura: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/* ****************************************************************
	 * 			Métodos para manejar CarritoCompras
	 *****************************************************************/
	/**
	 * Adiciona CarritoCompras de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public CarritoCompras adicionarCarritoCompras (String estado, long consumidor)
	{
        log.info ("Adicionando CarritoCompras: " + consumidor);
        CarritoCompras CarritoCompras = pp.adicionarCarritoCompras (estado, consumidor);		
        log.info ("Adicionando CarritoCompras: " + consumidor);
        return CarritoCompras;
	}
	
	/**
	 * Elimina CarritoCompras por idConsumidor
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCarritoComprasPorIdConsumidor (long idConsumidor)
	{
		log.info ("Eliminando CarritoCompras por idConsumidor: " + idConsumidor);
        long resp = pp.eliminarCarritoComprasPoIdConsumidor(idConsumidor);		
        log.info ("Eliminando CarritoCompras por idConsumidor: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina CarritoCompras por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCarritoComprasPorId (long id)
	{
		log.info ("Eliminando CarritoCompras por id: " + id);
        long resp = pp.eliminarCarritoComprasPorId (id);		
        log.info ("Eliminando CarritoCompras por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  CarritoCompras en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de CarritoCompras, llenos con su información básica
	 */
	public List<CarritoCompras> darCarritoCompras ()
	{
		log.info ("Consultando CarritoCompras");
        List<CarritoCompras> tiposBebida = pp.darCarritosCompras();	
        log.info ("Consultando CarritoCompras: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}
	
	/**
	 *	Lista  CarritoCompras en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de CarritoCompras, llenos con su información básica
	 */
	public List<CarritoCompras> darCarritosComprasPorIdConsumidor (long id)
	{
		log.info ("Consultando CarritoCompras");
        List<CarritoCompras> tiposBebida = pp.darCarritoComprasPorIdConsumidor(id);	
        log.info ("Consultando CarritoCompras: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra CarritoCompras en Superandes y los devuelve como una lista de VOCarritoCompras
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCarritoCompras con todos los tipos de CarritoCompras que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCarritoCompras> darVOCarritoComprasPorIdConsumidor (long id)
	{
		log.info ("Generando los VO de CarritoCompras");        
        List<VOCarritoCompras> voTipos = new LinkedList<VOCarritoCompras> ();
        for (CarritoCompras tb : pp.darCarritoComprasPorIdConsumidor(id))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de CarritoCompras: " + voTipos.size() + " existentes");
        return voTipos;
	}
	/**
	 * Encuentra CarritoCompras en Superandes y los devuelve como una lista de VOCarritoCompras
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCarritoCompras con todos los tipos de CarritoCompras que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCarritoCompras> darVOCarritoCompras ()
	{
		log.info ("Generando los VO de CarritoCompras");        
        List<VOCarritoCompras> voTipos = new LinkedList<VOCarritoCompras> ();
        for (CarritoCompras tb : pp.darCarritosCompras ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de CarritoCompras: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra CarritoCompras en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de CarritoCompras
	 * @return Un objeto CarritoCompras, con su información básica
	 */
	public CarritoCompras darCarritoComprasPorIdConsumidor (long idConsumidor)
	{
		log.info ("Buscando CarritoCompras por IdConsumidor: " + idConsumidor);
		List<CarritoCompras> tb = pp.darCarritoComprasPorIdConsumidor (idConsumidor);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar ProductoVenta
	 *****************************************************************/
	/**
	 * Adiciona ProductoVenta de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public ProductoVenta adicionarProductoVenta ( long venta, long producto, int cantidadProducto, String unidadMedida)
	{
        log.info ("Adicionando ProductoVenta: " + venta);
        ProductoVenta ProductoVenta = pp.adicionarProductoVenta ( venta,producto, cantidadProducto,unidadMedida);		
        log.info ("Adicionando ProductoVenta: " + venta);
        return ProductoVenta;
	}
	
	
	/**
	 * Elimina ProductoVenta por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoVentaPorIdVenta (long id)
	{
		log.info ("Eliminando ProductoVenta por idV: " + id);
        long resp = pp.eliminarProductoVentaPorIdVenta (id);		
        log.info ("Eliminando ProductoVenta por idV: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  ProductoVenta en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de ProductoVenta, llenos con su información básica
	 */
	public List<ProductoVenta> darProductoVenta ()
	{
		log.info ("Consultando ProductoVenta");
        List<ProductoVenta> tiposBebida = pp.darProductosVentas();	
        log.info ("Consultando ProductoVenta: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra ProductoVenta en Superandes y los devuelve como una lista de VOProductoVenta
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoVenta con todos los tipos de ProductoVenta que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoVenta> darVOProductoVentaes ()
	{
		log.info ("Generando los VO de ProductoVenta");        
        List<VOProductoVenta> voTipos = new LinkedList<VOProductoVenta> ();
        for (ProductoVenta tb : pp.darProductosVentas ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoVenta: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 *	Lista  ProductoVenta en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de ProductoVenta, llenos con su información básica
	 */
	public List<ProductoVenta> darProductosVentaPorIdVenta (long id)
	{
		log.info ("Consultando ProductoVenta");
        List<ProductoVenta> tiposBebida = pp.darProductoVentaPorIdVenta(id);	
        log.info ("Consultando ProductoVenta: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra ProductoVenta en Superandes y los devuelve como una lista de VOProductoVenta
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoVenta con todos los tipos de ProductoVenta que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoVenta> darVOProductoVentasPorIdVenta (long id)
	{
		log.info ("Generando los VO de ProductoVenta");        
        List<VOProductoVenta> voTipos = new LinkedList<VOProductoVenta> ();
        for (ProductoVenta tb : pp.darProductoVentaPorIdVenta(id))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoVenta: " + voTipos.size() + " existentes");
        return voTipos;
	}
	

	/* ****************************************************************
	 * 			Métodos para manejar ProductoCarritoCompras
	 *****************************************************************/
	/**
	 * Adiciona ProductoCarritoCompras de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public ProductoCarritoCompras adicionarProductoCarritoCompras ( long carritoCompras, long producto, int cantidadProducto, String unidadMedida)
	{
        log.info ("Adicionando ProductoCarritoCompras: " + carritoCompras);
        ProductoCarritoCompras ProductoCarritoCompras = pp.adicionarProductoCarritoCompras ( carritoCompras,producto, cantidadProducto,unidadMedida);		
        log.info ("Adicionando ProductoCarritoCompras: " + carritoCompras);
        return ProductoCarritoCompras;
	}
	
	
	/**
	 * Elimina ProductoCarritoCompras por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoCarritoComprasPorIdcarritoCompras (long id)
	{
		log.info ("Eliminando ProductoCarritoCompras por idV: " + id);
        long resp = pp.eliminarProductoCarritoComprasPorIdcarritoCompras (id);		
        log.info ("Eliminando ProductoCarritoCompras por idV: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  ProductoCarritoCompras en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de ProductoCarritoCompras, llenos con su información básica
	 */
	public List<ProductoCarritoCompras> darProductoCarritoCompras ()
	{
		log.info ("Consultando ProductoCarritoCompras");
        List<ProductoCarritoCompras> tiposBebida = pp.darProductoscarritoCompras();	
        log.info ("Consultando ProductoCarritoCompras: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra ProductoCarritoCompras en Superandes y los devuelve como una lista de VOProductoCarritoCompras
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoCarritoCompras con todos los tipos de ProductoCarritoCompras que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoCarritoCompras> darVOProductoCarritoComprases ()
	{
		log.info ("Generando los VO de ProductoCarritoCompras");        
        List<VOProductoCarritoCompras> voTipos = new LinkedList<VOProductoCarritoCompras> ();
        for (ProductoCarritoCompras tb : pp.darProductoscarritoCompras())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoCarritoCompras: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 *	Lista  ProductoCarritoCompras en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de ProductoCarritoCompras, llenos con su información básica
	 */
	public List<ProductoCarritoCompras> darProductoscarritoComprasPorIdcarritoCompras (long id)
	{
		log.info ("Consultando ProductoCarritoCompras");
        List<ProductoCarritoCompras> tiposBebida = pp.darProductoCarritoComprasPorIdcarritoCompras(id);	
        log.info ("Consultando ProductoCarritoCompras: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra ProductoCarritoCompras en Superandes y los devuelve como una lista de VOProductoCarritoCompras
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoCarritoCompras con todos los tipos de ProductoCarritoCompras que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoCarritoCompras> darVOProductoCarritoComprassPorIdcarritoCompras (long id)
	{
		log.info ("Generando los VO de ProductoCarritoCompras");        
        List<VOProductoCarritoCompras> voTipos = new LinkedList<VOProductoCarritoCompras> ();
        for (ProductoCarritoCompras tb : pp.darProductoCarritoComprasPorIdcarritoCompras(id))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoCarritoCompras: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar Promocion
	 *****************************************************************/
	/**
	 * Adiciona Promocion de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Promocion adicionarPromocion (String nombre, String descripcion , String tipo, Date fecI, Date fecF, String estado, int cantidadP)
	{
        log.info ("Adicionando Promocion: " + nombre);
        Promocion Promocion = pp.adicionarPromocion (nombre,descripcion, tipo, fecI, fecF, estado, cantidadP);		
        log.info ("Adicionando Promocion: " + nombre);
        return Promocion;
	}
	
	/**
	 * Elimina Promocion por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionPorNombre (String nombre)
	{
		log.info ("Eliminando Promocion por nombre: " + nombre);
        long resp = pp.eliminarPromocionPorNombre (nombre);		
        log.info ("Eliminando Promocion por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina Promocion por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionPorId (long id)
	{
		log.info ("Eliminando Promocion por id: " + id);
        long resp = pp.eliminarPromocionPorId (id);		
        log.info ("Eliminando Promocion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Promocion en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Promocion, llenos con su información básica
	 */
	public List<Promocion> darPromocion ()
	{
		log.info ("Consultando Promocion");
        List<Promocion> tiposBebida = pp.darPromociones();	
        log.info ("Consultando Promocion: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Promocion en Superandes y los devuelve como una lista de VOPromocion
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocion con todos los tipos de Promocion que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocion> darVOPromociones ()
	{
		log.info ("Generando los VO de Promocion");        
        List<VOPromocion> voTipos = new LinkedList<VOPromocion> ();
        for (Promocion tb : pp.darPromociones ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Promocion: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra Promocion en Superandes con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de Promocion
	 * @return Un objeto Promocion, con su información básica
	 */
	public Promocion darPromocionPorNombre (String nombre)
	{
		log.info ("Buscando Promocion por nombre: " + nombre);
		List<Promocion> tb = pp.darPromocionPorNombre (nombre);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	/**
	 * Adiciona Promocion de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public long finalizarPromocion (long id)
	{
        log.info ("finalizarPromocion: " + id);
        long Promocion = pp.finalizarPromocion (id);		
        log.info ("finalizarPromocion: " + id);
        return Promocion;
	}
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar PromocionProducto
	 *****************************************************************/
	/**
	 * Adiciona PromocionProducto de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public PromocionProducto adicionarPromocionProducto (long promocion, long Producto)
	{
        log.info ("Adicionando PromocionProducto: " + promocion);
        PromocionProducto PromocionProducto = pp.adicionarPromocionProducto( promocion, Producto);		
        log.info ("Adicionando PromocionProducto: " + promocion);
        return PromocionProducto;
	}
	
	
	
	/**
	 * Elimina PromocionProducto por su identificadorProducto
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionProductoPorProducto (long id)
	{
		log.info ("Eliminando PromocionProducto por idProducto: " + id);
        long resp = pp.eliminarPromocionProductoPorIdProducto (id);		
        log.info ("Eliminando PromocionProducto por idProducto: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina PromocionProducto por su identificadorPromocion
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionProductoPorPromocion (long id)
	{
		log.info ("Eliminando PromocionProducto por idPromocion: " + id);
        long resp = pp.eliminarPromocionProductoPorIdPromocion (id);		
        log.info ("Eliminando PromocionProducto por idPromocion: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  PromocionProducto en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de PromocionProducto, llenos con su información básica
	 */
	public List<PromocionProducto> darPromocionProducto ()
	{
		log.info ("Consultando PromocionProducto");
        List<PromocionProducto> tiposBebida = pp.darPromocionesProductoes();	
        log.info ("Consultando PromocionProducto: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra PromocionProducto en Superandes y los devuelve como una lista de VOPromocionProducto
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionProducto con todos los tipos de PromocionProducto que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionProducto> darVOPromocionProductoes ()
	{
		log.info ("Generando los VO de PromocionProducto");        
        List<VOPromocionProducto> voTipos = new LinkedList<VOPromocionProducto> ();
        for (PromocionProducto tb : pp.darPromocionesProductoes ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de PromocionProducto: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra PromocionProducto en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de PromocionProducto
	 * @return Un objeto PromocionProducto, con su información básica
	 */
	public PromocionProducto darPromocionProductoPorProducto (long Producto)
	{
		log.info ("Buscando PromocionProducto por Producto: " + Producto);
		List<PromocionProducto> tb = pp.darPromocionesProductoPorProducto(Producto);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	/**
	 * Encuentra PromocionProducto en Superandes y los devuelve como una lista de VOPromocionProducto
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionProducto con todos los tipos de PromocionProducto que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionProducto> darVOPromocionProductoesPorProducto (long Producto)
	{
		log.info ("Generando los VO de PromocionProducto");        
        List<VOPromocionProducto> voTipos = new LinkedList<VOPromocionProducto> ();
        for (PromocionProducto tb : pp.darPromocionesProductoPorProducto(Producto))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de PromocionProducto: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	/**
	 * Encuentra PromocionProducto en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de PromocionProducto
	 * @return Un objeto PromocionProducto, con su información básica
	 */
	public PromocionProducto darPromocionProductoPorPromocion (long p)
	{
		log.info ("Buscando PromocionProducto por Promocion: " + p);
		List<PromocionProducto> tb = pp.darPromocionesProductoPorProducto(p);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	/**
	 * Encuentra PromocionProducto en Superandes y los devuelve como una lista de VOPromocionProducto
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionProducto con todos los tipos de PromocionProducto que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionProducto> darVOPromocionProductoesPorPromocion (long p)
	{
		log.info ("Generando los VO de PromocionProducto");        
        List<VOPromocionProducto> voTipos = new LinkedList<VOPromocionProducto> ();
        for (PromocionProducto tb : pp.darPromocionesProductoPorPromocion(p))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de PromocionProducto: " + voTipos.size() + " existentes");
        return voTipos;
	}
	/* ****************************************************************
	 * 			Métodos para manejar Proveedores
	 *****************************************************************/
	/**
	 * Adiciona Proveedores de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Proveedores adicionarProveedores (long nit, String nombre)
	{
        log.info ("Adicionando Proveedores: " + nombre);
        Proveedores Proveedores = pp.adicionarProveedores (nit, nombre);		
        log.info ("Adicionando Proveedores: " + nombre);
        return Proveedores;
	}
	
	/**
	 * Elimina Proveedores por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedoresPorNombre (String nombre)
	{
		log.info ("Eliminando Proveedores por nombre: " + nombre);
        long resp = pp.eliminarProveedoresPorNombre (nombre);		
        log.info ("Eliminando Proveedores por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina Proveedores por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedoresPorId (long nit)
	{
		log.info ("Eliminando Proveedores por id: " + nit);
        long resp = pp.eliminarProveedoresPorNit(nit);		
        log.info ("Eliminando Proveedores por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Proveedores en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Proveedores, llenos con su información básica
	 */
	public List<Proveedores> darProveedores ()
	{
		log.info ("Consultando Proveedores");
        List<Proveedores> tiposBebida = pp.darProveedoreses();	
        log.info ("Consultando Proveedores: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Proveedores en Superandes y los devuelve como una lista de VOProveedores
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProveedores con todos los tipos de Proveedores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProveedores> darVOProveedoreses ()
	{
		log.info ("Generando los VO de Proveedores");        
        List<VOProveedores> voTipos = new LinkedList<VOProveedores> ();
        for (Proveedores tb : pp.darProveedoreses ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Proveedores: " + voTipos.size() + " existentes");
        return voTipos;
	}
	/**
	 * Encuentra Proveedores en Superandes y los devuelve como una lista de VOProveedores
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProveedores con todos los tipos de Proveedores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProducto> darVOProductos ()
	{
		log.info ("Generando los VO de Productos");        
        List<VOProducto> voTipos = new LinkedList<VOProducto> ();
        for (Producto tb : pp.darProductos())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Productos: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra Proveedores en Superandes con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de Proveedores
	 * @return Un objeto Proveedores, con su información básica
	 */
	public Proveedores darProveedoresPorNombre (String nombre)
	{
		log.info ("Buscando Proveedores por nombre: " + nombre);
		List<Proveedores> tb = pp.darProveedoresPorNombre (nombre);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	
	/**
	 * Encuentra Proveedores en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de Proveedores
	 * @return Un objeto Proveedores, con su información básica
	 */
	public Proveedores darProveedoresPor (int n)
	{
		log.info ("Buscando Proveedores por nit: " + n);
		List<Proveedores> tb = (List<Proveedores>) pp.darProveedoresPorNIT (n);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar ProductoOfrecido
	 *****************************************************************/
	/**
	 * Adiciona ProductoOfrecido de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public ProductoOfrecido adicionarProductoOfrecido (double precioProveedor, int calificacionTotal, int calidad, int cumplimiento,  long proveedor, long productoId)
	{
        log.info ("Adicionando ProductoOfrecido: " + productoId);
        ProductoOfrecido ProductoOfrecido = pp.adicionarProductoOfrecido ( precioProveedor, calificacionTotal, calidad,cumplimiento,   proveedor,productoId);		
        log.info ("Adicionando ProductoOfrecido: " + productoId);
        return ProductoOfrecido;
	}
	
	
	
	/**
	 * Elimina ProductoOfrecido por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoOfrecidoPorId (long id)
	{
		log.info ("Eliminando ProductoOfrecido por id: " + id);
        long resp = pp.eliminarProductoOfrecidoPorId (id);		
        log.info ("Eliminando ProductoOfrecido por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  ProductoOfrecido en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de ProductoOfrecido, llenos con su información básica
	 */
	public List<ProductoOfrecido> darProductoOfrecido ()
	{
		log.info ("Consultando ProductoOfrecido");
        List<ProductoOfrecido> tiposBebida = pp.darProductosOfrecidos();	
        log.info ("Consultando ProductoOfrecido: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra ProductoOfrecido en Superandes y los devuelve como una lista de VOProductoOfrecido
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoOfrecido con todos los tipos de ProductoOfrecido que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoOfrecido> darVOProductoOfrecidoes ()
	{
		log.info ("Generando los VO de ProductoOfrecido");        
        List<VOProductoOfrecido> voTipos = new LinkedList<VOProductoOfrecido> ();
        for (ProductoOfrecido tb : pp.darProductosOfrecidos ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoOfrecido: " + voTipos.size() + " existentes");
        return voTipos;
	}
	public List<VOProductoOfrecido> darVOProductoOfrecidoesPorIdProveedor (long id)
	{
		log.info ("Generando los VO de ProductoOfrecido");        
        List<VOProductoOfrecido> voTipos = new LinkedList<VOProductoOfrecido> ();
        for (ProductoOfrecido tb : pp.darProductosOfrecidosPorIdProveedor(id))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoOfrecido: " + voTipos.size() + " existentes");
        return voTipos;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar OrdenPedido
	 *****************************************************************/
	/**
	 * Adiciona OrdenPedido de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public OrdenPedido adicionarOrdenPedido (String estado, int calificacion, String fecha, String fechaEntrega, long proveedor, long productoOfrecido, double cantidadProducto, String unidadMedida, long sucursal)
	{
        log.info ("Adicionando OrdenPedido: " + proveedor);
        OrdenPedido ordenPedido = pp.adicionarOrdenPedido (estado, calificacion, fecha, fechaEntrega, proveedor, productoOfrecido, cantidadProducto, unidadMedida, sucursal);		
        log.info ("Adicionando OrdenPedido: " + proveedor);
        return ordenPedido;
	}
	
	
	
	/**
	 * Elimina OrdenPedido por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarOrdenPedidoPorId (long id)
	{
		log.info ("Eliminando OrdenPedido por id: " + id);
        long resp = pp.eliminarOrdenPedidoPorId (id);		
        log.info ("Eliminando OrdenPedido por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  OrdenPedido en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de OrdenPedido, llenos con su información básica
	 */
	public List<OrdenPedido> darOrdenPedido ()
	{
		log.info ("Consultando OrdenPedido");
        List<OrdenPedido> tiposBebida = pp.darOrdenesPedidos();	
        log.info ("Consultando OrdenPedido: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra OrdenPedido en Superandes y los devuelve como una lista de VOOrdenPedido
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOOrdenPedido con todos los tipos de OrdenPedido que conoce la aplicación, llenos con su información básica
	 */
	public List<VOOrdenPedido> darVOOrdenPedidoes ()
	{
		log.info ("Generando los VO de OrdenPedido");        
        List<VOOrdenPedido> voTipos = new LinkedList<VOOrdenPedido> ();
        for (OrdenPedido tb : pp.darOrdenesPedidos())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de OrdenPedido: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra OrdenPedido en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de OrdenPedido
	 * @return Un objeto OrdenPedido, con su información básica
	 */
	public OrdenPedido darOrdenPedidoPorId (long id)
	{
		log.info ("Buscando OrdenPedido por id: " + id);
		List<OrdenPedido> tb = (List<OrdenPedido>) pp.darOrdenPedidoPorId(id);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	public List<OrdenPedido> darOrdenPedidoPorIdProveedor (long id)
	{
		log.info ("Buscando OrdenPedido por idProveedor: " + id);
		List<OrdenPedido> tb = (List<OrdenPedido>) pp.darOrdenPedidoPorIdProveedor(id);
		return !tb.isEmpty () ? tb : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar Administrador
	 *****************************************************************/
	/**
	 * Adiciona Administrador de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public Administrador adicionarAdministrador ( int cantidadDeRecompra, String usuario,  String contrasenha)
	{
        log.info ("Adicionando Administrador: " + usuario);
        Administrador Administrador = pp.adicionarAdministrador (cantidadDeRecompra, usuario, contrasenha);		
        log.info ("Adicionando Administrador: " + usuario);
        return Administrador;
	}
	
	/**
	 * Elimina Administrador por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAdministradorPorUsuario (String nombre)
	{
		log.info ("Eliminando Administrador por nombre: " + nombre);
        long resp = pp.eliminarAdministradorPorUsuario (nombre);		
        log.info ("Eliminando Administrador por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina Administrador por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAdministradorPorId (long id)
	{
		log.info ("Eliminando Administrador por id: " + id);
        long resp = pp.eliminarAdministradorPorId (id);		
        log.info ("Eliminando Administrador por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  Administrador en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de Administrador, llenos con su información básica
	 */
	public List<Administrador> darAdministrador ()
	{
		log.info ("Consultando Administrador");
        List<Administrador> tiposBebida = pp.darAdministradores();	
        log.info ("Consultando Administrador: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra Administrador en Superandes y los devuelve como una lista de VOAdministrador
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOAdministrador con todos los tipos de Administrador que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAdministrador> darVOAdministradores ()
	{
		log.info ("Generando los VO de Administrador");        
        List<VOAdministrador> voTipos = new LinkedList<VOAdministrador> ();
        for (Administrador tb : pp.darAdministradores ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Administrador: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	/**
	 * Encuentra en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de OrdenPedido
	 * @return Un objeto OrdenPedido, con su información básica
	 */
	public Administrador darAdministradorPorId (long id)
	{
		log.info ("Buscando Administrador por id: " + id);
		List<Administrador> tb = (List<Administrador>) pp.darAdministradorPorId(id);
		return !tb.isEmpty () ? tb.get (0) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar AdministradorSucursal
	 *****************************************************************/
	/**
	 * Adiciona AdministradorSucursal de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public AdministradorSucursal adicionarAdministradorSucursal (long ad, long su)
	{
        log.info ("Adicionando AdministradorSucursal: " + ad);
        AdministradorSucursal AdministradorSucursal = pp.adicionarAdministradorSucursal (ad, su);		
        log.info ("Adicionando AdministradorSucursal: " + ad);
        return AdministradorSucursal;
	}
	
	
	
	/**
	 * Elimina AdministradorSucursal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAdministradorSucursalPorIdAdministrador (long id)
	{
		log.info ("Eliminando AdministradorSucursal por idAdmin: " + id);
        long resp = pp.eliminarAdministradorSucursalPorIdAdministrador (id);		
        log.info ("Eliminando AdministradorSucursal por idAdmin: " + resp + " tuplas eliminadas");
        return resp;
	}
	/**
	 * Elimina AdministradorSucursal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAdministradorSucursalPorIdSucursal (long id)
	{
		log.info ("Eliminando AdministradorSucursal por idSucu: " + id);
        long resp = pp.eliminarAdministradorSucursalPorIdSucursal (id);		
        log.info ("Eliminando AdministradorSucursal por idSucu: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  AdministradorSucursal en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de AdministradorSucursal, llenos con su información básica
	 */
	public List<AdministradorSucursal> darAdministradorSucursal ()
	{
		log.info ("Consultando AdministradorSucursal");
        List<AdministradorSucursal> tiposBebida = pp.darAdministradorSucursales();	
        log.info ("Consultando AdministradorSucursal: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra AdministradorSucursal en Superandes y los devuelve como una lista de VOAdministradorSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOAdministradorSucursal con todos los tipos de AdministradorSucursal que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAdministradorSucursal> darVOAdministradorSucursales ()
	{
		log.info ("Generando los VO de AdministradorSucursal");        
        List<VOAdministradorSucursal> voTipos = new LinkedList<VOAdministradorSucursal> ();
        for (AdministradorSucursal tb : pp.darAdministradorSucursales ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de AdministradorSucursal: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de OrdenPedido
	 * @return Un objeto OrdenPedido, con su información básica
	 */
	public AdministradorSucursal darAdministradorSucursalPorIdAdministrador (long id)
	{
		log.info ("Buscando AdministradorSucursal por idAdmin: " + id);
		List<AdministradorSucursal> tb = (List<AdministradorSucursal>) pp.darAdministradorSucursalPorIdAdministrador(id);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	/**
	 * Encuentra en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de OrdenPedido
	 * @return Un objeto OrdenPedido, con su información básica
	 */
	public AdministradorSucursal darAdministradorSucursalPorIdSucursal (long id)
	{
		log.info ("Buscando AdministradorSucursal por idSucu: " + id);
		List<AdministradorSucursal> tb = (List<AdministradorSucursal>) pp.darAdministradorSucursalPorIdSucursal(id);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	
	
	public List<VOEstante> darVOEstantes ()
	{
		log.info ("Generando los VO ");        
        List<VOEstante> voTipos = new LinkedList<VOEstante> ();
        for (VOEstante tb : pp.darEstantes())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO " + voTipos.size() + " existentes");
        return voTipos;
	}
	public List<VOEstante> darVOEstantesPorSucursal (long id)
	{
		log.info ("Generando los VO ");        
        List<VOEstante> voTipos = new LinkedList<VOEstante> ();
        for (VOEstante tb : pp.darEstantesPorSucursal (id))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO " + voTipos.size() + " existentes");
        return voTipos;
	}


	public List<VOBodega> darVOBodegas ()
	{
		log.info ("Generando los VO ");        
        List<VOBodega> voTipos = new LinkedList<VOBodega> ();
        for (VOBodega tb : pp.darBodegas())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO " + voTipos.size() + " existentes");
        return voTipos;
	}
	public List<VOBodega> darVOBodegasPorSucursal (long id)
	{
		log.info ("Generando los VO ");        
        List<VOBodega> voTipos = new LinkedList<VOBodega> ();
        for (VOBodega tb : pp.darBodegasPorSucursal(id))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<VOProducto> darVOProductosRPrecioUnitario (double d1, double d2)
	{
		log.info ("Generando los VO ");        
        List<VOProducto> voTipos = new LinkedList<VOProducto> ();
        for (VOProducto tb : pp.ProductosRPrecioUnitario(d1,d2))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	
	
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar Requerimientos
	 *****************************************************************/
	
	public Producto registrarProductoPerecedero(  String nombre, String categoria, int cantidad, String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida, double precioUnitario, String presentacion, String unidadDeMedida, String tipoCategoria, Date fechaDeVencimiento){
		long cat = 0;
		List<Categoria> list=pp.darCategoriasPorNombre(categoria);
		if(list.size()==0){
			cat=pp.adicionarCategoria( categoria, tipoCategoria, "").getId();
			pp.adicionarPerecedero( fechaDeVencimiento, tipoCategoria, cat);
			
		}
		else{
			
			boolean encontro=false;
			for (Categoria categoria2 : list) {
				if(categoria2.getTipoDeAlmacenamiento().equals(tipoCategoria)){
					cat=categoria2.getId();
					encontro=true;
				}
			}
			if(encontro==false){
				cat=pp.adicionarCategoria( categoria, tipoCategoria, "").getId();
				pp.adicionarPerecedero( fechaDeVencimiento, tipoCategoria, cat);
				
			}
			
		}
		log.info ("Adicionando Producto: " + nombre);
		Producto producto = pp.adicionarProducto( nombre, cat, cantidad, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, precioUnitario, presentacion, unidadDeMedida);		
        log.info ("Adicionando Producto: " + nombre);
       
        return producto;
	}
	public Producto registrarProductoNoPerecedero(  String nombre, String categoria, int cantidad, String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida, double precioUnitario, String presentacion, String unidadDeMedida, String tipoCategoria){
		long cat = 0;
		List<Categoria> list=pp.darCategoriasPorNombre(categoria);
		if(list.size()==0){
			cat=pp.adicionarCategoria( categoria, tipoCategoria, "").getId();
			pp.adicionarNoPerecedero(tipoCategoria,cat);
			
		}
		else{
			
			boolean encontro=false;
			for (Categoria categoria2 : list) {
				if(categoria2.getTipoDeAlmacenamiento().equals(tipoCategoria)){
					cat=categoria2.getId();
					encontro=true;
				}
			}
			if(encontro==false){
				cat=pp.adicionarCategoria( categoria, tipoCategoria, "").getId();
				pp.adicionarNoPerecedero(tipoCategoria,cat);
				
			}
			
		}
		log.info ("Adicionando Producto: " + nombre);
		Producto producto = pp.adicionarProducto( nombre, cat, cantidad, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, precioUnitario, presentacion, unidadDeMedida);		
        log.info ("Adicionando Producto: " + nombre);
       
        return producto;
	}
	
	public Consumidor registrarConsumidorPersonaNatural(String nombre, String correo,String tipo, long numI){
		
		Consumidor consu= pp.adicionarConsumidor(nombre, correo, tipo);
		pp.adicionarPersonaNatural(consu.getId(), numI);
		
		return consu;
		
	}
	public Consumidor registrarConsumidorEmpresa(String nombre, String correo,String tipo, long nit, String direccion){
		
		Consumidor consu= pp.adicionarConsumidor(nombre, correo, tipo);
		pp.adicionarEmpresa(consu.getId(),nit,direccion);
		
		return consu;
		
	}
	
	public Sucursal registrarSucursal(String nombre,long tamanho, String tipoDeMercado, double ventasTotales, String ciudad, String direccion){
		
		
		Sucursal sucu= pp.adicionarSucursal(nombre, tamanho, tipoDeMercado, ventasTotales, pp.darCiudadPorNombre(ciudad).get(0).getId(), direccion);
		
		return sucu;
		
	}
	/**
	 * Encuentra Ciudad en Superandes y los devuelve como una lista de VOCiudad
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCiudad con todos los tipos de Ciudad que conoce la aplicación, llenos con su información básica
	 */
	public List<VOSucursal> darVOSucursales ()
	{
		log.info ("Generando los VO de Sucursal");        
        List<VOSucursal> voTipos = new LinkedList<VOSucursal> ();
        for (Sucursal tb : pp.darSucursales ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Sucursal: " + voTipos.size() + " existentes");
        return voTipos;
	}
	public List<Sucursal> darSucursales() {
		// TODO Auto-generated method stub
		return pp.darSucursales ();
	}
	public Bodega registrarBodegaASucursal(int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, double nivelDeReorden, long sucursal){
		
		Bodega bode=pp.adicionarBodega(cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, nivelDeReorden, sucursal);
		
		return bode;
		
		
		
	}
	public Estante registrarEstanteASucursal(int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, String equipamientoAdicional,long nivelReorden, int nivelAbaste, long sucursal){
			Estante bode= pp.adicionarEstante(cantidadProductos, capacidadTotal, peso, volumen, tipoProducto,equipamientoAdicional, nivelReorden, nivelAbaste, sucursal);
	
		return bode;
	}
	public Producto darProductoPorId(long id){
		
		return pp.darProductoPorId(id);
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