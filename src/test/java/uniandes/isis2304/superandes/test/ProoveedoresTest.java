package uniandes.isis2304.superandes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOCiudad;




/**
 * Clase con los métdos de prueba 
 *
 */
public class ProoveedoresTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ProoveedoresTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasA.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private Superandes superandes;
	
    
    
    /* ****************************************************************
	 * 			PRUEBAS Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla 
	 * 1. Adicionar Ciudad
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar Ciudad por su identificador
	 * 4. Borrar Ciudad por su nombre
	 */
    @Test
	public void CRDCiudadTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD");
			superandes = new Superandes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		
    	try
		{
			// Lectura Ciudad con la tabla vacía
			List <VOCiudad> lista = superandes.darVOCiudades();
			assertEquals ("No debe haber Ciudad creados!!", 0, lista.size ());

			// Lectura de Ciudad, Ciudad adicionada
			String nombre = "Cali";
			VOCiudad elemento = superandes.adicionarCiudad(nombre);
			lista = superandes.darVOCiudades();
			assertEquals ("Debe haber Ciudad creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elemento.getId(), lista.get (0).getId());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elemento.getNombre(), lista.get (0).getNombre());
			

			// Lectura de los Ciudad
			String nombre2 = "Bogota";
			VOCiudad elemento2 = superandes.adicionarCiudad(nombre2);
			lista = superandes.darVOCiudades();
			assertEquals ("El primer Ciudad adicionado debe estar en la tabla !!", elemento.getId(), lista.get (0).getId());
			assertEquals ("El primer Ciudad adicionado debe estar en la tabla !!", elemento.getNombre(), lista.get (0).getNombre());
			
			assertEquals ("El segundo Ciudad adicionado debe estar en la tabla", elemento2.getId(), lista.get (1).getId());
			assertEquals ("El segundo Ciudad adicionado debe estar en la tabla", elemento2.getNombre(), lista.get (1).getNombre());
			

			// Prueba de eliminación, dado su identificador
			long tbEliminados = superandes.eliminarCiudadPorId (elemento.getId ());
			assertEquals ("Debe haberse eliminado Ciudad !!", 1, tbEliminados);
			lista = superandes.darVOCiudades();
			assertEquals ("Debe haber un Ciudad !!", 1, lista.size ());
			assertEquals ("El segundo Ciudad adicionado debe estar en la tabla", elemento2.getId(), lista.get (0).getId());
			assertEquals ("El segundo Ciudad adicionado debe estar en la tabla", elemento2.getNombre(), lista.get (0).getNombre());
			
			// Prueba de eliminación, dado su identificador
			tbEliminados = superandes.eliminarCiudadPorId (elemento2.getId ());
			assertEquals ("Debe haberse eliminado un Ciudad !!", 1, tbEliminados);
			lista = superandes.darVOCiudades();
			assertEquals ("La tabla debió quedar vacía !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Ciudad.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Ciudad");
		}
		finally
		{
			superandes.limpiarSuperandes();
    		superandes.cerrarUnidadPersistencia ();    		
		}
	}

    /* ****************************************************************
	 * 			PRUEBAS UNICIDAD
	 *****************************************************************/
	/**
	 *
	 * 1. Adicionar una tupla 1, con una PK conocida y nueva
	 * 2. Adicionar una tupla 2, con la PK de la tupla 1
	 * 3. Pruebas de insercion de del primer y segundo registro
	 */
	@Test
	public void unicidadCiudadTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD ");
			superandes = new Superandes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Ciudad incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Ciudad incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura con la tabla vacía
			List <VOCiudad> lista = superandes.darVOCiudades();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura 
			String nombre = "Cali";
			VOCiudad elemento1 = superandes.adicionarCiudad (nombre);
			lista = superandes.darVOCiudades();
			assertEquals ("Debe haber un Ciudad creado !!", 1, lista.size ());

			VOCiudad elemento2 = superandes.adicionarCiudad (nombre);
			assertNull ("No puede adicionar dos tipos de bebida con el mismo nombre !!", elemento2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla ciudad.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla");
		}    				
		finally
		{
			superandes.limpiarSuperandes();
    		superandes.cerrarUnidadPersistencia ();    		
		}
	}
	/* ****************************************************************
	 * 			PRUEBAS INTEGRIDAD FK
	 *****************************************************************/
	/**
	 *
	 * Pruebas de integridad con FK
	*  a. Inserte una tupla 1 que tenga una FK que se encuentra en la tabla referenciada
	*  b. Inserte una tupla 1 que tenga una FK que no se encuentra en la tabla referenciada
	*  c. Haga las pruebas de inserción para cada caso
	*  d. Haga pruebas de borrado de tuplas maestras y dependientes.
	*/
	
	//La entidad Ciudad no cuenta con restricciones de FK
	
	/* ****************************************************************
	 * 			PRUEBAS INTEGRIDAD RESTRICCIONES DE CHEQUEO
	 *****************************************************************/
	/**
	 *
	 * Pruebas de integridad de acuerdo con restricciones de chequeo
	*  a. Inserte tuplas que cumplen con las restricciones de chequeo establecidas
	*  b. Inserte tuplas que violan las restricciones de chequeo establecidas
	*  c. Haga las pruebas de inserción y borrado correspondientes.

	*/
	
	//La entidad Ciudad no cuenta con restricciones de chequeo

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
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
