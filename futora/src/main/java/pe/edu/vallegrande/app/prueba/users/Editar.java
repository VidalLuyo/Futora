package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class Editar {

	public static void main(String[] args) {
		try {
			Apoderado bean = new Apoderado(1, "Jesús", "Luyo", "77277795", "921320441", "jesusluyo@gmail.com");
			CrudApoderadoService service = new CrudApoderadoService();
			service.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
