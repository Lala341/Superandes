package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Administrador;
import uniandes.isis2304.superandes.negocio.AdministradorSucursal;
import uniandes.isis2304.superandes.negocio.Ciudad;


public class SQLAdministradorSucursal {
	
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
	public SQLAdministradorSucursal (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long administrador, long sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministradorSucursal()  + "(administrador, sucursal) values (?,  ?)");
        q.setParameters(administrador,sucursal);
        return (long) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public AdministradorSucursal darPorIdAdministrador (PersistenceManager pm, long administrador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorSucursal () + " WHERE administrador = ?");
		q.setResultClass(AdministradorSucursal.class);
		q.setParameters(administrador);
		return (AdministradorSucursal) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public AdministradorSucursal darPorIdSucursal (PersistenceManager pm, long sucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorSucursal () + " WHERE sucursal = ?");
		q.setResultClass(AdministradorSucursal.class);
		q.setParameters(sucursal);
		return (AdministradorSucursal) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdAdministrador (PersistenceManager pm, long administrador)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministradorSucursal()+ " WHERE administrador = ?");
        q.setParameters(administrador);
        return (long) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorIdSucursal (PersistenceManager pm, long sucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " +pp.darTablaAdministradorSucursal()+ " WHERE sucursal = ?");
        q.setParameters(sucursal);
        return (long) q.executeUnique();
	}

	


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<AdministradorSucursal> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministradorSucursal ());
		q.setResultClass(AdministradorSucursal.class);
		return (List<AdministradorSucursal>) q.executeList();
	}

	
	

}
