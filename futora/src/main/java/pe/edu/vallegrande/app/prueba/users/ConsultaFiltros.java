package pe.edu.vallegrande.app.prueba.users;

import java.util.List;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			Apoderado bean = new Apoderado();
			bean.setNames("o");
			bean.setLast_names("");
			CrudApoderadoService service = new CrudApoderadoService();
			List<Apoderado> lista = service.get(bean);
			for (Apoderado user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
