package ddconvict.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Updates Registration table, no other table needed update possibility in this project
 * because discord id most likely won't change for a user[unique id] for Permissions
 * as well as Commands table won't need old commands updated, as only login/password
 * is changes if the user wants to modify already existing account
 */

public class UpdateDBTable {
	private Statement stmt;
	
	public UpdateDBTable(String profke, String login, String psw) {
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			this.stmt = con.createStatement();
			PreparedStatement prstmt = con.prepareStatement("UPDATE Registration "
					+ "SET Login = ?, Password = ? "
					+ "WHERE Prof = ? ");
			prstmt.setString(1, login);
			prstmt.setString(2, psw);
			prstmt.setString(3, profke);
			prstmt.executeUpdate();
			
			con.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
