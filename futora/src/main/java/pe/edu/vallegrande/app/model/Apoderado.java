package pe.edu.vallegrande.app.model;

public class Apoderado {
	Integer id;
	String names;
	String last_names;
	String dni;
	String cell_phone;
	String email;
	String active;

	public Apoderado() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Apoderado(Integer id, String names, String last_names, String dni, String cell_phone, String email) {
		super();
		this.id = id;
		this.names = names;
		this.last_names = last_names;
		this.dni = dni;
		this.cell_phone = cell_phone;
		this.email = email;
	}



	public Apoderado(String names, String last_names, String dni, String cell_phone, String email) {
		super();
		this.names = names;
		this.last_names = last_names;
		this.dni = dni;
		this.cell_phone = cell_phone;
		this.email = email;
	}


	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLast_names() {
		return last_names;
	}

	public void setLast_names(String last_names) {
		this.last_names = last_names;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Apoderado [id=" + id + ", names=" + names + ", last_names=" + last_names + ", dni=" + dni
				+ ", cell_phone=" + cell_phone + ", email=" + email + ", active=" + active + "]";
	}

}
