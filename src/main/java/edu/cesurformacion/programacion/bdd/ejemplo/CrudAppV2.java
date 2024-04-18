package edu.cesurformacion.programacion.bdd.ejemplo;

public class CrudAppV2 {
	public static void main(String[] args) {
		UsersDataBaseOperationsManagerV2 usuario = new UsersDataBaseOperationsManagerV2();

		// Crear usuario
		usuario.createUsuario(new User(0, "Anna", "hola@hola.com"));

		// Leer usuarios
		System.out.println("Usuarios:");
		usuario.readUsuarios().forEach(u -> System.out.println(u.getNombre() + ", " + u.getEmail()));

		// Actualizar usuario
		User usuarioToUpdate = new User(23, "María Updated", "mariaupdate@hola.com");
		usuario.updateUsuario(usuarioToUpdate);
		
		System.out.println("-------------------\r");
		System.out.println("Usuarios:");
		usuario.readUsuarios().forEach(u -> System.out.println(u.getNombre() + ", " + u.getEmail()));

		// Eliminar usuario
		usuario.deleteUsuario(23); //hay que saber el id en la tabla
		
		System.out.println("-------------------\r");
		System.out.println("Usuarios:");
		usuario.readUsuarios().forEach(u -> System.out.println(u.getNombre() + ", " + u.getEmail()));
	}
}