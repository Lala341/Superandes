package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Administrador;


public class SQLAdministrador {
	
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
	public SQLAdministrador (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long id, int cantidadDeRecompra, String usuario,  String contrasenha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministrador () + "(id, cantidadDeRecompra, usuario, contrasenha) values (?,?, ?, ?)");
        q.setParameters(id, cantidadDeRecompra, usuario, contrasenha);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar 
	 */
	public long eliminarPorUsuario (PersistenceManager pm, String usuario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministrador () + " WHERE usuario = ?");
        q.setParameters(usuario);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministrador () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public Administrador darPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministrador () + " WHERE id = ?");
		q.setResultClass(Administrador.class);
		q.setParameters(id);
		return (Administrador) q.executeUnique();
	}

	public List<Administrador> darPorUsuario(PersistenceManager pm, String v) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministrador () + " WHERE usuario = ?");
		q.setResultClass(Administrador.class);
		q.setParameters(v);
		return ( List<Administrador>) q.executeList();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<Administrador> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministrador ());
		q.setResultClass(Administrador.class);
		return (List<Administrador>) q.executeList();
	}

	
	

}
