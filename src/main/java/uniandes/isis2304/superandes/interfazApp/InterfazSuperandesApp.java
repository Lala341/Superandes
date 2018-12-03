package uniandes.isis2304.superandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superandes.negocio.Administrador;
import uniandes.isis2304.superandes.negocio.AdministradorSucursal;
import uniandes.isis2304.superandes.negocio.Bodega;
import uniandes.isis2304.superandes.negocio.CarritoCompras;
import uniandes.isis2304.superandes.negocio.Consumidor;
import uniandes.isis2304.superandes.negocio.ConsumidorVenta;
import uniandes.isis2304.superandes.negocio.Estante;
import uniandes.isis2304.superandes.negocio.OrdenPedido;
import uniandes.isis2304.superandes.negocio.Producto;
import uniandes.isis2304.superandes.negocio.ProductoBodega;
import uniandes.isis2304.superandes.negocio.ProductoCarritoCompras;
import uniandes.isis2304.superandes.negocio.ProductoEstante;
import uniandes.isis2304.superandes.negocio.ProductoOfrecido;
import uniandes.isis2304.superandes.negocio.Proveedores;
import uniandes.isis2304.superandes.negocio.Sucursal;
import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOAdministrador;
import uniandes.isis2304.superandes.negocio.VOBodega;
import uniandes.isis2304.superandes.negocio.VOCarritoCompras;
import uniandes.isis2304.superandes.negocio.VOCiudad;
import uniandes.isis2304.superandes.negocio.VOConsumidor;
import uniandes.isis2304.superandes.negocio.VOEstante;
import uniandes.isis2304.superandes.negocio.VOOrdenPedido;
import uniandes.isis2304.superandes.negocio.VOProducto;
import uniandes.isis2304.superandes.negocio.VOProductoCarritoCompras;
import uniandes.isis2304.superandes.negocio.VOProductoEstante;
import uniandes.isis2304.superandes.negocio.VOProductoOfrecido;
import uniandes.isis2304.superandes.negocio.VOProductoVenta;
import uniandes.isis2304.superandes.negocio.VOPromocion;
import uniandes.isis2304.superandes.negocio.VOPromocionDescuento;
import uniandes.isis2304.superandes.negocio.VOPromocionParteDescuento;
import uniandes.isis2304.superandes.negocio.VOPromocionProducto;
import uniandes.isis2304.superandes.negocio.VOPromocionUnidad;
import uniandes.isis2304.superandes.negocio.VOProveedores;
import uniandes.isis2304.superandes.negocio.VOSucursal;
import uniandes.isis2304.superandes.negocio.VOVenta;
import uniandes.isis2304.superandes.negocio.Venta;



@SuppressWarnings("serial")

