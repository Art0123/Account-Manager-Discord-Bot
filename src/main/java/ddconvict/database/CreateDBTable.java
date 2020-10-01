package ddconvict.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Generates 3 tables : Registration, Commands, Permissions
 * Inserts default commands into Commands table
 * Inserts discord id of the first permissioned user, need to
 * provide it 2 times, where the comment //Insert your discord id here as a String, is added
 */

public class CreateDBTable {
	
	public CreateDBTable() {
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			Statement stmt = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Registration "
			+ "(id INTEGER auto_increment, "
			+ " prof VARCHAR(255), "
			+ " login VARCHAR(255), "
			+ " password VARCHAR(255), "
			+ " PRIMARY KEY (id))";
			
			stmt.executeUpdate(sql);
			System.out.println("Table Registration created");
			
			////////////////////////////////////////////////////////////////////////////////////////
			
			Statement stmt2 = con.createStatement();
			String sql2 = "CREATE TABLE IF NOT EXISTS Commands "
			+ "(id INTEGER auto_increment, "
			+ " CommandList VARCHAR(255), "
			+ " PRIMARY KEY (id))";
			
			stmt2.executeUpdate(sql2);
			System.out.println("Table Commands created");
			
			////////////////////////////////////////////////////////////////////////////////////////
			
			Statement stmt3 = con.createStatement();
			String sql3 = ("INSERT INTO Commands (CommandList) " 
					+ "SELECT 'acc' AS COMMANDLIST FROM DUAL WHERE NOT EXISTS "  
					+ "(SELECT CommandList FROM Commands WHERE CommandList = 'acc') UNION ALL "
					+ "SELECT 'add' AS COMMANDLIST FROM DUAL WHERE NOT EXISTS "  
					+ "(SELECT CommandList FROM Commands WHERE CommandList = 'add') UNION ALL "  
					+ "SELECT 'remove' AS COMMANDLIST FROM DUAL WHERE NOT EXISTS "  
					+ "(SELECT CommandList FROM Commands WHERE CommandList = 'remove') UNION ALL "  
					+ "SELECT 'list' AS COMMANDLIST FROM DUAL WHERE NOT EXISTS "  
					+ "(SELECT CommandList FROM Commands WHERE CommandList = 'list') UNION ALL "  
					+ "SELECT 'permissions' AS COMMANDLIST FROM DUAL WHERE NOT EXISTS "  
					+ "(SELECT CommandList FROM Commands WHERE CommandList = 'permissions')");
			
			stmt3.executeUpdate(sql3);
			System.out.println("Default commands: \'add\', \'acc\', \'remove\', \'list\', \'permissions\' - added succesfully");
			
			////////////////////////////////////////////////////////////////////////////////////////
			
			Statement stmt4 = con.createStatement();
			String sql4 = "CREATE TABLE IF NOT EXISTS Permissions "
			+ "(id INTEGER auto_increment, "
			+ " PermissionList VARCHAR(255), "
			+ " PRIMARY KEY (id))";
			
			stmt4.executeUpdate(sql4);
			System.out.println("Table Permissions created");
			
			////////////////////////////////////////////////////////////////////////////////////////
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Permissions (PermissionList) "
					+ "SELECT ? FROM DUAL WHERE NOT EXISTS(SELECT PermissionList FROM Permissions WHERE PermissionList = ?)");
			pstmt.setString(1, "194088158206361600"); //Insert your discord id here as a String
			pstmt.setString(2, "194088158206361600"); //Insert your discord id here as a String as well
			
			pstmt.executeUpdate();
			System.out.println("Initial admin permissions added");
			
			con.close();
			
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
	}
}
