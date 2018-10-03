package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:44
 */
public class Proveedores implements VOProveedores{

	public long NIT;
	public String nombre;

	public Proveedores(){

	}
	public Proveedores(long nit,String nombre){

		this.NIT=nit;
		this.nombre=nombre;
	}

	/**
	 * @return the NIT
	 */
	public long getNit() {
		return NIT;
	}

	/**
	 * @param NIT the NIT to set
	 */
	public void setNit(long nit) {
		this.NIT = nit;
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

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "Proveedor [NIT=" + NIT + ", nombre=" + nombre +  "]";
	}

	

}