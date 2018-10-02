package uniandes.isis2304.superandes.negocio;

public interface VOProductoBodega {

	/**
	 * 
	 * @return id del estante
	 */
	public long getIdBodega();

	/**
	 * idProducto
	 * @return idProducto id del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de ProductoBodega
	 */
	public String toString();
}