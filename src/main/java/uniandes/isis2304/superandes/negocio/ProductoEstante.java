package uniandes.isis2304.superandes.negocio;

public class ProductoEstante implements VOProductoEstante {

	private long idEstante;
	private long idProducto;
	private int cantidadProducto;
	
	public ProductoEstante()
	{
		
	}
	
	/**
	 * @param idEstante - El identificador del estante
	 * @param idProducto - El identificador de la producto
	 */
	public ProductoEstante(long idEstante, long idProducto, int cant) 
	{
		this.idEstante = idEstante;
		this.idProducto = idProducto;
		this.cantidadProducto=cant;
	}
	
	/**
	 * 
	 * @return id del estante
	 */
	public long getIdEstante() {
		return idEstante;
	}
	
	/**
	 * Modifica el id del estante
	 * @param idEstante id del estante
	 */
	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}
	
	/**
	 * idProducto
	 * @return idProducto id del producto
	 */
	public long getIdProducto() {
		return idProducto;
	}
	
	/**
	 * Modifica el id del producto
	 * @param idProducto id del producto
	 */
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de ProductoEstante
	 */
	public String toString() 
	{
		return "ProductoEstante [idEstante=" + idEstante + ", idProducto=" + idProducto + "cantidadProducto:"+cantidadProducto+"]";
	}
	
	
	
}
