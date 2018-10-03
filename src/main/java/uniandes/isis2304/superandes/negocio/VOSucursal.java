
package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * Interfaz para los métodos get de Sucursal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOSucursal {

	/**
	 * @return the tamanho
	 */
	public double getTamanho() ;


	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(double tamanho) ;


	/**
	 * @return the tipoDeMercado
	 */
	public String getTipoMercado() ;


	/**
	 * @param tipoDeMercado the tipoDeMercado to set
	 */
	public void setTipoMercado(String tipoDeMercado) ;


	/**
	 * @return the ventasTotales
	 */
	public double getVentasTotales() ;


	/**
	 * @param ventasTotales the ventasTotales to set
	 */
	public void setVentasTotales(double ventasTotales);

	/**
	 * @return the id
	 */
	public long getId();


	/**
	 * @param id the id to set
	 */
	public void setId(long id) ;


	/**
	 * @return the ciudad
	 */
	public long getCiudad() ;


	/**
	 * @param idCiudad the ciudad to set
	 */
	public void setCiudad(long idCiudad) ;


	
	
	/**
	 * @return the direccion
	 */
	public String getDireccion() ;

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) ;

	/**
	 * @return the nombre
	 */
	public String getNombre() ;

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString();

}
