package com.front.controller;

import java.util.Date;
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
import com.front.beans.UsuarioForm;
import com.front.beans.UsuariosForm;
import com.front.util.Constantes;
import com.front.util.Formatter;
import com.front.validator.MessageErrors;
@Controller
@RequestMapping("/usuarios/*")
public class PrivateUsuariosController extends GeneralController {

	private static Logger log = Logger.getLogger(PrivateUsuariosController.class);
	
	@RequestMapping("/usuarios")
	public ModelAndView usuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("---------------- admin/usuarios -----------------");
		HttpSession session = request.getSession(false);

		ModelAndView sessionInvalid = new ModelAndView("sessionInvalid");
		try{
			boolean isValidSesion = validateSession(request);
			log.info("Respuesta de la session: " + isValidSesion);
			
			if(!isValidSesion){
				log.info("Session invalida");
				request.setAttribute("tipo","'invalida'");
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return sessionInvalid;
			}
		}catch(Exception e){
			log.info("Error en la session");
			request.setAttribute("tipo","'tiempo'");
			return sessionInvalid;
		}
		
		UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		List<UsuarioDTO> listUsuarioDTO = usuariosBusinessImp.consultaUsuarios_BS(usuarioDTO);
		
		for(UsuarioDTO elem:listUsuarioDTO){
			log.info("usuario::"+elem.getUsuario()+" permiso admin"+elem.isPermisoAdmin()+" permiso L"+elem.isPermisoL()+" permiso P"+elem.isPermisoP());
		}
		
		
		session.setAttribute("usuarios", listUsuarioDTO);
		ModelAndView view = new ModelAndView("usuarios", "usuarios", listUsuarioDTO);
		
		String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
		if(ultimo_acceso == null){
			ultimo_acceso ="...";
		}

