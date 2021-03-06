package uniandes.isis2304.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLUtil {
	
	/**
	 * Cadena que representa el tipoconsumidor de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	 //			Atributos
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperandes pp;

	
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperandes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	
	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 */
	public long [] limpiarSuperandes (PersistenceManager pm)
	{
        Query qCiudad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCiudad());   
        Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal());   
        Query qAdministrador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministrador());          
        Query qAdministradorSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministradorSucursal());          
        Query qBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega());   
        Query qEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante()); 
        Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria());          
        Query qPerecedero = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPerecedero());   
        Query qNoPerecedero = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaNoPerecedero());   
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());   
        Query qProductoEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoEstante());  
        Query qProductoBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoBodega());
        Query qConsumidor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConsumidor());   
        Query qPersonaNatural = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonaNatural());   
        Query qEmpresa = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa());   
        Query qFidelizacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFidelizacion());  
        Query qVenta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVenta());   
        Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura());   
        Query qCarritoCompras = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoCompras());   
        Query qProductoVenta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoVenta());  
        Query qProductoCarritoCompras = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoCarritoCompras());          
        Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion());   
        Query qPromoDescuento = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionDescuento());   
        Query qPromoParteDescuento = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionParteDescuento());   
        Query qPromoUnidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionUnidad());   
        Query qPromoCantidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionCantidad());   
        Query qPromocionProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionProducto());   
        Query qProveedores = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedores());   
        Query qProductoOfrecido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoOfrecido());  
        Query qOrdenPedido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido());  

        
        long qCiudadEliminados = (long) qCiudad.executeUnique ();
        long qSucursalEliminados = (long) qSucursal.executeUnique ();
        long qAdministradorEliminados = (long) qAdministrador.executeUnique ();
        long qAdministradorSucursalEliminados = (long) qAdministradorSucursal.executeUnique ();
        long qBodegaEliminados = (long) qBodega.executeUnique ();
        long qEstanteEliminados = (long) qEstante.executeUnique ();
        long qCategoriaEliminados = (long) qCategoria.executeUnique ();
        long qPerecederoEliminados = (long) qPerecedero.executeUnique ();
        long qNoPerecederoEliminados = (long) qNoPerecedero.executeUnique ();
        long qProductoEliminados = (long) qProducto.executeUnique ();
        long qProductoEstanteEliminados = (long) qProductoEstante.executeUnique ();
        long qProductoBodegaEliminados = (long) qProductoBodega.executeUnique ();
        long qConsumidorEliminados = (long) qConsumidor.executeUnique ();
        long qPersonaNaturalEliminados = (long) qPersonaNatural.executeUnique ();
        long qEmpresaEliminados = (long) qEmpresa.executeUnique ();
        long qFidelizacionEliminados = (long) qFidelizacion.executeUnique ();
        long qVentaEliminados = (long) qVenta.executeUnique ();
        long qFacturaEliminados = (long) qFactura.executeUnique ();
        long qCarritoComprasEliminados = (long) qCarritoCompras.executeUnique ();
        long qProductoVentaEliminados = (long) qProductoVenta.executeUnique ();
        long qProductoCarritoComprasEliminados = (long) qProductoCarritoCompras.executeUnique ();
        long qPromocionEliminados = (long) qPromocion.executeUnique ();
        long qPromoDescuentoEliminados = (long) qPromoDescuento.executeUnique ();
        long qPromoParteDescuentoEliminados = (long) qPromoParteDescuento.executeUnique ();
        long qPromoUnidadEliminados = (long) qPromoUnidad.executeUnique ();
        long qPromoCantidadEliminados = (long) qPromoCantidad.executeUnique ();
        long qPromocionProductoEliminados = (long) qPromocionProducto.executeUnique ();
        long qProveedoresEliminados = (long) qProveedores.executeUnique ();
        long qProductoOfrecidoEliminados = (long) qProductoOfrecido.executeUnique ();
        long qOrdenPedidoEliminados = (long) qOrdenPedido.executeUnique ();

        return new long[] {qCiudadEliminados ,qSucursalEliminados,qAdministradorEliminados, qAdministradorSucursalEliminados, qBodegaEliminados, 
        		qEstanteEliminados,qCategoriaEliminados ,qPerecederoEliminados ,qNoPerecederoEliminados, 
        		qProductoEliminados, qProductoEstanteEliminados,qProductoBodegaEliminados,qPersonaNaturalEliminados,
        		qEmpresaEliminados,qConsumidorEliminados,qFidelizacionEliminados,
        		qVentaEliminados, qFacturaEliminados ,qCarritoComprasEliminados, qProductoVentaEliminados,
        		qProductoCarritoComprasEliminados, qPromocionEliminados,qPromoDescuentoEliminados,qPromoParteDescuentoEliminados,
        		qPromoUnidadEliminados ,qPromoCantidadEliminados, qPromocionProductoEliminados,
        		qProveedoresEliminados, qProductoOfrecidoEliminados, qOrdenPedidoEliminados};
	}


}
