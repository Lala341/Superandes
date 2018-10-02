package uniandes.isis2304.superandes.negocio;

public class AdministradorSucursal implements VOAdministradorSucursal {
	
	private long administrador;
	
	private long sucursal;
	
	

	/**
	 * @return the administrador
	 */
	public long getAdministrador() {
		return administrador;
	}



	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador(long administrador) {
		this.administrador = administrador;
	}



	/**
	 * @return the sucursal
	 */
	public long getSucursal() {
		return sucursal;
	}



	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}



	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "AdministradorProducto [administrador=" + administrador +", sucursal=" + sucursal+"]";
	}
}
