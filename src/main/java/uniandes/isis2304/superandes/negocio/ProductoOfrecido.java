package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:40
 */
public class ProductoOfrecido implements VOProductoOfrecido {

	private double precioProveedor;
	private int calificacionTotal;
	private int calidad;
	private int cumplimiento;
	private long producto;
	private long proveedor;
	private long id;
	
	public ProductoOfrecido(){

	}
	public ProductoOfrecido(long id,double precioProveedor, int calificacionTotal, int calidad, int cumplimiento, long producto, long proveedor){
		this.id=id;
		this.precioProveedor=precioProveedor;
		this.calificacionTotal=calificacionTotal;
		this.calidad=calidad;
		this.cumplimiento=cumplimiento;
		this.producto=producto;
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
	public int getCalidad() {
		return calidad;
	}

	/**
	 * @param calidad the calidad to set
	 */
	public void setCalidad(int calidad) {
		this.calidad = calidad;
	}

	/**
	 * @return the cumplimiento
	 */
	public int getCumplimiento() {
		return cumplimiento;
	}

	/**
	 * @param cumplimiento the cumplimiento to set
	 */
	public void setCumplimiento(int cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	/**
	 * @return the producto
	 */
	public long getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(long producto) {
		this.producto = producto;
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
		return "ProductoOfrecido [id=" + id + ", calidad=" + calidad  + ", calificacion=" + calificacionTotal  + ", cumplimiento=" + cumplimiento  + ", precioProveedor=" + precioProveedor  + ", producto=" + producto 
				 + ", proveedor=" + proveedor+  "]";
	}

	

}