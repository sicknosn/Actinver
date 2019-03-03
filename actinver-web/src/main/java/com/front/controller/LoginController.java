package com.front.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.business.impl.UsuariosBusinessImp;
import com.common.dto.UsuarioDTO;
import com.front.beans.AccesoTO;
import com.front.beans.AuthenticateTO;
import com.front.beans.LoginForm;
import com.front.beans.LoginTO;
import com.front.security.login.Autentificacion;
import com.front.util.Constantes;
import com.front.util.Formatter;
@Controller
@RequestMapping("/login/*")
public class LoginController extends MenuInicioController {
	private static Logger log = Logger.getLogger(LoginController.class);

	/**
	 * Metodo muestra el la vista de login con los campos vacios
	 */
	@RequestMapping("/relogin")
	public ModelAndView relogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Muestra Login");
		
		HttpSession session = request.getSession();
		Enumeration enu = session.getAttributeNames(); 
					while(enu.hasMoreElements()){
						try{
							session.removeAttribute(enu.nextElement().toString());
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		System.out.println("-------------------- Retorna ModelAndView login ------------------------ ");
		return new ModelAndView("login");
	}

	/**
	 * Metodo que recibe usuario
	 * Se valida si existe usuario existe y tiene persmisos.
	 * Si es exitoso regresa el nombre del usuario y se pide el password.
	 */
	@RequestMapping("/getNombre")
	public ModelAndView getNombre(HttpServletRequest request, HttpServletResponse response, LoginForm loginForm) throws Exception {
		log.info(" ----------------------- getNombre --------------------------");
		loginForm.setErrorNombre(null);
		loginForm.setNombreUsuario(null);
		
		ModelAndView exito = new ModelAndView("login", "loginForm", loginForm);
		AuthenticateTO responseAccess = new AuthenticateTO();
		
		List<UsuarioDTO> UsuarioDTOList = null;
		if(loginForm.getUsuario()!=null && !loginForm.getUsuario().isEmpty()){
			
			UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUsuario(loginForm.getUsuario());
			UsuarioDTOList= usuariosBusinessImp.consultaNombreUsuario_BS(usuarioDTO);
			
			if(UsuarioDTOList == null || UsuarioDTOList.isEmpty()){
				responseAccess.setStatus(AccesoTO.StatusAccess.INCORRECTO);
			}else if(UsuarioDTOList.size()>1){
				loginForm.setErrorNombre("Existe mas de un usuario con estas credenciales");
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return exito;
			}else if(!loginForm.getUsuario().equals(UsuarioDTOList.get(0).getUsuario())){
				responseAccess.setStatus(AccesoTO.StatusAccess.INCORRECTO);
			}else if(loginForm.getUsuario().equals(UsuarioDTOList.get(0).getUsuario()) 
					&& UsuarioDTOList.get(0).getIdEstatus() == com.common.util.Constantes.ESTATUS_INACTIVO){
				responseAccess.setStatus(AccesoTO.StatusAccess.INACTIVO);
			}else if(loginForm.getUsuario().equals(UsuarioDTOList.get(0).getUsuario())){
				responseAccess.setStatus(AccesoTO.StatusAccess.EXITOSO);
				loginForm.setNombreUsuario("Invitado");
			}else if(loginForm.getUsuario().equals(UsuarioDTOList.get(0).getMail())){
				responseAccess.setStatus(AccesoTO.StatusAccess.EXITOSO);
				loginForm.setNombreUsuario("Invitado");
			}
		}else{
			loginForm.setErrorNombre("Ingrese el usuario");
		}
		
		if(responseAccess != null) {
			String error = "";
			log.info("responseAccess.getStatus()="+responseAccess.getStatus());
				switch (responseAccess.getStatus()) {
				case CADUCADO:
					log.info("Usuario CADUCADO");
					error = "Ingrese Codigo nuevamente";
				case INACTIVO:
					log.info("Usuario INACTIVO");
					loginForm.setUsuario(null);
					error = "Usuario INACTIVO";
					break;
				case BLOQUEADO:
					log.info("usuario BLOQUEADO");
					loginForm.setUsuario(null);
					error = "Usuario BLOQUEADO";
					loginForm.setMensajeMinutos("Intente en "+ responseAccess.getMinutes() + " minuto(s)");
					break;
				case INCORRECTO:
						log.info("usuario INCORRECTO");
						error = "Usuario Incorrecto";
						break;
				case EXITOSO:
					log.info("usuario EXITOSO");
					log.info(USUARIOID+"="+UsuarioDTOList.get(0).getIdUsuario());
					request.getSession(false).removeAttribute("userName");
					request.getSession(false).setAttribute("nombreUsuario", loginForm.getNombreUsuario());
					request.getSession(false).setAttribute(Constantes.LOGIN_EXITOSO, "True");
					request.getSession(false).setAttribute(USUARIOID, UsuarioDTOList.get(0).getIdUsuario());
					request.getSession(false).setAttribute(CUSTOMER_USER, "Hola");
					
					/****** implementacion para el Autentificacion******/
//					RequestContextHolder.getRequestAttributes().setAttribute("relogin","aqui va la url", RequestAttributes.SCOPE_SESSION);
					
					break;
				default:
						error = "Usuario Incorrecto";
					break;
				}
			loginForm.setErrorNombre(error);
		}
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		log.info("-------------------------- fin getNombre ---------------------------------- ");
		return exito ;
	}
	
	/**
	 * Metodo que recibe usuario y password
	 * Se valida que sean las credenciales validas
	 * Si es exitoso regresa la vista al menu para realizar operaciones.
	 */
	@RequestMapping("/login")
	public synchronized ModelAndView login(HttpServletRequest request, HttpServletResponse response, LoginForm loginForm) throws Exception {
		log.info(" -------------------- Entro a login --------------------- ");		
		try{
			HttpSession session = request.getSession(false);
			
			 boolean authenticate = getLoginAuthenticate(request, Autentificacion.loginResponseTO);
			 log.info("authenticate = "+authenticate);
			if (authenticate) {

					String nivelSeguridad = "1";
					session.setAttribute(SECURITY_LEVEL, nivelSeguridad);
					
					String basePath = null;
		        	if(request.getServerName()!=null && request.getServerName().contains(Constantes.SERVER_NAME)){
		        		basePath=Constantes.PATH_PRIVATE;
		        	}else{
		        		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		        	}
		        	
		        	log.info("basePath = "+basePath);
		        	
		        	response.sendRedirect(basePath+"/menu/inicio.htm");
		        	
				return null;
			} else {
				String nombreUsuario = (String) request.getSession(false).getAttribute("nombreUsuario");
				loginForm.setNombreUsuario(nombreUsuario);
				loginForm.setErrorNombre("password incorrecto");
				loginForm.setUsuario(Autentificacion.username);
				log.info("Se setea atributo de session LOGIN_EXITOSO a true ");
				
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return new ModelAndView("login", "loginForm", loginForm);
//				return new ModelAndView("error", "loginForm", loginForm);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return errorGeneral;
		}
	}

	private synchronized boolean getLoginAuthenticate(HttpServletRequest request, LoginTO loginTO){
		log.info(" ---------------------- inicio getLoginAuthenticate ------------------------");
		HttpSession session = request.getSession(true);
		boolean authenticate = false;
		try {
			if (loginTO != null && loginTO.isAuthenticated()) {
				authenticate = loginTO.isAuthenticated();
				session.setAttribute(ID_SESSION, loginTO.getTrackingNumber());
				session.setAttribute(ID_USUARIO, loginTO.getIdAlnova());
				session.setAttribute(NOMBRE_USUARIO, Formatter.formatName(loginTO.getCustomerName()));
				session.setAttribute(APPLICATION_NAME, Constantes.SYSTEM_NAME);
				session.setAttribute(CUSTOMER_USER, Autentificacion.username);
				session.setAttribute(ULTIMO_ACCESO, loginTO.getLastAccessDate());

				if(authenticate){
					log.info("Usuario Activo");
					session.setAttribute(ControllerConstantes.ID_USUARIO, loginTO.getIdAlnova());
				}
				else {
					log.info("Usuario y/o contraseña incorrectos");
				}
			} else {
				log.info("Estimado usuario por el momento no podemos atender su peticion, por favor intente más tarde");
			}
		} catch (Exception ex) {
			log.info("Usuario y/o contraseña incorrectos");
		}
		log.info(" ---------------------- fin getLoginAuthenticate ------------------------");
		return authenticate;
   }
}
