package uniandes.isis2304.superandes.negocio;

public class PromocionParteDescuento implements VOPromocionParteDescuento {
	
	private long id;
	private double descuento;
	private int unidadVendidos;
	
	/**
	 * Constructor por defecto
	 */
	public PromocionParteDescuento()
	{
		
	}
	
	/**
	 * 
	 * @param id id de la promocion
	 * @param descuento descuento de la promocion
	 */
	public PromocionParteDescuento (long id, double descuento, int unidadVendidos)
	{
		this.id = id;
		this.descuento = descuento;
		this.unidadVendidos = unidadVendidos;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Descuento
	 * @return descuento el descuento
	 */
	public double getDescuento() {
		return descuento;
	}
	
	/**
	 * Modificar el descuento
	 * @param descuento el descuento
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
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
	 * @return Una cadena de caracteres con todos los atributos 
	 */
	public String toString() 
	{
		return "PromocionParteDescuento [id=" + id + ", descuento=" + descuento + ", unidadVendidas=" + unidadVendidos +"]";
	}
}
