package uniandes.isis2304.superandes.negocio;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:27
 */
public class Categoria {

	public long id;
	public String nombre;
	public String tipoAlmacenamiento;
	public String tipoManejo;
	
	public Categoria(){

	}
	
	/**
	 * @param idCategoria - El identificador de la categoria
	 * @param nombre - El nombre de la categoria
	 * @param tipoAlmacenamiento - El tipoconsumidor de almacenamiento de la categoria
	 * @param tipoManejo - El tipoconsumidor de manejo de la categoria
	 */
	public Categoria (long id, String nombre, String tipoDeAlmacenamiento, String tipoDeManejo) 
	{
		this.id = id;
		this.nombre = nombre;
		this.tipoAlmacenamiento = tipoDeAlmacenamiento;
		this.tipoAlmacenamiento = tipoDeManejo;
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
	 * @return el tipoAlmacenamiento
	 */
	public String getTipoDeAlmacenamiento() {
		return tipoAlmacenamiento;
	}
	
	/**
	 * @param tipoAlmacenamiento modifica el tipoconsumidor de almacenamiento 
	 */
	public void setTipoDeAlmacenamiento(String tipoDeAlmacenamiento) {
		this.tipoAlmacenamiento = tipoDeAlmacenamiento;
	}
	
	/**
	 * @return el tipoManejo
	 */
	public String getTipoDeManejo() {
		return tipoManejo;
	}
	
	/**
	 * @param tipoManejo modifica el tipoconsumidor de manejo
	 */
	public void setTipoDeManejo(String tipoDeManejo) {
		this.tipoManejo = tipoDeManejo;
	}
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Categoria
	 */
	public String toString() 
	{
		return "Categoria [id=" + id + ", nombre=" + nombre + ", tipoAlmacenamiento=" + tipoAlmacenamiento + ", tipoManejo=" + tipoManejo
				+ "]";
	}
	

	
}