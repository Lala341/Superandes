package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.ProductoOfrecido;


public class SQLProductoOfrecido {
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductoOfrecido (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long id, double precioProveedor, int calificacionTotal, int calidad, int cumplimiento,  long productoId, long proveedor) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoOfrecido () + "(id, precioProveedor, calificacionTotal, calidad, cumplimiento,  productoId, proveedor) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, precioProveedor, calificacionTotal, calidad, cumplimiento,  proveedor, productoId);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoOfrecido () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public ProductoOfrecido darPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoOfrecido () + " WHERE id = ?");
		q.setResultClass(ProductoOfrecido.class);
		q.setParameters(id);
		return (ProductoOfrecido) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoOfrecido> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoOfrecido());
		q.setResultClass(ProductoOfrecido.class);
		return (List<ProductoOfrecido>) q.executeList();
	}

	
	

}
