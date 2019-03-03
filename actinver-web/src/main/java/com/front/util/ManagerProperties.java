package com.front.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

public class ManagerProperties {

	private static HashMap<String,Properties> cache_properties;
	private static ManagerProperties instance;
	private static final String AMBIENTE=(System.getProperty("sistema.ambiente")==null?"produccion":System.getProperty("sistema.ambiente"));
	private static final Logger log= Logger.getLogger(ManagerProperties.class);

	private ManagerProperties() {
		cache_properties = new HashMap<String, Properties>();
	}

	/**
	 * Método que Obtiene una instancia única de la clase ConnectionDataBase
	 * */
	public static ManagerProperties getInstance() {
		if (instance == null) {
			instance = new ManagerProperties();
		}
		return instance;
	}

	/**
	 * Obtiene la propiedad del archivo
	 *
	 * @param fileName
	 *            nombre del archivo que contiene las propiedades.
	 * @param key
	 *            id de la propiedad.
	 * @return valor de la propiedad solicitada.
	 * */
	public String getProperty(String fileName, String key) {
		Properties properties = null;
		String value = null;
		properties = cache_properties.get(fileName);
		if (properties == null) {
			try {
				properties = loadFile(fileName);
				if( properties == null ){
					log.info( "properties retornado por metodo loadFile FUE null" );
					properties = new Properties();
				}
			} catch (IOException e) {
				properties = new Properties();
				log.info("[ Error ] - No se pudo cargar el archivo de propiedades");
				e.printStackTrace();
			}
		}
		value = properties.getProperty(key);
		if (value == null) {
			log.info("[ Warning ] - La propiedad [ " + key + " ] no existe");
		}
		return value;
	}

	/**
	 * Obtiene la propiedad del archivo
	 *
	 * @param fileName
	 *            nombre del archivo que contiene las propiedades.
	 * @param key
	 *            id de la propiedad.
	 * @return valor de la propiedad solicitada.
	 * */
	public Vector<String> getProperty(String fileName) {
		Properties properties = null;
		Vector<String> key = new Vector<String>();
		properties = cache_properties.get(fileName);
		if (properties == null) {
			try {
				properties = loadFile(fileName);
			} catch (IOException e) {
				properties = new Properties();
				log.info("[ Error ] - No se pudo cargar el archivo de propiedades");
				e.printStackTrace();
			}
		}
		Enumeration<Object> values = properties.keys();
		while(values.hasMoreElements()){
			key.add(String.valueOf(values.nextElement()));
		}

		if (key == null || key.size()==0) {
			log.info("[ Warning ] - La propiedad [ " + key + " ] no existe");
		}
		return key;
	}

	/**
	 * Método que carga el archivo properties y lo sube a cache.
	 *
	 * @param fileName
	 *            nombre del Archivo con extensión
	 * @return Properties - propiedades del archivo cargado
	 * @throws IOException
	 */
	private Properties loadFile(String fileName) throws IOException {
		Properties properties = null;
		StringBuilder path = new StringBuilder();
		path.append( "/" ).append( "properties" ).append( "/" ).append(AMBIENTE).append( "/" ).append(fileName);

		log.info( "se tratara de cargar archivo : [" + path.toString() + "]" );
		
		InputStream url = null;
		try {
			url = ManagerProperties.class.getResourceAsStream( path.toString() );
			if( url != null ){
				properties = new Properties();
				properties.load( url );
				cache_properties.put(fileName, properties);
			}else{
				log.info("[ Warning ] - El archivo de propiedades [ " + path + " ] no existe");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if( null != url ){
				url.close();
			}
		}
		
		
		return properties;
	}

	/**
	 * Obtiene el archivo
	 *
	 * @param fileName
	 *            nombre del archivo que contiene las propiedades.
	 * @param key
	 *            id de la propiedad.
	 * @return valor de la propiedad solicitada.
	 * */
	public Properties loadPropertie(String fileName) {
		Properties properties = null;
		properties = cache_properties.get(fileName);
		if (properties == null) {
			try {
				properties = loadFile(fileName);
			} catch (IOException e) {
				properties = new Properties();
				log.info("[ Error ] - No se pudo cargar el archivo de propiedades");
				e.printStackTrace();
			}
		}
		return properties;
	}

	/**
	 * Metodo que carga los properties que seran utilizados.
	 * */

	public void loadProperties() throws IOException {

		StringBuilder path = new StringBuilder();
		path.append( "/" ).append( "properties" ).append( "/" ).append(AMBIENTE).append( "/" );
		URL url=ManagerProperties.class.getResource(path.toString());
		File file = new File(url.getFile());

		if (!file.isDirectory()) {
			log.info("El EAR esta empaquetado");

			path = new StringBuilder().append("properties").append("/").append(AMBIENTE).append( "/" );
			
			ZipFile zipFile = null;
			try {
				zipFile = new ZipFile(file);
				Enumeration<?> e = zipFile.entries();
				ZipEntry entry = null;

				while (e.hasMoreElements()) {

					entry = (ZipEntry) e.nextElement();

					if (!entry.isDirectory() && entry.getName().contains(path.toString()) && entry.getName().endsWith(".properties")) {
						loadFile(entry.getName().replace(path.toString(), ""));
						log.info(entry.getName());
					}
				}
			} catch (Exception e) {
				log.error("Error al tratar el archivo comprimido");
			}finally{
				if( null != zipFile ){
					zipFile.close();
				}
			}
			
			

		} else {
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".properties");
				}
			};

			file = new File(url.getFile());
			File[] fileArray = file.listFiles(filter);

			for (File f : fileArray) {
				try {
					loadFile(f.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				log.info(f.getName());
			}
		}

	}

	public String getAmbiente(){
		return AMBIENTE;
	}
}