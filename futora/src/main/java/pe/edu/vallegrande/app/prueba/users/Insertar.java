package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Apoderado bean = new Apoderado("Vidal", "Luyo", "77277795", "921320441", "jesusluyo@gmail.com");
			CrudApoderadoService service = new CrudApoderadoService();
			service.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
