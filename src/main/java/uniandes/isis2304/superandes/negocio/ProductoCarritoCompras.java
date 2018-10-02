package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:42
 */
public class ProductoCarritoCompras implements VOProductoCarritoCompras {

	private long carritoCompras;
	private int cantidadVenta;
	private String unidadDeMedida;
	private long producto;
	
	
	public ProductoCarritoCompras(){

	}

	public ProductoCarritoCompras(long carritoCompras,int cantidadVenta,String unidadDeMedida,long producto){
		this.carritoCompras=carritoCompras;
		this.cantidadVenta=cantidadVenta;
		this.unidadDeMedida=unidadDeMedida;
		this.producto=producto;

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
	 * @return the carritoCompras
	 */
	public long getCarritoCompras() {
		return carritoCompras;
	}

	/**
	 * @param carritoCompras the carritoCompras to set
	 */
	public void setCarritoCompras(long carritoCompras) {
		this.carritoCompras = carritoCompras;
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

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "ProductoCarritoCompras [carritoCompras=" + carritoCompras + ", producto=" + producto + ", cantidadVenta=" + cantidadVenta + ", unidadDeMedida=" + unidadDeMedida
				+"]";
	}
}