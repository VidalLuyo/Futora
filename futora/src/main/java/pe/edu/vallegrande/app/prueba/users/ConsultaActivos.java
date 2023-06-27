package pe.edu.vallegrande.app.prueba.users;

import java.util.List;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class ConsultaActivos {

	public static void main(String[] args) {
		try {
			CrudApoderadoService service = new CrudApoderadoService();
			List<Apoderado> lista = service.getActive();
			for (Apoderado user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
