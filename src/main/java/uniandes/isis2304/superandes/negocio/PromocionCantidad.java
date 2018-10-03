package uniandes.isis2304.superandes.negocio;

public class PromocionCantidad {
	
	private long idPromocion;
	private int cantidadVendidos;
	private int cantidadPagados;
	
	/**
	 * Constructor por defecto
	 */
	public PromocionCantidad()
	{
		
	}
	
	/**
	 * @param idPromocion - id de la promocion
	 * @param cantidadVendidos cantidad vendidos
	 * @param cantidadPagados cantidad pagados
	 */
	public PromocionCantidad(long idPromocion, int cantidadVendidos, int cantidadPagados)
	{
		this.idPromocion = idPromocion;
		this.cantidadVendidos = cantidadVendidos;
		this.cantidadPagados = cantidadPagados;
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
	 * Retorna cantidad de vendidos
	 * @return cantidadVendidos
	 */
	public int getCantidadVendidos() {
		return cantidadVendidos;
	}
	
	/**
	 * Modifica cantidad de vendidos
	 * @param cantidadVendidos cantidad de vendidos
	 */
	public void setCantidadVendidos(int cantidadVendidos) {
		this.cantidadVendidos = cantidadVendidos;
	}
	
	/**
	 * Retorna cantidad de pagados
	 * @return cantidad de pagados
	 */
	public int getCantidadPagados() {
		return cantidadPagados;
	}
	
	/**
	 * Modifica cantidad de pagados
	 * @param cantidadPagados
	 */
	public void setCantidadPagados(int cantidadPagados) {
		this.cantidadPagados = cantidadPagados;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "PromocionCantidad [id=" + idPromocion + ", cantidadVendidos=" + cantidadVendidos + ", cantidadPagados=" + cantidadPagados +"]";
	}

}
