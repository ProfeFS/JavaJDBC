package edu.cesurformacion.programacion.bdd.ejemplo;

public class CrudApp {
	public static void main(String[] args) {
		UsersDataBaseOperationsManager usuario = new UsersDataBaseOperationsManager();

		// Crear usuario
		usuario.createUsuario(new User(0, "María", "hola@hola.com"));

		// Leer usuarios
		System.out.println("Usuarios:");
		usuario.readUsuarios().forEach(u -> System.out.println(u.getNombre() + ", " + u.getEmail()));

//		// Actualizar usuario
//		User usuarioToUpdate = new User(1, "Jane Doe Updated", "janeupdate@example.com");
//		usuario.updateUsuario(usuarioToUpdate);
//
//		// Eliminar usuario
//		usuario.deleteUsuario(1);
	}
}