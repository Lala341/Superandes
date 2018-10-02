package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:35
 */
public class NoPerecedero implements VONoPerecedero {

	private long id;
	private String tipoCategoriaNoPerecedera;

	public NoPerecedero(){

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