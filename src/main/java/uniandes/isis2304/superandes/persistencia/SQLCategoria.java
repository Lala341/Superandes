package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Categoria;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto CATEGORIA de Superandes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 */
class SQLCategoria 
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
	public SQLCategoria (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CATEGORIA a la base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @param idCategoria - El identificador de la categoria
	 * @param nombre - El nombre de la categoria
	 * @param tipoDeAlmacenamiento - El tipo de almacenamiento de la categoria
	 * @param tipoDeManejo - El tipo de manejo de la categoria 
	 * @return EL n�mero de tuplas insertadas
	 */
	public long adicionarCategoria (PersistenceManager pm, long id, String nombre, String tipoAlmacenamiento, String tipoManejo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCategoria () + "(id, nombre, tipoAlmacenamiento, tipoManejo) values (?, ?, ?, ?)");
        q.setParameters(id, nombre, tipoAlmacenamiento, tipoManejo);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CATEGORIAS de la base de datos de Superandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreCategoria - El nombre de la categoria
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarCategoriaPorNombre (PersistenceManager pm, String nombreCategoria)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria () + " WHERE nombre = ?");
        q.setParameters(nombreCategoria);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CATEGORIAS de la base de datos de Superandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCategoria - El identificador de la categoria
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarCategoriaPorId (PersistenceManager pm, long idCategoria)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria () + " WHERE id = ?");
        q.setParameters(idCategoria);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de CATEGORIAS de la 
	 * base de datos de Superandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreCategoria - El nombre de la categoria
	 * @return Una lista de objetos CATEGORIA que tienen el nombre dado
	 */
	public Categoria darCategoriasPorId (PersistenceManager pm, long nombreCategoria) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria () + " WHERE id = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(nombreCategoria);
		return (Categoria) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de CATEGORIAS de la 
	 * base de datos de Superandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreCategoria - El nombre de la categoria
	 * @return Una lista de objetos CATEGORIA que tienen el nombre dado
	 */
	public List<Categoria> darCategoriasPorNombre (PersistenceManager pm, String nombreCategoria) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria () + " WHERE nombre = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(nombreCategoria);
		return (List<Categoria>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LAS CATEGORIAS de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CATEGORIA
	 */
	public List<Categoria> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria ());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}

}
