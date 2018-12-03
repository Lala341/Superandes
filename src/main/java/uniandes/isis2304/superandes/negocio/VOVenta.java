package uniandes.isis2304.superandes.negocio;

import java.sql.Date;

/**
 * Interfaz para los m�todos get de Bodega.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOVenta {

	

	/**
	 * @return the id
	 */
	public long getId();


	/**
	 * @param id the id to set
	 */
	public void setId(long id);


	public String getFecha();


	/**
	 * @return the formaPago
	 */
	public String getFormaDePago();


	/**
	 * @param formaPago the formaPago to set
	 */
	public void setFormaDePago(String formaDePago);


	/**
	 * @return the valorTotal
	 */
	public double getValorTotal();


	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal);


	

	/**
	 * @return the consumidor
	 */
	public long getConsumidor() ;

	/**
	 * @param consumidor the consumidor to set
	 */
	public void setConsumidor(long consumidor);
	
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
