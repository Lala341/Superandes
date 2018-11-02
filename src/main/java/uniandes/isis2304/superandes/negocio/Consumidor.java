package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:29
 */
public class Consumidor implements VOConsumidor {

	private long id;
	private String nombre;
	private String correoElectronico;
	private String tipo;
	public Consumidor(){

	}
	public Consumidor(long id, String nombre, String correoElectronico, String tipo){
		this.id=id;
		this.nombre=nombre;
		this.correoElectronico=correoElectronico;
		this.tipo=tipo;
	}
	
	

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "Consumidor [id=" + id + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", tipo=" + tipo
				+  "]";
	}
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}