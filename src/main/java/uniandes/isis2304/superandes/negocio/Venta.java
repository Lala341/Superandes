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
	private long consumidor;
	private long sucursal;
	
	

	public Venta(){

	}

	public Venta(long id,String fecha, String formaDePago, double valorTotal,long consumidor, long sucursal){

		this.id=id;
		this.fecha=fecha;
		this.formaDePago=formaDePago;
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
		return "Venta [id=" + id + ", fecha=" + fecha + ", formaDePago=" + formaDePago + ", valorTotal=" + valorTotal
				+"]";
	}

	

}