
package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Producto;
import uniandes.isis2304.superandes.negocio.Categoria;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PRODUCTO de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLProducto 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperandes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProducto (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PRODUCTO a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
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
	 * @return EL n�mero de tuplas insertadas
	 */
	public long adicionarProducto (PersistenceManager pm, long id, String nombre, long idCategoria, int cantidad, String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida, double precioUnitario, String presentacion, String unidadDeMedida) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto () + "(id, nombre, cantidad, codigoBarras, especificacionesEmpaquetado, estado, marca, precioPorUnidadMedida, precioUnitario, presentacion, unidadMedida, categoria ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters( id, nombre, cantidad, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, precioPorUnidadMedida, precioUnitario, presentacion, unidadDeMedida, idCategoria);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTOS de la base de datos de Superandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreProducto - El nombre de la producto
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarProductoPorNombre (PersistenceManager pm, String nombreProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE nombre = ?");
        q.setParameters(nombreProducto);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTOS de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador de la producto
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarProductoPorId (PersistenceManager pm, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE id = ?");
        q.setParameters(idProducto);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de UNA PRODUCTO de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador de la producto
	 * @return El objeto PRODUCTO que tiene el identificador dado
	 */
	public Producto darCategoriaPorId (PersistenceManager pm, long idProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE id = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(idProducto);
		return (Producto) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de PRODUCTOS de la 
	 * base de datos de Superandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreProducto - El nombre de la producto
	 * @return Una lista de objetos PRODUCTO que tienen el nombre dado
	 */
	public List<Producto> darProductosPorNombre (PersistenceManager pm, String nombreProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE nombre = ?");
		q.setResultClass(Producto.class);
		q.setParameters(nombreProducto);
		return (List<Producto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LAS PRODUCTOS de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PRODUCTO
	 */
	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto ());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}

}
