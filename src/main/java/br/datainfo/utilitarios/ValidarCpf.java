package br.datainfo.utilitarios;

import java.util.Collection;
import java.util.Map;

public class ValidarCpf {

	public static boolean validarCPF(String validaCpf) {

		try {
			if (possuiValor(validaCpf)) {
				if (validaCpf.matches("[0-9]{11}") || validaCpf.matches("([0-9]{3}+[\\.]{1}+[0-9]{3}+[\\.]{1}+[0-9]{3}+[\\-]{1}+[0-9]{2})")) {

					validaCpf = validaCpf.replaceAll("\\.", "").replaceAll("-", "");

					String primeiroCaractere = validaCpf.substring(0, 1);
					String removerRepeticoes = validaCpf.replaceAll(primeiroCaractere, "");

					if (removerRepeticoes.length() > 0) {
						int somatorio1 = 0, somatorio2 = 0, peso1 = 10, peso2 = 11, numero;
						String[] valorCpf = validaCpf.split("");
						for (int i = 0; i < 10; i++) {
							numero = Integer.parseInt(valorCpf[i]);
							somatorio1 = (i < 9) ? somatorio1 + (numero * peso1) : somatorio1;
							somatorio2 = somatorio2 + (numero * peso2);
							peso1--;
							peso2--;
						}

						int valor1 = 11 - (somatorio1 % 11);
						int valor2 = 11 - (somatorio2 % 11);
						int digitoVerificador1 = (valor1 > 9) ? 0 : valor1;
						int digitoVerificador2 = (valor2 > 9) ? 0 : valor2;

						if (Integer.parseInt(valorCpf[9]) == digitoVerificador1
								&& Integer.parseInt(valorCpf[10]) == digitoVerificador2) {

							return true;

						}
					}
				}
			}

			return false;

		} catch (Exception e) {
			return false;
		}
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

}

