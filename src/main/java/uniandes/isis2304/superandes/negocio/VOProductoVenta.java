package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los m�todos get de Bodega.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProductoVenta {

	

	/**
	 * @return the cantidadVenta
	 */
	public int getCantidadVenta();


	/**
	 * @param cantidadVenta the cantidadVenta to set
	 */
	public void setCantidadVenta(int cantidadVenta);


	/**
	 * @return the unidadDeMedida
	 */
	public String getUnidadDeMedida() ;


	/**
	 * @param unidadDeMedida the unidadDeMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida);

	/**
	 * @return the venta
	 */
	public long getVenta();

	/**
	 * @param venta the venta to set
	 */
	public void setVenta(long venta);

	/**
	 * @return the producto
	 */
	public long getProducto();

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(long producto);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
