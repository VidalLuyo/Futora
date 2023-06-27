package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudApoderadoService service = new CrudApoderadoService();
			service.restore("6");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
