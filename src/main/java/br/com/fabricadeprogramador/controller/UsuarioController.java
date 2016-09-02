package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		System.out.println("Chamando o contrutor do Servlet !");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Iniciando o Servlet !");
	}

	@Override
	public void destroy() {
		System.out.println("Finalizando o Servlet !");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Requisição pelo método GET !");
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();

		if (request.getParameter("acao") != null && !request.getParameter("acao").isEmpty()) {
			if (request.getParameter("acao").equals("exec")) {
				usu.setId(Integer.parseInt(request.getParameter("id")));
				usuDAO.excluir(usu);
				response.getWriter().print("Removido com sucesso!");
			}
		} else {
			usu.setNome(request.getParameter("nome"));
			usu.setLogin(request.getParameter("login"));
			usu.setSenha(request.getParameter("senha"));
			usuDAO.salvar(usu);
			response.getWriter().print("Salvo com sucesso!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usu = new Usuario();
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			usu.setId(Integer.parseInt(request.getParameter("id")));
		}
		usu.setNome(request.getParameter("nome"));
		usu.setLogin(request.getParameter("login"));
		usu.setSenha(request.getParameter("senha"));
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		response.getWriter().print("Salvo comn sucesso!");
		System.out.println("Requisição pelo método POST !");
	}

}
