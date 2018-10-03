package uniandes.isis2304.superandes.negocio;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:32
 */
public class Empresa extends Consumidor implements VOEmpresa {

	private long idConsumidor;
	private String direccion;
	private long nit;

	public Empresa(){

	}
	
	/**
	 * @param idConsumidor - El identificador del Consumidor
	 * @param nit - id de Empresa
	 * @param direccion direccion de Empresa
	 */
	public Empresa (long idConsumidor, long nit, String direccion) 
	{
		this.idConsumidor = idConsumidor;
		this.direccion = direccion;
		this.nit = nit;
	}
	
	/**
	 * 
	 * @return el id de consumidor
	 */
	public long getIdConsumidor() {
		return idConsumidor;
	}
	
	/**
	 * Modificar id consumidor
	 * @param idConsumidor id consumidor a modificar
	 */
	public void setIdConsumidor(long idConsumidor) {
		this.idConsumidor = idConsumidor;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the nit
	 */
	public long getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(long nit) {
		this.nit = nit;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la Categoria
	 */
	public String toString() 
	{
		return "Empresa [nit=" + nit + ", direccion=" + direccion + "]";
	}

}