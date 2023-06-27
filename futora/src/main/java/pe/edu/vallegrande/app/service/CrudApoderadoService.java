package pe.edu.vallegrande.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.model.Apoderado;
import pe.edu.vallegrande.app.service.spec.CrudServiceSpec;
import pe.edu.vallegrande.app.service.spec.RowMapper;

public class CrudApoderadoService implements CrudServiceSpec<Apoderado>, RowMapper<Apoderado> {

	private final String SQL_SELECT_ACTIVE = "SELECT * FROM APODERADO WHERE active='A'";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM APODERADO WHERE active='I'";
	private final String SQL_SELECT_ID = "SELECT * FROM APODERADO WHERE active='A' AND id=?";
	private final String SQL_SELECT_LIKE = "SELECT * FROM APODERADO WHERE names LIKE ? AND last_names LIKE ? AND active='A'";
	private final String SQL_INSERT = "INSERT INTO APODERADO (names, last_names, dni, cell_phone, email) VALUES (?,?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE APODERADO SET names=?, last_names=?, dni=?, cell_phone=?, email=? WHERE id=?";
	private final String SQL_DELETE = "UPDATE APODERADO SET active='I' WHERE id=?";
	private final String SQL_RESTORE = "UPDATE APODERADO SET active='A' WHERE id=?";
	private final String SQL_ELIMINATE = "DELETE FROM APODERADO WHERE id=?";

	@Override
	public List<Apoderado> getActive() {
		List<Apoderado> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Apoderado bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Apoderado> getInactive() {
		List<Apoderado> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Apoderado bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Apoderado getForId(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Apoderado bean = null;
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_ID);
			pstm.setInt(1, Integer.parseInt(id));
			rs = pstm.executeQuery();
			if (rs.next()) {
				bean = mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return bean;
	}

	@Override
	public List<Apoderado> get(Apoderado bean) {
		Connection cn = null;
		List<Apoderado> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Apoderado item;
		String names, last_name;
		names = "%" + UtilService.setStringVacio(bean.getNames()) + "%";
		last_name = "%" + UtilService.setStringVacio(bean.getLast_names()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, names);
			pstm.setString(2, last_name);
			rs = pstm.executeQuery();
			while (rs.next()) {
				item = mapRow(rs);
				lista.add(item);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return lista;
	}

	@Override
	public void insert(Apoderado bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getNames());
			pstm.setString(2, bean.getLast_names());
			pstm.setString(3, bean.getDni());
			pstm.setString(4, bean.getCell_phone());
			pstm.setString(5, bean.getEmail());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void update(Apoderado bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getNames());
			pstm.setString(2, bean.getLast_names());
			pstm.setString(3, bean.getDni());
			pstm.setString(4, bean.getCell_phone());
			pstm.setString(5, bean.getEmail());
			pstm.setInt(6, bean.getId());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_DELETE);
			pstm.setInt(1, Integer.parseInt(id));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void restore(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_RESTORE);
			pstm.setInt(1, Integer.parseInt(id));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo restaurar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void eliminate(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_ELIMINATE);
			pstm.setInt(1, Integer.parseInt(id));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public Apoderado mapRow(ResultSet rs) throws SQLException {
		Apoderado bean = new Apoderado();
		bean.setId(rs.getInt("id"));
		bean.setNames(rs.getString("names"));
		bean.setLast_names(rs.getString("last_names"));
		bean.setDni(rs.getString("dni"));
		bean.setEmail(rs.getString("email"));
		bean.setCell_phone(rs.getString("cell_phone"));
		bean.setActive(rs.getString("active"));
		return bean;
	}
}
