package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;
import java.util.Date;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:43
 */
public class PromocionProducto implements VOPromocionProducto{

	private long promocion;
	private long producto;
	
	public PromocionProducto(){

	}
	public PromocionProducto(long promocion,long producto){
		this.promocion=promocion;
		this.producto=producto;
	}

	/**
	 * @return the promocion
	 */
	public long getPromocion() {
		return promocion;
	}

	/**
	 * @param promocion the promocion to set
	 */
	public void setPromocion(long promocion) {
		this.promocion = promocion;
	}

	/**
	 * @return the producto
	 */
	public long getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(long producto) {
		this.producto = producto;
	}

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "PromocionProducto [promocion=" + promocion + ", producto=" + producto +  "]";
	}

}