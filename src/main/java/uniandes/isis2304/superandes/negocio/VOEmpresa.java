package uniandes.isis2304.superandes.negocio;

public interface VOEmpresa {

	public long getIdConsumidor();
	
	/**
	 * Modificar id consumidor
	 * @param idConsumidor id consumidor a modificar
	 */
	public void setIdConsumidor(long idConsumidor);

	/**
	 * @return the direccion
	 */
	public String getDireccion();

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion);

	/**
	 * @return the NIT
	 */
	public long getNit();

	/**
	 * @param NIT the NIT to set
	 */
	public void setNit(long nit);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la Categoria
	 */
	public String toString() ;

}
