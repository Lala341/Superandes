package main.java.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:44
 */
public class SuperAndes {

	private int cantidadDeRecompra;
	private long id;
	private Sucursal surcursal;
	private Promocion promocion;
	private ArrayList<OrdenDePedido> ordenesDePedido;

	public SuperAndes(){

	}

	/**
	 * @return the cantidadDeRecompra
	 */
	public int getCantidadDeRecompra() {
		return cantidadDeRecompra;
	}

	/**
	 * @param cantidadDeRecompra the cantidadDeRecompra to set
	 */
	public void setCantidadDeRecompra(int cantidadDeRecompra) {
		this.cantidadDeRecompra = cantidadDeRecompra;
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
	 * @return the surcursal
	 */
	public Sucursal getSurcursal() {
		return surcursal;
	}

	/**
	 * @param surcursal the surcursal to set
	 */
	public void setSurcursal(Sucursal surcursal) {
		this.surcursal = surcursal;
	}

	/**
	 * @return the promocion
	 */
	public Promocion getPromocion() {
		return promocion;
	}

	/**
	 * @param promocion the promocion to set
	 */
	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	/**
	 * @return the ordenesDePedido
	 */
	public ArrayList<OrdenDePedido> getOrdenesDePedido() {
		return ordenesDePedido;
	}

	/**
	 * @param ordenesDePedido the ordenesDePedido to set
	 */
	public void setOrdenesDePedido(ArrayList<OrdenDePedido> ordenesDePedido) {
		this.ordenesDePedido = ordenesDePedido;
	}

	

}