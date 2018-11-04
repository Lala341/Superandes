package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:35
 */
public class NoPerecedero implements VONoPerecedero {

	private long id;
	private long idCategoria;
	private String tipoCategoriaNoPerecedera;

	public NoPerecedero(){

	}
	
	
	/**
	 * @param idNoPerecedero - El identificador del noPerecedero
	 * @param tipoCategoriaNoPerecedera - el tipoconsumidor de cateogoria del producto noPerecedero
	 * @param idCategoria
	 */
	public NoPerecedero (long idNoPerecedero, String tipoCategoriaNoPerecedera, long idCategoria) 
	{
		id = idNoPerecedero;
		this.idCategoria = idCategoria;
		this.tipoCategoriaNoPerecedera = tipoCategoriaNoPerecedera;
	}
	/**
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id modifica el id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the Categoria
	 */
	public long getIdCategoria() {
		return idCategoria;
	}
	
	/**
	 * Modifica la categoria
	 * @param idCategoria la categoria
	 */
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	/**
	 * @return the tipoCategoriaNoPerecedera
	 */
	public String getTipoCategoriaNoPerecedera() {
		return tipoCategoriaNoPerecedera;
	}

	/**
	 * @param tipoCategoriaNoPerecedera the tipoCategoriaNoPerecedera to set
	 */
	public void setTipoCategoriaNoPerecedera(String tipoCategoriaNoPerecedera) {
		this.tipoCategoriaNoPerecedera = tipoCategoriaNoPerecedera;
	}
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString() 
	{
		return "NoPerecedero [id=" + id + ", tipoDeCategoriaNoPerecedera=" + tipoCategoriaNoPerecedera + "]";
	}

	
}