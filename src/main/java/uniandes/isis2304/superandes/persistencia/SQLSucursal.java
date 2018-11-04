package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Ciudad;
import uniandes.isis2304.superandes.negocio.Sucursal;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto SUCURSAL de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLSucursal 
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
	public SQLSucursal (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un SUCURSAL a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param sucursal - El identificador del sucursal
	 * @param tamanho - El tamanho del sucursal
	 * @param tipoDeMercado - el tipoconsumidor de mercado del sucursal
	 * @param ventasTotales - Las ventas totales de la sucursal
	 * @param idCiudad
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarSucursal (PersistenceManager pm, long idSucursal, String nombre,long tamanho, String tipoDeMercado, double ventasTotales, long idCiudad,String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal () + "(id, nombre,tamanho, tipoMercado, ventasTotales, Ciudad, direccion ) values (?,?, ?, ?, ?, ?,?)");
        q.setParameters(idSucursal, nombre,tamanho, tipoDeMercado, ventasTotales, idCiudad,direccion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SUCURSAL de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param sucursal - El identificador del sucursal
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarSucursalPorId (PersistenceManager pm, long idSucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE id = ?");
        q.setParameters(idSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de UN SUCURSAL de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param sucursal - El identificador del sucursal
	 * @return El objeto SUCURSAL que tiene el identificador dado
	 */
	public Sucursal darSucursalPorId (PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(idSucursal);
		return (Sucursal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS SUCURSALES de la 
	 * base de datos de Superandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param sucursal - El id de sucursal buscado
	 * @return Una lista de objetos SUCURSAL que tienen el id dado
	 */
	public List<Sucursal> darSucursalesPorId (PersistenceManager pm, String idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(idSucursal);
		return (List<Sucursal>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS SUCURSALES de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SUCURSAL
	 */
	public List<Sucursal> darSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal ());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de SUCURSAL de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param sucursal - El identificador de la sucursal
	 * @return El objeto SUCURSAL que tiene el identificador dado
	 */
	public Sucursal darCiudadPorId (PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE id = ?");
		q.setResultClass(Ciudad.class);
		q.setParameters(idSucursal);
		return (Sucursal) q.executeUnique();
	}

	
	
}

