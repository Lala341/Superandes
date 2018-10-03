package uniandes.isis2304.superandes.negocio;

public interface VOPromocionDescuento {
	
	/**
	 * @return the id
	 */
	public long getId();
	
	/**
	 * Descuento
	 * @return descuento el descuento
	 */
	public double getDescuento();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString();
}
