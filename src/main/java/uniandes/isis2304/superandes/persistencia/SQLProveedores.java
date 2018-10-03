package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Proveedores;


public class SQLProveedores {
	
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
	public SQLProveedores (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long nit, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedores() + "(NIT, nombre) values (?, ?)");
        q.setParameters(nit, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar 
	 */
	public long eliminarPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedores() + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorNIT (PersistenceManager pm, long nit)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedores() + " WHERE NIT = ?");
        q.setParameters(nit);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public Proveedores darPorNIT (PersistenceManager pm, int nit) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedores() + " WHERE NIT = ?");
		q.setResultClass(Proveedores.class);
		q.setParameters(nit);
		return (Proveedores) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<Proveedores> darListaPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedores() + " WHERE nombre = ?");
		q.setResultClass(Proveedores.class);
		q.setParameters(nombre);
		return (List<Proveedores>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<Proveedores> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedores ());
		q.setResultClass(Proveedores.class);
		return (List<Proveedores>) q.executeList();
	}

	
	

}
