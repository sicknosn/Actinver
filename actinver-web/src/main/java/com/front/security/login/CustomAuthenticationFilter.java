package com.front.security.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.front.util.Constantes;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	
	 private static final String DEFAULT_FILTER_PROCESSES_URL = "/loginSecurity";
	 private static final String POST = "POST";
	
	private static Logger log = Logger.getLogger(CustomAuthenticationFilter.class);
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("---------------------inicio successfulAuthentication ------------------");
		
        String exito = (String) request.getSession(false).getAttribute(Constantes.LOGIN_EXITOSO);
        
        log.info("LOGIN_EXITOSO ="+exito);
        log.info("Se prepara para remover el atributo de session LOGIN_EXITOSO");
        
        request.getSession(false).removeAttribute(Constantes.LOGIN_EXITOSO);
        
        if(exito == null || !exito.equals("True")){
        	log.info("LOGIN no exitoso");
        	String basePath = null;
        	
        	if(request.getServerName()!=null && request.getServerName().contains(Constantes.SERVER_NAME)){
        		basePath = Constantes.PATH_PUBLIC;
        	}else{
        		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/public";
        	}
        	
        	log.info("basePath = "+basePath);
        	request.setAttribute("datosIncorrectos", "true");
        	response.sendRedirect(basePath+"/error.htm?tipo='revisarDatos'");
        	
        }else{
        	log.info("LOGIN exitoso");
        	request.getSession(false).setAttribute(Constantes.LOGIN_EXITOSO, "True");
        	Boolean sa = false;
        	if( null != request.getAttribute("sessionActual") ){
        		sa = (Boolean) request.getAttribute("sessionActual");
        	}
        	// Se valida si existe otra session activa
        	if( !sa ){
        		log.info("Es la unica session ");
        		super.successfulAuthentication(request, response, chain, authResult);
        	}else{
        		log.info("existe otra session activa");
            	String basePath = null;
            	if(request.getServerName()!=null && request.getServerName().contains(Constantes.SERVER_NAME)){
            		basePath = Constantes.PATH_PRIVATE;
            	}else{
            		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
            	}
            	log.info("basePath = "+basePath);
            	response.sendRedirect(basePath+"/error/OtraSession.htm");
        	}
        }
        log.info("---------------------fin successfulAuthentication ------------------");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.unsuccessfulAuthentication(request, response, failed);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		if(request.getMethod().equals(POST)) {
			request.setCharacterEncoding("UTF-8"); //Solucion a problema de envio de catacteres especials mediante Form POST
			super.doFilter(request, response, chain);    
		} else {
			chain.doFilter(request, response);    
		}  
	}
}
