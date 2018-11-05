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
import uniandes.isis2304.superandes.negocio.VOPersonaNatural;




/**
 * Clase con los m�tdos de prueba 
 *
 */
public class PersonaNaturalTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(PersonaNaturalTest.class.getName());
	
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
	/**
	 * M�todo que prueba las operaciones sobre la tabla 
	 * 1. Adicionar PersonaNatural
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar PersonaNatural por su identificador
	 * 4. Borrar PersonaNatural por su nombre
	 */
    @Test
	public void CRDPersonaNaturalTest() 
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
			// Lectura PersonaNatural con la tabla vac�a
			List <VOPersonaNatural> lista = superandes.darVOPersonasNaturales();
			int t=lista.size ();
			assertEquals ("No debe haber PersonaNatural creados!!", t, lista.size ());

			// Lectura de PersonaNatural, PersonaNatural adicionada
			String nombre = "Cali";
			VOPersonaNatural elemento = superandes.adicionarPersonaNatural(25, 3504);
			lista = superandes.darVOPersonasNaturales();
			assertEquals ("Debe haber PersonaNatural creado !!", t+1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elemento.getDocumentoIdentidad(), superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getDocumentoIdentidad());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elemento.getIdConsumidor(),  superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getIdConsumidor());
			

			// Lectura de los PersonaNatural
			String nombre2 = "Bogota";
			VOPersonaNatural elemento2 = superandes.adicionarPersonaNatural(20, 350);
			lista = superandes.darVOPersonasNaturales();
			assertEquals ("El primer PersonaNatural adicionado debe estar en la tabla !!", elemento.getDocumentoIdentidad(),  superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getDocumentoIdentidad());
			assertEquals ("El primer PersonaNatural adicionado debe estar en la tabla !!", elemento.getIdConsumidor(),  superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getIdConsumidor());
			
			assertEquals ("El primer PersonaNatural adicionado debe estar en la tabla !!", elemento2.getDocumentoIdentidad(),  superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getDocumentoIdentidad());
			assertEquals ("El primer PersonaNatural adicionado debe estar en la tabla !!", elemento2.getIdConsumidor(),  superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getIdConsumidor());
			

			// Prueba de eliminaci�n, dado su identificador
			long tbEliminados = superandes.eliminarPersonaNaturalPorId (elemento.getDocumentoIdentidad());
			assertEquals ("Debe haberse eliminado PersonaNatural !!", 1, tbEliminados);
			lista = superandes.darVOPersonasNaturales();
			assertEquals ("Debe haber un PersonaNatural !!", t+1, lista.size ());
			assertEquals ("El segundo PersonaNatural adicionado debe estar en la tabla", elemento2.getDocumentoIdentidad(), superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getDocumentoIdentidad());
			assertEquals ("El segundo PersonaNatural adicionado debe estar en la tabla", elemento2.getIdConsumidor(), superandes.darPersonaNaturalPorId(elemento.getDocumentoIdentidad()).getIdConsumidor());
			
			// Prueba de eliminaci�n, dado su identificador
			tbEliminados = superandes.eliminarPersonaNaturalPorId (elemento2.getDocumentoIdentidad());
			assertEquals ("Debe haberse eliminado un PersonaNatural !!", t+1, tbEliminados);
			lista = superandes.darVOPersonasNaturales();
			assertEquals ("La tabla debi� quedar vac�a !!", t, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de operaciones sobre la tabla PersonaNatural.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla PersonaNatural");
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
	public void unicidadPersonaNaturalTest() 
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
			log.info ("Prueba de UNICIDAD de PersonaNatural incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de PersonaNatural incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura con la tabla vac�a
			List <VOPersonaNatural> lista = superandes.darVOPersonasNaturales();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura 
			String nombre = "Cali";
			VOPersonaNatural elemento = superandes.adicionarPersonaNatural(25, 3504);
			lista = superandes.darVOPersonasNaturales();
			assertEquals ("Debe haber un PersonaNatural creado !!", 1, lista.size ());

			VOPersonaNatural elemento2 = superandes.adicionarPersonaNatural(20, 350);
			assertNull ("No puede adicionar dos tipos de bebida con el mismo nombre !!", elemento2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD sobre la tabla PersonaNatural.\n";
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
	
	//La entidad PersonaNatural no cuenta con restricciones de FK
	
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
	
	//La entidad PersonaNatural no cuenta con restricciones de chequeo

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


