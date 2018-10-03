package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los m�todos get de Bodega.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOFactura {

	

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public void setId(long id);
	
	/**
	 * @return the textoFactura
	 */
	public String getTextoFactura() ;
	/**
	 * @param textoFactura the textoFactura to set
	 */
	public void setTextoFactura(String textoFactura);
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
