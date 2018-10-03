package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Empresa;
import uniandes.isis2304.superandes.negocio.Consumidor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto EMPRESA de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLEmpresa
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
	public SQLEmpresa (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un EMPRESA a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idConsumidor - El identificador del Consumidor
	 * @param NIT - id de Empresa
	 * @param direccion direccion de Empresa
	 * @return El número de tuplas insertadas
	 */
	public long adicionarEmpresa (PersistenceManager pm, long idConsumidor, long nit, String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpresa () + "( idConsumidor, NIT, direccion) values (?, ?, ?)");
        q.setParameters(idConsumidor, nit, direccion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN EMPRESA de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpresa - El identificador del empresa
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEmpresaPorId (PersistenceManager pm, long idEmpresa)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa () + " WHERE id = ?");
        q.setParameters(idEmpresa);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN EMPRESA de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpresa - El identificador del empresa
	 * @return El objeto EMPRESA que tiene el identificador dado
	 */
	public Empresa darEmpresaPorId (PersistenceManager pm, long idEmpresa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa () + " WHERE id = ?");
		q.setResultClass(Empresa.class);
		q.setParameters(idEmpresa);
		return (Empresa) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS EMPRESAS de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos EMPRESA
	 */
	public List<Empresa> darEmpresas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa ());
		q.setResultClass(Empresa.class);
		return (List<Empresa>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de EMPRESA de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpresa - El identificador de la empresa
	 * @return El objeto EMPRESA que tiene el identificador dado
	 */
	public Empresa darConsumidorPorId (PersistenceManager pm, long idEmpresa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa () + " WHERE id = ?");
		q.setResultClass(Consumidor.class);
		q.setParameters(idEmpresa);
		return (Empresa) q.executeUnique();
	}
	
}

