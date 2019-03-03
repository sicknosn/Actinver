package com.front.beans;

import java.io.Serializable;

public class MensajeTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2115343551360348030L;
	private String propiedad;
	private String descripcion;

	public String getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
