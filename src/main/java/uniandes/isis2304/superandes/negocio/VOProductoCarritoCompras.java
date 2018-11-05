package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProductoCarritoCompras {

	

	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadProducto();


	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(int cantidadVenta);


	/**
	 * @return the unidadMedida
	 */
	public String getUnidadDeMedida();


	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida);


	/**
	 * @return the carritoCompras
	 */
	public long getCarritoCompras();

	/**
	 * @param carritoCompras the carritoCompras to set
	 */
	public void setCarritoCompras(long carritoCompras);

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
