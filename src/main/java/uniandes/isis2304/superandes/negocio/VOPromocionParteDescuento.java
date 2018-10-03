package uniandes.isis2304.superandes.negocio;

public interface VOPromocionParteDescuento {
	
	
	/**
	 * @return the id
	 */
	public long getId();
	
	/**
	 * Descuento
	 * @return descuento el descuento
	 */
	public double getDescuento();
	
	/**
	 * Retorna unidad de vendidos
	 * @return unidadVendidos
	 */
	public int getUnidadVendidos();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString();
}
