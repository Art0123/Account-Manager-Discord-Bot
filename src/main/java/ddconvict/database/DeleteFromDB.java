package ddconvict.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Deletes specific table rows, or all rows depending on method
 */

public class DeleteFromDB {
	private Statement stmt;
	private String sql;
	
	public DeleteFromDB() {
		
	}

	public DeleteFromDB(String deleteUnit) {
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			PreparedStatement prstmt = con.prepareStatement("DELETE FROM Registration "
					+ "WHERE Prof = ?");
			prstmt.setString(1, deleteUnit);
			prstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteAllAccounts() {
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			this.stmt = con.createStatement();
			this.sql = "DELETE FROM Registration";
			this.stmt.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteCommandFromDB(String profke) {
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			PreparedStatement prstmt = con.prepareStatement("DELETE FROM Commands "
					+ "WHERE CommandList = ?");
			prstmt.setString(1, profke);
			prstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteAllCommandsFromDB() {
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			this.stmt = con.createStatement();
			this.sql = "DELETE FROM Commands "
					+ "WHERE CommandList NOT IN ('acc', 'add', 'remove', 'permissions', 'list')";
			this.stmt.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void DeletePermission(String userId) {
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			PreparedStatement prstmt = con.prepareStatement("DELETE FROM Permissions "
					+ "WHERE PermissionList = ?");
			prstmt.setString(1, userId);
			prstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
