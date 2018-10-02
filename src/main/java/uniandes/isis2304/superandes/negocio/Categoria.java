package uniandes.isis2304.superandes.negocio;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:27
 */
public class Categoria {

	private long id;
	private String nombre;
	private String tipoDeAlmacenamiento;
	private String tipoDeManejo;
	
	public Categoria(){

	}
	
	/**
	 * @param idCategoria - El identificador de la categoria
	 * @param nombre - El nombre de la categoria
	 * @param tipoDeAlmacenamiento - El tipo de almacenamiento de la categoria
	 * @param tipoDeManejo - El tipo de manejo de la categoria
	 */
	public Categoria (long idCategoria, String nombre, String tipoDeAlmacenamiento, String tipoDeManejo) 
	{
		id = idCategoria;
		this.nombre = nombre;
		this.tipoDeAlmacenamiento = tipoDeAlmacenamiento;
		this.tipoDeAlmacenamiento = tipoDeManejo;
	}
	/**
	 * 
	 * @return el id
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
	 * @return the Nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre modifica el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return el tipoDeAlmacenamiento
	 */
	public String getTipoDeAlmacenamiento() {
		return tipoDeAlmacenamiento;
	}
	
	/**
	 * @param tipoDeAlmacenamiento modifica el tipo de almacenamiento 
	 */
	public void setTipoDeAlmacenamiento(String tipoDeAlmacenamiento) {
		this.tipoDeAlmacenamiento = tipoDeAlmacenamiento;
	}
	
	/**
	 * @return el tipoDeManejo
	 */
	public String getTipoDeManejo() {
		return tipoDeManejo;
	}
	
	/**
	 * @param tipoDeManejo modifica el tipo de manejo
	 */
	public void setTipoDeManejo(String tipoDeManejo) {
		this.tipoDeManejo = tipoDeManejo;
	}
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Categoria
	 */
	public String toString() 
	{
		return "Categoria [id=" + id + ", nombre=" + nombre + ", tipoDeAlmacenamiento=" + tipoDeAlmacenamiento + ", tipoDeManejo=" + tipoDeManejo
				+ "]";
	}
	

	
}