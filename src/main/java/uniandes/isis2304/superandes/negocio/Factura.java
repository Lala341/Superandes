package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:34
 */
public class Factura implements VOFactura {

	private long id;
	private String textoFactura;

	public Factura(){

	}
	public Factura(long id, String textoFactura){
		this.id=id;
		this.textoFactura=textoFactura;
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

	/**
	 * @return the textoFactura
	 */
	public String getTextoFactura() {
		return textoFactura;
	}
	/**
	 * @param textoFactura the textoFactura to set
	 */
	public void setTextoFactura(String textoFactura) {
		this.textoFactura = textoFactura;
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