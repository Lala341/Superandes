package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Bodega;
import uniandes.isis2304.superandes.negocio.Sucursal;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto BODEGA de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLBodega
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
	public SQLBodega (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BODEGA a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador del bodega
	 * @param cantidadProductos - la cantidad de productos en la bodega
	 * @param capacidadTotal - La capacidad de la bodega
	 * @param peso - El peso manejado en la bodega
	 * @param volumen - El volumen manejado en la bodega
	 * @param tipoProducto - El tipo de producto que maneja la bodega
	 * @param nivelDeAbastecimiento
	 * @param idSucursal
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarBodega (PersistenceManager pm, long idBodega, int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, double nivelDeReorden, long idSucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega () + "(id, cantidadProductos, capacidadTotal, peso, volumen,tipoProducto, nivelReorden, sucursal) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idBodega, cantidadProductos, capacidadTotal, peso, volumen, tipoProducto, nivelDeReorden, idSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BODEGA de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador del bodega
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarBodegaPorId (PersistenceManager pm, long idBodega)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega () + " WHERE id = ?");
        q.setParameters(idBodega);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de UN BODEGA de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador del bodega
	 * @return El objeto BODEGA que tiene el identificador dado
	 */
	public Bodega darBodegaPorId (PersistenceManager pm, long idBodega) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega () + " WHERE id = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(idBodega);
		return (Bodega) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS BODEGAS de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BODEGA
	 */
	public List<Bodega> darBodegas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega ());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de BODEGA de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador de la bodega
	 * @return El objeto BODEGA que tiene el identificador dado
	 */
	public Bodega darSucursalPorId (PersistenceManager pm, long idBodega) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(idBodega);
		return (Bodega) q.executeUnique();
	}
	
}

