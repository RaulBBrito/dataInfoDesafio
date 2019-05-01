package br.datainfo.utilitarios;

public class MensagensSistema {
	
	private String mensagem;
	
	public MensagensSistema(){}
	
	public MensagensSistema(String mensagem){
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String string) {
		this.mensagem = string;
	}

}

