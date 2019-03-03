package com.front.util;

import java.io.IOException;

import org.apache.log4j.Logger;


public class Loader {

	private static Logger log = Logger.getLogger(Loader.class);

	public Loader() {
		try {
			log.info("##################### Inicio de carga de las propiedades de la aplicación #####################");
			ManagerProperties.getInstance().loadProperties();
			log.info("##################### Fin de carga de las propiedades de la aplicación #####################");
			//new UpdateCronSystem().run();
		} catch (IOException e) {
			log.info("No se pudo realozar la Carga de las Propiedades del proyecto");
		}
	}

}
