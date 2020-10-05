package ddconvict.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import ddconvict.database.DeleteFromDB;
import ddconvict.database.ReadFromDB;

/**
 * Provides possibility to remove accounts and permissions
 * There is one special command !remove all, which requires admin discord id
 * if executed, the command !remove all will remove all existing accounts
 * from database
 */

public class RemoveCommand extends Command {
	
	public RemoveCommand() {
		super.name = "remove";
		super.help = "removes specific account[requires profession name] -> !remove mage1"
				+ "\nCan remove all accounts[admin only] -> !remove all"
				+ "\nCan also remove permissions -> !remove permissions discord_id";
	}

	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().isBot()) {
			System.out.println("its bot!");
        	return;
        }
		String[] userTyped = event.getMessage().getContentRaw().split("\\s+");
	
		if (new ReadFromDB().getPermissionList().contains(event.getAuthor().getId())) {
			if (userTyped.length > 1
					&& !new ReadFromDB().getAccounts().toString().contains(userTyped[1]) 
					&& (!userTyped[1].equalsIgnoreCase("all") && !userTyped[1].equalsIgnoreCase("add")
					&& !userTyped[1].equalsIgnoreCase("remove") && !userTyped[1].equalsIgnoreCase("acc") 
					&& !userTyped[1].equalsIgnoreCase("list") && !userTyped[1].equalsIgnoreCase("permissions"))) {
					event.reply("No such account as: " + userTyped[1]);
	
				} else if (userTyped.length == 1) {
					event.reply("You need to specify account profession to remove, example !remove mage1");
	
				} else if (userTyped[1].equalsIgnoreCase("all") && event.getAuthor().getId().equals(Main.adminId)) {
					new DeleteFromDB().DeleteAllAccounts();
					new DeleteFromDB().DeleteAllCommandsFromDB();
					event.reply("Cleared all accounts");
					
				} else if ((userTyped[1].equalsIgnoreCase("all") && !event.getAuthor().getId().equals(Main.adminId))
					|| (userTyped[1].equalsIgnoreCase("permissions") && !event.getAuthor().getId().equals(Main.adminId))) {
					event.reply("You do not have permissions to do this");
					
				} else if (userTyped[1].equalsIgnoreCase("permissions") && event.getAuthor().getId().equals(Main.adminId)
					&& new ReadFromDB().getPermissionList().contains(userTyped[2])) {
					new DeleteFromDB().DeletePermission(userTyped[2]);
					event.reply("Permissions removed from user <@" + userTyped[2] + ">");
					
				} else if (userTyped[1].equalsIgnoreCase("permissions") && event.getAuthor().getId().equals(Main.adminId) 
					&& new ReadFromDB().getPermissionList().contains(userTyped[2])) {
					event.reply("This user didn't have any permissions from the beginning");
				} else if (userTyped[1].equalsIgnoreCase("add") 
					|| userTyped[1].equalsIgnoreCase("remove") 
					|| userTyped[1].equalsIgnoreCase("commands") 
					|| userTyped[1].equalsIgnoreCase("acc")
					|| userTyped[1].equalsIgnoreCase("list")) {
					event.reply("These commands cannot be removed");
					
				} else {
					new DeleteFromDB(userTyped[1]);
					new DeleteFromDB().DeleteCommandFromDB(userTyped[1]);
					event.reply("Account " + userTyped[1] + " is removed");
				}
		} else {
			event.reply("You don't have permissions to use this bot");
		}
	}
}
