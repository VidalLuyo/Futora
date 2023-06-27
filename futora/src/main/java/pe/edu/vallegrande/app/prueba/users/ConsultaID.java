package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudApoderadoService service = new CrudApoderadoService();
			Apoderado bean = service.getForId("1");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
