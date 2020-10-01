package ddconvict.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ddconvict.Account;

/**
 * Reads information about all tables
 */

public class ReadFromDB {
	private String prof, login, password, sql, command, permission;
	private ResultSet rs;
	private Statement stmt;

	
	public ReadFromDB() {
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			this.stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Account> getAccounts() {
		ArrayList<Account> accounts = new ArrayList<>();
		this.sql = "SELECT * FROM Registration";
		
		try {
			this.rs = stmt.executeQuery(sql);
			while (this.rs.next()) {
				this.prof = rs.getNString("prof");
				this.login = rs.getNString("login");
				this.password = rs.getNString("password");
				
				accounts.add(new Account(this.prof, this.login, this.password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public Account getAccFromDB(String profke) {
		for (Account acc: this.getAccounts()) {
			if (acc.getProf().equals(profke)) {
				return acc;
			}
		}
		return null;
	}
	
	public ArrayList<String> getCommandList() {
		ArrayList<String> allCommands = new ArrayList<>();
		this.sql = "SELECT * FROM Commands";
		
		try {
			this.rs = stmt.executeQuery(sql);
			while (this.rs.next()) {
				this.command = rs.getNString("CommandList");
				allCommands.add(this.command);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCommands;
	}
	
	public ArrayList<String> getPermissionList() {
		ArrayList<String> allPermissions = new ArrayList<>();
		this.sql = "SELECT * FROM Permissions";
		
		try {
			this.rs = stmt.executeQuery(sql);
			while (this.rs.next()) {
				this.permission = rs.getNString("PermissionList");
				allPermissions.add(this.permission);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPermissions;
	}
	
	public ArrayList<String> getProfkes() {
		ArrayList<String> allProfkes = new ArrayList<>();
		this.sql = "SELECT * FROM Commands";
		
		try {
			this.rs = stmt.executeQuery(sql);
			while (this.rs.next()) {
				if (rs.getNString("CommandList").equals("add") || rs.getNString("CommandList").equals("acc") 
				|| rs.getNString("CommandList").equals("remove") || rs.getNString("CommandList").equals("list")
				|| rs.getNString("CommandList").equals("permissions")) {
					continue;
				} else {
					this.command = rs.getNString("CommandList");
					allProfkes.add(this.command);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allProfkes;
	}

}
