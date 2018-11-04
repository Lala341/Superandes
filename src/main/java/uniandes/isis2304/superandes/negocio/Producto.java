package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:40
 */
public class Producto implements VOProducto {

	public String nombre;
	public String marca;
	public String presentacion;
	public double precioPorUnidadMedida;
	public String unidadMedida;
	public String especificacionesEmpaquetado;
	public String codigoBarras;
	public boolean estado1;
	public String estado;
	
	public long categoria;
	public long id;
	

	public Producto(){

	}
	
	/**
	 * @param idProducto - El identificador de la producto
	 * @param nombre - El nombre de la producto
	 * @param categoria - El identificador del tipoconsumidor de categoria de la producto
	 * @param cantidad - cantidad de producto
	 * @param codigoBarras el codigo de barras
	 * @param especificacionesEmpaquetado especificacion del empaquetado
	 * @param estado1 estado1 del producto
	 * @param marca marca del producto
	 * @param precioPorUnidadMedida precio por unidad medida
	 * @param precioUnitario precio unitario 
	 * @param presentacion presentacion
	 * @param unidadMedida unidad de medida
	 */
	public Producto (long idProducto, String nombre, long idCategoria,  String codigoDeBarras, String especificacionDeEmpaquetado, boolean estado, String marca, double precioPorUnidadMedida,  String presentacion, String unidadDeMedida) 
	{
		id = idProducto;
		this.nombre = nombre;
		this.marca = marca;
		this.presentacion = presentacion;
		this.precioPorUnidadMedida = precioPorUnidadMedida;
		this.especificacionesEmpaquetado = especificacionDeEmpaquetado;
		this.codigoBarras = codigoDeBarras;
		this.estado1 = estado;
		this.categoria = idCategoria;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	

	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return the precioPorUnidadMedida
	 */
	public double getPrecioPorUnidadDeMedida() {
		return precioPorUnidadMedida;
	}

	/**
	 * @param precioPorUnidadMedida the precioPorUnidadMedida to set
	 */
	public void setPrecioPorUnidadDeMedida(double precioPorUnidadDeMedida) {
		this.precioPorUnidadMedida = precioPorUnidadDeMedida;
	}

	

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadDeMedida) {
		this.unidadMedida = unidadDeMedida;
	}

	/**
	 * @return the especificacionesEmpaquetado
	 */
	public String getEspecificacionesEmpaquetado() {
		return especificacionesEmpaquetado;
	}

	/**
	 * @param especificacionesEmpaquetado the especificacionesEmpaquetado to set
	 */
	public void setEspecificacionesEmpaquetado(String especificacionDeEmpaquetado) {
		this.especificacionesEmpaquetado = especificacionDeEmpaquetado;
	}

	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoDeBarras) {
		this.codigoBarras = codigoDeBarras;
	}

	/**
	 * @return the estado1
	 */
	public String getEstado() {
		return estado;
	}

	

	/**
	 * @return the categoria
	 */
	public long getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(long idCategoria) {
		this.categoria = idCategoria;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	

	

	/**
	 * @return the precioPorUnidadMedida
	 */
	public double getPrecioPorUnidadMedida() {
		return precioPorUnidadMedida;
	}

	/**
	 * @param precioPorUnidadMedida the precioPorUnidadMedida to set
	 */
	public void setPrecioPorUnidadMedida(double precioPorUnidadMedida) {
		this.precioPorUnidadMedida = precioPorUnidadMedida;
	}

	/**
	 * @return the estado1
	 */
	public boolean isEstado1() {
		return estado1;
	}

	/**
	 * @param estado1 the estado1 to set
	 */
	public void setEstado1(boolean estado1) {
		this.estado1 = estado1;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del Producto
	 */
	public String toString() 
	{
		return "Producto [id=" + id + ", nombre=" + nombre + ", marca=" + marca 
				+ ", precioPorUnidadMedida=" + precioPorUnidadMedida +  ", categoria=" + categoria 
				+ ", unidadMedida=" + unidadMedida + ", especificacionesEmpaquetado=" + especificacionesEmpaquetado 
				+ ", codigoBarras=" + codigoBarras + ", estado1=" + estado1 + "]";
	}

	@Override
	public void setEstado(boolean estado) {
		// TODO Auto-generated method stub
		
	}
	

}