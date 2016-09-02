package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb_db", "postgres", "123");
		} catch (SQLException | ClassNotFoundException e) {

			throw new RuntimeException("Não conectado!", e);
		}

	}
}
