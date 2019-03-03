package com.front.beans;

import com.front.validator.Errors;
import com.front.validator.MessageErrors;
import com.front.validator.Validator;


public class LoginForm implements Errors {

	private String usuario;
	private String password;
	private String nombreUsuario;
	private String codigo;
	private String errorNombre;
	private String mensajeMinutos;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getErrorNombre() {
		return errorNombre;
	}
	public void setErrorNombre(String errorNombre) {
		this.errorNombre = errorNombre;
	}
	public String getMensajeMinutos() {
		return mensajeMinutos;
	}
	public void setMensajeMinutos(String mensajeMinutos) {
		this.mensajeMinutos = mensajeMinutos;
	}
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		if(Validator.isEmptyData(usuario)){
			error.add("usuario", LOGIN_USUARIO_VACIO);
		}
		if(Validator.isEmptyData(password)){
			error.add("password", LOGIN_PSW_VACIO);
		}
		return error;
	}
}
