package com.front.security.login;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.front.util.Constantes;

public class LoginHelper implements AuthenticationSuccessHandler  {
	private static Logger log = Logger.getLogger(LoginHelper.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("---------------- inicio onAuthenticationSuccess------------------ ");
		  Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		  	for(String elem: roles){
		  		log.info("rol::"+elem);
		  	}
		  
	        if (roles.contains(Constantes.ROLE_ADMIN) || roles.contains(Constantes.ROLE_L) || roles.contains(Constantes.ROLE_P)) {
	        	log.info("request.getContextPath() = "+request.getContextPath());
	        	String basePath = null;
	        	if(request.getServerName()!=null && request.getServerName().contains(Constantes.SERVER_NAME)){
	        		basePath = Constantes.PATH_PRIVATE;
	        	}else{
	        		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	        	}
	        	
	        	log.info("basePath :: "+basePath);
	        	response.sendRedirect(basePath+"/login/login.htm");
	        }else{
	        	log.info("No cuenta con rol valido");
	        }
	}
}
