package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Fidelizacion;


public class SQLFidelizacion {
	
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
	public SQLFidelizacion (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long idConsumidor, int cantidadPuntos) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFidelizacion () + "(idConsumidor, cantidadPuntos) values (?, ?)");
        q.setParameters(idConsumidor, cantidadPuntos);
        return (long) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorId (PersistenceManager pm, long idConsumidor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFidelizacion () + " WHERE idConsumidor = ?");
        q.setParameters(idConsumidor);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public Fidelizacion darPorId (PersistenceManager pm, long idConsumidor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFidelizacion () + " WHERE idConsumidor = ?");
		q.setResultClass(Fidelizacion.class);
		q.setParameters(idConsumidor);
		return (Fidelizacion) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<Fidelizacion> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFidelizacion ());
		q.setResultClass(Fidelizacion.class);
		return (List<Fidelizacion>) q.executeList();
	}

	
	

}
