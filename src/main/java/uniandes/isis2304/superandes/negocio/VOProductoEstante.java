package uniandes.isis2304.superandes.negocio;

public interface VOProductoEstante {

	/**
	 * 
	 * @return id del estante
	 */
	public long getIdEstante();

	/**
	 * idProducto
	 * @return idProducto id del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de ProductoEstante
	 */
	public String toString();
}
