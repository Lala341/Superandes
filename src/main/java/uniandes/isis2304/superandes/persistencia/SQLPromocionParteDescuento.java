package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.PromocionParteDescuento;
import uniandes.isis2304.superandes.negocio.PromocionParteDescuento;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PROMOCION_PARTE_DESCUENTO de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLPromocionParteDescuento 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipoconsumidor de consulta que se va a realizar en las sentencias de acceso a la base de datos
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
	public SQLPromocionParteDescuento (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PROMOCION_PARTE_DESCUENTO a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - id de la promocion
	 * @param descuento el descuento
	 * @return EL n�mero de tuplas insertadas
	 */
	public long adicionarPromocionParteDescuento (PersistenceManager pm, long idPromocion, double descuento, int unidadVendidos) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionParteDescuento () + "(idPromocion, descuento, unidadVendidos) values (?, ?, ?)");
        q.setParameters(idPromocion, descuento, unidadVendidos);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PROMOCIONES_PARTE_DESCUENTO de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPromocionParteDescuento - El identificador de la promocionParteDescuento
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarPromocionParteDescuentoPorId (PersistenceManager pm, long idPromocionParteDescuento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionParteDescuento () + " WHERE id = ?");
        q.setParameters(idPromocionParteDescuento);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public PromocionParteDescuento darPromocionParteDescuentoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionParteDescuento () + " WHERE id = ?");
		q.setResultClass(PromocionParteDescuento.class);
		q.setParameters(id);
		return (PromocionParteDescuento) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LAS PROMOCIONES_PARTE_DESCUENTO de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROMOCION_PARTE_DESCUENTO
	 */
	public List<PromocionParteDescuento> darPromocionesParteDescuento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionParteDescuento ());
		q.setResultClass(PromocionParteDescuento.class);
		return (List<PromocionParteDescuento>) q.executeList();
	}

}
