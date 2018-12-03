package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.OrdenPedido;


public class SQLOrdenPedido {
	
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
	public SQLOrdenPedido (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long id, String estado, double calificacion, String fecha, String fechaEntrega, long proveedor, long productoOfrecido, double cantidadProducto, String unidadMedida, long sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrdenPedido () + "(id, estado, calificacion, fecha, fechaEntrega, proveedor, productoOfrecido, cantidadProducto, unidadMedida, sucursal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, estado, calificacion, fecha, fechaEntrega, proveedor, productoOfrecido, cantidadProducto, unidadMedida, sucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public OrdenPedido darPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido () + " WHERE id = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(id);
		return (OrdenPedido) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public List<OrdenPedido> darPorIdProveedor (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido () + " WHERE proveedor = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(id);
		return (List<OrdenPedido>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<OrdenPedido> darListaPorFecha (PersistenceManager pm, String fecha) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido () + " WHERE fecha = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(fecha);
		return (List<OrdenPedido>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<OrdenPedido> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido ());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>) q.executeList();
	}
	public long finalizarOrden (PersistenceManager pm, long p)
	{
		Query q = pm.newQuery(SQL, "UPDATE  " + pp.darTablaOrdenPedido ()+ " SET ESTADO='FINALIZADO' WHERE id= ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(p);
		
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List darProveedorMasSolicidado (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM( " 
				+ "SELECT fecha, proveedor, COUNT(proveedor) FROM" + pp.darTablaProductoVenta ()
				+ "group by fecha, proveedor" + "order by proveedor DESC)" 
				+ "WHERE ROWNUM = 1");
		q.setResultClass(OrdenPedido.class);
		return (List) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List darProveedorMenosSolicidado (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM( " 
				+ "SELECT fecha, proveedor, COUNT(proveedor) FROM" + pp.darTablaProductoVenta ()
				+ "group by fecha, proveedor" + "order by proveedor ASC)" 
				+ "WHERE ROWNUM = 1");
		q.setResultClass(OrdenPedido.class);
		return (List) q.executeList();
	}
	
	

}
