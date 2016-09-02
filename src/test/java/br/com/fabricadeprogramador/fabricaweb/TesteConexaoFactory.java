package br.com.fabricadeprogramador.fabricaweb;

import br.com.fabricadeprogramador.persistencia.jdbc.ConexaoFactory;

public class TesteConexaoFactory {

	public static void main(String[] args) {
		ConexaoFactory.getConnection();

	}

}
