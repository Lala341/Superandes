package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
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


	/**
	 * @return the fecha
	 */
	public String getFecha();


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha);


	/**
	 * @return the formaDePago
	 */
	public String getFormaDePago();


	/**
	 * @param formaDePago the formaDePago to set
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
	 * @return the factura
	 */
	public long getFactura();


	/**
	 * @param factura the factura to set
	 */
	public void setFactura(long factura);

	/**
	 * @return the consumidor
	 */
	public long getConsumidor() ;

	/**
	 * @param consumidor the consumidor to set
	 */
	public void setConsumidor(long consumidor);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
