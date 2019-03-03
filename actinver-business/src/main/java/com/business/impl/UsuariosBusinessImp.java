package com.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.business.UsuariosBusiness;
import com.common.dto.UsuarioDTO;
import com.persistence.dao.UsuariosDAO;
import com.persistence.dao.impl.UsuariosDAOImp;
import com.persistence.entities.TblUsuario;

public class UsuariosBusinessImp implements UsuariosBusiness {
	static Logger log = Logger.getLogger( UsuariosBusinessImp.class.getName() );
	
	@Override
	public UsuarioDTO altaUsuario_BS(UsuarioDTO usuarioDTO) {
		if(usuarioDTO!=null){
			UsuariosDAO usuariosDAO = new UsuariosDAOImp();
			TblUsuario usuario = new TblUsuario();
			
			usuario.setIdEstatus(1);
			usuario.setMail(usuarioDTO.getMail());
			usuario.setPass(String.valueOf(usuarioDTO.getPass()));
			usuario.setUsuario(usuarioDTO.getUsuario());
			
			TblUsuario tblUsuario = usuariosDAO.altaUsuario_DS(usuario);
			if(tblUsuario != null){
				usuarioDTO.setIdUsuario(tblUsuario.getIdUsuario());
				return usuarioDTO;
			}
			
		}else{
			log.info("EL usuarioDTO es null");
		}
		return null;
	}
	
