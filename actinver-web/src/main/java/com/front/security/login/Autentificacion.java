package com.front.security.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.business.impl.UsuariosBusinessImp;
import com.common.dto.UsuarioDTO;
import com.front.beans.LoginTO;
import com.front.util.Constantes;

@SuppressWarnings("deprecation")
public class Autentificacion implements AuthenticationProvider{

	Logger log=Logger.getLogger(Autentificacion.class);

    public static String username;
    public static LoginTO loginResponseTO=new LoginTO();

	public Authentication authenticate(Authentication authentication) {
		log.info("--------- inicio authenticate --------------------");
		Authentication custom = null;
		UserDetails user=null;
		
        username=(authentication.getPrincipal()==null) ? "NONE-PROVIDED": authentication.getName();
        
        char[] psw = authentication.getCredentials().toString().toCharArray(); 
        
        List<UsuarioDTO> UsuarioDTOList = null;
        
        UsuariosBusinessImp usuariosBusinessImp = new UsuariosBusinessImp();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuario(username);
		UsuarioDTOList= usuariosBusinessImp.validaUsuario_BS(usuarioDTO);
        
		if(!UsuarioDTOList.isEmpty()){
			if(UsuarioDTOList.size()==1){
				try {        	
		        	log.info("Mandar login sin cerrar session anterior, en caso de tenerla");
		        	try {
		        		if(UsuarioDTOList.get(0).getUsuario().equals(username) && String.valueOf(UsuarioDTOList.get(0).getPass()).equals(String.valueOf(psw))){
		        			log.info("el usuario es correcto");
		        			loginResponseTO.setAuthenticated(Boolean.TRUE);
		        			loginResponseTO.setLastAccessDate("24/10/2016");
		            		
		            		log.info("Seteando los roles  el usuario");
		            		
		            		if(UsuarioDTOList.get(0) != null){
		            			
			                	Vector<GrantedAuthority> authorities = new Vector<GrantedAuthority>();
			                	
			                	if(UsuarioDTOList.get(0).isPermisoAdmin() == false && UsuarioDTOList.get(0).isPermisoL() == false && UsuarioDTOList.get(0).isPermisoP() == false){
									log.error("Se manda la excepcion de error de autenticacion, el usuario no cuenta con roles validos");
				                	throw new AuthenticationServiceException("Credenciales Invalidas");
								}
			                	if(UsuarioDTOList.get(0).isPermisoAdmin()) {
			                		log.info(" ROLL::"+Constantes.ROLE_ADMIN);
									authorities.addElement( new GrantedAuthorityImpl( Constantes.ROLE_ADMIN) );
			                	}
			                	if(UsuarioDTOList.get(0).isPermisoL()) {
			                		log.info(" ROLL::"+Constantes.ROLE_L);
									authorities.addElement( new GrantedAuthorityImpl( Constantes.ROLE_L) );
			                	}
			                	if(UsuarioDTOList.get(0).isPermisoP()) {
			                		log.info(" ROLL::"+Constantes.ROLE_P);
									authorities.addElement( new GrantedAuthorityImpl( Constantes.ROLE_P) );
			                	}
			                		
			                	user = new User( username, authentication.getCredentials().toString(), true, true, true, true, authorities );
			                	
			                	List<GrantedAuthority> listaA=new ArrayList<GrantedAuthority>();
			                    listaA.addAll(user.getAuthorities());
			                    
			                    custom = new JaasAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),listaA,null);	
		            		}else{
		                		 log.error("Se manda la excepcion de error de autenticacion, el usuario no cuenta con roles");
		                		throw new AuthenticationServiceException("Credenciales Invalidas");
		                	}
		            		
		                    log.info("El usuario tiene accesso");
		        		}else{
		        			log.info("el usuario tiene acceso");
		        			loginResponseTO.setAuthenticated(false);
		        		}
		        		log.info("fin authenticate");
		            } catch (Exception e) {
		                log.info("Ocurrio un error "+e.getStackTrace().toString());
		            }
		        	
		            log.info("Acceso: "+loginResponseTO.isAuthenticated());
		            
	        	}catch(UsernameNotFoundException notFound) {
	               	 log.error("Autentificacion.authenticate()");
	                   throw new AuthenticationServiceException( "Bad credentials" );
	               }
			}else{
				log.info("el usuario tiene mas de un registro");
    			loginResponseTO.setAuthenticated(false);
			}
			
		}
        return custom;
    }

    public boolean supports(Class<? extends Object> arg0) {
        // TODO Auto-generated method stub
        return true;
    }

}
