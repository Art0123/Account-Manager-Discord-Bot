package ddconvict;

import javax.security.auth.login.LoginException;

public class Main {
	
	public static void main(String[] args) throws LoginException { 	
		/**
		 * Before launching the bot, make sure to edit CreateDBTable.class
		 * at the end of that class you will find "Insert your discord id here as a String"
		 * insert your discord id to set up the first user with permissions
		 * as this bot only allows to use commands for people who have permissions
		 * because it was made with intent to deal with sensitive information[accounts,logins,passwords]
		 * so new users who joins channel won't be able to retrieve this information from the bot
		 * unless you grant them permissions first
		 * to grant permissions, in discord guild chat type !add permissions user_discord_id
		 */
		
		new Boter();
	}
}
