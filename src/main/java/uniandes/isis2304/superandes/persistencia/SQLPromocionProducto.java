package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PromocionProducto;


public class SQLPromocionProducto {
	
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
	public SQLPromocionProducto (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long promocion, long Producto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionProducto () + "(promocion,Producto) values (?, ?)");
        q.setParameters(promocion,Producto);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdPromocion (PersistenceManager pm, long promocion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionProducto () + " WHERE promocion = ?");
        q.setParameters(promocion);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdProducto (PersistenceManager pm, long Producto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionProducto () + " WHERE Producto = ?");
        q.setParameters(Producto);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<PromocionProducto> darListaPorPromocion (PersistenceManager pm, long promocion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionProducto () + " WHERE promocion = ?");
		q.setResultClass(PromocionProducto.class);
		q.setParameters(promocion);
		return (List<PromocionProducto>) q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<PromocionProducto> darListaPorProducto (PersistenceManager pm, long Producto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionProducto () + " WHERE Producto = ?");
		q.setResultClass(PromocionProducto.class);
		q.setParameters(Producto);
		return (List<PromocionProducto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<PromocionProducto> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionProducto ());
		q.setResultClass(PromocionProducto.class);
		return (List<PromocionProducto>) q.executeList();
	}

	
	

}
