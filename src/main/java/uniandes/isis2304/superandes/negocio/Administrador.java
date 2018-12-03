package uniandes.isis2304.superandes.negocio;

public class Administrador implements VOAdministrador {
	
	public int cantidadRecompra;
	public long id;
	public String usuario;
	public String contrasenha;

	public Administrador(){

	}
	public Administrador( long id,int cantidadDeRecompra, String usuario,  String contrasenha){
		this.cantidadRecompra=cantidadDeRecompra;
		this.id=id;
		this.usuario=usuario;
		this.contrasenha=contrasenha;
	}

	/**
	 * @return the cantidadRecompra
	 */
	public int getCantidadDeRecompra() {
		return cantidadRecompra;
	}
	/**
	 * @param cantidadRecompra the cantidadRecompra to set
	 */
	public void setCantidadDeRecompra(int cantidadDeRecompra) {
		this.cantidadRecompra = cantidadDeRecompra;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the contrasenha
	 */
	public String getContrasenha() {
		return contrasenha;
	}
	/**
	 * @param contrasenha the contrasenha to set
	 */
	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Administrador [id=" + id +", cantidadRecompra=" + cantidadRecompra+ ", usuario=" + usuario + ", contrasenha=" + contrasenha+"]";
	}
}
