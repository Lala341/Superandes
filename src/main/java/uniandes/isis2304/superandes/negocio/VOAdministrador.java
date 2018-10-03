package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOAdministrador {


	/**
	 * @return the cantidadDeRecompra
	 */
	public int getCantidadDeRecompra();
	/**
	 * @param cantidadDeRecompra the cantidadDeRecompra to set
	 */
	public void setCantidadDeRecompra(int cantidadDeRecompra) ;
	/**
	 * @return the id
	 */
	public long getId();
	/**
	 * @param id the id to set
	 */
	public void setId(long id);
	/**
	 * @return the usuario
	 */
	public String getUsuario();
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) ;
	/**
	 * @return the contrasenha
	 */
	public String getContrasenha();
	
	/**
	 * @param contrasenha the contrasenha to set
	 */
	public void setContrasenha(String contrasenha);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
