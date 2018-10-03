package uniandes.isis2304.superandes.negocio;

public interface VOPromocionUnidad {

	/**
	 *  retorna el id
	 * @return id de la promocion
	 */
	public long getIdPromocion();

	/**
	 * Retorna unidad de vendidos
	 * @return unidadVendidos
	 */
	public int getUnidadVendidos();

	/**
	 * Retorna unidad de pagados
	 * @return unidad de pagados
	 */
	public int getUnidadPagados();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString();


}
