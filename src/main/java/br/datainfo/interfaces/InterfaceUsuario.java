package br.datainfo.interfaces;

import br.datainfo.models.UsuarioModel;
import br.datainfo.utilitarios.MensagensSistema;
import br.datainfo.utilitarios.Paginacao;

public interface InterfaceUsuario {
	
	MensagensSistema insertUsuario(UsuarioModel usuario);
	MensagensSistema deletUsuario(Integer id);
	MensagensSistema updatSituacao(UsuarioModel usuario, Integer id);
	MensagensSistema updatUsuario(UsuarioModel user, Integer id);
	Paginacao listUsuarios(String nome, String situacao, Short perfil, Integer nmPagina, Integer limiteRows);
}
