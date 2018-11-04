package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;
/**
 * Interfaz para los métodos get de Estante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOEstante {

	/**
	 * @return the peso
	 */
	public double getPeso() ;

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso);

	/**
	 * @return the volumen
	 */
	public double getVolumen();

	/**
	 * @param volumen the volumen to set
	 */
	public void setVolumen(double volumen);

	/**
	 * @return the capacidadTotal
	 */
	public int getCapacidadTotal();

	/**
	 * @param capacidadTotal the capacidadTotal to set
	 */
	public void setCapacidadTotal(int capacidadTotal);

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos();

	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidadProductos(int cantidadProductos);

	/**
	 * @return the nivelDeAbastecimiento
	 */
	public int getNivelDeAbastecimiento();

	/**
	 * @param nivelDeAbastecimiento the nivelDeAbastecimiento to set
	 */
	public void setNivelDeAbastecimiento(int nivelDeAbastecimiento) ;

	/**
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre);

	/**
	 * @return the equipamientoAdicional
	 */
	public String getEquipamientoAdicional();

	/**
	 * @param equipamientoAdicional the equipamientoAdicional to set
	 */
	public void setEquipamientoAdicional(String equipamientoAdicional) ;

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public void setId(long id);

	/**
	 * @return the sucursal
	 */
	public long getIdSucursal();

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setIdSucursal(long idSucursal);

	

	
	/**
	 * @return the nivelReorden
	 */
	public long getNivelReorden() ;

	/**
	 * @param nivelReorden the nivelReorden to set
	 */
	public void setNivelReorden(long nivelReorden);
	
	public String getTipoProducto();

	public void setTipoProducto(String tipoProducto);

	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Estante
	 */
	public String toString();

}
