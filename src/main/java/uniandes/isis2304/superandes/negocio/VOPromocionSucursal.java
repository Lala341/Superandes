package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOPromocionSucursal {

	

	/**
	 * @return the promocion
	 */
	public long getPromocion() ;

	/**
	 * @param promocion the promocion to set
	 */
	public void setPromocion(long promocion);

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
