package ddconvict.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import ddconvict.Account;
import ddconvict.database.ReadFromDB;

/**
 * Provides all accounts information or specific account information
*/

public class AccCommand extends Command {
	
	public AccCommand() {
		super.name = "acc";
		super.aliases = new String[] {"accounts"};
		super.help = "returns all accounts login/password information in PM -> !acc "
				+ "\nCan also be used to retrieve single account -> !acc mage1";
	}

	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().isBot()) {
			System.out.println("its bot!");
        	return;
        }
		String[] userTyped = event.getMessage().getContentRaw().split("\\s+");
		
		if (new ReadFromDB().getPermissionList().contains(event.getAuthor().getId())) {
			if (!new ReadFromDB().getAccounts().isEmpty() && userTyped.length == 1 ) {
				event.replyInDm(new ReadFromDB().getAccounts().toString()
						.replace(",", "--------------" + "\n")
		                .replace("[", "--------------" + "\n")
		                .replace("]", "--------------" + "\n")
		                .replace(" ", "")
		                .trim());
			} else if (!new ReadFromDB().getAccounts().isEmpty() && userTyped.length == 2 && new ReadFromDB().getAccounts().toString().contains(userTyped[1])) {
				for (Account acc : new ReadFromDB().getAccounts()) {
					if (acc.getProf().equalsIgnoreCase(userTyped[1])) {
						event.replyInDm(new ReadFromDB().getAccFromDB(userTyped[1]).toString());
					} 
				}
			} else if (userTyped.length > 1 && !new ReadFromDB().getAccounts().toString().contains(userTyped[1])) {
				event.reply("No account with profession: " + userTyped[1]);
			} else {
				event.reply("No accounts in database");
			}
		} else {
			event.reply("You don't have permissions to use this bot");
		}
		
	}

}
