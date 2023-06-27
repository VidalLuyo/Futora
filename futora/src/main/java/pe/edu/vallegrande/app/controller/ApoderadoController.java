package pe.edu.vallegrande.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.CrudApoderadoService;

@WebServlet({ "/ApoderadoBuscar", "/ApoderadoProcesar", "/ApoderadoActualizar", "/ApoderadoHistorial" })
public class ApoderadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CrudApoderadoService service = new CrudApoderadoService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/ApoderadoBuscar":
			buscar(request, response);
			break;
		case "/ApoderadoProcesar":
			procesar(request, response);
			break;
		case "/ApoderadoActualizar":
			actualizar(request, response);
			break;
		case "/ApoderadoHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		Apoderado bean = new Apoderado();
		bean.setId(Integer.parseInt(request.getParameter("id")));;
		bean.setNames(request.getParameter("names"));
		bean.setLast_names(request.getParameter("last_names"));
		bean.setDni(request.getParameter("dni"));
		bean.setEmail(request.getParameter("email"));
		bean.setCell_phone(request.getParameter("cell_phone"));
		// Proceso
		try {
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO: 
				service.insert(bean);
				break;
			case ControllerUtil.CRUD_EDITAR: 
				service.update(bean);
				break;
			case ControllerUtil.CRUD_ELIMINAR: 
				service.delete(bean.getId().toString());
				break;
			case ControllerUtil.CRUD_RESTAURAR: 
				service.restore(bean.getId().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE: 
				service.eliminate(bean.getId().toString());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Datos
		String names = request.getParameter("names");
		String last_name = request.getParameter("last_name");
		// Proceso
		Apoderado bean = new Apoderado();
		bean.setNames(names);
		bean.setLast_names(last_name);
		List<Apoderado> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Apoderado> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Apoderado> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
