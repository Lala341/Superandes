package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:29
 */
public class Consumidor {

	private String nombre;
	private String correoElectronico;
	private Fidelizacion fidelizacion;
	private ArrayList<Venta> ventas;

	public Consumidor(){

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
	 * @return the fidelizacion
	 */
	public Fidelizacion getFidelizacion() {
		return fidelizacion;
	}

	/**
	 * @param fidelizacion the fidelizacion to set
	 */
	public void setFidelizacion(Fidelizacion fidelizacion) {
		this.fidelizacion = fidelizacion;
	}

	/**
	 * @return the ventas
	 */
	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	/**
	 * @param ventas the ventas to set
	 */
	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	

}