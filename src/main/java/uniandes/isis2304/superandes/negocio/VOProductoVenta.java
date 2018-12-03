package uniandes.isis2304.superandes.negocio;

import java.sql.Date;

/**
 * Interfaz para los m�todos get de Bodega.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProductoVenta {

	

	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadVenta();


	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadVenta(int cantidadVenta);


	/**
	 * @return the unidadMedida
	 */
	public String getUnidadDeMedida() ;


	/**
	 * @param unidadMedida the unidadMedida to set
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
	
	public Date getFecha();

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
