package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PromocionDescuento;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMOCION_DESCUENTO de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPromocionDescuento 
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
	public SQLPromocionDescuento (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PROMOCION_DESCUENTO a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - id de la promocion
	 * @param descuento el descuento
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarPromocionDescuento (PersistenceManager pm, long idPromocion, double descuento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionDescuento () + "(idPromocion, descuento) values (?, ?)");
        q.setParameters(idPromocion, descuento);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PROMOCIONES_DESCUENTO de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPromocionDescuento - El identificador de la promocionDescuento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromocionDescuentoPorId (PersistenceManager pm, long idPromocionDescuento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionDescuento () + " WHERE id = ?");
        q.setParameters(idPromocionDescuento);
        return (long) q.executeUnique();            
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PROMOCIONES_DESCUENTO de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROMOCION_DESCUENTO
	 */
	public List<PromocionDescuento> darPromocionesDescuento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionDescuento ());
		q.setResultClass(PromocionDescuento.class);
		return (List<PromocionDescuento>) q.executeList();
	}

}
