package uniandes.isis2304.superandes.negocio;

public class PromocionDescuento implements VOPromocionDescuento {
	
	private long id;
	private double descuento;
	
	/**
	 * Constructor por defecto
	 */
	public PromocionDescuento()
	{
		
	}
	
	/**
	 * 
	 * @param id id de la promocion
	 * @param descuento descuento de la promocion
	 */
	public PromocionDescuento (long id, double descuento)
	{
		this.id = id;
		this.descuento = descuento;
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
	 * @return Una cadena de caracteres con todos los atributos 
	 */
	public String toString() 
	{
		return "PromocionDescuento [id=" + id + ", descuento=" + descuento + "]";
	}
	
	
}
