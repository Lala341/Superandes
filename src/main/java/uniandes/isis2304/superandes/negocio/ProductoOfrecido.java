package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:40
 */
public class ProductoOfrecido implements VOProductoOfrecido {

	public  long id;
	public double precioProveedor;
	public  int calificacionTotal;
	public  String calidad;
	public  String cumplimiento;
	public   long productoId;
	public   long proveedor;
	
	public ProductoOfrecido(){

	}
	public ProductoOfrecido(long id,double precioProveedor, int calificacionTotal, String calidad, String cumplimiento, long producto, long proveedor){
		this.id=id;
		this.precioProveedor=precioProveedor;
		this.calificacionTotal=calificacionTotal;
		this.calidad=calidad;
		this.cumplimiento=cumplimiento;
		this.productoId=producto;
		this.proveedor=proveedor;

	}

	/**
	 * @return the precioProveedor
	 */
	public double getPrecioProveedor() {
		return precioProveedor;
	}

	/**
	 * @param precioProveedor the precioProveedor to set
	 */
	public void setPrecioProveedor(double precioProveedor) {
		this.precioProveedor = precioProveedor;
	}

	/**
	 * @return the calificacionTotal
	 */
	public int getCalificacionTotal() {
		return calificacionTotal;
	}

	/**
	 * @param calificacionTotal the calificacionTotal to set
	 */
	public void setCalificacionTotal(int calificacionTotal) {
		this.calificacionTotal = calificacionTotal;
	}

	/**
	 * @return the calidad
	 */
	public String getCalidad() {
		return calidad;
	}

	/**
	 * @param calidad the calidad to set
	 */
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	/**
	 * @return the cumplimiento
	 */
	public String getCumplimiento() {
		return cumplimiento;
	}

	/**
	 * @param cumplimiento the cumplimiento to set
	 */
	public void setCumplimiento(String cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	/**
	 * @return the productoId
	 */
	public long getProductoId() {
		return productoId;
	}
	/**
	 * @param productoId the productoId to set
	 */
	public void setProductoId(long producto) {
		this.productoId = producto;
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
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "ProductoOfrecido [id=" + id + ", calidad=" + calidad  + ", calificacion=" + calificacionTotal  + ", cumplimiento=" + cumplimiento  + ", precioProveedor=" + precioProveedor  + ", productoId=" + productoId 
				 + ", proveedor=" + proveedor+  "]";
	}

	

}