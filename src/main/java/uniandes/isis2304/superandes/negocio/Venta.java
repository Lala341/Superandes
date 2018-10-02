package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:46
 */
public class Venta implements VOVenta{

	private long id;
	private String fecha;
	private String formaDePago;
	private double valorTotal;
	private  long factura;
	

	public Venta(){

	}

	public Venta(long id,String fecha, String formaDePago, double valorTotal,long factura){

		this.id=id;
		this.fecha=fecha;
		this.formaDePago=formaDePago;
		this.valorTotal=valorTotal;
		this.factura=factura;
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
	 * @return the formaDePago
	 */
	public String getFormaDePago() {
		return formaDePago;
	}


	/**
	 * @param formaDePago the formaDePago to set
	 */
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
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
	 * @return the factura
	 */
	public long getFactura() {
		return factura;
	}


	/**
	 * @param factura the factura to set
	 */
	public void setFactura(long factura) {
		this.factura = factura;
	}


	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Venta [id=" + id + ", fecha=" + fecha + ", formaDePago=" + formaDePago + ", valorTotal=" + valorTotal
				+ ", factura=" + factura  +"]";
	}

	

}