package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:37
 */
public class OrdenPedido implements VOOrdenPedido {

	private String fechaEntrega;
	private int calificacion;
	private String estado;
	private String fecha;
	private long id;
	private long proveedor;
	private int cantidadVenta;
	private String unidadDeMedida;
	private long productoOfrecido;
	private long sucursal;
	
	public OrdenPedido(){

	}

	public OrdenPedido(String fechaEntrega,int calificacion,String estado, String fecha,long id, long proveedor, int cantidadVenta, String unidadDeMedida,long productoOfrecido,long sucursal){
		this.fechaEntrega=fechaEntrega;
		this.calificacion=calificacion;
		this.estado=estado;
		this.fecha=fecha;
		this.id=id;
		this.proveedor=proveedor;
		this.cantidadVenta=cantidadVenta;
		this.unidadDeMedida=unidadDeMedida;
		this.productoOfrecido=productoOfrecido;
		this.sucursal=sucursal;
	}
	
	
	

	/**
	 * @return the cantidadVenta
	 */
	public int getCantidadVenta() {
		return cantidadVenta;
	}

	/**
	 * @param cantidadVenta the cantidadVenta to set
	 */
	public void setCantidadVenta(int cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	
	/**
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the calificacion
	 */
	public int getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * @return the proveedor
	 */
	public long getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(long proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the productoOfrecido
	 */
	public long getProductoOfrecido() {
		return productoOfrecido;
	}

	/**
	 * @param productoOfrecido the productoOfrecido to set
	 */
	public void setProductoOfrecido(long productoOfrecido) {
		this.productoOfrecido = productoOfrecido;
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
	public String toString() 
	{
		return "OrdenPedido [id=" + id + ", cantidadVenta=" + cantidadVenta  + ", calificacion=" + calificacion  + ", estado=" + estado  + ", fecha=" + fecha  + ", fechaEntrega=" + fechaEntrega 
				 + ", proveedor=" + proveedor+  ", productoOfrecido=" + productoOfrecido+  ", sucursal=" + sucursal+  ", unidadMedida=" + unidadDeMedida+ "]";
	}
	

}