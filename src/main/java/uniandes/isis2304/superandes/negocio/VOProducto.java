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
	public String getNombre() ;

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre);

	/**
	 * @return the marca
	 */
	public String getMarca() ;

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) ;

	
	/**
	 * @return the presentacion
	 */
	public String getPresentacion() ;

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) ;

	/**
	 * @return the precioPorUnidadDeMedida
	 */
	public double getPrecioPorUnidadDeMedida() ;

	/**
	 * @param precioPorUnidadDeMedida the precioPorUnidadDeMedida to set
	 */
	public void setPrecioPorUnidadDeMedida(double precioPorUnidadDeMedida) ;

	
	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida();

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadDeMedida) ;

	/**
	 * @return the especificacionDeEmpaquetado
	 */
	public String getEspecificacionesEmpaquetado() ;

	/**
	 * @param especificacionDeEmpaquetado the especificacionDeEmpaquetado to set
	 */
	public void setEspecificacionesEmpaquetado(String especificacionDeEmpaquetado) ;

	/**
	 * @return the codigoDeBarras
	 */
	public String getCodigoBarras();

	/**
	 * @param codigoDeBarras the codigoDeBarras to set
	 */
	public void setCodigoBarras(String codigoDeBarras);

	/**
	 * @return the estado
	 */
	public String getEstado() ;

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado);

	/**
	 * @return the categoria
	 */
	public long getCategoria() ;

	/**
	 * @param idCategoria the categoria to set
	 */
	public void setCategoria(long idCategoria);

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public void setId(long id);
	
	/**
	 * @return the precioPorUnidadMedida
	 */
	public double getPrecioPorUnidadMedida();

	/**
	 * @param precioPorUnidadMedida the precioPorUnidadMedida to set
	 */
	public void setPrecioPorUnidadMedida(double precioPorUnidadMedida);

	/**
	 * @return the estado1
	 */
	public boolean isEstado1();

	/**
	 * @param estado1 the estado1 to set
	 */
	public void setEstado1(boolean estado1);

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado);
	/**
	 * @return Una cadena de caracteres con todos los atributos del Producto
	 */
	public String toString();

	
}
