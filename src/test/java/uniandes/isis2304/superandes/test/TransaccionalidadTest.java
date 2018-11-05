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
import uniandes.isis2304.superandes.negocio.VOProducto;
import uniandes.isis2304.superandes.negocio.VOProveedores;




/**
 * Clase con los m�tdos de prueba 
 *
 */
public class TransaccionalidadTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(TransaccionalidadTest.class.getName());
	
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD tambi�n
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
	 * 			PRUEBAS Creaci�n y borrado
	 *****************************************************************/
	
    @Test
	public void CRDTest() 
	{
    	// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD");
			superandes = new Superandes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		
    	try
		{
			// Lectura Ciudad con la tabla vac�a
			List <VOCiudad> lista = superandes.darVOCiudades();
			
			// Lectura de Ciudad, Ciudad adicionada
			String nombre = "Cali";
			VOCiudad elemento = superandes.adicionarCiudad(nombre);
			lista = superandes.darVOCiudades();
			

			// Lectura de los Ciudad
			String nombre2 = "Bogota";
			VOCiudad elemento2 = superandes.adicionarCiudad(nombre2);
			lista = superandes.darVOCiudades();


			//TRANSACCIONALIDAD 
			//R1 REGISTRO PROVEEDORES
			List <VOProveedores> listap = superandes.darVOProveedoreses();
			
			// Lectura de Proveedores, Proveedores adicionada
			int i=listap.size();
			VOProveedores elementop = superandes.adicionarProveedores(3406,nombre);
			listap = superandes.darVOProveedoreses();
			
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elementop.getNit(), listap.get (i).getNit());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elementop.getNombre(), listap.get (i).getNombre());
			
			
			VOProveedores elemento2p = superandes.adicionarProveedores(3406,nombre2);
			
			
			assertEquals ("El primer Proveedores adicionado debe estar en la tabla !!", elementop.getNit(), listap.get (i).getNit());
			assertEquals ("El primer Proveedores adicionado debe estar en la tabla !!", elementop.getNombre(), listap.get (0).getNombre());
			
			assertEquals ("El segundo Proveedores adicionado debe estar en la tabla", elemento2p.getNit(), listap.get (i+1).getNit());
			assertEquals ("El segundo Proveedores adicionado debe estar en la tabla", elemento2p.getNombre(), listap.get (i+1).getNombre());
			

			
			
			//TRANSACCIONALIDAD 
			//R1 REGISTRO Productos
			List <VOProducto> listap1 = superandes.darVOProductos();
			
			// Lectura de Productos, Productos adicionada
			i=listap.size();
			VOProducto elementop1 = superandes.adicionarProducto("Tea Leaves - Oolong", (long) 143, "duis aliquam convallis nunc proin at turpis",  "Yamia", true,"semper est quam pharetra magna",(int) 9440,  "GR","4");
			listap1 = superandes.darVOProductos();
			
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elementop1.getId(), listap1.get (i).getId());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elementop1.getNombre(), listap1.get (i).getNombre());
			
			
			VOProducto elemento2p1 = superandes.adicionarProducto("Tea Leaves - Oolong", (long) 143, "duis aliquam convallis nunc proin at turpis",  "Yamia", true,"semper est quam pharetra magna",(int) 9440,  "GR","4");
			
			
			assertEquals ("El primer Productos adicionado debe estar en la tabla !!", elementop1.getId(), listap1.get (i).getId());
			assertEquals ("El primer Productos adicionado debe estar en la tabla !!", elementop1.getNombre(), listap1.get (0).getNombre());
			
			assertEquals ("El segundo Productos adicionado debe estar en la tabla", elemento2p1.getId(), listap1.get (i+1).getId());
			assertEquals ("El segundo Productos adicionado debe estar en la tabla", elemento2p1.getNombre(), listap1.get (i+1).getNombre());
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de operaciones sobre la tabla Ciudad.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepci�n";
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
    	// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando la restricci�n de UNICIDAD ");
			superandes = new Superandes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Ciudad incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Ciudad incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura con la tabla vac�a
			List <VOCiudad> lista = superandes.darVOCiudades();
			
			// Lectura 
			String nombre = "Cali";
			VOCiudad elemento1 = superandes.adicionarCiudad (nombre);
			lista = superandes.darVOCiudades();
			
			VOCiudad elemento2 = superandes.adicionarCiudad (nombre);
			assertNull ("No puede adicionar dos tipos de bebida con el mismo nombre !!", elemento2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD sobre la tabla ciudad.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepci�n";
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
	*  c. Haga las pruebas de inserci�n para cada caso
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
	*  c. Haga las pruebas de inserci�n y borrado correspondientes.

	*/
	
	//La entidad Ciudad no cuenta con restricciones de chequeo

	/* ****************************************************************
	 * 			M�todos de configuraci�n
	 *****************************************************************/
    /**
     * Lee datos de configuraci�n para la aplicaci�n, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipoconsumidor - El tipoconsumidor de configuraci�n deseada
     * @param archConfig - Archivo Json que contiene la configuraci�n
     * @return Un objeto JSON con la configuraci�n del tipoconsumidor especificado
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
			log.info ("Se encontr� un archivo de configuraci�n de tablas v�lido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de tablas v�lido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
