package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:42
 */
public class ProductoVenta implements VOProductoVenta{

	private long venta;
	private int cantidadVenta;
	private String unidadDeMedida;
	private long producto;
	
	
	public ProductoVenta(){

	}

	public ProductoVenta(long venta, int cantidadVenta, String unidadDeMedida, long producto){
		this.venta=venta;
		this.cantidadVenta=cantidadVenta;
		this.unidadDeMedida=unidadDeMedida;
		this.producto=producto;

	}

	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadVenta() {
		return cantidadVenta;
	}


	/**
	 * @param cantidadProducto the cantidadProducto to set
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
	 * @return the venta
	 */
	public long getVenta() {
		return venta;
	}

	/**
	 * @param venta the venta to set
	 */
	public void setVenta(long venta) {
		this.venta = venta;
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
		return "ProductoVenta [venta=" + venta + ", producto=" + producto + ", cantidadProducto=" + cantidadVenta + ", unidadMedida=" + unidadDeMedida
				+"]";
	}

}