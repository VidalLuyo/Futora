package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudApoderadoService service = new CrudApoderadoService();
			service.delete("6");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
