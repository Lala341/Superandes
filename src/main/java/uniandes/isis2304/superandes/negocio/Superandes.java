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
	 * 			Métodos para manejar los Sucursales
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Sucursal 
	 * Adiciona entradas al log de la aplicación
	 * @param idSucursal - El identificador del sucursal
	 * @param tamanho - El tamanho del sucursal
	 * @param tipoDeMercado - el tipo de mercado del sucursal
	 * @param ventasTotales - Las ventas totales de la sucursal
	 * @param idCiudad
	 * @return El objeto Sucursal adicionado. null si ocurre alguna Excepción
	 */
	public Sucursal adicionarSucursal (String nombre,long tamanho, String tipoDeMercado, double ventasTotales, long idCiudad,String direccion)
	{
        log.info ("Adicionando Sucursal: " + nombre);
        Sucursal Sucursal = pp.adicionarSucursal (nombre,tamanho, tipoDeMercado, ventasTotales, idCiudad,direccion);
        log.info ("Adicionando Sucursal: " + Sucursal);
        return Sucursal;
	}

	/**
	 * Elimina un Sucursal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idSucursal - El identificador del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarSucursalPorId (long idSucursal)
	{
        log.info ("Eliminando Sucursal por id: " + idSucursal);
        long resp = pp.eliminarSucursalPorId (idSucursal);
        log.info ("Eliminando Sucursal por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un Sucursal y su información básica, según su identificador
	 * @param idSucursal - El identificador del Sucursal buscado
	 * @return Un objeto Sucursal que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Sucursal con dicho identificador no existe
	 */
	public Sucursal darSucursalPorId (long idSucursal)
	{
        log.info ("Dar información de un Sucursal por id: " + idSucursal);
        Sucursal Sucursal = pp.darSucursalPorId (idSucursal);
        log.info ("Buscando Sucursal por Id: " + Sucursal != null ? Sucursal : "NO EXISTE");
        return Sucursal;
	}

	/**
	 * Encuentra todos los Sucursales en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Sucursal con todos las Sucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<Sucursal> darSucursales ()
	{
        log.info ("Listando Sucursales");
        List<Sucursal> Sucursales = pp.darSucursales ();	
        log.info ("Listando Sucursales: " + Sucursales.size() + " Sucursales existentes");
        return Sucursales;
	}
	
	/**
	 * Encuentra todos los Sucursales en Superandes y los devuelve como VOSucursal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOSucursal con todos las Sucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOSucursal> darVOSucursales ()
	{
        log.info ("Generando los VO de Sucursales");
         List<VOSucursal> voSucursales = new LinkedList<VOSucursal> ();
        for (Sucursal bdor : pp.darSucursales ())
        {
        	voSucursales.add (bdor);
        }
        log.info ("Generando los VO de Sucursales: " + voSucursales.size() + " Sucursales existentes");
       return voSucursales;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Bodegas
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Bodega 
	 * Adiciona entradas al log de la aplicación
	 * @param nidBodega - El identificador del bodega
	 * @param cantidadProductos - la cantidad de productos en la bodega
	 * @param capacidadTotal - La capacidad de la bodega
	 * @param peso - El peso manejado en la bodega
	 * @param volumen - El volumen manejado en la bodega
	 * @param tipoProducto - El tipo de producto que maneja la bodega
	 * @param nivelDeAbastecimiento
	 * @param idSucursal
	 * @return El objeto Bodega adicionado. null si ocurre alguna Excepción
	 */
	public Bodega adicionarBodega (String nombre, int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, double nivelDeReorden, long idSucursal)
	{
        log.info ("Adicionando Bodega: " );
        Bodega Bodega = pp.adicionarBodega (nombre, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, nivelDeReorden, idSucursal);
        log.info ("Adicionando Bodega: " + Bodega);
        return Bodega;
	}


	/**
	 * Elimina un Bodega por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idBodega - El identificador del Bodega a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarBodegaPorId (long idBodega)
	{
        log.info ("Eliminando Bodega por id: " + idBodega);
        long resp = pp.eliminarBodegaPorId (idBodega);
        log.info ("Eliminando Bodega por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un Bodega y su información básica, según su identificador
	 * @param idBodega - El identificador del Bodega buscado
	 * @return Un objeto Bodega que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Bodega con dicho identificador no existe
	 */
	public Bodega darBodegaPorId (long idBodega)
	{
        log.info ("Dar información de un Bodega por id: " + idBodega);
        Bodega Bodega = pp.darBodegaPorId (idBodega);
        log.info ("Buscando Bodega por Id: " + Bodega != null ? Bodega : "NO EXISTE");
        return Bodega;
	}

	/**
	 * Encuentra todos los Bodegas en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bodega con todos las Bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<Bodega> darBodegas ()
	{
        log.info ("Listando Bodegas");
        List<Bodega> Bodegas = pp.darBodegas ();	
        log.info ("Listando Bodegas: " + Bodegas.size() + " Bodegas existentes");
        return Bodegas;
	}
	/**
	 * Encuentra todos los Bodegas en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bodega con todos las Bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<Bodega> darBodegasPorTipo (String tipo)
	{
        log.info ("Listando Bodegas");
        List<Bodega> Bodegas = pp.darBodegasPorTipo(tipo);	
        log.info ("Listando Bodegas: " + Bodegas.size() + " Bodegas existentes");
        return Bodegas;
	}
	/**
	 * Encuentra todos los Bodegas en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bodega con todos las Bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<Bodega> darBodegasPorTipoSucursal (String tipo, long id)
	{
        log.info ("Listando Bodegas");
        List<Bodega> Bodegas = pp.darBodegasPorTipoSucursal(tipo, id);	
        log.info ("Listando Bodegas: " + Bodegas.size() + " Bodegas existentes");
        return Bodegas;
	}
	
	/**
	 * Encuentra todos los Bodegas en Superandes y los devuelve como VOBodega
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOBodega con todos las Bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOBodega> darVOBodegas ()
	{
        log.info ("Generando los VO de Bodegas");
         List<VOBodega> voBodegas = new LinkedList<VOBodega> ();
        for (Bodega bdor : pp.darBodegas ())
        {
        	voBodegas.add (bdor);
        }
        log.info ("Generando los VO de Bodegas: " + voBodegas.size() + " Bodegas existentes");
       return voBodegas;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Estantes
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Estante 
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El identificador del estante
	 * @param cantidadProductos - la cantidad de productos en el estante
	 * @param capacidadTotal - La capacidad de el estante
	 * @param peso - El peso manejado en el estante
	 * @param volumen - El volumen manejado en el estante
	 * @param tipoProducto - El tipo de producto que maneja el estante
	 * @param equipamientoAdicional el equipamiento adicional del estante
	 * @param nivelDeAbastecimiento nivel de abastecimiento del estante
	 * @param idSucursal
	 * @return El objeto Estante adicionado. null si ocurre alguna Excepción
	 */
	public Estante adicionarEstante (String nombre, int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, String equipamientoAdicional, long nivelReorden, int nivelDeAbastecimiento, long sucursal)
	{
        log.info ("Adicionando Estante: " );
        Estante Estante = pp.adicionarEstante (nombre, cantidadProductos, capacidadTotal, volumen,  peso, tipoProducto, equipamientoAdicional, nivelReorden, nivelDeAbastecimiento, sucursal);
        log.info ("Adicionando Estante: " + Estante);
        return Estante;
	}

	/**
	 * Elimina un Estante por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El identificador del Estante a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarEstantePorId (long idEstante)
	{
        log.info ("Eliminando Estante por id: " + idEstante);
        long resp = pp.eliminarEstantePorId (idEstante);
        log.info ("Eliminando Estante por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un Estante y su información básica, según su identificador
	 * @param idEstante - El identificador del Estante buscado
	 * @return Un objeto Estante que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Estante con dicho identificador no existe
	 */
	public Estante darEstantePorId (long idEstante)
	{
        log.info ("Dar información de un Estante por id: " + idEstante);
        Estante Estante = pp.darEstantePorId (idEstante);
        log.info ("Buscando Estante por Id: " + Estante != null ? Estante : "NO EXISTE");
        return Estante;
	}

	/**
	 * Encuentra todos los Estantes en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Estante con todos las Estantes que conoce la aplicación, llenos con su información básica
	 */
	public List<Estante> darEstantes ()
	{
        log.info ("Listando Estantes");
        List<Estante> Estantes = pp.darEstantes ();	
        log.info ("Listando Estantes: " + Estantes.size() + " Estantes existentes");
        return Estantes;
	}
	public List<Estante> darEstantesPorTipo (String tipo)
	{
        log.info ("Listando Estantes");
        List<Estante> Estantes = pp.darEstantesPorTipo (tipo);	
        log.info ("Listando Estantes: " + Estantes.size() + " Estantes existentes");
        return Estantes;
	}
	public List<Estante> darEstantesPorTipoSucursal (String tipo, long sucursal)
	{
        log.info ("Listando Estantes");
        List<Estante> Estantes = pp.darEstantesPorTipoSucursal (tipo, sucursal);	
        log.info ("Listando Estantes: " + Estantes.size() + " Estantes existentes");
        return Estantes;
	}
	/**
	 * Encuentra todos los Estantes en Superandes y los devuelve como VOEstante
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOEstante con todos las Estantes que conoce la aplicación, llenos con su información básica
	 */
	public List<VOEstante> darVOEstantes ()
	{
        log.info ("Generando los VO de Estantes");
         List<VOEstante> voEstantes = new LinkedList<VOEstante> ();
        for (Estante bdor : pp.darEstantes ())
        {
        	voEstantes.add (bdor);
        }
        log.info ("Generando los VO de Estantes: " + voEstantes.size() + " Estantes existentes");
       return voEstantes;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Categorias
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Categoria 
	 * Adiciona entradas al log de la aplicación
	 * @param idCategoria - El identificador de la categoria
	 * @param nombre - El nombre de la categoria
	 * @param tipoDeAlmacenamiento - El tipo de almacenamiento de la categoria
	 * @param tipoDeManejo - El tipo de manejo de la categoria 
	 * @return El objeto Categoria adicionado. null si ocurre alguna Excepción
	 */
	public Categoria adicionarCategoria (long id, String nombre, String tipoAlmacenamiento, String tipoManejo)
	{
        log.info ("Adicionando Categoria: " + nombre);
        Categoria Categoria = pp.adicionarCategoria (nombre, tipoAlmacenamiento, tipoManejo);
        log.info ("Adicionando Categoria: " + Categoria);
        return Categoria;
	}

	/**
	 * Elimina un Categoria por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Categoria a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCategoriaPorNombre (String nombre)
	{
        log.info ("Eliminando Categoria por nombre: " + nombre);
        long resp = pp.eliminarCategoriaPorNombre (nombre);
        log.info ("Eliminando Categoria por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Elimina un Categoria por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idCategoria - El identificador del Categoria a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCategoriaPorId (long idCategoria)
	{
        log.info ("Eliminando Categoria por id: " + idCategoria);
        long resp = pp.eliminarCategoriaPorId (idCategoria);
        log.info ("Eliminando Categoria por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra la información básica de los Categorias, según su nombre
	 * @param nombre - El nombre de Categoria a buscar
	 * @return Una lista de Categorias con su información básica, donde todos tienen el nombre buscado.
	 * 	La lista vacía indica que no existen Categorias con ese nombre
	 */
	public List<Categoria> darCategoriasPorNombre (String nombre)
	{
        log.info ("Dar información de Categorias por nombre: " + nombre);
        List<Categoria> Categorias = pp.darCategoriasPorNombre (nombre);
        log.info ("Dar información de Categorias por nombre: " + Categorias.size() + " Categorias con ese nombre existentes");
        return Categorias;
 	}
	
	/**
	 * Encuentra la información básica de los Categorias, según su nombre
	 * @param nombre - El nombre de Categoria a buscar
	 * @return Una lista de Categorias con su información básica, donde todos tienen el nombre buscado.
	 * 	La lista vacía indica que no existen Categorias con ese nombre
	 */
	public Categoria darCategoriasPorId (long id)
	{
        log.info ("Dar información de Categorias por id: " + id);
        return pp.darCategoriasPorId(id);
 	}

	/**
	 * Encuentra todos los Categorias en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Categoria con todos las Categorias que conoce la aplicación, llenos con su información básica
	 */
	public List<Categoria> darCategorias ()
	{
        log.info ("Listando Categorias");
        List<Categoria> Categorias = pp.darCategorias ();	
        log.info ("Listando Categorias: " + Categorias.size() + " Categorias existentes");
        return Categorias;
	}
	
	/**
	 * Encuentra todos los Categorias en Superandes y los devuelve como VOCategoria
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCategoria con todos las Categorias que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCategoria> darVOCategorias ()
	{
		log.info ("Generando los VO de Categoria");        
        List<VOCategoria> voTipos = new LinkedList<VOCategoria> ();
        for (Categoria tb : pp.darCategorias ())
        {
        	voTipos.add((VOCategoria) tb);
        }
        log.info ("Generando los VO de Categoria: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Perecederos
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Perecedero 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Perecedero
	 * @param presupuesto - El presupuesto del Perecedero (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del Perecedero
	 * @return El objeto Perecedero adicionado. null si ocurre alguna Excepción
	 */
	public Perecedero adicionarPerecedero (Date fechaDeVencimiento, String tipoCategoriaPerecedera, long idCategoria)
	{
        log.info ("Adicionando Perecedero: " );
        Perecedero Perecedero = pp.adicionarPerecedero (fechaDeVencimiento, tipoCategoriaPerecedera, idCategoria);
        log.info ("Adicionando Perecedero: " + Perecedero);
        return Perecedero;
	}

	/**
	 * Elimina un Perecedero por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPerecedero - El identificador del Perecedero a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPerecederoPorId (long idPerecedero)
	{
        log.info ("Eliminando Perecedero por id: " + idPerecedero);
        long resp = pp.eliminarPerecederoPorId (idPerecedero);
        log.info ("Eliminando Perecedero por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un Perecedero y su información básica, según su identificador
	 * @param idPerecedero - El identificador del Perecedero buscado
	 * @return Un objeto Perecedero que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Perecedero con dicho identificador no existe
	 */
	public Perecedero darPerecederoPorId (long idPerecedero)
	{
        log.info ("Dar información de un Perecedero por id: " + idPerecedero);
        Perecedero Perecedero = pp.darPerecederoPorId (idPerecedero);
        log.info ("Buscando Perecedero por Id: " + Perecedero != null ? Perecedero : "NO EXISTE");
        return Perecedero;
	}

	/**
	 * Encuentra todos los Perecederos en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Perecedero con todos las Perecederos que conoce la aplicación, llenos con su información básica
	 */
	public List<Perecedero> darPerecederos ()
	{
        log.info ("Listando Perecederos");
        List<Perecedero> Perecederos = pp.darPerecederos ();	
        log.info ("Listando Perecederos: " + Perecederos.size() + " Perecederos existentes");
        return Perecederos;
	}
	
	/**
	 * Encuentra todos los Perecederos en Superandes y los devuelve como VOPerecedero
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPerecedero con todos las Perecederos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPerecedero> darVOPerecederos ()
	{
        log.info ("Generando los VO de Perecederos");
        List<VOPerecedero> voPerecederos = new LinkedList<VOPerecedero> ();
        for (Perecedero bdor : pp.darPerecederos ())
        {
        	voPerecederos.add(bdor);
        }
        log.info ("Generando los VO de Perecederos: " + voPerecederos.size() + " Perecederos existentes");
       return voPerecederos;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los NoPerecederos
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un NoPerecedero 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del NoPerecedero
	 * @param presupuesto - El presupuesto del NoPerecedero (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del NoPerecedero
	 * @return El objeto NoPerecedero adicionado. null si ocurre alguna Excepción
	 */
	public NoPerecedero adicionarNoPerecedero (String tipoCategoriaNoPerecedera, long idCategoria)
	{
        log.info ("Adicionando NoPerecedero: ");
        NoPerecedero NoPerecedero = pp.adicionarNoPerecedero (tipoCategoriaNoPerecedera, idCategoria);
        log.info ("Adicionando NoPerecedero: " + NoPerecedero);
        return NoPerecedero;
	}

	/**
	 * Elimina un NoPerecedero por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idNoPerecedero - El identificador del NoPerecedero a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarNoPerecederoPorId (long idNoPerecedero)
	{
        log.info ("Eliminando NoPerecedero por id: " + idNoPerecedero);
        long resp = pp.eliminarNoPerecederoPorId (idNoPerecedero);
        log.info ("Eliminando NoPerecedero por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un NoPerecedero y su información básica, según su identificador
	 * @param idNoPerecedero - El identificador del NoPerecedero buscado
	 * @return Un objeto NoPerecedero que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un NoPerecedero con dicho identificador no existe
	 */
	public NoPerecedero darNoPerecederoPorId (long idNoPerecedero)
	{
        log.info ("Dar información de un NoPerecedero por id: " + idNoPerecedero);
        NoPerecedero NoPerecedero = pp.darNoPerecederoPorId (idNoPerecedero);
        log.info ("Buscando NoPerecedero por Id: " + NoPerecedero != null ? NoPerecedero : "NO EXISTE");
        return NoPerecedero;
	}
	
	/**
	 * Encuentra todos los NoPerecederos en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos NoPerecedero con todos las NoPerecederos que conoce la aplicación, llenos con su información básica
	 */
	public List<NoPerecedero> darNoPerecederos ()
	{
        log.info ("Listando NoPerecederos");
        List<NoPerecedero> NoPerecederos = pp.darNoPerecederos ();	
        log.info ("Listando NoPerecederos: " + NoPerecederos.size() + " NoPerecederos existentes");
        return NoPerecederos;
	}
	
	/**
	 * Encuentra todos los NoPerecederos en Superandes y los devuelve como VONoPerecedero
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VONoPerecedero con todos las NoPerecederos que conoce la aplicación, llenos con su información básica
	 */
	public List<VONoPerecedero> darVONoPerecederos ()
	{
        log.info ("Generando los VO de NoPerecederos");
         List<VONoPerecedero> voNoPerecederos = new LinkedList<VONoPerecedero> ();
        for (NoPerecedero bdor : pp.darNoPerecederos ())
        {
        	voNoPerecederos.add (bdor);
        }
        log.info ("Generando los VO de NoPerecederos: " + voNoPerecederos.size() + " NoPerecederos existentes");
       return voNoPerecederos;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Productos
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Producto 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Producto
	 * @param presupuesto - El presupuesto del Producto (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del Producto
	 * @return El objeto Producto adicionado. null si ocurre alguna Excepción
	 */
	public Producto adicionarProducto (String nombre, long idCategoria,  String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida, String presentacion, String unidadDeMedida)
	{
        log.info ("Adicionando Producto: " + nombre);
        Producto Producto = pp.adicionarProducto (nombre,idCategoria, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, presentacion, unidadDeMedida);
        log.info ("Adicionando Producto: " + Producto);
        return Producto;
	}

	/**
	 * Elimina un Producto por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoPorNombre (String nombre)
	{
        log.info ("Eliminando Producto por nombre: " + nombre);
        long resp = pp.eliminarProductoPorNombre (nombre);
        log.info ("Eliminando Producto por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Elimina un Producto por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idProducto - El identificador del Producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoPorId (long idProducto)
	{
        log.info ("Eliminando Producto por id: " + idProducto);
        long resp = pp.eliminarProductoPorId (idProducto);
        log.info ("Eliminando Producto por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra la información básica de los Productos, según su nombre
	 * @param nombre - El nombre de Producto a buscar
	 * @return Una lista de Productos con su información básica, donde todos tienen el nombre buscado.
	 * 	La lista vacía indica que no existen Productos con ese nombre
	 */
	public List<Producto> darProductosPorNombre (String nombre)
	{
        log.info ("Dar información de Productos por nombre: " + nombre);
        List<Producto> Productos = pp.darProductosPorNombre (nombre);
        log.info ("Dar información de Productos por nombre: " + Productos.size() + " Productos con ese nombre existentes");
        return Productos;
 	}

	/**
	 * Encuentra la información básica de los Productos, según su nombre y los devuelve como VO
	 * @param nombre - El nombre de Producto a buscar
	 * @return Una lista de Productos con su información básica, donde todos tienen el nombre buscado.
	 * 	La lista vacía indica que no existen Productos con ese nombre
	 */
	public List<VOProducto> darVOProductosPorNombre (String nombre)
	{
        log.info ("Generando VO de Productos por nombre: " + nombre);
        List<VOProducto> voProductos = new LinkedList<VOProducto> ();
       for (Producto bdor : pp.darProductosPorNombre (nombre))
       {
          	voProductos.add (bdor);
       }
       log.info ("Generando los VO de Productos: " + voProductos.size() + " Productos existentes");
      return voProductos;
 	}

	/**
	 * Encuentra todos los Productos en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Producto con todos las Productos que conoce la aplicación, llenos con su información básica
	 */
	public List<Producto> darProductos ()
	{
        log.info ("Listando Productos");
        List<Producto> Productos = pp.darProductos ();	
        log.info ("Listando Productos: " + Productos.size() + " Productos existentes");
        return Productos;
	}
	
	/**
	 * Encuentra todos los Productos en Superandes y los devuelve como VOProducto
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOProducto con todos las Productos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProducto> darVOProductos ()
	{
        log.info ("Generando los VO de Productos");
         List<VOProducto> voProductos = new LinkedList<VOProducto> ();
        for (Producto bdor : pp.darProductos ())
        {
        	voProductos.add (bdor);
        }
        log.info ("Generando los VO de Productos: " + voProductos.size() + " Productos existentes");
       return voProductos;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación ProductoEstante
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una preferencia de una bebida por un PersonaNatural
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El identificador del estante
	 * @param idProducto - El identificador de la producto
	 * @return Un objeto ProductoEstante con los valores dados
	 */
	public ProductoEstante adicionarProductoEstante (long idEstante, long idProducto, int cant)
	{
        log.info ("Adicionando ProductoEstante [" + idEstante + ", " + idProducto + "]");
        ProductoEstante resp = pp.adicionarProductoEstante (idEstante, idProducto, cant);
        log.info ("Adicionando ProductoEstante: " + resp + " tuplas insertadas");
        return resp;
	}
	
	/**
	 * Elimina de manera persistente una preferencia de una bebida por un PersonaNatural
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El identificador del estante
	 * @param idProducto - El identificador de la producto
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoEstante (long idEstante, long idProducto)
	{
        log.info ("Eliminando ProductoEstante");
        long resp = pp.eliminarProductoEstante (idEstante, idProducto);
        log.info ("Eliminando ProductoEstante: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los ProductoEstante en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoEstante con todos los ProductoEstante que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoEstante> darProductoEstante ()
	{
        log.info ("Listando ProductoEstante");
        List<ProductoEstante> ProductoEstante = pp.darProductoEstante ();	
        log.info ("Listando ProductoEstante: " + ProductoEstante.size() + " preferencias de gusto existentes");
        return ProductoEstante;
	}

	/**
	 * Encuentra todos los ProductoEstante en Superandes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoEstante con todos los ProductoEstante que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoEstante> darVOProductoEstante ()
	{
		log.info ("Generando los VO de ProductoEstante");
		List<VOProductoEstante> voProductoEstante = new LinkedList<VOProductoEstante> ();
		for (VOProductoEstante productoEstante: pp.darProductoEstante ())
		{
			voProductoEstante.add (productoEstante);
		}
		log.info ("Generando los VO de ProductoEstante: " + voProductoEstante.size () + " ProductoEstante existentes");
		return voProductoEstante;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación ProductoBodega
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una preferencia de una bebida por un PersonaNatural
	 * Adiciona entradas al log de la aplicación
	 * @param idBodega - El identificador del bodega
	 * @param idProducto - El identificador de la producto
	 * @return Un objeto ProductoBodega con los valores dados
	 */
	public ProductoBodega adicionarProductoBodega (long idBodega, long idProducto, int cant)
	{
        log.info ("Adicionando ProductoBodega [" + idBodega + ", " + idProducto + "]");
        ProductoBodega resp = pp.adicionarProductoBodega (idBodega, idProducto, cant);
        log.info ("Adicionando ProductoBodega: " + resp + " tuplas insertadas");
        return resp;
	}
	
	/**
	 * Elimina de manera persistente una preferencia de una bebida por un PersonaNatural
	 * Adiciona entradas al log de la aplicación
	 * @param idBodega - El identificador del bodega
	 * @param idProducto - El identificador de la producto
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoBodega (long idBodega, long idProducto)
	{
        log.info ("Eliminando ProductoBodega");
        long resp = pp.eliminarProductoBodega (idBodega, idProducto);
        log.info ("Eliminando ProductoBodega: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los ProductoBodega en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoBodega con todos los ProductoBodega que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoBodega> darProductoBodega ()
	{
        log.info ("Listando ProductoBodega");
        List<ProductoBodega> ProductoBodega = pp.darProductoBodega ();	
        log.info ("Listando ProductoBodega: " + ProductoBodega.size() + " preferencias de gusto existentes");
        return ProductoBodega;
	}

	/**
	 * Encuentra todos los ProductoBodega en Superandes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoBodega con todos los ProductoBodega que conoce la aplicación, llenos con su información básica
	 */
	public List<VOProductoBodega> darVOProductoBodega ()
	{
		log.info ("Generando los VO de ProductoBodega");
		List<VOProductoBodega> voProductoBodega = new LinkedList<VOProductoBodega> ();
		for (ProductoBodega productoBodega: pp.darProductoBodega())
		{
			voProductoBodega.add (productoBodega);
		}
		log.info ("Generando los VO de ProductoBodega: " + voProductoBodega.size () + " ProductoBodega existentes");
		return voProductoBodega;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PersonasNaturales
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un PersonaNatural 
	 * Adiciona entradas al log de la aplicación
	 * @param idConsumidor - El identificador del Consumidor
	 * @param documentoIdentidad - documentoIdentidad de PersonaNatural
	 * @return El objeto PersonaNatural adicionado. null si ocurre alguna Excepción
	 */
	public PersonaNatural adicionarPersonaNatural (long idConsumidor, long documentoIdentidad)
	{
        log.info ("Adicionando PersonaNatural: ");
        PersonaNatural PersonaNatural = pp.adicionarPersonaNatural (idConsumidor, documentoIdentidad);
        log.info ("Adicionando PersonaNatural: " + PersonaNatural);
        return PersonaNatural;
	}

	/**
	 * Elimina un PersonaNatural por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPersonaNatural - El identificador del PersonaNatural a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPersonaNaturalPorId (long idPersonaNatural)
	{
        log.info ("Eliminando PersonaNatural por id: " + idPersonaNatural);
        long resp = pp.eliminarPersonaNaturalPorId (idPersonaNatural);
        log.info ("Eliminando PersonaNatural por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un PersonaNatural y su información básica, según su identificador
	 * @param idPersonaNatural - El identificador del PersonaNatural buscado
	 * @return Un objeto PersonaNatural que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un PersonaNatural con dicho identificador no existe
	 */
	public PersonaNatural darPersonaNaturalPorId (long idPersonaNatural)
	{
        log.info ("Dar información de un PersonaNatural por id: " + idPersonaNatural);
        PersonaNatural PersonaNatural = pp.darPersonaNaturalPorId (idPersonaNatural);
        log.info ("Buscando PersonaNatural por Id: " + PersonaNatural != null ? PersonaNatural : "NO EXISTE");
        return PersonaNatural;
	}

	/**
	 * Encuentra todos los PersonasNaturales en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PersonaNatural con todos las PersonasNaturales que conoce la aplicación, llenos con su información básica
	 */
	public List<PersonaNatural> darPersonasNaturales ()
	{
        log.info ("Listando PersonasNaturales");
        List<PersonaNatural> PersonasNaturales = pp.darPersonasNaturales ();	
        log.info ("Listando PersonasNaturales: " + PersonasNaturales.size() + " PersonasNaturales existentes");
        return PersonasNaturales;
	}
	
	/**
	 * Encuentra todos los PersonasNaturales en Superandes y los devuelve como VOPersonaNatural
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPersonaNatural con todos las PersonasNaturales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPersonaNatural> darVOPersonasNaturales ()
	{
        log.info ("Generando los VO de PersonasNaturales");
         List<VOPersonaNatural> voPersonasNaturales = new LinkedList<VOPersonaNatural> ();
        for (PersonaNatural bdor : pp.darPersonasNaturales ())
        {
        	voPersonasNaturales.add (bdor);
        }
        log.info ("Generando los VO de PersonasNaturales: " + voPersonasNaturales.size() + " PersonasNaturales existentes");
       return voPersonasNaturales;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los EMPRESAS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un empresa 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del empresa
	 * @param presupuesto - El presupuesto del empresa (ALTO, MEDIO, BAJO)
	 * @param ciudad - La ciudad del empresa
	 * @return El objeto PROMOCION_PARTE_DESCUENTO adicionado. null si ocurre alguna Excepción
	 */
	public Empresa adicionarEmpresa (long idConsumidor, long nit, String direccion)
	{
        log.info ("Adicionando empresa: " );
        Empresa empresa = pp.adicionarEmpresa (idConsumidor, nit, direccion);
        log.info ("Adicionando empresa: " + empresa);
        return empresa;
	}


	/**
	 * Elimina un empresa por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idEmpresa - El identificador del empresa a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarEmpresaPorId (long idEmpresa)
	{
        log.info ("Eliminando empresa por id: " + idEmpresa);
        long resp = pp.eliminarEmpresaPorId (idEmpresa);
        log.info ("Eliminando empresa por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un empresa y su información básica, según su identificador
	 * @param idEmpresa - El identificador del empresa buscado
	 * @return Un objeto Empresa que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un empresa con dicho identificador no existe
	 */
	public Empresa darEmpresaPorId (long idEmpresa)
	{
        log.info ("Dar información de un empresa por id: " + idEmpresa);
        Empresa empresa = pp.darEmpresaPorId (idEmpresa);
        log.info ("Buscando empresa por Id: " + empresa != null ? empresa : "NO EXISTE");
        return empresa;
	}

	/**
	 * Encuentra todos los empresas en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Empresa con todos las empresas que conoce la aplicación, llenos con su información básica
	 */
	public List<Empresa> darEmpresas ()
	{
        log.info ("Listando Empresas");
        List<Empresa> empresas = pp.darEmpresas ();	
        log.info ("Listando Empresas: " + empresas.size() + " empresas existentes");
        return empresas;
	}
	
	/**
	 * Encuentra todos los empresas en Superandes y los devuelve como VOEmpresa
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOEmpresa con todos las empresas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOEmpresa> darVOEmpresas ()
	{
        log.info ("Generando los VO de Empresas");
         List<VOEmpresa> voEmpresas = new LinkedList<VOEmpresa> ();
        for (Empresa bdor : pp.darEmpresas ())
        {
        	voEmpresas.add (bdor);
        }
        log.info ("Generando los VO de Empresas: " + voEmpresas.size() + " empresas existentes");
       return voEmpresas;
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
	public List<Consumidor> darConsumidores ()
	{
		return pp.darConsumidores ();
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
	public CarritoCompras adicionarCarritoCompras ( long consumidor)
	{
        log.info ("Adicionando CarritoCompras: " + consumidor);
        CarritoCompras CarritoCompras = pp.adicionarCarritoCompras (consumidor);		
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
	 * 			Métodos para manejar los PROMOCION_UNIDAD
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un promocionUnidad 
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - id de la promocion
	 * @param unidadVendidos unidad vendidos
	 * @param unidadPagados unidad pagados
	 * @return El objeto PROMOCION_PARTE_DESCUENTO adicionado. null si ocurre alguna Excepción
	 */
	public PromocionUnidad adicionarPromocionUnidad (long idPromocion, int unidadVendidos, int unidadPagados)
	{
        log.info ("Adicionando promocionUnidad: ");
        PromocionUnidad promocionUnidad = pp.adicionarPromocionUnidad (idPromocion, unidadVendidos, unidadPagados);
        log.info ("Adicionando promocionUnidad: " + promocionUnidad);
        return promocionUnidad;
	}

	/**
	 * Elimina un promocionUnidad por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocionUnidad - El identificador del promocionUnidad a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionUnidadPorId (long idPromocionUnidad)
	{
        log.info ("Eliminando promocionUnidad por id: " + idPromocionUnidad);
        long resp = pp.eliminarPromocionUnidadPorId (idPromocionUnidad);
        log.info ("Eliminando promocionUnidad por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un promocionUnidad y su información básica, según su identificador
	 * @param idPromocionUnidad - El identificador del promocionUnidad buscado
	 * @return Un objeto PromocionUnidad que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un promocionUnidad con dicho identificador no existe
	 */
	public PromocionUnidad darPromocionUnidadPorId (long idPromocionUnidad)
	{
        log.info ("Dar información de un promocionUnidad por id: " + idPromocionUnidad);
        PromocionUnidad promocionUnidad = pp.darPromocionUnidadPorId (idPromocionUnidad);
        log.info ("Buscando promocionUnidad por Id: " + promocionUnidad != null ? promocionUnidad : "NO EXISTE");
        return promocionUnidad;
	}

	/**
	 * Encuentra todos los promocionesUnidad en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromocionUnidad con todos las promocionesUnidad que conoce la aplicación, llenos con su información básica
	 */
	public List<PromocionUnidad> darPromocionesUnidad ()
	{
        log.info ("Listando PromocionesUnidad");
        List<PromocionUnidad> promocionesUnidad = pp.darPromocionesUnidad ();	
        log.info ("Listando PromocionesUnidad: " + promocionesUnidad.size() + " promocionesUnidad existentes");
        return promocionesUnidad;
	}
	
	/**
	 * Encuentra todos los promocionesUnidad en Superandes y los devuelve como VOPromocionUnidad
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionUnidad con todos las promocionesUnidad que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionUnidad> darVOPromocionesUnidad ()
	{
        log.info ("Generando los VO de PromocionesUnidad");
         List<VOPromocionUnidad> voPromocionesUnidad = new LinkedList<VOPromocionUnidad> ();
        for (PromocionUnidad bdor : pp.darPromocionesUnidad ())
        {
        	voPromocionesUnidad.add((VOPromocionUnidad) bdor);
        }
        log.info ("Generando los VO de PromocionesUnidad: " + voPromocionesUnidad.size() + " promocionesUnidad existentes");
       return voPromocionesUnidad;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PROMOCION_DESCUENTO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un promocionDescuento 
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - id de la promocion
	 * @param descuento el descuento
	 * @return El objeto PROMOCION_PARTE_DESCUENTO adicionado. null si ocurre alguna Excepción
	 */
	public PromocionDescuento adicionarPromocionDescuento (long idPromocion, double descuento)
	{
        log.info ("Adicionando promocionDescuento: ");
        PromocionDescuento promocionDescuento = pp.adicionarPromocionDescuento (idPromocion, descuento);
        log.info ("Adicionando promocionDescuento: " + promocionDescuento);
        return promocionDescuento;
	}

	/**
	 * Elimina un promocionDescuento por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocionDescuento - El identificador del promocionDescuento a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionDescuentoPorId (long idPromocionDescuento)
	{
        log.info ("Eliminando promocionDescuento por id: " + idPromocionDescuento);
        long resp = pp.eliminarPromocionDescuentoPorId (idPromocionDescuento);
        log.info ("Eliminando promocionDescuento por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un promocionDescuento y su información básica, según su identificador
	 * @param idPromocionDescuento - El identificador del promocionDescuento buscado
	 * @return Un objeto PromocionDescuento que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un promocionDescuento con dicho identificador no existe
	 */
	public PromocionDescuento darPromocionDescuentoPorId (long idPromocionDescuento)
	{
        log.info ("Dar información de un promocionDescuento por id: " + idPromocionDescuento);
        PromocionDescuento promocionDescuento = pp.darPromocionDescuentoPorId (idPromocionDescuento);
        log.info ("Buscando promocionDescuento por Id: " + promocionDescuento != null ? promocionDescuento : "NO EXISTE");
        return promocionDescuento;
	}

	/**
	 * Encuentra todos los promocionDescuento en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromocionDescuento con todos las promocionDescuento que conoce la aplicación, llenos con su información básica
	 */
	public List<PromocionDescuento> darPromocionesDescuento ()
	{
        log.info ("Listando PromocionesDescuento");
        List<PromocionDescuento> promocionDescuento = pp.darPromocionesDescuento ();	
        log.info ("Listando PromocionesDescuento: " + promocionDescuento.size() + " promocionDescuento existentes");
        return promocionDescuento;
	}
	
	/**
	 * Encuentra todos los promocionDescuento en Superandes y los devuelve como VOPromocionDescuento
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionDescuento con todos las promocionDescuento que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionDescuento> darVOPromocionesDescuento ()
	{
        log.info ("Generando los VO de PromocionesDescuento");
         List<VOPromocionDescuento> voPromocionesDescuento = new LinkedList<VOPromocionDescuento> ();
        for (PromocionDescuento bdor : pp.darPromocionesDescuento ())
        {
        	voPromocionesDescuento.add (bdor);
        }
        log.info ("Generando los VO de PromocionesDescuento: " + voPromocionesDescuento.size() + " promocionDescuento existentes");
       return voPromocionesDescuento;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PROMOCION_PARTE_DESCUENTO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un promocionParteDescuento 
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - id de la promocion
	 * @param descuento el descuento
	 * @return El objeto PROMOCION_PARTE_DESCUENTO adicionado. null si ocurre alguna Excepción
	 */
	public PromocionParteDescuento adicionarPromocionParteDescuento (long idPromocion, double descuento, int unidadVendidos)
	{
        log.info ("Adicionando promocionParteDescuento: " );
        PromocionParteDescuento promocionParteDescuento = pp.adicionarPromocionParteDescuento (idPromocion, descuento, unidadVendidos);
        log.info ("Adicionando promocionParteDescuento: " + promocionParteDescuento);
        return promocionParteDescuento;
	}

	/**
	 * Elimina un promocionParteDescuento por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocionParteDescuento - El identificador del promocionParteDescuento a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionParteDescuentoPorId (long idPromocionParteDescuento)
	{
        log.info ("Eliminando promocionParteDescuento por id: " + idPromocionParteDescuento);
        long resp = pp.eliminarPromocionParteDescuentoPorId (idPromocionParteDescuento);
        log.info ("Eliminando promocionParteDescuento por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un promocionParteDescuento y su información básica, según su identificador
	 * @param idPromocionParteDescuento - El identificador del promocionParteDescuento buscado
	 * @return Un objeto PromocionParteDescuento que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un promocionParteDescuento con dicho identificador no existe
	 */
	public PromocionParteDescuento darPromocionParteDescuentoPorId (long idPromocionParteDescuento)
	{
        log.info ("Dar información de un promocionParteDescuento por id: " + idPromocionParteDescuento);
        PromocionParteDescuento promocionParteDescuento = pp.darPromocionParteDescuentoPorId (idPromocionParteDescuento);
        log.info ("Buscando promocionParteDescuento por Id: " + promocionParteDescuento != null ? promocionParteDescuento : "NO EXISTE");
        return promocionParteDescuento;
	}

	/**
	 * Encuentra todos los promocionesParteDescuento en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromocionParteDescuento con todos las promocionesParteDescuento que conoce la aplicación, llenos con su información básica
	 */
	public List<PromocionParteDescuento> darPromocionesParteDescuento ()
	{
        log.info ("Listando PromocionesParteDescuento");
        List<PromocionParteDescuento> promocionesParteDescuento = pp.darPromocionesParteDescuento ();	
        log.info ("Listando PromocionesParteDescuento: " + promocionesParteDescuento.size() + " promocionesParteDescuento existentes");
        return promocionesParteDescuento;
	}
	
	/**
	 * Encuentra todos los promocionesParteDescuento en Superandes y los devuelve como VOPromocionParteDescuento
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionParteDescuento con todos las promocionesParteDescuento que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionParteDescuento> darVOPromocionesParteDescuento ()
	{
        log.info ("Generando los VO de PromocionesParteDescuento");
         List<VOPromocionParteDescuento> voPromocionesParteDescuento = new LinkedList<VOPromocionParteDescuento> ();
        for (PromocionParteDescuento bdor : pp.darPromocionesParteDescuento ())
        {
        	voPromocionesParteDescuento.add (bdor);
        }
        log.info ("Generando los VO de PromocionesParteDescuento: " + voPromocionesParteDescuento.size() + " promocionesParteDescuento existentes");
       return voPromocionesParteDescuento;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PROMOCION_CANTIDADES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un promocionCantidad 
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - id de la promocion
	 * @param cantidadVendidos cantidad vendidos
	 * @param cantidadPagados cantidad pagados
	 * @return El objeto PROMOCION_CANTIDAD adicionado. null si ocurre alguna Excepción
	 */
	public PromocionCantidad adicionarPromocionCantidad (long idPromocion, int cantidadVendidos, int cantidadPagados)
	{
        log.info ("Adicionando promocionCantidad: ");
        PromocionCantidad promocionCantidad = pp.adicionarPromocionCantidad (idPromocion, cantidadVendidos, cantidadPagados);
        log.info ("Adicionando promocionCantidad: " + promocionCantidad);
        return promocionCantidad;
	}

	/**
	 * Elimina un promocionCantidad por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocionCantidad - El identificador del promocionCantidad a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionCantidadPorId (long idPromocionCantidad)
	{
        log.info ("Eliminando promocionCantidad por id: " + idPromocionCantidad);
        long resp = pp.eliminarPromocionCantidadPorId (idPromocionCantidad);
        log.info ("Eliminando promocionCantidad por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un promocionCantidad y su información básica, según su identificador
	 * @param idPromocionCantidad - El identificador del promocionCantidad buscado
	 * @return Un objeto PromocionCantidad que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un promocionCantidad con dicho identificador no existe
	 */
	public PromocionCantidad darPromocionCantidadPorId (long idPromocionCantidad)
	{
        log.info ("Dar información de un promocionCantidad por id: " + idPromocionCantidad);
        PromocionCantidad promocionCantidad = pp.darPromocionCantidadPorId (idPromocionCantidad);
        log.info ("Buscando promocionCantidad por Id: " + promocionCantidad != null ? promocionCantidad : "NO EXISTE");
        return promocionCantidad;
	}

	/**
	 * Encuentra todos los promocionesCantidad en Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromocionCantidad con todos las promocionesCantidad que conoce la aplicación, llenos con su información básica
	 */
	public List<PromocionCantidad> darPromocionesCantidad ()
	{
        log.info ("Listando PromocionesCantidad");
        List<PromocionCantidad> promocionesCantidad = pp.darPromocionesCantidad ();	
        log.info ("Listando PromocionesCantidad: " + promocionesCantidad.size() + " promocionesCantidad existentes");
        return promocionesCantidad;
	}
	
	/**
	 * Encuentra todos los promocionesCantidad en Superandes y los devuelve como VOPromocionCantidad
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPromocionCantidad con todos las promocionesCantidad que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPromocionCantidad> darVOPromocionesCantidad ()
	{
        log.info ("Generando los VO de PromocionesCantidad");
         List<VOPromocionCantidad> voPromocionesCantidad = new LinkedList<VOPromocionCantidad> ();
        for (PromocionCantidad bdor : pp.darPromocionesCantidad ())
        {
        	voPromocionesCantidad.add (bdor);
        }
        log.info ("Generando los VO de PromocionesCantidad: " + voPromocionesCantidad.size() + " promocionesCantidad existentes");
       return voPromocionesCantidad;
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
	
	public boolean actualizadoOrdenPedido(long idPedido)
	{
		List<OrdenPedido> pedidos= pp.darOrdenesPedidos();
		boolean existePedido = false;
		VOOrdenPedido pedido = null;
		for (int i = 0; i < pedidos.size() && !existePedido; i++) {
			if (pedidos.get(i).getId() == (idPedido)) {
			existePedido = true;
			pedido = pedidos.get(i);
			}
		}
		pedido.setEstado("Registrado");
		
		return existePedido;
	}

	
	public Producto registrarProductoPerecedero(  String nombre, String categoria, String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida,String presentacion, String unidadDeMedida, String tipoCategoria, Date fechaDeVencimiento){
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
		Producto producto = pp.adicionarProducto( nombre, cat,  codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, presentacion, unidadDeMedida);		
        log.info ("Adicionando Producto: " + nombre);
       
        return producto;
	}
	public Producto registrarProductoNoPerecedero(  String nombre, String categoria, String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida, String presentacion, String unidadDeMedida, String tipoCategoria){
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
		Producto producto = pp.adicionarProducto( nombre, cat,  codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, presentacion, unidadDeMedida);		
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
	
	public Bodega registrarBodegaASucursal(String nombre, int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, double nivelDeReorden, long sucursal){
		
		Bodega bode=pp.adicionarBodega(nombre, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, nivelDeReorden, sucursal);
		
		return bode;		
		
	}
	
	
	
	public Estante registrarEstanteASucursal(String nombre, int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, String equipamientoAdicional,long nivelReorden, int nivelAbaste, long sucursal){
			Estante bode= pp.adicionarEstante(nombre, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto,equipamientoAdicional, nivelReorden, nivelAbaste, sucursal);
	
		return bode;
	}
	
	public Venta registrarVentaAConsumidor (String fecha, String formaPago, double valorTotal, long consumidor)
	{
		return pp.adicionarVenta(fecha, formaPago, valorTotal, consumidor);
	}

	public Producto darProductoPorId(long id){
		
		return pp.darProductoPorId(id);
	}
	public List<ProductoOfrecido> darProductoOfrecidoPorIdProveedor (long id)
	{
		log.info ("Buscando ProductoOfrecidoPorIdProveedor: " + id);
		List<ProductoOfrecido> tb = (List<ProductoOfrecido>) pp.darProductosOfrecidosPorIdProveedor(id);
		return tb;
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