package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superandes.negocio.Consumidor;
import uniandes.isis2304.superandes.negocio.ConsumidorVenta;
import uniandes.isis2304.superandes.negocio.Venta;


public class SQLVenta {
	
	/**
	 * Cadena que representa el tipoconsumidor de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

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
	public SQLVenta (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar 
	 */
	public long adicionar (PersistenceManager pm, long id, String fecha, String formaPago, double valorTotal, long consumidor, long sucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVenta () + "(id, fecha, formaPago, valorTotal, consumidor, sucursal) values (?, ?, ?, ?,?,?)");
        q.setParameters(id, fecha, formaPago, valorTotal, consumidor, sucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar 
	 */
	public long eliminarPorFecha (PersistenceManager pm, String fecha)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVenta () + " WHERE fecha = ?");
        q.setParameters(fecha);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un elemento por por id
	 */
	public long eliminarPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVenta () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un elemento por su identificador
	 */
	public Venta darPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta () + " WHERE id = ?");
		q.setResultClass(Venta.class);
		q.setParameters(id);
		return (Venta) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<Venta> darListaPorFecha (PersistenceManager pm, String fecha) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta () + " WHERE fecha = ?");
		q.setResultClass(Venta.class);
		q.setParameters(fecha);
		return (List<Venta>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los elementos
	 */
	public List<Venta> darLista (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta ());
		q.setResultClass(Venta.class);
		return (List<Venta>) q.executeList();
	}
	public List<Venta> darListaPorSucursal (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta ()+ " WHERE (sucursal=?)");
		q.setResultClass(Venta.class);
		q.setParameters(id);
		
		return (List<Venta>) q.executeList();
	}
	public Venta darListaPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta ()+ " WHERE (id=?)");
		q.setResultClass(Venta.class);
		q.setParameters(id);
		
		return (Venta) q.executeList();
	}

	public List<ConsumidorVenta> req10o(PersistenceManager pm, String i, String f, long id, boolean sucu, long sucuid, boolean t, String tipo)
	{
		String g= "select * from(select t.fecha,formapago,valortotal,consumidor,sucursal,"
				+ "venta,producto,cantidadproducto,unidadmedida from(select *from venta where "
				+ "(fecha between ? and ?) )t inner join productoventa on "
				+ "t.id=productoventa.venta where productoventa.PRODUCTO=? "+
") m inner join consumidor on m.consumidor=consumidor.id ) ";
		if(sucu){
			g=g+"where sucursal = "+sucuid;
		}
		if(t){
			g=g+"order by "+tipo;
		}
		Query q = pm.newQuery(SQL, g);
		q.setResultClass(Venta.class);
		q.setParameters(i,f,id);
		
		return (List<ConsumidorVenta>) q.executeList();
	}
	public List<ConsumidorVenta> req11o(PersistenceManager pm, String i, String f, long id, boolean sucu, long sucuid, boolean t, String tipo)
	{
		String g= "select * from(select t.fecha,formapago,valortotal,consumidor,sucursal,venta,producto,"
				+ "cantidadproducto,unidadmedida from(select *from venta where (fecha between ? and ?) )"
				+ "t inner join productoventa on t.id=productoventa.venta where productoventa.PRODUCTO=? "+
") m right join consumidor on m.consumidor=consumidor.id where venta is null ";
		if(sucu){
			g=g+"and sucursal = "+sucuid;
		}
		if(t){
			g=g+"order by "+tipo;
		}
		Query q = pm.newQuery(SQL, g);
		q.setResultClass(Venta.class);
		q.setParameters(i,f,id);
		
		return (List<ConsumidorVenta>) q.executeList();
	}
	

}
