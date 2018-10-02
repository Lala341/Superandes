package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProveedores {

	
	/**
	 * @return the nit
	 */
	public int getNit();
	/**
	 * @param nit the nit to set
	 */
	public void setNit(int nit);

	/**
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
