package br.datainfo.utilitarios;

import java.util.List;

import br.datainfo.models.UsuarioModel;

public class Paginacao {

	private Integer qtd_total;
	private Integer qtd_paginas;
	private Boolean ultima_pagina;
	private Boolean primeira_pagina;
	private List<UsuarioModel> dados;

	public Integer getQtd_total() {
		return qtd_total;
	}

	public void setQtd_total(Integer qtd_total) {
		this.qtd_total = qtd_total;
	}

	public Integer getQtd_paginas() {
		return qtd_paginas;
	}

	public void setQtd_paginas(Integer qtd_paginas) {
		this.qtd_paginas = qtd_paginas;
	}

	public Boolean getUltima_pagina() {
		return ultima_pagina;
	}

	public void setUltima_pagina(Boolean ultima_pagina) {
		this.ultima_pagina = ultima_pagina;
	}

	public Boolean getPrimeira_pagina() {
		return primeira_pagina;
	}

	public void setPrimeira_pagina(Boolean primeira_pagina) {
		this.primeira_pagina = primeira_pagina;
	}

	public List<UsuarioModel> getDados() {
		return dados;
	}

	public void setDados(List<UsuarioModel> dados) {
		this.dados = dados;
	}

}

