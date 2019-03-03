package com.front.beans;

public class AccesoTO {
	  private String alias;
	  private String nombre;
	  private StatusAccess estatus;
	  private String minutos;

	  public String getAlias()
	  {
	    return this.alias;
	  }
	  public void setAlias(String alias) {
	    this.alias = alias;
	  }
	  public String getNombre() {
	    return this.nombre;
	  }
	  public void setNombre(String nombre) {
	    this.nombre = nombre;
	  }
	  public StatusAccess getEstatus() {
	    return this.estatus;
	  }
	  public void setEstatus(StatusAccess estatus) {
	    this.estatus = estatus;
	  }
	  public String getMinutos() {
	    return this.minutos;
	  }
	  public void setMinutos(String minutos) {
	    this.minutos = minutos;
	  }

	  public static enum StatusAccess
	  {
	    BLOQUEADO, INCORRECTO, EXITOSO, SOLICITADO, INACTIVO, CADUCADO;
	  }

}