	public List<UsuarioDTO> consultaNombreUsuario_BS(UsuarioDTO usuarioDTO) {
		
		log.info("------------ consultaNombreUsuario_BS -------------------");
		if(usuarioDTO!=null){
			UsuariosDAO usuariosDAO = new UsuariosDAOImp();
			List<TblUsuario> usuariosList;
			TblUsuario miUsuarios;
			
			miUsuarios = new TblUsuario();
			miUsuarios.setUsuario(usuarioDTO.getUsuario());
			log.info(" Se hace la consulta por el usuario : "+usuarioDTO.getUsuario());
			usuariosList = usuariosDAO.consultaUsuarios_DS(miUsuarios );
			if(!usuariosList.isEmpty()){

				log.info("Existe usuario");
				List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
				for(TblUsuario elem:usuariosList){
					usuarioDTO = new UsuarioDTO();
					usuarioDTO.setIdUsuario(elem.getIdUsuario());
					usuarioDTO.setUsuario(elem.getUsuario());
					usuarioDTO.setMail(elem.getMail());
					usuarioDTO.setPass(elem.getPass()!= null?elem.getPass().toCharArray():null);
					usuarioDTO.setIdEstatus(elem.getIdEstatus());
					usuarioDTOList.add(usuarioDTO);
				}
				return usuarioDTOList;
			
			}else{
				miUsuarios = new TblUsuario();
				miUsuarios.setMail(usuarioDTO.getUsuario());
				log.info("Se hace la consulta por el mail : "+usuarioDTO.getUsuario());
				usuariosList = usuariosDAO.consultaUsuarios_DS(miUsuarios );
				if(!usuariosList.isEmpty()){
					log.info("Existe usuario");
					List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
					for(TblUsuario elem:usuariosList){
						usuarioDTO = new UsuarioDTO();
						usuarioDTO.setIdUsuario(elem.getIdUsuario());
						usuarioDTO.setUsuario(elem.getUsuario());
						usuarioDTO.setMail(elem.getMail());
						usuarioDTO.setPass(elem.getPass()!= null?elem.getPass().toCharArray():null);
						usuarioDTO.setIdEstatus(elem.getIdEstatus());
						
						usuarioDTOList.add(usuarioDTO);
					}
					return usuarioDTOList;
				}
				
			}
		}else{
			log.info("EL usuarioDTO es null");
		}
		log.info(" No se encontro usuario");
		return null;
	}
public List<UsuarioDTO> validaUsuario_BS(UsuarioDTO usuarioDTO) {
		
		log.info("------------ validaUsuario_BS -------------------");
		if(usuarioDTO!=null){
			UsuariosDAO usuariosDAO = new UsuariosDAOImp();
			List<TblUsuario> usuariosList;
			List<UsuarioDTO> listUsuarioDTO = null;
			TblUsuario miUsuarios;
			
			miUsuarios = new TblUsuario();
			miUsuarios.setUsuario(usuarioDTO.getUsuario());
			miUsuarios.setPass(usuarioDTO.getPass()!= null?String.valueOf(usuarioDTO.getPass()):null);
			log.info(" Se hace la consulta por el usuario : "+usuarioDTO.getUsuario());
			usuariosList = usuariosDAO.consultaUsuarios_DS(miUsuarios);
			if(!usuariosList.isEmpty()){
				listUsuarioDTO = new ArrayList<>();
				usuarioDTO.setIdUsuario(usuariosList.get(0).getIdUsuario());
				usuarioDTO.setIdEstatus(usuariosList.get(0).getIdEstatus());
				usuarioDTO.setMail(usuariosList.get(0).getMail());
				usuarioDTO.setUsuario(usuariosList.get(0).getUsuario());
				usuarioDTO.setPass(usuariosList.get(0).getPass()!= null?usuariosList.get(0).getPass().toCharArray():null);
				usuarioDTO.setPermisoAdmin(true);
				usuarioDTO.setPermisoL(true);
				usuarioDTO.setPermisoP(true);
				listUsuarioDTO.add(usuarioDTO);
				return listUsuarioDTO;
			}else{
				miUsuarios = new TblUsuario();
				miUsuarios.setMail(usuarioDTO.getUsuario());
				miUsuarios.setPass(usuarioDTO.getPass()!= null?String.valueOf(usuarioDTO.getPass()):null);
				log.info("Se hace la consulta por el mail : "+usuarioDTO.getMail());
				usuariosList = usuariosDAO.consultaUsuarios_DS(miUsuarios );
				if(!usuariosList.isEmpty()){
					listUsuarioDTO = new ArrayList<>();
					usuarioDTO.setIdUsuario(usuariosList.get(0).getIdUsuario());
					usuarioDTO.setIdEstatus(usuariosList.get(0).getIdEstatus());
					usuarioDTO.setMail(usuariosList.get(0).getMail());
					usuarioDTO.setUsuario(usuariosList.get(0).getUsuario());
					usuarioDTO.setPass(usuariosList.get(0).getPass()!= null?usuariosList.get(0).getPass().toCharArray():null);
					usuarioDTO.setPermisoAdmin(true);
					usuarioDTO.setPermisoL(true);
					usuarioDTO.setPermisoP(true);
					listUsuarioDTO.add(usuarioDTO);
					return listUsuarioDTO;
				}
			}
		}else{
			log.info("EL usuarioDTO es null");
		}
		log.info(" No se encontro usuario");
		return null;
	}
	@Override
	public List<UsuarioDTO> consultaUsuarios_BS(UsuarioDTO usuarioDTO) {
		log.info("consultaUsuarios_BS");
		if(usuarioDTO!=null){
			UsuariosDAO usuariosDAO = new UsuariosDAOImp();
			
			TblUsuario miUsuarios = new TblUsuario();
			miUsuarios.setIdUsuario(usuarioDTO.getIdUsuario());
			miUsuarios.setUsuario(usuarioDTO.getUsuario());
			miUsuarios.setMail(usuarioDTO.getMail());
			miUsuarios.setPass(usuarioDTO.getPass()!= null?String.valueOf(usuarioDTO.getPass()):null);
			miUsuarios.setIdEstatus(usuarioDTO.getIdEstatus());
			
			List<TblUsuario> usuariosList = usuariosDAO.consultaUsuarios_DS(miUsuarios );
			if(usuariosList != null && !usuariosList.isEmpty()){
				 List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
					for(TblUsuario elem:usuariosList){
						 	usuarioDTO = new UsuarioDTO();
						 	usuarioDTO.setIdEstatus(elem.getIdEstatus());
						 	usuarioDTO.setIdUsuario(elem.getIdUsuario());
						 	usuarioDTO.setMail(elem.getMail());
						 	usuarioDTO.setPass(elem.getPass()!= null?elem.getPass().toCharArray():null);
						 	usuarioDTO.setPermisoAdmin(true);
						 	usuarioDTO.setPermisoL(true);
						 	usuarioDTO.setPermisoP(true);
						 	usuarioDTO.setUsuario(elem.getUsuario());
						 
						 listUsuarioDTO.add(usuarioDTO);
					}
				return listUsuarioDTO;
			}
		}else{
			log.info("EL usuarioDTO es null");
		}
		return null;
	}
	@Override
	public UsuarioDTO actualizaUsuario_BS(UsuarioDTO usuarioDTO) {
		log.info("actualizaUsuario_BS");
		if(usuarioDTO!=null){
			UsuariosDAO usuariosDAO = new UsuariosDAOImp();
			
			TblUsuario miUsuarios = new TblUsuario();
			miUsuarios.setIdUsuario(usuarioDTO.getIdUsuario());
			miUsuarios.setUsuario(usuarioDTO.getUsuario());
			miUsuarios.setMail(usuarioDTO.getMail());
			miUsuarios.setPass(usuarioDTO.getPass()!= null?String.valueOf(usuarioDTO.getPass()):null);
			miUsuarios.setIdEstatus(usuarioDTO.getIdEstatus());
			
			TblUsuario miUsuariosBusqueda = new TblUsuario();
			miUsuariosBusqueda.setIdUsuario(usuarioDTO.getIdUsuario());
			
			List<TblUsuario>  tblUsuarioLits= usuariosDAO.consultaUsuarios_DS(miUsuariosBusqueda );
			
			if(tblUsuarioLits != null && !tblUsuarioLits.isEmpty()){
				TblUsuario usuario = usuariosDAO.actualizaUsuario_DS(miUsuarios);
				
				if(usuario != null){
					usuarioDTO = new UsuarioDTO();
					usuarioDTO.setIdUsuario(usuario.getIdUsuario());
					usuarioDTO.setUsuario(usuario.getUsuario());
					usuarioDTO.setMail(usuario.getMail());
					usuarioDTO.setPass(usuario.getPass()!= null?usuario.getPass().toCharArray():null);
					usuarioDTO.setIdEstatus(usuario.getIdEstatus());
					
					return usuarioDTO;
				}
			}else{
				log.info("No existe usuario para actualizar");
			}
		}else{
			log.info("EL usuarioDTO es null");
		}
		return null;
	}
	
	@Override
	public boolean eliminaUsuario_BS(UsuarioDTO usuarioDTO) {
		log.info("eliminaUsuario_BS");
		try{
			if(usuarioDTO!=null){
				
				List<UsuarioDTO> listUsuarioDTO = consultaUsuarios_BS(usuarioDTO);
				
				if(listUsuarioDTO != null && !listUsuarioDTO.isEmpty()){
					
					for(UsuarioDTO elem:listUsuarioDTO){
						UsuariosDAO usuariosDAO = new UsuariosDAOImp();
						
						TblUsuario miUsuarios = new TblUsuario();
							miUsuarios.setIdUsuario(elem.getIdUsuario());
						
							usuariosDAO.actualizaUsuario_DS(miUsuarios);
							return Boolean.TRUE;
					}
				}else{
					log.info("No se encontro usuario con el Id::"+usuarioDTO.getIdUsuario());
				}
			}else{
				log.info("EL usuarioDTO es null");
			}
		}catch (Exception e) {
			log.info("Ocurrio un error al ejecutar eliminar usuario ::"+e.getMessage());
		}
		return Boolean.FALSE;
	}

}
