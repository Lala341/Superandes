package uniandes.isis2304.superandes.negocio;

public class PromocionUnidad implements VOPromocionUnidad {
	
	private long idPromocion;
	private int unidadVendidos;
	private int unidadPagados;
	
	/**
	 * Constructor por defecto
	 */
	public PromocionUnidad()
	{
		
	}
	
	/**
	 * @param idPromocion - id de la promocion
	 * @param unidadVendidos unidad vendidos
	 * @param unidadPagados unidad pagados
	 */
	public PromocionUnidad(long idPromocion, int unidadVendidos, int unidadPagados)
	{
		this.idPromocion = idPromocion;
		this.unidadVendidos = unidadVendidos;
		this.unidadPagados = unidadPagados;
	}
	/**
	 *  retorna el id
	 * @return id de la promocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}
	
	/**
	 * Modifica id de promocion
	 * @param idPromocion id de la promocion
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}
	
	/**
	 * Retorna unidad de vendidos
	 * @return unidadVendidos
	 */
	public int getUnidadVendidos() {
		return unidadVendidos;
	}
	
	/**
	 * Modifica unidad de vendidos
	 * @param unidadVendidos unidad de vendidos
	 */
	public void setUnidadVendidos(int unidadVendidos) {
		this.unidadVendidos = unidadVendidos;
	}
	
	/**
	 * Retorna unidad de pagados
	 * @return unidad de pagados
	 */
	public int getUnidadPagados() {
		return unidadPagados;
	}
	
	/**
	 * Modifica unidad de pagados
	 * @param unidadPagados
	 */
	public void setUnidadPagados(int unidadPagados) {
		this.unidadPagados = unidadPagados;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "PromocionUnidad [id=" + idPromocion + ", unidadVendidos=" + unidadVendidos + ", unidadPagados=" + unidadPagados +"]";
	}

}
