package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.Role;

public class JdbcExample {
	private Connection getconnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mybatis?zeroDateTimeBehavior=convertToNull";
			String user = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		return connection;
	}

	public Role getRole(Long id) {
		Connection connection = getconnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select id,role_name,note from t_role where id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Long roleId = rs.getLong("id");
				String userName = rs.getString("role_name");
				String note = rs.getString("note");
				Role role = new Role();
				role.setId(id);
				role.setRoleName(userName);
				role.setNote(note);
				return role;
			}
		} catch (SQLException ex) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			this.close(rs, ps, connection);
		}
		return null;
	}

	private void close(ResultSet rs, Statement stmt, Connection connection) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		JdbcExample example = new JdbcExample();
		Role role = example.getRole(1L);
		System.err.println(role.getRoleName());
		System.err.println(role.getNote());
	}

}
