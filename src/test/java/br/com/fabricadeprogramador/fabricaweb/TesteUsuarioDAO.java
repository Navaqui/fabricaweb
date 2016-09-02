package br.com.fabricadeprogramador.fabricaweb;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {
	public static void main(String[] args) {
		// testeCadastrar();
		// testeAlterar();
		// testeExcluir();
		// testeSalvar();
		// testeBuscarPorId();
		testeBuscarTodos();
		testeAutenticar();

	}

	private static void testeCadastrar() {
		Usuario usuario = new Usuario();
		usuario.setNome("Fulano Cadastro");
		usuario.setLogin("fulanocadastro");
		usuario.setSenha("123");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastrar(usuario);
	}

	private static void testeAlterar() {
		Usuario usuario = new Usuario();
		usuario.setId(3);
		usuario.setNome("Ciclano Altera");
		usuario.setLogin("ciclanoaltera");
		usuario.setSenha("321");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alterar(usuario);
	}

	private static void testeExcluir() {
		Usuario usuario = new Usuario();
		usuario.setId(3);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
	}

	private static void testeSalvar() {
		Usuario usuario = new Usuario();
		usuario.setNome("Beltrano Salvar");
		usuario.setLogin("baltranosalvar");
		usuario.setSenha("567");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}

	private static void testeBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioRetorno = usuarioDAO.buscarPorId(4);
		if (usuarioRetorno != null) {
			System.out.println(usuarioRetorno);
		}
	}

	private static void testeBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> listaRetorno = usuarioDAO.buscarTodos();
		for (Usuario usu : listaRetorno) {
			System.out.println(usu.getId() + " " + usu.getNome() + " " + usu.getLogin() + " " + usu.getSenha());
		}
	}

	private static void testeAutenticar() {
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuario.setNome("Beltrano Salvar");
		usuario.setLogin("baltranosalvar");
		usuario.setSenha("567");

		Usuario usuarioRetorno = usuarioDAO.autenticar(usuario);
		if (usuarioRetorno != null) {
			System.out.println(usuarioRetorno);
		}
	}
}
