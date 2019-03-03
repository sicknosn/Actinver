package com.common.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = -3624574428267431205L;
	
	private Long idUsuario; 
	private String usuario;
	private String mail;
	private char[] pass;
	private boolean permisoAdmin;
	private boolean permisoL;
	private boolean permisoP;
	
	private Integer idEstatus;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public char[] getPass() {
		return pass;
	}
	public void setPass(char[] pass) {
		this.pass = pass;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public boolean isPermisoAdmin() {
		return permisoAdmin;
	}
	public void setPermisoAdmin(boolean permisoAdmin) {
		this.permisoAdmin = permisoAdmin;
	}
	public boolean isPermisoL() {
		return permisoL;
	}
	public void setPermisoL(boolean permisoL) {
		this.permisoL = permisoL;
	}
	public boolean isPermisoP() {
		return permisoP;
	}
	public void setPermisoP(boolean permisoP) {
		this.permisoP = permisoP;
	}
}
