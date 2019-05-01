package br.datainfo.utilitarios;

public enum MensagensEnum {


	MN001(1L,"Cadastro efetuado com sucesso!"),
	MN005(5L,"Exclusão efetuada com sucesso."),
	MN030(30L,"Alteração efetuada com sucesso!"),
	MN031(31L,"Deseja realmente excluir o usuário?"),
	MN032(32L,"Usuário desabilitado com sucesso!"),
	MN033(33L,"Usuário habilitado com sucesso!"),
	MN034(34L,"Operação não realizada. Usuário já incluído."),
	MN035(35L,"Operação não realizada. CPF digitado é inválido."),
	MN098(98L,"Usuário não encontrado para a ação."),
	MN099(99L,"Erro do Sistema.");
	

	
	private Long flag;
	private String chaveDescricao;

	
	private MensagensEnum(Long flag, String chaveDescricao) {
		this.flag = flag;
		this.chaveDescricao = chaveDescricao;
	}
	public Long getFlag() {
		return flag;
	}
	public String getChaveDescricao() {
		return chaveDescricao;
	}
	public static String obterPorFlag(Long flag) {
		MensagensEnum eMensagem = null;
		for (MensagensEnum mensagem : values()) {
			if (mensagem.getFlag().equals(flag)) {
				eMensagem = mensagem;
				break;
			}
		}
		return eMensagem.getChaveDescricao();
	}
}

