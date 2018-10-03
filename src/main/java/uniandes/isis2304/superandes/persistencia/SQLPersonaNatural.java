package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PersonaNatural;
import uniandes.isis2304.superandes.negocio.Consumidor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PERSONA_NATURAL de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPersonaNatural
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
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
	public SQLPersonaNatural (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PERSONA_NATURAL a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idConsumidor - El identificador del Consumidor
	 * @param documentoIdentidad - documentoIdentidad de PersonaNatural
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPersonaNatural (PersistenceManager pm, long idConsumidor, long documentoIdentidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonaNatural () + "( idConsumidor, documentoIdentidad) values (?, ?)");
        q.setParameters(idConsumidor, documentoIdentidad);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PERSONA_NATURAL de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPersonaNatural - El identificador del personaNatural
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPersonaNaturalPorId (PersistenceManager pm, long idPersonaNatural)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonaNatural () + " WHERE id = ?");
        q.setParameters(idPersonaNatural);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PERSONA_NATURAL de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPersonaNatural - El identificador del personaNatural
	 * @return El objeto PERSONA_NATURAL que tiene el identificador dado
	 */
	public PersonaNatural darPersonaNaturalPorId (PersistenceManager pm, long idPersonaNatural) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonaNatural () + " WHERE id = ?");
		q.setResultClass(PersonaNatural.class);
		q.setParameters(idPersonaNatural);
		return (PersonaNatural) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PERSONAS_NATURALES de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PERSONA_NATURAL
	 */
	public List<PersonaNatural> darPersonasNaturales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonaNatural ());
		q.setResultClass(PersonaNatural.class);
		return (List<PersonaNatural>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de PERSONA_NATURAL de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPersonaNatural - El identificador de la persona
	 * @return El objeto PERSONA_NATURAL que tiene el identificador dado
	 */
	public PersonaNatural darConsumidorPorId (PersistenceManager pm, long idPersonaNatural) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonaNatural () + " WHERE id = ?");
		q.setResultClass(Consumidor.class);
		q.setParameters(idPersonaNatural);
		return (PersonaNatural) q.executeUnique();
	}
	
}

