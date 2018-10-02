package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.ProductoTransaccion;


public class SQLProductoTransaccion {
	
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
	public SQLProductoTransaccion (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long idProducto, int cantidadVenta) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoTransaccion () + "(idProducto, cantidadVenta) values (?, ?)");
        q.setParameters(idProducto, cantidadVenta);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar 
	 */
	public long eliminarPorCantidad (PersistenceManager pm, int cantidadVenta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoTransaccion () + " WHERE cantidadVenta = ?");
        q.setParameters(cantidadVenta);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdProducto (PersistenceManager pm, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoTransaccion () + " WHERE idProducto = ?");
        q.setParameters(idProducto);
        return (long) q.executeUnique();
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoTransaccion> darListaPorIdProducto (PersistenceManager pm, long idProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoTransaccion () + " WHERE idProducto = ?");
		q.setResultClass(ProductoTransaccion.class);
		q.setParameters(idProducto);
		return (List<ProductoTransaccion>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoTransaccion> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoTransaccion ());
		q.setResultClass(ProductoTransaccion.class);
		return (List<ProductoTransaccion>) q.executeList();
	}

	
	

}
