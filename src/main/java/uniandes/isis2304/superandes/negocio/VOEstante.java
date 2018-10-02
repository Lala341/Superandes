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
	public double getPeso();

	/**
	 * @return the volumen
	 */
	public double getVolumen();

	/**
	 * @return the capacidadTotal
	 */
	public int getCapacidadTotal();

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos();

	/**
	 * @return the nivelDeAbastecimiento
	 */
	public int getNivelDeAbastecimiento();

	/**
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * @return the equipamientoAdicional
	 */
	public String getEquipamientoAdicional();

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @return the sucursal
	 */
	public long getIdSucursal();

	/**
	 * @param productos the productos to set
	 */
	public ArrayList<Producto> getProductos();
	
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Estante
	 */
	public String toString();

}
