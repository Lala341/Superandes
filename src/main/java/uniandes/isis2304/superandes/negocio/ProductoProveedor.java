package uniandes.isis2304.superandes.negocio;

import java.sql.Date;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:40
 */
public class ProductoProveedor {

	public Date fecha;
	public long id;
	public int cont;
	public ProductoProveedor(){

	}
	
	public ProductoProveedor(Date fecha, long id, int cont){
		this.id=id;
		this.fecha = fecha;
		this.cont=cont;
	}
	
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "ProductoProveedor [id=" + id + ", fecha=" + fecha + ", cont=" + cont  +  "]";
	}

	

}