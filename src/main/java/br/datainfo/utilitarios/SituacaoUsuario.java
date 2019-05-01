package br.datainfo.utilitarios;

public enum SituacaoUsuario {

	ATIVO('S', "Ativo"), 
	INATIVO('N', "Inativo");

	private Character flag;
	private String chaveDescricao;

	private SituacaoUsuario(Character flag, String chaveDescricao) {

		this.flag = flag;
		this.chaveDescricao = chaveDescricao;
	}
	public Character getFlag() {

		return flag;
	}
	public String getChaveDescricao() {

		return chaveDescricao;
	}
	public static SituacaoUsuario obterPorFlag(Character flag) {

		SituacaoUsuario eSituacaoUsuario = null;

		for (SituacaoUsuario situacaoUsuario : values()) {

			if (situacaoUsuario.getFlag().equals(flag)) {

				eSituacaoUsuario = situacaoUsuario;
				break;
			}
		}

		return eSituacaoUsuario;
	}

}

