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
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
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

import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOAdministrador;
import uniandes.isis2304.superandes.negocio.VOCiudad;
import uniandes.isis2304.superandes.negocio.VOConsumidor;
import uniandes.isis2304.superandes.negocio.VOProducto;
import uniandes.isis2304.superandes.negocio.VOPromocion;
import uniandes.isis2304.superandes.negocio.VOProveedores;



@SuppressWarnings("serial")

public class InterfazSuperandesApp extends JFrame implements ActionListener{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperandesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
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
     * Asociación a la clase principal del negocio.
     */
    private Superandes superandes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazSuperandesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
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
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
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
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Superandes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
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
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
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
	 * 			Métodos administrativos
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
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperandes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("superandes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = superandes.limpiarSuperandes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
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
	 * Muestra la presentación general del proyecto
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
	 * Muestra el script de creación de la base de datos
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
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
    

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
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
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
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
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
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
			throw new Exception ("El NIT debe cumplir con los requerimientos, sin puntos y de tamaño 10 " );
		}
		
		if (nombre != null&&nit!=null&&nit.length()==10)
		{
    		VOProveedores tb = superandes.adicionarProveedores (Long.parseLong(nit), nombre);
    		if (tb == null)
    		{
    			throw new Exception ("No se pudo crear un proveedor con nombre: " + nombre +", y nit:" + nit);
    		}
    		String resultado = "En adicionarProveedor\n\n";
    		resultado += "Proveedor adicionado exitosamente: " + tb;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
			String cant = JOptionPane.showInputDialog (this, "Cantidad del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String unidadDeMedida = JOptionPane.showInputDialog (this, "Unidad de medida del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String marca = JOptionPane.showInputDialog (this, "Marca del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			
			String[] categorias= {"PERECEDERO", "NOPERECEDERO"};
			String categoria = (String) JOptionPane.showInputDialog(null,"Seleccione la categoria", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, categorias, categorias[0]);
			String[] tcategorias= {"ASEO", "ABARROTES", "PRENDASDEVESTIR", "MUEBLES", "HERRAMIENTAS", "ELECTRODOMESTICOS", "CONGELADOS"};
			String tipoCategoria = (String) JOptionPane.showInputDialog(null,"Seleccione el tipo de producto de la categoria", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, tcategorias, tcategorias[0]);
			
			int es= JOptionPane.showConfirmDialog(this, "¿El producto esta disponible?", "Estado producto", JOptionPane.YES_NO_OPTION );
			boolean estado = (es ==0)?true:false;
			
			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String especificacionDeEmpaquetado = JOptionPane.showInputDialog (this, "Especificacion de empaquetado del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String precioPorUnidadMedida = JOptionPane.showInputDialog (this, "Precio Por Unidad Medida del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String precioUnitario = JOptionPane.showInputDialog (this, "Precio Unitario del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			String presentacion = JOptionPane.showInputDialog (this, "Presentacion del producto", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
			
			Date fecI= new Date(0);
			if(categoria.equals(categorias[0])){
				Integer[] dias= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
				String diai =  JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha vencimiento)", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, dias, dias[0]) + "";
				String[] meses= {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "noviembre", "diciembre"};
				String mesi = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha vencimiento)", "Adicionar Producto", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
				String anhoi = JOptionPane.showInputDialog (this, "Seleccione un año  (Fecha vencimiento)", "Adicionar Producto", JOptionPane.QUESTION_MESSAGE);
				
				String posMesi= "0";
				int i=0;
				for (String string : meses) { i=i+1;
				if(string.equals(mesi)){ posMesi=i+"";}}
				
				fecI= new Date(Date.parse(diai+"/"+posMesi+ "/"+anhoi));
			}
			
			if (nombre != null&&cant!=null&&categoria!=null&&codigoDeBarras!=null&&marca!=null&&precioPorUnidadMedida!=null&&precioUnitario!=null&&unidadDeMedida!=null)
			{
				VOProducto tb;
				if(categoria.equals(categorias[0])){
					
	    		 tb = superandes.registrarProductoPerecedero(nombre, categoria, Integer.parseInt(cant), codigoDeBarras, especificacionDeEmpaquetado, estado, marca, Double.parseDouble(precioPorUnidadMedida), Double.parseDouble(precioUnitario), presentacion, unidadDeMedida, tipoCategoria,fecI);
				}
				else{
					 tb = superandes.registrarProductoNoPerecedero(nombre, categoria, Integer.parseInt(cant), codigoDeBarras, especificacionDeEmpaquetado, estado, marca, Double.parseDouble(precioPorUnidadMedida), Double.parseDouble(precioUnitario), presentacion, unidadDeMedida, tipoCategoria);
							
				}
				if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear un producto con nombre: " + nombre);
	    		}
	    		String resultado = "En adicionarProveedor\n\n";
	    		resultado += "Proveedor adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario, llene todos los campos.");
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
			String tipo = (String) JOptionPane.showInputDialog(null,"Seleccione el tipo de cliente", "Adicionar producto", JOptionPane.DEFAULT_OPTION, null, tipos, tipos[0]);
			
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
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario, llene todos los campos.");
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
	   
   }
   public void RF5_RegistrarBodega(){
	   
   }
   public void RF6_RegistrarEstante(){
	   
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
			String anhoi = JOptionPane.showInputDialog (this, "Seleccione un año  (Fecha inicial)", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			String diaf = JOptionPane.showInputDialog(null,"Seleccione un dia (Fecha final)", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, dias, dias[0])+ "";
			String mesf = (String) JOptionPane.showInputDialog(null,"Seleccione un mes  (Fecha final)", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, meses, meses[0]);
			String anhof = JOptionPane.showInputDialog (this, "Seleccione un año  (Fecha final)", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
			String[] estados= {"FINALIZADO", "VIGENTE"};
			String estado = (String) JOptionPane.showInputDialog(null,"Seleccione un estado", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, estados, estados[0]);
			String[] tipos= {"PROMODESCUENTO", "PROMOPARTEDESCUENTO", "PROMOUNIDAD", "PROMOCANTIDAD"};
			String tipo = (String) JOptionPane.showInputDialog(null,"Seleccione un tipo", "Adicionar promocion", JOptionPane.DEFAULT_OPTION, null, tipos, tipos[0]);
			
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
	    		VOPromocion tb = superandes.adicionarPromocion(nombre, descripcion, tipo, fecI, fecF, estado);
	    		if(tipo!=null){
					if(tipo.equals(tipos[0])){
						String desc = JOptionPane.showInputDialog (this, "Seleccione un porcentaje descuento (sin %)", "Adicionar promocion con descuento", JOptionPane.QUESTION_MESSAGE);
						
					}
					else if(tipo.equals(tipos[1])){
						String desc2 = JOptionPane.showInputDialog (this, "Seleccione un porcentaje descuento (sin %)", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						String unip = JOptionPane.showInputDialog (this, "Seleccione cantidad unidades pagadas", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						
					}
					else if(tipo.equals(tipos[2])){
						String unip = JOptionPane.showInputDialog (this, "Seleccione cantidad unidades pagadas", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						String univ = JOptionPane.showInputDialog (this, "Seleccione cantidad unidades vendidad (entregadas)", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						
					}
					else if(tipo.equals(tipos[3])){
						String cnip = JOptionPane.showInputDialog (this, "Seleccione cantidad pagado", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						String cniv = JOptionPane.showInputDialog (this, "Seleccione cantidad vendido (entregado)", "Adicionar promocion con parte descuento", JOptionPane.QUESTION_MESSAGE);
						
					}
					
				}
	    		
	    		if (tb == null)
	    		{
	    			throw new Exception ("No se pudo crear un proveedor con nombre: " + nombre );
	    		}
	    		String resultado = "En adicionarProveedor\n\n";
	    		resultado += "Proveedor adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
	   
   }
   public void RF9_RegistrarPedido(){
	   
   }
   public void RF10_RegistrarLlegadaPedido(){
	   
   }
   public void RF11_RegistrarVenta(){
	   
   }
   public void RFC1_DineroRecolectado(){
	   
   }
   public void RFC2_PromocionesPopulares(){
	   
   }
   public void RFC3_IndiceOcupacionBodegaEstante(){
	   
   }
   public void RFC4_ProductosCaracteristica(){
	   
   }
   public void RFC5_ComprasProveedores(){
	   
   }
   public void RFC6_VentasUsuarioDado(){
	   
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
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		else
		{
			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
			String con = JOptionPane.showInputDialog (this, "Contraseña", "Adicionar administrador", JOptionPane.QUESTION_MESSAGE);
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
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
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

}
