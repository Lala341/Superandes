package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:34
 */
public class Factura implements VOFactura {

	private long id;

	public Factura(){

	}
	public Factura(long id){
		this.id=id;
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
		return "Factura [id=" + id  +  "]";
	}
	

}