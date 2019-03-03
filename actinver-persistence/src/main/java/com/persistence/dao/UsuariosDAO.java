package com.persistence.dao;


import java.util.List;

import com.persistence.entities.TblUsuario;

public interface UsuariosDAO {
	public TblUsuario altaUsuario_DS(TblUsuario miUsuarios);
	public List<TblUsuario> consultaUsuarios_DS(TblUsuario miUsuarios);
	public TblUsuario actualizaUsuario_DS(TblUsuario miUsuarios);
	public boolean eliminaUsuario_DS(TblUsuario miUsuarios);
}
