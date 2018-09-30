package main.java.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:41
 */
public class ProductoOfrecidoTransaccion {

	private int cantidadVenta;
	private String unidadDeMedida;
	private ProductoOfrecido productoOfrecido;
	
	public ProductoOfrecidoTransaccion(){

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
	 * @return the productoOfrecido
	 */
	public ProductoOfrecido getProductoOfrecido() {
		return productoOfrecido;
	}

	/**
	 * @param productoOfrecido the productoOfrecido to set
	 */
	public void setProductoOfrecido(ProductoOfrecido productoOfrecido) {
		this.productoOfrecido = productoOfrecido;
	}

	

}