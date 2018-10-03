package uniandes.isis2304.superandes.negocio;

public interface VOPromocionCantidad {

	/**
	 *  retorna el id
	 * @return id de la promocion
	 */
	public long getIdPromocion();

	/**
	 * Retorna cantidad de vendidos
	 * @return cantidadVendidos
	 */
	public int getCantidadVendidos();

	/**
	 * Retorna cantidad de pagados
	 * @return cantidad de pagados
	 */
	public int getCantidadPagados();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString();


}
