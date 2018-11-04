package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOOrdenPedido {


	

	/**
	 * @return the cantidadVenta
	 */
	public int getCantidadVenta();

	/**
	 * @param cantidadVenta the cantidadVenta to set
	 */
	public void setCantidadVenta(int cantidadVenta);

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadDeMedida();

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida);

	
	/**
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega();

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega);

	/**
	 * @return the calificacion
	 */
	public int getCalificacion();

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(int calificacion) ;

	/**
	 * @return the estado
	 */
	public String getEstado();

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado);

	/**
	 * @return the fecha
	 */
	public String getFecha();

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha);

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public void setId(long id);
/**
	 * @return the proveedor
	 */
	public long getProveedor();

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(long proveedor);

	/**
	 * @return the productoOfrecido
	 */
	public long getProductoOfrecido() ;

	/**
	 * @param productoOfrecido the productoOfrecido to set
	 */
	public void setProductoOfrecido(long productoOfrecido);

	/**
	 * @return the sucursal
	 */
	public long getSucursal();

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(long sucursal);
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
