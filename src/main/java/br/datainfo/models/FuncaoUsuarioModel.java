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
@Table(name = "funcao_usuario_externo", indexes = { @Index(name = "pk_eprtb016", columnList = "co_funcao") })
public class FuncaoUsuarioModel implements Serializable {

	private static final long serialVersionUID = 4528724872382603248L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "co_funcao")
	private Integer co_funcao;

	@Column(name = "no_funcao")
	private String no_funcao;

	public Integer getCo_funcao() {
		return co_funcao;
	}

	public void setCo_funcao(Integer co_funcao) {
		this.co_funcao = co_funcao;
	}

	public String getNo_funcao() {
		return no_funcao;
	}

	public void setNo_funcao(String no_funcao) {
		this.no_funcao = no_funcao;
	}

}
