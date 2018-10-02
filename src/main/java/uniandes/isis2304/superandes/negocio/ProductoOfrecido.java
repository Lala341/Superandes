package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:40
 */
public class ProductoOfrecido {

	private double precioProveedor;
	private int calificacionTotal;
	private int calidad;
	private int cumplimiento;
	private Producto producto;
	private Proveedores proveedor;
	
	public ProductoOfrecido(){

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
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the proveedor
	 */
	public Proveedores getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	

}