package pe.edu.vallegrande.app.prueba.users;

import java.util.List;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudApoderadoService Apoderadoservice = new CrudApoderadoService();
			List<Apoderado> lista = Apoderadoservice.getInactive();
			for (Apoderado user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
