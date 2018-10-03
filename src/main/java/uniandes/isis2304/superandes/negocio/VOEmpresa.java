package uniandes.isis2304.superandes.negocio;

public interface VOEmpresa {

	/**
	 * 
	 * @return el id de consumidor
	 */
	public long getIdConsumidor();
	
	/**
	 * @return the direccion
	 */
	public String getDireccion();

	/**
	 * @return the nit
	 */
	public long getNit();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Empresa
	 */
	public String toString();

}
