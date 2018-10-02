package uniandes.isis2304.superandes.negocio;

import java.util.Date;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:38
 */
public class Perecedero implements VOPerecedero {

	private long id;
	private Date fechaDeVencimiento;
	private String tipoCategoriaPerecedera;

	public Perecedero(){

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
	 * @return the fechaDeVencimiento
	 */
	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	/**
	 * @param fechaDeVencimiento the fechaDeVencimiento to set
	 */
	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	/**
	 * @return the tipoCategoriaPerecedera
	 */
	public String getTipoCategoriaPerecedera() {
		return tipoCategoriaPerecedera;
	}

	/**
	 * @param tipoCategoriaPerecedera the tipoCategoriaPerecedera to set
	 */
	public void setTipoCategoriaPerecedera(String tipoCategoriaPerecedera) {
		this.tipoCategoriaPerecedera = tipoCategoriaPerecedera;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString() 
	{
		return "Perecedero [id=" + id + ", fechaDeVencimiento=" + fechaDeVencimiento + ", tipoDeCategoriaPerecedera=" + tipoCategoriaPerecedera + "]";
	}

}