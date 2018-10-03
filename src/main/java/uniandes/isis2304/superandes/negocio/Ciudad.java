package uniandes.isis2304.superandes.negocio;


/**
 * 
 * @version 1.0
 * @created 30-sep-2018 10:00:28
 */
public class Ciudad implements VOCiudad {

	private long id;
	private String nombre;
	
	public Ciudad(){

	}
	
	public Ciudad(long id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	
	}

	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Ciudad [id=" + id +",nombre=" + nombre+"]";
	}

	

}