package br.com.fabricadeprogramador.persistencia.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	Connection conn = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usuario) {
		String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?,?,MD5(?))";
		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.execute();
			System.out.println("Usuário cadastrado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET nome=?, login=?, senha=md5(?) WHERE id=?";
		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			preparador.execute();
			System.out.println("Alterado com sucesso!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Usuario usuario) {
		String sql = "DELETE FROM usuario WHERE id=?";
		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setInt(1, usuario.getId());
			preparador.execute();
			System.out.println("Usuario excluido!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Usuario usuario) {
		if (usuario.getId() == null) {
			cadastrar(usuario);
		} else {
			alterar(usuario);
		}
	}

	public Usuario buscarPorId(Integer id) {
		Usuario usuRetorno = null;
		String sql = "SELECT * FROM usuario WHERE id = ?";
		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setInt(1, id);
			preparador.execute();

			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return usuRetorno;
	}

	public List<Usuario> buscarTodos() {
		String sql = "SELECT * FROM usuario";
		List<Usuario> retornoBusca = new ArrayList<Usuario>();
		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Usuario usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				retornoBusca.add(usuRetorno);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retornoBusca;
	}

	public Usuario autenticar(Usuario usuConsulta) {
		Usuario usuRetorno = null;
		String sql = "SELECT * FROM usuario WHERE login = ? AND senha = MD5(?)";
		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				System.out.println("Usuario autenticado!");
			}else{
				System.out.println("Usuario NÃO autenticado!");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuRetorno;
	}
}
