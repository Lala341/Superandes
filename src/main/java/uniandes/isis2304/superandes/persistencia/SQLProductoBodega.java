package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.ProductoBodega;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ProductoBodega de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLProductoBodega 
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
	public SQLProductoBodega (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ProductoBodega a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador del bodega
	 * @param idProducto - El identificador de la producto
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarProductoBodega(PersistenceManager pm, long idBodega, long idProducto, int cant) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoBodega () + "(bodega, producto, cantidadproducto) values (?, ?, ?)");
        q.setParameters(idBodega, idProducto, cant);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ProductoBodega de la base de datos de Superandes, por sus identificador
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador del bodega
	 * @param idProducto - El identificador de la producto
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProductoBodega (PersistenceManager pm, long idBodega, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoBodega () + " WHERE idbodega = ? AND idproducto = ?");
        q.setParameters(idBodega, idProducto);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los ProductoBodega de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ProductoBodega
	 */
	public List<ProductoBodega> darProductoBodega (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoBodega ());
		q.setResultClass(ProductoBodega.class);
		List<ProductoBodega> resp = (List<ProductoBodega>) q.execute();
		return resp;
	}

}