		session.setAttribute("lastLogin", ultimo_acceso);
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return view;
	}
	
	@RequestMapping("/nuevo")
	public ModelAndView nuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("---------------- usuarios/nuevo -----------------");
		HttpSession session = request.getSession(false);

		ModelAndView view = new ModelAndView("nuevoUsuario");
		ModelAndView sessionInvalid = new ModelAndView("sessionInvalid");
		try{
			boolean isValidSesion = validateSession(request);
			log.info("Respuesta de la session: " + isValidSesion);
			
			if(!isValidSesion){
				log.info("Session invalida");
				request.setAttribute("tipo","'invalida'");
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return sessionInvalid;
			}
		}catch(Exception e){
			log.info("Error en la session");
			request.setAttribute("tipo","'tiempo'");
			return sessionInvalid;
		}
		
		UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		List<UsuarioDTO> listUsuarioDTO = usuariosBusinessImp.consultaUsuarios_BS(usuarioDTO);
		
		for(UsuarioDTO elem:listUsuarioDTO){
			log.info("usuario::"+elem.getUsuario()+" permiso admin"+elem.isPermisoAdmin()+" permiso L"+elem.isPermisoL()+" permiso P"+elem.isPermisoP());
		}
		
		
		session.setAttribute("usuarios", listUsuarioDTO);
		
		String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
		if(ultimo_acceso == null){
			ultimo_acceso ="...";
		}

		session.setAttribute("lastLogin", ultimo_acceso);
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return view;
	}
	@RequestMapping("/executeNuevo")
	public ModelAndView executeNuevo(HttpServletRequest request, HttpServletResponse response, UsuarioForm usuarioForm) throws Exception {
		log.info("---------------- admin/executeEditar -----------------");
		HttpSession session = request.getSession(false);

		usuarioForm.setAccion("nuevo");
		MessageErrors errors = usuarioForm.validate(request);
			
			if(!errors.getErrors().isEmpty()){
				log.info("Errores de validación : "+errors.getErrors().toString());
				return new ModelAndView("nuevoUsuario","usuarioForm", usuarioForm);
			}
			ModelAndView view = new ModelAndView("nuevoUsuario");
			
			List<UsuarioDTO> listUsuarioDTO = (List<UsuarioDTO>) session.getAttribute("usuarios");
			if(listUsuarioDTO != null && !listUsuarioDTO.isEmpty()){
				boolean isUsado = false;
				for(UsuarioDTO elem:listUsuarioDTO){
					if(elem.getUsuario().equals(usuarioForm.getUsuario())){
						request.setAttribute("usuarioForm", usuarioForm);
						request.setAttribute("errorGeneral","El usuario ya fue usado.");
						isUsado = Boolean.TRUE;
						break;
					}else if(elem.getMail().equals(usuarioForm.getMail())){
						request.setAttribute("usuarioForm", usuarioForm);
						request.setAttribute("errorGeneral","El Email ya fue usado.");
						isUsado = Boolean.TRUE;
						break;
					}
				}
				if(isUsado){
					String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
					if(ultimo_acceso == null){
						ultimo_acceso ="...";
					}

					session.setAttribute("lastLogin", ultimo_acceso);
					request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
					
					return view;
				}
				
			}
			
			UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
			
			UsuarioDTO usuarioDTO = new  UsuarioDTO();
			usuarioDTO.setUsuario(usuarioForm.getUsuario());
			usuarioDTO.setMail(usuarioForm.getMail());
			usuarioDTO.setPass(usuarioForm.getPass()!= null?usuarioForm.getPass().toCharArray():null);
			usuarioDTO.setIdEstatus(1);
			
			usuarioDTO = usuariosBusinessImp.altaUsuario_BS(usuarioDTO);
			
			if(usuarioDTO != null && usuarioDTO.getIdUsuario() != null){
				request.setAttribute("exitoUsuario","Se realizo el alta correctamente.");
				/*--------------------------- Consulta Usuario ---------------------------*/
				
				log.info("idUsuario::"+usuarioDTO.getIdUsuario());
				
				usuarioDTO = getUsuario(usuarioDTO.getIdUsuario());
				view = new ModelAndView("detalleUsuario");
				
				view = new ModelAndView("detalleUsuario","valorDTO", usuarioDTO);
			}else{
				request.setAttribute("usuarioForm", usuarioForm);
				request.setAttribute("errorGeneral","No se pudo dar de alta el usuario.");
			}			
			
			String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
			if(ultimo_acceso == null){
				ultimo_acceso ="...";
			}

			session.setAttribute("lastLogin", ultimo_acceso);
			request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
			
			return view;
		}
	
	@RequestMapping("/executeGuardar")
	public ModelAndView executeGuardar(HttpServletRequest request, HttpServletResponse response, UsuariosForm usuariosForm) throws Exception {
		log.info("---------------- admin/executeGuardar -----------------");
		
		log.info("Formulario::"+usuariosForm.toString());
		
		HttpSession session = request.getSession(false);

		ModelAndView sessionInvalid = new ModelAndView("sessionInvalid");
		try{
			boolean isValidSesion = validateSession(request);
			log.info("Respuesta de la session: " + isValidSesion);
			
			if(!isValidSesion){
				log.info("Session invalida");
				request.setAttribute("tipo","'invalida'");
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return sessionInvalid;
			}
		}catch(Exception e){
			log.info("Error en la session");
			request.setAttribute("tipo","'tiempo'");
			return sessionInvalid;
		}
		
		UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
		
		for(String elem:usuariosForm.getIdUsuario()){
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(new Long(elem));
			
			if(usuariosForm.getPermisoAdmin() != null && usuariosForm.getPermisoAdmin().length > 0){
				for(String elem2:usuariosForm.getPermisoAdmin()){
					if(elem.equals(elem2)){
						usuarioDTO.setPermisoAdmin(Boolean.TRUE);
					}
				}
			}
			if(usuariosForm.getPermisoL() != null && usuariosForm.getPermisoL().length > 0){
				for(String elem2:usuariosForm.getPermisoL()){
					if(elem.equals(elem2)){
						usuarioDTO.setPermisoL(Boolean.TRUE);
					}
				}
			}
			if(usuariosForm.getPermisoP() != null && usuariosForm.getPermisoP().length > 0){
				for(String elem2:usuariosForm.getPermisoP()){
					if(elem.equals(elem2)){
						usuarioDTO.setPermisoP(Boolean.TRUE);
					}
				}
			}
			log.info("Se actualizara el usuario ::"+usuarioDTO.getIdUsuario()
			+" PermisoAdmin::"+usuarioDTO.isPermisoAdmin()+" PermisoL::"+usuarioDTO.isPermisoL()+" PermisoP::"+usuarioDTO.isPermisoP());
			
		}
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		List<UsuarioDTO> listUsuarioDTO = usuariosBusinessImp.consultaUsuarios_BS(usuarioDTO);
		request.setAttribute("exitoUsuario", "Se guardaron los cambios correctamente.");
		for(UsuarioDTO elem:listUsuarioDTO){
			log.info("usuario::"+elem.getUsuario()+" permiso admin"+elem.isPermisoAdmin()+" permiso L"+elem.isPermisoL()+" permiso P"+elem.isPermisoP());
		}
		
		
		session.setAttribute("usuarios", listUsuarioDTO);
		ModelAndView view = new ModelAndView("usuarios", "usuarios", listUsuarioDTO);
		
		
		
		String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
		if(ultimo_acceso == null){
			ultimo_acceso ="...";
		}

		session.setAttribute("lastLogin", ultimo_acceso);
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return view;
	}
	
	@RequestMapping("/detalle")
	public ModelAndView detalle(HttpServletRequest request, HttpServletResponse response, String idUsuario) throws Exception {
		log.info("---------------- admin/detalle -----------------");
		HttpSession session = request.getSession(false);

		ModelAndView view = new ModelAndView("usuarios");
		
		ModelAndView sessionInvalid = new ModelAndView("sessionInvalid");
		try{
			boolean isValidSesion = validateSession(request);
			log.info("Respuesta de la session: " + isValidSesion);
			
			if(!isValidSesion){
				log.info("Session invalida");
				request.setAttribute("tipo","'invalida'");
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return sessionInvalid;
			}
		}catch(Exception e){
			log.info("Error en la session");
			request.setAttribute("tipo","'tiempo'");
			return sessionInvalid;
		}
		
		/*--------------------------- Consulta Usuario ---------------------------*/
		if(idUsuario == null || "".equals(idUsuario.trim()))
			idUsuario = request.getParameter("idUsuario"); 
		
		log.info("idUsuario::"+idUsuario);
		UsuarioDTO usuarioDTO = getUsuario(Long.parseLong(idUsuario));
		request.setAttribute("valorDTO", usuarioDTO);
		
		
	
		view = new ModelAndView("detalleUsuario");
		
		String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
		if(ultimo_acceso == null){
			ultimo_acceso ="...";
		}

		session.setAttribute("lastLogin", ultimo_acceso);
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return view;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("---------------- admin/aditar -----------------");
		HttpSession session = request.getSession(false);

		ModelAndView view = new ModelAndView("usuarios");
		
		ModelAndView sessionInvalid = new ModelAndView("sessionInvalid");
		try{
			boolean isValidSesion = validateSession(request);
			log.info("Respuesta de la session: " + isValidSesion);
			
			if(!isValidSesion){
				log.info("Session invalida");
				request.setAttribute("tipo","'invalida'");
				request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
				return sessionInvalid;
			}
		}catch(Exception e){
			log.info("Error en la session");
			request.setAttribute("tipo","'tiempo'");
			return sessionInvalid;
		}
		/*--------------------------- Consulta Usuario ---------------------------*/
		String idUsuario = request.getParameter("idUsuario"); 
		log.info("idUsuario::"+idUsuario);
		
		UsuarioDTO usuarioDTO = getUsuario(Long.parseLong(idUsuario));
		view = new ModelAndView("editaUsuario");
		
		request.setAttribute("valorDTO", usuarioDTO);
		String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
		if(ultimo_acceso == null){
			ultimo_acceso ="...";
		}

		session.setAttribute("lastLogin", ultimo_acceso);
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return view;
	}
	
	@RequestMapping("/executeEditar")
	public ModelAndView executeEditar(HttpServletRequest request, HttpServletResponse response, UsuarioForm usuarioForm) throws Exception {
		log.info("---------------- admin/executeEditar -----------------");
		HttpSession session = request.getSession(false);

		usuarioForm.setAccion("editar");
		MessageErrors errors = usuarioForm.validate(request);
		ModelAndView view = new ModelAndView("editaUsuario"); 
			if(!errors.getErrors().isEmpty()){
				log.info("Errores de validación : "+errors.getErrors().toString());
				request.setAttribute("valorDTO2", usuarioForm);
				return new ModelAndView("editaUsuario","valorDTO", usuarioForm);
			}
			/*------------------------------------------------------*/
			
			List<UsuarioDTO> listUsuarioDTO = (List<UsuarioDTO>) session.getAttribute("usuarios");
			
			if(listUsuarioDTO != null && !listUsuarioDTO.isEmpty()){
				boolean isUsado = false;
				SALIR: for(UsuarioDTO elem:listUsuarioDTO){
					log.info("usuarioForm.getIdUsuario()::"+usuarioForm.getIdUsuario()+" elem.getIdUsuario()::"+elem.getIdUsuario());
					if(usuarioForm.getIdUsuario() == elem.getIdUsuario()){
						if(!elem.getUsuario().equals(usuarioForm.getUsuario()) ){
							for(UsuarioDTO elem2:listUsuarioDTO){
								log.info("elem2.getIdUsuario()::"+elem2.getIdUsuario()+" usuarioForm.getIdUsuario()::"+usuarioForm.getIdUsuario());
								if(elem2.getIdUsuario() != usuarioForm.getIdUsuario()){
									if(elem2.getUsuario().equals(usuarioForm.getUsuario())){
										request.setAttribute("usuarioForm", usuarioForm);
										request.setAttribute("errorGeneral","El usuario ya fue usado.");
										isUsado = Boolean.TRUE;
										break SALIR;
									}else if(elem2.getMail().equals(usuarioForm.getMail())){
										request.setAttribute("usuarioForm", usuarioForm);
										request.setAttribute("errorGeneral","El Email ya fue usado.");
										isUsado = Boolean.TRUE;
										break SALIR;
									}
								}
							}
						}
					}
				}
				
				if(isUsado){
					request.setAttribute("valorDTO", usuarioForm);
					request.setAttribute("valorDTO2", usuarioForm);
					
					String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
					if(ultimo_acceso == null){
						ultimo_acceso ="...";
					}

					session.setAttribute("lastLogin", ultimo_acceso);
					request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
					
					return view;
				}else{
					/*--------------------------- Consulta Usuario ---------------------------*/
					String idUsuario = request.getParameter("idUsuario"); 
					log.info("idUsuario::"+idUsuario);
					
					UsuarioDTO usuarioDTO = getUsuario(Long.parseLong(idUsuario));
					
					request.setAttribute("valorDTO", usuarioDTO);
				}
			}
			
			UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
			
			UsuarioDTO usuarioDTO = new  UsuarioDTO();
			usuarioDTO.setIdUsuario(usuarioForm.getIdUsuario());
			usuarioDTO.setUsuario(usuarioForm.getUsuario());
			usuarioDTO.setMail(usuarioForm.getMail());
			usuarioDTO.setPass(usuarioForm.getPass()!= null?usuarioForm.getPass().toCharArray():null);
			usuarioDTO.setIdEstatus(usuarioForm.getIdEstatus());
			
			usuarioDTO = usuariosBusinessImp.actualizaUsuario_BS(usuarioDTO);
			
			String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
			if(ultimo_acceso == null){
				ultimo_acceso ="...";
			}
			
			/*------------------------------------------------------*/

			session.setAttribute("lastLogin", ultimo_acceso);
			request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
			
			return new ModelAndView("detalleUsuario","valorDTO", usuarioDTO);
		}
	
	private UsuarioDTO getUsuario(Long idUsuario){
		try{
			UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
			
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(idUsuario);
			List<UsuarioDTO> listUsuarioDTO = usuariosBusinessImp.consultaUsuarios_BS(usuarioDTO );
			
			if(listUsuarioDTO != null && !listUsuarioDTO.isEmpty()){
				for(UsuarioDTO elem:listUsuarioDTO){
					if(elem.getIdUsuario() == idUsuario){
						return  elem;
					}
				}
				
			}
		}catch (Exception e) {
			log.error("Ocurrio un error al obtener el usuario ::"+e.getMessage());
		}
		return null;
	}
	
}