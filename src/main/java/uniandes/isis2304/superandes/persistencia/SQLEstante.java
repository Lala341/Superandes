package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Estante;
import uniandes.isis2304.superandes.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ESTANTE de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLEstante
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
	public SQLEstante (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ESTANTE a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @param cantidadProductos - la cantidad de productos en el estante
	 * @param capacidadTotal - La capacidad de el estante
	 * @param peso - El peso manejado en el estante
	 * @param volumen - El volumen manejado en el estante
	 * @param tipoProducto - El tipo de producto que maneja el estante
	 * @param equipamientoAdicional el equipamiento adicional del estante
	 * @param nivelDeAbastecimiento nivel de abastecimiento del estante
	 * @param sucursal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarEstante (PersistenceManager pm, long id,String nombre,  int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, String equipamientoAdicional, long nivelReorden, int nivelDeAbastecimiento, long sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante () + "(id,nombre,  cantidadProductos, capacidadTotal, tipoProducto, peso, volumen, equipamientoAdicional, nivelReorden, nivelAbastecimiento, sucursal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        q.setParameters(id,nombre,  cantidadProductos, capacidadTotal, tipoProducto, volumen,  peso,  equipamientoAdicional, nivelReorden, nivelDeAbastecimiento, sucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ESTANTE de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEstantePorId (PersistenceManager pm, long idEstante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante () + " WHERE id = ?");
        q.setParameters(idEstante);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN ESTANTE de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @return El objeto ESTANTE que tiene el identificador dado
	 */
	public Estante darEstantePorId (PersistenceManager pm, long idEstante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante () + " WHERE id = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idEstante);
		return (Estante) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ESTANTES de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ESTANTE
	 */
	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante ());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
	public List<Estante> darEstantesPorTipo (PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante ()+ " WHERE tipoproducto = ?");
		q.setResultClass(Estante.class);
		q.setParameters(tipo);
		return (List<Estante>) q.executeList();
	}
	public List<Estante> darEstantesPorTipoSucursal (PersistenceManager pm, String tipo, long sucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante ()+ " WHERE (tipoproducto = ?) AND (sucursal = ?)");
		q.setResultClass(Estante.class);
		q.setParameters(tipo, sucursal);
		return (List<Estante>) q.executeList();
	}
	public List<Estante> darEstantesPorSucursal (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante ()+ " WHERE sucursal = ?");
		q.setResultClass(Estante.class);
		q.setParameters(id);
		return (List<Estante>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de ESTANTE de la 
	 * base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del estante
	 * @return El objeto ESTANTE que tiene el identificador dado
	 */
	public Estante darSucursalPorId (PersistenceManager pm, long idEstante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante () + " WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(idEstante);
		return (Estante) q.executeUnique();
	}
	
	public void aumentarCantidadDeProductosUno (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaEstante () + "SET CANTIDADPRODUCTO=(CANTIDADPRODUCTO+1) WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(id);
		q.executeUnique();
	}
	
}

