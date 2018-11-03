package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOBodega {

	/**
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre);
	/**
	 * @return the volumen de la Bodega
	 * 
	 */
	public double getVolumen();

	/**
	 * @return the peso de la Bodega
	 */
	public double getPeso();

	/**
	 * @return the capacidadTotal de la Bodega
	 */
	public int getCapacidadTotal();

	/**
	 * @return the cantidadProductos de la Bodega
	 */
	public int getCantidadProductos();

	/**
	 * @return the id de la Bodega
	 */
	public long getId();

	
	/**
	 * @return the sucursal of BOdega
	 */
	public long getIdSucursal();

	public double getNivelDeReorden();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
