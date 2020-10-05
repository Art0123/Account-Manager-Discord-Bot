package ddconvict;

import javax.security.auth.login.LoginException;

public class Main {
	public static String adminId = "194088158206361600"; // insert your discord id
	
	public static void main(String[] args) throws LoginException { 	
		/**
		 * Before launching the bot, make sure to edit adminId string in this Main.java file
		 * To grant permissions to someone, in discord guild chat type !add permissions user_discord_id
		 */
		
		new Boter();
	}
}
