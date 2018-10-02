package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:35
 */
public class Fidelizacion implements VOFidelizacion{

	private long id;
	private int cantidadPuntos;

	public Fidelizacion(){

	}
	
	public Fidelizacion(long id,int cantidadPuntos){
		this.id=id;
		this.cantidadPuntos=cantidadPuntos;
	}

	/**
	 * @return the cantidadPuntos
	 */
	public int getCantidadPuntos() {
		return cantidadPuntos;
	}

	/**
	 * @param cantidadPuntos the cantidadPuntos to set
	 */
	public void setCantidadPuntos(int cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
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
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "Fidelizacion [id=" + id + ", cantidadPuntos=" + cantidadPuntos +  "]";
	}

}