public class InterfazSuperandesApp extends JFrame implements ActionListener{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(InterfazSuperandesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuraci�n de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasA.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociaci�n a la clase principal del negocio.
     */
    private Superandes superandes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuraci�n de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacci�n para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Men� de la aplicaci�n
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicaci�n. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazSuperandesApp( )
    {
        // Carga la configuraci�n de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gr�fica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        superandes = new Superandes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			M�todos de configuraci�n de la interfaz
	 *****************************************************************/
    /**
<<<<<<< HEAD
     * Lee datos de configuraci�n para la aplicaci�, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuraci�n deseada
     * @param archConfig - Archivo Json que contiene la configuraci�n
     * @return Un objeto JSON con la configuraci�n del tipo especificado
=======
     * Lee datos de configuraci�n para la aplicaci�, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipoconsumidor - El tipoconsumidor de configuraci�n deseada
     * @param archConfig - Archivo Json que contiene la configuraci�n
     * @return Un objeto JSON con la configuraci�n del tipoconsumidor especificado
>>>>>>> branch 'master' of https://github.com/Lala341/Superandes.git
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "Superandes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * M�todo para configurar el frame principal de la aplicaci�n
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuraci�n por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuraci�n indicada en el archivo de configuraci�n" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * M�todo para crear el men� de la aplicaci�n con base em el objeto JSON le�do
     * Genera una barra de men� y los men�s con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los men�s deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creaci�n de la barra de men�s
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creaci�n de cada uno de los men�s
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creaci�n de cada una de las opciones del men�
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	
	/* ****************************************************************
	 * 			M�todos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogSuperandes ()
	{
		mostrarArchivo ("superandes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogSuperandes ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("superandes.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el n�mero de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecuci�n de la demo y recolecci�n de los resultados
			long eliminados [] = superandes.limpiarSuperandes();
			
			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Gustan eliminados\n";
			resultado += eliminados [1] + " Sirven eliminados\n";
			resultado += eliminados [2] + " Visitan eliminados\n";
			resultado += eliminados [3] + " Bebidas eliminadas\n";
			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			resultado += eliminados [5] + " Bebedores eliminados\n";
			resultado += eliminados [6] + " Bares eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentaci�n general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creaci�n de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentaci�n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la informaci�n acerca del desarrollo de esta apicaci�n
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogot�	- Colombia)\n";
		resultado += " * Departamento	de	Ingenier�a	de	Sistemas	y	Computaci�n\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versi�n 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germ�n Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jim�nez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	/* ****************************************************************
	 * 			M�todos privados para la presentaci�n de resultados y otras operaciones
	 *****************************************************************/
    

    /**
     * Genera una cadena de caracteres con la descripci�n de la excepcion e, haciendo �nfasis en las excepcionsde JDO
     * @param e - La excepci�n recibida
     * @return La descripci�n de la excepci�n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci�n
	 * @param e - La excepci�n generada
	 * @return La cadena con la informaci�n de la excepci�n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para m�s detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como par�metro con la aplicaci�n por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			M�todos de la Interacci�n
	 *****************************************************************/
    /**
     * M�todo para la ejecuci�n de los eventos que enlazan el men� con los m�todos de negocio
     * Invoca al m�todo correspondiente seg�n el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );	
		
        try 
        {
			Method req = InterfazSuperandesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
		
	}
    
    
   public void RF1_RegistrarProveedores(){ 
    try 
	{
		String nit = JOptionPane.showInputDialog (this, "NIT del proveedor \n (Ej: 444.333.222-1 ingresar: 4443332221 (sin puntos))", "Adicionar Proveedor", JOptionPane.QUESTION_MESSAGE);
		String nombre = JOptionPane.showInputDialog (this, "Nombre del proveedor?", "Adicionar Proveedor", JOptionPane.QUESTION_MESSAGE);
		
		if(nit.length()!=10&&nit.contains(".")||nit.contains("-")){
			throw new Exception ("El NIT debe cumplir con los requerimientos, sin puntos y de tama�o 10 " );
		}
		
		if (nombre != null&&nit!=null&&nit.length()==10)
		{
    		VOProveedores tb = superandes.adicionarProveedores (Long.parseLong(nit), nombre);
    		if (tb == null)
    		{
    			throw new Exception ("No se pudo crear un proveedor con nombre: " + nombre +", y NIT:" + nit);
    		}
    		String resultado = "En adicionarProveedor\n\n";
    		resultado += "Proveedor adicionado exitosamente: " + tb;
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
	} 
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
   }
   
   
   public void RF2_RegistrarProductos(){
	   try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String[] unidadDeMedidas= {"UNIDAD", "GR", "LB", "LITRO"};
			String unidadDeMedida = (String) JOptionPane.showInputDialog(null,"Seleccione la Unidad de medida del producto", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, unidadDeMedidas, unidadDeMedidas[0]);
			
			String marca = JOptionPane.showInputDialog (this, "Marca del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			
			String[] categorias= {"PERECEDERO", "NOPERECEDERO"};
			String categoria = (String) JOptionPane.showInputDialog(null,"Seleccione la categoria", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, categorias, categorias[0]);
			String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
			String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipoconsumidor de producto de la categoria", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
			
			int es= JOptionPane.showConfirmDialog(this, "�El producto esta disponible?", "Estado producto", JOptionPane.YES_NO_OPTION );
			boolean estado = (es ==0)?true:false;
			
			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String especificacionDeEmpaquetado = JOptionPane.showInputDialog (this, "Especificacion de empaquetado del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String precioPorUnidadMedida = JOptionPane.showInputDialog (this, "Precio Por Unidad Medida del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String presentacion = JOptionPane.showInputDialog (this, "Presentacion del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			
			Date fecI= new Date(0);
			if(categoria.equals(categorias[0])){
				Integer[] dias= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
				String diai =  JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha vencimiento)", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, dias, dias[0]) + "";
				String[] meses= {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "noviembre", "diciembre"};
				String mesi = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha vencimiento)", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
				String anhoi = JOptionPane.showInputDialog (this, "Seleccione un a�o  (Fecha vencimiento)", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
				
				String posMesi= "0";
				int i=0;
				for (String string : meses) { i=i+1;
				if(string.equals(mesi)){ posMesi=i+"";}}
				
				fecI= new Date(Date.parse(diai+"/"+posMesi+ "/"+anhoi));
			}
			
			if (nombre != null&&categoria!=null&&codigoDeBarras!=null&&marca!=null&&precioPorUnidadMedida!=null&&unidadDeMedida!=null)
			{
				VOProducto tb;
				if(categoria.equals(categorias[0])){
					
	    		 tb = superandes.registrarProductoPerecedero(nombre, categoria,  codigoDeBarras, especificacionDeEmpaquetado, estado, marca, Double.parseDouble(precioPorUnidadMedida),  presentacion, unidadDeMedida, tipoCategoria,fecI);
				}
				else{
					 tb = superandes.registrarProductoNoPerecedero(nombre, categoria, codigoDeBarras, especificacionDeEmpaquetado, estado, marca, Double.parseDouble(precioPorUnidadMedida),  presentacion, unidadDeMedida, tipoCategoria);
							
				}
				
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear un producto con nombre: " + nombre);
	    		}
				
				
				
	    		String resultado = "En adicionarProducto\n\n";
	    		resultado += "Producto adicionado exitosamente (Al lugar solicitado): " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
   }
   public void RF3_RegistrarClientes(){
	   try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del cliente", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String correoElectronico = JOptionPane.showInputDialog (this, "Correo Electronico del cliente", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			
			String[] tipos= {"PNATURAL", "EMPRESA"};
			String tipo = (String) JOptionPane.showInputDialog(null,"Seleccione el tipoconsumidor de cliente", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, tipos, tipos[0]);
			
			String numeroIdentficacion="";
			String nit="";
			String direccion="";
			if(tipo.equals(tipos[0])){
			numeroIdentficacion = JOptionPane.showInputDialog (this, "Numero Identficacion (Ingrese solo numeros, sin puntos)", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			}
			else if(tipo.equals(tipos[1])){
			nit = JOptionPane.showInputDialog (this, "NIT del cliente (Ingrese solo numeros, sin puntos)", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			direccion = JOptionPane.showInputDialog (this, "Direccion del cliente", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			}
			
			
			if (nombre != null&&tipo!=null&&correoElectronico!=null)
			{
				VOConsumidor tb= null;
				if(tipo.equals(tipos[0])){
					tb= superandes.registrarConsumidorPersonaNatural(nombre, correoElectronico, tipo, Long.parseLong(numeroIdentficacion));
				}
				else if(tipo.equals(tipos[1])){
					tb= superandes.registrarConsumidorEmpresa(nombre, correoElectronico, tipo, Long.parseLong(nit), direccion);
				}
				
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear un cliente con nombre: " + nombre);
	    		}
	    		String resultado = "En adicionarCliente\n\n";
	    		resultado += "Cliente adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
   }
   public void RF4_RegistrarSucursal(){
	   try 
		{
		   List<VOCiudad> ciudades=superandes.darVOCiudades();
		   String[] ciudadesn= new String[ciudades.size()];
		   int i=0;
		   for (VOCiudad voCiudad : ciudades) {
			   ciudadesn[i]=voCiudad.getNombre();
			   i=i+1;
		   }
		   if(ciudadesn.length==0){
			  
			   JOptionPane.showMessageDialog(this, "Agrege una ciudad antes de registrar una sucursal (Menu agregar).");
			   
		   }else{
		   
			String nombre = JOptionPane.showInputDialog (this, "Nombre de la Sucursal", "Adicionar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String tamanho = JOptionPane.showInputDialog (this, "Tama�o de la Sucursal", "Adicionar Sucursal", JOptionPane.QUESTION_MESSAGE);

			String tipoMercado = JOptionPane.showInputDialog (this, "Tipo de mercado de la Sucursal", "Adicionar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String ventas = JOptionPane.showInputDialog (this, "Ventas iniciales de la Sucursal", "Adicionar Sucursal", JOptionPane.QUESTION_MESSAGE);
			
			String ciudad = (String) JOptionPane.showInputDialog(null,"Seleccione la ciudad de la sucursal", "Adicionar Sucursal", JOptionPane.DEFAULT_OPTION, null, ciudadesn, ciudadesn[0]);
			
			String direccion = JOptionPane.showInputDialog (this, "Direccion de la Sucursal", "Adicionar Sucursal", JOptionPane.QUESTION_MESSAGE);

			
			
			
			
			if (nombre != null&&ciudad!=null&&tipoMercado!=null&&direccion!=null)
			{
				VOSucursal tb= superandes.registrarSucursal(nombre,Long.parseLong( tamanho), tipoMercado, Double.parseDouble(ventas), ciudad, direccion);
				
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear Sucursal con nombre: " + nombre);
	    		}
	    		String resultado = "En adicionarSucursal\n\n";
	    		resultado += "Sucursal adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		   }
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	   
   }
   public void RF5_RegistrarBodega(){
	   
	   try 
		{
		  List<VOSucursal> sucursales=superandes.darVOSucursales();
		    
		   String[] sucursalesn= new String[sucursales.size()];
		     
		   int i=0;
		   for (VOSucursal voSucursal :superandes.darSucursales()) {
			   sucursalesn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }
		   if(sucursales.size()==0){
			   System.out.println("voy4");
			   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un bodega (Menu requerimientos F).");
			   
		   }else{
		   
			String nombre = JOptionPane.showInputDialog (this, "Nombre de la Bodega", "Adicionar Bodega", JOptionPane.QUESTION_MESSAGE);
				
			String volumen = JOptionPane.showInputDialog (this, "Volumen de la Bodega", "Adicionar Bodega", JOptionPane.QUESTION_MESSAGE);
			String peso = JOptionPane.showInputDialog (this, "Peso de la Bodega", "Adicionar Bodega", JOptionPane.QUESTION_MESSAGE);
			
			String nivelReorden = JOptionPane.showInputDialog (this, "Nivel Reorden de la Bodega", "Adicionar Bodega", JOptionPane.QUESTION_MESSAGE);
			String capacidadTotal = JOptionPane.showInputDialog (this, "Capacidad total de la Bodega", "Adicionar Bodega", JOptionPane.QUESTION_MESSAGE);
			
			String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal de la bodega", "Adicionar Bodega", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
			
			
			String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
			String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipoconsumidor de producto de la bodega", "Adicionar bodega", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
			
			
			
			
			
			if (nombre!=null&&volumen != null&&peso!=null&&capacidadTotal!=null&&sucursal!=null&&tipoCategoria!=null)
			{
				VOBodega tb= superandes.registrarBodegaASucursal(nombre, 0, Integer.parseInt(capacidadTotal), Double.parseDouble(peso), Double.parseDouble(volumen), tipoCategoria, Double.parseDouble(nivelReorden), sucursales.get(i-1).getId());
				
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear la Bodega");
	    		}
	    		String resultado = "En adicionarBodega\n\n";
	    		resultado += "Bodega adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		   }
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	   
   }
   public void RF6_RegistrarEstante(){
	   
	   try 
		{
		  List<VOSucursal> sucursales=superandes.darVOSucursales();
		    
		   String[] sucursalesn= new String[sucursales.size()];
		     
		   int i=0;
		   for (VOSucursal voSucursal :superandes.darSucursales()) {
			   sucursalesn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }
		   if(sucursales.size()==0){
			   System.out.println("voy4");
			   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un Estante (Menu requerimientos F).");
			   
		   }else{
		   
			String nombre = JOptionPane.showInputDialog (this, "Nombre del Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
				
			String volumen = JOptionPane.showInputDialog (this, "Volumen del Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			String peso = JOptionPane.showInputDialog (this, "Peso de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);

			String nivelAbastecimiento = JOptionPane.showInputDialog (this, "Nivel Abastecimiento del Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);

			String nivelReorden = JOptionPane.showInputDialog (this, "Nivel Reorden de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			String capacidadTotal = JOptionPane.showInputDialog (this, "Capacidad total del Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			
			String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal del Estante", "Adicionar Estante", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
			
			
			String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
			String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipoconsumidor de producto de la Estante", "Adicionar Estante", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
			String equipamientoAdicional = JOptionPane.showInputDialog (this, "Equipamiento Adicional de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);

			
			
			
			
			if (nombre!=null&&volumen != null&&peso!=null&&capacidadTotal!=null&&sucursal!=null&&tipoCategoria!=null)
			{
				VOEstante tb= superandes.registrarEstanteASucursal(nombre, 0, Integer.parseInt(capacidadTotal), Double.parseDouble(peso), Double.parseDouble(volumen), tipoCategoria, equipamientoAdicional,Long.parseLong(nivelReorden), Integer.parseInt(nivelAbastecimiento), sucursales.get(i-1).getId());
				
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear la Estante");
	    		}
	    		String resultado = "En adicionarEstante\n\n";
	    		resultado += "Estante adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		   }
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
   }
   public void RF7_RegistrarPromocion(){
	   try 
		{
		   String nombre = JOptionPane.showInputDialog (this, "Nombre de la promocion", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			String descripcion = JOptionPane.showInputDialog (this, "Descripcion de la promocion", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			Integer[] dias= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
			String diai =  JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha inicial)", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, dias, dias[0]) + "";
			String[] meses= {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "noviembre", "diciembre"};
			String mesi = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha inicial)", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
			String anhoi = JOptionPane.showInputDialog (this, "Seleccione un a�o  (Fecha inicial)", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			String diaf = JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha final)", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, dias, dias[0])+ "";
			String mesf = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha final)", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
			String anhof = JOptionPane.showInputDialog (this, "Seleccione un a�o  (Fecha final)", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			String[] estados= {"FINALIZADO", "VIGENTE"};
			String estado = (String) JOptionPane.showInputDialog(null,"Seleccione un estado", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, estados, estados[0]);
			String[] tipos= {"PROMODESCUENTO", "PROMOPARTEDESCUENTO", "PROMOUNIDAD", "PROMOCANTIDAD"};
			String tipo = (String) JOptionPane.showInputDialog(null,"Seleccione un tipoconsumidor", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, tipos, tipos[0]);
			String cantidadP = JOptionPane.showInputDialog (this, "Cantidad de productos que pueden usar la promocion", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			
			String posMesi= "0";
			int i=0;
			for (String string : meses) { i=i+1;
			if(string.equals(mesi)){ posMesi=i+"";}}
			
			Date fecI= new Date(Date.parse(diai+"/"+posMesi+ "/"+anhoi));
			
			String posMesf= "0";
			i=0;
			for (String string : meses) { i=i+1;
			if(string.equals(mesf)){ posMesf=i+"";}}
			
			Date fecF= new Date(Date.parse(diaf+"/"+posMesf+ "/"+anhof));
			
			
			
			if (nombre != null)
			{
	    		VOPromocion tb = superandes.adicionarPromocion(nombre, descripcion, tipo, fecI, fecF, estado, Integer.parseInt(cantidadP));
	    		long idPromocion = tb.getId();
	    		if(tipo!=null){
					if(tipo.equals(tipos[0])){
						String desc = JOptionPane.showInputDialog (this, "Seleccione un porcentaje descuento (sin %)", "Adicionar promocion con descuento", JOptionPane.QUESTION_MESSAGE);
						superandes.adicionarPromocionDescuento(idPromocion, Double.parseDouble(desc));
						
					}
					else if(tipo.equals(tipos[1])){
						String desc2 = JOptionPane.showInputDialog (this, "Seleccione un porcentaje descuento (sin %)", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						String unip = JOptionPane.showInputDialog (this, "Seleccione cantidad unidades vendidas", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						superandes.adicionarPromocionParteDescuento(idPromocion, Double.parseDouble(desc2), Integer.parseInt(unip));
						//No está agregando esta promoción
					}
					else if(tipo.equals(tipos[2])){
						String unip = JOptionPane.showInputDialog (this, "Seleccione cantidad unidades pagadas", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						String univ = JOptionPane.showInputDialog (this, "Seleccione cantidad unidades vendidad (entregadas)", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						superandes.adicionarPromocionUnidad(idPromocion, Integer.parseInt(univ), Integer.parseInt(unip));
						//No está registrando esta promoción
					}
					else if(tipo.equals(tipos[3])){
						String cnip = JOptionPane.showInputDialog (this, "Seleccione cantidad pagado", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						String cniv = JOptionPane.showInputDialog (this, "Seleccione cantidad vendido (entregado)", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						superandes.adicionarPromocionCantidad(idPromocion, Integer.parseInt(cniv), Integer.parseInt(cnip));
						//No está registrando esta promoción
					}
					
				}
	    		
	    		if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear una promocion con nombre: " + nombre );
	    		}
	    		String resultado = "En adicionarPromociom\n\n";
	    		resultado += "Promoción adicionado exitosamente: " + tb ;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
   }
   public void RF8_FinalizarPromocion(){
	   try 
		{
		  List<VOSucursal> sucursales=superandes.darVOSucursales();
		    
		   String[] sucursalesn= new String[sucursales.size()];
		     
		   int i=0;
		   for (VOSucursal voSucursal :superandes.darSucursales()) {
			   sucursalesn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }
		   if(sucursales.size()==0){
			   System.out.println("voy4");
			   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un pedido (Menu requerimientos F).");
			   
		   }else{
		   
			String volumen = JOptionPane.showInputDialog (this, "Volumen de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			String peso = JOptionPane.showInputDialog (this, "Peso de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);

			String nivelAbastecimiento = JOptionPane.showInputDialog (this, "Nivel Abastecimiento de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);

			String nivelReorden = JOptionPane.showInputDialog (this, "Nivel Reorden de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			String capacidadTotal = JOptionPane.showInputDialog (this, "Capacidad total de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			
			String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal de la Estante", "Adicionar Estante", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
			
			String cantidadProducto = JOptionPane.showInputDialog (this, "Cantidad de productos de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);
			
			String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
			String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipoconsumidor de producto de la Estante", "Adicionar Estante", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
			String equipamientoAdicional = JOptionPane.showInputDialog (this, "Equipamiento Adicional de la Estante", "Adicionar Estante", JOptionPane.QUESTION_MESSAGE);

			
			
			
			
			if (volumen != null&&peso!=null&&capacidadTotal!=null&&sucursal!=null&&cantidadProducto!=null&&tipoCategoria!=null)
			{
				
				if (null == null)
	    		{
	    			throw new Exception ("No se pudo crear la Estante");
	    		}
	    		String resultado = "En adicionarEstante\n\n";
	    		resultado += "Estante adicionado exitosamente: " + null;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		   }
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

   }
   public void RF9_RegistrarPedido(){
	   

	   try 
		{
		  List<VOSucursal> sucursales=superandes.darVOSucursales();
		   String[] sucursalesn= new String[sucursales.size()];
		     
		   int i=0;
		   for (VOSucursal voSucursal :superandes.darSucursales()) {
			   sucursalesn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }
		   
		   List<VOProveedores> provedores=superandes.darVOProveedoreses();
		   String[] provedoresn= new String[provedores.size()];
		     
		   i=0;
		   for (VOProveedores voProveedor :superandes.darProveedores()) {
			   provedoresn[i]=voProveedor.getNombre();
			   i=i+1;
			   
		   }
		   if(sucursales.size()==0){
			   System.out.println("voy4");
			   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un pedido (Menu requerimientos F).");
			   
		   }else{
		   
			
			
			
			
			String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal dela orden de pedido", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
			String[] estados= {"VIGENTE", "ENTREGADO", "FINALIZADO"};
			String estado = (String) JOptionPane.showInputDialog(null,"Seleccione el estado de la orden de pedido", "Adicionar Orden Pedido", JOptionPane.DEFAULT_OPTION, null, estados, estados[0]);
			String calificacion = JOptionPane.showInputDialog (this, "Ingrese una calificacion para la orden de pedido. ", "Adicionar Orden Pedido", JOptionPane.QUESTION_MESSAGE);
			
			Integer[] dias= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
			
			
			String diai =  JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha del pedido)", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, dias, dias[0]) + "";
			String[] meses= {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "noviembre", "diciembre"};
			String mesi = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha del pedido)", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
			String anhoi = JOptionPane.showInputDialog (this, "Seleccione un a�o  (Fecha del pedido)", "Adicionar orden de pedido", JOptionPane.QUESTION_MESSAGE);
			String diaf = JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha entrega estimada del pedido)", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, dias, dias[0])+ "";
			String mesf = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha entrega estimada del pedido)", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
			String anhof = JOptionPane.showInputDialog (this, "Seleccione un a�o  (Fecha entrega estimada del pedido)", "Adicionar orden de pedido", JOptionPane.QUESTION_MESSAGE);
			String proveedor= (String) JOptionPane.showInputDialog(null,"Seleccione el proveedor de la orden de pedido", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, provedoresn, provedoresn[0]);
			
			
						
			
			String posMesi= "0";
			i=0;
			for (String string : meses) { i=i+1;
			if(string.equals(mesi)){ posMesi=i+"";}}
			
			Date fecI= new Date(Date.parse(diai+"/"+posMesi+ "/"+anhoi));
			
			String posMesf= "0";
			i=0;
			for (String string : meses) { i=i+1;
			if(string.equals(mesf)){ posMesf=i+"";}}
			
			Date fecF= new Date(Date.parse(diaf+"/"+posMesf+ "/"+anhof));
			
			i=0;
			Long proveedorId=(long) 0;
			List<Proveedores> proveedores1 =superandes.darProveedores();
			while (i<proveedores1.size()) {
				   if(proveedores1.get(i).getNombre().equals(proveedor)){
					   proveedorId=proveedores1.get(i).getNit();
				   }	
				   i=i+1;
				   
			 }
			i=0;
			Long sucursalId=(long) 0;
			List<Sucursal> sucursales1 =superandes.darSucursales();
			while (i<sucursales1.size()) {
				   if(sucursales1.get(i).getNombre().equals(sucursal)){
					   sucursalId=sucursales1.get(i).getId();
				   }
				   i=i+1;
				   
			 }
		   
		   
		   List<ProductoOfrecido> productosOfrecidos=superandes.darProductoOfrecidoPorIdProveedor(proveedorId);
		   
		   String[] productosOfrecidosn= new String[ productosOfrecidos.size()];
		     
		   i=0;
		   for (ProductoOfrecido productoOfrecido :productosOfrecidos) {
			   productosOfrecidosn[i]=superandes.darProductoPorId(productoOfrecido.getProductoId()).getNombre();
			   i=i+1;
			   
		   }
		   if(productosOfrecidos.size()==0){
			   throw new Exception("Operación cancelada, el proveedor seleccionado no tiene productos ofrecidos.");
		   }
		   
		   String productoOfrecido= (String) JOptionPane.showInputDialog(null,"Seleccione el producto del proveedor seleccionado.", "Adicionar orden de pedido", JOptionPane.DEFAULT_OPTION, null, productosOfrecidosn, productosOfrecidosn[0]);
		   
		   i=0;
			ProductoOfrecido productoOfrecidoo=null;
			while (i<productosOfrecidos.size()) {
				   if(superandes.darProductoPorId(productosOfrecidos.get(i).getProductoId()).getNombre().equals(productoOfrecido)){
					   productoOfrecidoo=productosOfrecidos.get(i);}
				   i=i+1;
				   
			 }
			String unidadMedida = superandes.darProductoPorId(productoOfrecidoo.getProductoId()).getUnidadMedida();
		   String cantidadProducto = JOptionPane.showInputDialog (this, "Cantidad de producto del pedido (Unidad de medida del producto seleccionado: "+unidadMedida+")", "Adicionar orden de pedido", JOptionPane.QUESTION_MESSAGE);
		   
			

			
			
			if (sucursal != null&&cantidadProducto!=null&&productoOfrecido!=null&&proveedor!=null)
			{
				VOOrdenPedido tb= superandes.adicionarOrdenPedido(estado, Integer.parseInt(calificacion), (diai+"/"+posMesi+ "/"+anhoi),(diaf+"/"+posMesf+ "/"+anhof), proveedorId, productoOfrecidoo.getId(), Integer.parseInt(cantidadProducto), unidadMedida, sucursalId);
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear la orden de pedido");
	    		}
	    		String resultado = "En  orden de pedido\n\n";
	    		resultado += " orden de pedido adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		   }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	   
	   
   }
   public void RF10_RegistrarLlegadaPedido(){
	   
	   try {
		   List<OrdenPedido> pedidos=superandes.darOrdenPedido();
		   String pedidoN = JOptionPane.showInputDialog (this, "Ingrese el id del pedido", JOptionPane.QUESTION_MESSAGE);
		   long idPedido = Long.valueOf(pedidoN);
		   Long t=superandes.finalizarOrdenPedido(idPedido);
		   String resultado="";
		   if(t!=null){
			   resultado+="Se finalizo el pedido solicitado." ;
			   panelDatos.actualizarInterfaz(resultado);
		   }
		   			   
		   
		   
		
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
	   }
	   
   }
   public void RF11_RegistrarVenta(){
	   
	   try {
		   
		   List<VOProducto> productos=superandes.darVOProductos();
		   String[] productosn= new String[productos.size()];
		   int j=0;
		   for (VOProducto voProducto : productos) {
			   productosn[j]=voProducto.getNombre();
			   j=j+1;
		   }
		   if(productosn.length==0){
			  
			   JOptionPane.showMessageDialog(this, "Agrege un producto antes de vender (Menu Requerimientos).");
			   
		   }else{
		   
			   String nombreProducto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto que desea vender", "Registrar Venta", JOptionPane.DEFAULT_OPTION, null, productosn,productosn[0]);
			   List<Consumidor> consumidores=superandes.darConsumidor();
			   String unidadMedida = "";
			   boolean existeConsumidor = false;
			   boolean existeProducto = false;
			   long producto = 0;
			   String idConsumidor = JOptionPane.showInputDialog (this, "Id del consumidor", JOptionPane.QUESTION_MESSAGE);
			   long idCon = (long ) Integer.parseInt(idConsumidor);
			   for (int i = 0; i < consumidores.size() && !existeConsumidor; i++) {
				   if (consumidores.get(i).getId() == (Long.parseLong(idConsumidor))) {
					existeConsumidor = true;
				}
			   }
			   for (int i = 0; i < productos.size() && !existeProducto; i++) {
				   if (productos.get(i).getNombre().equals(nombreProducto)) {
					existeProducto = false;
					unidadMedida = productos.get(i).getUnidadMedida();
					producto = productos.get(i).getId();
				}
			   }
			   
			   if(consumidores.size()==0){
				   if (!existeConsumidor) {
					  JOptionPane.showMessageDialog(this, "El consumidor debe de existir antes de registrar una venta ");
				}if (!existeProducto) {
					JOptionPane.showMessageDialog(this, "El producto debe de existir antes de registrar una venta.");
					}			   
			   }
			   else{
				   
				   String fecha = superandes.darFechaCortaDeHoy();
				   String formaPago = JOptionPane.showInputDialog (this, "Ingrese la forma de pago del cliente", JOptionPane.QUESTION_MESSAGE);
				   String valorTotalS = JOptionPane.showInputDialog (this, "Ingrese el valor total de la venta", JOptionPane.QUESTION_MESSAGE);
				   double valorTotal = Double.parseDouble(valorTotalS);
				   String cantidadProducto = JOptionPane.showInputDialog (this, "Ingrese la cantidad del producto", JOptionPane.QUESTION_MESSAGE);
				   String descripcionFactura = JOptionPane.showInputDialog (this, "Ingrese una descripcion de la factura", JOptionPane.QUESTION_MESSAGE);
				   List<VOSucursal> sucursales=superandes.darVOSucursales();
				    
				   String[] sucursalesn= new String[sucursales.size()];
				     
				   int i=0;
				   for (VOSucursal voSucursal :superandes.darSucursales()) {
					   sucursalesn[i]=voSucursal.getNombre();
					   i=i+1;
					   
				   }
				   if(sucursales.size()==0){
	
					   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un producto (Menu requerimientos F).");
					   
				   }
					String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal de la venta", "Venta", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);

					
					VOSucursal sucu=null;
					for (int k = 0; k < sucursales.size(); k++) {
						
						if (sucursal.equals(sucursales.get(k).getNombre())) {
							sucu= sucursales.get(k);
							break;
						}
					}
					   
					   Venta venta = superandes.adicionarVenta(fecha, formaPago, valorTotal, idCon, sucu.getId());
					   superandes.adicionarProductoVenta(venta.getId(), producto, Integer.parseInt(cantidadProducto), unidadMedida);
					   superandes.adicionarFactura(descripcionFactura);
					   
					   String resultado = "Registrar una venta\n\n";
			    		resultado += "Se registró una venta exitosamente: " + venta;
						resultado += "\n Operaciòn terminada";
						panelDatos.actualizarInterfaz(resultado);
			   }
		   }
		
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
	   }

	   
   }
 public void RF12_RegistrarCarritoCompras(){
	   
	   try {
		   
		   List<Consumidor> consumidores=superandes.darConsumidor();
		   
		   if(consumidores.isEmpty()){
			   panelDatos.actualizarInterfaz("No existen clientes en la base de datos. Por favor agrege uno y proceda a solicitar un carrito de compras.");
		   }
		   else{
		   String[] estados= {"PERSONA NATURAL", "EMPRESA"};
		   String estado = (String) JOptionPane.showInputDialog(null,"Elija el tipoconsumidor del cliente para el carrito de compras", "Adicionar Carrito Compras", JOptionPane.DEFAULT_OPTION, null, estados, estados[0]);
		
		   String consumidor="";
		   Long consumidorId= null;
		   
		   if(estado.equals("PERSONA NATURAL")){
			   consumidor= JOptionPane.showInputDialog (this, "Ingrese el documento de identidad del cliente.(Sin puntos) ", "Adicionar Carrito Compras", JOptionPane.QUESTION_MESSAGE);
			   try{
				   consumidorId= superandes.darPersonaNaturalPorId(Long.parseLong(consumidor)).getIdConsumidor();
				    
				   
			   }catch (Exception e) {
				   throw new Exception("No existen clientes en la base de datos con esa Identificacion. Por favor agregelo y proceda a solicitar un carrito de compras.");
				
				  
			   }
		   }else{
			   consumidor= JOptionPane.showInputDialog (this, "Ingrese el NIT del cliente. ", "Adicionar Carrito Compras", JOptionPane.QUESTION_MESSAGE);
			   
			   try{
				consumidorId= superandes.darEmpresaPorId(Long.parseLong(consumidor)).getIdConsumidor();
				    
				   
			   }catch (Exception e) {
				  
				   throw new Exception("No existen clientes en la base de datos con esa Identificacion. Por favor agregelo y proceda a solicitar un carrito de compras.");
				}
		   }
		   if(consumidorId!=null){
			   List<VOSucursal> sucursales=superandes.darVOSucursales();
			    
			   String[] sucursalesn= new String[sucursales.size()];
			     
			   int i=0;
			   for (VOSucursal voSucursal :superandes.darSucursales()) {
				   sucursalesn[i]=voSucursal.getNombre();
				   i=i+1;
				   
			   }
			   if(sucursales.size()==0){

				   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un producto (Menu requerimientos F).");
				   
			   }
			String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal para el producto", "Adicionar carrito compras", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
			Sucursal sucu=null;
			i=0;
			for (String string : sucursalesn) {
				if(string.equals(sucursal)){
					sucu=(Sucursal) sucursales.get(i);
					break;
				}
				i=i+1;
			}
			   CarritoCompras tb= superandes.adicionarCarritoCompras(consumidorId, sucu.getId());
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear el carrito de compras");
	    		}
	    		String resultado = "En  carrito de compraso\n\n";
	    		resultado += " carrito de compras adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}

		   }
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(e.getMessage());
	   }

	   
   }
 public void RFF13_AdicionarUnProductoCarritoCompras(){
	 try {
		 
		List<Consumidor> consumidores=superandes.darConsumidor();
		if (consumidores.isEmpty()) {
			 panelDatos.actualizarInterfaz("No existen ning�n cliente. Es necesario agregar uno nuevo para a�adir un producto al carrito de compras");
		}
		else
		{
			VOCarritoCompras carritoPorId = null;
			List<VOCarritoCompras> carritos=superandes.darVOCarritoCompras();
		    
			String[] carritosn= new String[carritos.size()];
			long id = 0;
			   
			for (int i = 0; i < carritos.size(); i++) {
				id = carritos.get(i).getConsumidor();
				carritosn[i]=String.valueOf(id);
				 carritoPorId = carritos.get(i);
			 }
			   
			 if(carritos.size()==0){

				 JOptionPane.showMessageDialog(this, "Agrege un carrito antes de registrar un producto (Menu requerimientos F).");
				   
			 }
			 else {
				 String idCon= (String) JOptionPane.showInputDialog(null,"Seleccione el id del consumidor", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, carritosn, carritosn[0]);

				long idConsumidor = Long.parseLong(idCon);
					
				if(carritoPorId!=null){
					String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
					String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipoconsumidor de producto", "Tipo producto", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
						
					
					List<VOEstante> estantes = superandes.darVOEstantes();
					VOEstante estante = null;
					boolean encontro = false;
					encontro = false;
					for (int i = 0; i < estantes.size() && !encontro; i++) {
						if (estantes.get(i).getTipoProducto().equals(tipoCategoria)) {
							encontro = true;
							estante = estantes.get(i);
						}
					}
						
					List<VOProducto> productos = superandes.darVOProductos();
					    
					String[] productosn= new String[productos.size()];
						   
					for (int i = 0; i < productos.size(); i++) {
						productosn[i]= productos.get(i).getNombre();
					}
					String productoAgregar= (String) JOptionPane.showInputDialog(null,"Seleccione el producto a vender", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, productosn, productosn[0]);

					long producto = 0;
					String unidadMedida = "";
					boolean encontrado = false;
						
					for (int j = 0; j < productos.size() && !encontrado; j++) {
							
						if (productos.get(j).getNombre().equals(productoAgregar)) {
							producto = productos.get(j).getId();
							unidadMedida = productos.get(j).getUnidadMedida();
							encontrado = true;
							}
					}
						
					String cantidadP = JOptionPane.showInputDialog (this, "Ingrese la cantidad del producto", JOptionPane.QUESTION_MESSAGE);
					int cantidadProducto = Integer.parseInt(cantidadP);
					ProductoCarritoCompras p = superandes.adicionarProductoCarritoCompras(carritoPorId.getId(), producto, cantidadProducto, unidadMedida);
						
					int cant = estante.getCantidadProductos();
					int nuevaCantidad = cant - cantidadProducto;			
						
						
					if (nuevaCantidad <= estante.getNivelDeAbastecimiento()) {
						superandes.sacarCantidadDeProductoB(idConsumidor, producto, cantidadProducto);	
						superandes.adicionarProductoEstante(estante.getId(), producto, estante.getNivelDeAbastecimiento()-nuevaCantidad);
					}
					String resultado = "En agregar producto a  carrito de compras\n\n";
			    	resultado += " producto adicionado exitosamente: " + p;
					resultado += "\n Operaci?n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else {
					panelDatos.actualizarInterfaz("El consumidor no tiene un carrito de compras creado");
				}
			 }
		}
		 
	} 
	catch (Exception e) 
	{
	 //e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(e.getMessage());
	}

 }
 public void RFF14_EliminarUnProductoCarritoCompras(){
	 
	   try {
		   
		   List<Consumidor> consumidores=superandes.darConsumidor();
		   
		   if(consumidores.isEmpty()){
			   panelDatos.actualizarInterfaz("No existen clientes en la base de datos. Por favor agrege uno y proceda a solicitar un carrito de compras.");
		   }
		   else{
		   String[] estados= {"PERSONA NATURAL", "EMPRESA"};
		   String estado = (String) JOptionPane.showInputDialog(null,"Elija el tipoconsumidor del cliente para agregar el producto al carrito de compras", "Eliminar Producto Carrito Compras", JOptionPane.DEFAULT_OPTION, null, estados, estados[0]);
		
		   String consumidor="";
		   Long consumidorId= null;
		   
		   if(estado.equals("PERSONA NATURAL")){
			   consumidor= JOptionPane.showInputDialog (this, "Ingrese el documento de identidad del cliente.(Sin puntos) ", "Eliminar Producto Carrito Compras", JOptionPane.QUESTION_MESSAGE);
			   try{
				   consumidorId= superandes.darPersonaNaturalPorId(Long.parseLong(consumidor)).getIdConsumidor();
				    
				   
			   }catch (Exception e) {
				   throw new Exception("No existen clientes en la base de datos con esa Identificacion. Por favor agregelo y proceda a solicitar un carrito de compras.");
				
				  
			   }
		   }else{
			   consumidor= JOptionPane.showInputDialog (this, "Ingrese el NIT del cliente. ", "Eliminar Producto Carrito Compras", JOptionPane.QUESTION_MESSAGE);
			   try{
				consumidorId= superandes.darEmpresaPorId(Long.parseLong(consumidor)).getIdConsumidor();
				    
				   
			   }catch (Exception e) {
				  
				   throw new Exception("No existen clientes en la base de datos con esa Identificacion. Por favor agregelo y proceda a solicitar un carrito de compras.");
				}
		   }
		   if(consumidorId!=null){
			   
			   CarritoCompras tb= superandes.darCarritoComprasPorIdConsumidor(consumidorId);
				if(tb!=null){
					List<ProductoCarritoCompras> productos= superandes.darProductoscarritoComprasPorIdcarritoCompras(tb.getId());
					if(productos.isEmpty()){
						   throw new Exception("No existen productos en el carrito de compras.");
					}
					else{
						 String[] productosn= new String[productos.size()];
					     
						   int i=0;
						   for (ProductoCarritoCompras voSucursal :productos) {
							   productosn[i]=superandes.darProductoPorId(voSucursal.getProducto()).getNombre();
							   i=i+1;
							   
						   }
						   String producto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto, que desea eliminar", "Eliminar Producto Carrito Compras", JOptionPane.DEFAULT_OPTION, null, productosn, productosn[0]);
						   Long productoId=null;
						   ProductoCarritoCompras pro=null;
						   i=0;
						   for (ProductoCarritoCompras voSucursal :productos) {
							   if(producto.equals(superandes.darProductoPorId(voSucursal.getProducto()).getNombre())){
								   productoId=voSucursal.getProducto();
								   pro=voSucursal;
								   break;
							   }
							   i=i+1;
							   
						   }
						   superandes.eliminarProductoCarritoComprasPorIdProducto(tb.getId(), productoId);
						   List<Bodega> bodega= superandes.darBodegasPorTipoYSucursal(superandes.darCategoriasPorId(superandes.darProductoPorId(productoId).getCategoria()).getTipoDeAlmacenamiento(), tb.getSucursal());
						   if(!bodega.isEmpty()){
						   if(!superandes.darProductoBodegaIdBodegaProducto (bodega.get(0).getId(), productoId).isEmpty()){
						   superandes.ingresarCantidadDeProductoB(bodega.get(0).getId(), pro.getProducto(), pro.getCantidadProducto());
						   }else{
							   superandes.adicionarProductoBodega(bodega.get(0).getId(), pro.getProducto(), pro.getCantidadProducto());
							   superandes.aumentarCantidadDeProductosUnoB(bodega.get(0).getId());
						   }
						   }
						   else{
							   System.out.println("voy2");
							   Bodega t= superandes.adicionarBodega("BodegaReserva", 0, 200, 200, 200,superandes.darCategoriasPorId(superandes.darProductoPorId(productoId).getCategoria()).getTipoDeAlmacenamiento(), 2, tb.getSucursal());
							   System.out.println("voy1");
							   
							   superandes.adicionarProductoBodega(t.getId(), productoId, pro.getCantidadProducto());
							   
							   System.out.println("voy3");
							   superandes.aumentarCantidadDeProductosUnoB(t.getId());
							   
						   }
					}
							   
				}
			   if (tb == null)
	    		{
	    			throw new Exception ("No se pudo encontrar el carrito de compras");
	    		}
	    		String resultado = "En  carrito de compraso\n\n";
	    		resultado += "Producto eliminado del carrito de compras adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}

		   }
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(e.getMessage());
	   }
	   
	   
 }
 public void agregarInventarioProducto(){
	 try{
		 
		 List<Producto> productos=superandes.darProductos();
		    
		   String[] productosn= new String[productos.size()];
		     
		   int i=0;
		   for (Producto voSucursal :productos) {
			   productosn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }
		   if(productos.size()==0){

			   JOptionPane.showMessageDialog(this, "Agrege un producto antes de registrar un producto (Menu requerimientos F).");
			   
		   }
	String producto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto, que desea agregar inventario", "Adicionar inventario", JOptionPane.DEFAULT_OPTION, null, productosn, productosn[0]);
	   
	Producto tb=null;
	i=0;
	for (String string : productosn) {
		if(string.equals(producto)){
			tb=productos.get(i);
			break;
		}
		i=i+1;
	}		 
		 
	 List<VOSucursal> sucursales=superandes.darVOSucursales();
	    
	   String[] sucursalesn= new String[sucursales.size()];
	     
	   i=0;
	   for (VOSucursal voSucursal :superandes.darSucursales()) {
		   sucursalesn[i]=voSucursal.getNombre();
		   i=i+1;
		   
	   }
	   if(sucursales.size()==0){

		   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un producto (Menu requerimientos F).");
		   
	   }
	String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal para el producto", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
	i=0;
	Long sucuId=null;
	for (VOSucursal voSucursal :superandes.darSucursales()) {
		   
		if(sucursal.equals(voSucursal.getNombre())){
			sucuId=voSucursal.getId();
			break;
		}
		   i=i+1;
		   
	   }
	
	String[] donde1= {"ESTANTE", "BODEGA"};
	String donde = (String) JOptionPane.showInputDialog(null,"Seleccione donde desea almacenar el producto", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, donde1, donde1[0]);
	String tipoCategoria=superandes.darCategoriasPorId(tb.getCategoria()).getTipoDeAlmacenamiento();
	
	if(tb!=null&&sucuId!=null){
	
		if(donde.equals("ESTANTE")){
		List<Estante> estantes= superandes.darEstantesPorTipoSucursal(tipoCategoria,sucuId );
	 	String[] estantesn=new String[estantes.size()];
	 	Long estanteid=null;
	 	i=0;
	 	for (Estante estante : estantes) {
	 		estantesn[i]=estante.getNombre();
	 		i=i+1;
		}
	 	if(estantes.isEmpty()){
	 		throw new Exception("No existen estantes en la sucursal para este tipoconsumidor de producto.");
	 	}
		String estante = (String) JOptionPane.showInputDialog(null,"Seleccione el estante a donde quiere registrar el  producto \n (Estantes seleccionados segun Categoria Producto)", "Adicionar producto a estante", JOptionPane.DEFAULT_OPTION, null, estantesn, estantesn[0]);
		
		i=0;
		for (String estante1 : estantesn) {
			if(estante1.equals(estantes.get(i).getNombre())){
				estanteid=estantes.get(i).getId();
				break;
			}
	 		i=i+1;
		}
		String cant= JOptionPane.showInputDialog (this, "Ingrese la cantidad de producto (Por unidad medida: "+tb.unidadMedida+") \n a adicionar en el estante.", "Adicionar Inventario", JOptionPane.QUESTION_MESSAGE);
		if (tb != null&&Integer.parseInt(cant)!=0&&tipoCategoria!=null&&estanteid!=null)
		{
				
  		ProductoEstante tb2=superandes.adicionarProductoEstante(estanteid, tb.getId(),Integer.parseInt(cant) );
  		
  		
			if (tb2 == null)
  		{
  			throw new Exception ("No se pudo agregar el producto al estante: "+tb.getId() );
  		}
  		String resultado = "En agregar el producto al estante\n\n";
  		resultado += "Agregar el producto al estante adicionado exitosamente: " + tb;
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
		}
	}else if(donde.equals( "BODEGA")){
		List<Bodega> bodegas= superandes.darBodegasPorTipoSucursal(tipoCategoria,sucuId );
		String[] bodegasn=new String[bodegas.size()];
	 	Long bodegaid=null;
	 	i=0;
	 	for (Bodega bodega : bodegas) {
	 		bodegasn[i]=bodega.getNombre();
	 		i=i+1;
		}
	 	if(bodegas.isEmpty()){
	 		throw new Exception("No existen bodegas en la sucursal para este tipoconsumidor de producto.");
	 	}
		String bodega = (String) JOptionPane.showInputDialog(null,"Seleccione la bodega a donde quiere registrar el  producto \n (Estantes seleccionados segun Categoria Producto)", "Adicionar producto a estante", JOptionPane.DEFAULT_OPTION, null, bodegasn, bodegasn[0]);
		
		i=0;
		for (String bodega1 : bodegasn) {
			if(bodega1.equals(bodegas.get(i).getNombre())){
				bodegaid=bodegas.get(i).getId();
				break;
			}
	 		i=i+1;
		}
		String cant= JOptionPane.showInputDialog (this, "Ingrese la cantidad de producto (Por unidad medida: "+tb.unidadMedida+") \n a adicionar en la bodega.", "Adicionar Inventario", JOptionPane.QUESTION_MESSAGE);
		
		if (tb != null&&Integer.parseInt(cant)!=0&&tipoCategoria!=null&&bodegaid!=null)
		{
				
  		ProductoBodega tb2=superandes.adicionarProductoBodega(bodegaid, tb.getId(),Integer.parseInt(cant));
  		
  		
			if (tb2 == null)
  		{
  			throw new Exception ("No se pudo agregar el producto a la bodega: "+tb.getId() );
  		}
  		String resultado = "En agregar el producto a la bodega\n\n";
  		resultado += "Agregar el producto a la bodega adicionado exitosamente: " + tb;
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
		}
		
	}
	}}catch (Exception e) {
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
 }
 
 public void RFF15_PagarCompra(){
	   
	 try {
		   
		List<VOProductoCarritoCompras> productosCarrito=superandes.darVOProductoCarritoComprases();
		if (productosCarrito.isEmpty()) {
			   JOptionPane.showMessageDialog(this, "El carrito de compras no tiene ningun producto para vender");
		   }
		else
		{
			List<VOProducto> productos=superandes.darVOProductos();
			String[] productosn= new String[productosCarrito.size()];
			long idP = 0;
			for (int i = 0; i < productosCarrito.size(); i++) {
				idP = productosCarrito.get(i).getProducto();
				productosn[i]=String.valueOf(idP);
			}
			String idDelProducto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto con el cual desea registrar la compra", "Registrar Compra", JOptionPane.DEFAULT_OPTION, null, productosn,productosn[0]);
			long idProducto = Long.parseLong(idDelProducto);
			
			List<VOCarritoCompras> carritos=superandes.darVOCarritoCompras();
			if (carritos.isEmpty()) {
				JOptionPane.showMessageDialog(this, "El usuario no tiene un carrito de compras registrado");
			}
			else
			{
				VOCarritoCompras carritoPorId = null;
				long idC = 0;
				String[] carritosn= new String[carritos.size()];
				
				for (int i = 0; i < carritos.size(); i++) {
					idC = carritos.get(i).getConsumidor();
					carritosn[i]=String.valueOf(idC);
				}
				
				String idDelConsumidor= (String) JOptionPane.showInputDialog(null,"Seleccione el id del consumidor", "Registrar Compra", JOptionPane.DEFAULT_OPTION, null, carritosn,carritosn[0]);
				long idConsumidor = Long.parseLong(idDelConsumidor);
				
				String unidadMedida = "";
				String fecha = superandes.darFechaDeHoy();
				boolean encontrado = false;
				
				for (int i = 0; i < productos.size() && !encontrado; i++) {
					
					if (productos.get(i).getId() == idProducto) {
						unidadMedida = productos.get(i).getUnidadMedida();
						encontrado = true;
					}
				}
				long idCarrito = 0;
				int cantidadProducto = 0;
				for (int i = 0; i < productosCarrito.size(); i++) {
					if (productosCarrito.get(i).getProducto() == idProducto) {
						cantidadProducto = productosCarrito.get(i).getCantidadProducto();
						idCarrito = productosCarrito.get(i).getCarritoCompras();
					}
				}
				String formaPago = JOptionPane.showInputDialog (this, "Ingrese la forma de pago del cliente", JOptionPane.QUESTION_MESSAGE);
				String valorTotalS = JOptionPane.showInputDialog (this, "Ingrese el valor total de la venta", JOptionPane.QUESTION_MESSAGE);
				double valorTotal = Double.parseDouble(valorTotalS);
				String descripcionFactura = JOptionPane.showInputDialog (this, "Ingrese una descripcion de la factura", JOptionPane.QUESTION_MESSAGE);
				
				List<VOSucursal> sucursales=superandes.darVOSucursales();
			    
				   String[] sucursalesn= new String[sucursales.size()];
				     
				   int i=0;
				   for (VOSucursal voSucursal :superandes.darSucursales()) {
					   sucursalesn[i]=voSucursal.getNombre();
					   i=i+1;
					   
				   }
				   if(sucursales.size()==0){
	
					   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de registrar un producto (Menu requerimientos F).");
					   
				   }
					String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal de la venta", "Venta", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);

					
					VOSucursal sucu=null;
					for (int k = 0; k < sucursales.size(); k++) {
						
						if (sucursal.equals(sucursales.get(k).getNombre())) {
							sucu= sucursales.get(k);
							break;
						}
					}
				
				Venta venta = superandes.adicionarVenta(fecha, formaPago, valorTotal, idConsumidor, sucu.getId());
				superandes.adicionarProductoVenta(venta.getId(), idProducto, cantidadProducto, unidadMedida);									   
				superandes.adicionarFactura(descripcionFactura);				
				superandes.eliminarCarritoComprasPorId(idCarrito);
				
				String resultado = "Pagar una compra\n\n";
		  		resultado += "Se pudo registrar la compra exitosamente: " + venta;
		  		resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
	   }
	   
 }
 public void RFF16_AbandonarCarritoCompras(){

	   try {
		   
		   List<Consumidor> consumidores=superandes.darConsumidor();
		   
		   if(consumidores.isEmpty()){
			   panelDatos.actualizarInterfaz("No existen clientes en la base de datos. Por favor agrege uno y proceda a elegir el carrito de compras.");
		   }
		   else{
		   String[] estados= {"PERSONA NATURAL", "EMPRESA"};
		   String estado = (String) JOptionPane.showInputDialog(null,"Elija el tipoconsumidor del cliente que abandono el carrito de compras", "Abandonar Carrito Compras", JOptionPane.DEFAULT_OPTION, null, estados, estados[0]);
		
		   String consumidor="";
		   Long consumidorId= null;
		   
		   if(estado.equals("PERSONA NATURAL")){
			   consumidor= JOptionPane.showInputDialog (this, "Ingrese el documento de identidad del cliente.(Sin puntos) ", "Abandonar Carrito Compras", JOptionPane.QUESTION_MESSAGE);
			   try{
				   consumidorId= superandes.darPersonaNaturalPorId(Long.parseLong(consumidor)).getIdConsumidor();
				    
				   
			   }catch (Exception e) {
				   throw new Exception("No existen clientes en la base de datos con esa Identificacion. Por favor agregelo.");
				
				  
			   }
		   }else{
			   consumidor= JOptionPane.showInputDialog (this, "Ingrese el NIT del cliente. ", "Abandonar Carrito Compras", JOptionPane.QUESTION_MESSAGE);
			   try{
				consumidorId= superandes.darEmpresaPorId(Long.parseLong(consumidor)).getIdConsumidor();
				    
				   
			   }catch (Exception e) {
				  
				   throw new Exception("No existen clientes en la base de datos con esa Identificacion.");
				}
		   }
		   if(consumidorId!=null){
			   
			   CarritoCompras tb= superandes.darCarritoComprasPorIdConsumidor(consumidorId);
				if(tb!=null){
					List<ProductoCarritoCompras> productos= superandes.darProductoscarritoComprasPorIdcarritoCompras(tb.getId());
					superandes.eliminarProductoCarritoComprasPorIdcarritoCompras(tb.getId());
					superandes.eliminarCarritoComprasPorId(tb.getId());
					for (ProductoCarritoCompras productoCarritoCompras : productos) {
						List<Bodega> bodegas= superandes.darBodegasPorTipoYSucursal(superandes.darCategoriasPorId(superandes.darProductoPorId(productoCarritoCompras.getProducto()).getCategoria()).getTipoDeAlmacenamiento(), tb.getSucursal());
						superandes.ingresarCantidadDeProductoB(bodegas.get(0).getId(), productoCarritoCompras.getProducto(), productoCarritoCompras.getCantidadProducto());
					}
							   
				}
			   if (tb == null)
	    		{
	    			throw new Exception ("No se pudo encontrar el carrito de compras");
	    		}
	    		String resultado = "En  carrito de compraso\n\n";
	    		resultado += "Productos eliminados del carrito de compras exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}

		   }
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(e.getMessage());
	   }
	   
	   
	   
 }
 public void RFF17_RecolectarProductosAbandonados(){
	 
 }
 public void RFC1_DineroRecolectado(){
	   
	   try{
		   
		   List<VOSucursal> sucursales=superandes.darVOSucursales();
		    
		   String[] sucursalesn= new String[sucursales.size()];
		     
		   int i=0;
		   for (VOSucursal voSucursal :superandes.darSucursales()) {
			   sucursalesn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }	   
		   
		   if(sucursales.size()==0){
			   throw new Exception( "Agrege una Sucursal antes de consultar (Menu requerimientos F).");
			   
		   }
		   else{
			   List<VOVenta> ventas=superandes.darVOVentas();
			   
			   String fechaInicial= JOptionPane.showInputDialog (this, "Ingrese la fecha inicial. (Ej: 03/11/2018)", "Fecha inicial", JOptionPane.QUESTION_MESSAGE);
			   String fechaFinal = JOptionPane.showInputDialog (this, "Ingrese la fecha final. (Ej: 05/11/2018)", "Fecha final", JOptionPane.QUESTION_MESSAGE);
			   
			   
			   
			   String mensaje = "El dinero recolectado por ventas dentro de ese rango de fecha en cada Sucursal fueron:\n";
			   
			   for (int j = 0; j < ventas.size(); j++) {
				   if (ventas.get(j).getFecha().compareTo(fechaInicial)>=0 || ventas.get(j).getFecha().compareTo(fechaFinal)<=0) {
					
				   mensaje += "Sucursal con id: "+ventas.get(j).getSucursal()+" y ventas de: " + ventas.get(j).getValorTotal() + "\n";
				   }
			   }
			   JOptionPane.showMessageDialog(this, mensaje);
			   
			   String mensaje1 = "El dinero recolectado por ventas en el año corrido en cada Sucursal fueron:\n";
			   for (int j = 0; j < ventas.size(); j++) {
				   String hoy = superandes.darFechaCortaDeHoy();
				   if (ventas.get(j).getFecha().compareTo(fechaInicial)>=0 || ventas.get(j).getFecha().compareTo(hoy)<=0) {
					   mensaje1 += "Sucursal con id: "+ventas.get(j).getSucursal()+" y ventas de: " + ventas.get(j).getValorTotal() + "\n";
				   }
			   }JOptionPane.showMessageDialog(this, mensaje1);
			
		   }
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	   
 }
 public void RFC2_PromocionesPopulares(){
	   
	   try {
		   		   
		JOptionPane.showMessageDialog(this, "Las 20 promociones más populares son:");
		List<VOPromocionProducto> promocionesProductos = superandes.darVOPromocionProductoes();
		List<VOProductoVenta> productos = superandes.darVOProductoVentaes();
		
		 
			   
		String cadena = "";
		
		
		productos.sort(Comparator.comparing(VOProductoVenta::getCantidadVenta));
		int contador = 0;
		for (int i = 0; i < promocionesProductos.size(); i++) {
			for (int j = 1; j < productos.size(); j++) {
				if (promocionesProductos.get(i).getProducto() == productos.get(j).getProducto()) {
					
					cadena += "id de la promocion: "+promocionesProductos.get(i).getPromocion()+" y el # de ventas: "+productos.get(j).getCantidadVenta();
					contador++;
					if (contador > 20) {
						break;
					}
				}
			}JOptionPane.showMessageDialog(this, cadena);
		}
		
		
		
	   } 
	   catch (Exception e) 
	   {
	//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
	   }
	   
	   
	   
 }
   public void RFC3_IndiceOcupacionBodegaEstante(){
	   try{
	   
	   List<VOSucursal> sucursales=superandes.darVOSucursales();
	    
	   String[] sucursalesn= new String[sucursales.size()];
	     
	   int i=0;
	   for (VOSucursal voSucursal :superandes.darSucursales()) {
		   sucursalesn[i]=voSucursal.getNombre();
		   i=i+1;
		   
	   }
	   
	   
	   if(sucursales.size()==0){
		   throw new Exception( "Agrege una Sucursal antes de consultar (Menu requerimientos F).");
		   
	   }
	   String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal", "Indice de ocupacion", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
		
	   i=0;
	   long idSucursal=0;
	   for (String string : sucursalesn) {
		if(string.equals(sucursal)){
			idSucursal=sucursales.get(i).getId();
		}
		   i++;
	}
	   List<VOEstante> estantes=superandes.darVOEstantesPorSucursal(idSucursal);
	   List<VOBodega> bodegas=superandes.darVOBodegasPorSucursal(idSucursal);
	   
	   String[] estantesn= new String[estantes.size()];
	   String[] bodegasn= new String[bodegas.size()];
	     
	    i=0;
	   for (VOEstante voSucursal :estantes) {
		   estantesn[i]=voSucursal.getId()+"";
		   i=i+1;
		   
	   }
	   i=0;
	   for (VOBodega voSucursal :bodegas) {
		   bodegasn[i]=voSucursal.getId()+"";
		   i=i+1;
		   
	   }
	   
	   
	   if(estantes.size()==0&&bodegas.size()==0){if(estantes.size()==0){
		   JOptionPane.showMessageDialog(this, "No existen estantes para esta sucursal (Menu requerimientos F).");
		   
	   }else if(bodegas.size()==0){
		   JOptionPane.showMessageDialog(this, "No existen bodegas para esta sucursal  (Menu requerimientos F).");

	   }}
	   else{
		   String resultado = "Estos son los Indices de ocupacion de cada una de las bodegas y estantes de una sucursal"+"\n Bodegas:"+bodegas.size();
				
		for (VOBodega string : bodegas) {
			resultado+= "\n Bodega:"+ string;
			resultado+= "\n BodegaIndice:"+ (double)string.getCantidadProductos()/string.getCapacidadTotal();
		}
		resultado+= "\n Estantes:"+estantes.size();
		for (VOEstante string : estantes) {
			resultado+= "\n Estante:"+ string;
			resultado+= "\n EstanteIndice:"+ (double)string.getCantidadProductos()/string.getCapacidadTotal();
		
		}
    		
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		
	   }
	} 
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}

	   
   }
   public void RFC4_ProductosCaracteristica(){
	   String[] unidadDeMedidas= {"RANGO PRECIOUNITARIO"};
	String caracteristica = (String) JOptionPane.showInputDialog(null,"Seleccione criterio de busqueda", "Buscar un producto por una caracteristica", JOptionPane.DEFAULT_OPTION, null, unidadDeMedidas, unidadDeMedidas[0]);
	try{
		
		if(caracteristica!=null){
			if(caracteristica.equals(unidadDeMedidas[0])){
				String d1 = (String) JOptionPane.showInputDialog(null,"Seleccione rango inferior");
				String d2 = (String) JOptionPane.showInputDialog(null,"Seleccione rango superior");
				
				List<VOProducto> producto=superandes.darVOProductosRPrecioUnitario(Double.parseDouble(d1), Double.parseDouble(d2));
			
				String resultado = "Estos son productos que cumplieron la condicion:"+ producto.size();
				
				for (VOProducto voProducto : producto) {
				resultado+="\n Producto:"+ voProducto ;
			}
				 	
				panelDatos.actualizarInterfaz(resultado);
			
			}
			
			
			
		   }
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
			}
		   
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
   }
   public void RFC5_ComprasProveedores(){

	   try{
		   
	   List<VOProveedores> proveedores=superandes.darVOProveedoreses();
	   
	   String[] proveedoresn= new String[proveedores.size()];
	     
	   int i=0;
	   
	   for (VOProveedores voproveedores :proveedores) {
		   proveedoresn[i]=voproveedores.getNombre();
		   i=i+1;
		   
	   }
	   
	   if(proveedores.size()==0){
		   JOptionPane.showMessageDialog(this, "No existen proveedores registrados, por tanto no existen ordenes de pedido. (Menu requerimientos F).");

	   }
	   else{
	   
		
		String proveedor= (String) JOptionPane.showInputDialog(null,"Seleccione el proveedor que desea ver", "Mostrar compras hechas por Superandes a proveedores", JOptionPane.DEFAULT_OPTION, null, proveedoresn, proveedoresn[0]);
		
		int posPro=0;
		i=0;
		for (String string : proveedoresn) {
			if(string.equals(proveedor)){
				posPro=i;
			}
			i=i+1;
		}
		i=0;
		
		
		
		if (proveedor!=null)
		{
			List<OrdenPedido> tb= superandes.darOrdenPedidoPorIdProveedor(proveedores.get(posPro).getNit());
			
			if (tb == null)
    		{
    			throw new Exception ("No existen ordenes de pedido para el proveedor seleccionado");
    		}
			String resultado = "El proveedor con NIT:" + proveedores.get(posPro).getNit() + "tienen los siguientes pedidos:" ;
			for (OrdenPedido ordenPedido : tb) {
				resultado += "\n" + ordenPedido;
			}
			JOptionPane.showMessageDialog(this, "Los pedidos del proveedor se imprimieron en el panel de datos.");

			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
		}
	   }
	} 
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}

   
   }
   public void RFC6_VentasUsuarioDado(){
		  
	   try{		   
		   String idConsumidor = JOptionPane.showInputDialog (this, "Id del consumidor", JOptionPane.QUESTION_MESSAGE);
		   List<VOVenta> ventas=superandes.darVOVentas();
		   boolean existeConsumidor = false;
		   for (int i = 0; i < ventas.size() && !existeConsumidor; i++) {
			   if (ventas.get(i).getConsumidor() == (Long.parseLong(idConsumidor))) {
				existeConsumidor = true;
			 
			}
		   }
		   
		    if (!existeConsumidor) {
			JOptionPane.showMessageDialog(this, "El consumidor no ha realizado ninguna venta");
		    }
		   else{
			   String mensaje = "";
			   String fechaInicial = JOptionPane.showInputDialog (this, "Ingrese la fecha inicial (Ej: 22/01/2018)", JOptionPane.QUESTION_MESSAGE);
			   String fechaFinal = JOptionPane.showInputDialog (this, "Ingrese la fecha final (Ej: 03/11/2018)", JOptionPane.QUESTION_MESSAGE);
			   for (int i = 0; i < ventas.size() && (ventas.get(i).getConsumidor() == (Long.parseLong(idConsumidor))); i++) {
				if (ventas.get(i).getFecha().compareTo(fechaInicial)>=0 || ventas.get(i).getFecha().compareTo(fechaFinal)<=0) {
					mensaje += "Venta#"+ i + " " + ventas.get(i).getId()+ "\n";
					JOptionPane.showMessageDialog(null, "VentaTest#"+ i + " " + ventas.get(i).getId()
                            + "\n");
			}JOptionPane.showMessageDialog(null, mensaje);
		   }
		   } 
		 } 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
   }

   
	public void RFC7_AnalisisDeLaOperacion()
	{
		try{		   
			 
			List<VOSucursal> sucursales=superandes.darVOSucursales();
		    
			   String[] sucursalesn= new String[sucursales.size()];
			     
			   int i=0;
			   for (VOSucursal voSucursal :superandes.darSucursales()) {
				   sucursalesn[i]=voSucursal.getNombre();
				   i=i+1;
				   
			   }
			   if(sucursales.size()==0){
				   System.out.println("voy4");
				   JOptionPane.showMessageDialog(this, "Agrege una Sucursal antes de hacer esta consulta (Menu requerimientos F).");
				   
			   }else{
				   String[] pTemporalidad= {"1", "7", "30", "365", "3650"};
					String sTemporalidad = (String) JOptionPane.showInputDialog(null,"Seleccione la temporalidad en dias", "Analisis de operacion", JOptionPane.DEFAULT_OPTION, null, pTemporalidad, pTemporalidad[0]);
					
					String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
					String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipo de producto", "Tipo producto", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
					
					String[] tOpciones= {"MAYORDEMANDA", "MAYORESINGRESOS", "MENORESINGRESOS"};
					String opcion = (String) JOptionPane.showInputDialog(null,"Seleccione el tipo de consulta", "Analisis de operacion", JOptionPane.DEFAULT_OPTION, null, tOpciones, tOpciones[0]);
					
					
					VOSucursal sucursal = null;
					String sSucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal", "Analisis de operacion", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
					for (int j = 0; j < sucursales.size(); j++) {
						if (sSucursal.equals(sucursales.get(i).getNombre())) {
							sucursal = sucursales.get(i);
						}
					}
					List<VOVenta> ventas=superandes.darVOVentas();
					List<VOProductoVenta> productos = superandes.darVOProductoVentaes();
					int temporalidad = Integer.parseInt(sTemporalidad);
					
					productos.sort(Comparator.comparing(VOProductoVenta::getCantidadVenta));
					ventas.sort(Comparator.comparing(VOVenta::getValorTotal));
					
					
					String mensaje = "";
					if (opcion.equals("MAYORDEMANDA")) {
						List<String> fechasMayorDemanda = new ArrayList<>();
						int contador = 0;
						for (int j = 0; j < ventas.size(); j++) {
							if (ventas.get(i).getSucursal() == sucursal.getId()) {
								fechasMayorDemanda.add(ventas.get(i).getFecha());
								if (!ventas.get(i).getFecha().equals(ventas.get(i+1).getFecha())) {
									if (contador == temporalidad) {
										break;
									}
									contador++;
								}
							}
						}
						for (int j = 1; j <= fechasMayorDemanda.size(); j++) {
							mensaje += j + ". " + fechasMayorDemanda.get(i) + "\n"; 
							   
						}
						JOptionPane.showMessageDialog(this, mensaje);
						
					}else if (opcion.equals("MAYORESINGRESOS")) {
						int contador = 0;
						List<Integer> fechasMayoresIngresos = new ArrayList<>();
						for (int j = 0; j < productos.size(); j++) {
								fechasMayoresIngresos.add(productos.get(i).getCantidadVenta());
								if (productos.get(i).getCantidadVenta() != productos.get(i+1).getCantidadVenta()) {
									if (contador == temporalidad) {
										break;
									}
									contador++;
								}
						}
						for (int j = 1; j <= fechasMayoresIngresos.size(); j++) {
							mensaje += j + ". " + fechasMayoresIngresos.get(i) + "\n"; 
							   
						}
						JOptionPane.showMessageDialog(this, mensaje);
					}else
					{
						Collections.reverse(productos);
						int contador = 0;
						List<Integer> fechasMenoresIngresos = new ArrayList<>();
						for (int j = 0; j < productos.size(); j++) {
								fechasMenoresIngresos.add(productos.get(i).getCantidadVenta());
								if (productos.get(i).getCantidadVenta() != productos.get(i+1).getCantidadVenta()) {
									if (contador == temporalidad) {
										break;
									}
									contador++;
								}
						}
						for (int j = 1; j <= fechasMenoresIngresos.size(); j++) {
							mensaje += j + ". " + fechasMenoresIngresos.get(i) + "\n"; 
							   
						}
						JOptionPane.showMessageDialog(this, mensaje);
					}
			   }
			   
			} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
   
public void RFC8_EncontrarClientesFrecuentes(){
	try{
		 List<VOSucursal> sucursales=superandes.darVOSucursales();
		   
		   String[] sucursalesn= new String[sucursales.size()];
		     
		   int i=0;
		   for (VOSucursal voSucursal :superandes.darSucursales()) {
			   sucursalesn[i]=voSucursal.getNombre();
			   i=i+1;
			   
		   }
		   if(sucursales.size()==0){

			   JOptionPane.showMessageDialog(this, "No existen sucursales registradas (Menu requerimientos F).");
			   
		   }
		String sucursal= (String) JOptionPane.showInputDialog(null,"Seleccione la sucursal", "Clientes Frecuentes por sucursal", JOptionPane.DEFAULT_OPTION, null, sucursalesn, sucursalesn[0]);
		Sucursal sucu=null;
		i=0;
		for (String string : sucursalesn) {
			if(string.equals(sucursal)){
				sucu=(Sucursal) sucursales.get(i);
				break;
			}
			i=i+1;
		}   
		
		Date fechaIOperacion= new Date(Date.parse("6/1/2018"));
		List<Venta> ventasR=superandes.darVentasPorSucursal(sucu.getId());
		List<Venta> ventas= ventasR;
		int d=1;
		int m=6;
		int a=2018;
		Date voy=new Date((Date.parse(m+"/"+d+"/"+a)));
		int d2=1;
		int m2=7;
		int a2=2018;
		Date voy2=new Date((Date.parse(m2+"/"+d2+"/"+a2)));
		
		Long[] t= new Long[2];
		List<Long[] > cli= new ArrayList();
		List<Consumidor> clientesF= new ArrayList();
		
		Date actual = new Date(Calendar.getInstance().getTimeInMillis());
		while(!voy2.after(actual)&&!ventas.isEmpty()){
			voy=new Date((Date.parse(m+"/"+d+"/"+a)));
			voy2=new Date((Date.parse(m2+"/"+d2+"/"+a2)));
			
			for (Venta v : ventas) {
				String[] t3=v.getFecha().split("/");
				Date t2=new Date(Date.parse(t3[1]+"/"+t3[0]+"/"+t3[2]));
				
				if(t2.after(voy)&&t2.before(voy2)){
					boolean entre=false;
					for (Long[] longs : cli) {
						if(longs[0]==v.getConsumidor()){
							longs[1]=longs[1]+1;
							entre=true;
						}
					}
					if(entre==false){
						Long[] te={v.getConsumidor(), (long) 1};
						cli.add(te);
						
					}
					//ventas.remove(j);
				}
			}
			if(voy.equals(fechaIOperacion)){
				System.out.println(cli.size());
				
				for (Long[] longs : cli) {
					
					if(longs[1]>=2){
						clientesF.add(superandes.darConsumidorPorId(longs[0]));
					}
				}
			}else{
			for(int j=0; j<clientesF.size();j++){
				boolean enc=false;
				for (Long[] longs : cli) {
					if(clientesF.get(j).getId()==longs[0]&&longs[1]>=2){
						enc=true;
					}
				}
				if(enc==false){
					clientesF.remove(j);
				}
			}
			}
			cli= new ArrayList();
			
			m=m+1;
			m2=m2+1;
			if(m==13){
				m=1;
				a=a+1;
			}
			if(m2==13){
				m2=1;
				a2=a2+1;
			}
		}
		if (!clientesF.isEmpty())
			{
			  String resultado = "La lista de clientes Frecuentes de la sucursal :" + sucursal ;
				for (Consumidor ordenPedido : clientesF) {
					resultado += "\n" + ordenPedido;
				}
				JOptionPane.showMessageDialog(this, "Los clientes frecuentes de la sucursal se imprimieron en el panel de datos.");

				panelDatos.actualizarInterfaz(resultado);
				
			}
			else
			{
				panelDatos.actualizarInterfaz("No existen clientes frecuentes.");
			}
		   
		} 
		catch (Exception e) 
		{
		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	   
   }
   
   public void agregarProductoOfrecido(){
	   
	   try{
		   
	   List<VOProducto> productos=superandes.darVOProductos();
	   List<VOProveedores> proveedores=superandes.darVOProveedoreses();
	   
	   String[] productosn= new String[productos.size()];
	   String[] proveedoresn= new String[proveedores.size()];
	     
	   int i=0;
	   for (VOProducto voSucursal :productos) {
		   productosn[i]=voSucursal.getNombre();
		   i=i+1;
		   
	   }
	   i=0;
	   for (VOProveedores voproveedores :proveedores) {
		   proveedoresn[i]=voproveedores.getNombre();
		   i=i+1;
		   
	   }
	   if(productos.size()==0){
		   JOptionPane.showMessageDialog(this, "Agrege un producto (de la tienda) antes de registrar un producto de proveedor (Menu requerimientos F).");
		   
	   }else if(proveedores.size()==0){
		   JOptionPane.showMessageDialog(this, "Agrege un proveedor antes de registrar un producto de proveedor (Menu requerimientos F).");

	   }
	   else{
	   
		String precioProveedor = JOptionPane.showInputDialog (this, "Precio Proveedor", "Adicionar Producto Proveedor", JOptionPane.QUESTION_MESSAGE);
		
		String calificacion = JOptionPane.showInputDialog (this, "Calificacion", "Adicionar Producto Proveedor", JOptionPane.QUESTION_MESSAGE);

		String calidad = JOptionPane.showInputDialog (this, "Calidad del producto (Numerico de 0 a 10)", "Adicionar Producto Proveedor", JOptionPane.QUESTION_MESSAGE);

		String cumplimiento = JOptionPane.showInputDialog (this, "Cumplimiento (Numerico de 0 a 10)e", "Adicionar Producto Proveedor", JOptionPane.QUESTION_MESSAGE);
		
		String producto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto de la tienda que se relaciona", "Adicionar Producto Proveedor", JOptionPane.DEFAULT_OPTION, null, productosn, productosn[0]);
		System.out.println(productosn);
		System.out.println("v"+producto);

		String proveedor= (String) JOptionPane.showInputDialog(null,"Seleccione el proveedor del producto", "Adicionar Producto Proveedor", JOptionPane.DEFAULT_OPTION, null, proveedoresn, proveedoresn[0]);
		
		int posPro=0;
		int posProd=0;
		i=0;
		for (String string : proveedoresn) {
			if(string.equals(proveedor)){
				posPro=i;
			}
			i=i+1;
		}
		i=0;
		
		for (String string : productosn) {
			if(string.equals(producto)){
				posProd=i;
			}
			i=i+1;
		}
		
		
		
		if (precioProveedor != null&&cumplimiento!=null&&calificacion!=null&&proveedor!=null&&producto!=null)
		{
			VOProductoOfrecido tb= superandes.adicionarProductoOfrecido(Double.parseDouble(precioProveedor), Integer.parseInt(calificacion), calidad, cumplimiento, proveedores.get(posPro).getNit(), productos.get(posProd).getId());
			
			if (tb == null)
    		{
    			throw new Exception ("No se pudo crear Producto Ofrecido");
    		}
    		String resultado = "En adicionarProductoOfrecido\n\n";
    		resultado += "ProductoOfrecido adicionado exitosamente: " + tb;
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario, llene todos los campos.");
		}
	   }
	} 
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}

   
	   
   }
   
   
   public void agregarCiudad(){
   
   try 
	{
		String nombre = JOptionPane.showInputDialog (this, "Nombre de la ciudad", "Adicionar Ciudad", JOptionPane.QUESTION_MESSAGE);
		
		
		if (nombre != null)
		{
   		VOCiudad tb = superandes.adicionarCiudad (nombre);
   		if (tb == null)
   		{
   			throw new Exception ("No se pudo crear la ciudad con nombre: " + nombre );
   		}
   		String resultado = "En adicionarCiudad\n\n";
   		resultado += "Ciudad adicionado exitosamente: " + tb;
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
	} 
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
   }
   public void agregarAdministrador(){
	   
	   try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Usuario", "Adicionar administrador", JOptionPane.QUESTION_MESSAGE);
			String con = JOptionPane.showInputDialog (this, "Contrase�a", "Adicionar administrador", JOptionPane.QUESTION_MESSAGE);
			String cantRecompra = JOptionPane.showInputDialog (this, "Cantidad recompra", "Adicionar administrador", JOptionPane.QUESTION_MESSAGE);
			
			
			if (nombre != null)
			{
	   		VOAdministrador tb = superandes.adicionarAdministrador(Integer.parseInt(cantRecompra), nombre, con);
	   		if (tb == null)
	   		{
	   			throw new Exception ("No se pudo crear administrador con usuario: " + nombre );
	   		}
	   		String resultado = "En adicionarAdministrador\n\n";
	   		resultado += "Administrador adicionado exitosamente: " + tb;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	   }
   	public void requerimiento10(){
	   
	   try 
		{
		   
			String nombre = JOptionPane.showInputDialog (this, "Usuario", "Consultar ventas clientes", JOptionPane.QUESTION_MESSAGE);
			
			
			if (nombre != null)
			{
	   		Administrador tb = superandes.darAdministradorPorUsuario(nombre);
	   		String con = JOptionPane.showInputDialog (this, "Contrase�a", "Consultar ventas clientes", JOptionPane.QUESTION_MESSAGE);
			
	   		if(tb.getContrasenha().equals(con)){
	   			
	   			String fechai = JOptionPane.showInputDialog (this, "Fecha Inicio", "Consultar ventas clientes Formato(01/02/2017)", JOptionPane.QUESTION_MESSAGE);
	   			String fechaf = JOptionPane.showInputDialog (this, "Fecha Final", "Consultar ventas clientes Formato(01/02/2017)", JOptionPane.QUESTION_MESSAGE);
	   			
	   			List<VOProducto> productos=superandes.darVOProductos();
	   		   
	   		   String[] productosn= new String[productos.size()];
	   		     
	   		   int i=0;
	   		   for (VOProducto voSucursal :productos) {
	   			   productosn[i]=voSucursal.getNombre();
	   			   i=i+1;
	   			   
	   		   }
	   		   i=0;
	   		   
	   		   if(productos.size()==0){
	   			   JOptionPane.showMessageDialog(this, "Agrege un producto (de la tienda) antes de registrar un producto de proveedor (Menu requerimientos F).");
	   			   
	   		   }
	   		   else{
	   		   
	   			String producto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto de la query", "Consulta ventas", JOptionPane.DEFAULT_OPTION, null, productosn, productosn[0]);
	   			System.out.println(productosn);
	   			
	   			int posProd=0;
	   			i=0;
	   			for (String string : productosn) {
	   				if(string.equals(producto)){
	   					posProd=i;
	   				}
	   				i=i+1;
	   			}
	   			String sol="";
	   			long id= productos.get(posProd).getId();
	   			JCheckBox check1 = new JCheckBox("Fecha");
	   	        JCheckBox check2 = new JCheckBox("Nombre");
	   	        JCheckBox check3 = new JCheckBox("CantidadProducto");
	   	        JCheckBox check4 = new JCheckBox("Email");
	   	     JCheckBox check5 = new JCheckBox("Ninguno");
	   	        
	   	        Object[] options = { check1,check2,check3,check4,check5,"Aceptar"};
	   	        int x = JOptionPane.showOptionDialog(null, "Ordernar por",
	   	                "Seleccione el criterio para ordenar",
	   	                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

	   	        boolean sucursalB=false;
	   	        AdministradorSucursal t5= superandes.darAdministradorSucursalPorIdAdministrador(tb.getId());
	   	        long idsucu=0;
	   	        if(t5!=null){
	   	        idsucu=t5.getSucursal();
	   	        sucursalB=true;
	   	        	
	   	        }
	   	        if(check5.isSelected()){
	   	        	List<ConsumidorVenta> res= superandes.req10o(fechai, fechaf, id,sucursalB, idsucu, false, sol);
	   	        	String y="";
	   	        	for (ConsumidorVenta consumidorVenta : res) {
						y=y+consumidorVenta.toString()+ "\n";
					}
	   	        	if(res.isEmpty()){
	   	        		panelDatos.actualizarInterfaz("No se encontraron datos en los intervalos buscados");
	   	        	}else{
	   	        	panelDatos.actualizarInterfaz(y);
	   	        	}
	   	        }else{
	   	        	String tipo= "";
	   	        	if(check1.isSelected()){tipo=tipo+"fecha";};
	   	        	if(check2.isSelected()){if(tipo.equals("")){tipo=tipo+"nombre";}else{tipo=tipo+" ,nombre";}};
	   	        	if(check3.isSelected()){if(tipo.equals("")){tipo=tipo+"cantidadProducto";}else{tipo=tipo+" ,cantidadProducto";}};
	   	        	if(check4.isSelected()){if(tipo.equals("")){tipo=tipo+"email";}else{tipo=tipo+" ,email";}};
	   	        	
	   	        	List<ConsumidorVenta> res= superandes.req10o(fechai, fechaf, id,sucursalB, idsucu, true, tipo);
	   	        	String y="";
	   	        	for (ConsumidorVenta consumidorVenta : res) {
						y=y+consumidorVenta.toString()+ "\n";
					}
	   	        	if(res.isEmpty()){
	   	        		panelDatos.actualizarInterfaz("No se encontraron datos en los intervalos buscados");
	   	        	}else{
	   	        	panelDatos.actualizarInterfaz(y + "Datos ordenados por "+tipo);
	   	        	}
	   	        	System.out.println("t");
	   	        }
	   			
	   			
	   			
	   		   }
	   			
	   		}else{
	   			throw new Exception ("La contrase�a no concide con el usuario: " + nombre );
	   			
	   		}
			
	   		if (tb == null)
	   		{
	   			throw new Exception ("No se encontro administrador con usuario: " + nombre );
	   		}
	   		
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	   }
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por l�nea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperandesApp interfaz = new InterfazSuperandesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
    public void requerimiento11(){
 	   
 	   try 
 		{
 		   
 			String nombre = JOptionPane.showInputDialog (this, "Usuario", "Consultar ventas clientes", JOptionPane.QUESTION_MESSAGE);
 			
 			
 			if (nombre != null)
 			{
 	   		Administrador tb = superandes.darAdministradorPorUsuario(nombre);
 	   		String con = JOptionPane.showInputDialog (this, "Contrase�a", "Consultar ventas clientes", JOptionPane.QUESTION_MESSAGE);
 			
 	   		if(tb.getContrasenha().equals(con)){
 	   			
 	   			String fechai = JOptionPane.showInputDialog (this, "Fecha Inicio", "Consultar ventas clientes Formato(01/02/2017)", JOptionPane.QUESTION_MESSAGE);
 	   			String fechaf = JOptionPane.showInputDialog (this, "Fecha Final", "Consultar ventas clientes Formato(01/02/2017)", JOptionPane.QUESTION_MESSAGE);
 	   			
 	   			List<VOProducto> productos=superandes.darVOProductos();
 	   		   
 	   		   String[] productosn= new String[productos.size()];
 	   		     
 	   		   int i=0;
 	   		   for (VOProducto voSucursal :productos) {
 	   			   productosn[i]=voSucursal.getNombre();
 	   			   i=i+1;
 	   			   
 	   		   }
 	   		   i=0;
 	   		   
 	   		   if(productos.size()==0){
 	   			   JOptionPane.showMessageDialog(this, "Agrege un producto (de la tienda) antes de registrar un producto de proveedor (Menu requerimientos F).");
 	   			   
 	   		   }
 	   		   else{
 	   		   
 	   			String producto= (String) JOptionPane.showInputDialog(null,"Seleccione el producto de la query", "Consulta ventas", JOptionPane.DEFAULT_OPTION, null, productosn, productosn[0]);
 	   			System.out.println(productosn);
 	   			
 	   			int posProd=0;
 	   			i=0;
 	   			for (String string : productosn) {
 	   				if(string.equals(producto)){
 	   					posProd=i;
 	   				}
 	   				i=i+1;
 	   			}
 	   			String sol="";
 	   			long id= productos.get(posProd).getId();
 	   			JCheckBox check1 = new JCheckBox("Nombre");
 	   	        JCheckBox check2 = new JCheckBox("Email");
 	   	     JCheckBox check3 = new JCheckBox("Ninguno");
 	   	        
 	   	        Object[] options = { check1,check2,check3,"Aceptar"};
 	   	        int x = JOptionPane.showOptionDialog(null, "Ordernar por",
 	   	                "Seleccione el criterio para ordenar",
 	   	                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

 	   	        boolean sucursalB=false;
 	   	        AdministradorSucursal t5= superandes.darAdministradorSucursalPorIdAdministrador(tb.getId());
 	   	        long idsucu=0;
 	   	        if(t5!=null){
 	   	        idsucu=t5.getSucursal();
 	   	        sucursalB=true;
 	   	        	
 	   	        }
 	   	        if(check3.isSelected()){
 	   	        	List<ConsumidorVenta> res= superandes.req11o(fechai, fechaf, id,sucursalB, idsucu, false, sol);
 	   	        	String y="";
 	   	        	for (ConsumidorVenta consumidorVenta : res) {
 						y=y+consumidorVenta.toString()+ "\n";
 					}
 	   	        	if(res.isEmpty()){
 	   	        		panelDatos.actualizarInterfaz("No se encontraron datos en los intervalos buscados");
 	   	        	}else{
 	   	        	panelDatos.actualizarInterfaz(y);
 	   	        	}
 	   	        }else{
 	   	        	String tipo= "";
 	   	        	if(check1.isSelected()){if(tipo.equals("")){tipo=tipo+"nombre";}else{tipo=tipo+" ,nombre";}};
 	   	        	if(check2.isSelected()){if(tipo.equals("")){tipo=tipo+"email";}else{tipo=tipo+" ,email";}};
 	   	        	
 	   	        	List<ConsumidorVenta> res= superandes.req11o(fechai, fechaf, id,sucursalB, idsucu, true, tipo);
 	   	        	String y="";
 	   	        	for (ConsumidorVenta consumidorVenta : res) {
 						y=y+consumidorVenta.toString()+ "\n";
 					}
 	   	        	if(res.isEmpty()){
 	   	        		panelDatos.actualizarInterfaz("No se encontraron datos en los intervalos buscados");
 	   	        	}else{
 	   	        	panelDatos.actualizarInterfaz(y + "Datos ordenados por "+tipo);
 	   	        	}
 	   	        	System.out.println("t");
 	   	        }
 	   			
 	   			
 	   			
 	   		   }
 	   			
 	   		}else{
 	   			throw new Exception ("La contrase�a no concide con el usuario: " + nombre );
 	   			
 	   		}
 			
 	   		if (tb == null)
 	   		{
 	   			throw new Exception ("No se encontro administrador con usuario: " + nombre );
 	   		}
 	   		
 			}
 			else
 			{
 				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
 			}
 		} 
 		catch (Exception e) 
 		{
// 			e.printStackTrace();
 			String resultado = generarMensajeError(e);
 			panelDatos.actualizarInterfaz(resultado);
 		}
 	   }

}


