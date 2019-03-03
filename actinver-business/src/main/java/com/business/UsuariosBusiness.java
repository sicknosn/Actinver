package com.business;

import java.util.List;

import com.common.dto.UsuarioDTO;

public interface UsuariosBusiness {
	public UsuarioDTO altaUsuario_BS(UsuarioDTO usuarioDTO);
	public List<UsuarioDTO> consultaNombreUsuario_BS(UsuarioDTO usuarioDTO);
	public List<UsuarioDTO> consultaUsuarios_BS(UsuarioDTO usuarioDTO);
	public UsuarioDTO actualizaUsuario_BS(UsuarioDTO usuarioDTO);
	public boolean eliminaUsuario_BS(UsuarioDTO usuarioDTO);
}
