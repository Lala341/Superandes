package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Categoria;
import uniandes.isis2304.superandes.negocio.NoPerecedero;
import uniandes.isis2304.superandes.negocio.Perecedero;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto NO_PERECEDERO de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLNoPerecedero
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperandes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLNoPerecedero (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un NO_PERECEDERO a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idNoPerecedero - El identificador del noPerecedero
	 * @param tipoCategoriaNoPerecedera - el tipo de cateogoria del producto noPerecedero
	 * @param idCategoria
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarNoPerecedero (PersistenceManager pm, long idNoPerecedero, String tipoCategoriaNoPerecedera, long idCategoria) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaNoPerecedero () + "(id, , categoria, tipoCategoriaNP) values (?, ?, ?)");
        q.setParameters(idNoPerecedero, idCategoria, tipoCategoriaNoPerecedera);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN NO_PERECEDERO de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idNoPerecedero - El identificador del noPerecedero
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarNoPerecederoPorId (PersistenceManager pm, long idNoPerecedero)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaNoPerecedero () + " WHERE id = ?");
        q.setParameters(idNoPerecedero);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de UN NO_PERECEDERO de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idNoPerecedero - El identificador del noPerecedero
	 * @return El objeto NO_PERECEDERO que tiene el identificador dado
	 */
	public NoPerecedero darNoPerecederoPorId (PersistenceManager pm, long idNoPerecedero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaNoPerecedero () + " WHERE id = ?");
		q.setResultClass(NoPerecedero.class);
		q.setParameters(idNoPerecedero);
		return (NoPerecedero) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS NO_NO_PERECEDEROS de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos NO_PERECEDERO
	 */
	public List<NoPerecedero> darNoPerecederos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaNoPerecedero ());
		q.setResultClass(NoPerecedero.class);
		return (List<NoPerecedero>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de NoPerecedero de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idNoPerecedero - El identificador de la NoPerecedero
	 * @return El objeto NoPerecedero que tiene el identificador dado
	 */
	public NoPerecedero darCategoriaPorId (PersistenceManager pm, long idNoPerecedero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaNoPerecedero () + " WHERE id = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(idNoPerecedero);
		return (NoPerecedero) q.executeUnique();
	}
	
}

