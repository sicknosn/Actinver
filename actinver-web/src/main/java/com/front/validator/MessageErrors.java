package com.front.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.front.beans.MensajeTO;
import com.front.util.ManagerProperties;

public class MessageErrors {
	Map <String, String> errors;
	private static final Properties properties= ManagerProperties.getInstance().loadPropertie("messages-errors.properties");

	public MessageErrors(){
		errors = new HashMap<String, String>();
	}
	
	public void setErrors(Map<String, String> errors) {
		this.errors.putAll(errors);
	}
	public void add(String key, String value){
		System.out.println("value::"+value);
		System.out.println("properties::"+properties);
		String messageError = properties.getProperty(value);
		System.out.println("messageError::"+messageError);
		errors.put(key, messageError);

	}
	public void add(String key, String value, String param0){
		String messageError = properties.getProperty(value);
		if( null != messageError ){
			messageError = messageError.replace("{0}", param0);
		}else{
			messageError = "Por favor valida que los datos ingresados sean correctos";
		}
		errors.put(key, messageError);

	}
	public void add(String key, String value, String param0, String param1){
		String messageError = properties.getProperty(value);
		messageError = messageError.replace("{0}", param0);
		messageError = messageError.replace("{1}", param1);
		errors.put(key, messageError);

	}
	public void add(String key, String value, String param0, String param1, String param2){
		String messageError = properties.getProperty(value);
		messageError = messageError.replace("{0}", param0);
		messageError = messageError.replace("{1}", param1);
		messageError = messageError.replace("{2}", param2);
		errors.put(key, messageError);

	}

	public void add(String key, String value, String []params){
		String messageError = properties.getProperty(value);
		StringBuilder cadena = new StringBuilder();
		for(int i=0; i<params.length; i++){
			cadena.append(params[i]);
			if(i+1 != params.length){
				cadena.append(" o ");
			}
		}
		cadena.append(" d�gitos");
		messageError += cadena.toString();
		errors.put(key, messageError);
	}

	public Map<String, String> getErrors(){
		return this.errors;
	}

	public boolean isErrors(){
		if(errors!=null && errors.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public String getMessagesString(){
		StringBuilder mensajes = new StringBuilder();
		if (isErrors()){
			for (String key : this.errors.keySet()){
				String value = this.errors.get(key);
				MensajeTO error = new MensajeTO();
				error.setPropiedad(key);
				mensajes.append(value + ". ");
			}
		}
		return mensajes.toString();
	}
}
