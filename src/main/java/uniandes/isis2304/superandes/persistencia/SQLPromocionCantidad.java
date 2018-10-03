package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PromocionCantidad;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMOCION_CANTIDAD de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPromocionCantidad 
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
	public SQLPromocionCantidad (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PROMOCION_CANTIDAD a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - id de la promocion
	 * @param cantidadVendidos cantidad vendidos
	 * @param cantidadPagados cantidad pagados
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarPromocionCantidad (PersistenceManager pm, long idPromocion, int cantidadVendidos, int cantidadPagados) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionCantidad () + "(idPromocion, cantidadVendidos, cantidadPagados) values (?, ?, ?)");
        q.setParameters(idPromocion, cantidadVendidos, cantidadPagados);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PROMOCIONES_CANTIDAD de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPromocionCantidad - El identificador de la promocionCantidad
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromocionCantidadPorId (PersistenceManager pm, long idPromocionCantidad)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionCantidad () + " WHERE id = ?");
        q.setParameters(idPromocionCantidad);
        return (long) q.executeUnique();            
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PROMOCIONES_CANTIDAD de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROMOCION_CANTIDAD
	 */
	public List<PromocionCantidad> darPromocionesCantidad (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionCantidad ());
		q.setResultClass(PromocionCantidad.class);
		return (List<PromocionCantidad>) q.executeList();
	}

}
