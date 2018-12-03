package uniandes.isis2304.superandes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.ProductoProveedor;
import uniandes.isis2304.superandes.negocio.ProductoVenta;


public class SQLProductoVenta {
	
	/**
	 * Cadena que representa el tipoconsumidor de consulta que se va a realizar en las sentencias de acceso a la base de datos
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
	public SQLProductoVenta (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long venta, long producto, int cantidadProducto, String unidadMedida, Date fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoVenta () + "(venta, producto, cantidadProducto,  unidadMedida) values ( ?, ?, ?, ?, ?)");
        q.setParameters(venta, producto, cantidadProducto,  unidadMedida, fecha);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdVenta (PersistenceManager pm, long venta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoVenta () + " WHERE venta = ?");
        q.setParameters(venta);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoVenta> darListaPorIdVenta (PersistenceManager pm, long venta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoVenta () + " WHERE venta = ?");
		q.setResultClass(ProductoVenta.class);
		q.setParameters(venta);
		return (List<ProductoVenta>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoVenta> darListaTodos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoVenta ());
		q.setResultClass(ProductoVenta.class);
		return (List<ProductoVenta>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoProveedor> darProductoMasVendido (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM( " 
				+ "SELECT fecha, producto, COUNT(producto) FROM" + pp.darTablaProductoVenta ()
				+ "group by producto, fecha" + "order by producto DESC)" 
				+ "WHERE ROWNUM = 1");
		q.setResultClass(ProductoVenta.class);
		return (List<ProductoProveedor>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<ProductoProveedor> darProductoMenosVendido (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM( " 
				+ "SELECT fecha, producto, COUNT(producto) FROM" + pp.darTablaProductoVenta ()
				+ "group by producto, fecha" + "order by producto ASC)" 
				+ "WHERE ROWNUM = 1");
		q.setResultClass(ProductoVenta.class);
		return (List<ProductoProveedor>) q.executeList();
	}
	
	

}
