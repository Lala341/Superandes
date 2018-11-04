package uniandes.isis2304.superandes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Perecedero;
import uniandes.isis2304.superandes.negocio.Perecedero;
import uniandes.isis2304.superandes.negocio.Categoria;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PERECEDERO de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPerecedero
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipoconsumidor de consulta que se va a realizar en las sentencias de acceso a la base de datos
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
	public SQLPerecedero (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PERECEDERO a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idPerecedero - El identificador del perecedero
	 * @param fechaDeVencimiento - la fecha de vencimiento del producto perecedero
	 * @param tipoCategoriaPerecedera - el tipoconsumidor de cateogoria del producto perecedero
	 * @param idCategoria
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPerecedero (PersistenceManager pm, long idPerecedero, Date fechaDeVencimiento, String tipoCategoriaPerecedera, long idCategoria) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPerecedero () + "(id, categoria, fechaVencimiento, tipoCategoriaP) values (?, ?, ?,? )");
        q.setParameters(idPerecedero , idCategoria, fechaDeVencimiento, tipoCategoriaPerecedera);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PERECEDERO de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPerecedero - El identificador del perecedero
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPerecederoPorId (PersistenceManager pm, long idPerecedero)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPerecedero () + " WHERE id = ?");
        q.setParameters(idPerecedero);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PERECEDERO de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPerecedero - El identificador del perecedero
	 * @return El objeto PERECEDERO que tiene el identificador dado
	 */
	public Perecedero darPerecederoPorId (PersistenceManager pm, long idPerecedero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPerecedero () + " WHERE id = ?");
		q.setResultClass(Perecedero.class);
		q.setParameters(idPerecedero);
		return (Perecedero) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PERECEDEROS de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PERECEDERO
	 */
	public List<Perecedero> darPerecederos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPerecedero ());
		q.setResultClass(Perecedero.class);
		return (List<Perecedero>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Perecedero de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPerecedero - El identificador de la Perecedero
	 * @return El objeto Perecedero que tiene el identificador dado
	 */
	public Perecedero darCategoriaPorId (PersistenceManager pm, long idPerecedero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPerecedero () + " WHERE id = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(idPerecedero);
		return (Perecedero) q.executeUnique();
	}
	
}

