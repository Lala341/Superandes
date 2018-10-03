package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProductoOfrecido {

	

	/**
	 * @return the precioProveedor
	 */
	public double getPrecioProveedor();

	/**
	 * @param precioProveedor the precioProveedor to set
	 */
	public void setPrecioProveedor(double precioProveedor) ;

	/**
	 * @return the calificacionTotal
	 */
	public int getCalificacionTotal();

	/**
	 * @param calificacionTotal the calificacionTotal to set
	 */
	public void setCalificacionTotal(int calificacionTotal);

	/**
	 * @return the calidad
	 */
	public int getCalidad();
	

	/**
	 * @param calidad the calidad to set
	 */
	public void setCalidad(int calidad);

	/**
	 * @return the cumplimiento
	 */
	public int getCumplimiento();

	/**
	 * @param cumplimiento the cumplimiento to set
	 */
	public void setCumplimiento(int cumplimiento);

	/**
	 * @return the producto
	 */
	public long getProductoId();
	/**
	 * @param producto the producto to set
	 */
	public void setProductoId(long producto);
	/**
	 * @return the proveedor
	 */
	public long getProveedor();
	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(long proveedor);
	/**
	 * @return the id
	 */
	public long getId();
	/**
	 * @param id the id to set
	 */
	public void setId(long id);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
