package com.front.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.front.util.Constantes;
import com.front.validator.MessageErrors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wait/*")
public class GeneralController extends ControllerConstantes{

	private static Logger log = Logger.getLogger(GeneralController.class);
	protected ModelAndView errorGeneral = new ModelAndView("errorGeneral");
	protected ModelAndView errorMessage = new ModelAndView("errorMessage");
	protected ModelAndView errorMessageLimitado = new ModelAndView("errorMessageLimitado");
	protected ModelAndView sessionInvalid = new ModelAndView("sessionInvalid");
	
	public ModelAndView errorMessage( HttpServletRequest request, Collection<String> errores ){
		request.setAttribute("mensajesError", errores);
		return errorMessage;
	}
	
	public ModelAndView errorMessageLimitado( HttpServletRequest request, Collection<String> errores ){
		request.setAttribute("mensajesError", errores);
		return errorMessageLimitado;
	}
	
	public ModelAndView errorMessageLimitado( HttpServletRequest request, String errores ){
		Collection<String> collection = new ArrayList<String>();
		collection.add(errores);
		request.setAttribute("mensajesError", errores);
		return errorMessageLimitado;
	}
	@RequestMapping("/wait.htm")
	public synchronized ModelAndView wait(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Wait");
		return new ModelAndView("wait");
	}

	public boolean validateSession(HttpServletRequest request){
		return checkSession(request);
	}
	
	private boolean checkSession(HttpServletRequest request){
		//Se valida mediante consulta a la base de datos si existe una session activa.

		HttpSession session = request.getSession(false);
		log.info("session::"+session);
		if(session != null){
			 String exito = (String) request.getSession(false).getAttribute(Constantes.LOGIN_EXITOSO);
			 log.info("exito::"+exito);
			if(exito != null && exito.equals("True")){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//Funcion que recuperar en un String los errores del objecto Errors
	public String getErrorsAsString(MessageErrors error){
		StringBuffer errores = new StringBuffer();
		Set<Map.Entry<String,String>> listErrors = error.getErrors().entrySet();
		if (listErrors.size()>1) {errores.append("Han ocurrido los siguientes errores: ");}
		for (int i=0;i<listErrors.size();i++){
		Iterator iter = listErrors.iterator();
			while(iter.hasNext()){
			Map.Entry<String,String> entries = (Map.Entry<String,String>) iter.next();
			errores.append("* " + entries.getValue() );
		}
		}	
		return errores.toString();
	}
	@RequestMapping("/errorGeneral.htm")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("errorGeneral") ;
	}
}
