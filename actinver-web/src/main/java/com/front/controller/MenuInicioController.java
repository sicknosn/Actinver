package com.front.controller;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.front.util.Constantes;
import com.front.util.Formatter;
@Controller
@RequestMapping("/menu/*")
public class MenuInicioController extends GeneralController {

	private static Logger log = Logger.getLogger(MenuInicioController.class);
	
	@RequestMapping("/existSession")
	public ModelAndView existSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		String responsestr = "error";
		try{
			boolean isValidSesion = validateSession(request);
			if(!isValidSesion){
				log.info("Session invalida");
				responsestr = "invalida";
			}else{
				responsestr = "success";
			}
		}catch(Exception e){
			responsestr = "error";
		}
		
		return null;
	}
	@RequestMapping("/inicio")
	public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("---------------- menu/inicio -----------------");
		HttpSession session = request.getSession(false);

		ModelAndView view = new ModelAndView("menu");
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
			
			request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
			return sessionInvalid;
		}
		
		String ultimo_acceso=(String)session.getAttribute(ULTIMO_ACCESO);
		if(ultimo_acceso == null){
			ultimo_acceso ="...";
		}
		
		request.getSession(false).setAttribute(Constantes.SESSION_VALIDA, Constantes.OK);
		session.setAttribute("lastLogin", ultimo_acceso);
		
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return view;
	}

}