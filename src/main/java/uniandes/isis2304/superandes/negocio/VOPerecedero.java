package uniandes.isis2304.superandes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de Perecedero.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOPerecedero {

	/**
	 * 
	 * @return id
	 */
	public long getId();
	
	/**
	 * 
	 * @return the Categoria
	 */
	public long getIdCategoria();

	/**
	 * @return the fechaDeVencimiento
	 */
	public Date getFechaDeVencimiento();

	/**
	 * @return the tipoCategoriaPerecedera
	 */
	public String getTipoCategoriaPerecedera();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de Perecedero
	 */
	public String toString();

}
