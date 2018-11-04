package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOCarritoCompras {

	

	
	/**
	 * @return the id
	 */
	public long getId();



	/**
	 * @param id the id to set
	 */
	public void setId(long id);



	/**
	 * @return the consumidor
	 */
	public long getConsumidor();



	/**
	 * @param consumidor the consumidor to set
	 */
	public void setConsumidor(long consumidor);
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
