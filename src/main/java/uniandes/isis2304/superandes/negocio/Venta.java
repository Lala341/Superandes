package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:46
 */
public class Venta implements VOVenta{

	public long id;
	public String fecha;
	public String formaPago;
	public double valorTotal;
	public long consumidor;
	public long sucursal;
	
	
	

	public Venta(){

	}

	public Venta(long id,String fecha, String formaDePago, double valorTotal,long consumidor, long sucursal){

		this.id=id;
		this.fecha=fecha;
		this.formaPago=formaDePago;
		this.valorTotal=valorTotal;
		this.consumidor=consumidor;
		this.sucursal=sucursal;
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return the formaPago
	 */
	public String getFormaDePago() {
		return formaPago;
	}


	/**
	 * @param formaPago the formaPago to set
	 */
	public void setFormaDePago(String formaDePago) {
		this.formaPago = formaDePago;
	}


	/**
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}


	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	


	/**
	 * @return the consumidor
	 */
	public long getConsumidor() {
		return consumidor;
	}

	/**
	 * @param consumidor the consumidor to set
	 */
	public void setConsumidor(long consumidor) {
		this.consumidor = consumidor;
	}

	/**
	 * @return the sucursal
	 */
	public long getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "VentaTest [id=" + id + ", fecha=" + fecha + ", formaPago=" + formaPago + ", valorTotal=" + valorTotal
				+"]";
	}

	

}