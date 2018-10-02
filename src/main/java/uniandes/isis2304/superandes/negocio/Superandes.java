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
	 * 			Métodos para manejar ProductoTransaccion
	 *****************************************************************/
	/**
	 * Adiciona ProductoTransaccion de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public ProductoTransaccion adicionarProductoTransaccion (long idProducto, int cantidadVenta)
	{
        log.info ("Adicionando ProductoTransaccion: " + idProducto);
        ProductoTransaccion ProductoTransaccion = pp.adicionarProductoTransaccion (idProducto, cantidadVenta);		
        log.info ("Adicionando ProductoTransaccion: " + idProducto);
        return ProductoTransaccion;
	}
	
	/**
	 * Elimina ProductoTransaccion por su cantidad
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoTransaccionPorCantidad (int c)
	{
		log.info ("Eliminando ProductoTransaccion por cantidad: " + c);
        long resp = pp.eliminarProductoTransaccionPorCantidad (c);		
        log.info ("Eliminando ProductoTransaccion por cantidad: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina ProductoTransaccion por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoTransaccionPorId (long id)
	{
		log.info ("Eliminando ProductoTransaccion por id: " + id);
        long resp = pp.eliminarProductoTransaccionPorId (id);		
        log.info ("Eliminando ProductoTransaccion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  ProductoTransaccion en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de ProductoTransaccion, llenos con su información básica
	 */
	public List<ProductoTransaccion> darProductoTransaccion ()
	{
		log.info ("Consultando ProductoTransaccion");
        List<ProductoTransaccion> tiposBebida = pp.darProductoTransacciones();	
        log.info ("Consultando ProductoTransaccion: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra ProductoTransaccion en Superandes y los devuelve como una lista de VOProductoTransaccion
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProductoTransaccion con todos los tipos de ProductoTransaccion que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoTransaccion> darVOProductoTransacciones ()
	{
		log.info ("Generando los VO de ProductoTransaccion");        
        List<VOProductoTransaccion> voTipos = new LinkedList<VOProductoTransaccion> ();
        for (ProductoTransaccion tb : pp.darProductoTransacciones ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de ProductoTransaccion: " + voTipos.size() + " existentes");
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
	public Venta adicionarVenta (String fecha, String formaPago, double valorTotal, long factura, long consumidor)
	{
        log.info ("Adicionando Venta: " + factura);
        Venta Venta = pp.adicionarVenta (fecha, formaPago, valorTotal, factura, consumidor);		
        log.info ("Adicionando Venta: " + factura);
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
	public Factura adicionarFactura ()
	{
        log.info ("Adicionando Factura: " );
        Factura Factura = pp.adicionarFactura ();		
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
	public Promocion adicionarPromocion (String nombre, String fecI, String fecF)
	{
        log.info ("Adicionando Promocion: " + nombre);
        Promocion Promocion = pp.adicionarPromocion (nombre, fecI, fecF);		
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
	
	
	/* ****************************************************************
	 * 			Métodos para manejar PromocionSucursal
	 *****************************************************************/
	/**
	 * Adiciona PromocionSucursal de manera persistente 
	 * Adiciona entradas al log de la aplicación
	* @return El objeto. null si ocurre alguna Excepción
	 */
	public PromocionSucursal adicionarPromocionSucursal (long promocion, long sucursal)
	{
        log.info ("Adicionando PromocionSucursal: " + promocion);
        PromocionSucursal PromocionSucursal = pp.adicionarPromocionSucursal ( promocion, sucursal);		
        log.info ("Adicionando PromocionSucursal: " + promocion);
        return PromocionSucursal;
	}
	
	
	
	/**
	 * Elimina PromocionSucursal por su identificadorSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionSucursalPorSucursal (long id)
	{
		log.info ("Eliminando PromocionSucursal por idSucursal: " + id);
        long resp = pp.eliminarPromocionSucursalPorIdSucursal (id);		
        log.info ("Eliminando PromocionSucursal por idSucursal: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina PromocionSucursal por su identificadorPromocion
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionSucursalPorPromocion (long id)
	{
		log.info ("Eliminando PromocionSucursal por idPromocion: " + id);
        long resp = pp.eliminarPromocionSucursalPorIdPromocion (id);		
        log.info ("Eliminando PromocionSucursal por idPromocion: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 *	Lista  PromocionSucursal en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de PromocionSucursal, llenos con su información básica
	 */
	public List<PromocionSucursal> darPromocionSucursal ()
	{
		log.info ("Consultando PromocionSucursal");
        List<PromocionSucursal> tiposBebida = pp.darPromocionesSucursales();	
        log.info ("Consultando PromocionSucursal: " + tiposBebida.size() + " existentes");
        return tiposBebida;
	}

	/**
	 * Encuentra PromocionSucursal en Superandes y los devuelve como una lista de VOPromocionSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionSucursal con todos los tipos de PromocionSucursal que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionSucursal> darVOPromocionSucursales ()
	{
		log.info ("Generando los VO de PromocionSucursal");        
        List<VOPromocionSucursal> voTipos = new LinkedList<VOPromocionSucursal> ();
        for (PromocionSucursal tb : pp.darPromocionesSucursales ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de PromocionSucursal: " + voTipos.size() + " existentes");
        return voTipos;
	}

	/**
	 * Encuentra PromocionSucursal en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de PromocionSucursal
	 * @return Un objeto PromocionSucursal, con su información básica
	 */
	public PromocionSucursal darPromocionSucursalPorSucursal (long sucursal)
	{
		log.info ("Buscando PromocionSucursal por sucursal: " + sucursal);
		List<PromocionSucursal> tb = pp.darPromocionesSucursalPorSucursal(sucursal);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	/**
	 * Encuentra PromocionSucursal en Superandes y los devuelve como una lista de VOPromocionSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionSucursal con todos los tipos de PromocionSucursal que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionSucursal> darVOPromocionSucursalesPorSucursal (long sucursal)
	{
		log.info ("Generando los VO de PromocionSucursal");        
        List<VOPromocionSucursal> voTipos = new LinkedList<VOPromocionSucursal> ();
        for (PromocionSucursal tb : pp.darPromocionesSucursalPorSucursal(sucursal))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de PromocionSucursal: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	/**
	 * Encuentra PromocionSucursal en Superandes 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de PromocionSucursal
	 * @return Un objeto PromocionSucursal, con su información básica
	 */
	public PromocionSucursal darPromocionSucursalPorPromocion (long p)
	{
		log.info ("Buscando PromocionSucursal por Promocion: " + p);
		List<PromocionSucursal> tb = pp.darPromocionesSucursalPorSucursal(p);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
	/**
	 * Encuentra PromocionSucursal en Superandes y los devuelve como una lista de VOPromocionSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionSucursal con todos los tipos de PromocionSucursal que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionSucursal> darVOPromocionSucursalesPorPromocion (long p)
	{
		log.info ("Generando los VO de PromocionSucursal");        
        List<VOPromocionSucursal> voTipos = new LinkedList<VOPromocionSucursal> ();
        for (PromocionSucursal tb : pp.darPromocionesSucursalPorPromocion(p))
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de PromocionSucursal: " + voTipos.size() + " existentes");
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
	public Proveedores adicionarProveedores (int nit, String nombre)
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
	public long eliminarProveedoresPorId (int nit)
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
	public ProductoOfrecido adicionarProductoOfrecido (double precioProveedor, int calificacionTotal, int calidad, int cumplimiento,  long productoId, long proveedor)
	{
        log.info ("Adicionando ProductoOfrecido: " + productoId);
        ProductoOfrecido ProductoOfrecido = pp.adicionarProductoOfrecido ( precioProveedor, calificacionTotal, calidad,cumplimiento,  productoId, proveedor);		
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