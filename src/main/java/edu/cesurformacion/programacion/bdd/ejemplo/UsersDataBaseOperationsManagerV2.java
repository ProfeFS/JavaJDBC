package edu.cesurformacion.programacion.bdd.ejemplo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDataBaseOperationsManagerV2 {
	private Connection conn;

	public UsersDataBaseOperationsManagerV2() {
		this.conn = DatabaseConnection.getConnection();
	}

	public void createUsuario(User usuario) {
		String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> readUsuarios() {
		List<User> usuarios = new ArrayList<>();
		String sql = "SELECT id, name, email FROM users";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				usuarios.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void updateUsuario(User usuario) {
		String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setInt(3, usuario.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUsuario(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}