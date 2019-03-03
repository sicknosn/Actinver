package com.front.beans;

import java.util.Arrays;

public class UsuariosForm {
	
	private String[] idUsuario; 
	private String[] permisoAdmin;
	private String[] permisoL;
	private String[] permisoP;
	
	public String[] getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String[] idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String[] getPermisoAdmin() {
		return permisoAdmin;
	}
	public void setPermisoAdmin(String[] permisoAdmin) {
		this.permisoAdmin = permisoAdmin;
	}
	public String[] getPermisoL() {
		return permisoL;
	}
	public void setPermisoL(String[] permisoL) {
		this.permisoL = permisoL;
	}
	public String[] getPermisoP() {
		return permisoP;
	}
	public void setPermisoP(String[] permisoP) {
		this.permisoP = permisoP;
	}
	@Override
	public String toString() {
		return "UsuariosForm [idUsuario=" + Arrays.toString(idUsuario) + ", permisoAdmin="
				+ Arrays.toString(permisoAdmin) + ", permisoL=" + Arrays.toString(permisoL) + ", permisoP="
				+ Arrays.toString(permisoP) + "]";
	}
	
	
	
}
