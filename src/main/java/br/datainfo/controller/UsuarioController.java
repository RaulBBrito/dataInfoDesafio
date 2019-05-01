package br.datainfo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.datainfo.dao.UsuarioDao;
import br.datainfo.models.UsuarioModel;
import br.datainfo.utilitarios.MensagensSistema;
import br.datainfo.utilitarios.MensagensEnum;
import br.datainfo.utilitarios.ValidarCpf;

@RestController
public class UsuarioController {
	
	static Long MS035 = 35L;

	private UsuarioDao usuarioDao = new UsuarioDao();
	
	@PostMapping("/usuarios")
	public MensagensSistema insertUsuario(@RequestBody UsuarioModel user) {
		if (ValidarCpf.validarCPF(user.getNu_cpf())) { return usuarioDao.insertUsuario(user);
		} else { return new MensagensSistema(MensagensEnum.obterPorFlag(MS035)); }
	}

	@GetMapping("/usuarios")
	public Object listUsuarios(@RequestParam(required = false) String nome, String situacao, Short perfil,
			Integer pagina, Integer limite) {
		return usuarioDao.listUsuarios(nome, situacao, perfil, pagina, limite);
	}

	@PatchMapping("/usuarios/{id}")
	public MensagensSistema updatSituacao(@RequestBody UsuarioModel user, @PathVariable Integer id) {
		return usuarioDao.updatSituacao(user,id);
	}

	@DeleteMapping("/usuarios/{id}")
	public MensagensSistema deletUsuario(@PathVariable Integer id) {
		return usuarioDao.deletUsuario(id);
	}

	@PutMapping("/usuarios/{id}")
	public MensagensSistema updatUsuario(@RequestBody UsuarioModel user, @PathVariable Integer id) {
		if (ValidarCpf.validarCPF(user.getNu_cpf())) { return usuarioDao.updatUsuario(user, id);
		} else { return new MensagensSistema(MensagensEnum.obterPorFlag(MS035)); }
	}

}

