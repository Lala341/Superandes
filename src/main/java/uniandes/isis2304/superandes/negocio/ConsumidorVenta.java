package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:29
 */
public class ConsumidorVenta  {

	public long producto;
	public String nombre;
	public String correoElectronico;
	public String tipoconsumidor;
	public long venta;
	public String fecha;
	public String formaPago;
	public String unidadMedida;
	public long id;
	public int cantidadProducto;
	
	
	public double valorTotal;
	public long consumidor;
	public long sucursal;
	
	public ConsumidorVenta(){

	}
	public ConsumidorVenta(long id, String nombre, String correoElectronico, String tipo){
		this.id=id;
		this.nombre=nombre;
		this.correoElectronico=correoElectronico;
		this.tipoconsumidor=tipo;
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
	 * @return the tipoconsumidor
	 */
	public String getTipo() {
		return tipoconsumidor;
	}
	/**
	 * @param tipoconsumidor the tipoconsumidor to set
	 */
	public void setTipo(String tipo) {
		this.tipoconsumidor = tipo;
	}
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "ConsumidorVenta [id=" + id + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", tipoconsumidor=" + tipoconsumidor
				+ ", Producto: " + producto+ ", venta ="+ venta + ", fecha="+ fecha+ ", formaDePago="+ formaPago+ ", UnidadMedida="+ 
				unidadMedida+ ", CantidadProducto=" +cantidadProducto+ ", valorTotal=" + valorTotal + ", sucursal="+ sucursal+ "]";
	}
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}