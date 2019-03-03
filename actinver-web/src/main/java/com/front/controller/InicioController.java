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
public class InicioController{

	private static Logger log = Logger.getLogger(MenuInicioController.class);
	
	@RequestMapping("/inicio")
	public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("---------------- inicio -----------------");
		
		request.setAttribute(Constantes.ETIQUETA,Formatter.getFormatDateEtiqueta(new Date()));
		
		ModelAndView view = new ModelAndView("inicio");
		return view;
	}

}