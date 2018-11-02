package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.CarritoCompras;


public class SQLCarritoCompras {
	
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
	public SQLCarritoCompras (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long id,long consumidor) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoCompras () + "(id,consumidor) values (?, ?)");
        q.setParameters(id, consumidor);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompras () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public CarritoCompras darPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompras () + " WHERE id = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(id);
		return (CarritoCompras) q.executeUnique();
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdConsumidor (PersistenceManager pm, long idConsumidor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompras () + " WHERE idConsumidor = ?");
        q.setParameters(idConsumidor);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public List<CarritoCompras> darPorIdConsumidor (PersistenceManager pm, long idConsumidor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompras () + " WHERE idConsumidor = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(idConsumidor);
		return (List<CarritoCompras>) q.executeList();
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<CarritoCompras> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarritoCompras ());
		q.setResultClass(CarritoCompras.class);
		return (List<CarritoCompras>) q.executeList();
	}

	
	

}
