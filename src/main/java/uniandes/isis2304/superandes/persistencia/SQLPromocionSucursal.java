package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PromocionSucursal;


public class SQLPromocionSucursal {
	
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
	public SQLPromocionSucursal (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long promocion, long sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionSucursal () + "(promocion,sucursal) values (?, ?)");
        q.setParameters(promocion,sucursal);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdPromocion (PersistenceManager pm, long promocion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionSucursal () + " WHERE promocion = ?");
        q.setParameters(promocion);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdSucursal (PersistenceManager pm, long sucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionSucursal () + " WHERE sucursal = ?");
        q.setParameters(sucursal);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<PromocionSucursal> darListaPorPromocion (PersistenceManager pm, long promocion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionSucursal () + " WHERE promocion = ?");
		q.setResultClass(PromocionSucursal.class);
		q.setParameters(promocion);
		return (List<PromocionSucursal>) q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<PromocionSucursal> darListaPorSucursal (PersistenceManager pm, long sucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionSucursal () + " WHERE sucursal = ?");
		q.setResultClass(PromocionSucursal.class);
		q.setParameters(sucursal);
		return (List<PromocionSucursal>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<PromocionSucursal> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionSucursal ());
		q.setResultClass(PromocionSucursal.class);
		return (List<PromocionSucursal>) q.executeList();
	}

	
	

}
