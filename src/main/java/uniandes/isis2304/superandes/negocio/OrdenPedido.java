package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:37
 */
public class OrdenPedido implements VOOrdenPedido {

	public String fechaEntrega;
	public int calificacion;
	public String estado;
	public String fecha;
	public long id;
	public long proveedor;
	public int cantidadProducto;
	public String unidadMedida;
	public long productoOfrecido;
	public long sucursal;
	
	public OrdenPedido(){

	}

	public OrdenPedido(String fechaEntrega,int calificacion,String estado, String fecha,long id, long proveedor, int cantidadVenta, String unidadDeMedida,long productoOfrecido,long sucursal){
		this.fechaEntrega=fechaEntrega;
		this.calificacion=calificacion;
		this.estado=estado;
		this.fecha=fecha;
		this.id=id;
		this.proveedor=proveedor;
		this.cantidadProducto=cantidadVenta;
		this.unidadMedida=unidadDeMedida;
		this.productoOfrecido=productoOfrecido;
		this.sucursal=sucursal;
	}
	
	
	

	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadVenta() {
		return cantidadProducto;
	}

	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadVenta(int cantidadVenta) {
		this.cantidadProducto = cantidadVenta;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadDeMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadMedida = unidadDeMedida;
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
		return "OrdenPedido [id=" + id + ", cantidadProducto=" + cantidadProducto  + ", calificacion=" + calificacion  + ", estado=" + estado  + ", fecha=" + fecha  + ", fechaEntrega=" + fechaEntrega 
				 + ", proveedor=" + proveedor+  ", productoOfrecido=" + productoOfrecido+  ", sucursal=" + sucursal+  ", unidadMedida=" + unidadMedida+ "]";
	}
	

}