package br.datainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.datainfo.models.UsuarioModel;
import br.datainfo.config.Conecta;
import br.datainfo.interfaces.InterfaceUsuario;
import br.datainfo.utilitarios.HibernateUtil;
import br.datainfo.utilitarios.MensagensSistema;
import br.datainfo.utilitarios.Paginacao;
import br.datainfo.utilitarios.MensagensEnum;

@SuppressWarnings("rawtypes")
public class UsuarioDao implements InterfaceUsuario {

	private static Long MN001 = 1L, MN005 = 5L, MN030 = 30L, MN034 = 34L, MN033 = 33L, MN032 = 32L, MN098 = 98L, MN099 = 99L;
	private static String ATIVO = "A";
	private static String INATIVO = "I";
	private static String SQL_INSERT_USER = "INSERT INTO public.usuario_externo (nu_cpf, no_usuario, de_email, ic_situacao, ic_perfil_acesso, co_funcao , nu_telefone) VALUES(?,?,?,?,?,?,?)";
	private static String SQL_UPDATE_SITUACAO = "UPDATE public.usuario_externo SET ic_situacao = ? WHERE id = ?";
	private static String SQL_DELETE_USUARIO = "DELETE FROM public.usuario_externo WHERE id = ?";
	private static String SQL_UPDATE_USUARIO = "UPDATE public.usuario_externo SET nu_cpf = ?, no_usuario = ?, de_email = ?, ic_situacao = ?, ic_perfil_acesso = ?, co_funcao = ?, nu_telefone = ? WHERE id = ?";
	
	public MensagensSistema insertUsuario(UsuarioModel usuario) {
		if (!possuiUsuarioByCpf(usuario.getNu_cpf())) {
			try (PreparedStatement st = conectar().prepareStatement(SQL_INSERT_USER);) {

				st.setString(1, usuario.getNu_cpf());
				st.setString(2, usuario.getNo_usuario());
				st.setString(3, usuario.getDe_email());
				st.setString(4, usuario.getIc_situacao());
				st.setInt(5, usuario.getIc_perfil_acesso());
				st.setInt(6, usuario.getCo_funcao());
				st.setString(7, usuario.getNu_telefone());

				st.addBatch();
				st.executeBatch();

				return new MensagensSistema(MensagensEnum.obterPorFlag(MN001));
			} catch (SQLException ex) {
				return new MensagensSistema(MensagensEnum.obterPorFlag(MN034));
			}catch (Exception e) {
	            throw e;
	        }
		}

		return new MensagensSistema(MensagensEnum.obterPorFlag(MN034));
	}

	public MensagensSistema updatSituacao(UsuarioModel usuario, Integer id) {

		if (this.possuiUsuario(id)
				&& (usuario.getIc_situacao().toUpperCase().equals(ATIVO)
						|| usuario.getIc_situacao().toUpperCase().equals(INATIVO))) {
			try (PreparedStatement st = conectar().prepareStatement(SQL_UPDATE_SITUACAO);) {

				st.setString(1, usuario.getIc_situacao());
				st.setInt(2, id);
				st.execute();
				st.close();

				if (usuario.getIc_situacao().toUpperCase().equals(ATIVO)) {
					return new MensagensSistema(MensagensEnum.obterPorFlag(MN033));
				} else if (usuario.getIc_situacao().toUpperCase().equals(INATIVO)) {
					return new MensagensSistema(MensagensEnum.obterPorFlag(MN032));
				}
			} catch (SQLException ex) {
				return new MensagensSistema(MensagensEnum.obterPorFlag(MN099));
			}
		}
		return new MensagensSistema(MensagensEnum.obterPorFlag(MN098));
	}

	public MensagensSistema deletUsuario(Integer id) {
		if (this.possuiUsuario(id)) {
			try (PreparedStatement st = conectar().prepareStatement(SQL_DELETE_USUARIO);) {
				st.setInt(1, id);
				st.execute();
				st.close();

				return new MensagensSistema(MensagensEnum.obterPorFlag(MN005));

			} catch (SQLException ex) {
				return new MensagensSistema(MensagensEnum.obterPorFlag(MN099));
			}

		} else {
			return new MensagensSistema(MensagensEnum.obterPorFlag(MN098));
		}
	}

