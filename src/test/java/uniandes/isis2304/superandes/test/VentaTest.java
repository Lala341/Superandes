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
import uniandes.isis2304.superandes.negocio.VOVenta;




/**
 * Clase con los métdos de prueba 
 *
 */
public class VentaTest {


	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(VentaTest.class.getName());
	
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
	 * 1. Adicionar Ventaes
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar Ventaes por su identificador
	 * 4. Borrar Ventaes por su nombre
	 */
    @Test
	public void CRDVentaesTest() 
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
			// Lectura Ventaes con la tabla vacía
			List <VOVenta> lista = superandes.darVOVentas();
			int sizei=lista.size();
			// Lectura de Ventaes, Ventaes adicionada
			String nombre = "Cali";
			long n = 1111111111;
			
			VOVenta elemento = superandes.adicionarVenta("20/06/2018", "CREDITO", (double) 85599,(long) 29, (long)2);
			lista = superandes.darVOVentas();
			assertEquals ("Debe haber Ventaes creado !!", sizei+1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elemento.getId(), superandes.darVentasPorId(elemento.getId()).getId());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", elemento.getConsumidor(), superandes.darVentasPorId(elemento.getId()).getConsumidor());
			

			// Lectura de los Ventaes
			String nombre2 = "Bogota";
			long n2 = 991211111;
			
			VOVenta elemento2 = superandes.adicionarVenta("20/06/2018", "CREDITO", (double) 85599,(long) 30, (long)1);
			lista = superandes.darVOVentas();
			assertEquals ("El primer Ventaes adicionado debe estar en la tabla !!", elemento.getId(), lista.get (lista.size()-2).getId());
			assertEquals ("El primer Ventaes adicionado debe estar en la tabla !!", elemento.getConsumidor(), lista.get (lista.size()-2).getConsumidor());
			
			assertEquals ("El segundo Ventaes adicionado debe estar en la tabla", elemento2.getId(), lista.get (lista.size()-1).getId());
			assertEquals ("El segundo Ventaes adicionado debe estar en la tabla", elemento2.getConsumidor(), lista.get (lista.size()-1).getConsumidor());
			

			// Prueba de eliminación, dado su identificador
			long tbEliminados = superandes.eliminarVentaPorId (elemento.getId());
			assertEquals ("Debe haberse eliminado Ventaes !!", 1, tbEliminados);
			lista = superandes.darVOVentas();
			assertEquals ("El segundo Ventaes adicionado debe estar en la tabla", elemento2.getId(), lista.get (lista.size()-1).getId());
			assertEquals ("El segundo Ventaes adicionado debe estar en la tabla", elemento2.getConsumidor(), lista.get (lista.size()-1).getConsumidor());
			
			// Prueba de eliminación, dado su identificador
			tbEliminados = superandes.eliminarVentaPorId (elemento2.getId());
			assertEquals ("Debe haberse eliminado un Ventaes !!", 1, tbEliminados);
			lista = superandes.darVOVentas();
			assertEquals ("La tabla debió quedar vacía !!", sizei, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Ventaes.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Ventaes");
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
	public void unicidadVentaesTest() 
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
			log.info ("Prueba de UNICIDAD de Ventaes incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Ventaes incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura con la tabla vacía
			List <VOVenta> lista = superandes.darVOVentas();
			int sizei=lista.size();
			// Lectura 
			String nombre = "Cali";
			long n = 1111111111;
			
			VOVenta elemento = superandes.adicionarVenta("20/06/2018", "CREDITO", (double) 85599,(long) 29, (long)2);
			lista = superandes.darVOVentas();
			assertEquals ("Debe haber un Ventaes creado !!", sizei+1, lista.size ());

			
			VOVenta elemento2 = superandes.adicionarVenta("20/06/2018", "CREDITO", (double) 85599,(long) 30, (long)1);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Ventaes.\n";
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
	
	@Test
	public void integridadVentaesTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de INTEGRIDAD ");
			superandes = new Superandes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de INTREGRIDAD de Ventaes incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de  INTREGRIDAD  de Ventaes incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superandes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura con la tabla vacía
			List <VOVenta> lista = superandes.darVOVentas();
			int sizei=lista.size();
			// Lectura 
			String nombre = "Cali";
			long n = 1111111111;
			
			VOVenta elemento = superandes.adicionarVenta("20/06/2018", "CREDITO", (double) 85599,(long) 29, (long)2);
			lista = superandes.darVOVentas();
			assertEquals ("Debe haber un Ventaes creado !!", sizei+1, lista.size ());

			
			VOVenta elemento2 = superandes.adicionarVenta("20/06/2018", "CREDITO", (double) 85599,(long) 100, (long)1);
			assertNull ("No existe la referencia del consumidor!!", elemento2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Ventaes.\n";
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
	 * 			PRUEBAS INTEGRIDAD RESTRICCIONES DE CHEQUEO
	 *****************************************************************/
	/**
	 *
	 * Pruebas de integridad de acuerdo con restricciones de chequeo
	*  a. Inserte tuplas que cumplen con las restricciones de chequeo establecidas
	*  b. Inserte tuplas que violan las restricciones de chequeo establecidas
	*  c. Haga las pruebas de inserción y borrado correspondientes.

	*/
	
	//La entidad Venta no cuenta con restricciones de chequeo

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipoconsumidor - El tipoconsumidor de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipoconsumidor especificado
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

