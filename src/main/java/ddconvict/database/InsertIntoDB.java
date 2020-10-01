package ddconvict.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ddconvict.Account;

/**
 * Inserts into tables information about accounts, commands and permissions
 */

public class InsertIntoDB {
	private String prof, log, psw, command;
	
	public InsertIntoDB() {
		
		
	}
	
	public void insertCommandIntoDB(String command) {
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			this.command = command;
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Commands (CommandList) "
					+ "VALUES (?)");
			pstmt.setString(1, this.command);
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertAccountIntoDB(String prof, String log, String psw) {
		this.prof = prof;
		this.log = log;
		this.psw = psw;
		
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Registration (prof, login, password) "
					+ "VALUES (?,?,?)");
			pstmt.setString(1, this.prof);
			pstmt.setString(2, this.log);
			pstmt.setString(3, this.psw);
			pstmt.executeUpdate();
			
			con.close();
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void insertPermissionIntoDB(String permissionId) {
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Permissions (PermissionList) "
					+ "SELECT ? FROM DUAL WHERE NOT EXISTS(SELECT PermissionList FROM Permissions WHERE PermissionList = ?)");
			pstmt.setString(1, permissionId);
			pstmt.setString(2, permissionId);
			pstmt.executeUpdate();
			
			con.close();
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
