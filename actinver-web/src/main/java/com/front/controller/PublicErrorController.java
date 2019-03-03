package com.front.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.front.util.Constantes;
import com.front.util.Formatter;

@Controller
@RequestMapping("/error/*")
public class PublicErrorController extends GeneralController {
	private static Logger log = Logger.getLogger(PublicErrorController.class);
	
	@RequestMapping("/errorLogin")
	public ModelAndView errorLogin(HttpServletRequest request, HttpServletResponse response){
		log.info("------------------- error/errorLogin --------------------");
	
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return new ModelAndView("errorLogin");
	}
	
	@RequestMapping("/general-error")
	public ModelAndView generalError(HttpServletRequest request, HttpServletResponse response){
		log.info("------------------- error/errorGeneral --------------------");
	
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return new ModelAndView("errorGeneral");
	}
	@RequestMapping("/Error404")
	public ModelAndView Error404(HttpServletRequest request, HttpServletResponse response){
		log.info("------------------- error/Error404 --------------------");
	
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));	
		return new ModelAndView("Error404");
	}
}
