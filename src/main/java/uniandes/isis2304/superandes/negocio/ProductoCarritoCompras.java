package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:42
 */
public class ProductoCarritoCompras {

	private CarritoCompras carritoCompras;
	private int cantidadVenta;
	private String unidadDeMedida;
	private Producto producto;
	
	
	public ProductoCarritoCompras(){

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
	 * @return the carritoCompras
	 */
	public CarritoCompras getCarritoCompras() {
		return carritoCompras;
	}


	/**
	 * @param carritoCompras the carritoCompras to set
	 */
	public void setCarritoCompras(CarritoCompras carritoCompras) {
		this.carritoCompras = carritoCompras;
	}


}