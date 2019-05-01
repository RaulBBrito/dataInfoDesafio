package br.datainfo.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_externo", indexes = { @Index(name = "pk_eprtb008", columnList = "nu_cpf") })
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(name = "nu_cpf")
	private String nu_cpf;

	@Column(name = "no_usuario")
	private String no_usuario;

	@Column(name = "de_email")
	private String de_email;

	@Column(name = "ic_situacao")
	private String ic_situacao;

	@Column(name = "ic_perfil_acesso")
	private short ic_perfil_acesso;

	@Column(name = "co_funcao")
	private Integer co_funcao;

	@Column(name = "nu_telefone")
	private String nu_telefone;
	
	public UsuarioModel(){}
	
	public UsuarioModel(Integer idUsuario, String nu_cpf, String no_usuario, String de_email, String ic_situacao, short ic_perfil_acesso, Integer co_funcao, String nu_telefone){
		this.idUsuario = idUsuario;
		this.nu_cpf = nu_cpf;
		this.no_usuario = no_usuario;
		this.de_email = de_email;
		this.ic_situacao = ic_situacao;
		this.ic_perfil_acesso = ic_perfil_acesso;
		this.co_funcao = co_funcao;
		this.nu_telefone = nu_telefone;
	}

	public Integer getId() {
		return idUsuario;
	}

	public void setId(Integer id) {
		this.idUsuario = id;
	}

	public String getNu_cpf() {
		return nu_cpf;
	}

	public void setNu_cpf(String nu_cpf) {
		this.nu_cpf = nu_cpf;
	}

	public String getNo_usuario() {
		return no_usuario;
	}

	public void setNo_usuario(String no_usuario) {
		this.no_usuario = no_usuario;
	}

	public String getDe_email() {
		return de_email;
	}

	public void setDe_email(String de_email) {
		this.de_email = de_email;
	}

	public String getIc_situacao() {
		return ic_situacao;
	}

	public void setIc_situacao(String ic_situacao) {
		this.ic_situacao = ic_situacao;
	}

	public short getIc_perfil_acesso() {
		return ic_perfil_acesso;
	}

	public void setIc_perfil_acesso(short ic_perfil_acesso) {
		this.ic_perfil_acesso = ic_perfil_acesso;
	}

	public Integer getCo_funcao() {
		return co_funcao;
	}

	public void setCo_funcao(Integer co_funcao) {
		this.co_funcao = co_funcao;
	}

	public String getNu_telefone() {
		return nu_telefone;
	}

	public void setNu_telefone(String nu_telefone) {
		this.nu_telefone = nu_telefone;
	}

}
