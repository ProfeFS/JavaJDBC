package edu.cesurformacion.programacion.bdd.ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppDBTodoJunto {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/db_users";
		String user = "postgres";
		String password = "1234";

		String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
		String selectSQL = "SELECT id, name, email FROM users";
		String updateSQL = "UPDATE users SET name = ?, email = ? WHERE id = ?";
		String deleteSQL = "DELETE FROM users WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
				PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
				PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);) {

			if (conn == null) {
				System.out.println("Fallo al conectar a la base de datos.");
			}

			System.out.println("Conectado a la base de datos PostgreSQL!");

			// recuperando usaurios
			ResultSet rs = selectStmt.executeQuery();
			System.out.println("Datos de usuarios\r");
			while (rs.next()) {
				int id = rs.getInt(1);
				int otraFormaId = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				System.out.println("ID: " + id + ", Nombre: " + name + ", email: " + email);
			}

			// creando un usuario
			insertStmt.setString(1, "Anna");
			insertStmt.setString(2, "anna@hola.com");

			int insertCount = insertStmt.executeUpdate();
			System.out.println("Registro agregado: " + insertCount);
			ResultSet rsGenetaredKeys = insertStmt.getGeneratedKeys();

			while (rsGenetaredKeys.next()) {
				System.out.println(rsGenetaredKeys.getInt(1));
			}

			// Actualizar un usuario
			// String updateSQL = "UPDATE users SET name = ?, email = ? WHERE id = ?";
			updateStmt.setString(1, "Nayrén");
			updateStmt.setString(2, "nayren@gmail.com");
			updateStmt.setInt(3, 1); // Suponiendo que sabemos que el ID 1 existe
			int updatedRows = updateStmt.executeUpdate();
			System.out.println("Filas modificadas: " + updatedRows);

			// Eliminar un usuario
			//String deleteSQL = "DELETE FROM users WHERE id = ?";
			deleteStmt.setInt(1, 9); // Suponiendo que queremos eliminar el usuario con ID 1
			int deletedRows= deleteStmt.executeUpdate();
			System.out.println("Filas eliminadas: " + deletedRows);

		} catch (SQLException e) {
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Message: " + e.getMessage());
			Throwable t = e.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
		}
	}

}