	public MensagensSistema updatUsuario(UsuarioModel usuario, Integer id) {
		if (this.possuiUsuario(id)) {
			try (PreparedStatement st = conectar().prepareStatement(SQL_UPDATE_USUARIO);) {
				st.setString(1, usuario.getNu_cpf());
				st.setString(2, usuario.getNo_usuario());
				st.setString(3, usuario.getDe_email());
				st.setString(4, usuario.getIc_situacao());
				st.setInt(5, usuario.getIc_perfil_acesso());
				st.setInt(6, usuario.getCo_funcao());
				st.setString(7, usuario.getNu_telefone());
				st.setInt(8, id);
				st.executeUpdate();
				st.close();

				return new MensagensSistema(MensagensEnum.obterPorFlag(MN030));

			} catch (SQLException ex) {
				return new MensagensSistema(MensagensEnum.obterPorFlag(MN099));
			}

		} else {
			return new MensagensSistema(MensagensEnum.obterPorFlag(MN098));
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Paginacao listUsuarios(String nome, String situacao, Short perfil, Integer nmPagina, Integer limiteRows) {
		
		Paginacao paginacao = new Paginacao();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			StringBuilder SQL_LIST_USUARIO = new StringBuilder();
			SQL_LIST_USUARIO.append(" FROM UsuarioModel ");

			if (possuiValor(nome) || possuiValor(situacao) || possuiValor(perfil)) {
				SQL_LIST_USUARIO.append(" WHERE 1 = 1 ");
				if (possuiValor(nome)) {SQL_LIST_USUARIO.append(" AND UPPER(no_usuario) LIKE :nome ");}
				if (possuiValor(situacao)) {SQL_LIST_USUARIO.append(" AND ic_situacao = :situacao ");}
				if (possuiValor(perfil)) {SQL_LIST_USUARIO.append(" AND ic_perfil_acesso = :perfil ");}
			}
			
			Query SQL_USUARIO = session.createQuery(SQL_LIST_USUARIO.toString(), UsuarioModel.class);
			
			if (possuiValor(nome)) {SQL_USUARIO.setParameter("nome",new StringBuilder("%").append(nome.toUpperCase()).append("%").toString());}
			if (possuiValor(situacao)) {SQL_USUARIO.setParameter("situacao", situacao.toUpperCase());}
			if (possuiValor(perfil)) {SQL_USUARIO.setParameter("perfil", perfil);}

			if (SQL_USUARIO.list().size() > 0) {

				limiteRows = (possuiValor(limiteRows) ? limiteRows : 10);
				nmPagina = (possuiValor(nmPagina) ? nmPagina : 1);
				int qtPag = (SQL_USUARIO.list().size() % 2 > 0) ? (SQL_USUARIO.list().size() / limiteRows + 1) : (SQL_USUARIO.list().size() / limiteRows);  SQL_USUARIO.setMaxResults(limiteRows); SQL_USUARIO.setFirstResult((nmPagina - 1) * limiteRows);

				
				paginacao.setQtd_total(SQL_USUARIO.list().size());
				paginacao.setQtd_paginas(qtPag);
				paginacao.setPrimeira_pagina(nmPagina == 1 ? true : false);
				paginacao.setUltima_pagina(nmPagina == qtPag ? true : false);
				paginacao.setDados(SQL_USUARIO.list());

				return paginacao;
			
			}
		}finally {
			return paginacao;
		}
	}
	

	public Boolean possuiUsuario(Integer id) {
		StringBuilder SQL_IS_USUARIO = new StringBuilder();
		SQL_IS_USUARIO.append("SELECT * FROM public.usuario_externo WHERE id = ? ");
		
		if(possuiValor(id)){
			
			try (PreparedStatement st = conectar().prepareStatement(SQL_IS_USUARIO.toString());) {
				if(possuiValor(id)){}else{}
				st.setInt(1, id);
				ResultSet result = st.executeQuery();
				if (result.next()) { 
					return true;
					} else { 
						return false; 
						}
			} catch (SQLException ex) {
				return false;
			}
		}
		return false;
	}

	public Boolean possuiUsuarioByCpf(String cpf) {
		StringBuilder SQL_IS_USUARIO = new StringBuilder();
		SQL_IS_USUARIO.append("SELECT * FROM public.usuario_externo WHERE nu_cpf = ? ");
		
		if(possuiValor(cpf)){
			try (PreparedStatement st = conectar().prepareStatement(SQL_IS_USUARIO.toString());) {
				if(possuiValor(cpf)){}else{}
				st.setString(1, cpf);
				ResultSet result = st.executeQuery();
				if (result.next()) { return true; } else { return false; }
			} catch (SQLException ex) {
				return false;
			}
		}
		return false;
	}

	public static boolean possuiValor(final Object valor) {

		boolean possuiValor = false;
		if (valor != null) {
			if (valor instanceof String || valor instanceof Character) { possuiValor = !valor.toString().trim().equals("");
			} else if (valor instanceof Collection<?>) { possuiValor = !((Collection<?>) valor).isEmpty();
			} else if (valor instanceof Map<?, ?>) { possuiValor = !((Map<?, ?>) valor).isEmpty();
			} else if (valor instanceof Object[]) { possuiValor = ((Object[]) valor).length > 0;
			} else { possuiValor = true;
			}
		}
		return possuiValor;
	}

	private Connection conectar() {
		try { return Conecta.criarConexao();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		}
		return null;
	}

}
