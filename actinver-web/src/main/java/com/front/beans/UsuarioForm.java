package com.front.beans;

import javax.servlet.http.HttpServletRequest;

import com.front.validator.Errors;
import com.front.validator.MessageErrors;
import com.front.validator.Validator;

public class UsuarioForm implements Errors{
	
//	USUARIO
	private String accion;
	private Long idUsuario; 
	private String usuario;
	private String mail;
	private String pass;
	private Integer idEstatus;
	
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	
	public MessageErrors validate(HttpServletRequest request){
		
		MessageErrors error = new MessageErrors();
		if(accion.equals("editar") && !Validator.checkNumeric(idUsuario)){
			error.add("idUsuario", CAMPO_REQUERIDO);
			request.setAttribute("errorIdUsuario", error.getErrors().get("idUsuario"));
		}
		if(Validator.isEmptyData(usuario)){
			error.add("usuario", CAMPO_REQUERIDO);
			request.setAttribute("errorUsuario", error.getErrors().get("usuario"));
		}
		if(Validator.isEmptyData(mail)){
			error.add("mail", CAMPO_REQUERIDO);
			request.setAttribute("errorMail", error.getErrors().get("mail"));
		}else if(!Validator.checkEMail(mail)){
			error.add("mail", CORREO_NO_VALIDO);
			request.setAttribute("errorMail", error.getErrors().get("mail"));
		}
		if(accion.equals("nuevo") && Validator.isEmptyData(pass)){
			error.add("password", CAMPO_REQUERIDO);
			request.setAttribute("errorPassword", error.getErrors().get("password"));
		}
		if(accion.equals("editar") && !Validator.checkNumeric(idEstatus)){
			error.add("idEstatus", CAMPO_REQUERIDO);
			request.setAttribute("errorIdEstatus", error.getErrors().get("idEstatus"));
		}
		return error;
	}
	
	@Override
	public String toString() {
		return "UsuarioForm [idUsuario=" + idUsuario + ", usuario=" + usuario + ", mail=" + mail + ", pass=" + pass
				+ ", idEstatus=" + idEstatus + "]";
	}
}
