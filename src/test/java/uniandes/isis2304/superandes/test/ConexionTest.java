package uniandes.isis2304.superandes.test;

import static org.junit.Assert.*;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.*;
import org.junit.Test;

public class ConexionTest {
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ConexionTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasA.json"; 
	/**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private Superandes superandes;
    
    /**
     * Método de prueba para acceso correcto a una base de datos
     */
    @Test
    public void accessTest ()
  	{
    	BasicConfigurator.configure(); 

  	  	try
		{
			log.info ("Probando el acceso a la base de datos con datos válidos (BD, credenciales, esquema");
			superandes = new Superandes (openConfig (CONFIG_TABLAS_A));
			log.info ("Conexión realizada correstamente");
			log.info ("Cerrando la conexión");
			
			superandes.cerrarUnidadPersistencia ();
			log.info ("Conexión cerrada");
		}
		catch (Exception e)
		{
e.printStackTrace();
			log.info ("Prueba de acceso normal FALLÓ !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de acceso normal a la base de datos falló !! Revise persistence.xml.\n";
			msg += "Revise el log  y el de datanucleus para conocer el detalle de la excepción";
//			System.out.println (msg);
			fail (msg);
		}
  	}
    
    
    /**
    * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
    * @param tipo - El tipo de configuración deseada
    * @param archConfig - Archivo Json que contiene la configuración
    * @return Un objeto JSON con la configuración del tipo especificado
    * 			NULL si hay un error en el archivo.
    */
   private JsonObject openConfig (String archConfig)
   {
   	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
       return config;
   }	
	

}
