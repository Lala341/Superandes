package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PromocionUnidad;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMOCION_UNIDAD de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPromocionUnidad 
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
	public SQLPromocionUnidad (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PROMOCION_UNIDAD a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - id de la promocion
	 * @param unidadVendidos unidad vendidos
	 * @param unidadPagados unidad pagados
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarPromocionUnidad (PersistenceManager pm, long idPromocion, int unidadVendidos, int unidadPagados) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionUnidad () + "(idPromocion, unidadVendidos, unidadPagados) values (?, ?, ?)");
        q.setParameters(idPromocion, unidadVendidos, unidadPagados);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PROMOCIONES_UNIDAD de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPromocionUnidad - El identificador de la promocionUnidad
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromocionUnidadPorId (PersistenceManager pm, long idPromocionUnidad)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionUnidad () + " WHERE id = ?");
        q.setParameters(idPromocionUnidad);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaciÃ³n de un elemento por su identificador
	 */
	public PromocionUnidad darPromocionUnidadPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionUnidad () + " WHERE id = ?");
		q.setResultClass(PromocionUnidad.class);
		q.setParameters(id);
		return (PromocionUnidad) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PROMOCIONES_UNIDAD de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROMOCION_UNIDAD
	 */
	public List<PromocionUnidad> darPromocionesUnidad (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionUnidad ());
		q.setResultClass(PromocionUnidad.class);
		return (List<PromocionUnidad>) q.executeList();
	}

}
