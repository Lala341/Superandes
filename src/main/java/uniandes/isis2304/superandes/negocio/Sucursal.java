package main.java.uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:45
 */
public class Sucursal {

	private double tamanho;
	private String tipoDeMercado;
	private double ventasTotales;
	private long id;
	private Ciudad ciudad;
	private ArrayList<Bodega> bodegas;
	private ArrayList<Estante> estantes;

	
	public Sucursal(){

	}


	/**
	 * @return the tamanho
	 */
	public double getTamanho() {
		return tamanho;
	}


	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}


	/**
	 * @return the tipoDeMercado
	 */
	public String getTipoDeMercado() {
		return tipoDeMercado;
	}


	/**
	 * @param tipoDeMercado the tipoDeMercado to set
	 */
	public void setTipoDeMercado(String tipoDeMercado) {
		this.tipoDeMercado = tipoDeMercado;
	}


	/**
	 * @return the ventasTotales
	 */
	public double getVentasTotales() {
		return ventasTotales;
	}


	/**
	 * @param ventasTotales the ventasTotales to set
	 */
	public void setVentasTotales(double ventasTotales) {
		this.ventasTotales = ventasTotales;
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
	 * @return the ciudad
	 */
	public Ciudad getCiudad() {
		return ciudad;
	}


	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	/**
	 * @return the bodegas
	 */
	public ArrayList<Bodega> getBodegas() {
		return bodegas;
	}


	/**
	 * @param bodegas the bodegas to set
	 */
	public void setBodegas(ArrayList<Bodega> bodegas) {
		this.bodegas = bodegas;
	}


	/**
	 * @return the estantes
	 */
	public ArrayList<Estante> getEstantes() {
		return estantes;
	}


	/**
	 * @param estantes the estantes to set
	 */
	public void setEstantes(ArrayList<Estante> estantes) {
		this.estantes = estantes;
	}

	

}