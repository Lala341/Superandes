package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * Interfaz para los métodos get de Producto
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOProducto {

	/**
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * @return the marca
	 */
	public String getMarca();

	/**
	 * @return the precioUnitario
	 */
	public double getPrecioUnitario();

	/**
	 * @return the presentacion
	 */
	public String getPresentacion();

	/**
	 * @return the precioPorUnidadDeMedida
	 */
	public double getPrecioPorUnidadDeMedida();

	/**
	 * @return the cantidad
	 */
	public int getCantidad();
	
	/**
	 * @return the unidadDeMedida
	 */
	public String getUnidadDeMedida();

	/**
	 * @return the especificacionDeEmpaquetado
	 */
	public String getEspecificacionDeEmpaquetado();

	/**
	 * @return the codigoDeBarras
	 */
	public String getCodigoDeBarras();

	/**
	 * @return the estado
	 */
	public boolean isEstado();

	/**
	 * @return the categoria
	 */
	public long getIdCategoria();

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @return the estantes
	 */
	public ArrayList<Estante> getEstantes();

	/**
	 * @return the bodegas
	 */
	public ArrayList<Bodega> getBodegas();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Producto
	 */
	public String toString();

}
