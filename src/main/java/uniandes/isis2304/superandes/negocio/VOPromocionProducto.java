package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOPromocionProducto {

	

	/**
	 * @return the promocion
	 */
	public long getPromocion() ;

	/**
	 * @param promocion the promocion to set
	 */
	public void setPromocion(long promocion);

	/**
	 * @return the Producto
	 */
	public long getProducto();

	/**
	 * @param sucursal the Producto to set
	 */
	public void setProducto(long producto);
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
