package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.ProductoEstante;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto ProductoEstante de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLProductoEstante 
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
	public SQLProductoEstante (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ProductoEstante a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @param idProducto - El identificador de la producto
	 * @return EL n�mero de tuplas insertadas
	 */
	public long adicionarProductoEstante(PersistenceManager pm, long idEstante, long idProducto, int cant) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoEstante () + "(estante, producto, cantidadproducto) values (?, ?, ?)");
        q.setParameters(idEstante, idProducto, cant);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ProductoEstante de la base de datos de Superandes, por sus identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @param idProducto - El identificador de la producto
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarProductoEstante (PersistenceManager pm, long idEstante, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoEstante () + " WHERE estante = ? AND producto = ?");
        q.setParameters(idEstante, idProducto);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los ProductoEstante de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ProductoEstante
	 */
	public List<ProductoEstante> darProductoEstante (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoEstante ());
		q.setResultClass(ProductoEstante.class);
		List<ProductoEstante> resp = (List<ProductoEstante>) q.execute();
		return resp;
	}

	public void sacarCantidadDeProducto (PersistenceManager pm, long id, long idProducto,int cant)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductoEstante() + "SET CANTIDADPRODUCTO=(CANTIDADPRODUCTO-?) WHERE estante = ? AND producto = ?");
        q.setParameters(cant, id, idProducto);
        q.executeUnique();
	}
	public void ingresarCantidadDeProducto (PersistenceManager pm, long id, long idProducto,int cant)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductoEstante() + "SET CANTIDADPRODUCTO=(CANTIDADPRODUCTO+?) WHERE estante = ? AND producto = ?");
        q.setParameters(cant, id, idProducto);
       q.executeUnique();
	}
}
