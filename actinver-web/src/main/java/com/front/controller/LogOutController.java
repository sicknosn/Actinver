package com.front.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.front.util.Constantes;
import com.front.util.Formatter;

@Controller
@RequestMapping("/logout/*")
public class LogOutController extends GeneralController {
	private static Logger log = Logger.getLogger(LogOutController.class);
	
	@RequestMapping("/cerrarsesiones")
	public ModelAndView cerrarsesiones(HttpServletRequest request, HttpServletResponse response){
		log.info("------------------- logout/cerrarsesiones --------------------");
		try{
			HttpSession session = request.getSession(false);
			
			log.info("request.getSession()::"+request.getSession());
			log.info("Cerrando session cliente: " + session.getAttribute(ID_USUARIO));
			
			if(session!=null)
			session.invalidate();
				
		}catch (Exception e) {
			log.error("Ocurrio un error en cerrarsesiones :"+e.getMessage());
		}
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return new ModelAndView("login");
	}
	
}
