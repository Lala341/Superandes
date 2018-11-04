package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.ProductoCarritoCompras;


public class SQLProductoCarritoCompras {
	
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
	public SQLProductoCarritoCompras (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long carritoCompras, long producto, int cantidadProducto, String unidadMedida) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoCarritoCompras () + "(carritoCompras, producto, cantidadProducto,  unidadMedida) values ( ?, ?, ?, ?)");
        q.setParameters(carritoCompras, producto, cantidadProducto,  unidadMedida);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdCarritoCompras (PersistenceManager pm, long carritoCompras)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoCarritoCompras () + " WHERE carritoCompras = ?");
        q.setParameters(carritoCompras);
        return (long) q.executeUnique();
	}

	public long eliminarPorIdCarritoComprasProducto (PersistenceManager pm, long carritoCompras, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoCarritoCompras () + " WHERE (carritoCompras = ?) AND (producto = ?)");
        q.setParameters(carritoCompras, id);
        return (long) q.executeUnique();
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoCarritoCompras> darListaPorIdCarritoCompras (PersistenceManager pm, long carritoCompras) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoCarritoCompras () + " WHERE carritoCompras = ?");
		q.setResultClass(ProductoCarritoCompras.class);
		q.setParameters(carritoCompras);
		return (List<ProductoCarritoCompras>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoCarritoCompras> darListaTodos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoCarritoCompras ());
		q.setResultClass(ProductoCarritoCompras.class);
		return (List<ProductoCarritoCompras>) q.executeList();
	}

	
	

}
