package ddconvict.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import ddconvict.database.*;

/**
 * Provides command which extracts profession names and places them into a list
 */

public class ListCommand extends Command {
	
	public ListCommand() {
		super.name = "list";
		super.help = "provides list of available accounts by profession name";
	}

	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().isBot()) {
        	return;
        }
		
		if (new ReadFromDB().getPermissionList().contains(event.getAuthor().getId())) {
			if (!new ReadFromDB().getAccounts().isEmpty()) {
				event.reply(new ReadFromDB().getProfkes().toString().replace("[", "").replace("]", ""));
			} else {
				event.reply("No accounts in database");
			}		
		} else {
			event.reply("You don't have permissions to use this bot");
		}
	}
}
