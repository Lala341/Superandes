package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:40
 */
public class Producto implements VOProducto {

	private String nombre;
	private String marca;
	private double precioUnitario;
	private String presentacion;
	private double precioPorUnidadDeMedida;
	private int cantidad;
	private String unidadDeMedida;
	private String especificacionDeEmpaquetado;
	private String codigoDeBarras;
	private boolean estado;
	private long idCategoria;
	private long id;
	private ArrayList<Estante> estantes;
	private ArrayList<Bodega> bodegas;

	public Producto(){

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
	 * @return the precioUnitario
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
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
	 * @return the precioPorUnidadDeMedida
	 */
	public double getPrecioPorUnidadDeMedida() {
		return precioPorUnidadDeMedida;
	}

	/**
	 * @param precioPorUnidadDeMedida the precioPorUnidadDeMedida to set
	 */
	public void setPrecioPorUnidadDeMedida(double precioPorUnidadDeMedida) {
		this.precioPorUnidadDeMedida = precioPorUnidadDeMedida;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the unidadDeMedida
	 */
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	/**
	 * @param unidadDeMedida the unidadDeMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	/**
	 * @return the especificacionDeEmpaquetado
	 */
	public String getEspecificacionDeEmpaquetado() {
		return especificacionDeEmpaquetado;
	}

	/**
	 * @param especificacionDeEmpaquetado the especificacionDeEmpaquetado to set
	 */
	public void setEspecificacionDeEmpaquetado(String especificacionDeEmpaquetado) {
		this.especificacionDeEmpaquetado = especificacionDeEmpaquetado;
	}

	/**
	 * @return the codigoDeBarras
	 */
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	/**
	 * @param codigoDeBarras the codigoDeBarras to set
	 */
	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * @return the categoria
	 */
	public long getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the categoria to set
	 */
	public void setCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
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
	 * @return the estantes
	 */
	public ArrayList<Estante> getEstantes() {
		return estantes;
	}

	/**
	 * @param estantes the estantes to set
	 */
	public void setEstantes(ArrayList<Estante> estantes) {
		this.estantes = estantes;
	}

	/**
	 * @return the bodegas
	 */
	public ArrayList<Bodega> getBodegas() {
		return bodegas;
	}

	/**
	 * @param bodegas the bodegas to set
	 */
	public void setBodegas(ArrayList<Bodega> bodegas) {
		this.bodegas = bodegas;
	}
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Producto
	 */
	public String toString() 
	{
		return "Producto [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", precioUnitario=" + precioUnitario
				+ ", precioPorUnidadDeMedida=" + precioPorUnidadDeMedida + ", cantidad=" + cantidad + ", idCategoria=" + idCategoria 
				+ ", unidadDeMedida=" + unidadDeMedida + ", especificacionDeEmpaquetado=" + especificacionDeEmpaquetado 
				+ ", codigoDeBarras=" + codigoDeBarras + ", estado=" + estado + ", bodegas="+bodegas + ", estantes="+ estantes + "]";
	}
	

}