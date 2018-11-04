package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:42
 */
public class ProductoCarritoCompras implements VOProductoCarritoCompras {

	public long carritoCompras;
	public int cantidadProducto;
	public String unidadMedida;
	public long producto;
	
	
	public ProductoCarritoCompras(){

	}

	public ProductoCarritoCompras(long carritoCompras,int cantidadVenta,String unidadDeMedida,long producto){
		this.carritoCompras=carritoCompras;
		this.cantidadProducto=cantidadVenta;
		this.unidadMedida=unidadDeMedida;
		this.producto=producto;

	}

	/**
	 * @return the cantidadProducto
	 */
	public int getCantidadProducto() {
		return cantidadProducto;
	}


	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(int cantidadVenta) {
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
		return "ProductoCarritoCompras [carritoCompras=" + carritoCompras + ", producto=" + producto + ", cantidadProducto=" + cantidadProducto + ", unidadMedida=" + unidadMedida
				+"]";
	}
}