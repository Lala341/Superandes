package uniandes.isis2304.superandes.negocio;



/**
 * @version 1.0
 * @created 30-sep-2018 10:00:35
 */
public class ProductoBodega implements VOProductoBodega {

	private long idBodega;
	private long idProducto;
	private int cantidadProducto;
	
	public ProductoBodega()
	{
		
	}
	
	/**
	 * @param idBodega - El identificador del bodega
	 * @param idProducto - El identificador de la producto
	 */
	public ProductoBodega( long idBodega, long idProducto, int cant) 
	{
		this.idBodega = idBodega;
		this.idProducto = idProducto;
		this.cantidadProducto= cant;
	}
	
	/**
	 * 
	 * @return id del bodega
	 */
	public long getIdBodega() {
		return idBodega;
	}
	
	/**
	 * Modifica el id del bodega
	 * @param idBodega id del bodega
	 */
	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
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
	 * @return Una cadena de caracteres con todos los atributos de la ProductoBodega
	 */
	public String toString() 
	{
		return "ProductoBodega [idBodega=" + idBodega + ", idProducto=" + idProducto + "]";
	}
	
	
	
}
