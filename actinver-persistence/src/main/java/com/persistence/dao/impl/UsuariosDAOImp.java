package com.persistence.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import com.persistence.dao.UsuariosDAO;
import com.persistence.entities.TblUsuario;
import com.persistence.hibernate.HibernateUtil;

public class UsuariosDAOImp implements UsuariosDAO {
   Logger log = Logger.getLogger(UsuariosDAOImp.class.getName());
	@Override
	public TblUsuario altaUsuario_DS(TblUsuario usuario) {
		if(usuario != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
				session.beginTransaction();
				session.persist(usuario);
				session.getTransaction().commit();
				
				return usuario;
			}catch (Exception e) {
				log.error("Ocurrio un error al dar de alta usuario ::"+e.getMessage());
			}finally{
				if(session!=null){
					session.close();
				}
			}
		}
		return null;
	}
	@Override
	public List<TblUsuario> consultaUsuarios_DS(TblUsuario usuario) {
		if(usuario != null){
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			List<TblUsuario> usuariosList = null; 
			
			StringBuilder query = new StringBuilder("FROM TblUsuario as u");
				query.append(" WHERE 1 = 1");
				
				if(usuario.getIdUsuario() != null)
					query.append(" AND u.idUsuario = ").append(usuario.getIdUsuario());
				if(usuario.getUsuario() != null && !"".equals(usuario.getUsuario()))
					query.append(" AND u.usuario = '").append(usuario.getUsuario()).append("'");
				if(usuario.getMail() != null && !"".equals(usuario.getMail()))
					query.append(" AND u.mail = '").append(usuario.getMail()).append("'");
				if(usuario.getPass() != null && !"".equals(usuario.getPass()))
					query.append(" AND u.pass = ").append(usuario.getPass());
				if(usuario.getIdEstatus() != null)	
					query.append(" AND u.idEstatus = ").append(usuario.getIdEstatus());
				
				log.info("query = "+query.toString());
			Query result = session.createQuery(query.toString());
			usuariosList =  result.list();
			session.close();
			
			return usuariosList;
		}else{
					
				}
		return null;
	}
	@Override
	public TblUsuario actualizaUsuario_DS(TblUsuario usuario) {
		if(usuario != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
				session.beginTransaction();
				session.merge(usuario);
				session.getTransaction().commit();
				
				return usuario;
			}catch (Exception e) {
				log.error("Ocurrio un error al actualizar usuario ::"+e.getMessage());
			}finally{
				if(session!=null){
					session.close();
				}
			}
		}
		return null;
	}
	@Override
	public boolean eliminaUsuario_DS(TblUsuario usuario) {
		log.info("----------------------- inicio eliminaUsuario_DS ------------------------");
		boolean flag = false;
		if(usuario != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
				session.beginTransaction();
				session.delete(usuario);
				session.getTransaction().commit();
				log.info("Se ejecuto delete correctamente");
				flag = Boolean.TRUE;
			}catch (Exception e) {
				log.error("Ocurrio un error al eliminar usuario ::"+e.getMessage());
			}finally{
				if(session!=null){
					session.close();
				}
			}
		}
		log.info("----------------------- fin eliminaUsuario_DS ------------------------");
		return flag;
	}
}
